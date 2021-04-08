package com.pwc.denali2.estimator.security;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.GenericFilterBean;

import com.pwc.denali2.estimator.util.cryptoToken;

public class TokenAuthenticationFilter extends GenericFilterBean {

	private List<GrantedAuthority> getGrantedAuthorities(
			com.pwc.denali2.estimator.model.User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new SimpleGrantedAuthority("ROLE_SUPERUSER"));

		return authorities;
	}

	@Override
	public void doFilter(final ServletRequest request,
			final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;

		// extract token from header
		try{
		final String accessToken = URLDecoder.decode(httpRequest.getParameter("token").replace("+", "%2B"),"UTF-8");
		
		if (null != accessToken) {
			if (SecurityContextHolder.getContext().getAuthentication() != null) {
				SecurityContextHolder.clearContext();
//				chain.doFilter(request, response);
//				return;
			}
			// get and check whether token is valid ( from DB or file wherever
			// you are storing the token)
			// cryptoToken crypto = new cryptoToken();
			String plainText = null;
			try {
				plainText = cryptoToken.decrypt(accessToken);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (accessToken != null) {
				String[] tokenArr = plainText.split("_");
				String ssoId = tokenArr[0];
				String projId = tokenArr[1];
				
				HttpSession session = ((HttpServletRequest) request).getSession();
				session.setAttribute("current_proj_id_token", projId);
				// Populate SecurityContextHolder by fetching relevant
				// information using token
				final User user = new User(ssoId, "", true, true, true, true,
						this.getGrantedAuthorities(null));
				final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						user, null, user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(
						authentication);

			}
		}
		}catch(Exception e){
			
		}
		chain.doFilter(request, response);
	}

}
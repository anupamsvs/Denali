package com.pwc.denali2.estimator.configuration;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({ "com.pwc.denali2.estimator.configuration" })
@PropertySource(value = { "classpath:ldap.properties" })
public class LdapConfig {

	private static LdapContext ctx = null;
	String dn = "OU=PwC Accounts,OU=Accounts and Groups,OU=US,DC=nam,DC=ad,DC=pwcinternal,DC=com";

	public LdapContext connetLDAP(String userName, String password) {

		String ldapDn = "CN=" + userName + ",OU=PwC Accounts,OU=Accounts and Groups,OU=US,DC=nam,DC=ad,DC=pwcinternal,DC=com";
		String ldapFactory = "com.sun.jndi.ldap.LdapCtxFactory";
		String ldapUrl = "ldap://matlkifs1adp103.nam.ad.pwcinternal.com:389";

		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, ldapFactory);

		env.put(Context.PROVIDER_URL, ldapUrl);

		env.put(Context.SECURITY_PRINCIPAL, ldapDn);

		env.put(Context.SECURITY_CREDENTIALS, password);

		try {
			ctx = new InitialLdapContext(env, null);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return ctx;
	}

	public void closeLdap() {
		try {
			this.ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public List<UserEngagement> GetADInfo(String name) {
		List<UserEngagement> userList = new ArrayList<UserEngagement>();
		String userName = name;
		if (userName == null) {
			userName = "";
		}
		String company = "";
		String result = "";
		try {

			String searchFilter = "displayName=*" + userName + "*";

			String[] returnedAtts = null;//{ "emailaddress","mail", "displayName" };
			SearchControls searchCtls = new SearchControls();

			if (returnedAtts != null && returnedAtts.length > 0) {
				searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
				searchCtls.setReturningAttributes(returnedAtts);
			} else {
				searchCtls.setReturningAttributes(null);
			}

			NamingEnumeration answer = ctx.search(dn, searchFilter, searchCtls);

			int totalResults = 0;
			int rows = 0;
			while (answer.hasMoreElements()) {
				UserEngagement ue = new UserEngagement();
				SearchResult sr = (SearchResult) answer.next();
				++rows;
				String dn = sr.getName();

				Attributes Attrs = sr.getAttributes();
				if (Attrs != null) {
					try {
						for (NamingEnumeration ne = Attrs.getAll(); ne.hasMore();) {
							Attribute Attr = (Attribute) ne.next();

							for (NamingEnumeration e = Attr.getAll(); e.hasMore(); totalResults++) {
								company = e.next().toString();

								if ("mail".equals(Attr.getID().toString())) {
									ue.setEmail(company);
								}
								//Added GUID - ananth
								ue.setGuid(dn.toString());
									
								if ("displayName".equals(Attr.getID().toString())) {
									ue.setUserName(company);
								}
							}
						}
					} catch (NamingException e) {
					}
				}
				userList.add(ue);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		System.out.println(userList);
		return userList;
	}
}

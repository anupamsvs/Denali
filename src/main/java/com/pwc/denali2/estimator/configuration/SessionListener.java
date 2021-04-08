package com.pwc.denali2.estimator.configuration;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationListener;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;

@Component
public class SessionListener implements ApplicationListener<HttpSessionDestroyedEvent> {

    public void onApplicationEvent(HttpSessionDestroyedEvent evt) {
        HttpSession session = evt.getSession();
        // Your logic here
        System.out.println(session);
    }
}
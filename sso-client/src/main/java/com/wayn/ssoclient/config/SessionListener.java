package com.wayn.ssoclient.config;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionAttributeListener, HttpSessionListener{

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session-create:" + se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session-destroyed:" + se.getSession());
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        System.out.println("session-add:" + se.getName() + "   " + se.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("session-remove:" + se.getName() + "   " + se.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        System.out.println("session-replaced:" + se.getName() + "   " + se.getValue());
    }
}

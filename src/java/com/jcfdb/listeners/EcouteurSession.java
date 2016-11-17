/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jcfdb.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author moumene
 */
public class EcouteurSession implements HttpSessionListener {
    
    private static final List SESSIONS = new ArrayList();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // http://stackoverflow.com/questions/3092363/how-can-i-load-java-httpsession-from-jsessionid/3092495#3092495
        HttpSession session = se.getSession();
        ServletContext appli = session.getServletContext();
        int n = Integer.parseInt(appli.getAttribute("nbConnectes").toString());
        n++;
        SESSIONS.add(session);
        System.out.println(n+"eme Session ouverte");
        appli.setAttribute("nbConnectes", n);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext appli = se.getSession().getServletContext();
        int n = Integer.parseInt(appli.getAttribute("nbConnectes").toString());
        n--;
        SESSIONS.remove(se.getSession());
        System.out.println((n+1)+"eme Session ferm�e");
        appli.setAttribute("nbConnectes", n);
    }    
    
    public static List getAllSessions() {
        return SESSIONS;
    }
}

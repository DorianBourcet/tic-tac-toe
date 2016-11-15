/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jcfdb.listeners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author moumene
 */
public class EcouteurSession implements HttpSessionListener {
    
    private void ajouterJoueur(HttpSessionEvent hse) {
        List joueurs = (ArrayList)hse.getSession().getServletContext().getAttribute("listeJoueurs");
        joueurs.add(hse.getSession().getAttribute("connecte"));
        hse.getSession().getServletContext().setAttribute("listeJoueurs", joueurs);
    }
    
    private void enleverJoueur(HttpSessionEvent hse) {
        List joueurs = (ArrayList)hse.getSession().getServletContext().getAttribute("listeJoueurs");
        // A continuer http://www.java67.com/2014/03/2-ways-to-remove-elementsobjects-from-ArrayList-java.html
        joueurs.remove(hse.getSession().getAttribute("connecte"));
        hse.getSession().getServletContext().setAttribute("listeJoueurs", joueurs);
        
        List invitations = (ArrayList)hse.getSession().getServletContext().getAttribute("listeInvitations");
        
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ajouterJoueur(se);
        ServletContext appli = se.getSession().getServletContext();
        int n = Integer.parseInt(appli.getAttribute("nbConnectes").toString());
        n++;
        System.out.println(n+"eme Session ouverte");
        appli.setAttribute("nbConnectes", n);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        enleverJoueur(se);
        ServletContext appli = se.getSession().getServletContext();
        int n = Integer.parseInt(appli.getAttribute("nbConnectes").toString());
        n--;
        System.out.println((n+1)+"eme Session ferm�e");
        appli.setAttribute("nbConnectes", n);
    }    
}

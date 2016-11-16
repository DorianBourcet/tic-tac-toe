/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcfdb.entites;

import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author dbourcet
 */
public class GestionnaireJeu {
    
    private HttpSession session;

    public GestionnaireJeu(HttpSession joueur) {
        this.session = joueur;
    }

    /*public Joueur getJoueur() {
        return joueur;
    }*/

    /*public void setUnJoueur(Joueur joueur) {
        this.joueur = joueur;
    }*/
    
    public void ajouterJoueur() {
        List joueurs = (ArrayList)session.getServletContext().getAttribute("listeJoueurs");
        joueurs.add(session.getAttribute("connecte"));
        session.getServletContext().setAttribute("listeJoueurs", joueurs);
    }
    
    public void enleverJoueur() {
        List joueurs = (ArrayList)session.getServletContext().getAttribute("listeJoueurs");
        // A continuer http://www.java67.com/2014/03/2-ways-to-remove-elementsobjects-from-ArrayList-java.html
        //Iterator itr = joueurs.iterator();
        joueurs.remove((String)session.getAttribute("connecte"));
        session.getServletContext().setAttribute("listeJoueurs", joueurs);
        
        List invitations = (ArrayList)session.getServletContext().getAttribute("listeInvitations");
        Iterator itr = invitations.iterator();
        Invitation uneInvitation;
        while (itr.hasNext()) {
            uneInvitation = (Invitation)itr.next();
            if (uneInvitation.getInvite().getNom().equals(session.getAttribute("connecte")) || uneInvitation.getHote().getNom().equals(session.getAttribute("connecte")))
                itr.remove();
        }
        
    }
    
    public List getMaListeJoueurs() {
        List joueurs = (ArrayList)session.getServletContext().getAttribute("listeJoueurs");
        joueurs.remove(session.getAttribute("connecte"));
        return joueurs;
    }
    
    public String getMaListeJoueursJSON() {
        List joueurs = (ArrayList)session.getServletContext().getAttribute("listeJoueurs");
        joueurs.remove((String)session.getAttribute("connecte"));
        Iterator itr = joueurs.iterator();
        String json = "[";
        while (itr.hasNext()) {
            json +="\""+itr.next()+"\"";
            if (itr.hasNext())
                json += ", ";
        }
        json += "]";
        return json;
    }
    
    public List getMaListeInvitations() {
        List invitations = (ArrayList)session.getServletContext().getAttribute("listeInvitations");
        Iterator itr = invitations.iterator();
        while(itr.hasNext()) {
            Invitation uneInvitation = (Invitation)itr.next();
            if (!uneInvitation.getInvite().getNom().equals(session.getAttribute("connecte"))) {
                itr.remove();
            }
        }
        return (List)itr;
    }
    
    public Partie getMaPartie() {
        List parties = (ArrayList)session.getServletContext().getAttribute("listeParties");
        Iterator itr = parties.iterator();
        while(itr.hasNext()) {
            Partie unePartie = (Partie)itr.next();
            if (unePartie.getJoueur1().getNom().equals(session.getAttribute("connecte")) || unePartie.getJoueur2().getNom().equals(session.getAttribute("connecte")))
                return unePartie;
        }
        return null;
    }
    
}

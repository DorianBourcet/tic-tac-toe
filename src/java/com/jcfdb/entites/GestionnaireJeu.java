/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcfdb.entites;

import com.jcfdb.listeners.EcouteurApplication;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ListIterator;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author dbourcet
 */
public class GestionnaireJeu {

    /*public GestionnaireJeu(HttpServletRequest requete) {
        this.requete = requete;
    }*/

    /*public Joueur getJoueur() {
        return joueur;
    }*/

    /*public void setUnJoueur(Joueur joueur) {
        this.joueur = joueur;
    }*/
    
    public static void ajouterJoueur(HttpServletRequest requete) {
        List joueurs = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeJoueurs");
        joueurs.add(requete.getSession().getAttribute("connecte"));
        EcouteurApplication.APPLI.setAttribute("listeJoueurs", joueurs);
    }
    
    public static void enleverJoueur(HttpServletRequest requete) {
        List joueurs = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeJoueurs");
        // A continuer http://www.java67.com/2014/03/2-ways-to-remove-elementsobjects-from-ArrayList-java.html
        //Iterator itr = joueurs.iterator();
        joueurs.remove((String)requete.getSession().getAttribute("connecte"));
        EcouteurApplication.APPLI.setAttribute("listeJoueurs", joueurs);
        
        List invitations = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeInvitations");
        Iterator itr = invitations.iterator();
        Invitation uneInvitation;
        while (itr.hasNext()) {
            uneInvitation = (Invitation)itr.next();
            if (uneInvitation.getInvite().getNom().equals(requete.getSession().getAttribute("connecte")) || uneInvitation.getHote().getNom().equals(requete.getSession().getAttribute("connecte")))
                itr.remove();
        }
        
    }
    
    public static List getListeJoueurs() {
        List joueurs = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeJoueurs");
        //joueurs.remove(requete.getSession().getAttribute("connecte"));
        return joueurs;
    }
    
    public static String getListeJoueursJSON() {
        List joueurs = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeJoueurs");
        //System.out.println("Session : "+requete.getSession().getAttribute("connecte"));
        //String currentUser = (String) session.getAttribute("connecte");
        Iterator itr = joueurs.iterator();
        /*Boolean supprime = false;
        while (itr.hasNext() && !supprime) {
            String currentUser = (String)itr.next();
            System.out.println(currentUser);
            if (currentUser == (String)requete.getSession().getAttribute("connecte")) {
                itr.remove();
                supprime = true;
            }
        }*/
        
        //joueurs.remove(currentUser);
        //Iterator itr2 = joueurs.iterator();
        String json = "[";
        while (itr.hasNext()) {
            json +="\""+itr.next()+"\"";
            if (itr.hasNext())
                json += ", ";
        }
        json += "]";
        return json;
    }
    
    public List getListeInvitations() {
        List invitations = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeInvitations");
        /*Iterator itr = invitations.iterator();
        while(itr.hasNext()) {
            Invitation uneInvitation = (Invitation)itr.next();
            if (!uneInvitation.getInvite().getNom().equals(requete.getSession().getAttribute("connecte"))) {
                itr.remove();
            }
        }*/
        return invitations;
    }
    
    public Partie getPartie(HttpServletRequest requete) {
        List parties = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeParties");
        Iterator itr = parties.iterator();
        while(itr.hasNext()) {
            Partie unePartie = (Partie)itr.next();
            if (unePartie.getJoueur1().getNom().equals(requete.getSession().getAttribute("connecte")) || unePartie.getJoueur2().getNom().equals(requete.getSession().getAttribute("connecte")))
                return unePartie;
        }
        return null;
    }
    
}

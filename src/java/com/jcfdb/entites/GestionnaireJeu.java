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
    
    private Joueur joueur;
    private HttpSession session;

    public GestionnaireJeu(Joueur joueur) {
        this.joueur = joueur;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setUnJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
    
    public List getMaListeJoueurs() {
        List joueurs = (ArrayList)session.getServletContext().getAttribute("listeJoueurs");
        joueurs.remove(session.getAttribute("connecte"));
        return joueurs;
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

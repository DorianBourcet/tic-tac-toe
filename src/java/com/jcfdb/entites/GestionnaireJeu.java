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
    
    private static List getListeJoueurs() {
        return (ArrayList)EcouteurApplication.APPLI.getAttribute("listeJoueurs");
    }
    
    private static List getListeInvitations() {
        return (ArrayList)EcouteurApplication.APPLI.getAttribute("listeInvitations");
    }
    
    private static List getListeParties() {
        return (ArrayList)EcouteurApplication.APPLI.getAttribute("listeParties");
    }
    
    /*private static void setListeJoueurs(List joueurs) {
        EcouteurApplication.APPLI.setAttribute("listeJoueurs", joueurs);
    }
    
    private static void setListeInvitations(List invitationss) {
        EcouteurApplication.APPLI.setAttribute("listeInvitations", invitationss);
    }
    
    private static void setListeParties(List parties) {
        EcouteurApplication.APPLI.setAttribute("listeParties", parties);
    }*/
    
    private static Partie getPartie(HttpServletRequest requete) {
        List parties = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeParties");
        Iterator itr = parties.iterator();
        while(itr.hasNext()) {
            Partie unePartie = (Partie)itr.next();
            if (unePartie.getJoueur1().getNom().equals(requete.getSession().getAttribute("connecte")) || unePartie.getJoueur2().getNom().equals(requete.getSession().getAttribute("connecte")))
                return unePartie;
        }
        return null;
    }
    
    public static String getListeJoueursJSON() {
        List joueurs = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeJoueurs");
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
    
    public static String getListeInvitationsJSON() {
        List invitations = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeInvitations");
        Iterator itr = invitations.iterator();
        String json = "[";
        Invitation uneInvitation;
        while (itr.hasNext()) {
            uneInvitation = (Invitation)itr.next();
            
            json +="{\"hote\":\""+uneInvitation.getHote().getNom()+"\",\"invite\":\""+uneInvitation.getInvite().getNom()+"\"}";
            if (itr.hasNext())
                json += ", ";
        }
        json += "]";
        return json;
    }
    
    public static void ajouterJoueur(HttpServletRequest requete) {
        List joueurs = getListeJoueurs();
        joueurs.add(requete.getSession().getAttribute("connecte"));
    }
    
    public static void enleverJoueur(HttpSession session) {
        List joueurs = getListeJoueurs();
        joueurs.remove((String)session.getAttribute("connecte"));
    }
    
    public static boolean ajouterInvitation(HttpServletRequest requete, String invite) {
        List joueurs = getListeJoueurs();
        List invitations = getListeInvitations();
        Iterator itrJ = joueurs.iterator();
        Iterator itrI = invitations.iterator();
        String unJoueur;
        while (itrJ.hasNext()) {
            unJoueur = (String)itrJ.next();
            if (unJoueur.equals(invite)) {
                Invitation uneInvitation = new Invitation((String)requete.getSession().getAttribute("connecte"),invite);
                Invitation currentInvitation;
                while (itrI.hasNext()) {
                    currentInvitation = (Invitation)itrI.next();
                    if ((currentInvitation.getHote().getNom().equals(uneInvitation.getHote().getNom())
                            && currentInvitation.getInvite().getNom().equals(uneInvitation.getInvite().getNom()))
                            || (currentInvitation.getHote().getNom().equals(uneInvitation.getInvite().getNom())
                            && currentInvitation.getInvite().getNom().equals(uneInvitation.getHote().getNom())))
                        return false;
                }
                invitations.add(uneInvitation);
                return true;
            }
        }
        return false;
    }
    
    public static void enleverInvitation(String hote, String invite) {
        List invitations = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeInvitations");
        Iterator itr = invitations.iterator();
        while(itr.hasNext()) {
            Invitation uneInvitation = (Invitation)itr.next();
            if (uneInvitation.getHote().getNom().equals(hote) && uneInvitation.getInvite().getNom().equals(invite))
                itr.remove();
        }
    
    }
    
    public static void enleverInvitations(HttpSession session) {
        List invitations = getListeInvitations();
        Iterator itr = invitations.iterator();
        Invitation uneInvitation;
        while (itr.hasNext()) {
            uneInvitation = (Invitation)itr.next();
            if (uneInvitation.getInvite().getNom().equals(session.getAttribute("connecte")) || uneInvitation.getHote().getNom().equals(session.getAttribute("connecte")))
                itr.remove();
        }
    }
    
    public static void ajouterPartie(HttpServletRequest requete, String joueur) {
        List parties = getListeParties();
        Partie partie = new Partie((String)requete.getSession().getAttribute("connecte"),joueur);
        partie.initialiser();
        parties.add(partie);
    }
    
    public static void enleverPartie(HttpSession session) {
        List parties = getListeParties();
        Iterator itr = parties.iterator();
        while(itr.hasNext()) {
            Partie unePartie = (Partie)itr.next();
            if (unePartie.getJoueur1().getNom().equals(session.getAttribute("connecte")) || unePartie.getJoueur2().getNom().equals(session.getAttribute("connecte")))
                itr.remove();
        }
    
    }
    
    public static boolean jouerCase(HttpServletRequest requete, int y, int x) {
        List parties = getListeParties();
        Iterator itr = parties.iterator();
        while(itr.hasNext()) {
            Partie unePartie = (Partie)itr.next();
            if (unePartie.getMain().getNom().equals(requete.getSession().getAttribute("connecte"))
                    && (unePartie.getGrille().getCase(y, x) == ' ')) {
                unePartie.remplirCase(y,x);
                if (unePartie.getGrille().alignement()) {
                    unePartie.designeVainqueur();
                    unePartie.setTerminee(Boolean.TRUE);
                    return true;
                }
                if (unePartie.getGrille().grillePleine()) {
                    unePartie.setVainqueurNull();
                    unePartie.setTerminee(Boolean.TRUE);
                    return true;
                }
                unePartie.changeMain();
                return true;
            }
        }
        return false;
    }
    
    public static String getGrilleJSON(HttpServletRequest requete) {
        List parties = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeParties");
        Iterator itr = parties.iterator();
        while(itr.hasNext()) {
            Partie unePartie = (Partie)itr.next();
            if (unePartie.getJoueur1().getNom().equals(requete.getSession().getAttribute("connecte")) || unePartie.getJoueur2().getNom().equals(requete.getSession().getAttribute("connecte"))) {
                GrilleJeu uneGrille = unePartie.getGrille();
                return uneGrille.toJSON();
            }
        }
        return null;
    }
    
    public static String getTour(HttpServletRequest requete) { // Retourne le nom du joueur dont c'est le tour de jouer
        Partie laPartie = getPartie(requete);
        if (laPartie != null)
            return laPartie.getMain().getNom();
        return null;
    }
    
    public static String getSymbole(HttpServletRequest requete) { // Retourne le symbole associé au joueur qui envoie la requête
        Partie laPartie = getPartie(requete);
        if (laPartie.getJoueur1().getNom().equals(requete.getSession().getAttribute("connecte"))) {
            return ""+laPartie.getJoueur1().getSymbole();
        }
        else if (laPartie.getJoueur2().getNom().equals(requete.getSession().getAttribute("connecte"))) {
            return ""+laPartie.getJoueur2().getSymbole();
        }
        return null;
    }
    
    public static String getNomAdversaire(HttpServletRequest requete) { // Retourne le nom du joueur adverse
        Partie laPartie = getPartie(requete);
        if (laPartie.getJoueur1().getNom().equals(requete.getSession().getAttribute("connecte"))) {
            return ""+laPartie.getJoueur2().getNom();
        }
        else if (laPartie.getJoueur2().getNom().equals(requete.getSession().getAttribute("connecte"))) {
            return ""+laPartie.getJoueur1().getNom();
        }
        return null;
    }
    
    public static String getEtatPartie(HttpServletRequest requete) { // Retourne "true" si la partie est terminée, "false" dans le cas contraire
        Partie laPartie = getPartie(requete);
        if (laPartie != null) {
            return ""+laPartie.isTerminee();
        }
        return null;
    }
    
    public static boolean getLigneComplete(HttpServletRequest requete) { // Retourne "true" si une line complète est formée
        Partie laPartie = getPartie(requete);
            if (laPartie != null) {
                return laPartie.getGrille().alignement();
            }
        return false;
    }
    
    public static String getNomVainqueur(HttpServletRequest requete) { // Renvoie le nom du vainqueur de la partie, lorsqu'il y en a un
        Partie laPartie = getPartie(requete);
        if (laPartie != null && laPartie.getVainqueur() != null) {
            return laPartie.getVainqueur().getNom();
        }
        return null;
    }
    
}

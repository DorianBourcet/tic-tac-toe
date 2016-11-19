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
    
    public static void enleverJoueur(HttpSession session) {
        List joueurs = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeJoueurs");
        // A continuer http://www.java67.com/2014/03/2-ways-to-remove-elementsobjects-from-ArrayList-java.html
        //Iterator itr = joueurs.iterator();
        joueurs.remove((String)session.getAttribute("connecte"));
        EcouteurApplication.APPLI.setAttribute("listeJoueurs", joueurs);
        
        enleverInvitations((String)session.getAttribute("connecte"));
        
    }
    
    public static boolean ajouterInvitation(HttpServletRequest requete, String invite) {
        List joueurs = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeJoueurs");
        List invitations = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeInvitations");
        Iterator itrJ = joueurs.iterator();
        Iterator itrI = invitations.iterator();
        String unJoueur;
        while (itrJ.hasNext()) {
            unJoueur = (String)itrJ.next();
            if (unJoueur.equals(invite)) {
                Invitation uneInvitation = new Invitation((String)requete.getSession().getAttribute("connecte"),invite);
                
                /*if (!invitations.contains(uneInvitation)) { // CONTAINS ne fonctionne pas. Essayer de trouver pourquoi.
                    invitations.add(uneInvitation);
                    EcouteurApplication.APPLI.setAttribute("listeInvitations", invitations);
                    return true;
                }*/
                
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
                EcouteurApplication.APPLI.setAttribute("listeInvitations", invitations);
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
        //invitations.remove(new Invitation("hote","invite"));
        EcouteurApplication.APPLI.setAttribute("listeInvitations", invitations);
    
    }
    
    public static void enleverInvitations(String nomJoueur) {
        List invitations = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeInvitations");
        Iterator itr = invitations.iterator();
        Invitation uneInvitation;
        while (itr.hasNext()) {
            uneInvitation = (Invitation)itr.next();
            if (uneInvitation.getInvite().getNom().equals(nomJoueur) || uneInvitation.getHote().getNom().equals(nomJoueur))
                itr.remove();
        }
    }
    
    public static void ajouterPartie(HttpServletRequest requete, String joueur) {
        List parties = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeParties");
        Partie partie = new Partie((String)requete.getSession().getAttribute("connecte"),joueur);
        partie.initialiser();
        parties.add(partie);
        EcouteurApplication.APPLI.setAttribute("listeParties", parties);
    }
    
    public static boolean jouerCase(HttpServletRequest requete, int y, int x) {
        List parties = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeParties");
        Iterator itr = parties.iterator();
        while(itr.hasNext()) {
            Partie unePartie = (Partie)itr.next();
            if (unePartie.getMain().getNom().equals(requete.getSession().getAttribute("connecte"))) {
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
    
    /*public List getListeInvitations() {
        List invitations = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeInvitations");
        /*Iterator itr = invitations.iterator();
        while(itr.hasNext()) {
            Invitation uneInvitation = (Invitation)itr.next();
            if (!uneInvitation.getInvite().getNom().equals(requete.getSession().getAttribute("connecte"))) {
                itr.remove();
            }
        }
        return invitations;
    }*/
    
    public static String getListeInvitationsJSON() {
        List invitations = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeInvitations");
        //System.out.println("Session : "+requete.getSession().getAttribute("connecte"));
        //String currentUser = (String) session.getAttribute("connecte");
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
    
    public static String getTour(HttpServletRequest requete) {
        List parties = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeParties");
        Iterator itr = parties.iterator();
        while(itr.hasNext()) {
            Partie unePartie = (Partie)itr.next();
            if (unePartie.getJoueur1().getNom().equals(requete.getSession().getAttribute("connecte")) || unePartie.getJoueur2().getNom().equals(requete.getSession().getAttribute("connecte"))) {
                return unePartie.getMain().getNom();
            }
        }
        return null;
    }
    
    public static String getSymbole(HttpServletRequest requete) {
        List parties = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeParties");
        Iterator itr = parties.iterator();
        while(itr.hasNext()) {
            Partie unePartie = (Partie)itr.next();
            if (unePartie.getJoueur1().getNom().equals(requete.getSession().getAttribute("connecte"))) {
                return ""+unePartie.getJoueur1().getSymbole();
            }
            else if (unePartie.getJoueur2().getNom().equals(requete.getSession().getAttribute("connecte"))) {
                return ""+unePartie.getJoueur2().getSymbole();
            }
        }
        return null;
    }
    
    public static String getNomAdversaire(HttpServletRequest requete) {
        List parties = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeParties");
        Iterator itr = parties.iterator();
        while(itr.hasNext()) {
            Partie unePartie = (Partie)itr.next();
            if (unePartie.getJoueur1().getNom().equals(requete.getSession().getAttribute("connecte"))) {
                return ""+unePartie.getJoueur2().getNom();
            }
            else if (unePartie.getJoueur2().getNom().equals(requete.getSession().getAttribute("connecte"))) {
                return ""+unePartie.getJoueur1().getNom();
            }
        }
        return null;
    }
    
    public static String getEtatPartie(HttpServletRequest requete) {
        List parties = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeParties");
        Iterator itr = parties.iterator();
        while(itr.hasNext()) {
            Partie unePartie = (Partie)itr.next();
            if (unePartie.getJoueur1().getNom().equals(requete.getSession().getAttribute("connecte")) || unePartie.getJoueur2().getNom().equals(requete.getSession().getAttribute("connecte"))) {
                return ""+unePartie.isTerminee();
            }
        }
        return null;
    }
    
    public static boolean getLigneComplete(HttpServletRequest requete) {
        List parties = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeParties");
        Iterator itr = parties.iterator();
        while(itr.hasNext()) {
            Partie unePartie = (Partie)itr.next();
            if (unePartie.getJoueur1().getNom().equals(requete.getSession().getAttribute("connecte")) || unePartie.getJoueur2().getNom().equals(requete.getSession().getAttribute("connecte"))) {
                return unePartie.getGrille().alignement(); // A TESTER
            }
        }
        return false;
    }
    
    /*public static String getPartie(HttpServletRequest requete) {
        List parties = (ArrayList)EcouteurApplication.APPLI.getAttribute("listeParties");
        Iterator itr = parties.iterator();
        while(itr.hasNext()) {
            Partie unePartie = (Partie)itr.next();
            if (unePartie.getJoueur1().getNom().equals(requete.getSession().getAttribute("connecte")) || unePartie.getJoueur2().getNom().equals(requete.getSession().getAttribute("connecte")))
                return unePartie.toString();
        }
        return null;
    }*/
    
}

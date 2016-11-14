/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcfdb.entites;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author usager
 */
public class ChampJeu {
    
    private List listeParties, listeJoueurs, listeInvitations;
    private User utilisateur;
    private Partie unePartie;
    
    public ChampJeu() {
        listeParties = new LinkedList();
        listeJoueurs = new LinkedList();
        listeInvitations = new LinkedList();
        utilisateur = new User();
        unePartie = null;
    }
    
    public ChampJeu(User u) {
        listeParties = new LinkedList();
        listeJoueurs = new LinkedList();
        listeInvitations = new LinkedList();
        utilisateur = u;
        unePartie = null;
    }
    
    public ChampJeu(User u, LinkedList lp, LinkedList lj, LinkedList li) {
        listeParties = lp;
        listeJoueurs = lj;
        listeInvitations = li;
        utilisateur = u;
        unePartie = null;
    }
    
    public ChampJeu(User u, LinkedList lp, LinkedList lj, LinkedList li, Partie p) {
        listeParties = lp;
        listeJoueurs = lj;
        listeInvitations = li;
        utilisateur = u;
        unePartie = p;
    }
    
    public List getListeParties() {
        return listeParties;
    }
    
    public void setListeParties(List lp) {
        listeParties = lp;
    }
    
    public List getListeJoueurs() {
        return listeJoueurs;
    }
    
    public void setListeJoueurs(List lj) {
        listeJoueurs = lj;
    }
    
    public List getListeInvitations() {
        return listeInvitations;
    }
    
    public void setListeInvitations(List li) {
        listeInvitations = li;
    }
    
    public void ajouterPartie(Partie p) {
        listeParties.add(p);
    }
    
    public void supprimerPartie(Partie p) {
        listeParties.remove(p);
    }
    
    public void ajouterJoueur(User u) {
        listeJoueurs.add(u);
    }
    
    public void supprimerJoueur(User u) {
        listeJoueurs.remove(u);
    }
    
    public void ajouterInvitation(Invitation i) {
        listeInvitations.add(i);
    }
    
    public void supprimerInvitation(Invitation i) {
        listeInvitations.remove(i);
    }
    
    public void accepterInvitation(Invitation i) {
        unePartie = new Partie(i.getHote().getUsername(), i.getInvite().getUsername());
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcfdb.entites;

/**
 *
 * @author usager
 */
public class Partie_old {
    private String id, joueur1, joueur2;
    private char couleurJ1 = ' ', couleurJ2 = ' ';
    private GrilleJeu_old grille;
    
    public Partie_old () {
        this.id = "";
        this.joueur1 = "";
        this.joueur2 = "";
        this.grille = new GrilleJeu_old();
    }
    
    public Partie_old (String id, String j1, String j2, GrilleJeu_old grille) {
        this.id = id;
        this.joueur1 = j1;
        this.joueur2 = j2;
        this.grille = grille;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getJoueur1() {
        return joueur1;
    }
    
    public void setJoueur1(String j) {
        this.joueur1 = j;
    }
    
    public String getJoueur2() {
        return joueur2;
    }
    
    public void setJoueur2(String j) {
        this.joueur2 = j;
    }
    
    public char getCouleurJ1() {
        return couleurJ1;
    }
    
    public void setCouleurJ1(char c) {
        this.couleurJ1 = c;
    }
    
    public char getCouleurJ2() {
        return couleurJ2;
    }
    
    public void setCouleurJ2(char j) {
        this.couleurJ2 = j;
    }
}
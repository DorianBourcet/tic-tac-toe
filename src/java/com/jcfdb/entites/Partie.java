/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcfdb.entites;

import java.util.Random;

/**
 *
 * @author usager
 */


public class Partie {
    private String id, joueur1, joueur2, vainqueur, main; // main : joueur dont c'est le tour de jouer
    private char signeJ1 = ' ', signeJ2 = ' ';
    private GrilleJeu grille;
    private Boolean terminee = false;
    
    public Partie () {
        this.id = "";
        this.joueur1 = "";
        this.joueur2 = "";
        this.grille = new GrilleJeu();
    }
    
    public Partie (String j1, String j2) {
        this.joueur1 = j1;
        this.joueur2 = j2;
    }
    
    public Partie (String id, String j1, String j2) {
        this.id = id;
        this.joueur1 = j1;
        this.joueur2 = j2;
    }
    
    public Partie (String id, String j1, String j2, GrilleJeu grille) {
        this.id = id;
        this.joueur1 = j1;
        this.joueur2 = j2;
        this.grille = grille;
    }
    
    public Partie (String id, String j1, String j2, char[][] grille) {
        this.id = id;
        this.joueur1 = j1;
        this.joueur2 = j2;
        this.grille = new GrilleJeu(grille);
    }
    
    // ========== Accesseurs et mutateurs ==========
    
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
    
    public char getSigneJ1() {
        return signeJ1;
    }
    
    public void setSigneJ1(char c) {
        this.signeJ1 = c;
    }
    
    public char getSigneJ2() {
        return signeJ2;
    }
    
    public void setSigneJ2(char j) {
        this.signeJ2 = j;
    }
    
    public String getMain() {
        return this.main;
    }
    
    public void setMain(String m) {
        this.main = m;
    }
    
    public String getVainqueur() {
        return vainqueur;
    }
    
    public void setVainqueur(String vainqueur) {
        this.vainqueur = vainqueur;
    }
    
    public GrilleJeu getGrille() {
        return grille;
    }
    
    public void setGrille(GrilleJeu g) {
        this.grille = g;
    }
    
    public Boolean isTerminee() {
        return terminee;
    }
    
    public void setTerminee(Boolean t) {
        this.terminee = t;
    }
    
    // ========== Méthodes du jeu ==========
    
    public void initialiser() { // Permet d'affecter aléatoirement une signe (X ou O) pour chaque joueur et de définir qui commence. Initialisation de la partie.
        Random random = new Random();
        if (random.nextBoolean()) { // Attribution aléatoire des signes
            this.signeJ1 = 'X';
            this.signeJ2 = 'O';
        }
        else {
            this.signeJ1 = 'O';
            this.signeJ2 = 'X';
        }
        if (random.nextBoolean()) // Qui commence ?
            this.main = this.joueur1;
        else
            this.main = this.joueur2;
    }
    
    //public void jouer(String joueur, int ligne, int colonne) {
    //    if (joueur != main || ligne >= 3 || ligne < 0 || colonne >= 3 || colonne < 0) {}
    //}
    
    public void changeMain() {
        if (main == joueur1)
            main = joueur2;
        else if (main == joueur2)
            main = joueur1;
    }
    
    @Override
    public String toString() {
        String chaine = new String();
        chaine = "id : " + this.id + " j1 : " + this.joueur1 + " " + this.signeJ1 + " j2 : " + this.joueur2 + " " + this.signeJ2 + "\n\n";
        chaine += this.grille.toString();
        chaine += "\n\nAu tour de : " + main;
        return chaine;
    }
}

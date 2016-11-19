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
    private Joueur joueur1, joueur2, vainqueur, main; // main : joueur dont c'est le tour de jouer
    private GrilleJeu grille;
    private Boolean terminee = false;
    
    public Partie () {
        
    }
    
    public Partie (String j1, String j2) {
        this.joueur1 = new Joueur(j1);
        this.joueur2 = new Joueur(j2);
        this.grille = new GrilleJeu();
    }
    
    // ========== Accesseurs et mutateurs ==========
    
    public Joueur getJoueur1() {
        return joueur1;
    }
    
    public void setJoueur1(Joueur j) {
        this.joueur1 = j;
    }
    
    public Joueur getJoueur2() {
        return joueur2;
    }
    
    public void setJoueur2(Joueur j) {
        this.joueur2 = j;
    }
    
    public Joueur getMain() {
        return this.main;
    }
    
    public void setMain(Joueur m) {
        this.main = m;
    }
    
    public Joueur getVainqueur() {
        return vainqueur;
    }
    
    public void setVainqueur(Joueur vainqueur) {
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
            this.joueur1.setSymbole('X');
            this.joueur2.setSymbole('O');
        }
        else {
            this.joueur1.setSymbole('O');
            this.joueur2.setSymbole('X');
        }
        if (random.nextBoolean()) // Qui commence ?
            this.main = this.joueur1;
        else
            this.main = this.joueur2;
    }
    
    public void remplirCase(int ligne, int colonne) {
        //if (ligne >= 3 || ligne < 0 || colonne >= 3 || colonne < 0) {}
        this.grille.setCase(ligne, colonne, this.main.getSymbole());
    }
    
    public void changeMain() {
        if (this.main.getNom().equals(this.joueur1.getNom()))
            this.main = this.joueur2;
        else if (this.main.getNom().equals(this.joueur2.getNom()))
            this.main = this.joueur1;
    }
    
    @Override
    public String toString() {
        String chaine;
        chaine = "j1 : " + this.joueur1.getNom() + " " + this.joueur1.getSymbole() + " j2 : " + this.joueur2.getNom() + " " + this.joueur2.getSymbole() + "\n\n";
        //chaine += this.grille.toString();
        chaine += "\n\nAu tour de : " + main.getNom();
        return chaine;
    }
}

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
    private char couleurJ1 = ' ', couleurJ2 = ' ';
    private char[][] grille;
    private Boolean terminee;
    
    public Partie () {
        this.id = "";
        this.joueur1 = "";
        this.joueur2 = "";
        this.grille = new char[][]{ // Initialisation des cases du tableau 3*3
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' }
        };
        this.terminee = false;
    }
    
    public Partie (String id, String j1, String j2, char[][] grille) {
        this.id = id;
        this.joueur1 = j1;
        this.joueur2 = j2;
        this.grille = grille;
        this.terminee = false;
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
    
    public char[][] getGrille() {
        return grille;
    }

    public void setGrille(char[][] grille) {
        this.grille = grille;
    }
    
    public char getCase(int ligne, int colonne) {
        return this.grille[ligne][colonne];
    }
    public void setCase(int ligne, int colonne, char valeur) {
        this.grille[ligne][colonne] = valeur;
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
    
    public Boolean isTerminee() {
        return terminee;
    }
    
    public void setTerminee(Boolean t) {
        this.terminee = t;
    }
    
    // ========== Méthodes du jeu ==========
    
    public void initialiser() { // Permet d'affecter aléatoirement une couleur (X ou O) pour chaque joueur et de définir qui commence. Initialisation de la partie.
        Random random = new Random();
        if (random.nextBoolean()) { // Attribution aléatoire des couleurs
            this.couleurJ1 = 'X';
            this.couleurJ2 = 'O';
        }
        else {
            this.couleurJ1 = 'O';
            this.couleurJ2 = 'X';
        }
        if (random.nextBoolean()) // Qui commence ?
            this.main = this.joueur1;
        else
            this.main = this.joueur2;
    }
    
    public Boolean verifierLigne() { // On vérifie si au moins une ligne de cases identiques est présente.
        for (int i=0;i<3;i++) {
            if ((((this.getCase(i,0) == this.getCase(i,1)) && (this.getCase(i,0) == this.getCase(i,2))) || ((this.getCase(0,i) == this.getCase(1,i)) && (this.getCase(0,i) == this.getCase(2,i)))) && (this.getCase(i,i) != ' ')) { // On vérifie la présence de trois couleurs identiques sur les trois lignes horizontales
                terminee = true;
                return terminee;
            }
        }
        if ((this.getCase(0,0) == this.getCase(1,1)) && (this.getCase(0,0) == this.getCase(2,2)) && (this.getCase(0,0) != ' ')) { // On vérifie sur la diagonale "nord-ouest - sud-est"
            terminee = true;
            return terminee;
        }
        if ((this.getCase(2,0) == this.getCase(1,1)) && (this.getCase(2,0) == this.getCase(0,2)) && (this.getCase(2,0) != ' ')) // On vérifie sur la diagonale "sud-ouest - nord-est"
            terminee = true;
        return terminee;
    }
    
    public Boolean verifierGrillePleine() { // On vérifie si la grille est totalement jouée (toutes les cases remplies)
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                if (grille[i][j] == ' ')
                    return false;
            }
        }
        return true;
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
        chaine = "id : " + this.id + " j1 : " + this.joueur1 + " " + this.couleurJ1 + " j2 : " + this.joueur2 + " " + this.couleurJ2 + "\n\n";
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                chaine += getCase(i,j);
            }
            chaine += "\n";
        }
        chaine += "\n\nAu tour de : " + main;
        return chaine;
    }
}

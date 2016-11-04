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
    private String id, joueur1, joueur2, main;
    private char couleurJ1 = ' ', couleurJ2 = ' ';
    private char[][] grille;
    
    public Partie () {
        this.id = "";
        this.joueur1 = "";
        this.joueur2 = "";
        this.grille = new char[][]{ // Initialisation des cases du tableau 3*3
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' }
        };
        //this.initialiser();
    }
    
    public Partie (String id, String j1, String j2, char[][] grille) {
        this.id = id;
        this.joueur1 = j1;
        this.joueur2 = j2;
        this.grille = grille;
        //this.initialiser();
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
    
    public void initialiser() {
        Random random = new Random();
        if (random.nextBoolean()) {
            this.couleurJ1 = 'X';
            this.couleurJ2 = 'O';
        }
        else {
            this.couleurJ1 = 'O';
            this.couleurJ2 = 'X';
        }
        if (random.nextBoolean())
            this.main = this.joueur1;
        else
            this.main = this.joueur2;
    }
    
    public Boolean verifierLigne() {
        for (int i=0;i<3;i++) // On vérifie la présence de trois couleurs identiques sur les lignes horizontales
            if ((this.getCase(i, 0) == this.getCase(i,1)) && (this.getCase(i,0) == this.getCase(i,2)))
                return true;
        for (int i=0;i<3;i++) // On vérifie la présence de trois couleurs identiques sur les lignes verticales
            if ((this.getCase(0,i) == this.getCase(1,i)) && (this.getCase(0,i) == this.getCase(2,i)))
                return true;
        if ((this.getCase(0,0) == this.getCase(1,1)) && (this.getCase(0,0) == this.getCase(2,2)))
            return true;
        if ((this.getCase(0,0) == this.getCase(1,1)) && (this.getCase(0,0) == this.getCase(2,2)))
            return true;
        return false;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcfdb.entites;

/**
 *
 * @author moumene
 */
public class GrilleJeu {
    private String id, idPartie;
    private char[][] cases;

    public GrilleJeu() {
        this.id = "";
        this.idPartie = "";
        this.cases = new char[][]{ // Initialisation des cases du tableau 3*3
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' }
        };
    }

    public GrilleJeu(String id, String partie) {
        this.id = id;
        this.idPartie = partie;
        this.cases = new char[][]{ // Initialisation des cases du tableau 3*3
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' }
        };
    }    
    
    public GrilleJeu(String id, String partie, char[][] grille) {
        this.id = id;
        this.idPartie = partie;
        this.cases = grille;
    }  

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(String partie) {
        this.idPartie = partie;
    }
    
    public char[][] getCases() {
        return cases;
    }

    public void setCases(char[][] grille) {
        this.cases = grille;
    }
    
    public char getCase(int ligne, int colonne) {
        return this.cases[ligne][colonne];
    }
    public void setCase(int ligne, int colonne, char valeur) {
        this.cases[ligne][colonne] = valeur;
    }
    
}

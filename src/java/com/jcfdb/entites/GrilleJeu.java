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
    private char[][] cases;

    public GrilleJeu() {
        //this.id = "";
        //this.idPartie = "";
        this.cases = new char[][]{ // Initialisation des cases du tableau 3*3
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' }
        };
    }
    
    public GrilleJeu(char[][] grille) {
        this.cases = grille;
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
    
    public Boolean verifierLigne() { // On vérifie si au moins une ligne de cases identiques est présente.
        for (int i=0;i<3;i++) {
            if ((((this.getCase(i,0) == this.getCase(i,1)) && (this.getCase(i,0) == this.getCase(i,2))) || ((this.getCase(0,i) == this.getCase(1,i)) && (this.getCase(0,i) == this.getCase(2,i)))) && (this.getCase(i,i) != ' ')) // On vérifie la présence de trois couleurs identiques sur les trois lignes horizontales
                return true;
        }
        if ((this.getCase(0,0) == this.getCase(1,1)) && (this.getCase(0,0) == this.getCase(2,2)) && (this.getCase(0,0) != ' ')) // On vérifie sur la diagonale "nord-ouest - sud-est"
            return true;
        if ((this.getCase(2,0) == this.getCase(1,1)) && (this.getCase(2,0) == this.getCase(0,2)) && (this.getCase(2,0) != ' ')) // On vérifie sur la diagonale "sud-ouest - nord-est"
            return true;
        return false;
    }
    
    public Boolean verifierGrillePleine() { // On vérifie si la grille est totalement jouée (toutes les cases remplies)
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                if (cases[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }
    
    @Override
    public String toString() {
        String chaine = new String();
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                chaine += getCase(i,j);
            }
            chaine += "\n";
        }
        return chaine;
    }
    
    public String toJSON() {
        String json;
        json = "[";
        for (int i=0;i<3;i++) {
            json += "{";
            for (int j=0;j<3;j++) {
                json = "\"" + i + j + "\":\"" + this.cases[i][j] + "\"";
                if (j<2)
                    json += ",";
            }
            json += "}";
            if (i<2)
                json += ",";
        }
        json += "]";
        return json;
    }
    
}

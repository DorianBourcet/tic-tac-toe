/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcfdb.entites;

/**
 *
 * @author soixa
 */
public class Joueur {
    
    private String nom;
    private char symbole;

    public Joueur() {
    }

    public Joueur(String nom) {
        this.nom = nom;
    }

    public Joueur(String nom, char signe) {
        this.nom = nom;
        this.symbole = signe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public char getSymbole() {
        return symbole;
    }

    public void setSymbole(char signe) {
        this.symbole = signe;
    }
    
    
    
}

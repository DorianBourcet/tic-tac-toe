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
public class TestPartie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Partie partie = new Partie();
        partie.getGrille().setCase(0, 0, 'X');
        partie.getGrille().setCase(0, 1, 'X');
        partie.getGrille().setCase(0, 2, 'X');
        partie.getGrille().setCase(1, 0, 'X');
        partie.getGrille().setCase(1, 1, 'O');
        partie.getGrille().setCase(1, 2, 'X');
        partie.getGrille().setCase(2, 0, 'X');
        partie.getGrille().setCase(2, 1, 'X');
        partie.getGrille().setCase(2, 2, 'X');
       // partie.setJoueur1("Dodo");
       // partie.setJoueur2("Jycy");
       // partie.setId("123");
        partie.initialiser();
        System.out.println(partie.toString());
       // System.out.println("Ligne complète ? "+partie.getGrille().verifierLigne());
       // System.out.println("Grille pleine ? "+partie.getGrille().verifierGrillePleine());
    }
    
}

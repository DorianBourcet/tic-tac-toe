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
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GrilleJeu grilleTest = new GrilleJeu();
        grilleTest.setCase(0, 0, 'X');
        //grilleTest.setCase(0, 1, 'O');
        grilleTest.setCase(0, 2, 'O');
        grilleTest.setCase(1, 0, 'X');
        grilleTest.setCase(1, 1, 'X');
        grilleTest.setCase(1, 2, 'O');
        grilleTest.setCase(2, 0, 'X');
        grilleTest.setCase(2, 1, 'O');
        grilleTest.setCase(2, 2, 'X');
        System.out.println(grilleTest.toString());
    }
    
}

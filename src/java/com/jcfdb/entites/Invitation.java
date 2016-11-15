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
public class Invitation {
    
    private Joueur hote, invite;
    
    public Invitation() {
        hote = new Joueur();
        invite = new Joueur();
    }
    
    public Invitation(Joueur h, Joueur i) {
        hote = h;
        invite = i;
    }
    
    public Invitation(String h, String i) {
        hote = new Joueur(h);
        invite = new Joueur(i);
    }

    public Joueur getHote() {
        return hote;
    }

    public void setHote(Joueur hote) {
        this.hote = hote;
    }
    
    public void setHote(String hote) {
        this.hote = new Joueur(hote);
    }

    public Joueur getInvite() {
        return invite;
    }

    public void setInvite(Joueur invite) {
        this.invite = invite;
    }
    
    public void setInvite(String invite) {
        this.invite = new Joueur(invite);
    }
    
}

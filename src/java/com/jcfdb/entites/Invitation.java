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
    
    private User hote, invite;
    
    public Invitation() {
        hote = new User();
        invite = new User();
    }
    
    public Invitation(User h, User i) {
        hote = h;
        invite = i;
    }
    
    public Invitation(String h, String i) {
        hote = new User(h);
        invite = new User(i);
    }

    public User getHote() {
        return hote;
    }

    public void setHote(User hote) {
        this.hote = hote;
    }
    
    public void setHote(String hote) {
        this.hote = new User(hote);
    }

    public User getInvite() {
        return invite;
    }

    public void setInvite(User invite) {
        this.invite = invite;
    }
    
    public void setInvite(String invite) {
        this.invite = new User(invite);
    }
    
}

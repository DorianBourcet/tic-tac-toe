package com.jcfdb.jdbc.implementation;

import com.jcfdb.entites.Invitation;
import com.atoudeft.jdbc.dao.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class InvitationDao extends Dao<Invitation> {

    public InvitationDao(Connection c) {
        super(c);
    }

    @Override
    public boolean create(Invitation x) {
        // TODO Auto-generated method stub       
        String req = "INSERT INTO invitation (`hote` , `invite`) "
                + "VALUES ('" + x.getHote().getUsername() + "','" + x.getInvite().getUsername() + "')";

        //System.out.println("REQUETE "+req);

        Statement stm = null;
        try {
            stm = cnx.createStatement();
            int n = stm.executeUpdate(req);
            if (n > 0) {
                stm.close();
                return true;
            }
        } 
        catch (SQLException exp) {
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    @Override
    public boolean delete(Invitation x) {
        // TODO Auto-generated method stub
        Statement stm = null;
        try {
            stm = cnx.createStatement();
            int n = stm.executeUpdate("DELETE FROM invitation WHERE hote='" + x.getHote().getUsername() + "'");
            if (n > 0) {
                stm.close();
                return true;
            }
        } catch (SQLException exp) {
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public Invitation read(String u) {
        // TODO Auto-generated method stub
        PreparedStatement stm = null;
        try {
//            Statement stm = cnx.createStatement();
//            ResultSet r = stm.executeQuery("SELECT * FROM user WHERE numId = '" + id + "'");
            //Avec requête paramétrée :
            stm = cnx.prepareStatement("SELECT * FROM invitation WHERE invite = ?");
            stm.setString(1,u);
            ResultSet r = stm.executeQuery();
            if (r.next()) {
                //Invitation c = new Invitation(r.getString("numId"),r.getString("mdp"));
                Invitation i = new Invitation();
                i.setHote(r.getString("hote"));
                i.setInvite(r.getString("invite"));
                r.close();
                stm.close();
                return i;
            }
        } catch (SQLException exp) {
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public boolean update(Invitation x) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Invitation> findAll() {
        // TODO Auto-generated method stub
        List<Invitation> liste = new LinkedList<Invitation>();
        try {
            Statement stm = cnx.createStatement();
            ResultSet r = stm.executeQuery("SELECT * FROM invitation");
            while (r.next()) {
                Invitation i = new Invitation(r.getString("hote"),
                        r.getString("invite"));
                liste.add(i);
            }
            r.close();
            stm.close();
        } catch (SQLException exp) {
        }
        return liste;
    }
}

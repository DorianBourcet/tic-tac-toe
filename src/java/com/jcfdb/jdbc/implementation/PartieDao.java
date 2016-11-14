/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcfdb.jdbc.implementation;

import com.atoudeft.jdbc.dao.Dao;
import com.jcfdb.entites.Partie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author usager
 */
public class PartieDao extends Dao<Partie> {

    public PartieDao(Connection cnx) {
        super(cnx);
    }

    @Override
    public boolean create(Partie x) {
        String req = "INSERT INTO partie ('joueur1', 'joueur2', 'signe_joueur1', 'signe_joueur2', 'main') "
                + "VALUES ('" + x.getJoueur1() + "', '" + x.getJoueur2() + "', '" + x.getSigneJ1() + "', '" + x.getSigneJ2() + "', '" + x.getMain() + "')";
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
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public Partie read(String id) {
        PreparedStatement stm = null;
        try {
            stm = cnx.prepareStatement("SELECT * FROM partie WHERE id = ?");
            stm.setString(1,id);
            ResultSet r = stm.executeQuery();
            if (r.next()) {
                Partie p = new Partie();
                p.setId(r.getString("id"));
                p.setJoueur1(r.getString("joueur1"));
                p.setJoueur2(r.getString("joueur2"));
                p.setSigneJ1(r.getString("signe_joueur1").charAt(0));
                p.setSigneJ2(r.getString("signe_joueur2").charAt(0));
                p.setMain(r.getString("main"));
                for (int i = 0;i<3;i++) {
                    for (int j = 0;j<3;j++) {
                        String coord = ""+i+j;
                        p.getGrille().setCase(i,j,(r.getString(coord).charAt(0)));
                    }
                }
                r.close();
                stm.close();
                return p;
            }
        } catch (SQLException exp) {
            } finally {
                if (stm != null) {
                    try {
                        stm.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

    @Override
    public boolean update(Partie x) {
        Statement stm = null;
        try {
            String req = "UPDATE partie SET joueur1 = '" + x.getJoueur1()
                    + "', joueur2 = '" + x.getJoueur2()
                    + "', signe_joueur1 = '" + x.getSigneJ1()
                    + "', signe_joueur2 = '" + x.getSigneJ2()
                    + "', main = '" + x.getMain();
            for (int i=0;i<3;i++) {
                for (int j = 0;j<3;j++) {
                    req += "', " + i + j + " = '" + x.getGrille().getCase(i, j);
                }
            }
            req += "' WHERE id = '" + x.getId() + "'";
            stm = cnx.createStatement();
            int n = stm.executeUpdate(req);
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
    public boolean delete(Partie x) {
        Statement stm = null;
        try {
            stm = cnx.createStatement();
            int n = stm.executeUpdate("DELETE FROM partie WHERE id='" + x.getId() + "'");
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
    public List<Partie> findAll() {
        List<Partie> liste = new LinkedList<Partie>();
        try {
            Statement stm = cnx.createStatement();
            ResultSet r = stm.executeQuery("SELECT * FROM partie");
            while (r.next()) {
                Partie p = new Partie(r.getString("id"), r.getString("joueur1"), r.getString("joueur2"));
                liste.add(p);
            }
            r.close();
            stm.close();
        } catch (SQLException exp) {
        }
        return liste;
    }
    
}

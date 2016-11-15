/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcfdb.web.mvc;

import com.jcfdb.entites.Partie;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dbourcet
 */
public class Jouer extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            int coordX = Integer.parseInt(request.getParameter("x")),
                coordY = Integer.parseInt(request.getParameter("y"));
            HttpSession session = request.getSession();
            String joueur = (String)session.getAttribute("connecte");
            ServletContext appli = session.getServletContext();
            List parties = (ArrayList)appli.getAttribute("listeParties");
            Iterator itr = parties.iterator();
            Partie partieSelectionnee = null;
            while (itr.hasNext() && (partieSelectionnee == null)) {
                Partie unePartie = (Partie)itr.next();
                if (unePartie.getMain().getNom().equals(joueur)) {
                    partieSelectionnee = unePartie;
                }
            }
            partieSelectionnee.remplirCase(coordX, coordY);
            if (partieSelectionnee.getGrille().alignement()) {
                // code en cas de ligne pleine
            }
            else if (partieSelectionnee.getGrille().grillePleine()) {
                // code en cas de grille pleine sans vainqueur
            }
            else
                partieSelectionnee.changeMain();
                // encore du code à faire
            parties.add(partieSelectionnee);
            appli.setAttribute("listeParties",parties);
            
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

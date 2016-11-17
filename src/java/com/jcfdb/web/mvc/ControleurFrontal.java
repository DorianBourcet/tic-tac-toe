/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcfdb.web.mvc;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dbourcet
 */
public class ControleurFrontal extends HttpServlet {

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
        //String action = request.getParameter("action");
        String uri = request.getRequestURI();
        //System.out.println("Hahahaha "+uri);
        String substring[] = uri.split("/");
        String action = substring[substring.length-1].replace(".do", "");
        HttpSession session = request.getSession();
        RequestDispatcher r;
        //System.out.println("Je suis le controleur !");
        

        switch (action)
          {
              case "login" :
                    r = this.getServletContext().getRequestDispatcher("/signin");
                    break;
              case "logout" :
                    r = this.getServletContext().getRequestDispatcher("/signout");
                    break;
              case "inviter" :
                    if (session.getAttribute("connecte") == null) {
                        r = this.getServletContext().getRequestDispatcher("/index.jsp");
                        break;
                    }
                    r = this.getServletContext().getRequestDispatcher("/invite");
                    break;
              case "start" :
                    if (session.getAttribute("connecte") == null) {
                        r = this.getServletContext().getRequestDispatcher("/index.jsp");
                        break;
                    }
                    r = this.getServletContext().getRequestDispatcher("/start");
                    break;
              case "turn" :
                    if (session.getAttribute("connecte") == null) {
                        r = this.getServletContext().getRequestDispatcher("/index.jsp");
                        break;
                    }
                    r = this.getServletContext().getRequestDispatcher("/turn");
                    break;
              case "obtenir" :
                    if (session.getAttribute("connecte") == null) {
                        r = this.getServletContext().getRequestDispatcher("/index.jsp");
                        break;
                    }
                    r = this.getServletContext().getRequestDispatcher("/getDatas");
                    break;
              default :
                    r = this.getServletContext().getRequestDispatcher("/index.jsp");
          }

        r.forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */

    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

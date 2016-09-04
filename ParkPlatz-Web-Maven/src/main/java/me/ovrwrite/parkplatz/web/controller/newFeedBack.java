/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.ovrwrite.parkplatz.web.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import me.ovrwrite.parkplatz.web.controller.beans.Conductor;
import me.ovrwrite.parkplatz.web.controller.beans.Feedback;

/**
 *
 * @author UCER
 */
@WebServlet(name = "newFeedBack", urlPatterns = {"/newFeedBack"})
public class newFeedBack extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String descrip, aviso;
        HttpSession sesion = request.getSession(true);
        Conductor conduc = new Conductor();
        Feedback feed = new Feedback();
        conduc = (Conductor) sesion.getAttribute("conductor");
        descrip = request.getParameter("feedback");
        
        if(descrip.length() < 200){
            boolean validHtml = true;
            for(int i = 0; i < descrip.length(); i++){
                if((descrip.charAt(i) == '<') || (descrip.charAt(i) == '>')){
                    validHtml = false;
                }
            }
            if(validHtml){
                
            try {
                
                feed.setDescripcion(descrip);
                aviso = enviarFeedDos(feed, conduc);
                
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<script type=\"text/javascript\">alert(\""+ aviso +"\");</script>");
                    out.println("</head>");
                    out.println("</html>");
                    out.println("<META HTTP-EQUIV='REFRESH' CONTENT='.0000001;URL=http://localhost/Parkplatz-web/Conductor/inicio.jsp'/>");
                }
            }   catch (ClassNotFoundException ex) {
                    Logger.getLogger(newFeedBack.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<script type=\"text/javascript\">alert(\"Hubo un error. Intente otra vez sin los <>\");</script>");           
                    out.println("</head>");
                    out.println("</html>");
                    out.println("<META HTTP-EQUIV='REFRESH' CONTENT='.0000001;URL=http://localhost/Parkplatz-web/Conductor/inicio.jsp'/>");
                }
            }
        }
        else{
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<script type=\"text/javascript\">alert(\"Ups... Cadena muy grande. Intente otra vez.\");</script>");           
                out.println("</head>");
                out.println("</html>");
                out.println("<META HTTP-EQUIV='REFRESH' CONTENT='.0000001;URL=http://localhost/Parkplatz-web/Conductor/inicio.jsp'/>");
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(newFeedBack.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(newFeedBack.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public String enviarFeedDos( Feedback fed, Conductor conductor) throws ClassNotFoundException {
        String estado = "";
        
        boolean exito = conductor.enviarFeed(fed);
        if(exito == true){
            estado = "Se ha enviado correctamente";
        }
        return estado;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.ovrwrite.parkplatz.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author UCER
 */
@WebServlet(name = "RecuperarContra", urlPatterns = {"/RecuperarContra"})
public class RecuperarContra extends HttpServlet {
   

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
        
        String correo = request.getParameter("username");
        boolean exito = recover(correo);
        
        if(exito){
                    response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");  
                out.println("<script type=\"text/javascript\">alert(\"Se le ha enviado un correo de recuperación de contraseña.\");</script>");
                out.println("</head>");
                out.println("</html>");
                out.println("<META HTTP-EQUIV='REFRESH' CONTENT='.0000001;URL=http://localhost/Parkplatz-web/index.jsp'/>");
            }
        }
        else{
                    response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<script type=\"text/javascript\">alert(\"Hubo un error, favor de intentarlo más tarde.\");</script>");
                out.println("</head>");
                out.println("</html>");
                out.println("<META HTTP-EQUIV='REFRESH' CONTENT='.0000001;URL=http://localhost/Parkplatz-web/index.jsp'/>");
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
        processRequest(request, response);
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
    public boolean recover(String correo){
        boolean exito;
        try{
            Email em = new Email(correo);
            exito = em.enviar(em.MENSAJE_RECUPERA_CONTRA);
        }catch(Exception e){
            System.out.println(e.getMessage());
            exito = false;
        }
        return exito;
    }
    

    
}
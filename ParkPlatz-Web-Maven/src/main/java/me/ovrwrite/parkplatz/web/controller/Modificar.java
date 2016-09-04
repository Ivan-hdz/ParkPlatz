/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.ovrwrite.parkplatz.web.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import me.ovrwrite.parkplatz.web.controller.beans.Administrador;
import me.ovrwrite.parkplatz.web.controller.beans.Conductor;

/**
 *
 * @author UCER
 */
@WebServlet(name = "Modificar", urlPatterns = {"/Modificar"})
public class Modificar extends HttpServlet {

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
        Conductor conduc = new Conductor();
        Administrador admin = new Administrador();
        String nombre, aPaterno, aMaterno, email, password, isAd;
        boolean exito;
        nombre = request.getParameter("name");
        aPaterno = request.getParameter("aPaterno");
        aMaterno = request.getParameter("aMaterno");
        email = request.getParameter("email");
        password = request.getParameter("password"); 
        isAd = request.getParameter("isAd");

        HttpSession sesion = request.getSession(true);
        sesion.removeAttribute("conductor");
        
        
        if(isAd.equals("1")){
            
            try {
                
                admin.setNombre(nombre);
                admin.setaPaterno(aPaterno);
                admin.setaMaterno(aMaterno);
                admin.setCorreo(email);
                admin.setPass(password);
                admin = actualizarDatosAdmin(admin);
                sesion.setAttribute("conductor", admin);
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<script type=\"text/javascript\">alert(\"lol!!!\");</script>");
                    out.println("</head>");
                    out.println("</html>");
                    out.println("<META HTTP-EQUIV='REFRESH' CONTENT='.0000001;URL=http://localhost/Parkplatz-web/Administrador/inicio.jsp'/>");
                }
            }   catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
                    
            try {
                
                conduc.setNombre(nombre);
                conduc.setaPaterno(aPaterno);
                conduc.setaMaterno(aMaterno);
                conduc.setCorreo(email);
                conduc.setPass(password);
                conduc = actualizarDatosConductor(conduc);
                sesion.setAttribute("conductor", conduc);
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<script type=\"text/javascript\">alert(\"Exito!!!\");</script>");
                    out.println("</head>");
                    out.println("</html>");
                    out.println("<META HTTP-EQUIV='REFRESH' CONTENT='.0000001;URL=http://localhost/Parkplatz-web/Conductor/inicio.jsp'/>");
                }
            }   catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
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

    public Conductor actualizarDatosConductor(Conductor cond) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if(cond.getPass() != null){
           Seguridad seg = new Seguridad();         
           seg.setKey(Seguridad.genKey(cond.getCorreo()));
           cond.setPass(seg.encriptar(cond.getPass()));
        }
        cond.actualizarDatos();      
        return cond;
    }

    public Administrador actualizarDatosAdmin( Administrador admin) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if(admin.getPass() != null){
           Seguridad seg = new Seguridad();         
           seg.setKey(Seguridad.genKey(admin.getCorreo()));
           admin.setPass(seg.encriptar(admin.getPass()));
        }
        admin.actualizarDatos();      
        return admin;
    }


    
}

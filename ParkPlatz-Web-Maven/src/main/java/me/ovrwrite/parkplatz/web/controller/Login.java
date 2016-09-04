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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    
    public Conductor conduc;
    private Administrador admin;
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
            throws ServletException, IOException{
        
        String email, pass; 
        HttpSession sesion = request.getSession(true);
        email = request.getParameter("username");
        pass = request.getParameter("password");
        boolean isAd = false;
        String cad = "@parkplatz.";
        for(int i = 0; i < email.length(); i++){
            if(email.charAt(i) == '@'){
                if(email.substring(i, email.length()).length() > 12){
                    if(cad.equalsIgnoreCase(email.substring(i, (i + 11)))){
                    isAd = true;
                    }
                }
                
            }
        }
        
        
        
        if(!isAd){
            try {
                conduc = new Conductor();
                conduc.setPass(pass);
                conduc.setCorreo(email);
                conduc = loginConductor(conduc);
                
                if(conduc.isLogueado()){
                    response.setContentType("text/html;charset=UTF-8");
                    response.sendRedirect("Conductor/inicio.jsp");
                    sesion.setAttribute("conductor", conduc);
                    sesion.setAttribute("tipo", "1");
                }
                else{
                    response.setContentType("text/html;charset=UTF-8");
                    try (PrintWriter out = response.getWriter()) {
                        /* TODO output your page here. You may use following sample code. */
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<script type=\"text/javascript\">alert(\"Usuario o Contraseña Incorrecta\");</script>");
                        out.println("</head>");
                        out.println("</html>");
                        out.println("<META HTTP-EQUIV='REFRESH' CONTENT='.0000001;URL=http://localhost/Parkplatz-web/index.jsp'/>");
                    }
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                admin = new Administrador();
                admin.setCorreo(email);
                admin.setPass(pass);
                admin = loginAdministrador(admin);
                
                if(admin.isLogueado()){
                    response.setContentType("text/html;charset=UTF-8");
                    response.sendRedirect("Administrador/inicio.jsp");
                    sesion.setAttribute("conductor", admin);
                    sesion.setAttribute("tipo", "2");
                }
                else{
                    response.setContentType("text/html;charset=UTF-8");
                    try (PrintWriter out = response.getWriter()) {
                        /* TODO output your page here. You may use following sample code. */
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<script type=\"text/javascript\">alert(\"Usuario o Contraseña Incorrecta\");</script>");
                        out.println("</head>");
                        out.println("</html>");
                        out.println("<META HTTP-EQUIV='REFRESH' CONTENT='.0000001;URL=http://localhost/Parkplatz-web/index.jsp'/>");
                    }
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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

    public Administrador loginAdministrador( Administrador objetoConUserPass) throws UnsupportedEncodingException, NoSuchAlgorithmException{
        Seguridad seg = new Seguridad();
        seg.setKey(Seguridad.genKey(objetoConUserPass.getCorreo()));
        objetoConUserPass.setPass(seg.encriptar(objetoConUserPass.getPass()));
        objetoConUserPass.iniciarSesion();
        return objetoConUserPass;
    }
    public Conductor loginConductor(Conductor objetoConUserPass) throws UnsupportedEncodingException, NoSuchAlgorithmException{
        Seguridad seg = new Seguridad();
        seg.setKey(Seguridad.genKey(objetoConUserPass.getCorreo()));
        objetoConUserPass.setPass(seg.encriptar(objetoConUserPass.getPass()));
        objetoConUserPass.iniciarSesion();
        return objetoConUserPass;
    }
    

}

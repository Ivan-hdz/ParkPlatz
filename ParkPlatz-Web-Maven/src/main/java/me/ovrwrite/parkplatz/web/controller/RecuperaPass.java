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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.ovrwrite.parkplatz.web.model.Sql;

/**
 *
 * @author honte_000
 */
@WebServlet(name = "RecuperaPass", urlPatterns = {"/RecuperaPass"})
public class RecuperaPass extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String correo = request.getParameter("correo");
            String nvoPass = request.getParameter("pass");
            String nvoCPass = request.getParameter("Cpass");
            String idaComparar = request.getParameter("id");
            String id;
            String limpiaID = "";
            for(int i = 0; i<idaComparar.length();i++){
                if(idaComparar.charAt(i) == ' '){
                    limpiaID += '+';
                }else{
                    limpiaID += idaComparar.charAt(i);
                }
            }
            idaComparar = limpiaID;
            Seguridad seg = new Seguridad();
            seg.setKey(Seguridad.genKey(correo));
            id = Seguridad.toSHA256(verContraActual(correo));
            id = seg.encriptar(id);
            System.out.println(correo+"<---Correo");
            System.out.println(id+" <---contra cifrado");
            System.out.println(idaComparar+" <---contra cifrada enviada desde email");
            if(id.equals(idaComparar)){
                if(nvoPass.equals(nvoCPass)){
                    nvoCPass = "";
                    seg.setKey(Seguridad.genKey(correo));
                    nvoCPass = seg.encriptar(nvoPass);
                    boolean estado = cambiarContra(correo, nvoCPass);
                    if(estado == true){
                        out.println("<h1>Se ha reestablecido la contraseña satisfactoriamente!</h1>");
                        out.println("<script>alert('Redireccionando...'); window.location.href = 'index.jsp':</script>");
                    }else{
                        out.println("<h1>Ha ocurrido un error</h1>");
                        out.println("<script>alert('Redireccionando...'); window.location.href = 'index.jsp':</script>");
                    }
                }else{
                    out.println("<h1>Las contraseñas no coinciden</h1>");
                    out.println("<script>alert('Redireccionando...'); window.location.href = 'index.jsp':</script>");
                }
            }else{
                out.println("<h1>Error en la comprobación de URL</h1>");
                out.println("<script>alert('Redireccionando...'); window.location.href = 'index.jsp':</script>");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (UnsupportedEncodingException ex) {
             Logger.getLogger(RecuperaPass.class.getName()).log(Level.SEVERE, null, ex);
         } catch (NoSuchAlgorithmException ex) {
             Logger.getLogger(RecuperaPass.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    private String verContraActual(String correo){
        String pass = "";
        try {
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call verContra(?)");
            ps.setString(1, correo);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                pass = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return pass;
    }
    private boolean cambiarContra(String correo ,String contra) throws ClassNotFoundException{
        boolean exito = false;
        try {
            Connection con = Sql.conectar();
            PreparedStatement ps = con.prepareStatement("call cambiar_pass(?,?)");
            ps.setString(1, correo);
            ps.setString(2, contra);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getString(1).equals("Contraseña cambiada")){
                    exito = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return exito;
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

}

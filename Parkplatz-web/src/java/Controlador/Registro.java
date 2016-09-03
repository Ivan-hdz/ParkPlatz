/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import ParkPlatzWSC.Administrador;
import ParkPlatzWSC.Conductor;
import ParkPlatzWSC.NoSuchAlgorithmException_Exception;
import ParkPlatzWSC.ParkPlatzWS_Service;
import ParkPlatzWSC.UnsupportedEncodingException_Exception;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "Registro", urlPatterns = {"/Registro"})
public class Registro extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Parkplatz-web/ParkPlatzWS.wsdl")
    private ParkPlatzWS_Service service;

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
            throws ServletException, IOException, NoSuchAlgorithmException_Exception, UnsupportedEncodingException_Exception {
        
        
        Conductor conduc = new Conductor();
        String nombre, aPaterno, aMaterno, email, password, captcha;
        boolean exito;
        nombre = request.getParameter("name");
        aPaterno = request.getParameter("aPaterno");
        aMaterno = request.getParameter("aMaterno");
        email = request.getParameter("email");
        password = request.getParameter("password");
        captcha = request.getParameter("g-recaptcha-response");
        boolean isAd = false;
        String cad = "@parkplatz.";
        boolean validCo = false;
        boolean validDat = true;
        boolean validCaptcha = false;

        for(int i = 0; i < email.length(); i++){
            if(email.charAt(i) == '@'){
                validCo = true;
                if(email.substring(i, email.length()).length() > 12){
                    if(cad.equalsIgnoreCase(email.substring(i, (i + 11)))){
                    isAd = true;
                    }
                }
                
            }
        }
        
        if((nombre.equals("")) && (aPaterno.equals("")) && (aMaterno.equals("")) && (password.equals(""))){
            validDat = false;
        }
        //validar capthca
        validCaptcha = VerifyRecaptcha.verify(captcha);
      if(validCo && validDat && validCaptcha)  
      {
        if(!isAd){
                
            conduc.setNombre(nombre);
            conduc.setAPaterno(aPaterno);
            conduc.setAMaterno(aMaterno);
            conduc.setCorreo(email);
            conduc.setPass(password);
            exito = registrarConductor(conduc);

            if(exito){
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<script type=\"text/javascript\">alert(\"Registro Exitoso. Inicie Sesion\");</script>");            
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
                    out.println("<script type=\"text/javascript\">alert(\"Hubo un error... Intente Otra Vez\");</script>");            
                    out.println("</head>");

                    out.println("</html>"); 
                    out.println("<META HTTP-EQUIV='REFRESH' CONTENT='.0000001;URL=http://localhost/Parkplatz-web/Conductor/registro.html'/>");
                }
            }
        }
        else{
            Administrador admin = new Administrador();
            admin.setNombre(nombre);
            admin.setAPaterno(aPaterno);
            admin.setAMaterno(aMaterno);
            admin.setCorreo(email);
            admin.setPass(password);
            exito = registrarAdministrador(admin);
            if(exito){
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<script type=\"text/javascript\">alert(\"exito\");</script>");            
                    out.println("</head>");

                    out.println("</html>"); 
                    out.println("<META HTTP-EQUIV='REFRESH' CONTENT='.0000001;URL=http://localhost/Parkplatz-web/index.jsp'/>");
                }
            }
            else{
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<script type=\"text/javascript\">alert(\"Hubo un error... Intente Otra Vez\");</script>");            
                    out.println("</head>");

                    out.println("</html>"); 
                    out.println("<META HTTP-EQUIV='REFRESH' CONTENT='.0000001;URL=http://localhost/Parkplatz-web/Conductor/registro.html'/>");
                }
            }
        }
    }
      else{
          try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<script type=\"text/javascript\">alert(\"Ups.. Intente otra vez. Complete todos los campos e Ingrese correctamente el email\");</script>");            
                    out.println("</head>");

                    out.println("</html>"); 
                    out.println("<META HTTP-EQUIV='REFRESH' CONTENT='.0000001;URL=http://localhost/Parkplatz-web/Conductor/registro.html'/>");
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
        } catch (NoSuchAlgorithmException_Exception ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException_Exception ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NoSuchAlgorithmException_Exception ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException_Exception ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
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

    private boolean registrarConductor(ParkPlatzWSC.Conductor objetoLleno) throws NoSuchAlgorithmException_Exception, UnsupportedEncodingException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.registrarConductor(objetoLleno);
    }

    private boolean registrarAdministrador(ParkPlatzWSC.Administrador objetoLleno) throws UnsupportedEncodingException_Exception, NoSuchAlgorithmException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.registrarAdministrador(objetoLleno);
    }

}

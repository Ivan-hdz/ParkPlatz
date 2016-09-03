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
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author UCER
 */
@WebServlet(name = "Modificar", urlPatterns = {"/Modificar"})
public class Modificar extends HttpServlet {
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
            throws ServletException, IOException, UnsupportedEncodingException_Exception, NoSuchAlgorithmException_Exception {
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
            
            admin.setNombre(nombre);
            admin.setAPaterno(aPaterno);
            admin.setAMaterno(aMaterno);
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
        }
        else{
                    
            conduc.setNombre(nombre);
            conduc.setAPaterno(aPaterno);
            conduc.setAMaterno(aMaterno);
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
        } catch (UnsupportedEncodingException_Exception ex) {
            Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException_Exception ex) {
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
        } catch (UnsupportedEncodingException_Exception ex) {
            Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException_Exception ex) {
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

    private Conductor actualizarDatosConductor(ParkPlatzWSC.Conductor obj) throws UnsupportedEncodingException_Exception, NoSuchAlgorithmException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.actualizarDatosConductor(obj);
    }

    private Administrador actualizarDatosAdmin(ParkPlatzWSC.Administrador obj) throws UnsupportedEncodingException_Exception, NoSuchAlgorithmException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.actualizarDatosAdmin(obj);
    }


    
}

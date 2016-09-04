/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.ovrwrite.parkplatz.web.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import me.ovrwrite.parkplatz.web.controller.beans.Administrador;
import me.ovrwrite.parkplatz.web.controller.beans.Estacionamiento;

/**
 *
 * @author Ivan
 */
public class ServletConsultaUsuarios extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequestGetTotalUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            if(session.getAttribute("conductor") == null){         
                    out.println("<META HTTP-EQUIV='REFRESH' CONTENT='.0000001;URL=http://localhost/Parkplatz-web/index.html'/>");
                }
                else{    
                    try {
                        int nUsrT = 0, nUsrE = 0, nUsrC= 0, nUsrA = 0;
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/parkplatz","root", "n0m3l0");
                        PreparedStatement ps = c.prepareStatement("select count(*) as nUsuarios from usuario");
                       
                        ResultSet rs = ps.executeQuery();
                        if(rs.next()){    
                            nUsrT = rs.getInt("nUsuarios");
                        }

                        ps = c.prepareStatement("select count(*) as nUsuarios from usuario where idTipoUsuario = 2");
                        rs = ps.executeQuery();
                        if(rs.next()){    
                            nUsrE = rs.getInt("nUsuarios");
                        }
                        
                        ps = c.prepareStatement("select count(*) as nUsuarios from usuario where idTipoUsuario = 3");
                        rs = ps.executeQuery();
                        if(rs.next()){    
                            nUsrC = rs.getInt("nUsuarios");
                        }
                        
                        ps = c.prepareStatement("select count(*) as nUsuarios from usuario where idTipoUsuario = 1");
                        rs = ps.executeQuery();
                        if(rs.next()){    
                            nUsrA = rs.getInt("nUsuarios");
                        }

                    if(nUsrT != 0){

                        out.println("<table>"
                                + "<tr>"
                                + "<td><h3>Total de Usuarios</h3></td>"
                                + "<td><h3>Usuarios Estacionamientos</h3></td>"
                                + "<td><h3>Usuarios Conductores</h3></td>"
                                + "<td><h3>Usuarios Administradores</h3></td>"
                                + "</tr>"
                                
                                + "<tr>"
                                + "<td><h2>" + nUsrT + "</h2></td>"
                                + "<td><h2>" + nUsrE + "</h2></td>"
                                + "<td><h2>" + nUsrC + "</h2></td>"
                                + "<td><h2>" + nUsrA + "</h2></td>"
                                + "</tr>");
                        out.println("</table>");
                    }
                    else{
                    out.println("<span class='byline'>No hay Usuarios</span>");
                    }                            
                            
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                  
                }
        }
    }
    protected void processRequestPostEstasBorrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            if(session.getAttribute("conductor") == null){         
                    out.println("<META HTTP-EQUIV='REFRESH' CONTENT='.0000001;URL=http://localhost/Parkplatz-web/index.html'/>");
                }else{
                    try{
                            
                            String g = request.getParameter("nombre");
                            ArrayList<Estacionamiento> estas =(ArrayList<Estacionamiento>)recuperaEsta(g);
                             out.println("<table>"
                                + "<tr>"
                                + "<td><h2>Nombre</h2></td>"
                                + "<td><h2>Direccion</h2></td>"
                                + "</tr>");
                              
                             for(int i = 0; i<estas.size(); i++){
                                 
                                 Estacionamiento esta = estas.get(i);
                               
                                out.println("<tr>"
                                    + "<td><h1>" + esta.getNombreEsta() + "</h1><br></td>"
                                    + "<td><span class='byline'>" + esta.getCalle() +", " + esta.getColonia() + ", " + esta.getDel_muni()
                                    + ", " + esta.getEstado()+"</span><br></td>"
                                    + "</tr>");
                                out.println("<tr><td><form action='../Baja' method='post' onSubmit=\"if(!confirm('¿Estas seguro de darlo de baja?')){return false;}\">"
                                        + "<button type='submit' class='button small' >Eliminar</button>"
                                        + "<input type='hidden' value='"+esta.getIdEstacionamiento()+"' name='id'>"
                                        + "</form></td></tr>");
                                
                             }
                                 out.println("</table>");
                            

                               
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                  
                }
        }
    }
    protected void processRequestPostEstasAviso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            if(session.getAttribute("conductor") == null){         
                    out.println("<META HTTP-EQUIV='REFRESH' CONTENT='.0000001;URL=http://localhost/Parkplatz-web/index.html'/>");
                }else{
                    try{
                            
                            String g = request.getParameter("nombre");
                            ArrayList<Estacionamiento> estas =(ArrayList<Estacionamiento>)recuperaEsta(g);
                             out.println("<table>"
                                + "<tr>"
                                + "<td><h2>Nombre</h2></td>"
                                + "<td><h2>Direccion</h2></td>"
                                + "</tr>");
                              
                             for(int i = 0; i<estas.size(); i++){
                                 
                                 Estacionamiento esta = estas.get(i);
                               
                                out.println("<tr>"
                                        + "<td><h1>" + esta.getNombreEsta() + "</h1><br></td>"
                                        + "<td><span class='byline'>" + esta.getCalle() +", " + esta.getColonia() + ", " + esta.getDel_muni()
                                        + ", " + esta.getEstado()+"</span><br></td>"
                                        + "</tr>");
                                    out.println("<tr><td></td><td><form action='../Aviso' method='post' id='form'>"
                                            + "<textarea name='aviso' required form='form' placeholder='Escribe el aviso a dar'></textarea><br>"
                                            + "<button type='submit' class='button small' >Dar Aviso</button>"
                                            + "<input type='hidden' value='"+esta.getIdEstacionamiento()+"' name='id'>"
                                            + "</form></td></tr>");                            
                             }
                                 out.println("</table>");
                            

                               
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                  
                }
        }
    }
  
    public ArrayList<Estacionamiento> recuperaEsta( String nombre) throws ClassNotFoundException {
        Administrador admin = new Administrador();
        
        ArrayList<Estacionamiento> estas = admin.recuperaEstacionamiento(nombre);
        
        return estas;
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
        
            processRequestGetTotalUsers(request, response);
        
        
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
        if(request.getParameter("opc").equals("1")){
            processRequestPostEstasBorrar(request, response);
        }else if ( request.getParameter("opc").equals("2")){
            processRequestPostEstasAviso(request, response);
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

}

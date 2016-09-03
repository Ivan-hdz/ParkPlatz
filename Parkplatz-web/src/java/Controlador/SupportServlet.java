/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import ParkPlatzWSC.Administrador;
import ParkPlatzWSC.Conductor;
import ParkPlatzWSC.Conversacion;
import ParkPlatzWSC.ParkPlatzWS_Service;
import ParkPlatzWSC.SoporteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Ivan
 */
public class SupportServlet extends HttpServlet {

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
    protected void processRequestPostAdminTraeMensajes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            ArrayList<Conversacion> convers = (ArrayList)traeSuppLogs();
            Conversacion conv = null;
            ObjectMapper mapper = new ObjectMapper();
            if(request.getParameter("ID") == null){
                
            }else{
            int ID = (int)Double.parseDouble(String.valueOf(request.getParameter("ID")));
            System.out.println(ID+ "<-- tra mensajito");
            int index = -1;
            for(int i = 0; i<convers.size(); i++){
                conv = convers.get(i);
                if(conv.getId() == ID){
                    break;
                }
            }
                
            out.println(mapper.writeValueAsString(conv));
            }
        }
    }
    protected void processRequestGetAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           ObjectMapper mapper = new ObjectMapper();
            System.out.println("pasa por traer todo admin");
           out.println(mapper.writeValueAsString((ArrayList)traeSuppLogs()));
        }
    }
    protected void processRequestPostUserTraeMensajes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            ArrayList<Conversacion> convers = (ArrayList)traeSuppLogs();
            Conversacion conv = null;
            ObjectMapper mapper = new ObjectMapper();
            if(request.getParameter("ID") == null){
                
            }else{
            int ID = (int)Double.parseDouble(String.valueOf(request.getParameter("ID")));
            System.out.println(ID+ "<-- tra mensajito");
            int index = -1;
            for(int i = 0; i<convers.size(); i++){
                conv = convers.get(i);
                if(conv.getId() == ID){
                    break;
                }
            }
                
            out.println(mapper.writeValueAsString(conv));
            }
        }
    }
    protected void processRequestPostUserNuevaConver(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            ArrayList<Conversacion> convers = (ArrayList)traeSuppLogs();
            HttpSession sesion = request.getSession();
            Conductor cond = (Conductor)sesion.getAttribute("conductor");
            String emisor = cond.getCorreo();
            Conversacion conv = new Conversacion();
            conv.setAsunto(request.getParameter("asunto"));
            conv.setEmisor(emisor);
            conv.setId((int)Double.parseDouble(String.valueOf(request.getParameter("ID"))));
            
            conv.getMensajes().clear();
            conv.getMensajes().add("ParkPlatz >> En un momento se pondra en contacto un administrador contigo");
            convers.add(conv);
            guardaSuppLogs(convers);
        }
    }
    protected void processRequestPostUserEnviarMensaje(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ArrayList<Conversacion> convers = (ArrayList)traeSuppLogs();
            HttpSession sesion = request.getSession();
            Conductor cond = (Conductor)sesion.getAttribute("conductor");
            Conversacion conv = new Conversacion();
            String mensajeFinal = cond.getCorreo()+" >> "+request.getParameter("mensaje");
            int ID = Integer.parseInt(request.getParameter("ID"));
            int index = -1;
           
            for(int i = 0; i<convers.size(); i++){
                conv = convers.get(i);
                if(conv.getId() == ID){
                    index = i;
                    break;
                }
            }
            conv.getMensajes().add(mensajeFinal);
            convers.set(index, conv);
            guardaSuppLogs((List)convers);
        }
    }

    protected void processRequestPostAdminEnviarMensaje(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ArrayList<Conversacion> convers = (ArrayList)traeSuppLogs();
            HttpSession sesion = request.getSession();
            Administrador admin = (Administrador)sesion.getAttribute("conductor");
            Conversacion conv = null;
            String mensajeFinal = request.getParameter("mensaje")+ " << "+ admin.getCorreo();
            int ID = Integer.parseInt(request.getParameter("ID"));
            int index = -1;
            for(int i = 0; i<convers.size(); i++){
                conv = convers.get(i);
                if(conv.getId() == ID){
                    index = i;
                    break;
                }
            }
            conv.getMensajes().add(mensajeFinal);
            convers.set(index, conv);
            guardaSuppLogs((List)convers);
        }
    }
    protected void processRequestGetUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession sesion = request.getSession();
           ArrayList<Conversacion> convers = new ArrayList();
           Conductor cond = (Conductor)sesion.getAttribute("conductor");
           String email = cond.getCorreo();
           
           ArrayList<Conversacion> conversAll = (ArrayList)traeSuppLogs();
           
           for(int i = 0; i<conversAll.size();i++){
               if(conversAll.get(i).getEmisor().equals(email)){
                   convers.add(conversAll.get(i));
                   System.out.println("<--> Id conversaciones");
                    System.out.println(convers.get(i).getId());
                    System.out.println("<-->");
               }
           }
           
           ObjectMapper mapper = new ObjectMapper();
           out.println(mapper.writeValueAsString(convers));
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
        //Trae todas las conversaciones
            if(request.getParameter("admin").equals("si")){
                processRequestGetAdmin(request, response);
            } else {
                processRequestGetUser(request, response);
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
        String op = request.getParameter("operacion");
        if(request.getParameter("admin").equals("si")){
            if(op.equals("getMensajes")){ //trae una conver
                processRequestPostAdminTraeMensajes(request, response);
            }else if(op.equals("sendMensaje")){
                processRequestPostAdminEnviarMensaje(request, response);
            }
        }else{
            if(op.equals("getMensajes")){
                processRequestPostUserTraeMensajes(request, response);
            }else if(op.equals("newConver")){
                processRequestPostUserNuevaConver(request, response);
            }else if(op.equals("sendMensaje")){
                processRequestPostUserEnviarMensaje(request, response);
            }
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


    private java.util.List<ParkPlatzWSC.Conversacion> traeSuppLogs() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.traeSuppLogs();
    }

    private SoporteRepository guardaSuppLogs(java.util.List<ParkPlatzWSC.Conversacion> arrayConversacion) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
        return port.guardaSuppLogs(arrayConversacion);
    }




  

}

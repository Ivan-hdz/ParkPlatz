/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.ovrwrite.parkplatz.web.controller;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import me.ovrwrite.parkplatz.web.controller.beans.Constants;
import me.ovrwrite.parkplatz.web.model.Sql;

/**
 *
 * @author honte_000
 */
public class Email {

    
    public String MENSAJE_RECUPERA_CONTRA;
    private String id;    
    private String para;
    public Email(String correo){
        ;
        this.para = correo;
        Seguridad seg = new Seguridad();
        try {
            seg.setKey(Seguridad.genKey(correo));
            id = Seguridad.toSHA256(verContraActual(correo));
            id = seg.encriptar(id);
            System.out.println(correo);
            System.out.println(id+" <---contra cifrado enviado en el email");
            MENSAJE_RECUPERA_CONTRA = mensajeHTML(para, id);
          } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean enviar(String mensaje){
        boolean success = false;
    // Recipient's email ID needs to be mentioned.
      String to = para;

      // Sender's email ID needs to be mentioned
      String from = "honter1997@gmail.com";
      final String user = "honter1997@gmail.com";
      final String pass = "KarenChiquitaBB";
      
      String host = "smtp.gmail.com";

      // Get system properties
      Properties properties = System.getProperties();

       Properties props = new Properties();
      props.put("mail.smtp.ssl.trust", host);
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.port", 587);

      Session session = Session.getDefaultInstance(props, null);
      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("Reestablecer contraseña");

         // Send the actual HTML message, as big as you like
         message.setContent(mensaje, "text/html" );
        
         // Send message
         Transport transport = session.getTransport("smtp");
         transport.connect("smtp.gmail.com", user, pass);
         transport.sendMessage(message, message.getAllRecipients());
         System.out.println("Sent message successfully....");
         success = true;
      }catch (javax.mail.MessagingException mex) {
          System.out.println(mex.getMessage());
          success = false;
      }catch(Exception e){
          success = false;
      }
      return success;
   }

    private String mensajeHTML(String correo, String key) {
       String html;
       html = "<body>"
               +"<style>"
               + "header{"
               + "background-color: #141414;"
               + "border-radius: 5px;"
               + "color: white;"
               + "font-family: Arial;"
               + "}"
               + "button{"
               + "background-color: white;"
               + "color: #141414;"
               + "border: 1px solid black;"
               + "border-radius: 5px;"
               + "transition: all 0.8s"
               + "}"
               + "button:hover{"
               + "color: white;"
               + "background-color: black;"
               + "}"
               + "</style>"
               + "<header>"
               + "<h1>¿Has olvidado tu contraseña?</h1>"
               + "<h2>Puedes recuperarla dando clic en el siguiente BOTON </h2>"
               + "</header>"
               + "<a href='http://"+Constants.mainDomain+"/"+ Constants.proyectName +"/recuperame.jsp?email="
               +correo+"&id="+key+"'>"
               + "<button type='button'>PRESIONA AQUI </button>"
               + "</a></body>";
               
       return html;
    }

  

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.ovrwrite.parkplatz.web.controller;

import java.util.ArrayList;

/**
 *
 * @author Ivan
 */
public class Conversacion {
     private String emisor;
    private String asunto;
    private ArrayList<String> mensajes;
    private int id;

            /**
             * @return the emisor
             */
            public String getEmisor() {
                return emisor;
            }

            /**
             * @param emisor the emisor to set
             */
            public void setEmisor(String emisor) {
                this.emisor = emisor;
            }

            /**
             * @return the asunto
             */
            public String getAsunto() {
                return asunto;
            }

            /**
             * @param asunto the asunto to set
             */
            public void setAsunto(String asunto) {
                this.asunto = asunto;
            }

            /**
             * @return the mensajes
             */
            public ArrayList<String> getMensajes() {
                return mensajes;
            }

            /**
             * @param mensajes the mensajes to set
             */
            public void setMensajes(ArrayList<String> mensajes) {
                this.mensajes = mensajes;
            }

            /**
             * @return the id
             */
            public int getId() {
                return id;
            }

            /**
             * @param id the id to set
             */
            public void setId(int id) {
                this.id = id;
            }
}

package com.example.ucer.WebService;

import android.graphics.Bitmap;

import com.example.ucer.Controlador.Conductor;
import com.example.ucer.Controlador.Estacionamiento;
import com.example.ucer.parkplatzapp.Main_Window;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UCER on 16/04/16.
 */

public class WebService {
    private static String NAMESPACE = "http://WS.Controlador/";
   //Cambiar URL por ip donde se ejecuta el webservice, asegurarse de que se ejecute en
    //el puerto 80
    //IP en la clase Main_Window
    private static String URL = "http://"+ Main_Window.IP+"/Parkplatz-web/ParkPlatzWS?WSDL";

    private static String SOAP_ACTION_LOGIN = "http://WS.Controlador/operacion_Login_Android";
    private static String METHOD_NAME_LOGIN = "operacion_Login_Android";

    private static String SOAP_ACTION_REGISTRO = "http://WS.Controlador/operacion_Registro_Android";
    private static String METHOD_NAME_REGISTRO= "operacion_Registro_Android";

    private static String SOAP_ACTION_FAVORITOS = "http://WS.Controlador/operacion_Mis_Favs";
    private static String METHOD_NAME_FAVORITOS = "operacion_Mis_Favs";

    private static String SOAP_ACTION_RECIENTES = "http://WS.Controlador/operacion_Mis_Recs";
    private static String METHOD_NAME_RECIENTES = "operacion_Mis_Recs";

    private static String SOAP_ACTION_BORRA_FAV = "http://WS.Controlador/operacion_Borra_Fav";
    private static String METHOD_NAME_BORRA_FAV = "operacion_Borra_Fav";

    private static String SOAP_ACTION_DETALLES = "http://WS.Controlador/operacion_Detalles_Estacionamiento";
    private static String METHOD_NAME_DETALLES = "operacion_Detalles_Estacionamiento";

    private static String SOAP_ACTION_RECUPERAR = "http://WS.Controlador/operacion_Recuperar_Datos";
    private static String METHOD_NAME_RECUPERAR = "operacion_Recuperar_Datos";

    private static String SOAP_ACTION_MODIFICAR = "http://WS.Controlador/operacion_Actualiza_Datos";
    private static String METHOD_NAME_MODIFICAR = "operacion_Actualiza_Datos";

    private static String SOAP_ACTION_MI_MAPA = "http://WS.Controlador/operacion_Mi_Mapa";
    private static String METHOD_NAME_MI_MAPA = "operacion_Mi_Mapa";

    private static String SOAP_ACTION_NUEVO_REC = "http://WS.Controlador/operacion_Nuevo_Reciente";
    private static String METHOD_NAME_NUEVO_REC = "operacion_Nuevo_Reciente";

    private static String SOAP_ACTION_NUEVO_FAV = "http://WS.Controlador/operacion_Nuevo_Favorito";
    private static String METHOD_NAME_NUEVO_FAV = "operacion_Nuevo_Favorito";

    private static String SOAP_ACTION_IS_FAV = "http://WS.Controlador/operacion_Is_Fav";
    private static String METHOD_NAME_IS_FAV = "operacion_Is_Fav";

    private static String SOAP_ACTION_FEED = "http://WS.Controlador/operacion_Feed";    private static String METHOD_NAME_FEED = "operacion_Feed";

    private static String SOAP_ACTION_SEARCH = "http://WS.Controlador/operacion_buscar";
    private static String METHOD_NAME_SEARCH = "operacion_buscar";

    private static String SOAP_ACTION_MURO = "http://WS.Controlador/operacion_Muro";
    private static String METHOD_NAME_MURO = "operacion_Muro";

    private static String SOAP_ACTION_NEW_COMEN = "http://WS.Controlador/operacion_NewComen";
    private static String METHOD_NAME_NEW_COMEN = "operacion_NewComen";

    private static String SOAP_ACTION_BUS_AMIGO = "http://WS.Controlador/operacion_busAmigos";
    private static String METHOD_NAME_BUS_AMIGO = "operacion_busAmigos";

    private static String SOAP_ACTION_NEW_AMIGO = "http://WS.Controlador/operacion_newAmigo";
    private static String METHOD_NAME_NEW_AMIGO = "operacion_newAmigo";

    private static String SOAP_ACTION_AMIGOS = "http://WS.Controlador/operacion_getAmigos";
    private static String METHOD_NAME_AMIGOS = "operacion_getAmigos";

    private static String SOAP_ACTION_LUGARES = "http://WS.Controlador/operacion_Lugares";
    private static String METHOD_NAME_LUGARES = "operacion_Lugares";

    public static String invokeLoginWS(String correo, String pass){

        String cadena = "01";

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_LOGIN);

        PropertyInfo wsLoginInfo_Correo = new PropertyInfo();
        wsLoginInfo_Correo.setName("correo");
        wsLoginInfo_Correo.setValue(correo);
        wsLoginInfo_Correo.setType(String.class);
        request.addProperty(wsLoginInfo_Correo);

        PropertyInfo wsLoginInfo_Pass = new PropertyInfo();
        wsLoginInfo_Pass.setName("pass");
        wsLoginInfo_Pass.setValue(pass);
        wsLoginInfo_Pass.setType(String.class);
        request.addProperty(wsLoginInfo_Pass);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            androidHttpTransport.call(SOAP_ACTION_LOGIN, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            cadena = response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            cadena = "Ocurrio un Error";
        }


        return cadena;
    }

    public static String invokeRegistroWS(String nombre, String aPaterno, String aMaterno, String correo, String pass){
        String cadena = null;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_REGISTRO);
        PropertyInfo nombrePI = new PropertyInfo();
        nombrePI.setName("nombre");
        nombrePI.setValue(nombre);
        nombrePI.setType(String.class);

        PropertyInfo aPaternoPi = new PropertyInfo();
        aPaternoPi.setName("aPaterno");
        aPaternoPi.setValue(aPaterno);
        aPaternoPi.setType(String.class);

        PropertyInfo aMaternoPi = new PropertyInfo();
        aMaternoPi.setName("aMaterno");
        aMaternoPi.setValue(aMaterno);
        aMaternoPi.setType(String.class);

        PropertyInfo correoPI = new PropertyInfo();
        correoPI.setName("correo");
        correoPI.setValue(correo);
        correoPI.setType(String.class);

        PropertyInfo passPI = new PropertyInfo();
        passPI.setName("pass");
        passPI.setValue(pass);
        passPI.setType(String.class);

        request.addProperty(nombrePI);
        request.addProperty(aPaternoPi);
        request.addProperty(aMaternoPi);
        request.addProperty(correoPI);
        request.addProperty(passPI);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try{
            androidHttpTransport.call(SOAP_ACTION_REGISTRO, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            cadena = response.toString();
        }
        catch (Exception e){
            e.printStackTrace();
            cadena = "Ocurrio un Error";
        }

        return cadena;
    }

    public static List<Estacionamiento> invokeSearchWS(String correo){
        List<Estacionamiento> lista = new ArrayList<Estacionamiento>();
        String []cadenaFavs, cadena;
        Estacionamiento estacionamiento = null;
        Bitmap bmp;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_SEARCH);

        PropertyInfo wsInfo_Correo = new PropertyInfo();
        wsInfo_Correo.setName("id");
        wsInfo_Correo.setValue(correo);
        wsInfo_Correo.setType(String.class);
        request.addProperty(wsInfo_Correo);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try{
            transporte.call(SOAP_ACTION_SEARCH, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            cadenaFavs = response.toString().split("//");
            lista = new ArrayList<Estacionamiento>();

            for(int i = 0; i < cadenaFavs.length; i++){
                cadena = cadenaFavs[i].split(";");

                estacionamiento = new Estacionamiento(cadena[0], cadena[1], cadena[2]);
                lista.add(estacionamiento);
            }
            return lista;

        } catch (Exception e){
            e.printStackTrace();
        }

        return lista;
    }


    public static List<Estacionamiento> invokeFavoritosWS(String correo){
        List<Estacionamiento> lista = new ArrayList<Estacionamiento>();
        String []cadenaFavs, cadena;
        Estacionamiento estacionamiento = null;
        Bitmap bmp;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_FAVORITOS);

        PropertyInfo wsInfo_Correo = new PropertyInfo();
        wsInfo_Correo.setName("correo");
        wsInfo_Correo.setValue(correo);
        wsInfo_Correo.setType(String.class);
        request.addProperty(wsInfo_Correo);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try{
            transporte.call(SOAP_ACTION_FAVORITOS, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            cadenaFavs = response.toString().split("//");
            lista = new ArrayList<Estacionamiento>();

            for(int i = 0; i < cadenaFavs.length; i++){
                cadena = cadenaFavs[i].split(";");

                estacionamiento = new Estacionamiento(cadena[0], cadena[1], cadena[2]);
                lista.add(estacionamiento);
            }
            return lista;

        } catch (Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    public static List<Estacionamiento> invokeRecientesWS(String correo){
        List<Estacionamiento> lista = new ArrayList<Estacionamiento>();
        String []cadenaFavs, cadena;
        Estacionamiento estacionamiento = null;
        Bitmap bmp;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_RECIENTES);

        PropertyInfo wsInfo_Correo = new PropertyInfo();
        wsInfo_Correo.setName("correo");
        wsInfo_Correo.setValue(correo);
        wsInfo_Correo.setType(String.class);
        request.addProperty(wsInfo_Correo);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try{
            transporte.call(SOAP_ACTION_RECIENTES, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            cadenaFavs = response.toString().split("//");
            lista = new ArrayList<Estacionamiento>();

            for(int i = 0; i < cadenaFavs.length; i++){
                cadena = cadenaFavs[i].split(";");

                estacionamiento = new Estacionamiento(cadena[0], cadena[1], cadena[2], cadena[3]);
                lista.add(estacionamiento);
            }
            return lista;

        } catch (Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    public static String invokeBorraFavWS(String correo, String id){

        String cadena = null;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_BORRA_FAV);

        PropertyInfo info_correo = new PropertyInfo();
        info_correo.setName("correo");
        info_correo.setValue(correo);
        info_correo.setType(String.class);

        PropertyInfo info_id = new PropertyInfo();
        info_id.setName("idEsta");
        info_id.setValue(id);
        info_id.setType(String.class);

        request.addProperty(info_correo);
        request.addProperty(info_id);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try{
            transporte.call(SOAP_ACTION_BORRA_FAV, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            cadena = response.toString();
            return cadena;
        }
        catch (Exception e){
            e.printStackTrace();
            cadena = "Intente mas tarde";
        }
        return cadena;
    }

    public static String invokeDetallesEstaWS(String id){

        String cadena = null;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_DETALLES);

        PropertyInfo info_id = new PropertyInfo();
        info_id.setName("idEsta");
        info_id.setValue(id);
        info_id.setType(String.class);
        request.addProperty(info_id);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try{
            transporte.call(SOAP_ACTION_DETALLES, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            cadena = response.toString();
            return cadena;

        }catch (Exception e){
            e.printStackTrace();
            cadena = "Intente mas Tarde";
        }

        return cadena;
    }

    public static String invokeRecuperaDatosWS(String correo){

        String cadena = null;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_RECUPERAR);

        PropertyInfo info_id = new PropertyInfo();
        info_id.setName("correo");
        info_id.setValue(correo);
        info_id.setType(String.class);
        request.addProperty(info_id);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try{
            transporte.call(SOAP_ACTION_RECUPERAR, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            cadena = response.toString();
            return cadena;

        }catch (Exception e){
            e.printStackTrace();
            cadena = "Intente mas Tarde";
        }

        return cadena;
    }

    public static String invokeModificarDatosWS(String nombre, String aPaterno, String aMaterno, String correo, String pass){
        String cadena = null;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_MODIFICAR);
        PropertyInfo nombrePI = new PropertyInfo();
        nombrePI.setName("nombre");
        nombrePI.setValue(nombre);
        nombrePI.setType(String.class);

        PropertyInfo aPaternoPi = new PropertyInfo();
        aPaternoPi.setName("aPaterno");
        aPaternoPi.setValue(aPaterno);
        aPaternoPi.setType(String.class);

        PropertyInfo aMaternoPi = new PropertyInfo();
        aMaternoPi.setName("aMaterno");
        aMaternoPi.setValue(aMaterno);
        aMaternoPi.setType(String.class);

        PropertyInfo correoPI = new PropertyInfo();
        correoPI.setName("correo");
        correoPI.setValue(correo);
        correoPI.setType(String.class);

        PropertyInfo passPI = new PropertyInfo();
        passPI.setName("pass");
        passPI.setValue(pass);
        passPI.setType(String.class);

        request.addProperty(nombrePI);
        request.addProperty(aPaternoPi);
        request.addProperty(aMaternoPi);
        request.addProperty(correoPI);
        request.addProperty(passPI);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try{
            androidHttpTransport.call(SOAP_ACTION_MODIFICAR, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            cadena = response.toString();
        }
        catch (Exception e){
            e.printStackTrace();
            cadena = "Ocurrio un Error";
        }

        return cadena;
    }

    public static List<Estacionamiento> invokeMiMapaWs(){
        List<Estacionamiento> lista = new ArrayList<Estacionamiento>();
        String []cadenaFavs, cadena;
        Estacionamiento estacionamiento = null;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_MI_MAPA);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try{
            transporte.call(SOAP_ACTION_MI_MAPA, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            cadenaFavs = response.toString().split("//");
            lista = new ArrayList<Estacionamiento>();

            for(int i = 0; i < cadenaFavs.length; i++){
                cadena = cadenaFavs[i].split(";");

                estacionamiento = new Estacionamiento();
                estacionamiento.setId(cadena[0]);
                estacionamiento.setNombre(cadena[1]);
                estacionamiento.setCoorX(cadena[2]);
                estacionamiento.setCoorY(cadena[3]);
                lista.add(estacionamiento);
            }
            return lista;

        } catch (Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    public static String invokeNuevoReciente(String correo, String idEst){
        String cadena = null;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_NUEVO_REC);

        PropertyInfo info_correo = new PropertyInfo();
        info_correo.setName("correo");
        info_correo.setValue(correo);
        info_correo.setType(String.class);

        PropertyInfo info_id = new PropertyInfo();
        info_id.setName("idEsta");
        info_id.setValue(idEst);
        info_id.setType(String.class);

        request.addProperty(info_correo);
        request.addProperty(info_id);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try{
            transporte.call(SOAP_ACTION_NUEVO_REC, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            cadena = response.toString();
        }
        catch (Exception e){
            e.printStackTrace();
            cadena = "Intente mas tarde";
        }
        return cadena;
    }

    public static String invokeNuevoFavorito(String correo, String idEst){
        String cadena = null;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_NUEVO_FAV);

        PropertyInfo info_correo = new PropertyInfo();
        info_correo.setName("correo");
        info_correo.setValue(correo);
        info_correo.setType(String.class);

        PropertyInfo info_id = new PropertyInfo();
        info_id.setName("idEsta");
        info_id.setValue(idEst);
        info_id.setType(String.class);

        request.addProperty(info_correo);
        request.addProperty(info_id);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try{
            transporte.call(SOAP_ACTION_NUEVO_FAV, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            cadena = response.toString();
        }
        catch (Exception e){
            e.printStackTrace();
            cadena = "Intente mas tarde";
        }
        return cadena;
    }

    public static boolean invokeisFav(String correo, String idEst){
        String cadena = null;
        boolean isFav = false;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_IS_FAV);

        PropertyInfo info_correo = new PropertyInfo();
        info_correo.setName("correo");
        info_correo.setValue(correo);
        info_correo.setType(String.class);

        PropertyInfo info_id = new PropertyInfo();
        info_id.setName("idEsta");
        info_id.setValue(idEst);
        info_id.setType(String.class);

        request.addProperty(info_correo);
        request.addProperty(info_id);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try{
            transporte.call(SOAP_ACTION_IS_FAV, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            cadena = response.toString();
        }
        catch (Exception e){
            e.printStackTrace();
            cadena = "Intente mas tarde";
        }

        if(cadena.equals("si")){
            isFav = true;
        }
        return isFav;
    }

    public static String invokeFeedWS(String correo, String msj){
        String cad = "";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_FEED);

        PropertyInfo infoCorreo = new PropertyInfo();
        infoCorreo.setName("correo");
        infoCorreo.setValue(correo);
        infoCorreo.setType(String.class);

        PropertyInfo infoMsj = new PropertyInfo();
        infoMsj.setName("msj");
        infoMsj.setValue(msj);
        infoMsj.setType(String.class);

        request.addProperty(infoCorreo);
        request.addProperty(infoMsj);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try {
            transporte.call(SOAP_ACTION_FEED, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            cad = response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            cad = "Ups... Ocurriol un error";
        }

        return cad;
    }

    public static List<Conductor> invokeAmigos(String correo){
        List<Conductor> lista = new ArrayList<Conductor>();;
        String []cadenaConduc, cadena;
        Conductor conductor = null;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_AMIGOS);

        PropertyInfo info_correo = new PropertyInfo();
        info_correo.setName("correo");
        info_correo.setValue(correo);
        info_correo.setType(String.class);

        request.addProperty(info_correo);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try{
            transporte.call(SOAP_ACTION_AMIGOS, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            cadenaConduc = response.toString().split("//");
            lista = new ArrayList<Conductor>();

            for(int i = 0; i < cadenaConduc.length; i++){
                cadena = cadenaConduc[i].split(";");

                conductor = new Conductor();
                conductor.setCorreo(cadena[0]);
                conductor.setNombre(cadena[1]);
                lista.add(conductor);
            }
            return lista;

        } catch (Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    public static String invokeComen(String msj){
        String cadena = null;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_NEW_COMEN);

        PropertyInfo info_correo = new PropertyInfo();
        info_correo.setName("msj");
        info_correo.setValue(msj);
        info_correo.setType(String.class);

        request.addProperty(info_correo);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try{
            transporte.call(SOAP_ACTION_NEW_COMEN, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            cadena = response.toString();
        }
        catch (Exception e){
            e.printStackTrace();
            cadena = "Intente mas tarde";
        }
        return cadena;
    }

    public static String invokeMuro(){
        String cadena = "";
        Conductor conductor = null;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_MURO);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try{
            transporte.call(SOAP_ACTION_MURO, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            cadena = response.toString();
            return cadena;

        } catch (Exception e){
            e.printStackTrace();
        }

        return cadena;
    }

    public static List<Conductor> invokeBusAmigo(String nombre){
        List<Conductor> lista = new ArrayList<Conductor>();
        String []cadenaConduc, cadena;
        Conductor conductor = null;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_BUS_AMIGO);

        PropertyInfo info_correo = new PropertyInfo();
        info_correo.setName("nombre");
        info_correo.setValue(nombre);
        info_correo.setType(String.class);

        request.addProperty(info_correo);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try{
            transporte.call(SOAP_ACTION_BUS_AMIGO, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            cadenaConduc = response.toString().split("//");
            lista = new ArrayList<Conductor>();

            for(int i = 0; i < cadenaConduc.length; i++){
                cadena = cadenaConduc[i].split(";");

                conductor = new Conductor();
                conductor.setCorreo(cadena[0]);
                conductor.setNombre(cadena[1]);
                lista.add(conductor);
            }
            return lista;

        } catch (Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    public static String invokeNewAmigo(String correo, String coAmi, String nomAmigo){
        String cadena = null;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_NEW_AMIGO);

        PropertyInfo info_correo = new PropertyInfo();
        info_correo.setName("correo");
        info_correo.setValue(correo);
        info_correo.setType(String.class);

        request.addProperty(info_correo);

        PropertyInfo info_coAmi = new PropertyInfo();
        info_coAmi.setName("coAmigo");
        info_coAmi.setValue(coAmi);
        info_coAmi.setType(String.class);

        request.addProperty(info_coAmi);

        PropertyInfo info_nom = new PropertyInfo();
        info_nom.setName("nomAmigo");
        info_nom.setValue(nomAmigo);
        info_nom.setType(String.class);

        request.addProperty(info_nom);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try{
            transporte.call(SOAP_ACTION_NEW_AMIGO, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            cadena = response.toString();
        }
        catch (Exception e){
            e.printStackTrace();
            cadena = "Intente mas tarde";
        }
        return cadena;
    }

    public static String invokeLugares(String idEst){
        String cadena = null;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_LUGARES);

        PropertyInfo info_id = new PropertyInfo();
        info_id.setName("idEsta");
        info_id.setValue(idEst);
        info_id.setType(String.class);

        request.addProperty(info_id);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try{
            transporte.call(SOAP_ACTION_LUGARES, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            cadena = response.toString();
        }
        catch (Exception e){
            e.printStackTrace();
            cadena = "Intente mas tarde";
        }
        return cadena;
    }


}

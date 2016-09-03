/*
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Dell
 */
public class Seguridad {
    private SecretKey key;
    private Cipher cif;
    private String algoritmo = "DES";
    private int keySize = 8; //Para AES seria de 16

    public static String genKey(String texto){
        char[] textoChar = texto.toCharArray();
        char[] key = new char[(textoChar.length)];
        String reg = "";
        for(int i = 0; i< textoChar.length; i++){
            switch(textoChar[i]){
                case 'a':
                {
                    key[i] = 'l';
                    break;
                }
                case 'e':
                {
                    key[i] = 'v';
                    break;
                }
                case 'i':
                {
                    key[i] = 'p';
                    break;
                }
                case 'o':
                {
                    key[i] = 's';
                    break;
                }
                case 'u':
                {
                    key[i] = 't';
                    break;
                }
                case '0':
                {
                    key[i] = 'd';
                    break;
                }
                case '1':
                {
                    key[i] = 'z';
                    break;
                }
                case '2':
                {
                    key[i] = 's';
                    break;
                }
                case '3':
                {
                    key[i] = 'a';
                    break;
                }
                case '4':
                {
                    key[i] = 'l';
                    break;
                }
                case '5':
                {
                    key[i] = 'i';
                    break;
                }
                case '6':
                {
                    key[i] = 'n';
                    break;
                }
                case '7':
                {
                    key[i] = 'a';
                    break;
                }
                case '8':
                {
                    key[i] = 's';
                    break;
                }
                case '9':
                {
                    key[i] = '!';
                    break;
                }
                default:{
                    key[i] = textoChar[i];
                }
            }
            if(textoChar[i] == '@'){
                break;
            }
            reg += String.valueOf(key[i]);
        }
        return reg;
    }
    
    public void setKey(String valor) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] valorBytes = valor.getBytes("UTF8");
        this.key = new SecretKeySpec(Arrays.copyOf(valorBytes, keySize), algoritmo);
       
    }

     public String encriptar( String texto ){
        String value="";
        try {
            cif = Cipher.getInstance( algoritmo );             
            cif.init( Cipher.ENCRYPT_MODE, key );             
            byte[] textobytes = texto.getBytes();
            byte[] cipherbytes = cif.doFinal( textobytes );
            value = new BASE64Encoder().encode( cipherbytes );
        } catch (NoSuchAlgorithmException ex) {
            System.err.println( ex.getMessage() );
        } catch (NoSuchPaddingException ex) {
            System.err.println( ex.getMessage() );
        } catch (InvalidKeyException ex) {
            System.err.println( ex.getMessage() );
        } catch (IllegalBlockSizeException ex) {
            System.err.println( ex.getMessage() );
        } catch (BadPaddingException ex) {
            System.err.println( ex.getMessage() );
        }
        return value;
    }

    public String desencriptar( String texto ){
        String str="";        
        try {
            byte[] value = new BASE64Decoder().decodeBuffer(texto);                 
            cif = Cipher.getInstance( algoritmo );            
            cif.init( Cipher.DECRYPT_MODE, key );
            byte[] cipherbytes = cif.doFinal( value );
            str = new String( cipherbytes );                                  
        } catch (InvalidKeyException ex) {
            System.err.println( ex.getMessage() );
        }  catch (IllegalBlockSizeException ex) {
            System.err.println( ex.getMessage() );
        } catch (BadPaddingException ex) {
            System.err.println( ex.getMessage() );            
        }   catch (IOException ex) {
            System.err.println( ex.getMessage() );
        }catch (NoSuchAlgorithmException ex) {
            System.err.println( ex.getMessage() );
        } catch (NoSuchPaddingException ex) {
            System.err.println( ex.getMessage() );
        }
        return str;
    }
    
    public static String toSHA256(String txt){
     String SHA = "";
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(txt.getBytes("UTF-8"));
            byte[] digest = md.digest();
            SHA = new String(digest);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return SHA;
    }

}

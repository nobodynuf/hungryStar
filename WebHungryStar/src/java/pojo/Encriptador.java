/*
 * Here comes the license
 * 
 * Oh snap, waddup
 * 
 * (CC BY-SA 4.0) Jorge Manriquez
 */

package pojo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;

/**
 *
 * @author Jorge Manriquez
 */
public class Encriptador {

    /***
     * Encripta la contraseña solo usando md5,
     * inicialmente iba a encriptarla con salt tambien,
     * pero se tendria que almacenar la salt y eso seria
     * contra producente
     * @param mensaje lo que se encriptara
     * @return hex de md5 de mensaje
     */
    public static String encriptar(String mensaje){
        try {
            // creamos una instancia de un generador de bytes aleatorios
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] sal = new byte[16];
            // llenamos el array de bytes para usarlo
            sr.nextBytes(sal);
            // obtenemos un MessageDiggest que nos ayudara a hacer un hash de 
            // la contraseña
            MessageDigest md = MessageDigest.getInstance("MD5");
            // agregamos los bytes de la sal
            // md.update(sal);
            // obtenemos un mensaje encriptado
            byte[] bytes = md.digest(mensaje.getBytes());
            // lo pasaremos a hexagecimal
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
           Registro.LOG.log(Level.SEVERE, "Al intentar encriptar la contrase\u00f1a se produjo \n un errror {0}", ex.getMessage());
           return null;
        }
        
    }
    public static boolean comprobarIgualdad(String hash, String mensajeNormal){
        return Encriptador.encriptar(mensajeNormal).equals(hash);
    }
    
}

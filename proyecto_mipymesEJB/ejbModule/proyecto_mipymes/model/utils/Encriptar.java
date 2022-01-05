package proyecto_mipymes.model.utils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author msalazar
 *
 */
public class Encriptar {
	private final static String secretKey = "mariosalazar";

	public static String encriptar(String cadena) {

		String encriptacion = "";

		try {

			MessageDigest md5 = MessageDigest.getInstance("MD5");

			byte[] llavePassword = md5.digest(secretKey.getBytes("utf-8"));

			byte[] BytesKey = Arrays.copyOf(llavePassword, 24);

			SecretKey key = new SecretKeySpec(BytesKey, "DESede");

			Cipher cifrado = Cipher.getInstance("DESede");

			cifrado.init(Cipher.ENCRYPT_MODE, key);

			byte[] plainTextBytes = cadena.getBytes("utf-8");

			byte[] buf = cifrado.doFinal(plainTextBytes);

			byte[] base64Bytes = Base64.getEncoder().encode(buf);

			encriptacion = new String(base64Bytes);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return encriptacion;

	}

	public static String descryp(String cadenaEncriptada) {

		String desencriptacion = "";

		try {

			byte[] message = Base64.getDecoder().decode(cadenaEncriptada.getBytes("utf-8"));

			MessageDigest md5 = MessageDigest.getInstance("MD5");

			byte[] digestOfPassword = md5.digest(secretKey.getBytes("utf-8"));

			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

			SecretKey key = new SecretKeySpec(keyBytes, "DESede");

			Cipher decipher = Cipher.getInstance("DESede");

			decipher.init(Cipher.DECRYPT_MODE, key);

			byte[] plainText = decipher.doFinal(message);

			desencriptacion = new String(plainText, "UTF-8");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return desencriptacion;

	}
}

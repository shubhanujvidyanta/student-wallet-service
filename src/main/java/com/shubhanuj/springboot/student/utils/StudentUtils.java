/**
 * 
 */
package com.shubhanuj.springboot.student.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;

import com.shubhanuj.springboot.student.constants.WalletConstants;
import com.shubhanuj.springboot.student.exception.EncryptionException;
import com.shubhanuj.springboot.student.repository.StudentRepository;
import com.sun.xml.messaging.saaj.packaging.mime.util.BASE64EncoderStream;

/**
 * @author Shubhanuj
 *
 */
public class StudentUtils {

	private static Cipher ecipher;
	private static Cipher dcipher;
	private static SecretKey key;
	
	@Autowired
	static StudentRepository studentRepository;

	static {
		try {
			key = KeyGenerator.getInstance("DES").generateKey();

			ecipher = Cipher.getInstance("DES");
			dcipher = Cipher.getInstance("DES");

			// initialize the ciphers with the given key
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			dcipher.init(Cipher.DECRYPT_MODE, key);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
	}

	public static String encryptPassword(String plainStr) throws EncryptionException {

		try {
			// encode the string into a sequence of bytes using the named charset
			// storing the result into a new byte array.
			byte[] utf8 = plainStr.getBytes("UTF8");
			byte[] enc = ecipher.doFinal(utf8);

			// encode to base64
			enc = BASE64EncoderStream.encode(enc);

			return new String(enc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public static String getCurrencyForCountry(String country) {
		
		for(WalletConstants.WALLET_AVAILABLE_CURRENCY currency: WalletConstants.WALLET_AVAILABLE_CURRENCY.values()) {
			if(currency.toString().equals(country)) {
				return currency.getCURRENCY();
			}
				
		}
		
		return null;

	}
	

}

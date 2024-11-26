package Query_Selector;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {


    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(password.getBytes());
            
           
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString().toLowerCase(); 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    
	}
}


package librarybookborrowing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PasswordHash {
    
    public static String hashPassword(String password) {
        try {
            // Create SHA-256 digest
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());

            // Convert bytes to hex
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }   
}

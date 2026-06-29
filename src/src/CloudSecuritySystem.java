import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CloudSecuritySystem {

    /**
     * Constants used throughout the application.
     */
    static class Constants {
        public static final String ADMIN = "Admin";
        public static final String USER = "User";
        public static final String AES = "AES";
        public static final int PASSWORD_LENGTH = 8;
    }

    private static Logger logger = Logger.getLogger("SecurityLogger");
    private static SecretKey secretKey;

    /**
     * Initializes logger and AES key.
     */
    static {
        try {
            FileHandler fileHandler = new FileHandler("security.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            KeyGenerator generator = KeyGenerator.getInstance(Constants.AES);
            generator.init(128, new SecureRandom());
            secretKey = generator.generateKey();

        } catch (Exception e) {
            System.out.println("Initialization Error: " + e.getMessage());
        }
    }

    /**
     * Validates password strength.
     */
    public static boolean validatePassword(String password) {

        if (password.length() < Constants.PASSWORD_LENGTH)
            return false;

        boolean upper = false;
        boolean lower = false;
        boolean digit = false;
        boolean special = false;

        for (char c : password.toCharArray()) {

            if (Character.isUpperCase(c))
                upper = true;
            else if (Character.isLowerCase(c))
                lower = true;
            else if (Character.isDigit(c))
                digit = true;
            else
                special = true;
        }

        return upper && lower && digit && special;
    }

    /**
     * Simple authentication.
     */
    public static boolean authenticate(String username, String password) {

        if (username.equals("admin") && password.equals("Admin@123")) {
            logger.info("Authentication Successful");
            return true;
        }

        logger.warning("Authentication Failed");
        return false;
    }

    /**
     * Checks role permissions.
     */
    public static void checkAccess(String role) {

        if (role.equals(Constants.ADMIN)) {
            System.out.println("Full Cloud Access Granted");
            logger.info("Admin Access Granted");
        }

        else if (role.equals(Constants.USER)) {
            System.out.println("Limited Access Granted");
            logger.info("User Access Granted");
        }

        else {
            System.out.println("Access Denied");
            logger.warning("Unknown Role");
        }

    }

    /**
     * Encrypts data using AES.
     */
    public static String encrypt(String data) {

        try {

            Cipher cipher = Cipher.getInstance(Constants.AES);

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encrypted = cipher.doFinal(data.getBytes());

            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {

            logger.warning("Encryption Error");

            return null;

        }

    }

    /**
     * Decrypts AES encrypted text.
     */
    public static String decrypt(String encryptedData) {

        try {

            Cipher cipher = Cipher.getInstance(Constants.AES);

            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decoded = Base64.getDecoder().decode(encryptedData);

            return new String(cipher.doFinal(decoded));

        } catch (Exception e) {

            logger.warning("Decryption Error");

            return null;

        }

    }

    /**
     * Logs system events.
     */
    public static void logActivity(String message) {

        logger.info(message);

    }

    /**
     * Displays password requirements.
     */
    public static void showPasswordRules() {

        System.out.println("\nPassword Rules");
        System.out.println("------------------------");
        System.out.println("Minimum 8 characters");
        System.out.println("At least one uppercase letter");
        System.out.println("At least one lowercase letter");
        System.out.println("At least one digit");
        System.out.println("At least one special character");
        System.out.println();

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        showPasswordRules();

        System.out.print("Username : ");
        String username = sc.nextLine();

        System.out.print("Password : ");
        String password = sc.nextLine();

        if (!validatePassword(password)) {

            System.out.println("\nWeak Password");
            logActivity("Weak Password Entered");
            return;

        }

        if (!authenticate(username, password)) {

            System.out.println("\nAuthentication Failed");
            return;

        }

        System.out.print("Enter Role (Admin/User): ");

        String role = sc.nextLine();

        checkAccess(role);

        System.out.print("\nEnter Sensitive Cloud Data: ");

        String data = sc.nextLine();

        String encrypted = encrypt(data);

        System.out.println("\nEncrypted Data:");
        System.out.println(encrypted);

        String decrypted = decrypt(encrypted);

        System.out.println("\nDecrypted Data:");
        System.out.println(decrypted);

        logActivity("Encryption and Decryption Completed");

        System.out.println("\nCloud Security Workflow Completed Successfully");

        sc.close();

    }

}

import java.util.*;
import java.util.logging.*;
import java.io.IOException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * ===========================================================
 *              CLOUD SECURITY SYSTEM
 * -----------------------------------------------------------
 * Features:
 * ✔ Multi User Authentication
 * ✔ Strong Password Validation
 * ✔ AES Encryption & Decryption
 * ✔ Role Based Access Control
 * ✔ Security Logging
 * ✔ DRY Principle
 * ✔ Exception Handling
 * ✔ JavaDoc Documentation
 * ===========================================================
 */

public class CloudSecuritySystem {

    /*=========================================================
                    CONSTANTS
    =========================================================*/

    static class Constants {

        public static final String ADMIN = "ADMIN";
        public static final String USER = "USER";

        public static final String AES = "AES";

        public static final int PASSWORD_LENGTH = 8;

    }

    /*=========================================================
                    USER CLASS
    =========================================================*/

    static class User {

        private String username;
        private String password;
        private String role;

        public User(String username,
                    String password,
                    String role){

            this.username=username;
            this.password=password;
            this.role=role;

        }

        public String getUsername(){

            return username;

        }

        public String getPassword(){

            return password;

        }

        public String getRole(){

            return role;

        }

        public void setPassword(String password){

            this.password=password;

        }

    }

    /*=========================================================
                    USER MANAGER
    =========================================================*/

    static class UserManager{

        private HashMap<String,User> users;

        public UserManager(){

            users=new HashMap<>();

        }

        /**
         * Register new user
         */

        public boolean registerUser(String username,
                                    String password,
                                    String role){

            if(users.containsKey(username))
                return false;

            users.put(username,
                    new User(username,password,role));

            return true;

        }

        /**
         * Authenticate user
         */

        public User authenticate(String username,
                                 String password){

            User user=users.get(username);

            if(user==null)
                return null;

            if(user.getPassword().equals(password))
                return user;

            return null;

        }

        /**
         * Check existing user
         */

        public boolean containsUser(String username){

            return users.containsKey(username);

        }

        /**
         * Display all users
         */

        public void displayUsers(){

            System.out.println("\nRegistered Users\n");

            for(User u:users.values()){

                System.out.println(
                        u.getUsername()
                                +"  ->  "
                                +u.getRole());

            }

        }

    }

    /*=========================================================
                PASSWORD VALIDATOR
    =========================================================*/

    static class PasswordValidator{

        /**
         * Validate Password
         */

        public static boolean validate(String password){

            if(password.length()<Constants.PASSWORD_LENGTH)
                return false;

            boolean upper=false;
            boolean lower=false;
            boolean digit=false;
            boolean special=false;

            for(char ch:password.toCharArray()){

                if(Character.isUpperCase(ch))
                    upper=true;

                else if(Character.isLowerCase(ch))
                    lower=true;

                else if(Character.isDigit(ch))
                    digit=true;

                else
                    special=true;

            }

            return upper&&lower&&digit&&special;

        }

        /**
         * Display Rules
         */

        public static void rules(){

            System.out.println("\nPASSWORD REQUIREMENTS");

            System.out.println("----------------------------");

            System.out.println("Minimum 8 characters");

            System.out.println("At least one uppercase");

            System.out.println("At least one lowercase");

            System.out.println("At least one digit");

            System.out.println("At least one special character");

            System.out.println();

        }

    }

    /*=========================================================
                LOGGER
    =========================================================*/

    static class SecurityLogger{

        private Logger logger;

        public SecurityLogger(){

            logger=Logger.getLogger("CloudSecurityLogger");

            try{

                FileHandler handler=
                        new FileHandler("security.log",true);

                handler.setFormatter(new SimpleFormatter());

                logger.addHandler(handler);

            }

            catch(IOException e){

                System.out.println("Logger Error");

            }

        }

        public void info(String message){

            logger.info(message);

        }

        public void warning(String message){

            logger.warning(message);

        }

    }

    /*=========================================================
                    AES ENCRYPTION
    =========================================================*/

    static class EncryptionService{

        private SecretKey secretKey;

        public EncryptionService(){

            try{

                KeyGenerator generator=
                        KeyGenerator.getInstance(Constants.AES);

                generator.init(128,new SecureRandom());

                secretKey=generator.generateKey();

            }

            catch(Exception e){

                System.out.println("Key Generation Failed");

            }

        }

        /**
         * Encrypt Data
         */

        public String encrypt(String text){

            try{

                Cipher cipher=
                        Cipher.getInstance(Constants.AES);

                cipher.init(Cipher.ENCRYPT_MODE,
                        secretKey);

                byte[] encrypted=
                        cipher.doFinal(text.getBytes());

                return Base64.getEncoder()
                        .encodeToString(encrypted);

            }

            catch(Exception e){

                return null;

            }

        }

        /**
         * Decrypt Data
         */

        public String decrypt(String encrypted){

            try{

                Cipher cipher=
                        Cipher.getInstance(Constants.AES);

                cipher.init(Cipher.DECRYPT_MODE,
                        secretKey);

                byte[] decoded=
                        Base64.getDecoder()
                                .decode(encrypted);

                return new String(
                        cipher.doFinal(decoded));

            }

            catch(Exception e){

                return null;

            }

        }

    }

    // -------- Part 2 starts from here in the next response --------

    /*=========================================================
                    ACCESS CONTROL
    =========================================================*/

    static class AccessControl {

        /**
         * Check Role Based Access
         */

        public void grantAccess(User user) {

            if (user.getRole().equals(Constants.ADMIN)) {

                System.out.println("\n==================================");
                System.out.println("ADMIN ACCESS GRANTED");
                System.out.println("Privileges:");
                System.out.println("- User Management");
                System.out.println("- Cloud Configuration");
                System.out.println("- Security Monitoring");
                System.out.println("- Encryption Service");
                System.out.println("==================================");

            }

            else {

                System.out.println("\n==================================");
                System.out.println("USER ACCESS GRANTED");
                System.out.println("Privileges:");
                System.out.println("- View Cloud Data");
                System.out.println("- Encrypt Files");
                System.out.println("==================================");

            }

        }

    }

    /*=========================================================
                    GLOBAL OBJECTS
    =========================================================*/

    static Scanner scanner = new Scanner(System.in);

    static UserManager userManager =
            new UserManager();

    static EncryptionService encryptionService =
            new EncryptionService();

    static SecurityLogger securityLogger =
            new SecurityLogger();

    static AccessControl accessControl =
            new AccessControl();

    /*=========================================================
                    DEFAULT USERS
    =========================================================*/

    public static void initializeUsers() {

        userManager.registerUser(
                "admin",
                "Admin@123",
                Constants.ADMIN);

        userManager.registerUser(
                "john",
                "John@123",
                Constants.USER);

        userManager.registerUser(
                "alice",
                "Alice@123",
                Constants.USER);

    }

    /*=========================================================
                    REGISTER USER
    =========================================================*/

    public static void registerUser() {

        PasswordValidator.rules();

        System.out.print("Enter Username : ");
        String username = scanner.nextLine();

        if (userManager.containsUser(username)) {

            System.out.println("User Already Exists");
            return;

        }

        System.out.print("Enter Password : ");
        String password = scanner.nextLine();

        if (!PasswordValidator.validate(password)) {

            System.out.println("Weak Password");
            return;

        }

        System.out.print("Role (ADMIN/USER) : ");
        String role = scanner.nextLine().toUpperCase();

        if (!role.equals(Constants.ADMIN)
                && !role.equals(Constants.USER)) {

            System.out.println("Invalid Role");
            return;

        }

        boolean status =
                userManager.registerUser(
                        username,
                        password,
                        role);

        if (status) {

            System.out.println("Registration Successful");

            securityLogger.info(
                    "New User Registered : "
                            + username);

        }

    }

    /*=========================================================
                    LOGIN
    =========================================================*/

    public static User login() {

        System.out.print("\nUsername : ");

        String username =
                scanner.nextLine();

        System.out.print("Password : ");

        String password =
                scanner.nextLine();

        User user =
                userManager.authenticate(
                        username,
                        password);

        if (user == null) {

            System.out.println("Invalid Credentials");

            securityLogger.warning(
                    "Failed Login : "
                            + username);

            return null;

        }

        securityLogger.info(
                "Successful Login : "
                        + username);

        return user;

    }

    /*=========================================================
                    ENCRYPT DATA
    =========================================================*/

    public static void encryptData() {

        System.out.print(
                "\nEnter Plain Text : ");

        String text =
                scanner.nextLine();

        String encrypted =
                encryptionService.encrypt(text);

        System.out.println(
                "\nEncrypted Text");

        System.out.println(
                encrypted);

        String decrypted =
                encryptionService.decrypt(encrypted);

        System.out.println(
                "\nDecrypted Text");

        System.out.println(
                decrypted);

        securityLogger.info(
                "Encryption Completed");

    }

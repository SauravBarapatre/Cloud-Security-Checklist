import java.util.*;
import java.util.Base64;

public class CloudSecuritySystem {

    static boolean validatePassword(String password) {

        boolean upper = false;
        boolean lower = false;
        boolean digit = false;
        boolean special = false;

        for(char ch : password.toCharArray()) {

            if(Character.isUpperCase(ch))
                upper = true;

            else if(Character.isLowerCase(ch))
                lower = true;

            else if(Character.isDigit(ch))
                digit = true;

            else
                special = true;
        }

        return password.length() >= 8 &&
                upper &&
                lower &&
                digit &&
                special;
    }

    static String getAccess(String role) {

        if(role.equalsIgnoreCase("admin"))
            return "Full Access";

        if(role.equalsIgnoreCase("employee"))
            return "Limited Access";

        return "Access Denied";
    }

    static String encrypt(String data) {

        return Base64.getEncoder()
                .encodeToString(data.getBytes());
    }

    static void logEvent(String event) {

        System.out.println("[LOG] " + event);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Cloud Security System");

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        if(!validatePassword(password)) {

            logEvent("Weak password attempt by " + username);

            System.out.println("Authentication Failed");

            return;
        }

        logEvent("Password validation successful");

        System.out.print("Role(admin/employee): ");
        String role = sc.nextLine();

        String access = getAccess(role);

        System.out.println("Access Level: " + access);

        if(access.equals("Access Denied")) {

            logEvent("Unauthorized access attempt");

            return;
        }

        System.out.print("Enter sensitive data: ");

        String data = sc.nextLine();

        String encrypted = encrypt(data);

        System.out.println("Encrypted Data: " + encrypted);

        logEvent("Data encrypted successfully");

        System.out.println("Security Check Completed");
    }
}

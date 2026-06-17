import java.util.Scanner;

public class PasswordChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String password = sc.nextLine();

        boolean upper = false;
        boolean lower = false;
        boolean digit = false;

        for(char ch : password.toCharArray()){
            if(Character.isUpperCase(ch)) upper = true;
            if(Character.isLowerCase(ch)) lower = true;
            if(Character.isDigit(ch)) digit = true;
        }

        if(password.length() >= 8 && upper && lower && digit)
            System.out.println("Strong Password");
        else
            System.out.println("Weak Password");
    }
}

import java.util.Scanner;

public class AccessControl {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String role = sc.nextLine();

        if(role.equalsIgnoreCase("admin"))
            System.out.println("Full Access");
        else if(role.equalsIgnoreCase("employee"))
            System.out.println("Limited Access");
        else
            System.out.println("Access Denied");
    }
}

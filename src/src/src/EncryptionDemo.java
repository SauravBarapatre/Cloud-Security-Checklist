import java.util.Base64;
import java.util.Scanner;

public class EncryptionDemo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String data = sc.nextLine();

        String encoded =
                Base64.getEncoder().encodeToString(data.getBytes());

        System.out.println("Encrypted Data: " + encoded);
    }
}

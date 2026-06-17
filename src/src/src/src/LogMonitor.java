public class LogMonitor {

    public static void main(String[] args) {

        String[] logs = {
                "User Login",
                "Failed Login",
                "Password Change",
                "Failed Login"
        };

        for(String log : logs) {

            if(log.contains("Failed")) {
                System.out.println("Security Alert: " + log);
            }
        }
    }
}

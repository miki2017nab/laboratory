package LABA5;
import java.util.Scanner;
public class Client {
    public static boolean mark = true;
    public void start() {
     try (Scanner scanner = new Scanner(System.in)) {
            javax.ws.rs.client.Client client = javax.ws.rs.client.ClientBuilder.newClient();
            Handler h1 = new Handler(client);
            System.out.println("Welcome to server");            
            while (mark) {
                h1.interpretator(scanner.nextLine().trim());
            }
        } catch(Exception ex){
            System.out.println("Connections problem");
        }
    }
}

package LABA4;
import java.util.Scanner;
import lpi.server.soap.ChatServer;
import lpi.server.soap.IChatServer;
public class Client {
    public static boolean mark = true;
    public void begin() {
        try (Scanner scanner = new Scanner(System.in)) {
            ChatServer serverWrapper = new ChatServer();
            IChatServer serverProxy = serverWrapper.getChatServerProxy();
            System.out.println("Welcome to server");
            Handler h1 = new Handler(serverProxy);
            while (mark) {
                String inLine = scanner.nextLine().trim();
                if (!inLine.equals("")) {
                    h1.interpretator(inLine);
                }
            }
        } catch(Exception ex){
            System.out.println("Connections problem");
        }
    }
}

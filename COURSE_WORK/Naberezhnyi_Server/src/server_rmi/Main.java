package server_rmi;

import java.io.IOException;
import java.util.Scanner;

import static server_rmi.Compute.USE_PORT;

public class Main{

    public static void main(String[] args) throws IOException {

        Server server = new Server(USE_PORT);
        server.runServer();

        System.out.println("Server starting");


        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        server.close();

        System.out.println("Server is closed");
    }
}

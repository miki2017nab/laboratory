package LABA3;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import SERVER.Server;
public class Client {
    public static boolean mark = true;
    public void start(int port) {
        try (Scanner scanner = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry(port);
            Server proxy = (Server) registry.lookup(Server.RMI_SERVER_NAME);
            System.out.println("Welcome to server");
            Handler inter = new Handler(proxy);
                    while (mark) {
                String inLine = scanner.nextLine().trim();
                    if (!inLine.equals("")) {
                    inter.interpretator(inLine);
                }
            }
        } catch (RemoteException | NotBoundException ex) {
            System.out.println("Problem conections");
        }
    }
	}

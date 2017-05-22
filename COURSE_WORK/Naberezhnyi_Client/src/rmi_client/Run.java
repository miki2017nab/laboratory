package rmi_client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import server_rmi.Compute;

public class Run {

    private ChoseCommand choseCommand;
    private Registry registry;
    private Compute remoteCompute;

    public static boolean workingFlag = true;

    public Run() throws Exception {
        securityManager();
        createConnections();
    }

    private void securityManager() {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
    }

    private void createConnections() throws RemoteException, NotBoundException {
        registry = LocateRegistry.getRegistry(Compute.USE_PORT);
        remoteCompute = (Compute) registry.lookup(Compute.USE_SERVER_NAME);
        choseCommand = new ChoseCommand(remoteCompute);
    }

    public void doCommand() {

        System.out.println("You are connected to rmi.com server");

        try(Scanner scan = new Scanner(System.in)) {
            while (workingFlag)
                choseCommand.interpretation(scan.nextLine());
        }
    }
}

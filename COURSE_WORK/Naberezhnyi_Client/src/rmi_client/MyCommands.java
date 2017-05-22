package rmi_client;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;

import server_rmi.Compute;
import server_rmi.Compute.FileInfo;

public class MyCommands {

    private final Compute remoteCompute;
    private static final int MAX_ELEMENT_VALUE = 1000000000;
    private static final int SIZE = 1000;


    public MyCommands(Compute compute) {
        this.remoteCompute = compute;
    }

    public void exit() throws RemoteException {
        System.out.println("You are exit from server");
        Run.workingFlag = false;
    }

    public void ping() throws RemoteException {
        System.out.println(remoteCompute.ping());
    }

    public void echo(String[] parsedParameters) throws RemoteException {
        if(parsedParameters.length == 2)
        System.out.println(remoteCompute.echo(parsedParameters[1]));
    }

    public void sort(String[] parsedParameters) throws IOException {

        if(parsedParameters.length == 2) {

            String[] valueInStringMass = createMass();
            toServer(valueInStringMass, parsedParameters);

        }else{
            System.out.println("Bad argument");
        }
    }

    private String[] createMass(){
        String[] temple = new String[SIZE];

        for (int i = 0; i < temple.length; i++)
            temple[i] = String.valueOf((int) (Math.random() * MAX_ELEMENT_VALUE));

        return temple;
    }

    private void toServer(String[] valueInStringMass, String[] parsedParameters) throws IOException {

        File file = writeToNewFile(parsedParameters[1], valueInStringMass);
        FileInfo newFileInfo = new FileInfo(file);

        FileInfo fileInfo = remoteCompute.executeTask(new Compute.SortFile(newFileInfo));
        System.out.println("Received a reply");

        writeToNewFile(fileInfo.getFilename(), new String(fileInfo.getFileContent(), StandardCharsets.UTF_8).split(" "));
    }

    private File writeToNewFile(String nameFile, String[] wrathedMass) {

        File file = new File(nameFile);
        try (DataOutputStream newStream = new DataOutputStream(new FileOutputStream(file))) {

            for (int i = 0; i < wrathedMass.length; i++){
                newStream.writeBytes(wrathedMass[i] + " ");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return file;
    }
}

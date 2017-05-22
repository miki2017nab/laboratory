package server_rmi;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.Instant;

public interface Compute extends Remote {

    public static final int USE_PORT = 18666;
    public static final String USE_SERVER_NAME = "rmi.com";

    public String ping() throws RemoteException;

    public String echo(String text) throws RemoteException;

    public <T> T executeTask(Task<T> t) throws IOException, RemoteException;

    public class SortFile implements Task<Compute.FileInfo>, Serializable {

        private static final long serialVersionUID = 227L;

        private final Compute.FileInfo fileInfo;

        public SortFile(FileInfo fileInfo) throws IOException {
            this.fileInfo = fileInfo;
        }

        @Override
        public Compute.FileInfo execute() throws IOException {

            String values = new String(fileInfo.getFileContent(), StandardCharsets.UTF_8).trim();

            String[] valuesToSort;
            if(values.equals("")){
                valuesToSort = new String[]{};
            }else {
                valuesToSort = values.split(" ");
            }

            int[] intValues;
            if(valuesToSort.length == 0){
                intValues = new int[]{};
            } else {
                intValues = new int[valuesToSort.length];

                for (int i = 0; i < valuesToSort.length; i++) {
                    intValues[i] = Integer.parseInt(valuesToSort[i]);
                }
            }

            int[] sorted = sort(intValues);

            File file = new File("response_to_" + fileInfo.getFilename());

            if (intValues.length != 0) {
                try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {

                    for (int i = 0; i < sorted.length; i++)
                        dos.writeBytes(sorted[i] + " ");

                } catch (Exception ex) {
                    System.out.println("Problem with write file");
                }
            }
            return new Compute.FileInfo(file);
        }

        private  int[] sort(int[] sortedMas){

            System.out.println("Begin sort");
            int firstTime = (int) (Instant.now().getEpochSecond());

            for (int i = 1; i < sortedMas.length; i++) {

                int index = i;
                int value = sortedMas[i];

                while (index > 0 && sortedMas[index - 1] > value) {
                    sortedMas[index] = sortedMas[index - 1];
                    index--;
                }
                sortedMas[index] = value;
            }

            int secondTime = (int) (Instant.now().getEpochSecond() - firstTime);
            System.out.println("It took " + secondTime + " seconds");

            return sortedMas;
        }
    }

    public static class FileInfo implements Serializable {

        private static final long serialVersionUID = 229L;
        private byte[] fileContent;
        private String filename;

        public FileInfo(File file) throws IOException {
            fileContent = Files.readAllBytes(file.toPath());
            filename = file.getName();
        }

        public String getFilename() {
            return filename;
        }

        public byte[] getFileContent() {
            return fileContent;
        }
    }
}

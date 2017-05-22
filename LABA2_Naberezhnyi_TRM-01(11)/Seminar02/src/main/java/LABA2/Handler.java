package LABA2;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class Handler {
    public static String sendComand = null;
    CommandsToServer messageToS = new CommandsToServer();

    public byte[] interpretator(String inLine) throws IOException {

        String[] comandMas = parsForComand(inLine);

        switch (comandMas[0]) {
            case "ping":
                return messageToS.pingToServer(comandMas[0]);

            case "eho":
                return messageToS.ehoToServer(comandMas);

            case "login":
                return messageToS.loginToServer(comandMas);

            case "list":
                return messageToS.listToServer(comandMas);

            case "msg":
                return messageToS.msgToServer(comandMas);

            case "file":
                return messageToS.fileToServer(comandMas);

            case "receivemsg":
                return messageToS.receiveMsgToServer();

            case "receivefile":
                return messageToS.receiveFileToServer();

            default:
                System.out.println("No this comand");
                return null;
        }
    }

    CommandsFromServer messageFromS = new CommandsFromServer();

    public void interpretation(byte[] serverResp) {
        if (serverResp != null) {
            try {
                switch (sendComand) {
                    case "ping":
                        messageFromS.pingFromServer(serverResp);
                        break;
                    case "eho":
                        messageFromS.ehoFromServer(serverResp);
                        break;
                    case "login":
                        messageFromS.loginFromServer(serverResp);
                        break;
                    case "list":
                        messageFromS.listFromServer(serverResp);
                        break;
                    case "msg":
                        messageFromS.msgFromServer(serverResp);
                        break;
                    case "file":
                        messageFromS.fileFromServer(serverResp);
                        break;
                    case "receivemsg":
                        messageFromS.receiveMsg(serverResp);
                        break;
                    case "receivefile":
                        messageFromS.receiveFile(serverResp);
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("problem with interpretation responds");
            }
        }
    }

    public static byte[] serialize(Object object) throws IOException {
        try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                ObjectOutputStream objectStream = new ObjectOutputStream(byteStream)) {
            objectStream.writeObject(object);
            return byteStream.toByteArray();
        }
    }

    public static <T> T deserialize(byte[] data, int offset, Class<T> clazz) throws ClassNotFoundException, IOException {
        try (ByteArrayInputStream stream = new ByteArrayInputStream(data, offset, data.length - offset);
                ObjectInputStream objectStream = new ObjectInputStream(stream)) {
            return (T) objectStream.readObject();
        }
    }

    private String[] parsForComand(String line) {
        String[] outMas = null;
        String[] parsMas = line.split(" ", 2);

        switch (parsMas[0]) {
            case "eho":
                outMas = line.split(" ", 2); // comand _ anyText                  
                break;

            case "msg":
                outMas = line.split(" ", 3); // comand _ destination _ messegeText 
                break;

            default:
                outMas = line.split(" ");
                break;
        }
        return outMas;
    }
}

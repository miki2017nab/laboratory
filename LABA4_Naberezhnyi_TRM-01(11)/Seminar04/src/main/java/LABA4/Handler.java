package LABA4;
import java.io.IOException;
import lpi.server.soap.ArgumentFault;
import lpi.server.soap.IChatServer;
import lpi.server.soap.LoginFault;
import lpi.server.soap.ServerFault;
public class Handler {
    private final MessageProc comP;
    private final Analyzer an = new Analyzer();
    public Handler(IChatServer ob) {
        comP = new MessageProc(ob);
    }
    public void interpretator(String inLine){
        String[] comandMas = an.parsForComand(inLine);
        try {
            switch (comandMas[0]) {
                case "ping":
                    comP.ping();
                    break;
                case "echo":
                    comP.echo(comandMas);
                    break;
                case "login":
                    comP.login(comandMas);
                    break;
                case "list":
                    comP.list();
                    break;
                case "message":
                    comP.msg(comandMas);
                    break;
                case "file":
                    comP.file(comandMas);
                    break;
                case "receivemsg":
                    comP.receiveMsg();
                    break;
                case "receivefile":
                    comP.receiveFile();
                    break;
                case "exit":
                    comP.exit();
                    break;
                default:
                    System.out.println("No this comand");
                    break;
           }
        } catch (ArgumentFault | ServerFault | LoginFault | IOException ex) {
            System.out.println("Interpretator problem");
        }
    }
}

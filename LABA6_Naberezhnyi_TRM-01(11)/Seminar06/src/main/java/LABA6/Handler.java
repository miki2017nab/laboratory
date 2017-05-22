package LABA6;

import java.util.List;
import javax.jms.Session;

public class Handler {

    private final CommandManage comandProcess;
    private final Analyzer an = new Analyzer();

    public Handler(List<Session> sessions) {
        comandProcess = new CommandManage(sessions);
    }

    public void interpretator(String inLine) {

        String[] comandMas =an.parsForComand(inLine);
        String command = comandMas[0];

        switch (command) {

            case "ping":
                comandProcess.ping();
                break;

            case "echo":
                comandProcess.echo(comandMas);
                break;

            case "login":
                comandProcess.login(comandMas);
                break;

            case "list":
                comandProcess.list();
                break;

            case "message":
                comandProcess.msg(comandMas);
                break;

            case "file":
                comandProcess.file(comandMas);
                break;

            case "exit":
                comandProcess.exit();
                break;

            default:
                System.out.println("No this comand");
                break;
        }
    }
}

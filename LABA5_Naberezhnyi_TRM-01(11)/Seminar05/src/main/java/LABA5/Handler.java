package LABA5;
public class Handler {
    private final CommandManage comP;
    private final Analyzer an = new Analyzer();
    public Handler(javax.ws.rs.client.Client client) {
        comP = new CommandManage(client);
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
                case "receivemessage":
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
        } catch (Exception ex) {
            System.out.println("error");
        }
    }
}

package LABA3;
import SERVER.Server;
public class Handler {
    private final MesImplement comP;
    private final Analyzer parser = new Analyzer();
    public Handler(Server ob)
    {
         comP = new MesImplement(ob);
    }
       public void interpretator(String inLine){
        String[] comandMas = parser.parsForComand(inLine);
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
            System.out.println("Interpretator problem");
            ex.printStackTrace();
        }
    }
}

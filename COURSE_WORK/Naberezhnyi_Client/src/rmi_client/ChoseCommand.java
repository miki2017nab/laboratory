package rmi_client;

import server_rmi.Compute;

public class ChoseCommand {
    private final MyCommands myCommands;
    
    public ChoseCommand(Compute remoteCompute) {
        myCommands = new MyCommands(remoteCompute);
    }


    public void interpretation(String inLine) {

        try {
            String[] parameters = pars(inLine);

            switch (parameters[0]) {

                case "ping":
                    myCommands.ping();
                    break;

                case "echo":
                    myCommands.echo(parameters);
                    break;

                case "sort":
                    myCommands.sort(parameters);
                    break;

                case "exit":
                    myCommands.exit();
                    break;

                default:
                    System.out.println("No this command, please enter correct");
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String[] pars(String in){
        String[] parsed = in.split(" ", 2);

        if(parsed[0].equals("echo"))
            return parsed;
        else
            return in.split(" ");
    }
}

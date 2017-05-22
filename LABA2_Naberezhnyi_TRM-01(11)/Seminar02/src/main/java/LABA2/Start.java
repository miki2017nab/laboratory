package LABA2;
public class Start {
    public static void main(String[] args)
    {
        Client client = new Client();
        client.begin("localhost",8745);
    }
}
package LABA6;

public class Analyzer {

    public String[] parsForComand(String line) {
        String[] parsMas = line.split(" ", 2);

        switch (parsMas[0]) {
            // comand _ anyText 
            case "echo":
                return parsMas;

            // comand _ destination _ messegeText     
            case "message":
                return line.split(" ", 3);                 

            default:
                return line.split(" ");                
        }        
    }
}

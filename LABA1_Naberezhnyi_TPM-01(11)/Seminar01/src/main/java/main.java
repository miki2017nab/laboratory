import java.util.Scanner;

public class main {
	public static boolean mark = true;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Handler input = new Handler();
		while(mark){
			System.out.print("\nPlease enter text:\n//enter stop to exit//\n");
			if(scanner.hasNextLine()){				
				input.operate(scanner.nextLine());
			}			
		}
		scanner.close();
	}
}

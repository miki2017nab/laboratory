
public class Handler {
	
	public void operate (String line){
			if(line.equals("stop")){
			main.mark = false;
		} else if (!line.equals("")){
			String [] mas = line.split(" ");
			for (int i = 0; i < mas.length; i++){
				if (i == 0){
					System.out.println("Entered text //" + mas[i] + "//");
				} else {
					if(i == 1){
						System.out.print(" and options");
					}
					System.out.print("//" + mas[i] + "//");
				}
			}		
		}
	}
}

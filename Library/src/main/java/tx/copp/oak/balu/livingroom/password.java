package tx.copp.oak.balu.livingroom;

import java.util.Scanner;

public class password {
	
	
	public static void doLoop() {
		Scanner scanner = new Scanner(System.in);
		int remainingattempts = 4;
		System.out.println("please enter password");
		while (true) {
			String password = scanner.nextLine();
			if (password.equals("zztop")) {
				System.out.println("you stole my diamantes");
				break;
			} else if (remainingattempts == 0) {
				System.err.println("Too many failed attempts. Account locked.");
				break;
			} else {
				remainingattempts--;
				System.err.println("Please try again. " + remainingattempts + " remaining attempts left.");
				
			}

			}
		}


	public static void main(String[] args) {
		password.doLoop();
	}
}

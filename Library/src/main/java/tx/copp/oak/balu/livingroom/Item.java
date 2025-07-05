package tx.copp.oak.balu.livingroom;

import java.util.Scanner;

public class Item {

	public static void main(String[] args) {
		
		int num = 0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter a number: ");
		num = scanner.nextInt();
		
		if (num > 18) { // if a condition is met, do this.
			System.out.println(num + " is greater than 18");
		} else if (num == 18){ 
			/* == means =
			 * if the condition is not met, then do this.
			 * else if can be written as many times, but end with an else.
			 * ex. if, else if, else if, else.
			 */
			System.out.println(num + " is 18.");
		} else if (num < 18 && num >= 12) {
			/*&& = and
			 * 2 conditions need to be met
			 */
			System.out.println(num + " is lucky.");
		} else if (num < 12 && num > 6) {	
			System.out.println(num + " is fun.");
		} else {
			System.out.println(num + " bad selection."); 
		}
		
		while (num > 100) {
			System.out.println("I am sorry I messed up!" + num);
			num = num +1;
			
			if(num == 100000) {
				System.out.println("exiting" + num);
				System.exit(1);
			}
		}
	}
}

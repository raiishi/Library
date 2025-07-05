package tx.copp.oak.balu.livingroom;

import java.util.Random;

public class Counter {
	
	public int addNum(int num1, int num2) {
		
		System.out.println("Adding Numbers " + num1 + " and " + num2);
		int temp = 0; //temporary number
		temp = num1 + num2;
		return temp;
		
		
	}
		
	public static void main(String[] args) {

		
		int counter = 1; //counter starts at 200
		
		Counter bk = new Counter();
		
		int test = bk.addNum(15, 10); //to store output to 
		System.out.println("The answer is " + test);
		
		Random random = new Random(); //generating a random number
		int numb = 0;
		
		while(counter > 0) {
			numb = random.nextInt(1000);
			System.out.println("Rand: " + numb);
			
		//	System.out.println("please wait" + counter + " time left.");
			counter -- ; //decrementing the counter. shorthand for counter = counter-1
			if (counter == 92) {
					System.out.println("Sorry I am breaking " + counter);
					break;
			}
		}
		System.out.println("kaboom there goes your tower watch it crumble feel the power");
	}

}

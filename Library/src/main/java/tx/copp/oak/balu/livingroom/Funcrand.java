
package tx.copp.oak.balu.livingroom;

import java.util.Random;

public class Funcrand {


	public int addNum() {
		Random r2 = new Random();
		int num1 = 0;
		num1 = r2.nextInt(1000);
		int num2 = 0;
		num2 = r2.nextInt(1000);
		
		System.out.println("Adding the numbers " + num1 + " and " + num2);
		int temp = 0;
		temp = num1 + num2;
		return temp;
	}
		
		public int multNum() {
			Random random = new Random();
			int num3 = 0;
			num3 = random.nextInt(1000);
			int num4 = 0;
			num4 = random.nextInt(1000);
			
			System.out.println("Multiplying the numbers " + num3 + " and " + num4);
			int temp = 0;
			temp = num3 * num4;
			return temp;	
	}
		
		public double divNum() {
			Random r3 = new Random();
			int num5 = 0;
			num5 = r3.nextInt(1000);
			int num6 = 0;
			num6 = r3.nextInt(1000);
			
			System.out.println("Dividing the numbers " + num5 + " and " + num6);
			double temp = (double) num5 / num6;
			return temp;
}
		
	public static void main(String[] args) {

		Funcrand bk = new Funcrand();
		int test = bk.addNum(); //to store output to 
		System.out.println("The sum is " + test);
		int test2 = bk.multNum();
		System.out.println("The product is " + test2);
		double test3 = bk.divNum();
		System.out.println("The quotient is " + test3);

	}

}

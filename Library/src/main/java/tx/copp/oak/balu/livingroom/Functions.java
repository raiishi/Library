package tx.copp.oak.balu.livingroom;

import java.util.ArrayList;
import java.util.Iterator;

public class Functions {

		public void testArray() {
			
			ArrayList<String> arr = new ArrayList<String>(); //array stores elements
			ArrayList<String> select = new ArrayList<String>();
			
			arr.add("chicken"); //adds elements to the array
			arr.add("biriyani");
			arr.add("chips - Doritos");
			arr.add("chips - Lemon");
			arr.add("chips - Banana");
			arr.add("lays-CHIPS");
			arr.add("burger");
			arr.add("noodles");
			arr.add("cake");
			
			System.out.println(arr);
			arr.remove(0); //indexing starts from 0
			System.out.println("size " + arr.size()); 

			for (int i = 0; i < arr.size(); i++) {
				// System.out.println(arr.get(i));
				
				String test = arr.get(i);
				
				if (test.toUpperCase().contains("CHIPS")) { //checks for the word chips but makes it uppercase so case is negated
				System.out.println(test.toUpperCase());
				select.add(test);
				}
			}
			
			System.out.println("Final Selection" + select);
		}
  
	public int addNum(int num1, int num2) {
		
		System.out.println("Adding the numbers " + num1 + " and " + num2);
		int temp = 0;
		temp = num1 + num2;
		return temp;
	}
		
		public int multNum(int num3, int num4) {
			
			System.out.println("Multiplying the numbers " + num3 + " and " + num4);
			int temp = 0;
			temp = num3 * num4;
			return temp;	
	}
		
		public double divNum(int num5, int num6) {
			
			System.out.println("Dividing the numbers " + num5 + " and " + num6);
			double temp = (double) num5 / num6;
			return temp;
}
		
	public static void main(String[] args) {

		Functions bk = new Functions();
		int test = bk.addNum(15, 10); //to store output to 
		System.out.println("The sum is " + test);
		int test2 = bk.multNum(17, 30);
		System.out.println("The product is " + test2);
		double test3 = bk.divNum(17, 30);
		System.out.println("The quotient is " + test3);

		
		bk.testArray();
	}

}

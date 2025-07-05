package tx.copp.oak.balu.livingroom;

import java.util.Random;

public class Rngtest1 {



	    public static void main(String[] args) {
	        Random rand = new Random(); // make sure this line comes AFTER the import!
	        int randomNum = rand.nextInt(100) + 1; // random number from 1 to 100
	        System.out.println("Your random number is: " + randomNum);
	    }
	}

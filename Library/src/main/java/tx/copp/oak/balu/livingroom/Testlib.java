package tx.copp.oak.balu.livingroom;

import java.util.ArrayList;
import java.util.Scanner;

import tx.copp.oak.balu.physics.Newton;

public class Testlib {

	public static void doLoop() {
		String[] test = new String[4]; // need to mention size in regular array
		test[0] = "balu";
		test[1] = "balu";
		test[2] = "balu";
		test[3] = "balu"; // runtime error if you put 4, doesn't show in the compiler (its not underlined
							// in red).
							// only shows in the console

		System.out.println(test);
		//for (int i = 0; i < 10; i++) {

			int count = 0;
			while (true) {
				System.out.println("please enter password");
				Scanner scanner = new Scanner(System.in);
				String password = scanner.nextLine();
			if (password.equals("zztop")) {
					System.out.println("you stole my diamantes");
					break;
			} else {
						System.out.println("please try again");
						count ++;
					}
			if (count == 5)
					System.out.println("Too many failed attempts.");
					break;
				}

			}

		
	//}

	public static void main(String[] args) {
		Testlib.doLoop();
		


		Person pablo = new Person();
		pablo.setName("Pablo Esco");
		pablo.setAge(3);
		pablo.setAddress("Columbia");
		pablo.setSsn("18574632");

		System.out.println(pablo);

		Newton nt = new Newton();

		Bat manbat = new Bat();
		manbat.setEyes(2);
		manbat.setInfo("dc comics");
		manbat.setLegs(2);
		manbat.setWings(4);

		System.err.println("==============================");
		System.out.println(manbat);
		System.err.println("==============================");

		Cat petey = new Cat();
		petey.setClaws(2);
		petey.setWhiskers(20);

		System.out.println(petey);
		System.err.println("==============================");

		Rat mickey = new Rat();
		mickey.setNose(2);
		mickey.setTail(1);
		mickey.setCheese(1000);

		System.out.println(mickey);
		System.err.println("==============================");

		ArrayList<Mammal> manRay = new ArrayList<Mammal>(); // object means you can add anything
		manRay.add(manbat);
		manRay.add(petey);
		manRay.add(mickey);

		System.out.println(manRay);
		System.err.println("==============================");

	
		ArrayList<Object> john = new ArrayList<Object>();

		for (Mammal mammal : manRay) {
			System.out.println(mammal.getEyes());
		}
	}

}

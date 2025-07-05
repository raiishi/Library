package tx.copp.oak.balu.physics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;

public class Newton {

	
	private HashMap<String, Element> chemMap = new HashMap<String, Element>();

	public void sayHello(String str) {
		System.out.println("Hello " + str.toUpperCase());
	}
	
	public void loadFile() throws FileNotFoundException {
		
		String fileName = "./chem.dat"; //./ = current directory (library)
		File chemF = new File(fileName);
		try {
			Scanner scan = new Scanner(chemF);
			
			String line = "";
			while(scan.hasNextLine()) {
				line = scan.nextLine();
				
				String[] larr = line.split(","); //converts each line into an array
				
				Element get = new Element();
				get.setName(larr[0]);
				get.setSymbol(larr[1]);
				get.setAtomicNumber(Integer.parseInt(larr[2])); //method to convert
				get.setAtomicMass(Double.parseDouble(larr[3]));
				
				chemMap.put(get.getSymbol(), get);
				
				
				// System.err.println(larr[0]);
				
			}
			
			scan.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

	public float finalVelocity(float vi, float time, float acc) {
		float vf = 0.0f;

		vf = vi + acc * time;

		return vf;
	}

	public float avgVelocity(float s1, float s2, float tottime) {
		float av = 0.0f;

		av = (s1 + s2) / tottime;

		return av;

	}
() {
		Element oxygen = new Element();
		oxygen.setName("Oxygen");
		oxygen.setSymbol("O");
		oxygen.setAtomicNumber(8);
		oxygen.setAtomicMass(15.999);

		Element hydrogen = new Element();
		hydrogen.setName("Hydrogen");
		hydrogen.setSymbol("H");
		hydrogen.setAtomicNumber(1);
		hydrogen.setAtomicMass(1.008);

		Element gold = new Element();
		gold.setName("Gold");
		gold.setSymbol("Au");
		gold.setAtomicNumber(79);
		gold.setAtomicMass(196.96657);

		Element uranium = new Element();
		uranium.setName("Uranium");
		uranium.setSymbol("U");
		uranium.setAtomicNumber(92);
		uranium.setAtomicMass(238.02891);

		Element radium = new Element();
		radium.setName("Radium");
		radium.setSymbol("Ra");
		radium.setAtomicNumber(88);
		radium.setAtomicMass(226.0);

		Element thorium = new Element();
		thorium.setName("Thorium");
		thorium.setSymbol("Th");
		thorium.setAtomicNumber(90);
		thorium.setAtomicMass(232.0377);

		chemMap.put(oxygen.getSymbol(), oxygen);
		chemMap.put(hydrogen.getSymbol(), hydrogen);
		chemMap.put(gold.getSymbol(), gold);
		chemMap.put(uranium.getSymbol(), uranium);
		chemMap.put(radium.getSymbol(), uranium);
		chemMap.put(thorium.getSymbol(), uranium);

	}
	
	public void writetoFile(String name) {

	}

	public HashMap<String, Element> getChemMap() { // no setters in hashmap
		return chemMap;
	}

	public void display() {
		Set<String> keyList = chemMap.keySet();

		for (String symbol : keyList) {
			Element ele = chemMap.get(symbol);
			System.err.println(ele);
		}

	}
	
	public Element search(String key) {
		return chemMap.get(key);
	}

	public static void main(String[] args) {


		Newton n1 = new Newton(); // dont have to create new instance again
		try {
			n1.loadFile();
			System.err.println("|||||||||||||||||||||||||||||||||||||||||");
			System.out.println(n1.getChemMap().get("U"));
			System.err.println("|||||||||||||||||||||||||||||||||||||||||");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(n1.getChemMap().get("nickel"));
		System.out.println("This is my first physics-related calculator");
		float test = n1.finalVelocity(25.4f, 10.0f, 7.0f); // float needs to end with f
		System.out.println("Your final velocity is " + test);

		float test2 = n1.avgVelocity(4.4f, 5.6f, 84.0f);
		System.out.println("Your average velocity is " + test2);

		n1.sayHello("pablo");

		// n1.loadElements();

		// System.out.println(n1.getChemMap());

		// n1.display();
		
		// System.out.println(n1.search("Th"));
		
	}
}

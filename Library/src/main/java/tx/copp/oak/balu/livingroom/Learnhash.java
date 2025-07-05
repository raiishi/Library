package tx.copp.oak.balu.livingroom;

import java.util.HashMap;

public class Learnhash {
	//hash is a data structure to store key/value pairs, like name and student id
	//written using flower brackets.
	HashMap<String, String> northhills = new HashMap<String, String>();
	
	public void addStudents() {
		northhills.put("001", "Arjun");
		northhills.put("002", "Varun");
		northhills.put("003", "Rishvik");
		northhills.put("004", "Rishi");
		northhills.put("005", "Arya");
		northhills.put("006", "Sanjana");
		northhills.put("007", "Advaita");
	}
	
	public static void main(String[] args) {
		Learnhash nh1 = new Learnhash();
		nh1.addStudents();
		System.err.println(nh1.northhills);
	}	
}

package tx.copp.oak.balu.livingroom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class LearnHashSet {
	// hash is a data structure to store key/value pairs, like name and student id
	// written using flower brackets.
	HashSet<String> northHash = new HashSet<String>();
	ArrayList<String> northArray = new ArrayList<String>();

	public HashSet<String> getNorthHash() {
		return northHash;
	}

	public void setNorthHash(HashSet<String> northHash) {
		this.northHash = northHash;
	}

	public ArrayList<String> getNorthArray() {
		return northArray;
	}

	public void setNorthArray(ArrayList<String> northArray) {
		this.northArray = northArray;
	}

	public void addStudents() {
		northHash.add("Arjun");
		northHash.add("Varun");
		northHash.add("Rishvik");
		northHash.add("Rishi");
		northHash.add("Arya");
		northHash.add("Sanjana");
		northHash.add("Advaita");
		
		for (int i = 0; i < 20; i++) {
			northHash.add("Arjun");
		}
		
		northArray.add("Arjun");
		northArray.add("Varun");
		northArray.add("Rishvik");
		northArray.add("Rishi");
		northArray.add("Arya");
		northArray.add("Sanjana");
		northArray.add("Advaita");
		
		for (int i = 0; i < 20; i++) {
			northArray.add("Arjun");
		}
		
	}

	public static void main(String[] args) {
		LearnHashSet nh1 = new LearnHashSet();
		nh1.addStudents();
		System.out.println("===========================");
		System.err.println("array: " + nh1.getNorthArray());
		System.out.println("===========================");
		System.err.println("hash: " + nh1.getNorthHash());
		System.out.println("===========================");
	}
}

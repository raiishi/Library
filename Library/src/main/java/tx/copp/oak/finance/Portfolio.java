package tx.copp.oak.finance;

import java.util.ArrayList;
import java.util.Iterator;

public class Portfolio { //has holdings, id, and name
	
	
	private String id;
	private String name;
	private ArrayList<Holding> holdingList = new ArrayList<Holding>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Holding> getHoldings() {
		return holdingList;
	}

	public void setHoldings(ArrayList<Holding> holdings) {
		this.holdingList = holdings;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void addHolding(Holding input) {
		holdingList.add(input);
	}

	public double totalmarketvalue() {
		double tmv = 0;
		for (Holding holding : holdingList) { //gets holdings
			tmv = tmv + holding.getAmtofshares() * holding.getStock().getPrice(); //iterating every time
		}
		
		return tmv;
			
		}

	
	@Override
	public String toString() {
		return "Portfolio [name = " + name + ", holdings = " + holdingList + "]";
	}

}

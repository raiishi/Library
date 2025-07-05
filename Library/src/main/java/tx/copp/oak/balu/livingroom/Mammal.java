package tx.copp.oak.balu.livingroom;

public class Mammal {

	private int legs;
	private int eyes;

	public int getLegs() {
		return legs;
	}

	public void setLegs(int legs) {
		this.legs = legs;
	}

	public int getEyes() {
		return eyes;
	}

	public void setEyes(int eyes) {
		this.eyes = eyes;
	}

	@Override
	public String toString() {
		return "Mammal [legs = " + legs + ", eyes = " + eyes + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

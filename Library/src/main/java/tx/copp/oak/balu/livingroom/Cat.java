package tx.copp.oak.balu.livingroom;

public class Cat extends Mammal {

	public int claws;
	public int whiskers;

	public int getClaws() {
		return claws;
	}

	public void setClaws(int claws) {
		this.claws = claws;
	}

	public int getWhiskers() {
		return whiskers;
	}

	public void setWhiskers(int whiskers) {
		this.whiskers = whiskers;
	}

	@Override
	public String toString() {
		return "Cat [claws = " + claws + ", whiskers = " + whiskers + ", getLegs() = " + getLegs() + ", getEyes() = "
				+ getEyes() + "]";
	}

}

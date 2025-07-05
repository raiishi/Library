package tx.copp.oak.balu.livingroom;

public class Bat extends Mammal {

	public int wings;
	public String info;

	public int getWings() {
		return wings;
	}

	public void setWings(int wings) {
		this.wings = wings;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "Bat [wings = " + wings + ", info = " + info + ", getLegs() = " + getLegs() + ", getEyes() = " + getEyes() + "]";
	}

	

}

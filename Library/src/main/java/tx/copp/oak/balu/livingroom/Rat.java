package tx.copp.oak.balu.livingroom;

public class Rat extends Mammal {

	public int nose;
	public int tail;
	public int cheese;

	public int getNose() {
		return nose;
	}

	public void setNose(int nose) {
		this.nose = nose;
	}

	public int getTail() {
		return tail;
	}

	public void setTail(int tail) {
		this.tail = tail;
	}

	public int getCheese() {
		return cheese;
	}

	public void setCheese(int cheese) {
		this.cheese = cheese;
	}

	@Override
	public String toString() {
		return "Rat [nose = " + nose + ", tail = " + tail + ", cheese = " + cheese + ", getLegs() = " + getLegs()
				+ ", getEyes() = " + getEyes() + "]";
	}

}

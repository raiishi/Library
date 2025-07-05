package tx.copp.oak.balu.physics;

public class Element {

	private int atomicNumber;
	private String symbol;
	private String name;
	private double atomicMass;

	public int getAtomicNumber() {
		return atomicNumber;
	}

	public void setAtomicNumber(int atomicNumber) {
		this.atomicNumber = atomicNumber;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAtomicMass() {
		return atomicMass;
	}

	public void setAtomicMass(double atomicMass) {
		this.atomicMass = atomicMass;
	}

	@Override
	public String toString() {
		return "Element [atomicNumber = " + atomicNumber + ", symbol = " + symbol + ", name = " + name
				+ ", atomicMass = " + atomicMass + "]";
	}

}

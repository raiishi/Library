package tx.copp.oak.finance;

public class Holding { //has security and number of shares

	private int amtofshares;
	private Security stock;
	
	
	
	public int getAmtofshares() {
		return amtofshares;
	}

	public void setAmtofshares(int amtofshares) {
		this.amtofshares = amtofshares;
	}

	public Security getStock() {
		return stock;
	}

	public void setStock(Security stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "holding [amtofshares = " + amtofshares + ", stock = " + stock + "]";
	}





	

	public static void main(String[] args) {

	}

}

package tx.copp.oak.balu.physics;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Testlib2 {

	public static void main(String[] args) {
		
		WriteElements wwwrr = new WriteElements();
		try {
			System.out.println("Writing now");
			wwwrr.writeChems("xu.text");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

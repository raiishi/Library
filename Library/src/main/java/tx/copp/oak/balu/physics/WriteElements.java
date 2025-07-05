package tx.copp.oak.balu.physics;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteElements {

	public void  writeChems(String fileName) throws IOException {
		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		fileName = fileName + "_" + sdf.format(currentDate);
		System.err.println(fileName);
		
		
		FileWriter ft = new FileWriter(fileName);
		ft.write("Darling please cooke me some murg musslum and fish tikka \n");
		ft.write("Darling do not \n");
		ft.write("Shush \n");
		ft.close(); //close the file
	}
	
}

package tx.copp.oak.balu.physics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ModifyElements {
	
	public void modifyElements() throws FileNotFoundException {
        String filename = "./chem.dat"; //“Look for a file called `chem.dat` in the same folder as this program.”
        File modified = new File(filename); //This creates a File object from the file name — it represents the file.
        Scanner scan = new Scanner(modified); //This creates a `Scanner` to **read the file** line by line.

        while (scan.hasNextLine()) { //This loop keeps running as long as there’s another line in the file.
            String line = scan.nextLine(); //This reads **one full line** from the file and stores it in a variable called `line`.
            String[] parts = line.split(","); //This splits the line into parts using commas.
			String elementName = parts[0]; // This stores the **first item** (the element name, like "Hydrogen") into a variable called `elementName`.
										   // splits the line into smaller pieces wherever there's a comma ,, and stores those pieces in an array called parts.
										   // "Take the first thing from the array (parts[0], which is "Hydrogen") and save it in a variable called elementName."

			System.out.println(elementName.toUpperCase()); // prints every line and it repeats for each line in the file.

            }
        scan.close(); //closes scanner to save memory
        }

	public void hashtagReplace(String hash) throws IOException { //hash is just the name of the parameter
		File inputFile = new File(hash);
		File outputFile = new File(hash + ".bak"); //adding the extension
		
		if(inputFile.exists()) {
			System.err.println("The file you are looking for exists. Creating backup of " + hash);
			Scanner scan = new Scanner(inputFile);
				FileWriter write = new FileWriter(outputFile);
				
				while(scan.hasNextLine()) {
					String temp = scan.nextLine();
					
					temp = temp.replace(",", "|");
					write.write(temp + "\n");
					
					
				}
			
				scan.close();
				write.close();
				System.out.println("Done writing to: " + outputFile.getAbsolutePath());
		} else {
			System.err.println("Original file does not exist: " + hash);
		}
	}
	
	public void deleteFile (String name) {
		File blob = new File(name);
		
		if(blob.exists()) {
			System.err.println("Trying to delete the file");
			System.err.println("Path: " + blob.getParentFile());
			blob.delete();
		}
		
	}
				
	public static void main(String[] args) { //main method
        ModifyElements modifier = new ModifyElements(); //This creates an object called modifier from the class ModifyElements.
		try { 
			modifier.modifyElements(); // calls your method to actually **read and print the elements** from the file.
			modifier.deleteFile("C://Users/cleve/Downloads/blob.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			//the try-catch catches the error in case the file is not found and prints the error for you to see.
		}
		try {
			modifier.hashtagReplace("./chem.dat");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}

}

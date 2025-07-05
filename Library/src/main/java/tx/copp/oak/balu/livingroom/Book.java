package tx.copp.oak.balu.livingroom;

// this is a comment
/* this
 *  is
 *  a
 *  multi-
 *  line
 *  comment.
 *  i cannot put words/lines on the closing bracket under me 
 */

public class Book {
	// first letter of class has to be uppercase. no special characters or spaces.
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("this is a library.");
		System.out.print(" lets work");
		System.out.println("print ln means new line. without it, i print on the same line\n"); // println makes a space AFTER the line.
		System.out.println("'slash n' means a new line under the text. it can scale with multiple.\n\n");
		System.out.println("see");
		System.out.println("'slash t' is tab, basically mulitple spaces. works the same as 'slash n'");
		
		String book = "Harry Not a Potter"; // Java executes from top to bottom. if you try and print book before it is declared it will complain.
		// If you declare a variable and then print the variable without assigning a value, it will return a 0.
		// The value of a variable can be assignmed after declaring it.
		System.out.println(book  + " is my favorite book!");
		System.out.println(book.toUpperCase());
		System.out.println("I wrote the book's name in uppercase.");
		
		int myNum = 14;

		int x = 5, y = 4, z = 9; // Variables can be assigned on one line.
		int prod = x * y * z; // I am doing multiplication.
		System.out.println("We have three numbers: " + x + ", " + y + ", " + "and " + z + ".");
		System.out.println("The product of the three numbers is " + prod + ".");
		
		String firstName = "Rishi";
		String lastName = "Thadakapalli";
		System.out.println("I am " + myNum + " years old");
		int myNum2 = myNum * 2;
		System.out.println("If time moved twice as fast I would be " + myNum2 + " years old.");
		
		// DATA TYPES (useful)
		int myTrashNum = 5;          // Integer (whole number)
		float myFloatNum = 5.99f;    // Floating point number
		char myLetter = 'D';         // Character
		boolean myBool = true;       // Boolean
		String myText = "Hello";     // String
		
		String sentence = "Bob is cool.";
		int index1 = sentence.indexOf("cool");
		System.out.println("The index of cool is " + book.indexOf("cool")); //tells you where the word cool starts, or its index (position).
		System.out.println("ZZtop: " + sentence.substring(index1, index1+4)); //cool has 4 characters, so adding 4 gives you the full word.
		System.out.println("Substring of cool: " + sentence.substring(5)); //cuts out everything before the word cool, or index (position), 5 and shows the rest.
		

	}

}

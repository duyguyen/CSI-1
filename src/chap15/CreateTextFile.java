package chap15;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class CreateTextFile {

	// == driver ==
	public static void main(String[] args) {
		try (Formatter output = new Formatter("clients.txt")) {
			Scanner input = new Scanner(System.in);
			System.out.printf("%s%n%s%n? ", "Enter account number, first name, last name, balance.",
					"Enter end of file indicator to the input.");
			while(input.hasNext()) { // loop until end of file indicator
				try {
					//  output new record to file; assume valid input
					output.format("%d %s %s %.2f%n", input.nextInt(),
							input.next(), input.next(), input.nextDouble());
				}
				catch(NoSuchElementException elementException) {
					System.err.println("invalid input. Please try again.");
					input.nextLine(); // discard input so user can try again
				}
				System.out.print("? ");
			}
		}
		catch(SecurityException | FileNotFoundException | FormatterClosedException e) {
			e.printStackTrace();
		}
	}
}

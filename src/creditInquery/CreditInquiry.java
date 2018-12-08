package creditInquery;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CreditInquiry {

	// == attributes ==
	private final static MenuOption[] choices = MenuOption.values();
	
	// == driver ==
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// get user request
		MenuOption accountType = getRequest(input);
		
		while(accountType != MenuOption.END) {
			switch(accountType) {
			case ZERO_BALANCE: System.out.printf("%nAccount with zoro balances:%n");break;
			case CREDIT_BALANCE: System.out.printf("%nAccount with credit balances:%n"); break;
			case DEBIT_BALANCE: System.out.printf("%nAccount with debit balances:%n"); break;
			}
			
			readRecords(accountType);
			accountType = getRequest(input); // get user's request
		}
	}
	
	// == private methods ==
	private static void readRecords(MenuOption accountType) {
		try(Scanner input = new Scanner(Paths.get("clients.txt"))){
			while (input.hasNext()) { // more data to read
				int accountNumber = input.nextInt();
				String firstName = input.next();
				String lastName = input.next();
				double balance = input.nextDouble();
				
				// if proper account type, display record
				if(shouldDisplay(accountType, balance)) {
					System.out.printf("%-10d%-12s%-12s%10.2f%n", 
							accountNumber, firstName, lastName, balance);
				}else {
					input.nextLine(); // discard the rest of the current record
				}
			}
		}catch(NoSuchElementException | IllegalStateException | IOException e) {
			System.err.println("Error processing file. Terminating.");
			System.exit(1);
		}
	}
	
	private static boolean shouldDisplay(MenuOption option, double balance) {
		if((option == MenuOption.CREDIT_BALANCE) && (balance < 0)) {
			return true;
		}else if((option == MenuOption.DEBIT_BALANCE) && (balance > 0)) {
			return true;
		}else if((option == MenuOption.ZERO_BALANCE) && (balance == 0)) {
			return true;
		}
		
		return false;
	}
	
	private static MenuOption getRequest(Scanner input) {
		int request = 4;
		System.out.printf("%nEnter Request%n%s%n%s%n%s%n%s%n", 
				"1 - List account with zero balances",
				"2 - List account with credit balances",
				"3 - List account with debit balances",
				"4 - Terminate program");
		
		try {
			do { // input user request
				System.out.printf("%n? ");
				request = input.nextInt();
			}while((request < 1) || (request > 4));
		}
		catch(NoSuchElementException noSuchElementException) {
			System.err.println("Invalid input. Terminating.");
		}
		
		return choices[request - 1]; // return enum value for option
	}
}

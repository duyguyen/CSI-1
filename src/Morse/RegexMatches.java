package Morse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {
	
	// == constants ==
	private String firstName;
	private String zipCode;
	private String lastName;
	private String address;
	private String phoneNumber;
	
	// == constructor ==
	public RegexMatches(String firstName, String zipCode, String lastName, String address, 
			String phoneNumber) {
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.address = address;
		this.zipCode = zipCode;
		this.lastName = lastName;
	}

	// == driver ==
	public static void main(String[] args) {
		RegexMatches construct = new RegexMatches("Jane", "12345", "Doe", "123 Some Street", 
				"223-456-7890");
		construct.validateInput();
		construct.tokenTest();
		construct.characters();
	}
		
	// == private methods ==
	private void characters() {
		int radix = 16;
		char character = 'A';	
		System.out.printf("Convert digit to Character: %s%nConvert character to digit: %s%n",
				Character.forDigit(13, radix), Character.digit(character, radix));
		System.out.printf("%s%n%n", "-------------------------------");
	}
	
	private void tokenTest() {
		String sentence = "This is a sentence with seven tokens";
		String[] tokens = sentence.split(" ");
		System.out.printf("Number of elements: %d%nThe tokens are: %n", tokens.length);
		for(String token: tokens) {
			System.out.println(token);
		}
		System.out.printf("%s%n%n", "-------------------------------");
	}
	
	private void validateInput() {
		System.out.println("FirstName: " + validateFirstName());
		System.out.println("LastName: " + validateLastName());
		System.out.println("Zipcode: " + validateZipcode());
		System.out.println("Address: " + validateAddress());	
		System.out.println("PhoneNumber: " + validatePhoneNumber());	
		System.out.printf("%s%n%n", "-------------------------------");
	}
	
	private boolean validateFirstName() {
		return firstName.matches("[A-Z][a-zA-Z]*");
	}
	private boolean validateLastName() {
		return lastName.matches("[a-zA-Z]+(['-][a-zA-Z]+)*");
	}
	private boolean validateAddress() {
		return address.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
	}
	
	private boolean validatePhoneNumber() {
		return phoneNumber.matches("[1-3]\\d{2}-[1-9]\\d{2}-\\d{4}");
	}
	
	private boolean validateZipcode() {
		return zipCode.matches("\\d{5}");
	}
	
	private void sampleTest() {
		// create pattern
		Pattern expression = Pattern.compile("J.*\\d[0-3?5-9]-\\d\\d-\\d\\d");
		String string1 = "Jan's Birthday is 05-12-75\n" + 
				"Jame's birthday is 11-04-68\n" + 
				"Jim's birthday is 04-28-73\n" +
				"Join's birthday is 12-17-77\n";
		
		// match regular expression to string and print matches
		Matcher matcher = expression.matcher(string1);
		
		while(matcher.find()) {
			System.out.println(matcher.group());
		}
	}
	
	
}

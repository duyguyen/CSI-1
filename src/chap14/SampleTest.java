package chap14;
public class SampleTest{
	
	// == main methods ==
	public static void main(String[] args){
		SampleTest sampleTest = new SampleTest();
		
//		sampleTest.stringConstructor();
//		sampleTest.stringMiscellaneous();
//		sampleTest.stringCompare();
//		sampleTest.stringStartAndEnd();
//		sampleTest.testIndexOf();
//		sampleTest.subString();
//		sampleTest.concatenation();
//		sampleTest.stringMiscellaneous2();
//		sampleTest.stringValue();
		sampleTest.switchMessage();
	}
	
	// == public methods ==
	public void switchMessage() {
		char response = 'y';
		
		switch(response) {
		case 'y': System.out.println("true"); break;
		default: System.out.println("Invalid");
		}
	}
	
	public void stringValue() {
		char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f'};
		boolean booleanValue = false;
		char charracterValue = 'Z';
		int integerValue = 7;
		long longValue = 10000000000L; // L suffix indicates long
		float floatValue = 2.5f;
		double doubleValue = 33.333;
		Object objectRef = "hello"; // assign string to an object reference
		
		System.out.println("Char array: " + String.valueOf(charArray));
		System.out.println("Part of the char array: " + String.valueOf(charArray, 1, 2));
		System.out.println("Boolean: " + String.valueOf(booleanValue));
		System.out.println("Character: " + String.valueOf(charracterValue));
		
		System.out.println("integerValue: " + String.valueOf(integerValue));
		System.out.println("longValue: " + String.valueOf(longValue));
		System.out.println("floatValue: " + String.valueOf(floatValue));
		System.out.println("doubleValue: " + String.valueOf(doubleValue));
		System.out.println("objectRef: " + String.valueOf(objectRef));
	}
	
	public void stringMiscellaneous2() {
		String s1 = "hello";
		String s2 = "GOODBYE";
		String s3 = "    spaces    ";
		
		System.out.println("Replace l with L in s1: " + s1.replace("l", "L"));
		System.out.println("To uppercase: " + s1.toUpperCase());
		System.out.println("To lowercase: " + s1.toLowerCase());
		
		System.out.println("Trim: " + s3.trim());
		
		char[] charArray = s1.toCharArray();
		for(char letter: charArray) {
			System.out.print(letter + ", ");
		}

	}
	
	public void concatenation() {
		String s1 = "Happy";
		String s2 = "Birthday";
		
		System.out.println("Concatenate: " + s1.concat(s2));		
		System.out.println("s1 after concat: " + s1);
	}
	
	public void subString() {
		String strings = "qwertyuiopasdfghjklzxcvbnm,m";
		
		System.out.println("Start from index of 20: " + strings.substring(20));
		System.out.println("from 3 upt to , bbut not include 6: " + strings.substring(3, 6));
	}
	
	public void testIndexOf() {
		String letters = "qwecrtyuiopasdfghjklzxcvbnm";
		
		System.out.printf("Index of c: %d%n", letters.indexOf("c"));
		System.out.printf("Index of c: %d%n", letters.indexOf("c", 1));
		
		System.out.printf("Last Index of c: %d%n", letters.lastIndexOf("c"));
		
		System.out.printf("Index of def: %d%n", letters.indexOf("def"));
	}
	
	public void stringStartAndEnd() {
		String[] strings = {"started",  "starting", "ended", "ending"};
		
		// test for start with
		for(String string: strings) {
			if(string.startsWith("st")) {
				System.out.printf("\"%s\" start with \"st\"%n", string);
			}
		}
		
		// startWith starting from position 2
		for(String string: strings) {
			if(string.startsWith("art", 2)) {
				System.out.printf("\"%s\" start with \"art\" at position 2%n", string);
			}
		}
		
		for(String string: strings) {
			if(string.endsWith("ed")) {
				System.out.printf("\"%s\" end with \"ed\"%n", string);
			}
		}
	}
	
	public void stringCompare() {
		String s1 = new String("hello");
		String s2 = "goodbye";
		String s3 = "happy Birday";
		String s4 = "happy birday";
		
		
		
		boolean equalsCompare = s1.equals("Hello");
		int compareTo = s1.compareTo(s2);
		
		System.out.printf("equalsCompare: %s%n", equalsCompare ? "true":"false");
		System.out.printf("compareTo: %d%n", compareTo);
		
		// first -> starting index in invoke string
		// second -> comparison string
		// third -> starting index of comparison string
		// fourth -> number of characters to compare between two string
		if(s3.regionMatches(0,  s4,  0,  5)) {
			System.out.println("first five characters of s3 and s4 are matched");
		}
		
	}
	
	public void stringMiscellaneous(){
		String s1 = "Hello";
		char[] charArray = new char[5];
		
		System.out.printf("s1: %s", s1);
		
		// test length method
		System.out.printf("%nLength of s1: %d", s1.length());
		
		// loop through characters in s1 with charAt and display reverse
		System.out.printf("%nThe sting reverse is: ");
		
		for(int count = s1.length() - 1; count >= 0; count--){
			System.out.printf("%c ", s1.charAt(count));
		}
		
		// copy characters from string to charArray
		// 0 -> srcBegin, 5 -> srcEnd,  
		// charArray -> destination array, 0 -> start offset in destination array
		s1.getChars(0, 5, charArray, 0);  
		
		System.out.printf("%nThe characters array is: ");
		for(char letter: charArray){
			print(letter);
		}
	}
	
	public void stringConstructor(){
		char[] charArray = {'b', 'i', 'r', 't', 'h', ' ', 'd', 'a', 'y'};
		String s = new String("Hello");
		
		String s1 = new String();
		String s2 = new String(s);
		String s3 = new String(charArray);
		String s4 = new String(charArray, 6, 3);
		
		System.out.printf("s1 = %s%ns2 = %s%ns3 = %s%ns4 = %s%n", s1, s2, s3, s4);
	}
	
	public void print(Object object){
		System.out.print(object);
	}
}
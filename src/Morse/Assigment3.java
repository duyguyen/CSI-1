package Morse;

import java.util.Scanner;

public class Assigment3 {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.printf("%5sletters [a-zA-Z] and digit [0-9]%5s%n%n", "*****", "*****");
		System.out.printf("%5sCONVERT ENGLISH TO MORSE%n", "     ");
		System.out.printf("%5sEnter an english sentence: ", "     ");
		String english = scanner.nextLine();
		Translate englishToMorse = new EnglishToMorse(english);
		englishToMorse.translate();

		System.out.println();

		System.out.printf("%5sCONVERT MORSE TO ENGLISH%n", "     ");
		System.out.printf("%5s(one space between letters and three spaces between words)%n", "     ");
		System.out.printf("%5sEnter a morse code sentence: ", "     ");
		String morse = scanner.nextLine();
		MorseToEnglish morseToEnglish = new MorseToEnglish(morse);
		morseToEnglish.translate();

	}
}

interface Translate {
	void translate();
}

abstract class TranslateBase {
	
	// == constants ==
	protected final String[] morseSigns = { "-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...",
			"---..", "----.", ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
			"--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.." };
	
	protected int indexMorse;
	protected int offset;
	protected String indexString;
	protected StringBuilder result = new StringBuilder();
	
	// == constructor ==
	public TranslateBase(String indexString) {
		this.indexString = indexString;
	}
	
	// == abstract methods ==
	abstract boolean validateUserInput(String aStr);
	abstract void buildOffset(String str);
	abstract void buildIndexMorse(String str);
	
	// == public methods ==
	public String getIndexString() {
		return indexString;
	}
	
	public StringBuilder getResult() {
		return result;
	}
	
	public void setIndexMorse(int indexMorse) {
		this.indexMorse = indexMorse;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public String trimWord(String word) {
		return word.trim();
	}
}

class EnglishToMorse extends TranslateBase implements Translate{
	
	// == constructor ==
	public EnglishToMorse(String indexString) {
		super(indexString);
	}
	
	// == public methods ==
	@Override
	public void translate() { // convert English to Morse code
		String[] englishWords = indexString.split(" ");
		for(int i = 0; i < englishWords.length; i++) {
			String upercase = englishWords[i].toUpperCase();
			char[] charLetters = upercase.toCharArray();
			for(char letter: charLetters) {	
				String letterToString = String.valueOf(letter);
				if(validateUserInput(letterToString)) {
					buildIndexMorse(letterToString);
					buildOffset(letterToString);
					
					result.append(morseSigns[indexMorse - offset]);
				}else {
					throw new IllegalArgumentException("Your value couldn't be converted into morse code"); // throw a message for Exception error when user input doesn't match				
				}
				
				result.append(" ");
			}
			result.append("   ");			
		}
		System.out.printf("%5sMorse Code => %s%n%n","     ", result);
	}
	
	@Override
	public void buildIndexMorse(String str) {
		char charType = str.charAt(0);
		indexMorse = (int)charType;
	}
	
	@Override
	public boolean validateUserInput(String str) { // validate user input in English language
		return str.matches("[A-Z]|[0-9]");
	}
	
	@Override
	public void buildOffset(String str) {
		char charType = str.charAt(0);
		offset = (Character.isDigit(charType))? 48: 55;
	}

}

class MorseToEnglish extends TranslateBase implements Translate {
	// == constructor ==
	public MorseToEnglish(String morseString) {
		super(morseString);
	}

	// == public methods ==
	@Override
	public void translate() { // translate Morse code to English language
		String[] splitWordUserMorseInput = indexString.split("\\s{3}");
		
				
		for (int i = 0; i < splitWordUserMorseInput.length; i++) {
		
			String[] splitLetterUserInput = trimWord(splitWordUserMorseInput[i]).split("\\s");

			for (int j = 0; j < splitLetterUserInput.length; j++) {
				if (validateUserInput(splitLetterUserInput[j])) {
					
					buildIndexMorse(splitLetterUserInput[j]);
					buildOffset(splitLetterUserInput[j]);
										
					result.append((char) (indexMorse + offset));
				} else {
					throw new IllegalArgumentException("Your value couldn't be converted into englisnh");  // throw a message for Exception error when user input doesn't match
				}				
			}
			result.append(" ");	
		}
		System.out.printf("%5sEnglish => %s","     ", result);
	}
	
	@Override
	public void buildIndexMorse(String str) {
		for(int i = 0; i < morseSigns.length; i++) {
			if(morseSigns[i].equals(str)) {
				indexMorse = i;
			}
		}
	}

	@Override
	public boolean validateUserInput(String aStr) { // validate user input in Morse code
		return aStr.matches("[-.]{5}|[-.]{4}|[-.]{3}|[-.]{2}|[-.]{1}");
	}

	@Override
	public void buildOffset(String str) {
		setOffset((str.length() == 5) ? 48 : 55);
	}	
	
}
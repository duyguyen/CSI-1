package Assignment_1;
/**
 * 
 * Programming Assignment 1, task C
 * Class name: TaskC
 * Description: 1. Print the frequency of each letter in word1 which appear in word2.
 *              2. Print the histogram of the frequency.
 * Author:[Thanh Nguyen]
 * Date: 09/15/2018
 * 
 */

import java.util.Scanner;

public class TaskC
{
   public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		TaskC taskC = new TaskC();
		
		System.out.print("Enter the first word: ");
		String firstWord = input.nextLine();
		
		System.out.print("Enter the second word: ");
		String secondWord = input.nextLine();
		
		taskC.process(firstWord, secondWord);
		
		
	}
	
	// == public methods ==
	public void process(String firstWord, String secondWord){
		System.out.println();
		

		char[] secondWordChar = secondWord.toCharArray();
		char[] removeDuplicateInfirstWord = eliminateDuplicate(firstWord);
		int[] frequency = new int[removeDuplicateInfirstWord.length];
			
		// save frequency to frequency array
		for(int i = 0; i < removeDuplicateInfirstWord.length; i++){
			for(int j = 0; j < secondWordChar.length; j++){
				if(removeDuplicateInfirstWord[i] == secondWordChar[j]){
					frequency[i]++;
				}
			}
		}
		
		// print the frequency of letters in word1 appear in word2
		System.out.print("Letters");
		System.out.print("       ");
		System.out.print("Frequency\n");
		for(int a = 0; a < removeDuplicateInfirstWord.length; a++){
			System.out.printf("%3c %s %d", removeDuplicateInfirstWord[a], "            ", frequency[a]);	
			System.out.println();
		}
		
		// print histogram of the frequency
		System.out.println();
		System.out.println("Histogram of the frequency");
		System.out.println();
		for(int j = 0; j < frequency.length; j++){
			System.out.print("Letter " + removeDuplicateInfirstWord[j] + ": ");
			
			// print stars for each letter
			for(int star = 0; star < frequency[j]; star++){
				System.out.print("* ");
			}
			
			System.out.println();
		}
	}
	
	public char[] eliminateDuplicate(String str){	
		
		// sort the input string by Unicode characters
		TaskB taskB = new TaskB();
		char[] sortArr = taskB.sortUnicodeCharacter(str);	
		char[] temp = new char[sortArr.length];
		int m = 0;
		int count = 0;
		
		// save unique char in temp array
		for(int i = 0; i < sortArr.length; i++){
			if((i + 1) <= sortArr.length - 1){
				if(sortArr[i] != sortArr[i + 1]){
					temp[m] = sortArr[i];
					m++;
				}
			}else{
				temp[m] = sortArr[i];
			}
		}
		
		// count actual char in temp array
		for(int j = 0; j < temp.length; j++){
			if(temp[j] != 0){
				count++;
			}
		}
		
		// save chars in temp array to temp2 array
		char[] temp2 = new char[count];
		for(int k = 0; k < temp2.length; k++){
			temp2[k] = temp[k];
		}
		
		return temp2;
	}
}

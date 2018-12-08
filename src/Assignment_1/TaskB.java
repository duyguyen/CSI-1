package Assignment_1;
/**
 * 
 * Programming Assignment 1, task B
 * Class name: TaskB
 * Description: 1. Convert user's input to an array of Unicode decimal numbers.
 *              2. Determine if the input is palindrome.
 *              3. Sort Unicode character array according Unicode decimal number.
 *              4. Compute max, min, sum, average of the Unicode values.
 *              5. Display items 1 to 4 as processing occure,
 * Author:[Thanh Nguyen]
 * Date: 09/15/2018
 * 
 */

import java.util.Scanner;

public class TaskB
{
    public static void main(String[] args){
       System.out.print("Enter a word: ");
       
       // declare scanner
       TaskB TaskB = new TaskB();
       Scanner so = new Scanner(System.in);
       String word = so.nextLine();
       String[] chars = word.split("");
       
     
	   System.out.println();
       System.out.print("1. Unicode number array: {");
       TaskB.convertTodecimal(word);
	   System.out.print("} \n");
	   System.out.println();
	   
	   
       System.out.println("2. Is input Palindrome: " + TaskB.isPalindrome(word));
	   System.out.println();
	   
	   System.out.print("3. Sort unicode characters: {");
	   for(int i = 0; i < TaskB.sortUnicodeCharacter(word).length; i++){
		   System.out.print(TaskB.sortUnicodeCharacter(word)[i] + " ");
	   }
	   //TaskB.sortUnicodeCharacter(word);
	   System.out.print("}\n");
	   System.out.println();
	   
	   System.out.print(
		   "4. Max: " + TaskB.max(word) + ";" + 
		   " Min: " + TaskB.min(word) + ";" +
		   " sum: " + TaskB.sum(word) + ";" +
		   " Average: " + TaskB.average(word)
	   );
	   System.out.println();
            
    }
    
    // == publi cmethods ==
	public double average(String str){
		char[] charArray = str.toCharArray();
		double sum = (double)sum(str);
		return (sum / charArray.length);
	}
	
	public int sum(String str){
		char[] charArray = str.toCharArray();
		int sum = 0;
		for(char charVal: charArray){
			sum += (int)charVal;
		}
		
		return sum;
	}
	
	public int min(String str){
		char[] charArray = str.toCharArray();
		int minimum = (int)charArray[0];
		for(char value: charArray){
			if(minimum > (int)value){
				minimum = (int)value;
			}
		}
		return minimum;
	}
	
	public int max(String str){
		char[] charArray = str.toCharArray();
		int maximum = (int)charArray[0];
		for(char value: charArray){
			if(maximum < (int)value){
				maximum = (int)value;
			}
		}
		return maximum;
	}
	
	public char[] sortUnicodeCharacter(String str){
		
		char[] charArray = str.toCharArray();
		boolean swap = true;
		char temp;
		
		while(swap){
			swap = false;
			for(int i = 0; i < charArray.length - 1; i++){	
				if((int)charArray[i] > (int)charArray[i + 1]){
					swap = true;	
					temp = charArray[i];
					charArray[i] = charArray[i + 1];
					charArray[i + 1] = temp;
				}				
			}
		}

		return charArray;
	}
	
	public boolean isPalindrome(String str)
	{	
		boolean result = false;
        
        str = str.toLowerCase();
		char[] charArray = str.toCharArray();
		
		for(int i = 0; i < charArray.length; i++){
			
			int left = i;
			int right = charArray.length - 1 - i;
			
			if(left < right){
				if(charArray[left] == charArray[right]){
					result = true;
				}else{
					result = false;
				}
			}	
		}
		return result;
	}
	
    public void convertTodecimal(String words){
	
		char[] charArray = words.toCharArray();
		int[] unicodeDecimalArr = new int[charArray.length];
		
        for(int i = 0; i < charArray.length; i++){
			int intChar = (int)charArray[i];
			unicodeDecimalArr[i] = intChar;
            System.out.print(unicodeDecimalArr[i] + " ");
        }
		
  
    }
}

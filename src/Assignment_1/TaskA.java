package Assignment_1;
/**
 * 
 * Programming Assignment 1, task A
 * Class name: TaskA
 * Description: Compile and run the program.
 *              Modify the program to print the program
 *              to print the first and last letters of user's input
 * Author:[Thanh Nguyen]
 * Date: 09/14/2018
 * 
 */

import java.util.Scanner;

public class TaskA
{
   public static void main(String[] args){
       System.out.print("Enter a word: ");
        Scanner input = new Scanner(System.in);    
        String word = input.nextLine();
        String[] chars = word.split("");
        
        System.out.println("The word entered is: " + word);
        System.out.println("The first letter is: " + chars[0]);
        System.out.println("The last letter is: " + chars[chars.length - 1]); 
       
          
    }
}





























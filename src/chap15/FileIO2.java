package chap15;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO2 {

	// == driver ==
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("clients.txt")); // specify file 
		FileWriter fw = null;
		File file = null;
		
		try {
			file = new File("output2.txt");
			if(!file.exists()) {
				file.createNewFile();
			}
			
			fw = new FileWriter(file);
			
			while(input.hasNextLine()) {
				String line = input.nextLine();
				String[] fields = line.split(","); // split on comma delimiters
				fw.write(fields[0] + "\t" + fields[6] + "\n"); // inserting \n for new line
				
				System.out.println(fields[0] + "\t" + fields[6]); // write only cols 1 & 7
			}
			fw.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

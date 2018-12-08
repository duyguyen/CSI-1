package chap15;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO3 {
	
	// == driver ==
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader("clients.txt"));
		BufferedWriter output = new BufferedWriter(new FileWriter("output3.txt"));
		String line;
		
		System.out.println(input.readLine());
		
		/*while((line = input.readLine()) != null) {
			String[] fields = line.split(",");
			output.write(fields[0] + "\t" + fields[6]);
			output.newLine();
		}
		
		input.close();
		output.close();*/
	}

}

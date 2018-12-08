package chap15;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ClassFileIO {

	// == driver ==
	public static void main(String[] args) throws IOException {
		FileInputStream input = null;
		FileOutputStream output = null;
		
		try {
			input = new FileInputStream("clients.txt"); // specify input file
			output = new FileOutputStream("output.txt");
			
			int stream;
			
			while((stream = input.read()) != -1) { // until end of stream
				output.write(stream);
			}
		}finally {
			if(input != null) {
				input.close();
			}
			if(output != null) {
				output.close();
			}
		}
	}
}

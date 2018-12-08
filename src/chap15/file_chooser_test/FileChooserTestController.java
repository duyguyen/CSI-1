package chap15.file_chooser_test;

import java.awt.Button;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

// (637) line 39

public class FileChooserTestController {

	@FXML private BorderPane borderPane;
	@FXML private Button selectButton;
	@FXML private Button selectDirectoryButton;
	@FXML private TextArea textArea;
	
	// handle selectFileButton's events
	@FXML
	private void selectFileButtonPressed(ActionEvent e) {
		// configure dialog allowing selection of a file
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select file");
		
		// display files in folder from which the app was launched
		fileChooser.setInitialDirectory(new File("."));
		
		// display the file chooser
		File file = fileChooser.showOpenDialog(borderPane.getScene().getWindow());
		
		// process selected path or display message
		if(file != null) {
			analyzePath(file.toPath());
		}
	}
	
	// display information about file or directory user specifies
	public void analyzePath(Path path) {
		try {
			// if the file or directory exists, display its info
			if(path != null && Files.exists(path)) {
				// gather file (or directory) information
				StringBuilder builder = new StringBuilder();
				builder.append(String.format("%s:%n", path.getFileName()));
				builder.append(String.format("%s a directory%n", 
						Files.isDirectory(path)?"is":"is not"));
				
				builder.append(String.format("%s an absolute path%n",
						path.isAbsolute()?"is":"is not"));
				
				builder.append(String.format("Last modified: %s%n",
						Files.getLastModifiedTime(path)));
				
				builder.append(String.format("Size: %s%n",
						Files.size(path)));
				
				builder.append(String.format("Path: %s%n",
						path));
				
				builder.append(String.format("Absolut path: %s%n",
						path.toAbsolutePath()));
				
				if(Files.isDirectory(path)) { // output directory listing
					builder.append(String.format("%nDirectory contents:%n"));
					
					// object for iterating through a directory's contents
					DirectoryStream<Path> directoryStream = 
							Files.newDirectoryStream(path);
					
					for(Path p:directoryStream) {
						builder.append(String.format("%s%n", p));
					}
				}
				
				// display file or directory info
				textArea.setText(builder.toString());
			}else {
				textArea.setText("Path doesn't exist");
			}
		}catch(IOException ioException) {
			textArea.setText(ioException.toString());
		}
	}
}

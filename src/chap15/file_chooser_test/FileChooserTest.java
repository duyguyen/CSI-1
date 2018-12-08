package chap15.file_chooser_test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FileChooserTest extends Application {

	// == driver ==
	public static void main(String[] args) {
		launch(args);
	}

	// == public methods ==
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FileChooserTest.fxml"));

		Scene scene = new Scene(root);
		stage.setTitle("File Chooser Test"); // display in title bar
		stage.setScene(scene);
		stage.show();

	}
}

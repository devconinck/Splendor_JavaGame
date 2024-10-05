package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class SchermController extends AnchorPane {

	public SchermController() {
		
	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("TaalScherm.fxml"));
 	loader.setController(this);
 	loader.setRoot(this);
 	
 	
 		try {
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	
	}
	}

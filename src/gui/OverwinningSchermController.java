package gui;

import java.io.IOException;
import domein.DomeinController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import resources.Taal;

/**
 * klasse voor het overwinningscherm, wordt getoond wanneer 1 of meerdere spelers zijn gewonnen
 */
public class OverwinningSchermController extends Pane {
	
	@FXML
	private Label lblWinnaar, lblProficiat;
	
	DomeinController dc;
	
	/**
	 * Constructor voor de OverwinningSchermController, roept de FXMLLoader aan, set de controller en root, laadt de background en roept buildGUI() aan 
	 * @param dc, DomeinController
	 */
	public OverwinningSchermController(DomeinController dc)
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("OverwinningScherm.fxml"));
	 	loader.setController(this);
	 	loader.setRoot(this);
	 	try
	 	{
	 		this.dc = dc;
	 		loader.load();
	 		buildGUI();
	 	}
	 	catch(IOException e)
	 	{
	 		System.out.println("OverwinningSchermController.java");
	 		e.printStackTrace();
	 		System.out.println(Taal.getString("schermNietBeschikbaar"));
	 	}
	 }
	
	 /**
	  * toont de correcte tekst voor de winnaar(s)
	  */
	private void buildGUI() {
		lblProficiat.setText(Taal.getString("isGewonnen"));
		lblWinnaar.setWrapText(true);
		lblWinnaar.setText(dc.geefWinnaars() );
	}
}

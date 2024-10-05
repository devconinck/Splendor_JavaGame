package gui;

import java.io.IOException;
import domein.DomeinController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import resources.Taal;

/**
 * klasse voor het taalscherm, dit wordt als eerste aangeroepen wanneer het spel wordt opgestart
 * @author Quinten
 *
 */
public class TaalSchermController extends VBox {
	
	  private DomeinController dc;
	  @FXML
	   private Button btnSkip;
	  
	  /**
	   * Constructor voor de TaalSchermController, roept de FXMLLoader aan, set de controller en root en roept buildGUI() aan 
	   * @param dc, DomeinController
	   */
	 public TaalSchermController(DomeinController dc)
	 {
	 	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("TaalScherm.fxml"));
	 	loader.setController(this);
	 	loader.setRoot(this);
	 	try
	 	{
	 		this.dc = dc;
	 		loader.load();

	 	btnSkip.setText("Ctrl + ENT to skip");
	 	btnSkip.requestFocus();
	    btnSkip.setOnKeyPressed(e -> {										
	    	if (e.getCode() == KeyCode.ENTER && e.isControlDown()) {
		   		dc.voegSpelerToeAanSpel("xXx_Killer_xXx", 1999);
		   		dc.voegSpelerToeAanSpel("A_Star", 1999);
		   		dc.voegSpelerToeAanSpel("S", 2016);
		   		dc.voegSpelerToeAanSpel("A_", 1997);
		   		 
	    		SpelOverzichtController spel = new SpelOverzichtController(dc);
	 			Scene huidigeScene = this.getScene();
		 		Stage huidigeStage = (Stage) huidigeScene.getWindow();
		 		huidigeStage.setTitle("SpelOverzicht Splendor");
				huidigeStage.setTitle(Taal.getString("nogSpelersToevoegen"));
		 		huidigeStage.setScene(new Scene(spel));
		 		huidigeStage.setMaximized(true);							
		    	 }
		     });
	 	}
	 	catch(IOException e)
	 	{
	 		System.out.println("TaalScherm");
	 		e.printStackTrace();
	 		System.out.println("Het Scherm kan niet getoond worden");
	 	}
	 }
	 
	 /**
	  * zorgt ervoor dat de juiste taal wordt gekozen op basis van op welke vlag de speler klikt
	  * @param event, klik event op 1 van de vlaggen
	  */
	 @FXML
	 private void setTaal(MouseEvent event) {
	     ImageView imgView = (ImageView) event.getSource();
	     int taalKeuze = Integer.parseInt(imgView.getId());
	     Taal.setTaal(taalKeuze);
	     gaNaarSpelerToevoegen();
	 }
	 
	 /**
	  * maakt een SpelerToevoegenController aan en toont de nieuwe scene, dit gebeurt wanneer de speler op 1 van de vlaggen heeft geklikt, na de setTaal() methode
	  */
	@FXML
	private void gaNaarSpelerToevoegen() {
		SpelerToevoegenController voegSpelerToeScherm = new SpelerToevoegenController(dc);
		Scene huidigeScene = this.getScene();
		Stage huidigeStage = (Stage) huidigeScene.getWindow();
		huidigeStage.setTitle("Voeg speler toe");
		huidigeStage.setScene(new Scene(voegSpelerToeScherm));
	}
}

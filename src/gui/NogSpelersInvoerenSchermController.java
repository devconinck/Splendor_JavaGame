package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import resources.Taal;
/**
 * klasse voor het scherm dat wordt getoond van zodra er 1 speler is ingelogd, geeft de gebruiker de keuze tussen nog een speler invoegen of het spel starten
 * @author Quinten
 *
 */
public class NogSpelersInvoerenSchermController extends Pane
{

    @FXML
    private Button btnGeenSpelersMeer, btnNogEenSpeler;
    @FXML
    private Label lblVraag;
    private Alert aFoutmelding;
    private DomeinController dc;
    
    /**
     * Constructor voor de NogSpelersInvoerenController, roept de FXMLLoader aan, set de controller en root, laadt de background en roept buildGUI() aan 
     * @param dc, DomeinController
     */
    public NogSpelersInvoerenSchermController(DomeinController dc)
    {
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("NogSpelersInvoerenScherm.fxml"));
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
    		System.out.println("NogSpelersInvoerenSchermController.java");
    		System.out.println(Taal.getString("hetNogSpelersSchermFaalt"));
    		e.printStackTrace();
    	}
    }
    
    /**
     * zet de tekst voor de buttons en het label 
     */
    private void buildGUI() 
    {
		btnGeenSpelersMeer.setText(Taal.getString("btnGeenSpelersMeer"));
		btnNogEenSpeler.setText(Taal.getString("btnNogEenSpeler"));
		if(dc.getSpelersDTO().size() == 4) {
		btnNogEenSpeler.setDisable(true);
		}
		lblVraag.setText(Taal.getString("lblVraagNogSpeler"));
	}
    
    /**
     * toont het spelbord wanneer de gebruiker kiest om het spel te starten, maakt dan een SpelOverzichtController aan, of werpt een exception wanneer het aantal spelers niet klopt
     * @param event, klik event op de button
     */
	@FXML
    void StartSpelOverzicht(ActionEvent event)
    {

		if(dc.getSpelersDTO().size() > 1 && dc.getSpelersDTO().size() < 5) {
			SpelOverzichtController spel = new SpelOverzichtController(dc);
			Scene huidigeScene = this.getScene();
			Stage huidigeStage = (Stage) huidigeScene.getWindow();
			huidigeStage.setTitle("SpelOverzicht Splendor");
			huidigeStage.setTitle(Taal.getString("nogSpelersToevoegen"));
			huidigeStage.setScene(new Scene(spel));
			huidigeStage.setMaximized(true);
		} else {
			aFoutmelding = new Alert(AlertType.ERROR);
    		aFoutmelding.setTitle(Taal.getString("geeftFout"));
    		aFoutmelding.setHeaderText(null); 
    		aFoutmelding.setContentText(Taal.getString("aantalGebruikers")); //"There must be a minimum of 2 and maximum of 4 players!"
    		aFoutmelding.show();
		}
    }
	
	/**
	 * gaat terug naar het SpelerToevoegen scherm wanneer de gebruiker kiest voor "nog een speler toevoegen"
	 * @param event, klikt event wanneer de gebruiker op de button klikt
	 */
    @FXML
    void voegSpelerToeAanLijst(ActionEvent event)
    {
    	
		SpelerToevoegenController sInvoer = new SpelerToevoegenController(dc);
		Scene huidigeScene = this.getScene();
		Stage huidigeStage = (Stage) huidigeScene.getWindow();
		huidigeStage.setTitle(Taal.getString("nogSpelersToevoegen"));
		huidigeStage.setScene(new Scene(sInvoer));
    }
}

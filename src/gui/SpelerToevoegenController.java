package gui;

import java.io.IOException;
import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import resources.Taal;
/**
 * klasse voor het Speler toevoegen scherm, dit wordt getoond wanneer de gebruiker een taal heeft gekozen of wanneer de gebruiker kiest om nog een speler toe te voegen
 * @author Quinten
 *
 */
public class SpelerToevoegenController extends Pane
{
    @FXML
    private Button btnSpelerToevoegen;
    
    @FXML
    private TextField txfGeboortejaar;
    
    @FXML
    private Label lblGeboortejaar, lblGebruikersnaam, lblSpelersToevoegen;
    
    @FXML
    private TextField txfGebruikersnaam;
    
	private DomeinController dc;
	private Alert aFoutmelding;
	
	/**
	 * Constructor voor de SpelerToevoegenController, roept de FXMLLoader aan, set de controller en root en roept buildGUI() aan 
	 * @param dc, DomeinController
	 */
    public SpelerToevoegenController(DomeinController dc)
    {
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("SpelerToevoegen.fxml"));
    	loader.setController(this);
    	loader.setRoot(this);
    	try
    	{
    		this.dc = dc;
    		loader.load();
    	}
    	catch(IOException e)
    	{
    		System.out.println("SpelerToevoegenController.java");
    		System.out.println(Taal.getString("schermNietBeschikbaar"));
    		e.printStackTrace();
    	}
    	buildGUI();
    	
    }
    
    /**
     * zorgt ervoor dat de juiste tekst wordt getoond op de labels en de button
     */
    private void buildGUI() 
    {
		lblSpelersToevoegen.setText(Taal.getString("SpelersToevoegenTitel"));
		lblGebruikersnaam.setText(Taal.getString("gebruikersnaam"));
		lblGeboortejaar.setText(Taal.getString("geboorejaar"));
		btnSpelerToevoegen.setText(Taal.getString("btnToevoegen"));
		
	}


	@FXML
    private void btnSpelerToevoegen(ActionEvent event)
    {
    	try 
    	{
        	int geboortejaar;
        	String gebruikersnaam= txfGebruikersnaam.getText();
        	//kijken of string leeg is en gooi exception
        	if(txfGebruikersnaam.getText().isBlank() && txfGeboortejaar.getText().isBlank())
        		throw new IllegalArgumentException(Taal.getString("spelerNaamGeboortejaar"));
    
        	//geboortejaar
        	try {
        		geboortejaar = Integer.parseInt(txfGeboortejaar.getText());
        	}
        	catch (NumberFormatException e) 
        	{
        		throw new NumberFormatException(Taal.getString("voerGeheelGetalIn"));
        	}
    	
        	dc.voegSpelerToeAanSpel(gebruikersnaam, geboortejaar);
        	toonSchermNogEenSpelerToevoegen();
    	}
    	catch(Exception e)
    	{
    		aFoutmelding = new Alert(AlertType.ERROR);
    		aFoutmelding.setTitle(Taal.getString("geeftFout"));
    		aFoutmelding.setHeaderText(null); 
    		aFoutmelding.setContentText(e.getMessage()); //"Gebruikersnaam en Geboortejaar moeten ingevuld zijn!"
    		aFoutmelding.show();
    	}

    }
   
    
    private void toonSchermNogEenSpelerToevoegen()
    {
    	NogSpelersInvoerenSchermController nogSpelers = new NogSpelersInvoerenSchermController(dc);
		Scene huidigeScene = this.getScene();
		Stage huidigeStage = (Stage) huidigeScene.getWindow();
		huidigeStage.setTitle(Taal.getString("nogSpelersToevoegen"));
		huidigeStage.setScene(new Scene(nogSpelers));
    }

}//einde controller

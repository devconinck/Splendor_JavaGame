package gui;

import java.io.IOException;
import domein.DomeinController;
import dto.OntwikkelingskaartDTO;
import event.FichesEvent;
import event.OntKaartEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import resources.Taal;
/**
 * klasse om ontwikkelingskaarten te nemen, wordt geopend wanneer een speler op een van de ontwikkelingskaartImages klikt om een kaart te kopen
 * @author Quinten
 *
 */
public class NeemOntwikkelingskaartController extends Pane {
	
	private DomeinController dc;
	private OntwikkelingskaartDTO ontwikkelingskaartDTO;
	
    @FXML
    private Button btnKoop;
    
    @FXML
    private Button btnTerug;
    
    @FXML
    private ImageView ivKaart;
    
    /**
     * Constructor voor de NeemOntwikkelingskaartController, roept de FXMLLoader aan, set de controller en root, laadt de background en roept buildGUI(OntwikkelingskaartDTO ont) aan 
     * @param dc, DomeinController
     * @param ont, OntwikkelingskaartDTO, kaart die de speler wil kopen
     */
	public NeemOntwikkelingskaartController(DomeinController dc, OntwikkelingskaartDTO ont){
		
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("NeemOntwikkelingskaart.fxml"));
    	loader.setController(this);
    	loader.setRoot(this);
    	this.dc = dc;
    	this.ontwikkelingskaartDTO = ont;
    	this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream(("/images/Deck/background.jpg"))),BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT
    			, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    	this.setStyle("-fx-border-color: white; -fx-border-width: 1px;");
    	try
    	{
    		loader.load();
    	}
    	catch(IOException e)
    	{
    		System.out.println("NeemOntwikkelingskaartController.java");
    		System.out.println(Taal.getString("schermNietBeschikbaar"));
    		e.printStackTrace();
    	}
        	
    	buildGUI(ont);
	}
	
	/**
	 * buildGUI voor het koop ontwikkelingskaart scherm, toont de image van de kaart die de speler wil kopen 
	 * en roept de juiste methodes aan wanneer op de buttons wordt geklikt
	 * @param ont, OntwikkelingskaartDTO, kaart die de speler wil kopen
	 */
    private void buildGUI(OntwikkelingskaartDTO ont) {
    	Image image = new Image(getClass().getResourceAsStream(
    			String.format("/images/Kaarten/Ontwikkelingskaart/Niveau%s/%s", ont.niveau(), ont.naamImage())));
    	ivKaart.setImage(image);
    	btnTerug.setOnAction(e -> gaTerug());
    	btnKoop.setOnAction(e -> koopOntwikkelingskaart());
    }
    
    /**
     * gaTerug methode die de Stage sluit, wordt aangeroepen wanneer de speler op de terug knop klikt
     */
    void gaTerug() {
    	Stage OKStage = (Stage) this.getScene().getWindow();
    	OKStage.close();
    }
    
    /**
     * methode die wordt aangeroepen wanneer de speler voor de specifieke kaart op de KOOP knop klikt, roept de koop methode van de DomeinController aan
     * en vuurt het koop event af dat in het speloverzicht opgevangen kan worden, werpt een alert op als er iets misgaat
     */
    private void koopOntwikkelingskaart() {
    	try {
    	    dc.koopOntwikkelingskaart(ontwikkelingskaartDTO);
    	    OntKaartEvent oke = new OntKaartEvent(OntKaartEvent.KOOP_EVENT);
			fireEvent(oke);
    	    gaTerug();
    	}
    	catch(IllegalAccessException e) {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setTitle(e.getMessage());
    		a.setContentText("Neem voldoende fiches voor je een ontwikkelingskaart kan kopen!");
    		a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    		a.show();
     	}

    }
}

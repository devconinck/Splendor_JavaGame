package gui;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domein.DomeinController;
import domein.Fiche;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import resources.Taal;
/**
 * klasse voor scherm om Fiches terug te leggen wanneer de speler meer dan 10 fiches heeft
 * @author Quinten
 *
 */
public class LegFichesTerugController extends Pane {
	
	@FXML
	private VBox mainVbox;
	
	@FXML
	private Label lblInfo;
	
	@FXML
	private Label lblError;
	
	private DomeinController dc;
	
	private List<List<Fiche>> tempFicheLijst = Arrays.asList(
		    new ArrayList<>(),
		    new ArrayList<>(),
		    new ArrayList<>(),
		    new ArrayList<>(),
		    new ArrayList<>()
	);
	/**
	 * constructor voor de LegFichesTerugController, roept de FXMLLoader aan, set de controller en root, laadt de background en roept buildGUI() aan 
	 * @param dc, DomeinController
	 */
	public LegFichesTerugController(DomeinController dc) {
    	
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("LegFichesTerug.fxml"));
    	loader.setController(this);
    	loader.setRoot(this);
    	this.dc = dc;
    	this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream(("/images/Deck/background.jpg"))),BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT
    			, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    	this.setStyle("-fx-border-color: white; -fx-border-width: 1px;");
    	try
    	{
    		loader.load();
    	}
    	catch(IOException e)
    	{
    		System.out.println("LegFichesTerugController.java");
    		System.out.println(Taal.getString("schermNietBeschikbaar"));
    		e.printStackTrace();
    	}
       
    	buildGUI();	
    }
    
    /**
     * maakt hbox voor de twee fotos van de fiches en de labels allemaal correct te zetten
     * @param indexHuidigeEdelsteen, int, laat weten welke soort edelsteen het momenteel is
     * @return HBox, hbox die we toevoegen aan ons scherm en updaten
     */
	private HBox edelsteenComponent(int indexHuidigeEdelsteen) {
		HBox HboxPerFiche = new HBox();
		
		String fotoNaam = SpelOverzichtController.FOTO_FICHES_NAAM.get(indexHuidigeEdelsteen);
		
		Label lblAantalInv = new Label();
		int aantalHuidigeEdelsteen = dc.getHuidigeSpelerDTO().fiches().get(indexHuidigeEdelsteen).size();
		lblAantalInv.setText(String.valueOf(aantalHuidigeEdelsteen));
		lblAantalInv.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
		lblAantalInv.setTranslateX(-50);
		lblAantalInv.setTranslateY(30);
	
		
		Label lblAantalTerug = new Label();
		lblAantalTerug.setText("0");
		lblAantalTerug.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
		lblAantalTerug.setTranslateX(-50);
		lblAantalTerug.setTranslateY(30);
		
		for (int i = -1 ; i <= 1; i += 2) {
			final int temp = i;
			
			StackPane tempStackPane = new StackPane();
			Image tempImage = new Image(getClass().getResourceAsStream(String.format("/images/EdelsteenFiches/%s", fotoNaam)));
			ImageView tempImageView = new ImageView(tempImage);
			
			tempImageView.setPreserveRatio(true);
			tempImageView.setFitHeight(90);
			tempImageView.setFitWidth(120);
			tempImageView.setCursor(Cursor.HAND);
	
			
			tempImageView.setOnMouseClicked( e -> {
				// TODO mss
				List<Fiche> ficheLijstHuidigeEdelsteen = dc.getHuidigeSpelerDTO().fiches().get(indexHuidigeEdelsteen);
				
				try {
					if (temp > 0) {
						Fiche tempFiche = tempFicheLijst.get(indexHuidigeEdelsteen).remove(0);
						ficheLijstHuidigeEdelsteen.add(tempFiche);
					}
					else  {
						Fiche tempFiche = ficheLijstHuidigeEdelsteen.remove(0);
						tempFicheLijst.get(indexHuidigeEdelsteen).add(tempFiche);
					}
					
					String aantal1 = lblAantalInv.getText();
					aantal1 = String.valueOf((Integer.parseInt(aantal1) + temp));
					lblAantalInv.setText(aantal1);
					
					
					String aantal2 = lblAantalTerug.getText();
					aantal2 = String.valueOf((Integer.parseInt(aantal2) - temp));
					lblAantalTerug.setText(aantal2);
					
				} catch (IndexOutOfBoundsException exception) {
					lblError.setText("Je hebt geen fiches van dit type meer!");
				}
				
				totaalAantalFichesUpdate();
			});
			
			tempStackPane.getChildren().addAll(tempImageView, i == -1 ? lblAantalInv : lblAantalTerug);
			tempStackPane.setStyle("-fx-padding: 0 10");
			HboxPerFiche.getChildren().add(tempStackPane);
			HboxPerFiche.setStyle("-fx-padding:0 50 5 50;");
		}
		return HboxPerFiche;
	}
	
    /**
     * buildGUI voor het scherm, maakt de vbox aan met de images van de fiches, de knop om te bevestigen en de error text box en roept de juiste methodes aan
     */
    private void buildGUI() {
    	Label lblHeader = new Label();
    	lblHeader.setText("Druk op de edelsteenfiches die je wil terugleggen!");
    	lblHeader.setStyle("-fx-text-fill: white; -fx-font-size: 15px; -fx-padding: 5 0 15 0; -fx-font-weight: bold;");
    	
    	
    	mainVbox.getChildren().add(lblHeader);
    	
    	
    	for (int indexHuidigeEdelsteen = 0; indexHuidigeEdelsteen < SpelOverzichtController.FOTO_FICHES_NAAM.size(); indexHuidigeEdelsteen++) {
			mainVbox.getChildren().add(edelsteenComponent(indexHuidigeEdelsteen));
		}
    	
    	// Maak groter en align in het midden
    	lblInfo = new Label();
    	totaalAantalFichesUpdate();
    	lblInfo.setStyle("-fx-text-fill: white; -fx-font-size: 17px; -fx-padding: 5 0 0 0;");
    	
    	lblError = new Label();
    	lblError.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 15px;");
    	
    	Button legTerugButton = new Button();
    	legTerugButton.setText("Leg terug");
    	legTerugButton.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 20px; -fx-background-color: lightgreen; fx-background-radius: 15;");
    	HBox hboxKnop = new HBox(legTerugButton);
    	hboxKnop.setAlignment(Pos.CENTER);
    	hboxKnop.setStyle("-fx-padding: 5");
    	
    	legTerugButton.setOnMouseClicked( e -> {
    		int aantalEdelstenen = (int) dc.getHuidigeSpelerDTO().fiches().stream().flatMap(t -> t.stream()).count();
    		
    		if (aantalEdelstenen > 10 || aantalEdelstenen < 10) lblError.setText("Je moet exact 10 fiches overhouden!");
    		else {
    			dc.legFichesTerug(tempFicheLijst);
    			
    			Stage stage = (Stage) this.getScene().getWindow();
    		    stage.close();
    		};
    	});
    	
    	mainVbox.getChildren().addAll(lblInfo, lblError, hboxKnop);
    	
    		
    }
    
    /**
     * kijkt hoeveel fiches de speler zou hebben, dit aantal moet namelijk gelijk zijn aan 10 voor de speler het scherm kan sluiten
     */
    private void totaalAantalFichesUpdate() {
    	lblInfo.setText(String.format("Je hebt momenteel %d fiches.", (int) dc.getHuidigeSpelerDTO().fiches().stream().flatMap(t -> t.stream()).count()));
    }
        
   	}



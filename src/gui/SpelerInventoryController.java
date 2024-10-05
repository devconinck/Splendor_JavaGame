package gui;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.DomeinController;
import domein.Ontwikkelingskaart;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import resources.Taal;

/**
 * klasse voor de inventory van de speler, wordt getoond wanneer een speler op de INVENTORY button klikt om de inventory van de huidige speler te tonen
 * @author Quinten
 *
 */
public class SpelerInventoryController extends ScrollPane {

    private DomeinController dc;
    
    @FXML
    private ScrollPane SPRoot;
    
    @FXML
    private VBox vb;
     
    /**
     *Constructor voor de SpelerInventoryController, roept de FXMLLoader aan, set de controller en root, laadt de background en roept buildGUI() aan   
     * @param dc, DomeinController
     */
	public SpelerInventoryController(DomeinController dc) {
		
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("spelerInventory.fxml"));
    	loader.setController(this);
    	loader.setRoot(this);
    	this.dc = dc;
    	try
    	{
    		loader.load();
    	}
    	catch(IOException e)
    	{
    		System.out.println("SpelerInventoryController.java");
    		System.out.println(Taal.getString("Speler inventory kan niet getoond wroden"));
    		e.printStackTrace();
    	}
    	
    	buildGUI();
	}
	
	/**
	 * buildGUI methode voor dit scherm, maakt een verticale ScrollPane aan met daarin telkens per niveau en voor de edele een label en een
	 * horizontale ScrollPane aan en toont in die horizontale ScrollPanes altijd de kaarten die de huidige speler reeds heeft gekocht
	 */
	private void buildGUI() 
	{
		
		List<Ontwikkelingskaart> niveau1 = dc.getHuidigeSpelerDTO().ontwikkelingskaarten().stream().filter(kaart -> kaart.getNiveau() == 1).toList();
		List<Ontwikkelingskaart> niveau2 = dc.getHuidigeSpelerDTO().ontwikkelingskaarten().stream().filter(kaart -> kaart.getNiveau() == 2).toList();
		List<Ontwikkelingskaart> niveau3 = dc.getHuidigeSpelerDTO().ontwikkelingskaarten().stream().filter(kaart -> kaart.getNiveau() == 3).toList();
		
		int aantalKaarten1 = niveau1.size();
		int aantalKaarten2 = niveau2.size();
		int aantalKaarten3 = niveau3.size();
		int[] kaarten = {aantalKaarten1, aantalKaarten2, aantalKaarten3};
		
		
		
		List<List<Ontwikkelingskaart>> lijst = new ArrayList<>();
		lijst.add(niveau1);
		lijst.add(niveau2);
		lijst.add(niveau3);
		
		SPRoot.setContent(vb);
		SPRoot.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream(("/images/Deck/background.jpg"))),BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT
	    			, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		vb.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream(("/images/Deck/background.jpg"))),BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT
    			, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		Button btnClose = new Button("X");
		btnClose.setFont(Font.font("System", FontWeight.BOLD, 20));
		btnClose.setOnMouseClicked(e -> {
			Stage stage = (Stage) this.getScene().getWindow();
		    stage.close();
		});
		
		vb.getChildren().add(btnClose);
	
		//Niveaus 1 tot 3 kaarten
		for(int i = 1;i<=3;i++) {
			
			Label lbl = new Label("Kaarten niveau " + i);
			lbl.setFont(Font.font("System", FontWeight.BOLD, 20));
			lbl.setStyle("-fx-background-color: rgba(0,0,0,0);");
			
			ScrollPane spKaart = new ScrollPane();
			spKaart.setFitToWidth(true);			
			spKaart.setPrefWidth(1000);
			spKaart.setMinViewportHeight(250);
			spKaart.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream(("/images/Deck/background.jpg"))),BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT
	    			, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
			//setStyle("-fx-background-color: rgba(0,0,0,0);");
			spKaart.setPrefViewportWidth(500);
			spKaart.setStyle("-fx-border-color: black; -fx-border-width: 2px");
			//spKaart.setStyle("-fx-background-color: transparent");
			spKaart.setHbarPolicy(ScrollBarPolicy.ALWAYS);
			spKaart.setVbarPolicy(ScrollBarPolicy.NEVER);

			HBox hb = new HBox();
			hb.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream(("/images/Deck/background.jpg"))),BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT
	    			, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
			//hb.setStyle("-fx-background-color: rgba(0,0,0,0);");
			spKaart.setContent(hb);
			//hb.setStyle("-fx-background-color: transparent");
			
			for(int j = 0; j<kaarten[i-1]; j++) {
				
				ImageView imgKaart = new ImageView();
			
				imgKaart.setImage(new Image(getClass().getResourceAsStream(
						String.format("/images/Kaarten/Ontwikkelingskaart/Niveau%d/%s",i, lijst.get(i-1).get(j).getNaamImage()))));
				imgKaart.setFitHeight(250);
				imgKaart.setPreserveRatio(true);
				
				hb.getChildren().add(imgKaart);	
			}
			vb.getChildren().add(lbl);
			vb.getChildren().add(spKaart);
		}
		//Kaarten edelen
		Label lbl = new Label("Edelen ");
		lbl.setFont(Font.font("System", FontWeight.BOLD, 20));
		ScrollPane spKaart = new ScrollPane();
		spKaart.setPrefViewportHeight(250);
		spKaart.setPrefViewportWidth(500);
		spKaart.setStyle("-fx-border-color: black; -fx-border-width: 2px");
		spKaart.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		spKaart.setVbarPolicy(ScrollBarPolicy.NEVER);
		
		HBox hb = new HBox();
		spKaart.setContent(hb);

		int aantalEdelen = (int) dc.getEdelenHuidigeSpelerDTO().size();

		for(int j = 0; j<aantalEdelen; j++) {
			
			ImageView imgKaart = new ImageView();
		
			imgKaart.setImage(new Image(getClass().getResourceAsStream(
					String.format("/images/Kaarten/Edele/%s", dc.getEdelenHuidigeSpelerDTO().get(j).naamImage()))));		
			imgKaart.setFitHeight(250);
			imgKaart.setFitWidth(200);
			imgKaart.setPreserveRatio(true);
						
			hb.getChildren().add(imgKaart);
		}
		vb.getChildren().add(lbl);
		vb.getChildren().add(spKaart);
	}
}

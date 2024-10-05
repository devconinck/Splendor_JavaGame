package gui;

import java.io.IOException;


import event.FichesEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import domein.DomeinController;
import domein.Fiche;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import resources.Taal;
/**
 * klasse voor het scherm dat opengaat wanneer de speler fiches wil nemen
 * @author Quinten
 *
 */
public class NeemFichesController extends Pane {
	
	private DomeinController dc;
	
    @FXML
    private ImageView ivDiamanten, ivOnyxen, ivRobijnen, ivSaffieren, ivSmaragden;
    
    @FXML //labels voor aantallen tussen plus en min
    private Label lblAantalD, lblAantalO, lblAantalR, lblAantalSa,lblAantalSm;
    
    @FXML //labels voor aantallen naast fotos fiches
    private Label lblAantalDiamanten, lblAantalOnyxen, lblAantalRobijnen, lblAantalSaffieren,lblAantalSmaragden;
    
    @FXML
    private Label lblInfo;
    
    @FXML
    private Button btnKoop, btnClose;
    
    @FXML
    private Button btnMin1, btnMin2, btnMin3, btnMin4,btnMin5;
    
    @FXML
    private Button btnPlus1,btnPlus2, btnPlus3, btnPlus4,btnPlus5;
    
    // Totaal aantal fiches.
    private List<Integer> lijstFiches;
    
    /**
     * Constructor voor de NeemFichesController, roept de FXMLLoader aan, set de controller en root, laadt de background en roept buildGUI() aan 
     * @param dc, DomeinController
     */
    public NeemFichesController(DomeinController dc) {
    	
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("NeemFiches.fxml"));
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
    		System.out.println("NeemFichesController.java");
    		System.out.println(Taal.getString("schermNietBeschikbaar"));
    		e.printStackTrace();
    	}
        lijstFiches = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            lijstFiches.add(0);
        }
    	buildGUI();	
    }
    /**
     * buildGUI methode voor dit scherm, roept showAantalFiches() aan, zorgt voor de juiste acties als de gebruiker op de knoppen klikt
     */
    private void buildGUI() {

    	showAantalFiches();
    	btnKoop.setCursor(Cursor.HAND);
    	btnKoop.setOnAction(e -> koopFiches());
    	lblAantalSm.setStyle("-fx-text-fill: white ");
    	lblAantalD.setStyle("-fx-text-fill: white ");
    	lblAantalSa.setStyle("-fx-text-fill: white ");
    	lblAantalO.setStyle("-fx-text-fill: white ");
    	lblAantalR.setStyle("-fx-text-fill: white ");
    	lblInfo.setStyle("-fx-text-fill: white ");
		lblInfo.setFont(Font.font("System", FontWeight.BOLD, 25));
		btnClose.setOnAction(e -> {
			Stage stage = (Stage) this.getScene().getWindow();
		    stage.close();
		});
    }
    /**
     * Vermeerdert het label van de juiste soort edelsteenfiches met 1 als aan alle voorwaarden is voldaan en voegt 1 toe in lijstFiches
     * @param ficheLabel,Label van de soort fiches waar we op PLUS hebben geklikt
     * @param id, int, id dat we nodig hebben om te weten van welke soort we een edelsteenFiche willen toevoegen
     */
    private void vermeerderFiche(Label ficheLabel, int id) {
    	int aantalVanFiche = Integer.parseInt(ficheLabel.getText());
    	int totaalFiches = lijstFiches.stream().mapToInt(Integer::intValue).sum();
    	int verschillendeFiches = lijstFiches.stream().filter(aantalFiche -> aantalFiche != 0).toList().size();
    	   
    	if (totaalFiches == 2 && verschillendeFiches == 1) {
    		lblInfo.setText("Je mag maar maximum 2 fiches van hetzelfde type nemen.");
 			lblInfo.setStyle("-fx-font-weight: bold; -fx-text-fill: red");
    	}
    	else if (totaalFiches == 2 && aantalVanFiche == 1) {
    		lblInfo.setText("Je kan alleen 3 verschillende fiches nemen.");
 			lblInfo.setStyle("-fx-font-weight: bold; -fx-text-fill: red");
    	}
    	else if (totaalFiches == 3) {
    		lblInfo.setText("Je mag maar 3 fiches in totaal nemen!");
 			lblInfo.setStyle("-fx-font-weight: bold; -fx-text-fill: red");
    	}
    	else if(aantalVanFiche == 1 && dc.getFichesDTO().get(id).size() - 1 <= 2) {

    		lblInfo.setText("Er moeten minstens 2 fiches in voorraad blijven als je 2 fiches wil nemen.");
    		lblInfo.setStyle("-fx-font-weight: bold; -fx-text-fill: red");
    	}
    	else if (dc.getFichesDTO().get(id).size() == 0) {
    		lblInfo.setText("Deze fiches zijn op!");
    		lblInfo.setStyle("-fx-font-weight: bold; -fx-text-fill: red");
    	}
    	else {
    	    ficheLabel.setText(String.valueOf(aantalVanFiche + 1));
    	    ficheLabel.setStyle("-fx-text-fill: white ");
    	    lijstFiches.set(id,lijstFiches.get(id) + 1);
    	}
    }
    /**
     * Vermindert het label van de juiste soort edelsteenfiches met 1 als aan alle voorwaarden is voldaan en verwijdert er 1 in lijstFiches
     * @param ficheLabel,Label van de soort fiches waar we op MIN hebben geklikt
     * @param id, int, id dat we nodig hebben om te weten van welke soort we een edelsteenFiche willen verwijderen
     */
    private void verminderFiche(Label ficheLabel, int id) {
    	int aantalVanFiche = Integer.parseInt(ficheLabel.getText());
    	
    	if (aantalVanFiche == 0) {
    		lblInfo.setText("Aantal fiches mag niet negatief zijn!");
 			lblInfo.setStyle("-fx-font-weight: bold; -fx-text-fill: red");
    	} else {
    		ficheLabel.setText(String.valueOf(aantalVanFiche-1));
    		if (lijstFiches.get(id) != 0) lijstFiches.set(id, lijstFiches.get(id) - 1);    		
    	}
    }
    /**
     * methode die wordt uigevoerd wanneer we op de KOOP knop klikken, roept het legFichesTerug scherm aan als de speler >10 fiches heeft, 
     * anders verwijdert het de fiches uit het spel en voegt ze toe bij de speler en sluit dan het scherm af en vuurt het koopFiche event af
     */
    @FXML
    void koopFiches() {
    	dc.koopFiches(lijstFiches);

    	
    	if(dc.getHuidigeSpelerDTO().fiches().stream()
    			.flatMap(t -> t.stream())
    			.count() > 10) {
    		LegFichesTerugController lft = new LegFichesTerugController(dc);
	        Stage EdeleStage = new Stage();
	        EdeleStage.initStyle(StageStyle.UNDECORATED);
	        Scene sc = new Scene(lft);
	        EdeleStage.setScene(sc);
	        EdeleStage.setTitle("Leg fiches terug");
	        EdeleStage.showAndWait();    		
    	}
    	
    	Stage NFStage = (Stage) this.getScene().getWindow();
    	NFStage.close();
       	FichesEvent nce = new FichesEvent();
    	fireEvent(nce);
    }
    
    /**
     * roept de verminderFiche() methode aan voor het correcte label en id als wanneer de speler op een MIN button klikt
     * @param event, klik event op een van de min buttons
     */
    @FXML
    void neemWeg(ActionEvent event) {

    	Button btn = (Button) event.getSource();
    	String btnId = btn.getId();
    
    	  switch(btnId) {
          case "btnMin1" -> verminderFiche(lblAantalSm, 0);
          case "btnMin2" -> verminderFiche(lblAantalD, 1);
          case "btnMin3" -> verminderFiche(lblAantalSa, 2);
          case "btnMin4" -> verminderFiche(lblAantalO, 3);
          case "btnMin5" -> verminderFiche(lblAantalR, 4);
    	  }
    }
    
    /**
     * roept de vermeerderFiche() methode aan voor het correcte label en id als wanneer de speler op een PLUS button klikt
     * @param event, klik event op een van de plus buttons
     */
    @FXML
    void voegToe(ActionEvent event) {
    	
    	Button btn = (Button) event.getSource();
    	String btnId = btn.getId();
    	
    	  switch(btnId) {
          case "btnPlus1" -> vermeerderFiche(lblAantalSm, 0);
          case "btnPlus2" -> vermeerderFiche(lblAantalD, 1);
          case "btnPlus3" -> vermeerderFiche(lblAantalSa, 2);
          case "btnPlus4" -> vermeerderFiche(lblAantalO, 3);
          case "btnPlus5" -> vermeerderFiche(lblAantalR, 4);      
    	  }
    }
    
    /**
     * toont het correcte aantal fiches van elke soort en zet de tekst in het wit
     */
    private void showAantalFiches() {
    	lblAantalSmaragden.setText(String.format("%d", dc.getFichesDTO().get(0).size()));
    	lblAantalSmaragden.setStyle("-fx-text-fill: white ");
    	lblAantalDiamanten.setText(String.format("%d", dc.getFichesDTO().get(1).size()));
    	lblAantalDiamanten.setStyle("-fx-text-fill: white ");
    	lblAantalSaffieren.setText(String.format("%d", dc.getFichesDTO().get(2).size()));
    	lblAantalSaffieren.setStyle("-fx-text-fill: white ");
    	lblAantalOnyxen.setText(String.format("%d", dc.getFichesDTO().get(3).size()));
    	lblAantalOnyxen.setStyle("-fx-text-fill: white ");
    	lblAantalRobijnen.setText(String.format("%d", dc.getFichesDTO().get(4).size()));
    	lblAantalRobijnen.setStyle("-fx-text-fill: white ");
    	
    }

}
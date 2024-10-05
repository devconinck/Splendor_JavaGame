package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import domein.DomeinController;
import domein.Edele;
import dto.KaartDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import resources.Taal;
/**
 * klasse voor de controler van ons neem edelen scherm, dit scherm wordt getoond wanneer de speler genoeg bonussen heeft zodat de edele langskomt
 * @author Quinten
 *
 */
public class NeemEdeleController extends Pane {
	
	private DomeinController dc;
	private List<KaartDTO> edelen;
	
    @FXML
    private Button btnNeemEdele;
    
    @FXML
    private HBox hboxEdeleLijst;
    private ImageView selectedImageView;
    private KaartDTO selectedEdele;
    
    /**
     * Constructor voor de NeemEdeleController, roept de FXMLLoader aan, set de controller en root, laadt de background en roept buildGUI() aan 
     * @param dc, DomeinController
     * @param edelen, List van KaartDTO met de edelen die in het spel zitten
     */
	public NeemEdeleController(DomeinController dc, List<KaartDTO> edelen){
		
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("NeemEdele.fxml"));
    	loader.setController(this);
    	loader.setRoot(this);
    	this.dc = dc;
    	this.edelen = edelen;
    	this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream(("/images/Deck/background.jpg"))),BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT
    			, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
   
    	
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
        	
    	buildGUI();
	}
	
	/**
	 * buildGUI van dit scherm, toont de fotos van de Edelen en roept de methodes aan voor de buttons en images onclick
	 */
    private void buildGUI() {
    	List<ImageView> edelenFotos = new ArrayList<>();
    	
    	
    	for (KaartDTO edele : edelen) {
    		final ImageView  imgViewEdele = new ImageView(new Image(getClass().getResourceAsStream(String.format("/images/Kaarten/Edele/%s", edele.naamImage()))));
    		imgViewEdele.setPreserveRatio(true);
    		imgViewEdele.setFitWidth(200);
    		
    		imgViewEdele.setOnMouseClicked(e -> {
                if (selectedImageView != null) {
                    selectedImageView.setEffect(null); // Verwijder het effect van de vorige geselecteerde kaart
                }
         
                selectedImageView = imgViewEdele; // Zet de huidige image view als geselecteerde foto.
                selectedEdele = edele;
                
                DropShadow dropShadow = new DropShadow();
                dropShadow.setColor(Color.RED);
                dropShadow.setRadius(20);
                selectedImageView.setEffect(dropShadow); // Voeg de highlight toe
            });
    		
    		edelenFotos.add(imgViewEdele);
    	}
    	
    	hboxEdeleLijst.getChildren().addAll(edelenFotos);
 
    	btnNeemEdele.setOnAction(e -> {
    		if (selectedImageView == null) {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setHeaderText("ERROR: Geen edele geselecteerd");
    			alert.setContentText("Gelieve 1 edele te selecteren en opnieuw te proberen");
    			alert.showAndWait();
    		} else neemEdele();
    	});
    }

    /**
     * roept de methode neemEdele op in de DomeinController.
     */
    private void neemEdele() {
    	dc.neemEdele(selectedEdele);
    	
    	Stage EdeleStage = (Stage) this.getScene().getWindow();
    	EdeleStage.close();
    }
	

}

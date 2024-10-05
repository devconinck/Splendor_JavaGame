package gui;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import domein.DomeinController;
import domein.Spel;
import dto.KaartDTO;
import dto.OntwikkelingskaartDTO;
import event.OntKaartEvent;
import event.FichesEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import resources.Taal;

/**
 * klasse voor het speloverzicht, dit is ons spelbord waar alles zal gebeueren, dit toont alle informatie en laat de speler toe al zijn acties uit te voeren
 * @author Quinten
 *
 */
public class SpelOverzichtController extends BorderPane {
	
	private static final int MAX_AANTAL_RIJEN = 3;
    private static final int MAX_AANTAL_KOLOMMEN = 4;
    
    /**
     * De namen van de foto's van de verschillende fiches in een List van Strings
     */
    public static final List<String> FOTO_FICHES_NAAM = Arrays.asList("Smaragd.png","Diamant.png","Saffier.png","Onyx.png", "Robbijn.png");
    private static final List<Color> KLEUREN_FICHES = Arrays.asList(Color.GREEN,Color.GREY, Color.BLUE, Color.SADDLEBROWN, Color.RED, Color.GOLD);
    private static final String cssButton = "-fx-background-color:  rgba(0,0,0,0);\r\n "
    		+ "-fx-border-color: white;\r\n"
			+ "-fx-border-width: 2px;\r\n"
			+ "-fx-border-radius: 15px;\r\n"
			+ "-fx-text-fill: white;\r\n"
			+ "-fx-font-weight: bold;\r\n"
			+ "-fx-font-size: 25px;\r\n"
			+ "-fx-padding: 10 20 10 20;\r\n"
			+ "-fx-margin: 30 0 0  0";
    @FXML
    private BorderPane root_overzicht;
    @FXML
    private Button btnClose;
    private DomeinController dc;
    private HBox hbox_in_borderpane = new HBox();
    private static final int AANTAL_VERSCHILLENDE_FICHES = 5;
    
    /**
     * Constructor van de SpelOverzichtController, roept de buildGUI methode aan
     * @param dc, DomeinController
     */
    public SpelOverzichtController(DomeinController dc) {
    	this.dc = dc;
    	dc.startSpel();
    	buildGUI();
    }
    
    /**
     * buildGUI methode voor dit scherm, roept de FXMLLoader aan, set de controller en root, laadt de background en roept de correcte methodes aan
     * een rerender (die alles op het scherm verwijdert en dan opnieuw inlaadt met de nieuwe informatie), zorgt voor de CLOSE Button
     */
    private void buildGUI() {
    	
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("SpelOverzicht.fxml"));
    	loader.setController(this);
    	loader.setRoot(this);
    	this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream(("/images/Deck/background.jpg"))),BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT
    			, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
   
    	try {
    		
	    	loader.load();
	    	rerender();
	    	
	    	// hbox_borderpane laten zien
	    	root_overzicht.getChildren().add(hbox_in_borderpane);
	
	    	btnClose.setCursor(Cursor.HAND);
	    	btnClose.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream(("/images/Deck/background.jpg"))),BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT
	    			, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
	    	btnClose.setOnAction(e -> {
	    		Stage stage = (Stage) this.getScene().getWindow();
	    		stage.close();
	    	});

    	}
    	catch (IOException e) {
    		e.printStackTrace();
    		System.out.println("SpelOverzichtController.java");
    		System.out.println(Taal.getString("schermNietBeschikbaar"));
    	}
    }
    
    /**
     * roept de methodes aan om alle informatie te tonen, de spelerInfo, de Fiches en de knoppen, en de kaarten (zowel edelen als ontwikkelingskaarten)
     */
    public void rerender() {
    	// namen en scores spelers weergeven
    	showSpelersInfo();
    	//toon aantal edelsteenfiches
    	showAantalFichesPassknopInventoryknop();
    	// ontwikkelingskaarten  en edelen weergeven
    	showKaarten();
    }
    
    /**
     * toont de informatie van de speler: naam en score, per soort de fiches die de speler heeft en per soort de bonus die de speler heeft + hoeveel edelen
     */
    public void showSpelersInfo() {   
    	VBox vbox_players = new VBox();
    	vbox_players.setPrefSize(850,600);
    	vbox_players.setPadding(new Insets(10,0,10,0));

    	for (int spelerNr = 0; spelerNr < dc.getSpelersDTO().size(); spelerNr++) {
    		// vbox per speler
    		VBox vbox = new VBox();
    		vbox.setMinSize(550, 220);
    		vbox.setPrefSize(620, 250);
    		
    		// naam en score
    		vbox.getChildren().addAll(naamEnScoreSpeler(vbox, spelerNr));
    		
    		// gridpane voor fiches
    		GridPane grid_fiches = new GridPane();
    		grid_fiches.setStyle("-fx-border-color: white; -fx-border-width: 3px;");
    		for(int column=0 ; column<5 ; column++) {
    	        StackPane stack = new StackPane();
    	        stack.setMinSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
    	        stack.setPrefSize(110, 90);
    	        stack.setStyle("-fx-border-color: white; -fx-border-width: 1px;");
    	        stack.getChildren().addAll(spelerStackFichesLabel(spelerNr, column),
    	        		spelerStackFichesImage(column));
    	        stack.setAlignment(Pos.CENTER_LEFT);
                grid_fiches.add(stack, column, 0);
                grid_fiches.setAlignment(Pos.CENTER);
    		}
    		
    		// gridpane voor kaarten
    		GridPane grid_kaarten = new GridPane();
    		grid_kaarten.setStyle("-fx-border-color: white; -fx-border-width:2px;");
    		final int[] i = {0};
    		for(int column = 0; column < 6 ; column++) { 
    			StackPane stack = new StackPane();
    			stack.setMinSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                stack.setPrefSize(110, 90);
    			stack.setStyle("-fx-border-color: white; -fx-border-width: 2px;");
    			
    			// label aantal kaarten (ont + edele)
    			Label labelstack = spelerStackKaartenLabel(spelerNr, column, i);
    			
    			// kleur kaart
    			Rectangle rechthoek = new Rectangle(40,70, KLEUREN_FICHES.get(column));
    			stack.getChildren().addAll(labelstack,rechthoek);
    			grid_kaarten.add(stack, column, 0);
    			i[0]++;
    			labelstack.toFront();
    		}
    		
    		// alles showen
    		vbox.getChildren().addAll(grid_fiches, grid_kaarten);
    		vbox_players.getChildren().add(vbox);
    		vbox_players.setSpacing(3);
    		vbox_players.setPadding(new Insets(20,0,0,30));
       
    	}
    	hbox_in_borderpane.getChildren().add(vbox_players);
	}
    
    /**
     * maakt de hbox aan waarin de naam en de score van de speler komt
     * @param vbox, de vbox waaraan we de info moeten toevoegen 
     * @param spelerNr, hoeveelste speler in de lijst het is
     * @return HBox, de hbox die we dan toevoegen op het spelbord
     */
    private HBox naamEnScoreSpeler(VBox vbox, int spelerNr) {
    	// hbox voor naam en score speler
		HBox hbox = new HBox();
		hbox.setMinSize(550, 50);
		hbox.setPrefSize(620, 60);
		
		Label naam = new Label();
		Label score = new Label();
		Separator s = new Separator();
		s.setStyle("-fx-border-style: none;");
		s.setVisible(false);
		
		naam.setText(dc.getSpelersDTO().get(spelerNr).gebruikersnaam());
		naam.setStyle("-fx-text-fill: white ");
		naam.setFont(Font.font("System", FontWeight.BOLD, 25));
		if(dc.getSpelersDTO().get(spelerNr).equals(dc.getHuidigeSpelerDTO())) 
			naam.setStyle("-fx-font-weight: bold; -fx-text-fill: green");

		score.setText(String.format("%d", dc.getSpelersDTO().get(spelerNr).score()));
		score.setFont(Font.font("Verdana", FontWeight.BOLD, 28));
	  	score.setStyle("-fx-text-fill: white ");
		if(dc.getSpelersDTO().get(spelerNr).equals(dc.getHuidigeSpelerDTO()))
			score.setStyle("-fx-font-weight: bold; -fx-text-fill: green");
		
		s.setPrefWidth(Region.USE_COMPUTED_SIZE);
		HBox.setHgrow(s, Priority.ALWAYS);
		
		hbox.setPadding(new Insets(10,10,10,10));
		hbox.setStyle("-fx-border-color: white; -fx-border-width: 1px;");
		hbox.getChildren().addAll(naam,  s, score);
		return hbox;
    }
    
    /**
     * toont het aantal bonussen dat de speler heeft of het aantal edelen
     * @param spelerNr, de hoeveelste speler in de lijst dit is
     * @param column, de kolom die we op dit moment willen tonen, dit bepaalt de soort bonus of de edele
     * @param i, array die we overlopen, om index bij te houden voor de BONUS_TYPES
     * @return Label, het label, dus het aantal bonussen van een soort of het aantal edelen
     */
    private Label spelerStackKaartenLabel(int spelerNr, int column, int[] i) {
		Label labelstack = new Label();
		labelstack.setText(String.format("%d", 
                dc.getSpelersDTO().get(spelerNr).ontwikkelingskaarten().stream()
                    .filter(ontwikkelingskaart -> ontwikkelingskaart.getBonus() == (Spel.BONUS_TYPES.get(i[0])))
                    .count()));
		labelstack.setFont(Font.font("Verdana", FontWeight.BOLD, 22)); 
	  	labelstack.setStyle("-fx-text-fill: white ");
	  	
	  	if (column == 5) {
	  		labelstack.setText(String.format("%d", dc.getEdelenVanSpelerDTO(spelerNr).size()));
	  	}
	  	return labelstack;
    }
    
    /**
     * toont de image van een bepaalde soort fiche, afhankelijk van de kolom die we op dat moment tonen 
     * @param column, int die de kolom bepaalt die we op dat moment tonen
     * @return ImageView, de image van de fichesoort die we tonen
     */
    private ImageView spelerStackFichesImage(int column) {
        ImageView imagestack = new ImageView();
        imagestack.setImage(new Image(getClass().getResourceAsStream(
        		String.format("/images/EdelsteenFiches/%s",
        		FOTO_FICHES_NAAM.get(column)))));
        imagestack.setFitWidth(88);
        imagestack.setPreserveRatio(true);
    	return imagestack;
    }
    
    /**
     * toont hoeveel fiches van een bepaald type de speler heeft
     * @param spelerNr, de hoeveelste speler in de list waarvan we de info tonen
     * @param column, de kolom, deze bepaald van welke soort fiches we het aantal opvragen
     * @return Label, het aantal fiches dat de speler van die soort heeft
     */
    private Label spelerStackFichesLabel(int spelerNr, int column) {
        Label labelstack = new Label();
        labelstack.setText(String.format("%d", dc.getSpelersDTO().get(spelerNr).fiches().get(column).size()));
        labelstack.setFont(Font.font("System", FontWeight.BOLD, 20));
      	labelstack.setStyle("-fx-text-fill: white ");
        labelstack.setTranslateX(85);
    	return labelstack;
    }
    
    /**
     * voegt alle edelen en ontwikkelinsgkaarten images toe aan de hbox, vbox of gridpane die daarvoor is voorzien en voegt deze dan toe aan het scherm
     */
	private void showKaarten() {
		VBox vbox_ontkaarten_edelen = new VBox();
    	vbox_ontkaarten_edelen.setPrefSize(850,900);
    	
    	vbox_ontkaarten_edelen.setPadding(new Insets(20,20,0,0));
    	
    	HBox hbox_edelen = new HBox();
    	hbox_edelen.setMinHeight(200);
    	hbox_edelen.setPrefSize(850,200);
    	GridPane gridpanekaarten = new GridPane();
    	gridpanekaarten.setPadding(new Insets(20,10,10,10));
		
		// edelen
		for(int i=0 ; i<dc.getEdelenInSpelDTO().size() ; i++) {
			hbox_edelen.getChildren().add(imageEdelen(i));
		}
		
		// ontwikkelingskaarten
		for(int row=0 ; row<MAX_AANTAL_RIJEN ; row++) {
			stackOntwikkelingskaartenDeckFoto(gridpanekaarten, row);
            for(int column=0; column<MAX_AANTAL_KOLOMMEN ; column++) {
                imageOntwikkelingskaartenOverzicht(gridpanekaarten, row, column);
            }
        }
		HBox.setMargin(vbox_ontkaarten_edelen, new Insets(20,20,0,0));
		vbox_ontkaarten_edelen.toBack();
		vbox_ontkaarten_edelen.getChildren().addAll(hbox_edelen, gridpanekaarten);
		hbox_in_borderpane.getChildren().add(vbox_ontkaarten_edelen);
	}
	
	/**
	 * toont de images van de edelen die in het spel zitten
	 * @param i, de i'de edele die we wilen tonen
	 * @return ImageView van de edele die we dan toevoegen aan het scherm
	 */
	private ImageView imageEdelen(int i) {
		ImageView imageEdele = new ImageView();
		imageEdele.setImage(new Image(getClass().getResourceAsStream(
                String.format("/images/Kaarten/Edele/%s", dc.getEdelenInSpelDTO().get(i).naamImage()))));
        imageEdele.setFitWidth(170);
        imageEdele.setPreserveRatio(true);
		return imageEdele;
	}
	
	/**
	 * maakt de gridpane aan met alle ontwikkelingskaarten die zichtbaar mogen zijn en die de speler dus kan kopen
	 * @param gridpanekaarten, de GridPane waaraan we al onze images gaan toevoegen
	 * @param row, de rij die we op dat moment willen vullen
	 * @param column, de kolom die we op dat moment willen vullen
	 */
	private void imageOntwikkelingskaartenOverzicht(GridPane gridpanekaarten, int row, int column) {
		ImageView image = new ImageView();
		image.setFitWidth(140);
        image.setFitHeight(200);
        gridpanekaarten.add(image, column+1, row);
        GridPane.setMargin(image, new Insets(4,5,0,3));
        
        try {
        	 image.setImage(new Image(getClass().getResourceAsStream(
                     String.format("/images/Kaarten/Ontwikkelingskaart/Niveau%s/%s",3-row,
                             dc.getOntwikkelingskaartenDTO().get(2-row).get(column).naamImage()))));
             image.setCursor(Cursor.HAND);
             final int finalrow = 2-row;
             final int finalcolumn = column;
             image.setOnMouseClicked(e -> {
             	NeemOntwikkelingskaart(dc.getOntwikkelingskaartenDTO().get(finalrow).get(finalcolumn));
             });
        } catch (IndexOutOfBoundsException e) {
        	// Makkelijkste manier om het spel niet te doen crashen als er minder dan 4 kaarten op het bord liggen.
        }
       
	}
	
	/**
	 * maakt een nieuwe NeemOntwikkelingskaartController aan en een nieuwe Stage en toont dit dan wanneer de speler op een van de ontwikkelingskaarten
	 * klikt om deze te kopen
	 * @param ont, de OntwikkelingskaartDTO van de kaart die de speler wil kopen
	 */
	public void NeemOntwikkelingskaart(OntwikkelingskaartDTO ont) {
        NeemOntwikkelingskaartController no = new NeemOntwikkelingskaartController(dc, ont);
        Stage OKStage = new Stage();
        OKStage.initStyle(StageStyle.UNDECORATED);
        OKStage.initModality(Modality.APPLICATION_MODAL);
        Scene sc = new Scene(no);
        OKStage.setScene(sc);
        OKStage.setTitle("Koop ontwikkelingskaart");
        OKStage.show();
        no.addEventHandler(OntKaartEvent.KOOP_EVENT, e -> actieGedaan());
	}
	
	/**
	 * toont de fotos van de Decks met het aantal kaarten dat nog in dat Deck zit
	 * @param gridpanekaarten, de GridPane waar we alle ontwikkelingskaarten die gekocht kunnen worden toevoegen en dus ook de fotos van de Decks
	 * @param row, de rij die we op dat moment tonen, dit bepaalt welk Deck het is en dus welke foto we moeten tonen
	 */
	private void stackOntwikkelingskaartenDeckFoto(GridPane gridpanekaarten, int row) {
        StackPane stack = new StackPane();
        Label label = new Label();
        ImageView imagestack = new ImageView();
        gridpanekaarten.add(stack, 0, row); // Syntax(Node, column, row)
        stack.getChildren().addAll(label,imagestack);
        imagestack.setImage(new Image(getClass().getResourceAsStream(
                String.format("/images/Deck/deck%s.png",3-row))));
        imagestack.setFitWidth(220);
        imagestack.setPreserveRatio(true);
        imagestack.setTranslateX(-30);
        label.setText(String.format("%d", dc.getOntwikkelingskaartenDTO().get(2-row).size() - 4 > 0 ? dc.getOntwikkelingskaartenDTO().get(2-row).size() - 4 : 0));
      	label.setStyle("-fx-text-fill: white ");
        label.setTextAlignment(TextAlignment.LEFT);
        label.setFont(Font.font("System", FontWeight.BOLD, 22));
        label.setTranslateX(-26);
        label.toFront();
	}
	
	/**
	 * Toont de PASS Button, de INVENTORY Button en de verschillende Fiches met aantallen nog in het spel en voegt deze aan het scherm toe
	 */
	public void showAantalFichesPassknopInventoryknop() {
		VBox vbox_fiches = new VBox();
		
		// knoppen boven fiches toevoegen
		VBox vbox_knoppen = new VBox();
		vbox_knoppen.setAlignment(Pos.CENTER);

		vbox_knoppen.getChildren().addAll(passButton(),inventoryButton());
		vbox_knoppen.setSpacing(5);
		vbox_fiches.getChildren().add(vbox_knoppen);
		VBox.setMargin(vbox_knoppen, new Insets(0,0,30,0));
		
		// fiches en aantallen in overzicht
		for(int i =0 ; i<AANTAL_VERSCHILLENDE_FICHES ; i++) {
			StackPane stack = new StackPane();
			stack.setPrefSize(150, 113);
            stack.setAlignment(Pos.BOTTOM_LEFT);

            // ImageView en Label toevoegen aan StackPane
            stack.getChildren().addAll(imagestackFichesOverzicht(i), labelFichesOverzicht(i));
            stack.setPadding(new Insets(10,10,10,10));
            vbox_fiches.getChildren().add(stack);
		}
		vbox_fiches.setPadding(new Insets(80,20,0,40));
		hbox_in_borderpane.getChildren().add(vbox_fiches);
	}
	
	/**
	 * maakt ImageView aan voor de soort Fiche die we willen tonen
	 * @param i, hoeveelste image we willen tonen, dit bepaald welke foto we moeten tonen
	 * @return ImageView van de fiche, deze voegen we dan toe aan het scherm
	 */
	private ImageView imagestackFichesOverzicht(int i) {
        ImageView imagestack = new ImageView();
        imagestack.setFitWidth(150);
        imagestack.setPreserveRatio(true);
        imagestack.setCursor(Cursor.HAND);
        imagestack.setImage(new Image(getClass().getResourceAsStream(
                String.format("/images/EdelsteenFiches/%s", FOTO_FICHES_NAAM.get(i)))));
        imagestack.setOnMouseClicked(e -> {neemFiches();});
        return imagestack;
	}
	
	/**
	 * methode die wordt aangeroepen wanneer een speler op een van de images van de fiches klikt, dit maakt dan een NeemFichesController aan 
	 * en maakt een nieuwe stage aan waarop dit wordt getoond
	 */
    private void neemFiches() {
        NeemFichesController nf = new NeemFichesController(dc);
        Stage secondaryStage = new Stage();
        Scene sc = new Scene(nf);
        secondaryStage.initStyle(StageStyle.UNDECORATED);
        secondaryStage.initModality(Modality.APPLICATION_MODAL);
        secondaryStage.setScene(sc);
        secondaryStage.setTitle("Kies een aantal edelsteenfiches");
        secondaryStage.show();
        
        nf.addEventHandler(FichesEvent.NEEM_EVENT_TYPE, e -> actieGedaan());
    }
    
	/**
	 * toont het aantal fiches die in het spel zitten per soort
	 * @param i, hiermee bepalen we de soort Fiches waarvan we het aantal willen tonen
	 * @return Label, aantal Fiches van bepaalde soort die we dan toevoegen aan het scherm
	 */
	private Label labelFichesOverzicht(int i) {
		Label label = new Label();
		label.setText(String.format("%d", dc.getFichesDTO().get(i).size()));
		label.setFont(Font.font("System", FontWeight.BOLD, 25));
		label.setStyle("-fx-font-weight: bold; -fx-text-fill: white ");
		return label;
	}
	
	/**
	 * maakt de PASS Button aan en zorgt ervoor dat de juiste methode wordt aangeroepen onclick
	 * @return Button, de PASS Button die we dan toevoegen aan het scherm
	 */
	private Button passButton() {
		Button btnpass = new Button();
		btnpass.setStyle(cssButton);
		btnpass.setCursor(Cursor.HAND);
		btnpass.setText("Pass");
		btnpass.setOnAction(e -> actieGedaan());
		return btnpass;
	}
	
	/**
	 * maakt de INVENTORY Button aan en zorgt ervoor dat de juiste methode wordt aangeroepen onclick
	 * @return Button, de INVENTORY Button, die we dan toevoegen aan het scherm
	 */
	private Button inventoryButton() {
		Button btnInventory = new Button();
		btnInventory.setText("Inventory");
		btnInventory.setCursor(Cursor.HAND);
		//btnInventory.setFont(Font.font("System", FontWeight.BOLD, 20));
		btnInventory.setStyle(cssButton);
		
		btnInventory.setOnAction(e -> openInventorySpeler());
		return btnInventory;
	}
	
	/**
	 * maakt een nieuwe SpelerInventoryController aan en maakt een stage aan om deze te tonen wanneer de speler op de INVENTORY Button klikt
	 */
	public void openInventorySpeler() {
		SpelerInventoryController sic = new SpelerInventoryController(dc);
        Stage SpelerInv = new Stage();
        Scene sc = new Scene(sic);
        SpelerInv.setScene(sc);
        SpelerInv.setTitle("Inventory speler");
        //SpelerInv.setWidth(760);
        SpelerInv.initStyle(StageStyle.UNDECORATED);
        SpelerInv.initModality(Modality.APPLICATION_MODAL);
        //SpelerInv.setResizable(false);
        SpelerInv.show();
        
	}
	
	/**
	 * methode die kijkt of de speler genoeg bonussen heeft zodat een van de edelen kunnen langskomen
	 * gaat over alle edelen en kijkt of de speler genoeg heeft om deze te krijgen, als alles klopt wordt het neemEdele scherm getoond
	 */
    private void neemEdele() { 
    	
    	List<KaartDTO> edelen = dc.getEdelenInSpelDTO();
    	List<Integer> bonus = dc.getHuidigeSpelerBonus();
    	List<KaartDTO> keuzeUitEdele = new ArrayList<>();
    	
    	for (int i = 0; i < edelen.size(); i++) {
			KaartDTO huidigeEdele = edelen.get(i);
			
			if (bonus.get(0) < huidigeEdele.smaragden() 
					|| bonus.get(1) < huidigeEdele.diamanten() 
					|| bonus.get(2) < huidigeEdele.saffieren() 
					|| bonus.get(3) < huidigeEdele.onyxen() 
					|| bonus.get(4) < huidigeEdele.robijnen()) {
				continue;
			} else keuzeUitEdele.add(huidigeEdele);
		}
    	
    	if (keuzeUitEdele.size() > 0) {
    		NeemEdeleController ne = new NeemEdeleController(dc, keuzeUitEdele);
	        Stage EdeleStage = new Stage();
	        EdeleStage.initStyle(StageStyle.UNDECORATED);
	        Scene sc = new Scene(ne);
	        EdeleStage.setScene(sc);
	        EdeleStage.setTitle("Neem edele");
	        EdeleStage.showAndWait();
    	}
    }
    
    /**
     * deze methode wordt aangeroepen waneer de speler een actie heeft gedaan(passen, fiches nemen of kaart kopen) en roept dan de neemEdele methode aan
     * die checkt of de speler genoeg bonussen heeft om een edele te krijgen, refresht het hele scherm en kijkt daarna of de ronde gedaan is om te kijken of er een winnaar is
     * als er een winnaar is wordt alles op deze stage op disabled gezet en wordt de winnaarstage aangemaakt om het overwinningscherm te tonen
     */
    public void actieGedaan() {
    	neemEdele();
    	
    	// refresh scherm
    	root_overzicht.getChildren().remove(hbox_in_borderpane);
    	this.hbox_in_borderpane= new HBox();
    	root_overzicht.getChildren().add(hbox_in_borderpane);
    	
    	if (dc.volgendeSpeler()) {
			if (dc.isEindeSpel()) {
				
				OverwinningSchermController winnaarScherm = new OverwinningSchermController(dc);
				
				// Haalt parent element op en zet het uit.
				Node root = this.getScene().getRoot();
				root.setDisable(true); 
						
		        Stage winnaarStage = new Stage();
		        Scene sc = new Scene(winnaarScherm); 
		        
		        winnaarStage.setScene(sc);
		        winnaarStage.setTitle("Gewonnen");
		        winnaarStage.show();
			}
		}
		rerender();	
    }
}
    
    
    

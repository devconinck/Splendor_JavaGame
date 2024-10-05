package main;
import domein.DomeinController;
import gui.SchermController;
import gui.TaalSchermController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * StartUp klasse voor de GUI, heeft enkel een start methode die het eerste scherm van de GUI aanroept
 * @author Quinten
 *
 */
public class StartUpGUI extends Application 
{
	/**
	 * start methode, maakt een nieuw TaalschermController aan en zet deze op de stage die de methode aanmaakt
	 * @param Stage de stage waarmee JavaFX start
	 */
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			SchermController root = new SchermController();
			Scene scene = new Scene(root);

			primaryStage.setTitle("Splendor");

			primaryStage.setScene(scene);
	
			primaryStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String... args) 
	{
		launch(args);	
	}
	
}
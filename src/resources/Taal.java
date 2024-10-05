package resources;

import java.util.Locale;
import java.util.ResourceBundle;
/**
 * klasse Taal die doorgeeft in welke taal alles moet worden weergegeven
 * @author Quinten
 *
 */
public class Taal {
	
	private static ResourceBundle huidigeTaal = ResourceBundle.getBundle("resources.taal", Locale.ROOT);
	
	/**
	 * zegt welke taal moete worden gekozen gebaseerd op de keuze van de gebruiker in het TaalScherm
	 * @param keuze, int die 1, 2 of 3 kan zijn, correspondeert met de vlag die de gebruiker kiest
	 */
	public static void setTaal(int keuze) {
		
		// Retourneert de juiste taal terug afhankelijk of je voor optie 1, 2 of 3 gekozen hebt
		String taalKeuze = switch(keuze) {
			case 1 -> "nl";
			case 2 -> "en";
		    case 3 -> "fr";
		    default -> throw new IllegalArgumentException(Taal.getString("taalNietGevonden"));
		};
		
		// De Locale.ROOT is de ResourceBundle die de Nederlandse vertalingen bevat.
		// Als de gebruiker Nederlands als taal wil, moet er niet verandert worden.
		// Anders wordt taal gelijk gestelt aan de nieuwe ResourceBundle met de gewenste
		// taal.
		if (taalKeuze != "nl")
			huidigeTaal = ResourceBundle.getBundle("resources.taal", new Locale(taalKeuze));
	}
	
	/**
	 * Haalt de juiste string in de juiste taal op a.d.h.v. de sleutel die als parameter wordt meegegeven.
	 * @param String De sleutel van de tekst die opgehaald moet worden als String
	 * @return De de tekst die opgehaald is in de juiste taal.
	 */
	public static String getString(String string) {
		return huidigeTaal.getString(string);
	}

}

package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Klasse dat de definitie van Ontwikkelingskaarten bevat
 * @author Arno
 *
 */
public class Ontwikkelingskaart extends Kaart {
	
	private int niveau;
	private String bonus;
	
	/**
	 * Constructor voor Ontwikkelingskaart object
	 * @param prestigePunten, aantal Presigepunten die de kaart kost
	 * @param smaragden, aantal SmaragdenFiches die de kaart kost
	 * @param diamanten, aantal DiamantenFiches die de kaart kost
	 * @param saffieren, aantal SaffierenFiches die de kaart kost
	 * @param onyxen, aantal OnyxenFiches die de ontwikkelingskaart kost
	 * @param robijnen, aantal RobijnenFiches die de ontwikkelingskaart kost
	 * @param naamImage, String die de naam van de image bevat
	 * @param niveau, niveau van de ontwikkelingskaart, kan 1, 2 of 3 zijn
	 */
	public Ontwikkelingskaart(int prestigePunten, String bonus, int smaragden, int diamanten,
			int saffieren, int onyxen, int robijnen, String naamImage, int niveau) 
	{
		super(prestigePunten, smaragden, diamanten, saffieren, onyxen, robijnen, naamImage);
		this.bonus = bonus;
		this.niveau = niveau;
	
	}
	
	/**
	 * vraagt het niveau van de kaart op
	 * @return int, 1, 2 of 3 
	 */
	public int getNiveau() {
		return niveau;
	}
	
	/**
	 * zet het kaart object om naar een String
	 * @return  String die de info over de kaart beschrijft
	 */
	@Override
	public String toString() {
		// Maakt een lijst van strings aan:
		List<String> strings = new ArrayList<String>(Arrays.asList(super.toString().split("\n")));
		/* [
		 * Ontwikkelingskaart,
		 *  PrestigePunten: 4,
		 *	Onyxen: 7,
		 *  Foto: ontwikkelingskaart313.PNG,
		 * ]
		 */
		
		
		// Voeg plaats de String in array
		strings.add(2, " Bonus: " + bonus + "\n");
		/* [
		 * Ontwikkelingskaart,
		 *  PrestigePunten: 4,
		 * 	Bonus: diamant, <-- Lijn ingevoegt
		 *	Onyxen: 7,
		 *  Foto: ontwikkelingskaart313.PNG,
		 * ]
		 */
		
		
		// Maak een nieuwe String
		StringBuilder result = new StringBuilder();
		for (String string : strings)
			// Voeg String toe aan result
			result.append(string);
		
		return result.toString();
	
		
	}
	
	/**
	 * vraagt de bonus van de kaart
	 * @return String, die soort van de kaart is
	 */
	@Override
	public String getBonus() {
		return bonus;
	}
}

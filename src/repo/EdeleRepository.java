package repo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domein.Edele;
/**
 * Klasse EdeleRepository, die alle edelen van het Spel aanmaakt en het juiste aantal dan aan Spel object doorgeeft
 * @author Quinten
 *
 */
public class EdeleRepository {

	private final static int AANTAL_EDELEN_TWEE_SPELERS = 3;
	private final static int AANTAL_EDELEN_DRIE_SPELERS = 4;
	private final static int AANTAL_EDELEN_VIER_SPELERS = 5;
	private List<Edele> edelen = new ArrayList<>();
	/**
	 * Constructor voor de edeleRepo, roept enkel de maakEdelen() methode aan
	 */
	public EdeleRepository() {
		maakEdelen();
	}
	/**
	 * maakt instanties van Edele aan voor alle edelen die in het Splendor spel voorkomen
	 */
	private void maakEdelen() {
		edelen.add(new Edele(3, 0,4,0,4,0,"edele1.PNG"));
		edelen.add(new Edele(3, 0,3,0,3,3,"edele2.PNG"));
		edelen.add(new Edele(3, 0,3,3,3,0,"edele3.PNG"));
		edelen.add(new Edele(3, 3,3,3,0,0,"edele4.PNG"));
		edelen.add(new Edele(3, 3,0,0,3,3,"edele5.PNG"));
		edelen.add(new Edele(3, 3,0,3,0,3,"edele6.PNG"));
		edelen.add(new Edele(3, 4,0,4,0,0,"edele7.PNG"));
		edelen.add(new Edele(3, 0,0,0,4,4,"edele8.PNG"));
		edelen.add(new Edele(3, 0,4,4,0,0,"edele9.PNG"));
		edelen.add(new Edele(3, 4,0,0,0,4,"edele10.PNG"));
	}
	/**
	 * geeft het correcte aantal edelen voor het aantal spelers dat het spel willen spelen terug volgens de domeinregels
	 * @param aantal_spelers, het aantal Spelers die aan het spel willen beginnen, bepalen hoeveel edelen er op het spelbord komen te liggen
	 * @return List met Edele objecten
	 */
	public List<Edele> getEdelen(int aantal_spelers) {
		List<Edele> edeleInSpel = new ArrayList<>();
		Collections.shuffle(edelen);
		switch(aantal_spelers) {
		case 2 -> edeleInSpel.addAll(edelen.subList(0, AANTAL_EDELEN_TWEE_SPELERS));
		case 3 -> edeleInSpel.addAll(edelen.subList(0, AANTAL_EDELEN_DRIE_SPELERS));
		default -> edeleInSpel.addAll(edelen.subList(0, AANTAL_EDELEN_VIER_SPELERS));
		};
		return edeleInSpel;
	}

}

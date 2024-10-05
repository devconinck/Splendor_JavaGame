package repo;

import java.util.ArrayList;
import java.util.List;

import domein.Fiche;
import util.FicheNaam;
/**
 * klasse EdelsteenfichesRepository die alle edelsteenfiches van het spel aanmaakt en het correcte aantal voor het aantal spelers terug kan geven 
 * @author Quinten
 *
 */
public class EdelsteenficheRepository {
	
	private final static int AANTAL_FICHES = 5;
	private final static int EDELSTENEN_VIER_SPELERS = 7;
	private final static int EDELSTENEN_DRIE_SPELERS = 5;
	private final static int EDELSTENEN_TWEE_SPELERS = 4;
	// lijsten met alle fiches
	private List<Fiche> smaragden = new ArrayList<>();
	private List<Fiche> diamanten = new ArrayList<>();
	private List<Fiche> saffieren = new ArrayList<>();
	private List<Fiche> onyxen = new ArrayList<>();
	private List<Fiche> robijnen = new ArrayList<>();
	/**
	 * constructor voor de edelsteenfichesrepository, roept enkel de methode maakEdelsteenFiches() aan
	 */
	public EdelsteenficheRepository() {
		maakEdelsteenFiches();
	}
	/**
	 * maakt 7 edelsteenfiches van elke soort aan en voegt deze toe aan de correcte Lijst van Fiche objecten volgens soort
	 */
	private void maakEdelsteenFiches() {
		
		for (int i = 0; i < 7; i++) {
			smaragden.add(new Fiche(FicheNaam.SMARAGDEN.name(), FicheNaam.SMARAGDEN.getKleur()));
			diamanten.add(new Fiche(FicheNaam.DIAMANTEN.name(), FicheNaam.DIAMANTEN.getKleur()));
			saffieren.add(new Fiche(FicheNaam.SAFFIEREN.name(), FicheNaam.SAFFIEREN.getKleur()));
			onyxen.add(new Fiche(FicheNaam.ONYXEN.name(), FicheNaam.ONYXEN.getKleur()));
			robijnen.add(new Fiche(FicheNaam.ROBIJNEN.name(), FicheNaam.ROBIJNEN.getKleur()));
		}
	}	
	/**
	 * geeft het aantalFiches dat volgens de domeinregels hoort bij het aantal spelers die het spel willen spelen
	 * @param aantalSpelers, int die bepaalt hoeveel fiches er in dit spel mogen zitten volgens de domeinregels
	 * @return List, gevuld met Lists van Fiche objecten, per soort in een List gestoken
	 */
	public List<List<Fiche>> getFiches(int aantalSpelers) {
		int aantalEdelstenen;
		switch(aantalSpelers) {
		case 2 -> aantalEdelstenen = EDELSTENEN_TWEE_SPELERS;
		case 3 -> aantalEdelstenen = EDELSTENEN_DRIE_SPELERS;
		default -> aantalEdelstenen = EDELSTENEN_VIER_SPELERS;
		};
		List<List<Fiche>> alleFiches = new ArrayList<List<Fiche>>();
		for(int i=0;i<AANTAL_FICHES;i++)
		{
			alleFiches.add(new ArrayList<Fiche>());
		}
		alleFiches.get(0).addAll(smaragden.subList(0, aantalEdelstenen));
		alleFiches.get(1).addAll(diamanten.subList(0, aantalEdelstenen));
		alleFiches.get(2).addAll(saffieren.subList(0, aantalEdelstenen));
		alleFiches.get(3).addAll(onyxen.subList(0, aantalEdelstenen));
		alleFiches.get(4).addAll(robijnen.subList(0, aantalEdelstenen));
		return alleFiches;
	}
	
}

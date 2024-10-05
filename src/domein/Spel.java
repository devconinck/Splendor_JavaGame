package domein;
import java.util.*;

import repo.EdeleRepository;
import repo.EdelsteenficheRepository;
import repo.OntwikkelingskaartRepository;

/**
* De klasse Spel modelleert een spel van het bordspel Splendor.
* Het bevat functionaliteiten voor het initialiseren van het spel,
* bepalen van de juiste volgorde van spelers, controleren of het spel ten einde is,
* bepalen van de winnaar(s), het toelaten om edele te nemen en ontwikkelingskaarten
* te kopen bij voldoende fiches.
*/
public class Spel {
	
	/**
	 * Minimum aantal spelers dat vereist is om te kunnen spelen.
	 */
	public static final int MIN_AANTAL_SPELERS = 2;
	
	/**
	 * Maximum aantal spelers dat kan meedoen aan een spel.
	 */
	public static final int MAX_AANTAL_SPELERS = 4;
	

	private final int WINNENDE_SCORE = 15;	
	private int huidigeSpeler = 0;
	
	private final EdelsteenficheRepository edelsteenficheRepo;
	private final EdeleRepository edeleRepo;
	private final OntwikkelingskaartRepository ontwikkelingskaartRepo;
	
	private List<Speler> spelers;
	private List<List<Fiche>> fichesInSpel;
	private List<Edele> edeleInSpel;
	private List<List<Ontwikkelingskaart>> ontkaartenInSpel;
	
	
	/**
	 * Bonustypes die het spel bevat.
	 */
	public static final List<String> BONUS_TYPES = Arrays.asList("smaragd", "diamant", "saffier", "onyx", "robijn", "");
	
	/**
	 * Constructor voor het maken van een nieuw Spel met repositories voor de spelers, edelsteenfiches, edelen en ontwikkelingskaarten.
	 * Deze methode vult echter de spelers, fiches, edele en ontwikkelingskaarten in Spel nog niet op.
	 */
	public Spel() {
		this.spelers = new ArrayList<Speler>();
		
		// initialiseren Repo's
		this.edelsteenficheRepo = new EdelsteenficheRepository();
		this.edeleRepo = new EdeleRepository();
		this.ontwikkelingskaartRepo = new OntwikkelingskaartRepository();
		
	}
	/**
	 * Vult de spelers, fiches, edele en ontwikkelingskaarten in Spel op door ze op te halen in hun overeenstemmende repo.
	 * De hoeveelheid fiches en edele wordt bepaald a.d.h.v. de paramater aantalSpelers. Ook de spelervolgorde wordt bepaald. 
	 * De sortering verloopt eerst van jong -> oud, dan op lengte van de naam en als laatste van Z -> A. Hiervoor roept
	 * initialiseerSpel de private methode bepaalSpelerVolgerde() aan.
	 *
	 * @param het aantal spelers als een int.
	 */
	public void initialiseerSpel(int aantalSpelers) {
		this.spelers = bepaalSpelerVolgorde(spelers);
		
		fichesInSpel = edelsteenficheRepo.getFiches(aantalSpelers);
	
		edeleInSpel = edeleRepo.getEdelen(aantalSpelers);
		ontkaartenInSpel = ontwikkelingskaartRepo.getOntwikkelingskaarten();
	}
	/**
	 * Controleert of een speler een score heeft die groter is de winnende score
	 * @return een boolean, true als het spel ten einde is.
	 */
	public boolean isEindeSpel() {
		boolean einde = false;
		for(Speler deelnemer : spelers) {
			if(deelnemer.getScore() >= WINNENDE_SCORE)
				einde = true;
				break;
		}
		return einde;
	}
	
	/**
	 * Bepaalt de juiste volgorde van de spelers die in het spel meedoen. De sortering verloopt 
	 * eerst van jong -> oud, dan op lengte van de naam en als laatste van Z -> A.
	 * 
	 * @param spelers de lijst van Speler objecten die aan het spel deelnemen
	 * @return een lijst met spelers, gesorteerd in de juiste volgorde.
	 */
	private List<Speler> bepaalSpelerVolgorde (List<Speler> spelers) {
		Collections.sort(spelers, 
				Comparator.comparing(Speler::getGeboortejaar)
		        .thenComparingInt(speler -> speler.getNaam().length()).reversed()
		        .thenComparing(Comparator.comparing(Speler::getNaam).reversed()));
		return spelers;
		
	}
	/**
	 * Bepaald de winnaars van het spel. De hoogste score wordt bepaald, indien er meerdere spelers zijn met de maximum score, 
	 * kijkt men naar de hoeveelheid ontwikkelingskaarten de speler bezit. De speler met het meeste ontwikkelingskaarten is dan
	 * de winnaar. Zijn er meerdere spelers met de maximum score en evenveel ontwikkelingskaarten, dan zijn er meerdere winnaars.
	 * Deze methode wordt aangeroepen op het einde van het spel.
	 * 
	 * @return een lijst met Speler objecten, deze spelers zijn de winnaars
	 * @throws NoSuchElementException indien er nog geen spelers in spel zitten.
	 */
	public List<Speler> bepaalWinnaars() {
		// Bij gelijk aantal prestigepunten wint de speler met het minste aantal ontwikkelingskaarten in zijn bezit 
		// Bij een gelijk aantal prestigepunten en aantal ontwikkelingskaarten delen de  betrokken spelers de overwinning 
		
		// Als er geen max is crash.
		int maxScore = spelers.stream().max(Comparator.comparing(Speler::getScore)).orElseThrow().getScore();
		
		List<Speler> spelersMetGelijkeScore = spelers.stream().filter( speler -> speler.getScore() == maxScore).toList();
		
		// Als er geen max is crash. Dus voeg check toe voor kaarten
		int maxAantalKaarten = spelersMetGelijkeScore.stream().mapToInt(speler -> speler.getOntwikkelingskaarten().size()).max().orElseThrow();
		
		// winnaars
		return spelersMetGelijkeScore.stream().filter( speler -> speler.getOntwikkelingskaarten().size() == maxAantalKaarten).toList();
	}
	/**
	 * Geeft de edelen die in de huidige instantie van Spel op het bord liggen.
	 * @return een lijst met de edelen die in het spel zitten
	 */
	public List<Edele> getEdeleInSpel() {
		return edeleInSpel;
	}
	/**
	 * Geeft de edelsteenfiches die in de huidige instantie van Spel op het bord liggen
	 * @return een lijst met daarin voor elke soort fiches een lijst met aantallen in het spel op dat moment
	 */
	public List<List<Fiche>> getFichesInSpel() {
		return fichesInSpel;
	}
	/**
	 * Geeft de spelers in het huidige spel terug.
	 * @return lijst met de spelers die het spel spelen
	 */
	public List<Speler> getSpelersInSpel() {
		return spelers;
	}
	/**
	 * Geeft de ontwikkelingskaarten terug die op dat moment in het spel zitten. Op index 0 zitten de kaarten van niveau 1,
	 * op index 1 de kaarten van niveau 2, op index 2 de kaarten van niveau 3.
	 * 
	 * @return lijst met daarin voor elk niveau van kaarten een lijst met de kaarten op dat moment nog in het spel
	 */
	public List<List<Ontwikkelingskaart>> getOntKaartenInSpel() {
		return ontkaartenInSpel;
	}	
	
	/**
	 * Bepaald de volgende speler en bepaald of het het begin is van een nieuwe ronde.
	 * @return Retourneert true als er een nieuwe ronde start, false in alle andere gevallen.
	 */
	public boolean volgendeSpeler() {
		huidigeSpeler = (++huidigeSpeler) % spelers.size();
		return huidigeSpeler == 0;
	}
	
	/**
	 * @return Geeft de speler terug die momenteel aan de beurt is.
	 */
	public Speler getHuidigeSpeler() {
		return spelers.get(huidigeSpeler);
	}
	
	/**
	 * Bepaald de daadwerkelijke kost van een Ontwikkelingskaart bij een Speler.
	 * De methode berekent het verschil tussen de kost van de kaart en de bonussen die de huidigeSpeler heeft.
	 * 
	 * @return een lijst van Integers waarbij elke Int overeenstemt met het aantal fiches die nodig zijn om de kaart te kopen. 
	 */
	private List<Integer> berekenDaadwerkelijkeOntwikkelingskaartKost(Ontwikkelingskaart kaart) {		
		// Haal kost kaart op
		List<Integer> kaartKost = kaart.getKost();
		
		// Haal bonus huidige speler op
		List<Integer> spelerBonus = getHuidigeSpeler().getBonus();
		
		List<Integer> kostenList = new ArrayList<>();
		 		
		for (int i = 0; i < fichesInSpel.size(); i++) {
			int daadwerkelijkeKost = kaartKost.get(i) - spelerBonus.get(i);
			kostenList.add(daadwerkelijkeKost < 0 ? 0 : daadwerkelijkeKost);
		}
		
		return kostenList;
	}
	
	
	/**
	 * Bepaald de daadwerkelijke kost van een Ontwikkelingskaart bij een Speler.
	 * De methode berekent het verschil tussen de kost van de kaart en de bonussen die de huidigeSpeler heeft.
	 * 
	 * @return een lijst van Integers waarbij elke Int overeenstemt met het aantal fiches die nodig zijn om de kaart te kopen. 
	 */
	public void koopFiches(List<Integer> lijstFiches) {
		for (int i = 0;i<5;i++) {
        	int aantalSoort = lijstFiches.get(i);
    
    		for (int j = 0;j<aantalSoort;j++) {
    			getHuidigeSpeler().getFiches().get(i).add(fichesInSpel.get(i).remove(j));
   			}	
    	}
	}
	
	/**
 	 * Zoekt de Ontwikkelingskaart in de lijst met Ontwikkelingskaarten in het Spel op basis van het niveau en de naam van de image
	 * 
	 * @param Ontwikkelingskaart die gezocht wordt
	 * @return een ontwikkelingskaart object van de gezochte kaart
	 * @throws NoSuchElementException Deze foutmelding treedt op als de ontwikkelingskaart niet gevonden wordt. 
	 * Dit kan in normale opstart van het spel nooit voorvallen.
	 */
	private Ontwikkelingskaart zoekOntwikkelingsKaart(Ontwikkelingskaart ontwikkelingskaart) {
		int niveau = ontwikkelingskaart.getNiveau();
		
		for(Ontwikkelingskaart deKaartInKwestie : ontkaartenInSpel.get(niveau-1)) {
			if(deKaartInKwestie.getNaamImage() == ontwikkelingskaart.getNaamImage()) return deKaartInKwestie;
			
			}
		throw new NoSuchElementException("Vertaling toevoegen, element niet gevonden blablabla");
	}
	
	
	/**
 	 * Voegt de fiches van de parameter toe aan het spel. 
 	 * Wordt gebruikt als de speler meer dan 10 fiches heeft.
	 * 
	 * @param een lijst met daarin meerdere lijsten met de fiche objecten per type.
	 */
	
	public void legFichesTerug(List<List<Fiche>> tempFicheLijst) {
		for (int i = 0; i < fichesInSpel.size(); i++) {
			for (int j = 0; j < tempFicheLijst.get(i).size(); j++) {
				fichesInSpel.get(i).add(tempFicheLijst.get(i).get(j));
			}
		}
	}

	
	/**
 	 * Zoekt de edele die de speler wilt nemen in de lijst van edele in het Spel op basis van de naam van de image
	 * 
	 * @param Edele die gezocht wordt
	 * @return een edele object van de gezochte kaart
	 * @throws NoSuchElementException Deze foutmelding treedt op als de ontwikkelingskaart niet gevonden wordt. 
	 * Dit kan in normale opstart van het spel nooit voorvallen.
	 */
    private Edele zoekEdele(Edele selectedEdele) {
    	for(Edele huidigeEdele : edeleInSpel) {
			if(huidigeEdele.getNaamImage() == selectedEdele.getNaamImage()) 
				return huidigeEdele;
			}
		throw new NoSuchElementException("Vertaling toevoegen, element niet gevonden blablabla");
	}
	
    
	/**
 	 * Methode die gebruikt wordt om een edele te nemen, de edele wordt uit het
 	 * spel weggehaald en aan de inventory van de speler toegevoegd.
	 * 
	 * @param Edele die de speler wil kopen
	 * @return een edele object van de gezochte kaart
	 */
	public void neemEdele(Edele selectedEdele) {
		Edele edele = zoekEdele(selectedEdele);
		getHuidigeSpeler().addKaart(edele);
		edeleInSpel.remove(edele);
	}
	
	/**
 	 * Wordt aangeroepen bij het kopen van de kaarten. De methode vraagt de fiches van de huidige speler op, en
 	 * gaat controleren of de speler genoeg fiches heeft om de kaart te kopen. De methode houdt ook rekening met 
 	 * de bonussen van de speler. De private methode zoekOntwikkelingsKaart wordt gebruikt om de kaart te zoeken
 	 * in het spel en de private methode berekenDaadwerkelijkeOntwikkelingskaartKost wordt gebruikt om de kost na 
 	 * bonus te berekenen. Als de speler genoeg fiches heeft wordt de kaart gekocht en anders een exceptie gegooid.
	 * 
	 * @param De ontwikkelingskaart die de speler wil kopen
	 * @throws IllegalAccessException Indien de speler niet genoeg fiches heeft.
	 */
	public void koopOntwikkelingskaart(Ontwikkelingskaart ontwikkelingskaart) throws IllegalAccessException {
		List<List<Fiche>> spelerFiches = getHuidigeSpeler().getFiches();
			
		Ontwikkelingskaart gezochteKaart = zoekOntwikkelingsKaart(ontwikkelingskaart);
		
		List<Integer> kostenList = berekenDaadwerkelijkeOntwikkelingskaartKost(gezochteKaart);
		
		List<List<Fiche>> spelerFichesCopy = spelerFiches;
		
		boolean genoegFiches = true;
		for (int i = 0; i < spelerFiches.size(); i++) {
			// Kost van de fiche
			int kost = kostenList.get(i);
			
			// Verschil speler - kost => #fiches die speler overhoudt na kopen 5 - 4 -> 4 fiches verwijderen
		    int diff = spelerFiches.get(i).size() - kost;
		    
		    if (diff < 0) {
		    	getHuidigeSpeler().setFiches(spelerFichesCopy);
		    	genoegFiches = false;
		    	break;
		    };

		    for (int j = 0; j < kost; j++) {
		    	Fiche fiche = spelerFiches.get(i).remove(0);
		    	
		    	// Fiche terug toevoegen aan het spel.
		    	fichesInSpel.get(i).add(fiche);
		    }
		}
		
		if (genoegFiches) {
			getHuidigeSpeler().addKaart(gezochteKaart);
			ontkaartenInSpel.get(gezochteKaart.getNiveau()-1).remove(gezochteKaart);
		} 
		else {
			throw new IllegalAccessException(String.format("Niet genoeg fiches"));
		}
		}
}


	

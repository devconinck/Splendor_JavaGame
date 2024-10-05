package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dto.FicheDTO;
import dto.KaartDTO;
import dto.OntwikkelingskaartDTO;
import dto.SpelerDTO;

import repo.SpelerRepository;
import resources.Taal;

/**
* De DomeinController klasse is de controller tussen de gebruikersinterface en de domeinlaag.
* Deze klasse is verantwoordelijk voor de aansturing van het spel en communiceert met andere klassen in de domeinlaag om het spelverloop te regelen.
* Deze klasse bevat methoden om een spel te starten, de huidige status van het spel op te vragen en DTO-objecten op te halen om te tonen in de gebruikersinterface. 
* Ook heeft het methoden om een speler toe te voegen aan het spel en de database te controleren of de speler al bestaat.
*/
public class DomeinController {

	private final SpelerRepository spelerRepo;

	private Spel spel;

	// Aanpassen in analyse
	/**
	 * Minimum aantal spelers dat vereist is om te kunnen spelen.
	 */
	public final int MIN_AANTAL_SPELERS = Spel.MIN_AANTAL_SPELERS;
	
	/**
	 * Maximum aantal spelers dat kan meedoen aan een spel.
	 */
	public final int MAX_AANTAL_SPELERS = Spel.MAX_AANTAL_SPELERS;

	
	/**
	 * Constructor van DomeinController.
	 * Maakt een nieuwe SpelerRepository en een nieuw Spel aan.
	 */
	public DomeinController() {
		this.spelerRepo = new SpelerRepository();
		
		this.spel = new Spel();
	}
	
	/**
	 * Start een nieuw spel.
	 * Initialiseert een nieuw spel met het aantal spelers dat op dat moment in het spel zit.
	 * 
	 * @throws IllegalArgumentException als er minder dan {@link #MIN_AANTAL_SPELERS} of meer dan {@link #MAX_AANTAL_SPELERS} spelers zijn.
	 */
	public void startSpel() {
		int aantalSpelers = spel.getSpelersInSpel().size();
		if (aantalSpelers > MAX_AANTAL_SPELERS || aantalSpelers < MIN_AANTAL_SPELERS) throw new IllegalArgumentException(Taal.getString("aantalGebruikers"));

		spel.initialiseerSpel(aantalSpelers);
		
	}

	/**
	 * Controleert of het spel is afgelopen.
	 * 
	 * @return true als het spel is afgelopen, anders false.
	 */
	public boolean isEindeSpel() {
		return spel.isEindeSpel();
	}
	
	
	/**
	 * Geeft de namen van de winnaars van het spel terug.
	 * Bepaalt de winnaars van het spel en geeft de namen van de winnaars terug in een String.
	 * 
	 * @return Een String met de namen van de winnaars.
	 */
	public String geefWinnaars() {
		// Geen zin in DTO
		List<Speler> winnaars = spel.bepaalWinnaars();
		String winnaarString = winnaars.stream().map(speler -> speler.getNaam()).collect(Collectors.joining(", "));
		return winnaarString;
	}
	
	
	/**
	 * Geeft een speler terug als hij gevonden is in de databank. Indien dit niet het geval is wordt er een error gegooid.
	 * 
	 * @param speler die men wil opzoeken in de databank.
	 * @return speler indien de gebruiker in de databank gevonden is.
	 * @throws IllegalArgumentException indien de gebruiker niet in de databank gevonden wordt.
	 */
	private Speler bestaatSpelerInDB(Speler speler) {
		for (Speler sp : spelerRepo.geefSpelersUitDB()) {
			if (speler.equals(sp))
				return sp;
			
		}
		throw new IllegalArgumentException(Taal.getString("gebruikerBestaatNiet"));
	}
	
	
	/**
	 * Roept de methode aan om te kijken of de speler in de databank zit. Vervolgens
	 * controleert de methode of de speler reeds in het spel zit. Als dit niet het geval
	 * is wordt hij toegevoegd aan het spel.
	 * 
	 * @param gebruikersnaam en geboortjejaar van de speler die je wil toevoegen.
	 * @throws IllegalArgumentException indien de gebruiker reeds in het spel zit.
	 */
	public void voegSpelerToeAanSpel(String gebruikersnaam, int geboortejaar) {
		Speler sp = bestaatSpelerInDB(new Speler(gebruikersnaam, geboortejaar));
		
		List<Speler> spelersInSpel = spel.getSpelersInSpel();
		//zorgen dat dezelfde speler geen tweede keer toegevoegd kan worden
		if (!(spelersInSpel.contains(sp))) {
			spelersInSpel.add(sp);
		}else 
			throw new IllegalArgumentException(Taal.getString("spelerReedsInSpel"));
	}
	
	/**
	* Geeft alle ontwikkelingskaarten in het spel terug als een lijst van lijsten van {@link OntwikkelingskaartDTO}.
	* Een lijst van {@link OntwikkelingskaartDTO} representeert alle ontwikkelingskaarten in een rij.
	* @return  De ontwikkelingskaarten in het spel als List van meerdere Lists van OntwikkelingskaartDTO;
	*/
	public List<List<OntwikkelingskaartDTO>> getOntwikkelingskaartenDTO() {
		List<List<OntwikkelingskaartDTO>> ontkaartenDTO = new ArrayList<List<OntwikkelingskaartDTO>>();

		for(List<Ontwikkelingskaart> rij : spel.getOntKaartenInSpel()) {
			List<OntwikkelingskaartDTO> ontkaartDTO = new ArrayList<OntwikkelingskaartDTO>();
			for(Ontwikkelingskaart o : rij) {
				ontkaartDTO.add(new OntwikkelingskaartDTO(o.getPrestigePunten(), o.getBonus(), o.getSmaragden(), o.getDiamanten(),
						o.getSaffieren(), o.getOnyxen(), o.getRobijnen(), o.getNaamImage(), o.getNiveau()));
			}
			ontkaartenDTO.add(ontkaartDTO);
		}
		return ontkaartenDTO;
	}
	
	/**
	* Geeft alle edelen in het spel terug als een lijst van {@link KaartDTO}.
	* @return List<KaartDTO> De edelen in het spel.
	*/
	public List<KaartDTO> getEdelenInSpelDTO() {
		List<KaartDTO> edelenDTOList = new ArrayList<>();
		for(Edele ed : spel.getEdeleInSpel()) {
			edelenDTOList.add(new KaartDTO(ed.getPrestigePunten(), ed.getSmaragden(), ed.getDiamanten(), ed.getSaffieren(), ed.getOnyxen(), ed.getRobijnen(), ed.getNaamImage()));
		}
		return edelenDTOList;
	}
	
	
	/**
	* Geeft alle edelen van de huidige speler terug als een lijst van {@link KaartDTO}.
	* @return List<KaartDTO> De edelen van de huidige speler.
	*/
	public List<KaartDTO> getEdelenHuidigeSpelerDTO() {
		List<KaartDTO> edelenDTOList = new ArrayList<>();
		for(Kaart ed : getHuidigeSpeler().getEdelen()) {
			edelenDTOList.add(new KaartDTO(ed.getPrestigePunten(), ed.getSmaragden(), ed.getDiamanten(), ed.getSaffieren(), ed.getOnyxen(), ed.getRobijnen(), ed.getNaamImage()));
		}
		return edelenDTOList;
	}
	
	/**
	* Geeft alle edelen van een speler terug als een lijst van {@link KaartDTO} op basis van zijn spelerNr.
	* 
	* @param spelerNr een integer die aanduidt van welke speler de Edelen Opgevraagt moeten worden.
	* @return List<KaartDTO> De edelen van de huidige speler.
	*/
	public List<KaartDTO> getEdelenVanSpelerDTO(int spelerNr) {
		List<KaartDTO> edelenDTOList = new ArrayList<>();
		for(Kaart ed : spel.getSpelersInSpel().get(spelerNr).getEdelen()) {
			edelenDTOList.add(new KaartDTO(ed.getPrestigePunten(), ed.getSmaragden(), ed.getDiamanten(), ed.getSaffieren(), ed.getOnyxen(), ed.getRobijnen(), ed.getNaamImage()));
		}
		return edelenDTOList;
	}
	
	

	/**
	* Geeft een lijst van integer bonussen terug van de huidige speler van het spel,
	* de index van de bonus stemt overeen met de BONUS_TYPES in {@link Spel}
	* @return List<Integer> lijst van integer bonussen van de huidige speler
	*/
	public List<Integer> getHuidigeSpelerBonus() {
		return spel.getHuidigeSpeler().getBonus();
	}
	
	/**
	* Geeft een lijst van lijsten van FicheDTO-objecten terug van het spel.
	* Het zet de fiches in het spel om naar zijn DTO voorstelling.
	* @return List<List<FicheDTO>> lijst van lijsten van FicheDTO-objecten van het spel
	*/
	public List<List<FicheDTO>> getFichesDTO() {
		List<List<FicheDTO>> allefichesDTO = new ArrayList<List<FicheDTO>>();
		
		for(List<Fiche> rij : spel.getFichesInSpel()) {
			List<FicheDTO> ficheDTO = new ArrayList<FicheDTO>();
			for(Fiche f : rij) {
				ficheDTO.add(new FicheDTO(f.getSoort(), f.getKleur()));
			}
			allefichesDTO.add(ficheDTO);
		}
		return allefichesDTO;
	}
	
	/**
	* Geeft de beurt door aan de volgende speler. Houdt ook de rondes bij.
	* 
	* @return boolean true als er een nieuwe ronde gestart is, anders false.
	*/
	public boolean volgendeSpeler() {
	return spel.volgendeSpeler();
	}
	
	/**
	* Geeft een Speler-object terug van de huidige speler van het spel.
	* @return geeft een Speler object van de huidige speler terug.
	*/
	private Speler getHuidigeSpeler() {
		return spel.getHuidigeSpeler();
	}
  
	
	/**
	* Geeft een SpelerDTO-object terug van de huidige speler van het spel.
	* Zet het speler object om naar een {@link SpelerDTO}
	* @return geeft een SpelerDTO object van de huidige speler terug.
	*/
	public SpelerDTO getHuidigeSpelerDTO() {
		return new SpelerDTO(spel.getHuidigeSpeler().getNaam(), spel.getHuidigeSpeler().getGeboortejaar(),
				spel.getHuidigeSpeler().getScore(), spel.getHuidigeSpeler().getFiches(),
				spel.getHuidigeSpeler().getOntwikkelingskaarten());
	}
	
	/**
	* Geeft een lijst van SpelerDTO-objecten terug die meedoen aan het spel.
	* Zet alle spelers binnen om naar een list van {@link SpelerDTO}.
	* 
	* @return geeft een List van SpelerDTO objecten terug.
	*/
	public List<SpelerDTO> getSpelersDTO() 
	{
		//Stap 1: array opvullen met geg
		List<Speler> array = spel.getSpelersInSpel();
		
		//Stap 2: nieuwe array aanmaken
		List<SpelerDTO> overzichtAlleSpelers = new ArrayList<>();
		
		//Stap 3
		for(Speler s : array)
		{
			overzichtAlleSpelers.add(new SpelerDTO(s.getNaam(), s.getGeboortejaar(), s.getScore(), s.getFiches(), s.getOntwikkelingskaarten()));
		}
		return overzichtAlleSpelers;
	}
	
	
	/**
	 * Methode die gebruikt wordt bij het nemen van fiches door de huidige speler.
	 * Het is een doorgeefluik naar de methode koopFiches in de klasse Speler
	 * 
	 * @param een lijst van integer genaamd lijstFiches
	 */
	public void koopFiches(List<Integer> lijstFiches)
	{
		spel.koopFiches(lijstFiches);
		
	}
	
	/**
	 * Methode die gebruikt wordt bij het terugleggen van fiches door de huidige speler.
	 * Het is een doorgeefluik naar de methode legFichesTerug in de klasse Speler
	 * 
	 * @param een lijst met daarin een lijst met Fiches
	 */
	public void legFichesTerug(List<List<Fiche>> tempFicheLijst) {
		spel.legFichesTerug(tempFicheLijst);
		
	}
	
	/**
	 * Methode die gebruikt wordt bij het kopen van een ontwikkelingskaart door de huidige speler.
	 * Het zet de {@link OntwikkelingskaartDTO} parameter terug om naar een {@link Ontwikkelingskaart} object
	 * en geeft dit door aan spel.
	 * 
	 * @param een {@link OntwikkelingskaartDTO} 
	 */
	public void koopOntwikkelingskaart(OntwikkelingskaartDTO kaart) throws IllegalAccessException {
		spel.koopOntwikkelingskaart(new Ontwikkelingskaart(
				kaart.prestigePunten(),
				kaart.bonus(),
				kaart.smaragden(),
				kaart.diamanten(),
				kaart.saffieren(),
				kaart.onyxen(),
				kaart.robijnen(),
				kaart.naamImage(),
				kaart.niveau()
				));
	}
	
	/**
	 * Methode die gebruikt wordt bij het nemen van een Edele door de huidige speler.
	 * Het zet de {@link KaartDTO} parameter terug om naar een {@link Edele} object
	 * en geeft dit door aan spel.
	 * 
	 * @param een {@link KaartDTO} 
	 */
	public void neemEdele(KaartDTO edele) {
		spel.neemEdele(new Edele(
				edele.prestigePunten(),
				edele.smaragden(),
				edele.diamanten(),
				edele.saffieren(), 
				edele.onyxen(),
				edele.robijnen(),
				edele.naamImage()));
	}
	
	}


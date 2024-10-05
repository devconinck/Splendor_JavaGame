package domein;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import resources.Taal;
/**
 * klasse voor Speler, hier wordt het Speler object aangemaakt
 * @author Quinten
 *
 */
public class Speler {
	
	private String gebruikersnaam;
	private int geboortejaar;
	private List<List<Fiche>> fiches;
	private List<Kaart> kaarten = new ArrayList<>();
	
	
	public final static int MINIMUM_LEEFTIJD = 6;
	public final static int MAXIMUM_LEEFTIJD = 100;
	/**
	 * Constructor voor Speler
	 * @param gebruikersnaam, String,  username van de speler
	 * @param geboortejaar, int, geboortejaar van de speler
	 */
	public Speler(String gebruikersnaam, int geboortejaar) {	
/*
		// For testing purposes
        kaarten.add(new Edele(3, 0,4,0,4,0,"edele1.PNG"));
        //Niveau 1 kaarten		
       
		kaarten.add(new Ontwikkelingskaart(0,"onyx",2,0,0,0,1,"ontwikkelingskaart123.PNG",1));
		kaarten.add(new Ontwikkelingskaart(0,"smaragd",0,0,1,2,2,"ontwikkelingskaart124.PNG",1));
		kaarten.add(new Ontwikkelingskaart(0,"saffier",0,0,0,3,0,"ontwikkelingskaart125.PNG",1));
		kaarten.add(new Ontwikkelingskaart(0,"robijn",1,2,1,1,0,"ontwikkelingskaart126.PNG",1));
		kaarten.add(new Ontwikkelingskaart(0,"smaragd",0,1,1,2,1,"ontwikkelingskaart127.PNG",1));
		kaarten.add(new Ontwikkelingskaart(0,"diamant",0,0,0,1,2,"ontwikkelingskaart128.PNG",1));
		kaarten.add(new Ontwikkelingskaart(0,"saffier",3,0,1,0,1,"ontwikkelingskaart129.PNG",1));
		kaarten.add(new Ontwikkelingskaart(0,"saffier",2,1,0,0,2,"ontwikkelingskaart130.PNG",1));
		kaarten.add(new Ontwikkelingskaart(1,"diamant",4,0,0,0,0,"ontwikkelingskaart131.PNG",1));
		kaarten.add(new Ontwikkelingskaart(1,"smaragd",0,0,0,4,0,"ontwikkelingskaart132.PNG",1));
		kaarten.add(new Ontwikkelingskaart(0,"smaragd",0,0,2,0,2,"ontwikkelingskaart133.PNG",1));
		kaarten.add(new Ontwikkelingskaart(0,"robijn",0,2,0,0,2,"ontwikkelingskaart134.PNG",1));
		kaarten.add(new Ontwikkelingskaart(1,"robijn",0,4,0,0,0,"ontwikkelingskaart135.PNG",1));
		kaarten.add(new Ontwikkelingskaart(0,"saffier",2,0,0,2,0,"ontwikkelingskaart136.PNG",1));
		kaarten.add(new Ontwikkelingskaart(0,"saffier",1,1,0,1,1,"ontwikkelingskaart137.PNG",1));
		

        //Niveau 2 kaarten
        kaarten.add(new Ontwikkelingskaart(2,"onyx",0,5,0,0,0,"ontwikkelingskaart21.PNG",2));
        kaarten.add(new Ontwikkelingskaart(1,"onyx",2,3,2,0,0,"ontwikkelingskaart22.PNG",2));

        //Niveau 3 kaarten
        kaarten.add(new Ontwikkelingskaart(4,"robijn",6,0,3,0,3,"ontwikkelingskaart31.PNG",3));
        kaarten.add(new Ontwikkelingskaart(5,"onyx",0,0,0,3,7,"ontwikkelingskaart32.PNG",3));
    	kaarten.add(new Ontwikkelingskaart(5,"robijn",7,0,0,0,3,"ontwikkelingskaart37.PNG",3));
*/		
        
        
		setGebruikersnaam(gebruikersnaam);
		setGeboortejaar(geboortejaar);
		fiches = initializeFiches();
		
	}
	
	/**
	 * berekent de score van de speler
	 * @return int, score die de speler heeft
	 */
	public int getScore() {
		return kaarten.stream().mapToInt(kaart -> kaart.getPrestigePunten()).sum();
	}
	
	/**
	 * vraagt de bonussen die de Speler heeft van elke soort
	 * @return List met Integers, deze stellen de bonussen voor die de speler heeft
	 */
	public List<Integer> getBonus() {
		List<Integer> bonussen = Spel.BONUS_TYPES.stream()
                .map(bonusType -> (int) getOntwikkelingskaarten().stream().filter(ontwikkelingskaart -> ontwikkelingskaart.getBonus().equals(bonusType)).count())
                .toList();
		
		return bonussen;
	}
	
	/**
	 * geeft naam van de speler
	 * @return String, de gebruikersnaam van de Speler
	 */
	public String getNaam() {																				
		return this.gebruikersnaam;
	}
	
	/**
	 * geeft geboortejaar van de speler
	 * @return int, geboortejaar van de Speler
	 */
	public int getGeboortejaar() {														
		return this.geboortejaar;
	}
	
	/**
	 * geeft de edelen die de speler heeft terug
	 * @return List, gevuld met Kaart objecten die de edelen van de speler voorstellen
	 */
	public List<Kaart> getEdelen() {
		List<Kaart> edelen = new ArrayList<>();
		for(Kaart k : kaarten) {
			if(k instanceof Edele)
				edelen.add(k);
		}
		return edelen;
	}
	
	/**
	 * geeft de fiches van de speler terug
	 * @return List, gevuld met voor elke soort een List van Fiche objecten
	 */
	public List<List<Fiche>> getFiches() {
		return fiches;
	}
	
	/**
	 * setter voor de fiches van de speler
	 * @param fiches, List, met voor elke soort fiches een List met Fiche objecten
	 */
	public void setFiches(List<List<Fiche>> fiches) {
		this.fiches = fiches;
	}
	
	/**
	 * vraagt de onwikkelingskaarten van de speler op
	 * @return List, gevuld met Ontwikkelingskaart objecten
	 */
	public List<Ontwikkelingskaart> getOntwikkelingskaarten() {
		List<Ontwikkelingskaart> ontwikkelingskaarten = new ArrayList<>();
		for (Kaart k : kaarten)
			if (k instanceof Ontwikkelingskaart)
				ontwikkelingskaarten.add((Ontwikkelingskaart) k);
		return ontwikkelingskaarten;
	}
	
	/**
	 * voegt kaart toe aan de lijst van de speler wanneer de speler deze koopt
	 * @param kaart, Kaart object die we bij de speler willen toevoegen
	 */
	public void addKaart(Kaart kaart) {
		kaarten.add(kaart);
	}
	
	/**
	 * setter voor de gebruikersnaam van onze Speler
	 * @param gebruikersnaam, String die de username voorstelt die de gebruiker kiest
	 */
	private void setGebruikersnaam(String gebruikersnaam) {
		// TODO naam moet uniek zijn mannen das onze primaire key!!!!
		// IDEE: Request maken naar databank en kijken of het opvragen van de gebruikersnaam een antwoord oplevert 
		
		// Controle gebruikersnaam leeg
		if(gebruikersnaam == null || gebruikersnaam.isBlank())
					throw new IllegalArgumentException(Taal.getString("gebruikersnaamIsLeeg"));				
		
		// Controle of gebruikersnaam enkel letters, cijfers, _ en " " bevat.
		// We kunnen dit later vervangen door regex
		for (int i = 0; i < gebruikersnaam.length(); i++) {
			char c = gebruikersnaam.charAt(i);
			
			if(!Character.isLetterOrDigit(c) && c != ' ' && c != '_')
				throw new IllegalArgumentException(Taal.getString("gebruikersnaamBevatIllegaalKarakter"));
		}
		
		// Controle gebruikersnaam lengte
		if(gebruikersnaam.length() > 20) 
			throw new IllegalArgumentException(Taal.getString("gebruikersnaamMax20Chars"));			
		
		// Controle eerste letter gebruikersnaam letter
		char eersteChar = gebruikersnaam.charAt(0);
		if(!Character.isLetter(eersteChar))
			throw new IllegalArgumentException(Taal.getString("gebruikersnaamStartMetLetter"));	
	
		this.gebruikersnaam = gebruikersnaam;
	}
	
	/**
	 * setter voor geboortejaar van de speler
	 * @param geboortejaar, int, geboortejaar van de speler
	 */
	private void setGeboortejaar(int geboortejaar) {
		// haalt het huidige jaar op
		int currentYear = Year.now().getValue();
		
		int leeftijd = currentYear - geboortejaar;
		
		// Controleert of leeftijd hoger of gelijk is aan MINIMUM_LEEFTIJD
		if (leeftijd < MINIMUM_LEEFTIJD)
			throw new IllegalArgumentException(Taal.getString("teJong")); 
		
		// Controleert of leeftijd lager of gelijk is aan MAXIMUM_LEEFTIJD
		if(leeftijd > MAXIMUM_LEEFTIJD)
			throw new IllegalArgumentException(Taal.getString("teOud"));							
		
		this.geboortejaar = geboortejaar;
	}
	
	/**
	 * initialiseert de lijst fiches, die voor elke soort van fiches een lege lijst bevat
	 * @return List, met voor elke soort fiches een lege List met Fiche objecten
	 */
	private List<List<Fiche>> initializeFiches() {
		List<List<Fiche>> fiches = new ArrayList<List<Fiche>>();
		for(int i=0; i<5 ; i++) {
			fiches.add(new ArrayList<Fiche>());
		}
		return fiches;
	}

	@Override
	public int hashCode() {
		return Objects.hash(geboortejaar, gebruikersnaam);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Speler other = (Speler) obj;
		return geboortejaar == other.geboortejaar && Objects.equals(gebruikersnaam, other.gebruikersnaam);
	}
	
}
	
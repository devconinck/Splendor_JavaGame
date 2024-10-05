package ui;

import java.util.*;

import domein.DomeinController;
import dto.KaartDTO;
import dto.OntwikkelingskaartDTO;
import dto.SpelerDTO;
import resources.Taal;

/**
* De klasse SplendorApplicatieCLI is verantwoordelijk voor het tonen van de command line interface.
* Deze klasse bevat de methoden om de taal van het spel te kiezen, spelers aan te maken, overzicht van de
* kaarten te laten zien, de startSpeler printen en de sortering te tonen.
*/
public class SplendorApplicatieCLI {

	private final DomeinController dc;
	
	Scanner input = new Scanner(System.in);
	
	
	/**
	 * Constructor die de DomeinController initialisatie.
	 * 
	 * @param dc object van de DomeinController klasse
	 */
	public SplendorApplicatieCLI(DomeinController dc) {
		this.dc = dc;
	}
	
	/**
	 * Deze methode start de command line interface.
	 */
	public void start() {
		
		// Haalt op welke taal de gebruiker wil gebruiken
		kiesTaal();
		
		// Toevoegen aan analyse
		for (int i = 1; i <= dc.MAX_AANTAL_SPELERS; i++) {
			// Menu alleen laten zien als er al minstens 2 spelers zijn
			if (i > dc.MIN_AANTAL_SPELERS) 
				if (keuzeMenu()) break;
			
			geefSpeler(i);
		}
		
		System.out.printf(Taal.getString("spelStarten"));
		dc.startSpel();
		printKaartenInSpel();
		toonSortering();
		
	}

	/**
	 * Deze methode print alle kaarten in het spel (edelen en ontwikkelingskaarten).
	 * Enkel ter controle en mag verwijderd worden in de toekomst.
	 */
	public void printKaartenInSpel() {
		System.out.println("***********************");
		System.out.println("*                     *");
		System.out.println("* Edelen in het spel: *");
		System.out.println("*                     *");
		System.out.println("***********************");
		for(KaartDTO edele : dc.getEdelenInSpelDTO()) {
			System.out.println(edele.toString());
		}
		
		System.out.println("**********************************************");
		System.out.println("*                                            *");
		System.out.println("* Ontwikkelingskaarten niveau 1 in het spel: *");
		System.out.println("*                                            *");
		System.out.println("**********************************************");
		for(OntwikkelingskaartDTO ontwikkelingskaart : dc.getOntwikkelingskaartenDTO().get(0))
			System.out.println(ontwikkelingskaart.toString());
		
		System.out.println("**********************************************");
		System.out.println("*                                            *");
		System.out.println("* Ontwikkelingskaarten niveau 2 in het spel: *");
		System.out.println("*                                            *");
		System.out.println("**********************************************");
		for(OntwikkelingskaartDTO ontwikkelingskaart : dc.getOntwikkelingskaartenDTO().get(1))
			System.out.println(ontwikkelingskaart.toString());
		
		System.out.println("**********************************************");
		System.out.println("*                                            *");
		System.out.println("* Ontwikkelingskaarten niveau 3 in het spel: *");
		System.out.println("*                                            *");
		System.out.println("**********************************************");
		for(OntwikkelingskaartDTO ontwikkelingskaart : dc.getOntwikkelingskaartenDTO().get(2))
			System.out.println(ontwikkelingskaart.toString());
	}

	
	/**
	* Deze methode toont het keuzemenu op het scherm en vraagt om een keuze van de gebruiker.
	* De gebruiker kan kiezen tussen twee opties: een spel starten of een speler toevoegen.
	* Als de gebruiker een getal ingeeft dat niet gelijk is aan 1 of 2, wordt een IllegalArgumentException gethrowd.
	* 
	* @return boolean Retourneert true als de gebruiker kiest om een spel te starten en false als de gebruiker kiest om een speler toe te voegen.
	* @throws IllegalArgumentException Wordt gegooid als de gebruiker een getal ingeeft dat niet gelijk is aan 1 of 2.
	*/
	private boolean keuzeMenu() {
		int keuze;
		do {
			
			// Opties weergeven:
			System.out.println(Taal.getString("opties"));
			System.out.println("1. " + Taal.getString("optieStartSpel"));
			System.out.println("2. " + Taal.getString("optieSpelerToevoegen"));
			
			// Nood aan try: -Niets ingegeven (of enter, spatie, blank etc.): PROBLEEM!
			//				 -Verkeerde type ingegeven bvb. String i.p.v. int: OK
			//				 -Niet binnen bereik [1, 2]: OK
    		try {
    			keuze = input.nextInt();
    			
    			if (keuze < 1 || keuze > 2) throw new IllegalArgumentException(Taal.getString("kiesGetalInInterval") + " [1,2]");
    			
    			input.nextLine();
    			
    			if (keuze == 1) return true;
    			else if (keuze == 2) return false;
    			
    			// Vangt foute types op
    		} catch (InputMismatchException e) {
    			System.out.println(Taal.getString("voerGeheelGetalIn"));
    			input.nextLine(); // Oneindige loop als er bvb String ingelezen wordt omdat Java dezelfde input opnieuw blijft inlezen. input.next() lost dit probleem op.
    			
    			// Vangt fouten op interval
    		} catch (IllegalArgumentException e) {
    			System.out.println(e.getMessage());
    			
    		}
    		
    	} while (true);
		
	}
	
	/**
	* Deze methode print alle spelers die in het spel zitten, en de startspeler dubbel.
	*/
	private void toonSortering()
	{
		int spelerTeller = 1;
		List<SpelerDTO> spelers = new ArrayList<>(dc.getSpelersDTO());
		
		for(SpelerDTO s : spelers) {
			System.out.printf("Speler %d met naam: %s en geboortejaar %d%n", spelerTeller++, s.gebruikersnaam(), s.geboortejaar());
		}
		
		//Startspeler
		String startSpeler = spelers.get(0).gebruikersnaam();
		System.out.printf("%nStartspeler: %s", startSpeler);
	}
	
	
	/**
	* Deze methode vraagt de gebruiker om een gebruikersnaam in te geven voor de speler en retourneert deze gebruikersnaam.
	* @param i Het nummer van de speler waarvan je de gebruikersnaam invult.
	* @return String Retourneert de ingegeven gebruikersnaam.
	*/
	private String geefGebruikersnaam(int i) {
		String gebruikersnaam;
		do {
			// Opties weergeven:
			System.out.printf(Taal.getString("geefSpelerGebruikersnaam"), i);
			System.out.println();
						
			// Nood aan try: -Niets ingegeven (of enter, spatie, blank etc.): PROBLEEM!
			//				 -Verkeerde type ingegeven bvb. int i.p.v. String: OK
    		try {
    			gebruikersnaam = input.nextLine();
    		
    			return gebruikersnaam;
    			
    			// Vangt foute types op
    		} catch (InputMismatchException e) {
    			System.out.println(Taal.getString("voerStringIn"));
    			input.nextLine(); // Oneindige loop als er bvb String ingelezen wordt omdat Java dezelfde input opnieuw blijft inlezen. input.next() lost dit probleem op.
    		} 
    	} while (true);
	}
	
	/**
	* Deze methode vraagt de gebruiker om een geboortejaar in te geven voor de speler en retourneert dit geboortejaar.
	* @param i Het nummer van de speler waarvan je het geboortejaar invult.
	* @return int Retourneert de ingegeven gebruikersnaam.
	*/
	private int geefGeboortejaar(int i) {
		int geboortejaar;
		do {
			
			// Opties weergeven:
			System.out.printf(Taal.getString("geefSpelerGeboortejaar"), i);
			System.out.println();
			
			// Nood aan try: -Niets ingegeven (of enter, spatie, blank etc.): PROBLEEM!
			//				 -Verkeerde type ingegeven bvb. int i.p.v. String: OK
    		try {
    			geboortejaar = Integer.parseInt(input.nextLine());
    		
    			return geboortejaar;
    			
    			// Vangt foute types op
    		} catch (InputMismatchException e) {
    			System.out.println(Taal.getString("voerStringIn"));
    			input.nextLine(); // Oneindige loop als er bvb String ingelezen wordt omdat Java dezelfde input opnieuw blijft inlezen. input.next() lost dit probleem op.
    		} catch (NumberFormatException e) {
    			System.out.println(Taal.getString("voerGeheelGetalIn"));
    			input.nextLine();
    		}
    		
    	} while (true);
	}
	
	/**
	* Roept geefSpelerGeboortjeaar en geefSpelerGebruikersnaam op.
	* Vraagt dus de gegevens van de spelers op.
	* 
	* @param i Het nummer van de huidige speler
	*/
	private void geefSpeler(int i) {
		String gebruikersnaam;
		int geboortejaar;
		
		do {
			// Nood aan try: -Niets ingegeven (of enter, spatie, blank etc.): PROBLEEM!
			//				 -Verkeerde type ingegeven bvb. int i.p.v. String: OK
    		try {
    			gebruikersnaam = geefGebruikersnaam(i);
    			
    			geboortejaar = geefGeboortejaar(i);
    				
    			dc.voegSpelerToeAanSpel(gebruikersnaam, geboortejaar);
    			
    			System.out.printf(Taal.getString("spelerToegevoegd"), i);
    			
    			break;
    			
    			// Vangt foute types op
    		} catch (IllegalArgumentException e) {
    			System.out.println(e.getMessage());
    		} 
    		
    	} while (true);
		
	}

	/**
	* Vraagt aan de gebruiker om een taal te kiezen en stelt deze in.
	*/
	private void kiesTaal() {
		int keuze;
		do {
			// Opties weergeven:
			System.out.println(Taal.getString("kiesTaal"));
			System.out.println("1. NL");
			System.out.println("2. EN");
			System.out.println("3. FR");
			
			// Nood aan try: -Niets ingegeven (of enter, spatie, blank etc.): PROBLEEM!
			//				 -Verkeerde type ingegeven bvb. String i.p.v. int: OK
			//				 -Niet binnen bereik [1, 3]: OK
    		try {
    			keuze = input.nextInt();
    			
    			if (keuze < 1 || keuze > 3) throw new IllegalArgumentException(Taal.getString("kiesGetalInInterval") + " [1,3]");
    			    			
    			Taal.setTaal(keuze);
    			
    			// Lijn clearen
    			input.nextLine();
    			
    			break;
    			
    			// Vangt foute types op
    		} catch (InputMismatchException e) {
    			System.out.println(Taal.getString("voerGeheelGetalIn"));
    			input.nextLine(); // Oneindige loop als er bvb String ingelezen wordt omdat Java dezelfde input opnieuw blijft inlezen. input.next() lost dit probleem op.
    			
    			// Vangt fouten op interval
    		} catch (IllegalArgumentException e) {
    			System.out.println(e.getMessage());
    			
    		}
    		
    	} while (true);
	}
}
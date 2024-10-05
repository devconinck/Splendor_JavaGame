package testen;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.DomeinController;
import domein.Fiche;
import domein.Ontwikkelingskaart;
import domein.Spel;
import domein.Speler;
import util.FicheNaam;

class SpelTest {
	
	private DomeinController dc;
	private Spel sp;
	
	@BeforeEach
	void init() {
		dc = new DomeinController();
		sp = new Spel();
	}

	
	/*
	 * 		Juiste startspeler
	 */
	
	@Test
	void startSpeler_Leeftijdsverschil() {
		dc.voegSpelerToeAanSpel("Quinten", 2002);
		dc.voegSpelerToeAanSpel("Tijn", 2004);
		dc.startSpel();
		Assertions.assertEquals(dc.getHuidigeSpelerDTO().gebruikersnaam(), "Tijn");
	}
	
	@Test
	void startSpeler_ZelfdeLeeftijd() {
		dc.voegSpelerToeAanSpel("Quinten", 2002);
		dc.voegSpelerToeAanSpel("Arno", 2002);
		dc.startSpel();
		Assertions.assertEquals(dc.getHuidigeSpelerDTO().gebruikersnaam(), "Quinten");
	}
	
	@Test
	void startSpeler_ZelfdeLeeftijd_EvenLangeNaam() {
		dc.voegSpelerToeAanSpel("Henk", 2002);
		dc.voegSpelerToeAanSpel("Arno", 2002);
		dc.startSpel();
		Assertions.assertEquals(dc.getHuidigeSpelerDTO().gebruikersnaam(), "Henk");
	}
	
	/*
	 * 		Juiste volgorde van spelers
	 */
	
	@Test
	void volgorde_Spelers_Correct() {
		dc.voegSpelerToeAanSpel("Henk", 2002);
		dc.voegSpelerToeAanSpel("Arno", 2002);
		dc.voegSpelerToeAanSpel("Brecht", 2002);
		dc.voegSpelerToeAanSpel("Quinten", 2002);
		dc.startSpel();
		Assertions.assertEquals(dc.getHuidigeSpelerDTO().gebruikersnaam(), "Quinten");
		dc.volgendeSpeler();
		Assertions.assertEquals(dc.getHuidigeSpelerDTO().gebruikersnaam(), "Brecht");
		dc.volgendeSpeler();
		Assertions.assertEquals(dc.getHuidigeSpelerDTO().gebruikersnaam(), "Henk");
		dc.volgendeSpeler();
		Assertions.assertEquals(dc.getHuidigeSpelerDTO().gebruikersnaam(), "Arno");
		dc.volgendeSpeler();
		Assertions.assertEquals(dc.getHuidigeSpelerDTO().gebruikersnaam(), "Quinten");
	}
	
	/*
	 * 		Winnaar(s) wordt bepaald
	 */
	
	@Test
	void winnaarCorrect_Enkele() {
		Spel spel = new Spel();
		Speler sp2 = new Speler("Arno", 2002);
		Speler sp1 = new Speler("Henk", 2002);
		sp1.addKaart(new Ontwikkelingskaart(0,"onyx",2,0,0,0,1,"ontwikkelingskaart123.PNG",1));
		sp1.addKaart(new Ontwikkelingskaart(2,"onyx",0,5,0,0,0,"ontwikkelingskaart21.PNG",2));
		sp1.addKaart(new Ontwikkelingskaart(4,"robijn",6,0,3,0,3,"ontwikkelingskaart31.PNG",3));
		sp1.addKaart(new Ontwikkelingskaart(5,"onyx",0,0,0,3,7,"ontwikkelingskaart32.PNG",3));
		sp1.addKaart(new Ontwikkelingskaart(5,"robijn",7,0,0,0,3,"ontwikkelingskaart37.PNG",3));
		spel.getSpelersInSpel().add(sp1);
		spel.getSpelersInSpel().add(sp2);
		Assertions.assertEquals("Henk", spel.bepaalWinnaars().stream().map(speler -> speler.getNaam())
															.collect(Collectors.joining(", ")));
	}
	
	@Test
	void winnaarsCorrect_Meerdere() {
		Spel spel = new Spel();
		Speler sp2 = new Speler("Arno", 2002);
		Speler sp1 = new Speler("Henk", 2002);
		sp1.addKaart(new Ontwikkelingskaart(0,"onyx",2,0,0,0,1,"ontwikkelingskaart123.PNG",1));
		sp1.addKaart(new Ontwikkelingskaart(2,"onyx",0,5,0,0,0,"ontwikkelingskaart21.PNG",2));
		sp1.addKaart(new Ontwikkelingskaart(4,"robijn",6,0,3,0,3,"ontwikkelingskaart31.PNG",3));
		sp1.addKaart(new Ontwikkelingskaart(5,"onyx",0,0,0,3,7,"ontwikkelingskaart32.PNG",3));
		sp1.addKaart(new Ontwikkelingskaart(5,"robijn",7,0,0,0,3,"ontwikkelingskaart37.PNG",3));
		sp2.addKaart(new Ontwikkelingskaart(0,"onyx",2,0,0,0,1,"ontwikkelingskaart123.PNG",1));
		sp2.addKaart(new Ontwikkelingskaart(2,"onyx",0,5,0,0,0,"ontwikkelingskaart21.PNG",2));
		sp2.addKaart(new Ontwikkelingskaart(4,"robijn",6,0,3,0,3,"ontwikkelingskaart31.PNG",3));
		sp2.addKaart(new Ontwikkelingskaart(5,"onyx",0,0,0,3,7,"ontwikkelingskaart32.PNG",3));
		sp2.addKaart(new Ontwikkelingskaart(5,"robijn",7,0,0,0,3,"ontwikkelingskaart37.PNG",3));
		spel.getSpelersInSpel().add(sp1);
		spel.getSpelersInSpel().add(sp2);
		Assertions.assertEquals("Henk, Arno", spel.bepaalWinnaars().stream().map(speler -> speler.getNaam())
															.collect(Collectors.joining(", ")));
	}
	
	/*
	 * 		Einde spel wordt bepaald
	 */
	
	@Test
	void eindeSpel_Niet() {
		Speler sp2 = new Speler("Arno", 2002);
		Speler sp1 = new Speler("Henk", 2002);
		sp1.addKaart(new Ontwikkelingskaart(5,"onyx",0,0,0,3,7,"ontwikkelingskaart32.PNG",3));
		sp1.addKaart(new Ontwikkelingskaart(5,"robijn",7,0,0,0,3,"ontwikkelingskaart37.PNG",3));
		sp.getSpelersInSpel().add(sp1);
		sp.getSpelersInSpel().add(sp2);
		Assertions.assertEquals(false , sp.isEindeSpel());
	}
	
	@Test
	void eindeSpel_JuistNiet() {
		Speler sp2 = new Speler("Arno", 2002);
		Speler sp1 = new Speler("Henk", 2002);
		sp1.addKaart(new Ontwikkelingskaart(4,"robijn",6,0,3,0,3,"ontwikkelingskaart31.PNG",3));
		sp1.addKaart(new Ontwikkelingskaart(5,"onyx",0,0,0,3,7,"ontwikkelingskaart32.PNG",3));
		sp1.addKaart(new Ontwikkelingskaart(5,"robijn",7,0,0,0,3,"ontwikkelingskaart37.PNG",3));
		sp.getSpelersInSpel().add(sp1);
		sp.getSpelersInSpel().add(sp2);
		Assertions.assertEquals(false , sp.isEindeSpel());
	}
	
	@Test
	void eindeSpel_JuistWel() {
		Spel spel = new Spel();
		Speler sp2 = new Speler("Arno", 2002);
		Speler sp1 = new Speler("Henk", 2002);
		sp1.addKaart(new Ontwikkelingskaart(1,"diamant",4,0,0,0,0,"ontwikkelingskaart131.PNG",1));
		sp1.addKaart(new Ontwikkelingskaart(4,"robijn",6,0,3,0,3,"ontwikkelingskaart31.PNG",3));
		sp1.addKaart(new Ontwikkelingskaart(5,"onyx",0,0,0,3,7,"ontwikkelingskaart32.PNG",3));
		sp1.addKaart(new Ontwikkelingskaart(5,"robijn",7,0,0,0,3,"ontwikkelingskaart37.PNG",3));
		spel.getSpelersInSpel().add(sp1);
		spel.getSpelersInSpel().add(sp2);
		Assertions.assertEquals(true , spel.isEindeSpel());
	}
	
	@Test
	void eindeSpel_Wel() {
		Speler sp2 = new Speler("Arno", 2002);
		Speler sp1 = new Speler("Henk", 2002);
		sp1.addKaart(new Ontwikkelingskaart(2,"onyx",0,5,0,0,0,"ontwikkelingskaart21.PNG",2));
		sp1.addKaart(new Ontwikkelingskaart(4,"robijn",6,0,3,0,3,"ontwikkelingskaart31.PNG",3));
		sp1.addKaart(new Ontwikkelingskaart(5,"onyx",0,0,0,3,7,"ontwikkelingskaart32.PNG",3));
		sp1.addKaart(new Ontwikkelingskaart(5,"robijn",7,0,0,0,3,"ontwikkelingskaart37.PNG",3));
		sp.getSpelersInSpel().add(sp1);
		sp.getSpelersInSpel().add(sp2);
		Assertions.assertEquals(true , sp.isEindeSpel());
	}
	
	/*
	 * 		Kaarten kunnen kopen
	 */
	
	@Test
	void spelerKaartKopen_Niet() {
		// spel aanmaken met kaart in kwestie
		Ontwikkelingskaart k = new Ontwikkelingskaart(3,"robijn",0,0,0,0,6,"ontwikkelingskaart230.PNG",2);
		sp.initialiseerSpel(2);
		sp.getOntKaartenInSpel().get(0).add(k);
		
		// speler toevoegen die kaart wil kopen
		Speler speler1 = new Speler("Arno", 2002);
		sp.getSpelersInSpel().add(speler1);
		
		// check
		Assertions.assertThrows(IllegalAccessException.class, () -> sp.koopOntwikkelingskaart(k));
	}
	
	@Test
	void spelerKaartKopen_NetNiet() {
		// spel aanmaken met kaart in kwestie
		Ontwikkelingskaart k = new Ontwikkelingskaart(3,"robijn",0,0,0,0,6,"ontwikkelingskaart230.PNG",2);
		sp.initialiseerSpel(2);
		sp.getOntKaartenInSpel().get(0).add(k);
		
		// speler toevoegen die kaart wil kopen
		Speler speler1 = new Speler("Arno", 2002);
		List<Fiche> fiches = Arrays.asList(new Fiche(FicheNaam.ROBIJNEN.name(), FicheNaam.ROBIJNEN.getKleur()),
				new Fiche(FicheNaam.ROBIJNEN.name(), FicheNaam.ROBIJNEN.getKleur()),
				new Fiche(FicheNaam.ROBIJNEN.name(), FicheNaam.ROBIJNEN.getKleur()),
				new Fiche(FicheNaam.ROBIJNEN.name(), FicheNaam.ROBIJNEN.getKleur()),
				new Fiche(FicheNaam.ROBIJNEN.name(), FicheNaam.ROBIJNEN.getKleur()));
		speler1.getFiches().get(4).addAll(fiches);
		sp.getSpelersInSpel().add(speler1);
		
		// check
		Assertions.assertThrows(IllegalAccessException.class, () -> sp.koopOntwikkelingskaart(k));
	}
	
	@Test
	void spelerKaartKopen_NetWel() throws IllegalAccessException {
		// spel aanmaken met kaart in kwestie
		Ontwikkelingskaart k = new Ontwikkelingskaart(3,"robijn",0,0,0,0,6,"ontwikkelingskaart230.PNG",2);
		sp.initialiseerSpel(2);
		sp.getOntKaartenInSpel().get(0).add(k);
		
		// speler toevoegen die kaart wil kopen
		Speler speler1 = new Speler("Arno", 2002);
		List<Fiche> fiches = Arrays.asList(new Fiche(FicheNaam.ROBIJNEN.name(), FicheNaam.ROBIJNEN.getKleur()),
				new Fiche(FicheNaam.ROBIJNEN.name(), FicheNaam.ROBIJNEN.getKleur()),
				new Fiche(FicheNaam.ROBIJNEN.name(), FicheNaam.ROBIJNEN.getKleur()),
				new Fiche(FicheNaam.ROBIJNEN.name(), FicheNaam.ROBIJNEN.getKleur()),
				new Fiche(FicheNaam.ROBIJNEN.name(), FicheNaam.ROBIJNEN.getKleur()),
				new Fiche(FicheNaam.ROBIJNEN.name(), FicheNaam.ROBIJNEN.getKleur()));
		speler1.getFiches().get(4).addAll(fiches);
		sp.getSpelersInSpel().add(speler1);
		sp.koopOntwikkelingskaart(k);
		
		// check
		Assertions.assertEquals(k.getNaamImage(), speler1.getOntwikkelingskaarten().get(0).getNaamImage());
	}
	
	@Test
	void spelerKaartKopen_Wel() throws IllegalAccessException {
		// spel aanmaken met 1 kaart
		Ontwikkelingskaart k = new Ontwikkelingskaart(3,"robijn",0,0,0,0,6,"ontwikkelingskaart230.PNG",2);
		sp.initialiseerSpel(2);
		sp.getOntKaartenInSpel().get(0).add(k);
		
		// speler toevoegen die kaart wil kopen
		Speler speler1 = new Speler("Arno", 2002);
		List<Fiche> fiches = Arrays.asList(new Fiche(FicheNaam.ROBIJNEN.name(), FicheNaam.ROBIJNEN.getKleur()),
				new Fiche(FicheNaam.ROBIJNEN.name(), FicheNaam.ROBIJNEN.getKleur()),
				new Fiche(FicheNaam.ROBIJNEN.name(), FicheNaam.ROBIJNEN.getKleur()),
				new Fiche(FicheNaam.ROBIJNEN.name(), FicheNaam.ROBIJNEN.getKleur()),
				new Fiche(FicheNaam.ROBIJNEN.name(), FicheNaam.ROBIJNEN.getKleur()),
				new Fiche(FicheNaam.ROBIJNEN.name(), FicheNaam.ROBIJNEN.getKleur()),
				new Fiche(FicheNaam.ROBIJNEN.name(), FicheNaam.ROBIJNEN.getKleur()));
		speler1.getFiches().get(4).addAll(fiches);
		sp.getSpelersInSpel().add(speler1);
		sp.koopOntwikkelingskaart(k);
		
		// check
		Assertions.assertEquals(k.getNaamImage(), speler1.getOntwikkelingskaarten().get(0).getNaamImage());
	}

}

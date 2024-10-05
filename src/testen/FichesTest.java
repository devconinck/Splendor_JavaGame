package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.DomeinController;

class FichesTest {

	private final static int AANTAL_FICHES = 5;
	private final static int EDELSTENEN_VIER_SPELERS = 7;
	private final static int EDELSTENEN_DRIE_SPELERS = 5;
	private final static int EDELSTENEN_TWEE_SPELERS = 4;
	private DomeinController dc;
	
	/*
	 * 		Correct aantal fiches in spel
	 */
	
	@BeforeEach
	void init() {
		dc = new DomeinController();
	}
	
	@Test
	void correctAantal_Fiches_TweeSpelers() {
		dc.voegSpelerToeAanSpel("Arno", 2002);
		dc.voegSpelerToeAanSpel("Quinten", 2002);
		dc.startSpel();
		for(int fiche=0; fiche<AANTAL_FICHES ; fiche++) {
			Assertions.assertEquals(EDELSTENEN_TWEE_SPELERS, dc.getFichesDTO().get(fiche).size());
		}
	}
	
	@Test
	void correctAantal_Fiches_DrieSpelers() {
		dc.voegSpelerToeAanSpel("Arno", 2002);
		dc.voegSpelerToeAanSpel("Quinten", 2002);
		dc.voegSpelerToeAanSpel("Tijn", 2004);
		dc.startSpel();
		for(int fiche=0; fiche<AANTAL_FICHES ; fiche++) {
			Assertions.assertEquals(EDELSTENEN_DRIE_SPELERS, dc.getFichesDTO().get(fiche).size());
		}
	}
	
	@Test
	void correctAantal_Fiches_VierSpelers() {
		dc.voegSpelerToeAanSpel("Arno", 2002);
		dc.voegSpelerToeAanSpel("Quinten", 2002);
		dc.voegSpelerToeAanSpel("Tijn", 2004);
		dc.voegSpelerToeAanSpel("Brecht", 2002);
		dc.startSpel();
		for(int fiche=0; fiche<AANTAL_FICHES ; fiche++) {
			Assertions.assertEquals(EDELSTENEN_VIER_SPELERS, dc.getFichesDTO().get(fiche).size());
		}
	}
	
	/*
	 * 		Naam van de soort van de fiches is correct + juiste volgorde in lijst
	 */
	
	@Test
	void correcteNaamKleurPlaats_Smaragden() {
		dc.voegSpelerToeAanSpel("Arno", 2002);
		dc.voegSpelerToeAanSpel("Quinten", 2002);
		dc.startSpel();
		Assertions.assertEquals("SMARAGDEN", dc.getFichesDTO().get(0).get(0).soort());
		Assertions.assertEquals("groen", dc.getFichesDTO().get(0).get(0).kleur());
	}
	
	@Test
	void correcteNaamKleurPlaats_Diamanten() {
		dc.voegSpelerToeAanSpel("Arno", 2002);
		dc.voegSpelerToeAanSpel("Quinten", 2002);
		dc.startSpel();
		Assertions.assertEquals("DIAMANTEN", dc.getFichesDTO().get(1).get(0).soort());
		Assertions.assertEquals("wit", dc.getFichesDTO().get(1).get(0).kleur());
	}
	
	@Test
	void correcteNaamKleurPlaats_Saffieren() {
		dc.voegSpelerToeAanSpel("Arno", 2002);
		dc.voegSpelerToeAanSpel("Quinten", 2002);
		dc.startSpel();
		Assertions.assertEquals("SAFFIEREN", dc.getFichesDTO().get(2).get(0).soort());
		Assertions.assertEquals("blauw", dc.getFichesDTO().get(2).get(0).kleur());
	}
	
	@Test
	void correcteNaamKleurPlaats_Onyxen() {
		dc.voegSpelerToeAanSpel("Arno", 2002);
		dc.voegSpelerToeAanSpel("Quinten", 2002);
		dc.startSpel();
		Assertions.assertEquals("ONYXEN", dc.getFichesDTO().get(3).get(0).soort());
		Assertions.assertEquals("zwart", dc.getFichesDTO().get(3).get(0).kleur());
	}
	
	@Test
	void correcteNaamKleurPlaats_Robijnen() {
		dc.voegSpelerToeAanSpel("Arno", 2002);
		dc.voegSpelerToeAanSpel("Quinten", 2002);
		dc.startSpel();
		Assertions.assertEquals("ROBIJNEN", dc.getFichesDTO().get(4).get(0).soort());
		Assertions.assertEquals("rood", dc.getFichesDTO().get(4).get(0).kleur());
	}

}

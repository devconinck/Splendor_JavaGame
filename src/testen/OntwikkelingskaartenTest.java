package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.DomeinController;

class OntwikkelingskaartenTest {
	
	private static final int AANTAL_ONTIKKELINGSKAARTEN_NIVEAU_1 = 40;
	private static final int AANTAL_ONTIKKELINGSKAARTEN_NIVEAU_2 = 30;
	private static final int AANTAL_ONTIKKELINGSKAARTEN_NIVEAU_3 = 20;
	private DomeinController dc;

	@BeforeEach
	void init() {
		dc = new DomeinController();
	}
	
	/*
	 * 		Aantal kaarten van elk niveau is correct in de geneste list
	 */
	
	@Test
	void correctAantalOntkaarten_Niv1() {
		dc.voegSpelerToeAanSpel("A_Star", 1999);
		dc.voegSpelerToeAanSpel("HarryPotter", 1980);
		dc.startSpel();
		Assertions.assertEquals(AANTAL_ONTIKKELINGSKAARTEN_NIVEAU_1, dc.getOntwikkelingskaartenDTO().get(0).size());
	}
	
	@Test
	void correctAantalOntkaarten_Niv2() {
		dc.voegSpelerToeAanSpel("A_Star", 1999);
		dc.voegSpelerToeAanSpel("HarryPotter", 1980);
		dc.startSpel();
		Assertions.assertEquals(AANTAL_ONTIKKELINGSKAARTEN_NIVEAU_2, dc.getOntwikkelingskaartenDTO().get(1).size());
	}

	@Test
	void correctAantalOntkaarten_Niv3() {
		dc.voegSpelerToeAanSpel("A_Star", 1999);
		dc.voegSpelerToeAanSpel("HarryPotter", 1980);
		dc.startSpel();
		Assertions.assertEquals(AANTAL_ONTIKKELINGSKAARTEN_NIVEAU_3, dc.getOntwikkelingskaartenDTO().get(2).size());
	}
	
	/*
	 * 		Alle kaarten op index 0 -> niveau 1, index 1 -> niveau 2, index 2 -> niveau 3
	 */
	
	@Test
	void correctNiveauOntkaarten_Index_0() {
		dc.voegSpelerToeAanSpel("A_Star", 1999);
		dc.voegSpelerToeAanSpel("HarryPotter", 1980);
		dc.startSpel();
		for(int i=0; i<AANTAL_ONTIKKELINGSKAARTEN_NIVEAU_1 ; i++) {
			Assertions.assertEquals(1, dc.getOntwikkelingskaartenDTO().get(0).get(i).niveau());
		}
	}
	
	@Test
	void correctNiveauOntkaarten_Index_1() {
		dc.voegSpelerToeAanSpel("A_Star", 1999);
		dc.voegSpelerToeAanSpel("HarryPotter", 1980);
		dc.startSpel();
		for(int i=0; i<AANTAL_ONTIKKELINGSKAARTEN_NIVEAU_2 ; i++) {
			Assertions.assertEquals(2, dc.getOntwikkelingskaartenDTO().get(1).get(i).niveau());
		}
	}
	
	@Test
	void correctNiveauOntkaarten_Index_2() {
		dc.voegSpelerToeAanSpel("A_Star", 1999);
		dc.voegSpelerToeAanSpel("HarryPotter", 1980);
		dc.startSpel();
		for(int i=0; i<AANTAL_ONTIKKELINGSKAARTEN_NIVEAU_3 ; i++) {
			Assertions.assertEquals(3, dc.getOntwikkelingskaartenDTO().get(2).get(i).niveau());
		} 
	}
	
}

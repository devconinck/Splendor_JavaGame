package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.DomeinController;

class EdelenTest {

	/*
	 * Aanmaak van Edele checken
	 * 
	 */
	
	private final static int AANTAL_EDELEN_TWEE_SPELERS = 3;
	private final static int AANTAL_EDELEN_DRIE_SPELERS = 4;
	private final static int AANTAL_EDELEN_VIER_SPELERS = 5;
	private DomeinController dc;
	
	@BeforeEach
	void init() {
		this.dc = new DomeinController();
	}
	
	@Test
	void correctAantal_Edelen_TweeSpelers() {
		dc.voegSpelerToeAanSpel("A_Star", 1999);
		dc.voegSpelerToeAanSpel("HarryPotter", 1980);
		dc.startSpel();
		Assertions.assertEquals(AANTAL_EDELEN_TWEE_SPELERS, dc.getEdelenInSpelDTO().size());
	}
	
	@Test
	void correctAantal_Edelen_DrieSpelers() {
		dc.voegSpelerToeAanSpel("A_Star", 1999);
		dc.voegSpelerToeAanSpel("HarryPotter", 1980);
		dc.voegSpelerToeAanSpel("Lily123", 2015);
		dc.startSpel();
		Assertions.assertEquals(AANTAL_EDELEN_DRIE_SPELERS, dc.getEdelenInSpelDTO().size());
	}
	
	@Test
	void correctAantal_Edelen_VierSpelers() {
		dc.voegSpelerToeAanSpel("A_Star", 1999);
		dc.voegSpelerToeAanSpel("HarryPotter", 1980);
		dc.voegSpelerToeAanSpel("Lily123", 2015);
		dc.voegSpelerToeAanSpel("S", 2016);
		dc.startSpel();
		Assertions.assertEquals(AANTAL_EDELEN_VIER_SPELERS, dc.getEdelenInSpelDTO().size());
	}

}

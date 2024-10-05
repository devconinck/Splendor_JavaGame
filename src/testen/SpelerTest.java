package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.DomeinController;


/**
 * Klasse om de code van klasse speler te testen.
 * @author Arno
 *
 */
public class SpelerTest 
{
	
	private DomeinController dc;
	
	
	/*
	 * Gebruikersnamen en geboortejaren checken
	 * 
	 */
	
	@BeforeEach
	void init() {
		this.dc = new DomeinController();
	}
	

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"Veel Te lange Naam12345", "123naam", "**Naam**", " Player"})
	void maakSpeler_OngeldigeNaamEnGeldigGeboortejaar_WerptIllegalArumentExceptionException(String gebruikersnaam)
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> dc.voegSpelerToeAanSpel(gebruikersnaam, 2004));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {2018, 2020})
	void maakSpeler_GelidgeNaamEnOngeldigeGeboortejaar_TeJong_WerptException(int geboortejaar)
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> dc.voegSpelerToeAanSpel("A_", geboortejaar));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1800, 1922})
	void maakSpeler_GelidgeNaamEnOngeldigeGeboortejaar_TeOud_WerptException(int geboortejaar)
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> dc.voegSpelerToeAanSpel("A_", geboortejaar));
	}
	
	/*
	 * Aantal spelers in spel checken
	 * 
	 */
	
	@Test
	void aantalSpelers_TeWeinigSpelers() {
		dc.voegSpelerToeAanSpel("A_", 1997);
		Assertions.assertThrows(IllegalArgumentException.class, () -> dc.startSpel());
	}
	
	@Test
	void aantalSpelers_MIN() {
		dc.voegSpelerToeAanSpel("A_", 1997);
		dc.voegSpelerToeAanSpel("A_Star", 1999);
		Assertions.assertDoesNotThrow(() -> IllegalArgumentException.class);
	}
	
	@Test
	void aantalSpelers_MAX() {
		dc.voegSpelerToeAanSpel("A_", 1997);
		dc.voegSpelerToeAanSpel("A_Star", 1999);
		dc.voegSpelerToeAanSpel("HarryPotter", 1980);
		dc.voegSpelerToeAanSpel("Lily123", 2015);
		Assertions.assertDoesNotThrow(() -> IllegalArgumentException.class);
	}
	
	@Test
	void aantalSpelers_TeVeelSpelers() {
		dc.voegSpelerToeAanSpel("A_", 1997);
		dc.voegSpelerToeAanSpel("A_Star", 1999);
		dc.voegSpelerToeAanSpel("HarryPotter", 1980);
		dc.voegSpelerToeAanSpel("Lily123", 2015);
		dc.voegSpelerToeAanSpel("S", 2016);
		Assertions.assertThrows(IllegalArgumentException.class, () -> dc.startSpel());
	}
	
	/*
	 * Twee dezelfde spelers toevoegen
	 * 
	 */
	
	@Test
	void toevoegenVanTweedeSpeler_MetZelfdeGebruikersnaam_AanSpel()
	{
		dc.voegSpelerToeAanSpel("A_", 1997);
		Assertions.assertThrows(IllegalArgumentException.class, () -> dc.voegSpelerToeAanSpel("A_", 1997));
	}

}

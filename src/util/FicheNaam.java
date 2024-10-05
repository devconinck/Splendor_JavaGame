package util;

/**
* Deze klasse beschrijft de verschillende fiche-namen die kunnen worden gebruikt in een spel, samen met hun kleur.
*/
public enum FicheNaam {
	
	/**
	 * De SMARAGDEN fiche heeft een groene kleur.
	 */
	SMARAGDEN("groen"),

	/**
	 * De DIAMANTEN fiche heeft een witte kleur.
	 */
	DIAMANTEN("wit"),

	/**
	 * De SAFFIEREN fiche heeft een blauwe kleur.
	 */
	SAFFIEREN("blauw"),

	/**
	 * De ONYXEN fiche heeft een zwarte kleur.
	 */
	ONYXEN("zwart"),

	/**
	 * De ROBIJNEN fiche heeft een rode kleur.
	 */
	ROBIJNEN("rood");

	private final String kleur;

	/**
	 * Constructor voor de FicheNaam klasse, waarbij de kleur van het fiche wordt vastgesteld.
	 * @param kleur de kleur van het fiche
	 */
	private FicheNaam( String kleur) {
	    this.kleur = kleur;
	}

	/**
	 * Geeft de kleur van het fiche terug.
	 * @return de kleur van het fiche
	 */
	public String getKleur() {
	    return kleur;
	}

}

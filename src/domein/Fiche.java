package domein;

public class Fiche {
	
	private String soort;
	private String kleur;
	
	/**
	 * Constructor voor een fiche
	 * @param soort, soort van Fiches, kan Smaragd, Diamant, Saffier, Onyx of Robijn zijn
	 * @param kleur, kleur die de Fiche heeft, groen, wit, blauw, zwart of rood
	 */
	public Fiche(String soort, String kleur) {
		this.soort = soort;
		this.kleur = kleur;
	}
	
	
	/**
	 * Haalt de soort op van de fiche.
	 * @return string met de soort
	 */
	public String getSoort() {
		return soort;
	}
	
	
	/**
	 * Haalt de kleur op van de fiche.
	 * @return string met de kleur
	 */
	public String getKleur() {
		return kleur;
	}

}

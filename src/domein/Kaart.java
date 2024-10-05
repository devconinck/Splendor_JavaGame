package domein;

import java.util.Arrays;
import java.util.List;


/**
 * Klasse die de definitie van Kaart bevat
 * @author Arno
 *
 */
public abstract class Kaart {
	private int prestigePunten, smaragden, diamanten, saffieren, onyxen, robijnen;
	private String naamImage;
	
	/**
	 * Constructor voor Kaart object
	 * @param prestigePunten, aantal Presigepunten die de kaart kost
	 * @param smaragden, aantal SmaragdenFiches die de kaart kost
	 * @param diamanten, aantal DiamantenFiches die de kaart kost
	 * @param saffieren, aantal SaffierenFiches die de kaart kost
	 * @param onyxen, aantal OnyxenFiches die de kaart kost
	 * @param robijnen, aantal RobijnenFiches die de kaart kost
	 * @param naamImage, String die de naam van de image bevat
	 */
	public Kaart(int prestigePunten, int smaragden, int diamanten, int saffieren, int onyxen, int robijnen, String naamImage) {
		this.prestigePunten = prestigePunten;
		this.smaragden = smaragden;
		this.diamanten = diamanten;
		this.saffieren = saffieren;
		this.onyxen = onyxen;
		this.robijnen = robijnen;
		this.naamImage = naamImage;
	}

	/**
	 * Haalt de prestigepunten van een kaart op.
	 * @return prestige punten van de kaart (Integer)
	 */
	public int getPrestigePunten() {
		return prestigePunten;
	}
	
	/**
	 * Haalt een lijst op die de kost van een kaart teruggeeft.
	 * @return geeft een lijst van Integers terug. Bevat de hoeveelheid van fiches nodig zijn om de kaart te kopen..
	 */
	public List<Integer> getKost() {
		List<Integer> kost = Arrays.asList(smaragden, diamanten, saffieren, onyxen, robijnen);
		return kost;
	}

	/**
	 * Geeft de hoeveelheid smaragden er nodig zijn om de kaart te kopen.
	 * @return aantal smaragden als int.
	 */
	public int getSmaragden() {
		return smaragden;
	}

	/**
	 * Geeft de hoeveelheid diamanten er nodig zijn om de kaart te kopen.
	 * @return aantal diamanten als int.
	 */
	public int getDiamanten() {
		return diamanten;
	}

	/**
	 * Geeft de hoeveelheid saffieren er nodig zijn om de kaart te kopen.
	 * @return aantal saffieren als int.
	 */
	public int getSaffieren() {
		return saffieren;
	}

	/**
	 * Geeft de hoeveelheid onyxen er nodig zijn om de kaart te kopen.
	 * @return aantal onyxen als int.
	 */
	public int getOnyxen() {
		return onyxen;
	}

	/**
	 * Geeft de hoeveelheid robijnen er nodig zijn om de kaart te kopen.
	 * @return aantal robijnen als int.
	 */
	public int getRobijnen() {
		return robijnen;
	}

	/**
	 * Abstracte getter voor de bonus.
	 */
	abstract public String getBonus();


	/**
	 * Geeft Naam van de image terug als String.
	 * @return de filenaam van de image als String
	 */
	public String getNaamImage() {
		return naamImage;
	}
	
	
	/**
	 * Geeft een mooi geformatteerde String versie terug van een Kaart.
	 * @return String met geformatteerde kaart String.
	 */
	@Override
	public String toString() {
		StringBuilder kaartString = new StringBuilder();
		
		kaartString.append(getClass().getSimpleName()).append(System.lineSeparator());
	    kaartString.append(" Prestigepunten: ").append(getPrestigePunten()).append(System.lineSeparator());
	    
	    if (getSmaragden() > 0) kaartString.append(" Smaragden: ").append(getSmaragden()).append(System.lineSeparator());

	    if (getDiamanten() > 0) kaartString.append(" Diamanten: ").append(getDiamanten()).append(System.lineSeparator());
	    
	    if (getSaffieren() > 0) kaartString.append(" Saffieren: ").append(getSaffieren()).append(System.lineSeparator());
	    
	    if (getOnyxen() > 0) kaartString.append(" Onyxen: ").append(getOnyxen()).append(System.lineSeparator());
	    
	    if (getRobijnen() > 0) kaartString.append(" Robijnen: ").append(getRobijnen()).append(System.lineSeparator());
	   
	    kaartString.append(" Foto: ").append(getNaamImage()).append(System.lineSeparator());
	    
	    return kaartString.toString();
	}

}

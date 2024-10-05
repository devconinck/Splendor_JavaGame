package domein;
/**
 * deze klasse stelt de kaart voor de Edelen voor
 * @author Quinten
 *
 */
public class Edele extends Kaart {
	/**
	 * Constructor voor een Edele kaart
	 * @param prestigePunten, prestigepunten die de kaart waard is 
	 * @param smaragden, aantal SmaragdenFiches die de kaart kost
	 * @param diamanten, aantal DiamantenFiches die de kaart kost
	 * @param saffieren, aantal SaffierenFiches die de kaart kost
	 * @param onyxen, aantal OnyxenFiches die de kaart kost
	 * @param robijnen, aantal RobijnenFiches die de kaart kost
	 * @param naamImage, String die de naam van de Image bevat
	 */
	public Edele(int prestigePunten, int smaragden, int diamanten, int saffieren, int onyxen, int robijnen, String naamImage) {
		super(prestigePunten, smaragden, diamanten, saffieren, onyxen, robijnen, naamImage);
	}

	
	/**
	 * Geeft de bonus van een edele terug, deze zal altijd null zijn
	 * aangezien een Edele geen bonus heeft.
	 */
	@Override
	public String getBonus() {
		return null;
	}

}
package repo;

import java.util.*;

import domein.Speler;
import persistentie.SpelerMapper;
import resources.Taal;
/**
 * Klasse spelerRepository die spelers die het spel willen spelen toevoegt aan een List
 * @author Quinten
 *
 */
public class SpelerRepository {
	
		private final SpelerMapper mapper;
		private List<Speler> spelers; 
		/**
		 * Constructor van de SpelerRepository, maakt een nieuwe instantie van SpelerMapper aan en de ArrayList spelers
		 */
		public SpelerRepository() {
			mapper = new SpelerMapper();
			spelers = new ArrayList<>();
		}
		/**
		 * voegt een speler toe aan de mapper
		 * @param speler, Speler object dat we willen toevoegen aan het spel
		 */
		public void voegToe(Speler speler) {
			if (bestaatSpeler(speler))
				throw new IllegalArgumentException(Taal.getString("spelerBestaatAl"));
			mapper.voegToe(speler);
		}
		/**
		 * kijkt of de Speler reeds in de mapper zit 
		 * @param speler, Speler die we willen toevoegen aan het spel
		 * @return boolean, true als de speler al in de mapper zit
		 */
		private boolean bestaatSpeler(Speler speler) {
			try {
				return mapper.geefSpelers().contains(speler);
			} catch(NullPointerException ex) {
				return false;
			} catch(ClassCastException ex) {
				throw new IllegalArgumentException(Taal.getString("speler"));
			}
		}
		/**
		 * geeft een Speler terug als deze in de lijst zit
		 * @param gebruikersnaam, username van de speler 
		 * @return Speler object als de speler in de lijst zit, anders exception 
		 */
		public Speler geefSpeler(String gebruikersnaam) {
			for(Speler deelnemer : spelers) {
				if(deelnemer.getNaam() == gebruikersnaam)
					return deelnemer;
			}
			throw new IllegalArgumentException(String.format(Taal.getString("geenSpelerMetNaam"), gebruikersnaam));//test
		}
		/**
		 * geeft lijst met alle spelers uit de database
		 * @return List, gevuld met Speler objecten 
		 */
		public List<Speler> geefSpelersUitDB() {
			return mapper.geefSpelers();
		}
}

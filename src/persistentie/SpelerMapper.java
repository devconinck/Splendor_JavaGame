package persistentie;

import java.util.ArrayList;
import java.sql.*;
import java.util.*;
import domein.Speler;

import domein.Speler;
/**
 * SpelerMapper klasse, die we kunnen gebruiken om spelers aan onze databank toe te voegen(gebruiken we hier niet), maar vooral om de lijst met spelers uit de DB te halen
 * @author Quinten
 *
 */
public class SpelerMapper {

	private static final String INSERT_SPELER = "INSERT INTO ID399280_g17.Speler (gebruikersnaam, geboortejaar)"
												+ "VALUES(?,?)";
	
	/**
	 * functie die we zouden kunnen gebruiken om een speler aan de databank toe te voegen
	 * @param speler, Speler object dat we hebben aangemaakt die we dan willen toevoegen in de databank
	 */
	public void voegToe(Speler speler) {
		
		try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
				PreparedStatement query = conn.prepareStatement(INSERT_SPELER)){
			
			query.setString(1, speler.getNaam());
			query.setInt(2, speler.getGeboortejaar());
			query.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		
	}
	/**
	 * geeft een lijst met de Spelers die in onze databank zitten
	 * @return List met Speler objecten
	 */
	public List<Speler> geefSpelers(){
		List<Speler> spelers = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
				PreparedStatement query = conn.prepareStatement("SELECT * FROM ID399280_g17.Speler");
				ResultSet rs = query.executeQuery()){
			
			while (rs.next()) {
				String gebruikersnaam = rs.getString("gebruikersnaam");
				int geboortejaar = rs.getInt("geboortejaar");
				
				spelers.add(new Speler(gebruikersnaam, geboortejaar));
			}
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return spelers;
	}

}
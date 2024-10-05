package dto;

import java.util.List;

import domein.Fiche;
import domein.Ontwikkelingskaart;

/**
 * Omzetten speler object naar DTO
 * @author Arno
 *
 */
public record SpelerDTO(String gebruikersnaam, int geboortejaar,
		int score, List<List<Fiche>> fiches, List<Ontwikkelingskaart> ontwikkelingskaarten) {

}

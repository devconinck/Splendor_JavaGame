package event;

import javafx.event.Event;
import javafx.event.EventType;
/**
 * OntKaartEvent klasse, kan ofwel een KOOP event ofwel een TERUG event zijn dat we kunnen afvuren wanneer er op het OntwikkelingskaartScherm een kaart gekocht wordt of op de terug knop wordt gedrukt
 * @author Quinten
 *
 */
public class OntKaartEvent extends Event {
	
public static final EventType<OntKaartEvent> KOOP_EVENT = new EventType<> (Event.ANY, "KOOP_EVENT");
public static final EventType<OntKaartEvent> TERUG_EVENT = new EventType<> (Event.ANY, "TERUG_EVENT");	
	
	public OntKaartEvent(EventType<? extends Event> eventType) {
		super(eventType);
	}
	


}

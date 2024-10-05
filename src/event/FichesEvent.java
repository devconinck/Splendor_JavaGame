package event;

import javafx.event.Event;
import javafx.event.EventType;
/**
 * klasse fichesEvent, een event dat we uitsturen wanneer er fiches worden genomen, zo kunnen we tussen de verschillende schermen in de GUI info doorgeven
 * @author Quinten
 *
 */
public class FichesEvent extends Event {

	public static final EventType<FichesEvent> NEEM_EVENT_TYPE = new EventType<> (Event.ANY, "NEEM_EVENT");
	/**
	 * Constructor van het FichesEvent, stuurt een neem_event uit 
	 */
	public FichesEvent() {
		super(NEEM_EVENT_TYPE);
	}
}

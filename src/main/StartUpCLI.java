package main;

import domein.DomeinController;
import ui.SplendorApplicatieCLI;
/**
 * Start up klasse van de command line interface, maakt een nieuwe SplendorApplicatieCLI object aan met een nieuwe DomeinController als argument
 * @author Quinten
 *
 */
public class StartUpCLI {

	/**
	 * De methode om de command line interface te starten.
	 * @param args, een array van Strings
	 */
	public static void main(String[] args) {
		new SplendorApplicatieCLI(new DomeinController()).start();
	}
}

package ss.week7.hotel.server;

import java.io.PrintWriter;


/**
 * Hotel Server TUI for user input and user messages
 * 
 * @author Wim Kamerman
 */
public class HotelServerTUI implements HotelServerView {
	
	/** The PrintWriter to write messages to */
	private PrintWriter console;

	/**
	 * Constructs a new HotelServerTUI. Initializes the console.
	 */
	public HotelServerTUI() {
		console = new PrintWriter(System.out, true);
	}

	@Override
	public void showMessage(String message) {
		console.println(message);
	}
	
	@Override
	public String getString(String question) {
		// To be implemented
		return "U Parkhotel";
	}

	@Override
	public int getInt(String question) {
		// To be implemented
		return 8888;
	}

	@Override
	public boolean getBoolean(String question) {
		// To be implemented
		return true;
	}

}

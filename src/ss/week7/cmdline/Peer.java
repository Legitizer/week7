package ss.week7.cmdline;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Peer for a simple client-server application
 * @author  Theo Ruys
 * @version 2005.02.21
 */
public class Peer implements Runnable {
    public static final String EXIT = "exit";

    protected String name;
    protected Socket sock;
    protected BufferedReader in;
    protected BufferedWriter out;
    protected Scanner scanner;


    /**
     * @requires (nameArg != null) && (sockArg != null) 
     * @param   nameArg name of the Peer process
     * @param   sockArg Socket of the Peer process
     */
    public Peer(String nameArg, Socket sockArg) throws IOException
    {
    	if (nameArg == null || sockArg == null) {
    		throw new IOException();
    	}
    	
    	name = nameArg;
    	sock = sockArg;
    	
    	in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    	out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
    }

    /**
     * Reads strings of the stream of the socket connection and
     * writes the characters to the default output.
     */
    public void run() {
    	while(true) {
    		String line;
        	try {
    			while ((line = in.readLine()) != null) {
    				System.out.println(line);
    			}
    		} catch (IOException e) {
    		}
    	}
    }

    /**
     * Reads a string from the console and sends this string over
     * the socket-connection to the Peer process.
     * On Peer.EXIT the method ends
     */
    public void handleTerminalInput() {
    	while(true) {
    		try {
    			String signature = getName() + ": ";
        		String line = signature + readString("");
        		
        		if (line.equals(signature)) {
        			continue;
        		}
        		if (line.toLowerCase().equals(signature.toLowerCase() + "exit")) {
        			break;
        		}
    			out.write(line);
    			out.newLine();
    			out.flush();
    		} catch (IOException e) {
    			System.out.println("Reached end of input");
    		}
    	}
    	
    }

    /**
     * Closes the connection, the sockets will be terminated
     */
    public void shutDown() {
    	try {
			in.close();
			out.close();
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**  returns name of the peer object*/
    public String getName() {
        return name;
    }

    /** read a line from the default input */
    static public String readString(String text) {
        //System.out.print(text);
        String antw = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    System.in));
            antw = in.readLine();
        } catch (IOException e) {
        }

        return (antw == null) ? "" : antw;
    }
}

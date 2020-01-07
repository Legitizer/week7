
package ss.week7.cmdline;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Server. 
 * @author  Theo Ruys
 * @version 2005.02.21
 */
public class Server {
    private static final String USAGE
        = "usage: " + Server.class.getName() + " <name> <port>";

    /** Starts a Server-application. */
    public static void main(String[] args) {
    	if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(0);
        }

        String name = args[0];
        int port = 0;
        ServerSocket sock = null;

        // parse args[2] - the port
        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println(USAGE);
            System.out.println("ERROR: port " + args[1]
            		           + " is not an integer");
            System.exit(0);
        }

        // try to open a ServerSocket
        try {
            sock = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("ERROR: could not create a socket on " + " with port " + port);
        }
        
        Socket s = null;
        try {
			s = sock.accept();
			System.out.println("Connection established");
		} catch (IOException e1) {
			System.out.println("Could not connect a client to the server");
		}

        // create Peer object and start the two-way communication
        try {
            Peer client = new Peer(name, s);
            Thread streamInputHandler = new Thread(client);
            streamInputHandler.start();
            client.handleTerminalInput();
            client.shutDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

} // end of class Server

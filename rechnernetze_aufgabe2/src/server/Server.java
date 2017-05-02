package server;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;


public class Server {
	private static Server server = new Server();
	private ArrayList<Connection> connections;
	private int port;
	private String IP;

	public static Server getInstance(){
		return server;
	}
	
	public int getPort(){
		return port;
	}
	
	public String getIP(){
		return IP;
	}
	
	private Server(){
		connections = new ArrayList<Connection>();
		
		port = 6789;
		IP = "localhost";
		try{
			IP = InetAddress.getLocalHost().getHostAddress();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public int loadPortFromConfig(String path){
		  try {
			  Properties properties = new Properties();
//			  try {
//				  File file = new File("config.properties");
//				  PrintWriter pw = new PrintWriter(file);
//				  pw.write("test2");
//				  pw.close();
//			  } catch(Exception e){
//				  e.printStackTrace();
//			  }
			  BufferedInputStream stream = new BufferedInputStream(new FileInputStream(path));
			  properties.load(stream);
			  stream.close();
			  return Integer.parseInt(properties.getProperty("Port", "6789"));
		  } catch (IOException e) {
			  System.out.println("Keine Config-Datei namens 'config.properties' gefunden.");
			  return 6790;
		  }
		}
	
	public void removeConnection(Connection con){
		if(con.isRunning() == false)
			connections.remove(con);
		else
			throw new IllegalStateException("Client-Thread is still running");
	}
	
	public void doBroadcast(String message){
		connections.forEach((i) -> {
			try {
				i.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	public static void main(String[] args) {
//		String clientSentence;
//	    String capitalizedSentence;
	    
	    if(args.length > 0)
	    	getInstance().port = Integer.parseInt(args[0]);
	    else
	    	getInstance().port = getInstance().loadPortFromConfig("config.properties");
		System.out.println("Dieser Server empfängt auf den Port " + getInstance().getPort() +
					" mit der IP " + getInstance().getIP());
	    
	    ServerSocket welcomeSocket = null;
		try {
			welcomeSocket = new ServerSocket(getInstance().getPort());
		    while(true) {
		      Socket connectionSocket = welcomeSocket.accept();
		      Connection con = new Connection(connectionSocket, getInstance());
		      getInstance().connections.add(con);
		      con.start();
		    }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(welcomeSocket != null)
				try {
					welcomeSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}

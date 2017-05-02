package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {
	private boolean isRunning;
	private Socket clientSocket;
	private DataOutputStream outToServer;
	private DataInputStream inFromServer;

	public Client(Socket socket) throws IOException {
		clientSocket = socket;
		isRunning = false;
		
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
		inFromServer = new DataInputStream(clientSocket.getInputStream());
	}
	
	public Client(String ip, int port) throws UnknownHostException, IOException{
		isRunning = false;
		clientSocket = new Socket(ip, port);
	}
	
	public boolean isRunning(){
		return isRunning;
	}
	
	public void sendToServer(String message) throws IOException{
		outToServer.writeUTF(message + '\n');
	}
	
	public String promptForNewMessage() throws IOException{
		return inFromServer.readUTF();
	}
	
	public void shutDown() throws IOException{
		clientSocket.close();
	}
	
	public void run(){
		
	}

	public static void main(String[] args) {
		String ip = "locahost";
		int port = 6789;
		String message = "hallo";

		if (args.length > 0)
			ip = args[0];
		if (args.length > 1)
			port = Integer.parseInt(args[1]);

		try {
			Socket clientSocket = new Socket(ip, port); // 6789
			Client client = new Client(clientSocket);
			
			client.sendToServer(message);
			String messageFromServer = "";
			while(messageFromServer.equals("\\exit") == false){
				messageFromServer = client.promptForNewMessage();
				System.out.println(messageFromServer);
				
				System.out.println("Schreibe dem Server eine Nachricht: ");
				
			}
			client.shutDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

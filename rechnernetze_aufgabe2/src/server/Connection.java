package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {
	Socket clientSocket;
	private boolean isRunning;
	Statistik stats;
	DataInputStream inFromClient;
	DataOutputStream outToClient;
	private Server server;

	public Connection(Socket client, Server server) throws IOException {
		isRunning = false;
		this.server = server;
		clientSocket = client;
		stats = new Statistik();

		inFromClient = new DataInputStream(clientSocket.getInputStream());
		outToClient = new DataOutputStream(clientSocket.getOutputStream());
	}

	public void sendToClient(String message) throws IOException {
		outToClient.writeUTF(message);
		stats.setCount(stats.getCount() + 1);
		stats.setAggregatetSize(stats.getAggregatetSize() + message.length());
	}

	public Statistik getStats() {
		return stats;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void run() {
		isRunning = true;
		String capitalizedSentence;

		while (true) {
			try {
				String clientSentence = inFromClient.readUTF();

				if (clientSentence != null)
					if (clientSentence.length() > stats.longest)
						stats.setLongest(clientSentence.length());
					else if (clientSentence.length() > stats.shortest)
						stats.setShortest(clientSentence.length());

				System.out.println("Vom Client: " + clientSentence);

				capitalizedSentence = "(IP = " + clientSocket.getInetAddress().toString() + " Port = "
						+ clientSocket.getPort() + ")" + clientSentence + '\n';
				sendToClient(capitalizedSentence);

				if(clientSentence.contains("\\broadc")){
					server.doBroadcast(clientSentence.replace("\\broadc", ""));
				}
				
				if (clientSentence.contains("\\exit")) {
					clientSocket.close();
					isRunning = false;
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		server.removeConnection(this);
	}
}

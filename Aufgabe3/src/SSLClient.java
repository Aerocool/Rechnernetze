import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.net.SocketFactory;

//TODO import SSL-Classes

public class SSLClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO setup truststore to verfy server-certifiacte

		System.setProperty("javax.net.ssl.trustStore", "C:/Users/Matse/Downloads/Rechnernetze/truststore.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "geheim");

		System.out.println("client starts");
		int port = 3000;
		String IP = InetAddress.getLocalHost().getHostAddress();

		Socket sslSocket = null;
		// TODO create SSLSocket
		SocketFactory socketFactory = SocketFactory.getDefault();
		try {
			sslSocket = socketFactory.createSocket(IP, port); // TODO assign
		} catch (Exception e) {
			e.printStackTrace();
		}

		Scanner scanner = new Scanner(System.in);
		DataInputStream dataInputStream = new DataInputStream(sslSocket.getInputStream());
		DataOutputStream dataOutputStream = new DataOutputStream(sslSocket.getOutputStream());
		while (scanner.hasNextLine()) {
			String s = scanner.nextLine();
			System.out.println("s: " + s);
			dataOutputStream.writeUTF(s);
			System.out.println(dataInputStream.readUTF());
		}
		scanner.close();
	}

}
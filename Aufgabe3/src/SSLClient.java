import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class SSLClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.setProperty("javax.net.ssl.trustStore", "truststore.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "geheim");

		System.out.println("client starts");
		int port = 443;
		String IP = InetAddress.getLocalHost().getHostAddress();

		SSLSocket sslSocket = null;
		SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		try {
			sslSocket = (SSLSocket) socketFactory.createSocket(IP, port);
		} catch (Exception e) {
			e.printStackTrace();
		}

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String s = scanner.next();
		DataInputStream dataInputStream = new DataInputStream(sslSocket.getInputStream());
		DataOutputStream dataOutputStream = new DataOutputStream(sslSocket.getOutputStream());
		
		dataOutputStream.writeUTF(s);
		while(true)
			System.out.println(dataInputStream.readUTF());
	}

}
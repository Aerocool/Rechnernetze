import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;

//TODO import SSL-Classes

public class SSLServer {

	public static void main(String[] args) throws IOException {
		ServerSocket sslServerSocket = null;
		try {
			// TODO set keystore and password
			System.setProperty("javax.net.ssl.keyStore",
					"C:/Users/Matse/Downloads/Rechnernetze/rn-ssl.jks"/* 'keystoreFile' */);
			System.setProperty("javax.net.ssl.keyStorePassword", "geheim");

			System.out.println("server starts");
			// TODO create SSLServerSocket
			int port = 3000;
			ServerSocketFactory ssocketFactory = SSLServerSocketFactory.getDefault();
			sslServerSocket = ssocketFactory.createServerSocket(port); // TODO
																					// assign
			Socket sslSocket = sslServerSocket.accept();
			DataInputStream dataInputStream = new DataInputStream(sslSocket.getInputStream());
			DataOutputStream dataOutputStream = new DataOutputStream(sslSocket.getOutputStream());
			while (true) {
				System.out.println(dataInputStream.readUTF());
				dataOutputStream.writeUTF(dataInputStream.readUTF().toUpperCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(sslServerSocket != null)
				sslServerSocket.close();
		}
	}

}
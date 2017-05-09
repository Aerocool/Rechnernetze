import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

//TODO import SSL-Classes

public class SSLServer {

	public static void main(String[] args) throws IOException {
		SSLServerSocket sslServerSocket = null;
		try {
			System.setProperty("javax.net.ssl.keyStore", "rn-ssl.jks");
			System.setProperty("javax.net.ssl.keyStorePassword", "geheim");

			System.out.println("server starts");
			int port = 443;
			ServerSocketFactory ssocketFactory = SSLServerSocketFactory.getDefault();
			sslServerSocket = (SSLServerSocket) ssocketFactory.createServerSocket(port);
			SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();
			DataInputStream dataInputStream = new DataInputStream(sslSocket.getInputStream());
			DataOutputStream dataOutputStream = new DataOutputStream(sslSocket.getOutputStream());
			while (true) {
				String s = dataInputStream.readUTF();
				System.out.println(s);
				dataOutputStream.writeUTF(s.toUpperCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(sslServerSocket != null)
				sslServerSocket.close();
		}
	}

}
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

<<<<<<< HEAD
	public static void main(String[] args) {
		ServerSocket sslServerSocket = null;
=======
	public static void main(String[] args) throws IOException {
		SSLServerSocket sslServerSocket = null;
>>>>>>> 3105522b0363fb2a75d980503b979916bfbae68b
		try {
			System.setProperty("javax.net.ssl.keyStore", "rn-ssl.jks");
			System.setProperty("javax.net.ssl.keyStorePassword", "geheim");

			System.out.println("server starts");
			int port = 443;
			ServerSocketFactory ssocketFactory = SSLServerSocketFactory.getDefault();
<<<<<<< HEAD
			sslServerSocket = ssocketFactory.createServerSocket(port); // TODO
																		// assign
			Socket sslSocket = sslServerSocket.accept();
=======
			sslServerSocket = (SSLServerSocket) ssocketFactory.createServerSocket(port);
			SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();
>>>>>>> 3105522b0363fb2a75d980503b979916bfbae68b
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
			if (sslServerSocket != null)
				try {
					sslServerSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
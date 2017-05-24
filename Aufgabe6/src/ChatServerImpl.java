import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChatServerImpl extends UnicastRemoteObject implements IChatServer {
 
  private static final long serialVersionUID = 1L;
  private static final int registryPort = 1099;
 
  // Hier speichern wir die Callbacks!
  private Map<String, IChatClientCallback> users;
 
  private ChatServerImpl() throws RemoteException {
    super();
    HashMap<String, IChatClientCallback> callbackHashMap = new HashMap<>();
    users = Collections.synchronizedMap(callbackHashMap);
  }
 
  public boolean login(String userID, IChatClientCallback receiver)
  throws RemoteException {
      //TODO Alle informieren
	  receiver.receiveUserLogin(userID, users.values().toArray());
	  users.put(userID, receiver);
	  return true;
  }
 
  public void logout(String userID) throws RemoteException {
    //TODO Alle informieren
	  IChatClientCallback client = users.get(userID);
	  client.receiveUserLogout(userID, users.values().toArray());
	  users.remove(userID);
  }
 
	public void chat(String userID, String message) throws RemoteException {
		// TODO: Alle die Chatnachricht empfangen lassen
		users.forEach((userId, specificClient) -> {
			try {
				specificClient.receiveChat(userID, message);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}
 
  public static void main(String[] args) {
    try {
      LocateRegistry.createRegistry(1099);
      Naming.bind("rmi://localhost/queue", new ChatServerImpl());
      System.out.println("ChatServer ready");
    } catch (Exception ex) {
      ex.printStackTrace();
      System.exit(0);
    }
  }
}
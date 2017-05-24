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
	  return false;
  }
 
  public void logout(String userID) throws RemoteException {
    //TODO Alle informieren
  }
 
  public void chat(String userID, String message) throws RemoteException {
    // TODO: Alle die Chatnachricht empfangen lassen
  }
 
  public static void main(String[] args) {
    try {
      LocateRegistry.createRegistry(1099);
      Naming.bind("rmi://localhost/queue", new OwnStackImplementation());
      System.out.println("ChatServer ready");
    } catch (Exception ex) {
      ex.printStackTrace();
      System.exit(0);
    }
  }
}
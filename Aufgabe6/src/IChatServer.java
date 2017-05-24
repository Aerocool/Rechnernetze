import java.rmi.Remote;
import java.rmi.RemoteException;
 
public interface IChatServer extends Remote {
 
  /**
  * Ein neuer Benutzer betritt das System. Zusammen mit dem Benutzer erhalten
  * wir auch die Callback-Information
  */
  boolean login(String userID, IChatClientCallback receiver)
  throws RemoteException;
 
  /**
  * Benutzer verlaesst das System
  */
  void logout(String userID) throws RemoteException;
 
  /**
  * Benutzer verschickt eine Nachricht
  */
  void chat(String userID, String message) throws RemoteException;
}
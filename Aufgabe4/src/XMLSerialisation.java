
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import generated.EchoMessage;
import generated.EchoMessageType;
import generated.ObjectFactory;
 
public class XMLSerialisation {
  private ObjectFactory of;
  private JAXBContext jc;
  private Marshaller marshaller;
  private Unmarshaller unmarshaller;
  private String sender;
 
  /**
  * Erstellt ein Serialiser Objekt was ein EchoMessageObject in einen
  * XML-String serialisiert/deserialisiert
  * <p>
  * In der Methode werdern die Klassenparameter intialisiert. Ausserdem wird
  * der Fomatierte Output zur besseren Lesbarkeit eingeschaltet.
  *
  * @param sender
  *            an absolute URL giving the base location of the image
  * @see JAXBContext, marshaller, unmarshaller
  */
  public XMLSerialisation(String sender) {
	  try
	  {
		  jc = JAXBContext.newInstance(EchoMessage.class);
		  marshaller = jc.createMarshaller();
		  unmarshaller = jc.createUnmarshaller();
		  this.sender = sender;
		  of = new ObjectFactory();
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
 
  }
 
  /**
  * Serialisiert das Object in die XML Repräsentation.
  *
  * @param message
  *            Object das serialisiert werden soll
  * @return die XML-Repräsentation des Objects als String
  * @see StringWriter, Marshaller.marshall()
  */
  String messageToXMLString(EchoMessage message) throws JAXBException {
	  StringWriter sw = new StringWriter();
	  marshaller.marshal(message, sw);
	  return sw.toString();
  }
 
  	EchoMessage getNewMessage() {
    EchoMessage em = of.createEchoMessage();
    em.setSender(sender);
    em.setType(EchoMessageType.DEFAULT);
    return em;
  }
 
  /**
  * Deserialisiert von eine XML-String ein Object aus dem JAXBContext
  *
  * @param xml
  *            XML-repräsentation eines ECHO-Message Objectes
  * @return EchoMessage-Object
  * @throws JAXBException
  * @see StringReader, Unmarshaller.unmarshall()
  */
	EchoMessage xmlStringToMessage(String xml) throws JAXBException {
		StringReader reader = new StringReader(xml);
		return (EchoMessage) unmarshaller.unmarshal(reader);
	}
 
}
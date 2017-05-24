//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.05.17 um 02:52:34 PM CEST 
//


package generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für EchoMessageType.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="EchoMessageType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DEFAULT"/>
 *     &lt;enumeration value="BROADCAST"/>
 *     &lt;enumeration value="EXIT"/>
 *     &lt;enumeration value="SHOWSTAT"/>
 *     &lt;enumeration value="SHOWALLSTAT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EchoMessageType")
@XmlEnum
public enum EchoMessageType {

    DEFAULT,
    BROADCAST,
    EXIT,
    SHOWSTAT,
    SHOWALLSTAT;

    public String value() {
        return name();
    }

    public static EchoMessageType fromValue(String v) {
        return valueOf(v);
    }

}

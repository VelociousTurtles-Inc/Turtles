
package Services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the Services package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GameListResponse_QNAME = new QName("http://Controller/", "GameListResponse");
    private final static QName _GameList_QNAME = new QName("http://Controller/", "GameList");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: Services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GameList }
     * 
     */
    public GameList createGameList() {
        return new GameList();
    }

    /**
     * Create an instance of {@link GameListResponse }
     * 
     */
    public GameListResponse createGameListResponse() {
        return new GameListResponse();
    }

    /**
     * Create an instance of {@link GameInfo }
     * 
     */
    public GameInfo createGameInfo() {
        return new GameInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GameListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Controller/", name = "GameListResponse")
    public JAXBElement<GameListResponse> createGameListResponse(GameListResponse value) {
        return new JAXBElement<GameListResponse>(_GameListResponse_QNAME, GameListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GameList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Controller/", name = "GameList")
    public JAXBElement<GameList> createGameList(GameList value) {
        return new JAXBElement<GameList>(_GameList_QNAME, GameList.class, null, value);
    }

}

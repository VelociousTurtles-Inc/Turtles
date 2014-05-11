
package ServicesTypes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ServicesTypes package. 
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

    private final static QName _GetGameBoardGraph_QNAME = new QName("http://Services/", "getGameBoardGraph");
    private final static QName _GetDeckList_QNAME = new QName("http://Services/", "getDeckList");
    private final static QName _GetDeckListResponse_QNAME = new QName("http://Services/", "getDeckListResponse");
    private final static QName _GetPlayerCards_QNAME = new QName("http://Services/", "getPlayerCards");
    private final static QName _PlayCard_QNAME = new QName("http://Services/", "playCard");
    private final static QName _GetPlayerCardsResponse_QNAME = new QName("http://Services/", "getPlayerCardsResponse");
    private final static QName _GetGameState_QNAME = new QName("http://Services/", "getGameState");
    private final static QName _GetGameBoardGraphResponse_QNAME = new QName("http://Services/", "getGameBoardGraphResponse");
    private final static QName _GetGameStateResponse_QNAME = new QName("http://Services/", "getGameStateResponse");
    private final static QName _PlayCardResponse_QNAME = new QName("http://Services/", "playCardResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ServicesTypes
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPlayerCardsResponse }
     * 
     */
    public GetPlayerCardsResponse createGetPlayerCardsResponse() {
        return new GetPlayerCardsResponse();
    }

    /**
     * Create an instance of {@link PlayCardResponse }
     * 
     */
    public PlayCardResponse createPlayCardResponse() {
        return new PlayCardResponse();
    }

    /**
     * Create an instance of {@link GetGameStateResponse }
     * 
     */
    public GetGameStateResponse createGetGameStateResponse() {
        return new GetGameStateResponse();
    }

    /**
     * Create an instance of {@link GetGameBoardGraphResponse }
     * 
     */
    public GetGameBoardGraphResponse createGetGameBoardGraphResponse() {
        return new GetGameBoardGraphResponse();
    }

    /**
     * Create an instance of {@link GetGameState }
     * 
     */
    public GetGameState createGetGameState() {
        return new GetGameState();
    }

    /**
     * Create an instance of {@link GetGameBoardGraph }
     * 
     */
    public GetGameBoardGraph createGetGameBoardGraph() {
        return new GetGameBoardGraph();
    }

    /**
     * Create an instance of {@link GetDeckListResponse }
     * 
     */
    public GetDeckListResponse createGetDeckListResponse() {
        return new GetDeckListResponse();
    }

    /**
     * Create an instance of {@link GetDeckList }
     * 
     */
    public GetDeckList createGetDeckList() {
        return new GetDeckList();
    }

    /**
     * Create an instance of {@link GetPlayerCards }
     * 
     */
    public GetPlayerCards createGetPlayerCards() {
        return new GetPlayerCards();
    }

    /**
     * Create an instance of {@link PlayCard }
     * 
     */
    public PlayCard createPlayCard() {
        return new PlayCard();
    }

    /**
     * Create an instance of {@link GameInfo }
     * 
     */
    public GameInfo createGameInfo() {
        return new GameInfo();
    }

    /**
     * Create an instance of {@link Turtle }
     * 
     */
    public Turtle createTurtle() {
        return new Turtle();
    }

    /**
     * Create an instance of {@link Field }
     * 
     */
    public Field createField() {
        return new Field();
    }

    /**
     * Create an instance of {@link BoardGraph }
     * 
     */
    public BoardGraph createBoardGraph() {
        return new BoardGraph();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGameBoardGraph }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services/", name = "getGameBoardGraph")
    public JAXBElement<GetGameBoardGraph> createGetGameBoardGraph(GetGameBoardGraph value) {
        return new JAXBElement<GetGameBoardGraph>(_GetGameBoardGraph_QNAME, GetGameBoardGraph.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDeckList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services/", name = "getDeckList")
    public JAXBElement<GetDeckList> createGetDeckList(GetDeckList value) {
        return new JAXBElement<GetDeckList>(_GetDeckList_QNAME, GetDeckList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDeckListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services/", name = "getDeckListResponse")
    public JAXBElement<GetDeckListResponse> createGetDeckListResponse(GetDeckListResponse value) {
        return new JAXBElement<GetDeckListResponse>(_GetDeckListResponse_QNAME, GetDeckListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPlayerCards }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services/", name = "getPlayerCards")
    public JAXBElement<GetPlayerCards> createGetPlayerCards(GetPlayerCards value) {
        return new JAXBElement<GetPlayerCards>(_GetPlayerCards_QNAME, GetPlayerCards.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlayCard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services/", name = "playCard")
    public JAXBElement<PlayCard> createPlayCard(PlayCard value) {
        return new JAXBElement<PlayCard>(_PlayCard_QNAME, PlayCard.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPlayerCardsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services/", name = "getPlayerCardsResponse")
    public JAXBElement<GetPlayerCardsResponse> createGetPlayerCardsResponse(GetPlayerCardsResponse value) {
        return new JAXBElement<GetPlayerCardsResponse>(_GetPlayerCardsResponse_QNAME, GetPlayerCardsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGameState }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services/", name = "getGameState")
    public JAXBElement<GetGameState> createGetGameState(GetGameState value) {
        return new JAXBElement<GetGameState>(_GetGameState_QNAME, GetGameState.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGameBoardGraphResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services/", name = "getGameBoardGraphResponse")
    public JAXBElement<GetGameBoardGraphResponse> createGetGameBoardGraphResponse(GetGameBoardGraphResponse value) {
        return new JAXBElement<GetGameBoardGraphResponse>(_GetGameBoardGraphResponse_QNAME, GetGameBoardGraphResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGameStateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services/", name = "getGameStateResponse")
    public JAXBElement<GetGameStateResponse> createGetGameStateResponse(GetGameStateResponse value) {
        return new JAXBElement<GetGameStateResponse>(_GetGameStateResponse_QNAME, GetGameStateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlayCardResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Services/", name = "playCardResponse")
    public JAXBElement<PlayCardResponse> createPlayCardResponse(PlayCardResponse value) {
        return new JAXBElement<PlayCardResponse>(_PlayCardResponse_QNAME, PlayCardResponse.class, null, value);
    }

}

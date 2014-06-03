package Chat.Message;

/**
 * Created by main on 5/28/14.
 */
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Message implements Serializable {
    final public String author, text;
    final public Date time;
    final public int id;
    private static int idGen = 42;

    public Message(String nauthor, String ntext, Date ntime) {
        author = nauthor;
        text = ntext;
        time = ntime;
        id = idGen++;
    }

    public static String MessagesToText(List<Message> someMessages) {
        StringBuilder temp = new StringBuilder();
        for(Message a : someMessages)
            temp.append(a.author + ": " + a.text + "\n");
        return temp.toString();
    }

}
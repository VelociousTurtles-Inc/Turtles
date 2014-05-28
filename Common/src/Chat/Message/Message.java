package Chat.Message;

/**
 * Created by main on 5/28/14.
 */
import java.util.Date;

public class Message {
    public String author, text;
    public Date time;

    public Message(String nauthor, String ntext, Date ntime) {
        author = nauthor;
        text = ntext;
        time = ntime;
    }

}
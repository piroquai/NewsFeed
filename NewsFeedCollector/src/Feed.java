import java.util.ArrayList;
import java.util.List;

/*
 * Stores an RSS feed
 */
public class Feed {

    private final String title;
    private final String link;
    private final String description;


    private final List<FeedMessage> entries = new ArrayList<FeedMessage>();

    Feed(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;

    }

    List<FeedMessage> getMessages() {
        return entries;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

}

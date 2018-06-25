import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

public class Start {

    public static void main(String[] args) throws IOException, SQLException {
        RSScontrol.clear();
        int i = 0;
        List<String> news;
        news = Files.readAllLines(Paths.get("/root/newsfeeds.txt"), StandardCharsets.UTF_8); //path for linux server
//        news = Files.readAllLines(Paths.get("/root/newsfeeds.txt"), StandardCharsets.UTF_8); uncomment for using in IDEA
        for (String line : news) {
            NewsFeedCollector.parseNews(line, i);
            i++;
            System.out.println(line);
        }
    }
}

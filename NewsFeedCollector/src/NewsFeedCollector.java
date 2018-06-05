import java.sql.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsFeedCollector {


    public static void main(String[] args) throws SQLException {

        try (
                Connection con = DBConnection.getConnection();
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rst = stmt.executeQuery("SELECT * FROM Feed1");
        ) {
            rst.last();
            System.out.println("connected");

            RSSFeedParser parser = new RSSFeedParser(
//                    "http://feeds.bbci.co.uk/news/rss.xml?edition=int");
                    "http://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml");
            Feed feed = parser.readFeed();
            RSScontrol create = new RSScontrol();

            for (FeedMessage message : feed.getMessages()) {

                try {
                    String link = message.link;
                    String description = message.description;
                    String title = message.title;
//                    String media = message.media;
//                    String pubDate = message.pubDate;

//parsing article
                    MercuryParser mercuryParser = new MercuryParser();
                    String parsedArticle = mercuryParser.sendGet(link);

                    final Pattern patternImage = Pattern.compile("lead_image_url\":\"(.+?)\"");
                    final Pattern patternDate = Pattern.compile("date_published\":\"(.+?)\"");
                    final Pattern patternContent = Pattern.compile("content\":\"(.+?)\",\"author");
                    final Matcher matcherContent = patternContent.matcher(parsedArticle);
                    final Matcher matcherDate = patternDate.matcher(parsedArticle);
                    final Matcher matcherImage = patternImage.matcher(parsedArticle);

                    matcherContent.find();
                    matcherDate.find();
                    matcherImage.find();
                    String pubDate = matcherDate.group(1);
                    String media = matcherImage.group(1);
                    String content = matcherContent.group(1);

                    create.add(link, description, title, media, pubDate, content);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(message);

            }


        } catch (SQLException e) {
            System.err.print(e);
        }
    }


}

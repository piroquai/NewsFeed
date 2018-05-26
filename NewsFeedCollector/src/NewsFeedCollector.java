import java.sql.*;
import java.util.Scanner;

public class NewsFeedCollector {


    public static void main(String[] args) throws SQLException {

        try (
                Connection con = DBConnection.getConnection();
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rst = stmt.executeQuery("SELECT * FROM Feed1");
        ) {
            rst.last();
            System.out.println("connected");
//            System.out.println(rst.getRow());
//
//            System.out.println("enter 1");
//            Scanner scanner = new Scanner(System.in);
//            String link = scanner.nextLine();
//            System.out.println("enter 2");
//            String description = scanner.nextLine();
//            System.out.println("enter 3");
//            String title = scanner.nextLine();
            RSSFeedParser parser = new RSSFeedParser(
                    "https://news.yandex.ru/auto.rss");
            Feed feed = parser.readFeed();
            RSScontrol create = new RSScontrol();
            for (FeedMessage message : feed.getMessages()) {

                try {
                    String link = message.link;
                    String description = message.description;
                    String title = message.title;

                    create.add(link, description, title);

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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class NewsFeedCollector  {

    static void parseNews(String url, int portalNumber) {
        //parsing feed
        RSSFeedParser parser = new RSSFeedParser( url, portalNumber);

        Feed feed = parser.readFeed();
        RSScontrol create = new RSScontrol();

        for (FeedMessage message : feed.getMessages()) {
            try {
                String link = message.link;
                String description = message.description;
                String title = message.title;
//parsing article (в идеале надо вынести в отдельный метод)
                MercuryParser mercuryParser = new MercuryParser();
                String parsedArticle = mercuryParser.sendGet(link);

                final Pattern patternImage = Pattern.compile("lead_image_url\":\"(.+?)\"");
                final Pattern patternDate = Pattern.compile("date_published\":\"(.+?)\"");
                final Pattern patternContent = Pattern.compile("content\":\"(.+?)\",\"");
                final Matcher matcherContent = patternContent.matcher(parsedArticle);
                final Matcher matcherDate = patternDate.matcher(parsedArticle);
                final Matcher matcherImage = patternImage.matcher(parsedArticle);

                matcherContent.find();
                matcherDate.find();
                matcherImage.find();
                String pubDate = matcherDate.group(1);
                String media = matcherImage.group(1);
                String content = matcherContent.group(1);

                create.add(link, description, title, media, pubDate, content, portalNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(message.link);
            System.out.println("================");
        }
    }
}

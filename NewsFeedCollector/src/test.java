import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) throws Exception {
        MercuryParser mercuryParser = new MercuryParser();
        String link1 = "https://www.nytimes.com/2018/06/04/technology/apple-shuns-apology-tour.html";
        String parsedArticle = mercuryParser.sendGet(link1);

        final Pattern patternImage = Pattern.compile("lead_image_url\":\"(.+?)\"");
        final Pattern patternDate = Pattern.compile("date_published\":\"(.+?)\"");
        final Pattern patternContent = Pattern.compile("content\":\"(.+?)\",\"author" );
        final Matcher matcherContent = patternContent.matcher(parsedArticle);
        final Matcher matcherDate = patternDate.matcher(parsedArticle);
        final Matcher matcherImage = patternImage.matcher(parsedArticle);

        matcherContent.find();
        matcherDate.find();
        matcherImage.find();

                System.out.println(matcherContent.group(1));
        System.out.println(matcherDate.group(1));
        System.out.println(matcherImage.group(1));


// String media = matcher.group(1);
    }
}

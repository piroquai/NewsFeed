import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MercuryParser {
//    public static void main(String[] args) throws Exception {
//        MercuryParser http = new MercuryParser();
//        System.out.println("Testing 1 - Send Http GET request");
//        http.sendGet("https://trackchanges.postlight.com/building-awesome-cms-f034344d8ed");
//    }

    // HTTP GET request
    public String sendGet(String link) throws Exception {
        String url = "https://mercury.postlight.com/parser?url=" + link;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("x-api-key", "1m7VtzXHQM4tUBtDBgS4drmTcOQ7IlB4UDP1Ralz");
//        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();

        String jsonString = in.readLine();
        System.out.println(jsonString);
        //        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
        in.close();
        return jsonString;
    }

}
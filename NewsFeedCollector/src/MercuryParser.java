import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class MercuryParser {
    // HTTP GET request
    String sendGet(String link) throws Exception {
        String url = "https://mercury.postlight.com/parser?url=" + link;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("x-api-key", "1m7VtzXHQM4tUBtDBgS4drmTcOQ7IlB4UDP1Ralz");
//        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String jsonString = in.readLine();
        System.out.println(jsonString);
        in.close();
        return jsonString;
    }
}
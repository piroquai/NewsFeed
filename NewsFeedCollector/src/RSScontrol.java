import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RSScontrol {
    public RSScontrol(){

    }
    public void add (String link,String description, String title, String media, String pubDate, String content){
        String sql = "INSERT INTO Feed1 (link, description, title, media, pubDate, content) VALUES (?, ?, ?, ?, ?, ?)";
        try(            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        )
        {
            pstmt.setString(1,link);
            pstmt.setString(2,description);
            pstmt.setString(3,title);
            pstmt.setString(4, media);
            pstmt.setString(5, pubDate);
            pstmt.setString(6, content);

            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

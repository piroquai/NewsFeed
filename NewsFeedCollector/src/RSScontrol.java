import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RSScontrol {
    public RSScontrol(){

    }
    public void add (String link,String description, String title){
        String sql = "INSERT INTO rssentrys (link, description, title) VALUES (?, ?, ?)";
        try(            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        )
        {
            pstmt.setString(1,link);
            pstmt.setString(2,description);
            pstmt.setString(3,title);
               pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

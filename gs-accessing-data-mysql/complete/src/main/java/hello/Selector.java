package hello;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.ResultSet;

import java.sql.*;

public class Selector {

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    private String table;


    public JSONArray getSelected (String table) throws Exception {
        Connection con = DBConnection.getConnection();
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rst = stmt.executeQuery("SELECT * FROM "+table);
        return convertToJSON(rst);
    }

//    public static void main(String[] args) {
//
//
//        try (
//                Connection con = DBConnection.getConnection();
//                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                ResultSet rst = stmt.executeQuery("SELECT * FROM feed1");
//        ) {
//
//            System.out.println(convertToJSON(rst));
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//
//    }


    public static JSONArray convertToJSON(ResultSet resultSet)
            throws Exception {
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            for (int i = 0; i < total_rows; i++) {
                JSONObject obj = new JSONObject();
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                        .toLowerCase(), resultSet.getObject(i + 1));
                jsonArray.put(obj);
            }
        }
        return jsonArray;
    }
}

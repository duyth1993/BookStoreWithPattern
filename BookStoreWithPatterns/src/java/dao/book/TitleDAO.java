package dao.book;

import common.utility.MysqlDbConnect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.book.Title;

public class TitleDAO {

    public TitleDAO() {
    }

    public ArrayList<Title> getAllTitle() throws SQLException {
        String query = "SELECT * FROM tbltitle";
        ArrayList<Title> arrTitle = new ArrayList();
        PreparedStatement ps = MysqlDbConnect.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");

            Title title = new Title(id, name, description);

            arrTitle.add(title);
            System.out.println(title);
        }
        
        return arrTitle;
    }

    public Title getTitleByName(String name) throws SQLException {
        Title title = null;
        String sql = "SELECT * FROM tbltitle WHERE title_name = '" + name + "'";
        PreparedStatement ps = MysqlDbConnect.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String description = rs.getString("description");
            title = new Title(id, name, description);
        }
        
        return title;
    }

    public Title getTitle(int id) throws SQLException {
        Title title = null;
        String sql = "SELECT * FROM tbltitle WHERE id = ?";
        PreparedStatement ps = MysqlDbConnect.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Title(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3)
            );
        }
        
        return title;
    }
}

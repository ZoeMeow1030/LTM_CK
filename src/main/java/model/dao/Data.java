package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.bean.PDF2XLS;

public class Data {
    public List<PDF2XLS> getStatusFromUser(String user) {
        try {
            List<PDF2XLS> list = new ArrayList<PDF2XLS>();
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                model.dao.ConnectInfo.url,
                model.dao.ConnectInfo.dbuser,
                model.dao.ConnectInfo.dbpass
            );    
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM PDF2XLS WHERE User = '" + user + "'";
            ResultSet rs = stmt.executeQuery(query);         

            while (rs.next()) {
                PDF2XLS item = new PDF2XLS();
                item.setID(rs.getInt("ID"));
                item.setUser(rs.getString("User"));
                item.setSourceName(rs.getString("SourceName"));
                item.setSourcePath(rs.getString("SourcePath"));
                item.setTargetPath(rs.getString("TargetPath"));
                item.setResult(rs.getInt("Result"));

                list.add(item);
            }

            return list;
        }
        catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public void addStatus(PDF2XLS pdf) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                model.dao.ConnectInfo.url,
                model.dao.ConnectInfo.dbuser,
                model.dao.ConnectInfo.dbpass
            );
            // https://stackoverflow.com/a/10167435    
            String query = "INSERT INTO pdf2xls (User, SourceName, SourcePath, TargetPath, Result) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, pdf.getUser());
            ps.setString(2, pdf.getSourceName());
            ps.setString(3, pdf.getSourcePath());
            ps.setString(4, pdf.getSourcePath());
            ps.setInt(5, pdf.getResult());

        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

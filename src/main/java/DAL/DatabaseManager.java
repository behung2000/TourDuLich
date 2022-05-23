package DAL;

import GUI.JFrameMess;

import javax.swing.*;
import java.sql.*;

public class DatabaseManager {
    static Connection connection;
    static Statement statement;
    static PreparedStatement preparedStatement;

    //Connection
    public static void ConnectionDB(){
        String host = "localhost";
        String port = "3306";
        String dbname = "tour_dulich";
        String dbuser = "root";
        String dbpwd = "";

        String dbPath ="jdbc:mysql://"+host+":" + port + "/" + dbname + "?useUnicode=yes&characterEncoding=UTF-8";

        try{
            connection = (Connection) DriverManager.getConnection(dbPath, dbuser, dbpwd);
            statement = connection.createStatement();
            System.out.println("connected");
        }
        catch (SQLException e){
            System.out.println("ERROR -> DatabaseManager -> ConectionDB -> "+e.getMessage());
            JFrameMess jFrameMess = new JFrameMess();
            jFrameMess.mess("Database","disconnect mysql");
            System.exit(0);
        }
    }

    //Read 1 table
    public static ResultSet readTable(String nameTable, String where, String order){
        String sql = "SELECT * FROM " + nameTable + " WHERE " + where + " ORDER BY " + order;
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);
        }
        catch (SQLException e) {
            System.out.println("ERROR -> DatabaseManager -> readTable -> "+e.getMessage());
        }
        return rs;
    }

    //Read 1 table extends
    public static ResultSet readTableExtended(String select, String nameTable, String where, String order){
        String sql = "SELECT "+select+" FROM " + nameTable + " WHERE " + where + " ORDER BY " + order;
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);
        }
        catch (SQLException e) {
            System.out.println("ERROR -> DatabaseManager -> readTable -> "+e.getMessage());
        }
        return rs;
    }

    //Insert 1 row
    public static boolean insertrow(String name, String column, String values)
    {
        String sql = "INSERT INTO "+name+" ("+column+") "+"VALUES ("+values+")";
        try{
            statement.executeUpdate(sql);
            return true;
        }
        catch (SQLException e)
        {
            System.out.println("ERROR -> DatabaseManager -> insertrow -> "+e.getMessage());
            JOptionPane.showInternalMessageDialog(null,"Thêm thất bại ! ");
            return false;
        }
    }

    //Update 1 row
    public static boolean updaterow(String name, String set, String where)
    {
        String sql = "UPDATE "+name+" SET "+set+" where "+where;
        try{
            statement.executeUpdate(sql);
            return true;
        }
        catch (SQLException e)
        {
            System.out.println("ERROR -> DatabaseManager -> updaterow -> "+e.getMessage());
            JOptionPane.showInternalMessageDialog(null,"Cập nhật thất bại ! ");
            return false;
        }
    }

    //Delete 1 row
    public static boolean deleterow(String name, String where)
    {
        String sql = "DELETE FROM "+name+" where "+where;
        try{
            statement.executeUpdate(sql);
            return true;
        }
        catch (SQLException e)
        {
            System.out.println("ERROR -> DatabaseManager -> deleterow -> "+e.getMessage());
            JOptionPane.showInternalMessageDialog(null,"Xóa thất bại ! ");
            return false;
        }
    }

    //Test
    /*
    public static void main(String[] args){
        ConnectionDB();
    }
     */
}

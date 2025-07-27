package project.Business;
import project.Business.ConnectDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;

public class stock {
    int item_code;
    String namep;
    int qty;
    Connection conn;
    Scanner sc;
    public stock(Connection conn,Scanner sc) {
        this.conn = conn;
        this.sc = sc;
    }
    public void views(){
        String query = "select * from stock;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                System.out.println("+-----------------------------------+");
                System.out.println("Item code : "+rs.getInt("item_code"));
                System.out.println("Name : "+rs.getString("name"));
                System.out.println("Quantity : "+rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }
    public void insert()
    {
        System.out.print("enter the item code : ");
        item_code = sc.nextInt();
        sc.nextLine();
        System.out.print("\nenter the name of new product : ");
        namep = sc.nextLine();
        System.out.print("\nenter the quantity");
        qty = sc.nextInt();
        String query = "INSERT INTO stock(item_code,name,quantity) VALUES ("+item_code+",'"+namep+"',"+qty+");";
        try {
            Statement stmt = conn.createStatement();
            int rs = stmt.executeUpdate(query);
            if(rs>0)
                System.out.println("inventory updated");
            else
                System.out.println("system failed to update");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void update(){
        System.out.println("Enter item code to update : ");
        item_code = sc.nextInt();
        System.out.println("enter new quantity : ");
        qty = sc.nextInt();
        String query = "UPDATE stock SET quantity = "+qty+" WHERE item_code = "+item_code+";";
        try {
            Statement stmt = conn.createStatement();
            int rs = stmt.executeUpdate(query);
            if(rs>0)
                System.out.println("quantity updated");
            else
                System.out.println("system failed to update");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }

}

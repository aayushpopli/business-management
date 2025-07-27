package project.Business;

import java.sql.*;
import java.util.Scanner;

public class billing { int invoice;
    long contact;
    String gst;
    int amount;
    String name;
    Date date;
    Connection conn;
    Scanner sc;
    int finalquantity,oldquantity;
    public billing(Connection conn,Scanner sc){
        this.conn = conn;
        this.sc = sc;
    }
    void createbill() {
        System.out.print("enter the name of the customer : ");
        name = sc.nextLine();
        System.out.print("\nenter the gst number(if there is no gst number just enter 0): ");
        gst = sc.nextLine();
        System.out.print("\nenter the billing amount : ");
        amount = sc.nextInt();
        System.out.print("\nenter the contact number : ");
        contact = sc.nextLong();
        System.out.println("enter the item code you want to sell : ");
        int item_code = sc.nextInt();
        System.out.println("enter the quantity to sell ");
        int quantity = sc.nextInt();

        String query = "INSERT INTO billdesk(name,contact,gst,cost) VALUES('" + name + "'," + contact + ",'" + gst + "'," + amount + ");";
        String query2 = "select quantity from stock where item_code = " + item_code + ";";

        try {
            Statement stmt = conn.createStatement();
            int rs = stmt.executeUpdate(query);
            ResultSet rsk = stmt.executeQuery(query2);
            if (rsk.next()) {  // <- this is required
                oldquantity = rsk.getInt("quantity");
                if (oldquantity < quantity) {
                    System.out.println("Stock not available, please check quantity before moving forward...");
                    System.exit(0);
                }
                finalquantity = oldquantity - quantity;
            } else {
                System.out.println("Item code not found in stock.");
                System.exit(0);
            }


            if (rs > 0)
                System.out.println("your bill is : ");
            else
                System.out.println("unable to generate the bill");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String query3 = " UPDATE stock SET quantity = " + finalquantity + " WHERE item_code = " + item_code + ";";
        try {
            Statement stmt = conn.createStatement();
            int ups = stmt.executeUpdate(query3);
            if (ups > 0)
                System.out.println("stock is updated");
            else
                System.out.println("please reenter the stock");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        query = "select * from billdesk where name = '" + name + "'AND cost = " + amount + ";";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println("+-----------------------------------+");
                System.out.println("Invoice no. : " + rs.getInt("invoice_no"));
                System.out.println("Customer name : " + rs.getString("name"));
                System.out.println("Contact no." + rs.getLong("contact"));
                System.out.println("Gst : " + rs.getString("gst"));
                System.out.println("Amount : " + rs.getInt("cost"));
                //System.out.println("Date : "+rs.getDate("billing_date") );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



        public void searchc() {
        System.out.print("enter customer name : ");
        String namep = sc.nextLine();
        String query = "select * from billdesk where name = '"+namep+"';";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                System.out.println("+-----------------------------------+");
                System.out.println("Invoice no. : "+rs.getInt("invoice_no"));
                System.out.println("Customer name : "+rs.getString("name"));
                System.out.println("Contact no."+rs.getLong("contact"));
                System.out.println("Gst : "+rs.getString("gst"));
                System.out.println("Amount : "+rs.getInt("cost"));
                System.out.println("Date : "+rs.getDate("billing_date") );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package project.Business;
import project.Business.ConnectDB;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class employeedata {
    int id;
    String name;
    long contact;
    Date date_of_joining;
    int salary;
    Connection conn;
    Scanner sc;
    public employeedata(Connection conn,Scanner sc) {
        this.conn = conn;
        this.sc = sc;

    }

    public void search(String namee) {
       String query = "SELECT * FROM employee where name ='"+namee+"';";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                System.out.println("+-----------------------------------+");
                System.out.println("Employee ID : "+rs.getInt("id"));
                System.out.println("Employee name : "+rs.getString("name"));
                System.out.println("Contact no."+rs.getLong("contact"));
                System.out.println("Date of joining : "+rs.getString("date_of_joining"));
                System.out.println("Salary : "+rs.getInt("salary"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removee(int id) {
        String query = "delete from employee where id=" + id + ";";
        try {
            Statement stmt = conn.createStatement();
            int rs = stmt.executeUpdate(query);
            if (rs > 0)
                System.out.println("employee data removed");
            else
                System.out.println("employee data unable to remove");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add() {
        System.out.print("enter the name of the new employee : ");
        name = sc.nextLine();
//        System.out.print("\nenter the date of joining ");
//        String userInput = sc.nextLine();
//
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//            date_of_joining = (Date) (Date) (Date) sdf.parse(userInput);
//
//        } catch (Exception e) {
//            System.out.println("Invalid date format. Please enter in dd-MM-yyyy format.");
//        }
        System.out.print("\nenter the salary : ");
        salary = sc.nextInt();
        System.out.print("\nenter the contact nummber : ");
        contact = sc.nextLong();
        String query = "INSERT INTO employee(name,contact,salary) VALUES('" + name + "'," + contact + "," + salary + ");";

        try {
            Statement stmt = conn.createStatement();
            int rs = stmt.executeUpdate(query);
            if (rs>0)
                System.out.println("employee data added");
            else
                System.out.println("employee data unable to add");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



package project.Business;
import project.Business.ConnectDB;
import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection conn = ConnectDB.getConnection(); // Only one connection created here

        if (conn == null) {
            System.out.println("Database connection failed.");
        }
        boolean menu1 = true;
        while (menu1 == true) {
            System.out.println("---hey! welcome to business desk---");
            System.out.println("enter 1 for billingdesk");
            System.out.println("enter 2 for employee data");
            System.out.println("enter 3 for stock information");
            System.out.println("enter 4 to exit");
            Scanner sc = new Scanner(System.in);
            int selection = sc.nextInt();

            if (selection == 1) // billingdesk
            {
                billing bd = new billing(conn,sc);
                boolean menu2 = true;
                while (menu2 == true) {
                    System.out.println("welecome to billing desk");
                    System.out.println("enter 1 to create a new bill");
                    System.out.println("enter 2 to search for old invoice");
                    System.out.println("enter 3 to go back to previous menu");
                    int s = sc.nextInt();
                    switch (s) {
                        case 1:
                            sc.nextLine();
                            bd.createbill();
                            break;
                        case 2:
                            sc.nextLine();
                            bd.searchc();
                            break;
                        case 3:
                            menu2 = false;
                            break;
                        default:
                            System.out.print("\nenter correct number : ");
                            break;
                    }

                }
            }
            else if (selection == 2) // employee
            {
                employeedata ed = new employeedata(conn,sc);
                boolean menu3 = true;
                while (menu3 == true) {
                    System.out.println("welcome to employee data");
                    System.out.println("enter 1 to add new employee data");
                    System.out.println("enter 2 to search for employee details");
                    System.out.println("enter 3 to remove employee");
                    System.out.println("enter 4 to go to previous menu");
                    int s = sc.nextInt();
                    switch (s) {
                        case 1:
                            sc.nextLine();
                            ed.add();
                            break;
                        case 2:
                            sc.nextLine();
                            System.out.println("enter the employee name you want to search for");
                            String namee = sc.nextLine();
                            ed.search(namee);
                            break;
                        case 3:
                            System.out.println("enter the employee id to remove");
                            int id = sc.nextInt();
                            ed.removee(id);
                            break;
                        case 4:
                            menu3 = false;
                        default:
                            System.out.println("enter correct number");
                            break;
                    }
                }
            }
            else if (selection == 3) //stock
            {
                stock st = new stock(conn,sc);
                boolean menu4 = true;
                while (menu4) {
                    System.out.println("Welcome to inventory");
                    System.out.println("enter 1 to view inventory");
                    System.out.println("enter 2 to add new item in inventory");
                    System.out.println("enter 3 to update quantity in inventory");
                    System.out.println("enter 4 to go to previous menu");
                    int s = sc.nextInt();
                    switch (s) {
                        case 1:
                            sc.nextLine();
                            st.views(); // view stock
                            break;
                        case 2:
                            sc.nextLine();
                            st.insert(); // update stock
                            break;
                        case 3:
                            sc.nextLine();
                            st.update();
                            break;
                        case 4:
                            menu4 = false;
                        default:
                            break;
                    }
                }


            } else if (selection == 4) {
                System.exit(0);

            } else {
                System.out.println("enter the correct number");
            }
            }
            }
        }



package JavaDocument;

import java.util.List;
import java.util.Scanner;
import java.util.SimpleTimeZone;

import static JavaDocument.GsonReader.*;

public class ViewStudent {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while (run){
            System.out.println("1. Add Student");
            System.out.println("2. Display Student");
            System.out.println("3. Search Name");
            System.out.println("4. Search Email");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter id: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter address: ");
                    String address = scanner.nextLine();
                    System.out.println("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.println("Enter phone: ");
                    String phone = scanner.nextLine();
                    System.out.println("Enter (YYYY-MM-DD): ");
                    String bod = scanner.nextLine();
                    Student newStudent = new Student(id, name, address, email, phone, bod);
                    addNewStudent(newStudent);
                    System.out.println("Student added");
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    System.out.println("Enter name: ");
                    String searchName = scanner.nextLine();
                    searchByName(searchName);
                    break;
                case 4:
                    System.out.println("Enter email: ");
                    String searchEmail = scanner.nextLine();
                    searchByEmail(searchEmail);
                    break;
                case 5:
                    run = false;
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize the system with default passwords
        ManagementSystem system = new ManagementSystem("Admin123", "User1234");
        Scanner sc = new Scanner(System.in);

        int choice;
        do {
            System.out.println("===== Welcome to Smart Home System =====");
            System.out.println("1. Enter Admin Mode");
            System.out.println("2. Enter Control Mode (User Mode)");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();
            //depending on number we enter which mode
            switch (choice) {
                case 1:
                    system.enterAdminMode1();  
                    break;
                case 2:
                    system.enterControlMode(); 
                    break;
                case 3:
                    System.out.println("Thank you for using Smart Home System, have a good day!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 3);
    }
}

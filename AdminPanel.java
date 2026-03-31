import java.util.Scanner;

public class AdminPanel {
    private Scanner scanner = new Scanner(System.in);
    private UserService userService = new UserService();
    private BikeService bikeService = new BikeService();
    private RentalService rentalService = new RentalService();

    public void userManagementOptions() {
        int choice;
        do {
            System.out.println("\n1. Add Users");
            System.out.println("2. View Users");
            System.out.println("3. Remove Users");
            System.out.println("4. Update Users");
            System.out.println("5. Demo Rental");
            System.out.println("6. Exit");
            System.out.print("Choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: userService.addUser(scanner); break;
                case 2: userService.viewUsers(); break;
                case 3: 
                    System.out.print("Email to remove: ");
                    userService.removeUser(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Email to update: ");
                    userService.updateUser(scanner.nextLine(), scanner);
                    break;
                case 5: demoRental(); break;
            }
        } while (choice != 6);
    }

    private void demoRental() {
        System.out.print("Registered? (true/false): ");
        boolean registered = scanner.nextBoolean();
        scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Location: ");
        String location = scanner.nextLine();
        
        rentalService.startRental(registered, email, location, bikeService);
        rentalService.viewActiveRentals();
    }
}
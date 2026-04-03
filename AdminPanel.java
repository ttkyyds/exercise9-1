import java.util.Scanner;

public class AdminPanel {
    private Scanner scanner = new Scanner(System.in);
    private UserService userService = new UserService();
    private BikeService bikeService = new BikeService();
    private RentalService rentalService;

    public AdminPanel() {
        this.rentalService = new RentalService(bikeService);
    }

    public void userManagementOptions() {
        int choice;
        do {
            System.out.println("\n=== Admin Panel ===");
            System.out.println("1. Add Users");
            System.out.println("2. View Users");
            System.out.println("3. Remove Users");
            System.out.println("4. Update Users");
            System.out.println("5. Demo Rental");
            System.out.println("6. View System Logs");
            System.out.println("7. Manage Pending Bike Requests");
            System.out.println("8. Exit");
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
                case 6: viewSystemLogs(); break;
                case 7: managePendingRequests(); break;
                case 8: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 8);
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

    private void viewSystemLogs() {
        System.out.println("\n=== System Logs ===");
        if (bikeService.getSystemLogsStack().isEmpty()) {
            System.out.println("No system logs available.");
        } else {
            for (ERyderLog log : bikeService.getSystemLogsStack()) {
                System.out.println(log);
            }
        }
    }

    private void managePendingRequests() {
        int subChoice;
        do {
            System.out.println("\n=== Manage Pending Bike Requests ===");
            System.out.println("1. View Queue");
            System.out.println("2. Update Queue");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            
            subChoice = scanner.nextInt();
            scanner.nextLine();

            switch (subChoice) {
                case 1: viewQueue(); break;
                case 2: updateQueue(); break;
                case 3: System.out.println("Returning to main menu..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (subChoice != 3);
    }

    private void viewQueue() {
        System.out.println("\n=== Pending Bike Requests Queue ===");
        if (bikeService.getBikeRequestQueue().isEmpty()) {
            System.out.println("No pending requests in the queue.");
        } else {
            int count = 1;
            for (BikeRequest request : bikeService.getBikeRequestQueue()) {
                System.out.println(count + ". " + request);
                count++;
            }
        }
    }

    private void updateQueue() {
        if (bikeService.getBikeRequestQueue().isEmpty()) {
            System.out.println("Queue is empty. Nothing to update.");
        } else {
            BikeRequest removed = bikeService.getBikeRequestQueue().poll();
            System.out.println("Removed request: " + removed);
            System.out.println("Queue updated successfully.");
        }
    }
}
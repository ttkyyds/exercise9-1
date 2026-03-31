import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private List<RegisteredUsers> registeredUsersList = new ArrayList<>();

    public void addUser(Scanner scanner) {
        System.out.print("How many users to add? ");
        int numUsers = scanner.nextInt();
        scanner.nextLine();

        for (int u = 0; u < numUsers; u++) {
            System.out.println("\n--- User #" + (u + 1) + " ---");
            
            System.out.print("Full Name: ");
            String fullName = scanner.nextLine();
            
            System.out.print("Email: ");
            String email = scanner.nextLine();
            
            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String dob = scanner.nextLine();
            
            System.out.print("Card Number: ");
            long cardNum = scanner.nextLong();
            scanner.nextLine();
            
            System.out.print("Card Expiry (MM/YY): ");
            String expiry = scanner.nextLine();
            
            System.out.print("Card Provider: ");
            String provider = scanner.nextLine();
            
            System.out.print("CVV: ");
            int cvv = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("User Type (Regular/VIP): ");
            String type = scanner.nextLine();

            String[] trips = new String[3];
            for (int i = 0; i < 3; i++) {
                System.out.println("\nTrip #" + (i + 1) + ":");
                System.out.print("Date (YYYY-MM-DD): ");
                String date = scanner.nextLine();
                System.out.print("Source: ");
                String source = scanner.nextLine();
                System.out.print("Destination: ");
                String dest = scanner.nextLine();
                System.out.print("Fare: ");
                String fare = scanner.nextLine();
                System.out.print("Feedback (Enter for NULL): ");
                String feedback = scanner.nextLine();
                
                trips[i] = "Date:" + date + ",Source:" + source + 
                          ",Destination:" + dest + ",Fare:" + fare + 
                          ",Feedback:" + (feedback.isEmpty() ? "NULL" : feedback);
            }
            
            registeredUsersList.add(new RegisteredUsers(fullName, email, dob, cardNum, 
                                                       expiry, provider, cvv, type, trips));
        }
    }

    public void viewUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No users.");
        } else {
            for (RegisteredUsers user : registeredUsersList) {
                System.out.println(user);
            }
        }
    }

    public boolean removeUser(String email) {
        Iterator<RegisteredUsers> iterator = registeredUsersList.iterator();
        while (iterator.hasNext()) {
            RegisteredUsers user = iterator.next();
            if (user.getEmailAddress().equalsIgnoreCase(email)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean updateUser(String email, Scanner scanner) {
        for (RegisteredUsers user : registeredUsersList) {
            if (user.getEmailAddress().equalsIgnoreCase(email)) {
                System.out.print("New full name (Enter to skip): ");
                String name = scanner.nextLine();
                if (!name.isEmpty()) user.setFullName(name);
                
                System.out.print("New email (Enter to skip): ");
                String newEmail = scanner.nextLine();
                if (!newEmail.isEmpty()) user.setEmailAddress(newEmail);
                
                return true;
            }
        }
        return false;
    }
}
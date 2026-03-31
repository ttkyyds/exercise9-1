/*import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class BikeRental {
    private boolean isRegisteredUser;
    private String emailAddress;
    private String location;
    private LocalDateTime tripStartTime;
    private String bikeID;
    private boolean locationValid;
    private ActiveRental activeRental;
    private LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();

    public void simulateApplicationInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("This is the simulation of the e-bike rental process.");
        System.out.print("Is the user registered? (true/false): ");
        isRegisteredUser = scanner.nextBoolean();
        scanner.nextLine();
        System.out.print("Enter user email: ");
        emailAddress = scanner.nextLine();
        System.out.print("Enter desired rental location: ");
        location = scanner.nextLine();
        System.out.println("Simulating the analysis of the rental request.");
        bikeID = analyseRequest(isRegisteredUser, emailAddress, location);
        scanner.close();
        if (!locationValid) {
            System.out.println("Exiting simulation due to invalid location.");
            return;
        }
        System.out.println("Simulating e-bike reservation...");
        reserveBike(bikeID);
        System.out.println("Displaying the active rentals...");
        viewActiveRentals();
        System.out.println("Simulating the end of the trip...");
        removeTrip(bikeID);
        System.out.println("Displaying the active rentals after trip end...");
        viewActiveRentals();
    }

    private String analyseRequest(boolean isRegistered, String email, String loc) {
        if (isRegistered) {
            System.out.println("Welcome back, " + email + "!");
        } else {
            System.out.println("You're not our registered user. Please consider registering.");
            UserRegistration userReg = new UserRegistration();
            userReg.registration();
        }
        return validateLocation(loc);
    }

    private String validateLocation(String loc) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equals(loc) && bike.getIsAvailable()) {
                System.out.println("A bike is available at the location you requested.");
                locationValid = true;
                return bike.getBikeID();
            }
        }
        System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");
        locationValid = false;
        return null;
    }

    private void reserveBike(String bikeId) {
        if (bikeId != null) {
            for (Bike bike : BikeDatabase.bikes) {
                if (bike.getBikeID().equals(bikeId)) {
                    tripStartTime = LocalDateTime.now();
                    bike.setIsAvailable(false);
                    bike.setLastUsedTime(tripStartTime);
                    System.out.println("Reserving the bike with the " + bikeId + ". Please follow the on-screen instructions to locate the bike and start your pleasant journey.");
                    activeRental = new ActiveRental(bikeId, emailAddress, tripStartTime);
                    activeRentalsList.add(activeRental);
                    break;
                }
            }
        } else {
            System.out.println("Sorry, we're unable to reserve a bike at this time. Please try again later.");
        }
    }

    private void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println("No active rentals at the moment.");
        } else {
            for (ActiveRental rental : activeRentalsList) {
                System.out.println(rental);
            }
        }
    }

    private void removeTrip(String bikeId) {
        Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        while (iterator.hasNext()) {
            ActiveRental rental = iterator.next();
            if (rental.getBikeID().equals(bikeId)) {
                iterator.remove();
                break;
            }
        }
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeId)) {
                bike.setIsAvailable(true);
                bike.setLastUsedTime(LocalDateTime.now());
                System.out.println("Your trip has ended. Thank you for riding with us.");
                break;
            }
        }
    }
}*/
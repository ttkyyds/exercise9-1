import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;

public class RentalService {
    private LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();
    private BikeService bikeService;

    public RentalService(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    public boolean startRental(boolean isRegistered, String email, String location, BikeService bikeService) {
        if (isRegistered) {
            System.out.println("Welcome back, " + email + "!");
        } else {
            System.out.println("You're not our registered user. Please consider registering.");
        }

        String bikeId = bikeService.findAvailableBike(location);
        if (bikeId == null) {
            System.out.println("Sorry, no bikes are available at the location you requested.");
            return false;
        }

        ActiveRental newRental = bikeService.reserveBike(bikeId, email);
        if (newRental != null) {
            activeRentalsList.add(newRental);
            System.out.println("Bike reserved successfully.");
            
            // 记录行程开始日志
            String logEntry = "TS" + bikeId.substring(1);
            String eventDesc = "Trip started for bike " + bikeId + " by user " + email;
            ERyderLog log = new ERyderLog(logEntry, eventDesc, LocalDateTime.now());
            bikeService.getSystemLogsStack().push(log);
            
            return true;
        }
        return false;
    }

    public void endRental(String bikeId, BikeService bikeService) {
        Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        while (iterator.hasNext()) {
            ActiveRental rental = iterator.next();
            if (rental.getBikeID().equals(bikeId)) {
                iterator.remove();
                break;
            }
        }
        bikeService.releaseBike(bikeId);
        System.out.println("Rental ended.");
        
        // 记录行程结束日志
        String logEntry = "TE" + bikeId.substring(1);
        String eventDesc = "Trip ended for bike " + bikeId;
        ERyderLog log = new ERyderLog(logEntry, eventDesc, LocalDateTime.now());
        bikeService.getSystemLogsStack().push(log);
    }

    public void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println("No active rentals.");
        } else {
            for (ActiveRental rental : activeRentalsList) {
                System.out.println(rental);
            }
        }
    }
}
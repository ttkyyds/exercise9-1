import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class BikeService {
    private Deque<BikeRequest> bikeRequestQueue = new ArrayDeque<>();
    private Stack<ERyderLog> systemLogsStack = new Stack<>();

    public String findAvailableBike(String location) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equals(location) && bike.getIsAvailable()) {
                return bike.getBikeID();
            }
        }
        return null;
    }

    public ActiveRental reserveBike(String bikeId, String userEmail) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeId)) {
                bike.setIsAvailable(false);
                bike.setLastUsedTime(LocalDateTime.now());
                
                String logEntry = "BR" + bikeId.substring(1);
                String eventDesc = "Bike with " + bikeId + " was rented by " + userEmail + " from location";
                ERyderLog log = new ERyderLog(logEntry, eventDesc, LocalDateTime.now());
                systemLogsStack.push(log);
                
                return new ActiveRental(bikeId, userEmail, LocalDateTime.now());
            }
        }
        
        BikeRequest request = new BikeRequest(userEmail, "Unknown", LocalDateTime.now());
        bikeRequestQueue.add(request);
        return null;
    }

    public void releaseBike(String bikeId) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeId)) {
                bike.setIsAvailable(true);
                bike.setLastUsedTime(LocalDateTime.now());
                break;
            }
        }
        
        if (!bikeRequestQueue.isEmpty()) {
            BikeRequest nextRequest = bikeRequestQueue.poll();
        }
    }

    public Deque<BikeRequest> getBikeRequestQueue() {
        return bikeRequestQueue;
    }

    public Stack<ERyderLog> getSystemLogsStack() {
        return systemLogsStack;
    }
}

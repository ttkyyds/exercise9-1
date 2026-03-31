import java.time.LocalDateTime;

public class BikeService {
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
                return new ActiveRental(bikeId, userEmail, LocalDateTime.now());
            }
        }
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
    }
}
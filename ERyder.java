public class ERyder {
    
    public static final String COMPANY_NAME = "ERyder";
    public static final double BASE_FARE = 1.0;
    public static final double PER_MINUTE_FARE = 0.5;

    private final String linkedAccount;
    private final String linkedPhoneNumber;

    private String bikeID;
    private int batteryLevel;
    public boolean isAvailable;
    public float kmDriven;
    
    private int totalUsageInMinutes;
    private double totalFare;

    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, float kmDriven) {
        this.bikeID = bikeID;
        this.setBatteryLevel(batteryLevel); 
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        
        this.linkedAccount = "";
        this.linkedPhoneNumber = "Unknown";
        
        this.totalUsageInMinutes = 0;
        this.totalFare = 0.0;
    }

    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, float kmDriven, 
                  String linkedAccount, String linkedPhoneNumber) {
        this.bikeID = bikeID;
        this.setBatteryLevel(batteryLevel);
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;

        this.linkedAccount = linkedAccount;
        this.linkedPhoneNumber = linkedPhoneNumber;
        
        this.totalUsageInMinutes = 0;
        this.totalFare = 0.0;
    }

    public void ride() {
        if (batteryLevel > 0 && isAvailable) {
            System.out.println("The bike is available");
        } else {
            System.out.println("The bike is not available");
        }
    }

    public void setBatteryLevel(int batteryLevel) { 
        if ((batteryLevel > 0 && batteryLevel <= 100)) {
            this.batteryLevel = batteryLevel;
        } else {
            this.batteryLevel = 0;
            System.out.println("Invalid battery level");
        }
    }

    public void printBikeDetails() {
        System.out.println("Bike ID: " + bikeID);
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Is Available: " + isAvailable);
        System.out.println("Kilometers Driven: " + kmDriven);
    }

    public void printRideDetails(int usageInMinutes) {
        this.totalFare = calculateFare(usageInMinutes);
        this.totalUsageInMinutes = usageInMinutes;
        
        System.out.println("\n--- Ride Details ---");
        System.out.println("Linked Account: " + linkedAccount);
        System.out.println("Linked Phone Number: " + linkedPhoneNumber);
        System.out.println("Bike ID: " + bikeID);
        System.out.println("Usage in Minutes: " + usageInMinutes);
        System.out.println("Total Fare: $" + totalFare);
        System.out.println("Company: " + COMPANY_NAME);
    }

    private double calculateFare(int usageInMinutes) { 
        return BASE_FARE + (PER_MINUTE_FARE * usageInMinutes);
    }
    
    public double getCalculatedFare(int usageInMinutes) {
        return calculateFare(usageInMinutes);
    }
    
    public int getTotalUsageInMinutes() {
        return totalUsageInMinutes;
    }
    
    public void addUsageTime(int additionalMinutes) {
        if (additionalMinutes > 0) {
            this.totalUsageInMinutes += additionalMinutes;
        }
    }
}
//I used AI to generate variable names.
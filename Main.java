public class Main {
    public static void main(String[] args) {
    ERyder bike1 = new ERyder("Bike001", 85, true, 150.5f);
        
        System.out.println("Details of bike1:");
        bike1.printBikeDetails();
        
        bike1.printRideDetails(30); 

        System.out.println("\n" + "=".repeat(50) + "\n");
        
        ERyder bike2 = new ERyder("Bike002", 60, false, 200.0f, 
                                  "user_john", "+358401234567");
        
        System.out.println("Details of bike2:");
        bike2.printBikeDetails();
        
        bike2.printRideDetails(45); 
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        double fareForBike1 = bike1.getCalculatedFare(20);
        System.out.println("Fare for 20 minutes (via public method): $" + fareForBike1);
        
        System.out.println("\nCompany Name Constant: " + ERyder.COMPANY_NAME);
        System.out.println("Base Fare Constant: $" + ERyder.BASE_FARE);
        System.out.println("Per Minute Fare Constant: $" + ERyder.PER_MINUTE_FARE);
              
        AdminPanel adminPanel = new AdminPanel();
       
        adminPanel.userManagementOptions();
    }
}
//I used AI to help me udnderstand the promblems and exercise.
//I used AI to generate: usageInMinutes
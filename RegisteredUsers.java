import java.util.Arrays;

public class RegisteredUsers {
    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardExpiryDate;
    private String cardProvider;
    private int cvv;
    private String userType;
    private String[] lastThreeTrips = new String[3];
    
    public static final double BASE_FARE = 3.0;

    public RegisteredUsers(String fullName, String emailAddress, String dateOfBirth,
                           long cardNumber, String cardExpiryDate, String cardProvider,
                           int cvv, String userType, String[] lastThreeTrips) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.cardProvider = cardProvider;
        this.cvv = cvv;
        this.userType = userType;
        
        if (lastThreeTrips != null) {
            for (int i = 0; i < Math.min(this.lastThreeTrips.length, lastThreeTrips.length); i++) {
                this.lastThreeTrips[i] = lastThreeTrips[i];
            }
        }
    }

    public double calculateFare() {
        return BASE_FARE;
    }
    
    public void displayUserType() {
        System.out.println("Regular User");
    }

    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }
    
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public long getCardNumber() {
        return cardNumber;
    }
    
    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public String getCardExpiryDate() {
        return cardExpiryDate;
    }
    
    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }
    
    public String getCardProvider() {
        return cardProvider;
    }
    
    public void setCardProvider(String cardProvider) {
        this.cardProvider = cardProvider;
    }
    
    public int getCvv() {
        return cvv;
    }
    
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
    
    public String getUserType() {
        return userType;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    public String[] getLastThreeTrips() {
        return lastThreeTrips;
    }
    
    public void setLastThreeTrips(String[] lastThreeTrips) {
        this.lastThreeTrips = lastThreeTrips;
    }

    @Override
    public String toString() {
        return "RegisteredUsers{" +
                "fullName='" + fullName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", cardNumber=" + cardNumber +
                ", cardExpiryDate='" + cardExpiryDate + '\'' +
                ", cardProvider='" + cardProvider + '\'' +
                ", cvv=" + cvv +
                ", userType='" + userType + '\'' +
                ", lastThreeTrips=" + Arrays.toString(lastThreeTrips) +
                '}';
    }
}
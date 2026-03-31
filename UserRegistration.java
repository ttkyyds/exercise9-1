import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class UserRegistration {

  
    private final double VIP_DISCOUNT_UNDER_18_BIRTHDAY = 25.0;
    private final double VIP_DISCOUNT_UNDER_18 = 20.0;
    private final double VIP_BASE_FEE = 100.0;

    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardProvider;
    private String cardExpiryDate;
    private double feeToCharge = 0.0;
    private int cvv;
    private String userType = "Regular User";

    private boolean emailValid = false;
    private boolean minorAndBirthday = false;
    private boolean minor = false;
    private boolean ageValid = false;
    private boolean cardNumberValid = false;
    private boolean cardStillValid = false;
    private boolean validCVV = false;

    private Scanner scanner = new Scanner(System.in);

    public void registration() {
        System.out.println("Welcome to the ERyder Registration.");
        System.out.println("Here are your two options:");
        System.out.println("1. Register as a Regular User");
        System.out.println("2. Register as a VIP User");
        System.out.print("Please enter your choice (1 or 2): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 2) {
            userType = "VIP User";
        } else {
            userType = "Regular User";
        }

        System.out.print("Please enter your full name: ");
        fullName = scanner.nextLine();

        System.out.print("Please enter your email address: ");
        emailAddress = scanner.nextLine();
        emailValid = analyseEmail(emailAddress);

        System.out.print("Please enter your date of birth (YYYY-MM-DD): ");
        dateOfBirth = scanner.nextLine();
        LocalDate dob = LocalDate.parse(dateOfBirth);
        ageValid = analyseAge(dob);

        System.out.println("Please enter your card number (Only Visa, MasterCard, and American Express are accepted): ");
        String cardInput = scanner.nextLine();
        try {
            cardNumber = Long.parseLong(cardInput);
        } catch (NumberFormatException e) {
            cardNumberValid = false;
            System.out.println("Invalid card number format. Going back to the start of the registration.");
            registration();
            scanner.close();
            return;
        }
        cardNumberValid = analyseCardNumber(cardNumber);

        System.out.print("Please enter your card expiry date (MM/YY): ");
        cardExpiryDate = scanner.nextLine();
        cardStillValid = analyseCardExpiryDate(cardExpiryDate);

        System.out.print("Please enter your card's CVV: ");
        cvv = scanner.nextInt();
        scanner.nextLine();
        validCVV = analyseCVV(cvv);

        finalCheckpoint();

        scanner.close();
    }

    private boolean analyseEmail(String email) {
        if (email.contains("@") && email.contains(".")) {
            System.out.println("Email is valid");
            return true;
        } else {
            System.out.println("Invalid email address. Going back to the start of the registration");
            registration();
            return false;
        }
    }

    private boolean analyseAge(LocalDate dob) {
        LocalDate today = LocalDate.now();
        Period agePeriod = Period.between(dob, today);
        int ageInYears = agePeriod.getYears();

        if (ageInYears <= 12 || ageInYears > 120) {
            System.out.println("Looks like you are either too young or already dead. Sorry, you can't be our user. Have a nice day");
            System.exit(0);
        }

        boolean isBirthday = (dob.getMonthValue() == today.getMonthValue()) && (dob.getDayOfMonth() == today.getDayOfMonth());

        if (userType.equals("VIP User")) {
            if (isBirthday && ageInYears <= 18 && ageInYears > 12) {
                System.out.println("Happy Birthday!");
                System.out.println("You get 25% discount on the VIP subscription fee for being born today and being under 18!");
                minorAndBirthday = true;
            } else if (!isBirthday && ageInYears <= 18 && ageInYears > 12) {
                System.out.println("You get 20% discount on the VIP subscription fee for being under 18!");
                minor = true;
            }
        }
        return true;
    }

    private boolean analyseCardNumber(long cardNumber) {
        String cardNumStr = String.valueOf(cardNumber);
        int firstTwoDigits = 0;
        int firstFourDigits = 0;
        if (cardNumStr.length() >= 2) {
            firstTwoDigits = Integer.parseInt(cardNumStr.substring(0, 2));
        }
        if (cardNumStr.length() >= 4) {
            firstFourDigits = Integer.parseInt(cardNumStr.substring(0, 4));
        }

        if ((cardNumStr.length() == 13 || cardNumStr.length() == 15 || cardNumStr.length() == 16) && cardNumStr.startsWith("4")) {
            cardProvider = "VISA";
            return true;
        } else if (cardNumStr.length() == 16 && ((firstTwoDigits >= 51 && firstTwoDigits <= 55) || (firstFourDigits >= 2221 && firstFourDigits <= 2720))) {
            cardProvider = "MasterCard";
            return true;
        } else if (cardNumStr.length() == 15 && (cardNumStr.startsWith("34") || cardNumStr.startsWith("37"))) {
            cardProvider = "American Express";
            return true;
        } else {
            System.out.println("Sorry, but we accept only VISA, MasterCard, or American Express cards. Please try again with a valid card.");
            System.out.println("Going back to the start of the registration.");
            registration();
            return false;
        }
    }

    private boolean analyseCardExpiryDate(String expiryDate) {
        int month = Integer.parseInt(expiryDate.substring(0, 2));
        int year = 2000 + Integer.parseInt(expiryDate.substring(3, 5));

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();

        if (year > currentYear || (year == currentYear && month >= currentMonth)) {
            System.out.println("The card is still valid");
            return true;
        } else {
            System.out.println("Sorry, your card has expired. Please use a different card.");
            System.out.println("Going back to the start of the registration process...");
            registration();
            return false;
        }
    }

    private boolean analyseCVV(int cvvNum) {
        String cvvStr = String.valueOf(cvvNum);
        int cvvLength = cvvStr.length();

        if ((cardProvider.equals("American Express") && cvvLength == 4) ||
            (cardProvider.equals("VISA") && cvvLength == 3) ||
            (cardProvider.equals("MasterCard") && cvvLength == 3)) {
            System.out.println("Card CVV is valid.");
            return true;
        } else {
            System.out.println("Invalid CVV for the given card.");
            System.out.println("Going back to the start of the registration process.");
            registration();
            return false;
        }
    }

    private void finalCheckpoint() {
        if (emailValid && ageValid && cardNumberValid && cardStillValid && validCVV) {
            chargeFees();
        } else {
            System.out.println("Sorry, your registration was unsuccessful due to the following reason(s):");
            if (!emailValid) {
                System.out.println("Invalid email address");
            }
            if (!ageValid) {
                System.out.println("Invalid age");
            }
            if (!cardNumberValid) {
                System.out.println("Invalid card number");
            }
            if (!cardStillValid) {
                System.out.println("Card has expired");
            }
            if (!validCVV) {
                System.out.println("Invalid CVV");
            }
            System.out.println("Going back to the start of the registration process.");
            registration();
        }
    }

    private void chargeFees() {
        double discount = 0.0;
        if (minorAndBirthday) {
            discount = VIP_DISCOUNT_UNDER_18_BIRTHDAY;
        } else if (minor) {
            discount = VIP_DISCOUNT_UNDER_18;
        }

        feeToCharge = VIP_BASE_FEE * (1 - discount / 100.0);
        
        String cardNumStr = String.valueOf(cardNumber);
        String lastFourDigits = cardNumStr.substring(cardNumStr.length() - 4);
        
        System.out.printf("Thank you for your payment.\nA fee of %.2f has been charged to your card ending with %s\n", feeToCharge, lastFourDigits);
    }

    @Override
    public String toString() {
        String cardNumStr = String.valueOf(cardNumber);
        String censoredPart = cardNumStr.substring(0, cardNumStr.length() - 4).replaceAll(".", "*");
        String lastFourDigits = cardNumStr.substring(cardNumStr.length() - 4);
        String censoredNumber = censoredPart + lastFourDigits;

        return "\nRegistration successful! Here are your details:\n" +
               "User Type: " + userType + "\n" +
               "Full Name: " + fullName + "\n" +
               "Email Address: " + emailAddress + "\n" +
               "Date of Birth: " + dateOfBirth + "\n" +
               "Card Number: " + censoredNumber + "\n" +
               "Card Provider: " + cardProvider + "\n" +
               "Card Expiry Date: " + cardExpiryDate + "\n";
    }
}
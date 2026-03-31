public class Feedback {
    private String firstName;
    private String lastName;
    private String email;
    private String completeFeedback;
    private String reviewID;
    private boolean longFeedback;

    public Feedback(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.completeFeedback = "";
        this.reviewID = "";
        this.longFeedback = false;
    }

    public void analyseFeedback(boolean isConcatenation, String sent1, String sent2, String sent3, String sent4, String sent5) {
        if (isConcatenation) {
            this.completeFeedback = feedbackUsingConcatenation(sent1, sent2, sent3, sent4, sent5);
        } else {
            this.completeFeedback = feedbackUsingStringBuilder(sent1, sent2, sent3, sent4, sent5);
        }
        
        this.longFeedback = checkFeedbackLength(this.completeFeedback);
        this.reviewID = createReviewID(this.firstName, this.lastName, this.completeFeedback);
    }

    private String feedbackUsingConcatenation(String s1, String s2, String s3, String s4, String s5) {
        String concatenatedFeedback = s1 + s2 + s3 + s4 + s5;
        return concatenatedFeedback;
    }

    private String feedbackUsingStringBuilder(String s1, String s2, String s3, String s4, String s5) {
        StringBuilder sb = new StringBuilder();
        sb.append(s1);
        sb.append(s2);
        sb.append(s3);
        sb.append(s4);
        sb.append(s5);
        return sb.toString();
    }

    private boolean checkFeedbackLength(String feedback) {
        if (feedback.length() > 500) {
            return true;
        } else {
            return false;
        }
    }

    private String createReviewID(String firstName, String lastName, String completeFeedback) {
        String idPart = "";
        String nameCombined = firstName + lastName;
        
        if (nameCombined.length() > 6) {
            idPart = nameCombined.substring(2, 6).toUpperCase();
        } else {
            idPart = nameCombined.toUpperCase();
        }
        
        if (completeFeedback.length() > 15) {
            idPart = idPart + completeFeedback.substring(10, 15).toLowerCase();
        } else {
            idPart = idPart + completeFeedback.toLowerCase();
        }
        
        idPart = idPart + completeFeedback.length() + "_" + System.currentTimeMillis();
        idPart = idPart.replace(" ", "");
        
        return idPart;
    }

    @Override
    public String toString() {
        return "--- Feedback Details ---\n" +
               "First Name: " + firstName + "\n" +
               "Last Name: " + lastName + "\n" +
               "Email: " + email + "\n" +
               "Complete Feedback: " + completeFeedback + "\n" +
               "Is Long Feedback (>500 chars)? " + longFeedback + "\n" +
               "Review ID: " + reviewID;
    }

    public static void main(String[] args) {
        String sentence1 = "I was very satisfied with the service.";
        String sentence2 = "The e-Bike is quite comfortable to ride.";
        String sentence3 = "The battery life of the e-Bike is impressive.";
        String sentence4 = "The customer support was helpful and responsive.";
        String sentence5 = "I would recommend this e-Bike to my friends and family.";

        Feedback myFeedback = new Feedback("Wei", "Zirui", "Wei.Zirui@1779815633qq.com");
        myFeedback.analyseFeedback(false, sentence1, sentence2, sentence3, sentence4, sentence5);
        System.out.println(myFeedback);
    }
}
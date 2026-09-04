package OOP_Constructor_PracticeProblems;
public class FareSplitter {

    private String tripId;
    private double totalFare;
    private int passengerCount;

    // Constructor 1: Full details
    public FareSplitter(String tripId, double totalFare, int passengerCount) {

        if (tripId == null || tripId.trim().isEmpty()) {
            throw new IllegalArgumentException("Trip ID cannot be empty");
        }

        if (totalFare < 0) {
            throw new IllegalArgumentException("Fare cannot be negative");
        }

        if (passengerCount <= 0 || passengerCount > 60) {
            throw new IllegalArgumentException("Invalid passenger count");
        }

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    // Constructor 2: Trip ID and total fare
    // Default passenger count = 3
    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 3);
    }

    // Constructor 3: Only Trip ID
    // Default fare = 0 and passenger count = 3
    public FareSplitter(String tripId) {
        this(tripId, 0.0, 3);
    }

    // Divide fare fairly and handle remainder
    public double[] fareBreakdown() {

        double[] shares = new double[passengerCount];

        // Convert fare to paise/cents to avoid decimal problems
        long totalPaise = Math.round(totalFare * 100);

        long baseShare = totalPaise / passengerCount;
        long remainder = totalPaise % passengerCount;

        for (int i = 0; i < passengerCount; i++) {

            long share = baseShare;

            // Give the remaining paise to the LAST passengers
            if (i >= passengerCount - remainder) {
                share++;
            }

            shares[i] = share / 100.0;
        }

        return shares;
    }

    // Check whether confirmations are overdue
    public boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }

    // Main method for testing
    public static void main(String[] args) {

        // Example 1
        FareSplitter trip1 =
                new FareSplitter("TRIP001", 10000, 3);

        double[] result1 = trip1.fareBreakdown();

        System.out.print("Fare Breakdown: [");
        for (int i = 0; i < result1.length; i++) {
            System.out.printf("%.2f", result1[i]);

            if (i < result1.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");


        // Example 2
        FareSplitter trip2 = new FareSplitter("TRIP003");

        double[] result2 = trip2.fareBreakdown();

        System.out.print("Fare Breakdown: [");
        for (int i = 0; i < result2.length; i++) {
            System.out.printf("%.2f", result2[i]);

            if (i < result2.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");


        // Testing confirmation
        System.out.println("Confirmation overdue: "
                + trip1.isConfirmationOverdue(2, 3));
    }
}

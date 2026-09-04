package OOP_Constructor_PracticeProblems;
import java.util.HashSet;
import java.util.Set;

public class BusTicketBookingValidator {

    static int valid = 0;
    static int rejected = 0;
    static int duplicatesSkipped = 0;

    Set<String> checkedBookings = new HashSet<>();

    // Parameterized constructor only
    public BusTicketBookingValidator(String passengerName, String destination) {

        // Null validation
        if (passengerName == null || destination == null) {
            throw new IllegalArgumentException("Name or destination cannot be null");
        }

        // Empty or whitespace-only validation
        if (passengerName.trim().isEmpty() || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Name or destination cannot be empty");
        }

        // Passenger name should contain only letters
        if (!passengerName.matches("[A-Za-z ]+")) {
            throw new IllegalArgumentException("Invalid passenger name");
        }
    }

    // Marks booking as checked
    public void markChecked() {
        // Method can be called multiple times safely
        // No extra action needed after checking
    }

    // Process all bookings
    public static void processBatch(String[][] rawBookings) {

        Set<String> bookingPairs = new HashSet<>();

        for (String[] booking : rawBookings) {

            try {
                // Check invalid array data
                if (booking == null || booking.length < 2) {
                    rejected++;
                    continue;
                }

                String passengerName = booking[0];
                String destination = booking[1];

                // Create object - validation happens in constructor
                new BusTicketBookingValidator(passengerName, destination);

                // Create unique key for duplicate checking
                String key = passengerName + "|" + destination;

                // Check duplicate
                if (bookingPairs.contains(key)) {
                    duplicatesSkipped++;
                } else {
                    bookingPairs.add(key);
                    valid++;
                }

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid
                + " | Rejected: " + rejected
                + " | Duplicates skipped: " + duplicatesSkipped);
    }

    // Main method for testing
    public static void main(String[] args) {

        String[][] rawBookings = {
                {"Divya", "Chennai"},
                {"", "Bangalore"},
                {"Ravi123", "Pune"},
                {"Divya", "Chennai"},
                {" ", " "}
        };

        processBatch(rawBookings);
    }
}

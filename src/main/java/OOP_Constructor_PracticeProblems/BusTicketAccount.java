package OOP_Constructor_PracticeProblems;
public class BusTicketAccount {

    private final String bookingId;
    private final double ticketFare;

    // One-time class-level state
    private static final double REGULAR_RATE;
    private static final double SLEEPER_RATE;

    // Static block
    static {
        REGULAR_RATE = 10.0;
        SLEEPER_RATE = 15.0;
    }

    // Full constructor
    public BusTicketAccount(String bookingId, double ticketFare) {

        if (bookingId == null || bookingId.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid booking ID");
        }

        if (ticketFare < 0) {
            throw new IllegalArgumentException("Ticket fare cannot be negative");
        }

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    // Provisional constructor - chains to full constructor
    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    // Penalty calculation
    // Simple flat-rate version
    public final double calculatePenalty(int minutesLate) {

        if (minutesLate < 0 || minutesLate > 500) {
            throw new IllegalArgumentException(
                    "Minutes late must be between 0 and 500"
            );
        }

        return ticketFare * REGULAR_RATE * minutesLate / 100.0;
    }

    // Instance-based dispatch
    public void settle(double amount, int minutesLate) {

        if (minutesLate < 0 || minutesLate > 500) {
            System.out.println("Invalid minutes late for " + bookingId);
            return;
        }

        if (amount < 0) {
            System.out.println("Invalid amount for " + bookingId);
            return;
        }

        double penalty = calculatePenalty(minutesLate);

        System.out.println(
                "Booking: " + bookingId +
                " | Type: Regular" +
                " | Amount: " + amount +
                " | Penalty: " + penalty
        );
    }

    // Static batch processing method
    public static void processBatch(
            BusTicketAccount[] accounts,
            double[] amounts,
            int[] minutesLateArray) {

        // Check for null arrays
        if (accounts == null ||
                amounts == null ||
                minutesLateArray == null) {

            System.out.println("Invalid input arrays");
            return;
        }

        // All arrays must have matching lengths
        if (accounts.length != amounts.length ||
                accounts.length != minutesLateArray.length) {

            System.out.println(
                    "Error: All input arrays must have the same length"
            );
            return;
        }

        int processed = 0;
        int nullSkipped = 0;
        int sleeperCount = 0;
        int regularCount = 0;

        double grandTotal = 0.0;

        for (int i = 0; i < accounts.length; i++) {

            // Skip null account safely
            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            try {
                // Instance-based dispatch
                accounts[i].settle(
                        amounts[i],
                        minutesLateArray[i]
                );

                processed++;

                // Calculate total only for genuinely processed accounts
                grandTotal += amounts[i];

                if (accounts[i] instanceof SleeperBusTicketAccount) {
                    sleeperCount++;
                } else {
                    regularCount++;
                }

            } catch (Exception e) {
                // Do not allow one bad entry to crash the batch
                System.out.println(
                        "Error processing account at index " + i
                );
            }
        }

        System.out.println("\n----- BATCH SUMMARY -----");
        System.out.println("Processed: " + processed);
        System.out.println("Null skipped: " + nullSkipped);
        System.out.println("Sleeper: " + sleeperCount);
        System.out.println("Regular: " + regularCount);
        System.out.println("Grand total: " + grandTotal);
    }

    // Main method
    public static void main(String[] args) {

        BusTicketAccount[] accounts = {

                new SleeperBusTicketAccount("BK001", 200),

                null,

                new BusTicketAccount("BK002", 100)
        };

        double[] amounts = {1200, 500, 70};

        int[] minutesLateArray = {10, 5, 0};

        processBatch(
                accounts,
                amounts,
                minutesLateArray
        );
    }
}


// Sleeper account class
class SleeperBusTicketAccount extends BusTicketAccount {

    public SleeperBusTicketAccount(
            String bookingId,
            double ticketFare) {

        super(bookingId, ticketFare);
    }

    // Sleeper account settles differently
    @Override
    public void settle(double amount, int minutesLate) {

        if (minutesLate < 0 || minutesLate > 500) {
            System.out.println("Invalid minutes late");
            return;
        }

        if (amount < 0) {
            System.out.println("Invalid amount");
            return;
        }

        // Sleeper gets different settlement calculation
        double sleeperPenalty =
                amount * 15.0 * minutesLate / 100.0;

        System.out.println(
                "Sleeper Account" +
                " | Amount: " + amount +
                " | Penalty: " + sleeperPenalty
        );
    }
}

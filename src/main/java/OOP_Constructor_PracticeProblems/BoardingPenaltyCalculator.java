package OOP_Constructor_PracticeProblems;
public class BoardingPenaltyCalculator {

    // Cannot be changed after object creation
    private final double minimumPenaltyPercent;

    // Constructor
    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {

        if (minimumPenaltyPercent < 0) {
            throw new IllegalArgumentException(
                    "Minimum penalty percent cannot be negative"
            );
        }

        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    // Calculate penalty
    public double calculatePenalty(double ticketFare, int minutesLate) {

        if (ticketFare < 0) {
            throw new IllegalArgumentException(
                    "Ticket fare cannot be negative"
            );
        }

        if (minutesLate < 0 || minutesLate > 500) {
            throw new IllegalArgumentException(
                    "Minutes late must be between 0 and 500"
            );
        }

        // On time -> no penalty and no minimum floor
        if (minutesLate == 0) {
            return 0.0;
        }

        double penaltyPercent;

        // Minutes 1 to 5 -> 5% per minute
        if (minutesLate <= 5) {
            penaltyPercent = minutesLate * 5.0;
        }

        // Minutes 6 to 15
        else if (minutesLate <= 15) {
            penaltyPercent = (5 * 5.0)
                    + ((minutesLate - 5) * 10.0);
        }

        // Minutes 16 onwards
        else {
            penaltyPercent = (5 * 5.0)
                    + (10 * 10.0)
                    + ((minutesLate - 15) * 20.0);
        }

        // Calculate tier-based penalty
        double penalty = ticketFare * penaltyPercent / 100.0;

        // Calculate minimum penalty floor
        double minimumPenalty =
                ticketFare * minimumPenaltyPercent / 100.0;

        // Apply whichever penalty is higher
        return Math.max(penalty, minimumPenalty);
    }

    // Main method for testing
    public static void main(String[] args) {

        // Minimum penalty floor = 10%
        BoardingPenaltyCalculator calculator =
                new BoardingPenaltyCalculator(10.0);

        System.out.println("minutesLate = 0: Rs "
                + calculator.calculatePenalty(100, 0));

        System.out.println("minutesLate = 1: Rs "
                + calculator.calculatePenalty(100, 1));

        System.out.println("minutesLate = 16: Rs "
                + calculator.calculatePenalty(100, 16));
    }
}

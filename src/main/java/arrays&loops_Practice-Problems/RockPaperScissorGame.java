package arrays&loops_Practice-Problems;
import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {

    // Method to determine the winner of a round
    static String playRound(String playerMove, String computerMove) {

        if (playerMove.equals(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
            (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
            (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {

            return "Player Wins";
        }

        return "Computer Wins";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int N = 5;

        String[] playerMoves = new String[N];
        String[] computerMoves = new String[N];
        String[] results = new String[N];

        int wins = 0;
        int losses = 0;
        int draws = 0;

        System.out.println("===== ROCK PAPER SCISSORS =====");
        System.out.println("You will play " + N + " rounds.");

        // Play N rounds
        for (int i = 0; i < N; i++) {

            System.out.println("\nRound " + (i + 1));
            System.out.println("1. Rock");
            System.out.println("2. Paper");
            System.out.println("3. Scissors");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            // Player's move
            if (choice == 1) {
                playerMoves[i] = "Rock";
            } 
            else if (choice == 2) {
                playerMoves[i] = "Paper";
            } 
            else if (choice == 3) {
                playerMoves[i] = "Scissors";
            } 
            else {
                System.out.println("Invalid choice!");
                i--;
                continue;
            }

            // Computer's random move: 1, 2 or 3
            int computerChoice = random.nextInt(3) + 1;

            if (computerChoice == 1) {
                computerMoves[i] = "Rock";
            } 
            else if (computerChoice == 2) {
                computerMoves[i] = "Paper";
            } 
            else {
                computerMoves[i] = "Scissors";
            }

            // Find result
            results[i] = playRound(playerMoves[i], computerMoves[i]);

            // Update score
            if (results[i].equals("Player Wins")) {
                wins++;
            } 
            else if (results[i].equals("Computer Wins")) {
                losses++;
            } 
            else {
                draws++;
            }

            System.out.println("Player: " + playerMoves[i]);
            System.out.println("Computer: " + computerMoves[i]);
            System.out.println("Result: " + results[i]);
        }

        // Calculate win percentage
        double winPercentage = ((double) wins / N) * 100;

        // Final table
        System.out.println("\n========== FINAL SCOREBOARD ==========");

        System.out.printf("%-8s %-15s %-17s %-15s%n",
                "Round", "Player Move", "Computer Move", "Result");

        System.out.println("-------------------------------------------------------");

        for (int i = 0; i < N; i++) {
            System.out.printf("%-8d %-15s %-17s %-15s%n",
                    i + 1,
                    playerMoves[i],
                    computerMoves[i],
                    results[i]);
        }

        // Final summary
        System.out.println("\n========== FINAL SUMMARY ==========");
        System.out.println("Wins   : " + wins);
        System.out.println("Losses : " + losses);
        System.out.println("Draws  : " + draws);
        System.out.printf("Win %%  : %.1f%%%n", winPercentage);

        sc.close();
    }
}

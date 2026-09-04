package arrays&loops_Practice-Problems;
import java.util.Scanner;

public class FirstNonRepeatingCharacter {

    // Method to find the first non-repeating character
    static char findFirstNonRepeatingChar(String text) {

        // Check every character
        for (int i = 0; i < text.length(); i++) {

            char current = text.charAt(i);
            int count = 0;

            // Count how many times current character occurs
            for (int j = 0; j < text.length(); j++) {

                if (current == text.charAt(j)) {
                    count++;
                }
            }

            // If character occurs only once, return it
            if (count == 1) {
                return current;
            }
        }

        // Return '\0' if no non-repeating character exists
        return '\0';
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a word or sentence: ");
        String text = sc.nextLine();

        char result = findFirstNonRepeatingChar(text);

        if (result == '\0') {
            System.out.println("No Non-Repeating Character Found");
        } else {
            System.out.println("First Non-Repeating Character: '" + result + "'");
        }

        sc.close();
    }
}

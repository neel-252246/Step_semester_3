package StringManipulation_Practice-Problems;
import java.util.Scanner;

public class MaskedPhoneNumberFormatter {

    // Method to mask the phone number
    static String maskPhoneNumber(String phone) {

        // Validate length
        if (phone.length() != 10) {
            return "Invalid phone number";
        }

        // Validate that all characters are digits
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        // Get the last 4 digits
        String lastFour = phone.substring(6);

        // Create masked string using StringBuilder
        StringBuilder masked = new StringBuilder("XXXXXX");

        // Add hyphen
        masked.append("-");

        // Add last 4 digits
        masked.append(lastFour);

        return masked.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Take phone number as String
        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        // Call method
        String result = maskPhoneNumber(phone);

        // Print result
        System.out.println(result);

        sc.close();
    }
}

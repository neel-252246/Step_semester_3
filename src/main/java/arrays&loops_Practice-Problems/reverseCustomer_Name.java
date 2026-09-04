package arrays&loops_Practice-Problems;
import java.util.Scanner;

public class ReverseCustomerName {

    // Method to reverse the customer name
    static String reverseCustomerName(String customerName) {

        String reversed = "";

        // Traverse the string from last character to first
        for (int i = customerName.length() - 1; i >= 0; i--) {
            reversed = reversed + customerName.charAt(i);
        }

        return reversed;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Take customer name as input
        System.out.print("Enter customer name: ");
        String customerName = sc.nextLine();

        // Call the reverse method
        String reversedName = reverseCustomerName(customerName);

        // Print original and reversed names
        System.out.println("Original Name: " + customerName);
        System.out.println("Reversed Name: " + reversedName);

        sc.close();
    }
}

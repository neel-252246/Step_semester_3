package arrays&loops_Practice-Problems;

import java.util.Scanner;

public class PalindromeChecker {

    // 1. Iterative approach
    static boolean isPalindromeIterative(String text) {

        int left = 0;
        int right = text.length() - 1;

        while (left < right) {

            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    // 2. Recursive approach
    static boolean isPalindromeRecursive(String text) {

        // Base case
        if (text.length() <= 1) {
            return true;
        }

        // Compare first and last characters
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }

        // Check the remaining substring
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    // 3. Array reversal approach
    static boolean isPalindromeArrayReversal(String text) {

        // Convert string to character array
        char[] original = text.toCharArray();

        // Create another array for reversed string
        char[] reversed = new char[original.length];

        // Reverse the array
        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }

        // Compare original and reversed arrays
        for (int i = 0; i < original.length; i++) {
            if (original[i] != reversed[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Take input
        System.out.print("Enter a word or phrase: ");
        String text = sc.nextLine();

        // Call all three methods
        boolean iterative = isPalindromeIterative(text);
        boolean recursive = isPalindromeRecursive(text);
        boolean arrayReversal = isPalindromeArrayReversal(text);

        // Print results
        System.out.println();

        System.out.println("Iterative: " +
                (iterative ? "Palindrome" : "Not Palindrome"));

        System.out.println("Recursive: " +
                (recursive ? "Palindrome" : "Not Palindrome"));

        System.out.println("Array Reversal: " +
                (arrayReversal ? "Palindrome" : "Not Palindrome"));

        // Check whether all three agree
        if (iterative == recursive && recursive == arrayReversal) {
            System.out.println("\nAll three approaches agree.");
        } else {
            System.out.println("\nThe approaches do not agree.");
        }

        sc.close();
    }
}

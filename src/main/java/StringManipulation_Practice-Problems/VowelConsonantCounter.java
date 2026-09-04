package StringManipulation_Practice-Problems;

import java.util.Scanner;

public class VowelConsonantCounter {

    // Method to count vowels and consonants
    static void countVowelsAndConsonants(String text) {

        int vowels = 0;
        int consonants = 0;

        // Loop through each character
        for (int i = 0; i < text.length(); i++) {

            char ch = text.charAt(i);

            // Convert character to lowercase
            ch = Character.toLowerCase(ch);

            // Check for vowel
            if (ch == 'a' || ch == 'e' || ch == 'i' ||
                ch == 'o' || ch == 'u') {

                vowels++;
            }
            // Check for consonant
            else if (ch >= 'a' && ch <= 'z') {

                consonants++;
            }
            // Spaces are ignored
        }

        // Print result
        System.out.println("Vowels: " + vowels +
                           " | Consonants: " + consonants);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Take input
        System.out.print("Enter a string: ");
        String text = sc.nextLine();

        // Call method
        countVowelsAndConsonants(text);

        sc.close();
    }
}

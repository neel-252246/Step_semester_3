package StringManipulation_Practice-Problems;
import java.util.Scanner;

public class FileExtensionValidator {

    // Method to validate file extension
    static String validateFileExtension(String filename) {

        // Find the position of the last dot
        int dotPosition = filename.lastIndexOf('.');

        // If there is no dot, there is no extension
        if (dotPosition == -1) {
            return "Rejected — invalid file type";
        }

        // Extract the extension
        String extension = filename.substring(dotPosition + 1);

        // Compare extension with accepted types
        if (extension.equalsIgnoreCase("pdf") ||
            extension.equalsIgnoreCase("docx") ||
            extension.equalsIgnoreCase("zip")) {

            return "Accepted";
        }

        return "Rejected — invalid file type";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Take filename as input
        System.out.print("Enter filename: ");
        String filename = sc.nextLine();

        // Validate the extension
        String result = validateFileExtension(filename);

        // Print result
        System.out.println(result);

        sc.close();
    }
}

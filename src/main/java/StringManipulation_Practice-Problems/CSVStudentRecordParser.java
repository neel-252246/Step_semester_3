package StringManipulation_Practice-Problems;
import java.util.Scanner;

public class CSVStudentRecordParser {

    // Method to parse and print student record
    static void parseStudentRecord(String csvLine) {

        // Split the CSV line using comma
        String[] fields = csvLine.split(",");

        // Check if exactly 3 fields are present
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        // Store the three fields
        String name = fields[0];
        String rollNo = fields[1];
        String department = fields[2];

        // Print formatted record
        System.out.println("Name: " + name +
                           " | Roll No: " + rollNo +
                           " | Dept: " + department);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Take CSV input
        System.out.print("Enter student record: ");
        String csvLine = sc.nextLine();

        // Call method
        parseStudentRecord(csvLine);

        sc.close();
    }
}

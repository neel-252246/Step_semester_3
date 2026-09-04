package arrays&loops_Practice-Problems;
import java.util.Scanner;

public class BMICalculator {

    // Method to classify BMI
    static String getBmiStatus(double bmi) {

        if (bmi < 18.5) {
            return "Underweight";
        }
        else if (bmi < 25) {
            return "Normal";
        }
        else if (bmi < 30) {
            return "Overweight";
        }
        else {
            return "Obese";
        }
    }

    // Method to print the wellness report
    static void printWellnessReport(double[] heights, double[] weights) {

        System.out.println("\n================ WELLNESS REPORT ================");

        System.out.printf("%-10s %-12s %-12s %-10s %-15s%n",
                "Person", "Height(m)", "Weight(kg)", "BMI", "Status");

        System.out.println("------------------------------------------------------------");

        for (int i = 0; i < heights.length; i++) {

            // Calculate BMI
            double bmi = weights[i] / (heights[i] * heights[i]);

            // Get BMI status
            String status = getBmiStatus(bmi);

            // Print details
            System.out.printf("%-10d %-12.2f %-12.2f %-10.2f %-15s%n",
                    i + 1,
                    heights[i],
                    weights[i],
                    bmi,
                    status);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Number of people
        int n = 5;

        double[] heights = new double[n];
        double[] weights = new double[n];

        System.out.println("===== BMI CALCULATOR =====");

        // Take input
        for (int i = 0; i < n; i++) {

            System.out.println("\nPerson " + (i + 1));

            System.out.print("Enter height in meters: ");
            heights[i] = sc.nextDouble();

            System.out.print("Enter weight in kg: ");
            weights[i] = sc.nextDouble();
        }

        // Print final report
        printWellnessReport(heights, weights);

        sc.close();
    }
}

package Classes&Objects_PracticeProblems;
public class AttendanceSystem {

    // Student class
    static class SrmStudent {

        String name;
        String regNo;
        double attendance;

        // Constructor
        SrmStudent(String name, String regNo, double attendance) {
            this.name = name;
            this.regNo = regNo;
            this.attendance = attendance;
        }

        // Instance method to update attendance
        void attendanceUpdate(int newAttendance) {
            this.attendance = newAttendance;
        }

        // Instance method to check eligibility
        boolean isEligible() {
            return attendance >= 75;
        }
    }

    // Static method to calculate average attendance
    static double classAverage(SrmStudent[] students) {

        double total = 0;

        for (int i = 0; i < students.length; i++) {
            total += students[i].attendance;
        }

        return total / students.length;
    }

    public static void main(String[] args) {

        // Create an array of 5 student objects
        SrmStudent[] students = new SrmStudent[5];

        students[0] = new SrmStudent("Ravi", "RA101", 82);
        students[1] = new SrmStudent("Anitha", "RA102", 68);
        students[2] = new SrmStudent("Karthik", "RA103", 91);
        students[3] = new SrmStudent("Meera", "RA104", 74);
        students[4] = new SrmStudent("Suresh", "RA105", 60);

        // Example of updating attendance after a re-check
        students[1].attendanceUpdate(68);

        // Print each student's details
        System.out.println("===== ATTENDANCE REPORT =====");

        for (int i = 0; i < students.length; i++) {

            String status;

            if (students[i].isEligible()) {
                status = "Eligible";
            } else {
                status = "Detained";
            }

            System.out.printf("%s - %.0f%% - %s%n",
                    students[i].name,
                    students[i].attendance,
                    status);
        }

        // Calculate and print class average
        double average = classAverage(students);

        System.out.printf("Class average: %.1f%%%n", average);
    }
}

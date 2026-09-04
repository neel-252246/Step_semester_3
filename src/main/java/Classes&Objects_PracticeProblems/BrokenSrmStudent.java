package Classes&Objects_PracticeProblems;
public class StudentStaticDemo {

    // =========================================================
    // BROKEN VERSION
    // =========================================================
    static class BrokenSrmStudent {

        // These should NOT be static
        static String name;
        static String regNo;
        static int attendance;

        BrokenSrmStudent(String name, String regNo, int attendance) {
            BrokenSrmStudent.name = name;
            BrokenSrmStudent.regNo = regNo;
            BrokenSrmStudent.attendance = attendance;
        }

        void printName() {
            System.out.println(name);
        }
    }


    // =========================================================
    // CORRECTED VERSION
    // =========================================================
    static class SrmStudent {

        // Instance fields - each student gets separate values
        String name;
        String regNo;
        int attendance;

        // Static fields - shared by the whole class
        static String university = "SRM";
        static int admissionCount = 0;

        // Constructor
        SrmStudent(String name, int attendance) {

            this.name = name;
            this.attendance = attendance;

            // Increase admission count for every new student
            admissionCount++;

            // Generate registration number automatically
            this.regNo = "RA2311003010"
                    + String.format("%02d", admissionCount);
        }

        // Instance method
        void printIdCard() {

            System.out.println(
                    name + " | " + regNo
            );
        }

        // Static method
        static void printTotalAdmissions() {

            System.out.println(
                    "Students admitted so far: " + admissionCount
            );
        }
    }


    public static void main(String[] args) {

        // =====================================================
        // BROKEN VERSION TEST
        // =====================================================

        System.out.println("BROKEN VERSION:");

        BrokenSrmStudent student1 =
                new BrokenSrmStudent("Ravi", "RA101", 82);

        BrokenSrmStudent student2 =
                new BrokenSrmStudent("Meera", "RA102", 90);

        System.out.println("Student 1 name:");
        student1.printName();

        System.out.println("Student 2 name:");
        student2.printName();

        System.out.println(
                "Both students show Meera because name is static."
        );


        // =====================================================
        // CORRECTED VERSION TEST
        // =====================================================

        System.out.println("\nFIXED VERSION:");

        SrmStudent s1 =
                new SrmStudent("Ravi", 82);

        SrmStudent s2 =
                new SrmStudent("Meera", 90);

        // Print both student ID cards
        s1.printIdCard();
        s2.printIdCard();

        // Print total admissions
        SrmStudent.printTotalAdmissions();
    }
}

package Classes&Objects_PracticeProblems;
public class CollegeManagementSystem {

    // =========================================================
    // F2: FeeAccount - Parent Class
    // =========================================================
    static class FeeAccount {

        private String regNo;
        private double totalFee;
        private double amountPaid;

        // Constructor
        FeeAccount(String regNo, double totalFee, double amountPaid) {
            this.regNo = regNo;
            this.totalFee = totalFee;
            this.amountPaid = amountPaid;
        }

        // Payment method
        void pay(double amount) {

            if (amount <= 0) {
                System.out.println(
                    "Rejected payment for " + regNo +
                    ": amount must be positive"
                );
                return;
            }

            amountPaid += amount;
        }

        // Calculate remaining fee
        double getDue() {
            return totalFee - amountPaid;
        }
    }


    // =========================================================
    // F2: HostelFeeAccount - Child Class
    // =========================================================
    static class HostelFeeAccount extends FeeAccount {

        // Constructor
        HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
            super(regNo, totalFee, amountPaid);
        }

        // Pay an amount in two equal installments
        void payInTwoInstallments(double amount) {

            if (amount <= 0) {
                System.out.println(
                    "Rejected hostel payment: amount must be positive"
                );
                return;
            }

            double installment = amount / 2;

            pay(installment);
            pay(installment);
        }
    }


    // =========================================================
    // F3: HostelRoom
    // =========================================================
    static class HostelRoom {

        String roomNo;
        int beds;
        int occupied;

        // Constructor
        HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        // Allot one bed
        void allot(String name) {

            if (occupied < beds) {
                occupied++;

                System.out.println(
                    name + " allotted to room " + roomNo
                );
            }
        }
    }


    // Find first room with an available bed
    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {

        for (int i = 0; i < rooms.length; i++) {

            if (rooms[i] != null &&
                rooms[i].occupied < rooms[i].beds) {

                return rooms[i];
            }
        }

        return null;
    }


    // Safely allot a student to a room
    static void safeAllot(HostelRoom[] rooms, String studentName) {

        HostelRoom room = findAvailableRoom(rooms);

        // Check for null before using room
        if (room == null) {
            System.out.println(
                "No rooms available for " + studentName
            );
            return;
        }

        room.allot(studentName);
    }


    // =========================================================
    // F5: SrmStudent
    // =========================================================
    static class SrmStudent {

        String name;
        String regNo;

        // Student HAS-A fee account
        HostelFeeAccount feeAccount;

        // Student HAS-A hostel room
        HostelRoom room;

        // Static counter shared by all students
        static int totalStudents = 0;

        // Constructor
        SrmStudent(String name, String regNo,
                   HostelFeeAccount feeAccount) {

            this.name = name;
            this.regNo = regNo;
            this.feeAccount = feeAccount;
            this.room = null;

            totalStudents++;
        }

        // Assign a room to the student
        void assignRoom(HostelRoom room) {
            this.room = room;
        }

        // Display complete student information
        String fullStatus() {

            String roomInfo;

            if (room == null) {
                roomInfo = "unallotted";
            } else {
                roomInfo = room.roomNo;
            }

            return name +
                   " | Due: Rs " +
                   feeAccount.getDue() +
                   " | Room: " +
                   roomInfo;
        }
    }


    // =========================================================
    // MAIN
    // =========================================================
    public static void main(String[] args) {

        // -----------------------------------------------------
        // Create hostel rooms
        // -----------------------------------------------------

        HostelRoom room1 =
            new HostelRoom("C-214", 3, 2);

        HostelRoom room2 =
            new HostelRoom("C-507", 2, 1);

        HostelRoom[] rooms = {
            room1,
            room2
        };


        // -----------------------------------------------------
        // Create fee accounts
        // -----------------------------------------------------

        HostelFeeAccount raviFee =
            new HostelFeeAccount(
                "RA101",
                200000,
                60000
            );

        HostelFeeAccount anithaFee =
            new HostelFeeAccount(
                "RA102",
                200000,
                20000
            );

        HostelFeeAccount karthikFee =
            new HostelFeeAccount(
                "RA103",
                200000,
                0
            );


        // -----------------------------------------------------
        // Create students
        // -----------------------------------------------------

        SrmStudent ravi =
            new SrmStudent(
                "Ravi",
                "RA101",
                raviFee
            );

        SrmStudent anitha =
            new SrmStudent(
                "Anitha",
                "RA102",
                anithaFee
            );

        SrmStudent karthik =
            new SrmStudent(
                "Karthik",
                "RA103",
                karthikFee
            );


        // -----------------------------------------------------
        // Process payments
        // -----------------------------------------------------

        // Valid payment
        ravi.feeAccount.pay(10000);

        // Valid payment using two installments
        anitha.feeAccount.payInTwoInstallments(20000);

        // Rejected payment
        karthik.feeAccount.pay(-5000);


        // -----------------------------------------------------
        // Allocate rooms to only TWO students
        // -----------------------------------------------------

        HostelRoom raviRoom = findAvailableRoom(rooms);

        if (raviRoom != null) {
            ravi.assignRoom(raviRoom);
            raviRoom.allot(ravi.name);
        }

        HostelRoom anithaRoom = findAvailableRoom(rooms);

        if (anithaRoom != null) {
            anitha.assignRoom(anithaRoom);
            anithaRoom.allot(anitha.name);
        }

        // Karthik intentionally receives no room


        // -----------------------------------------------------
        // Print final student status
        // -----------------------------------------------------

        System.out.println("\n========== STUDENT STATUS ==========");

        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());

        System.out.println(
            "Total students: " +
            SrmStudent.totalStudents
        );
    }
}

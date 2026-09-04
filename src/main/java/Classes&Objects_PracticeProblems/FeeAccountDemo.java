package Classes&Objects_PracticeProblems;
public class FeeAccountDemo {

    // Parent class
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

        // Pay method
        void pay(double amount) {

            // Reject non-positive payments
            if (amount <= 0) {
                System.out.println("Invalid payment amount");
                return;
            }

            amountPaid += amount;
        }

        // Calculate remaining fee
        double getDue() {
            return totalFee - amountPaid;
        }
    }

    // Child class 1
    static class HostelFeeAccount extends FeeAccount {

        HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
            super(regNo, totalFee, amountPaid);
        }

        // Pay amount in two installments
        void payInTwoInstallments(double amount) {

            if (amount <= 0) {
                System.out.println("Invalid payment amount");
                return;
            }

            double installment = amount / 2;

            pay(installment);
            pay(installment);
        }
    }

    // Child class 2
    static class ScholarshipFeeAccount extends FeeAccount {

        private double scholarshipPercent;

        ScholarshipFeeAccount(String regNo, double totalFee,
                              double amountPaid, double scholarshipPercent) {

            super(regNo, totalFee, amountPaid);

            // Scholarship must be between 0 and 100
            if (scholarshipPercent >= 0 && scholarshipPercent <= 100) {
                this.scholarshipPercent = scholarshipPercent;
            } else {
                this.scholarshipPercent = 0;
            }
        }

        // Calculate fee after scholarship
        double effectiveDue() {

            double due = getDue();

            double discount = due * scholarshipPercent / 100;

            return due - discount;
        }
    }

    public static void main(String[] args) {

        // Create one object of each account type

        FeeAccount plain =
                new FeeAccount("RA101", 150000, 150000);

        HostelFeeAccount hostel =
                new HostelFeeAccount("RA102", 200000, 60000);

        ScholarshipFeeAccount scholarship =
                new ScholarshipFeeAccount("RA103", 180000, 0, 20);


        // Plain account payment
        plain.pay(0);   // Non-positive payment is rejected


        // Print plain account due
        System.out.println("Plain account due: Rs " + plain.getDue());


        // Use instanceof to identify HostelFeeAccount
        if (hostel instanceof HostelFeeAccount) {

            hostel.payInTwoInstallments(0);
        }

        System.out.println("Hostel account due: Rs " + hostel.getDue());


        // Use instanceof to identify ScholarshipFeeAccount
        if (scholarship instanceof ScholarshipFeeAccount) {

            System.out.println("Scholarship effective due: Rs "
                    + scholarship.effectiveDue());
        }
    }
}

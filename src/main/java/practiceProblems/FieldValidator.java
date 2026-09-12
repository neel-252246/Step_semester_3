public class Main {

    // ---------------------------------------------------
    // AccessRuleEngine
    // ---------------------------------------------------
    static class AccessRuleEngine {

        // Decide whether access is ALLOWED or DENIED
        static String classifyAccess(String fieldModifier,
                                     String accessorContext) {

            // private -> accessible only within SAME_CLASS
            if (fieldModifier.equals("private")) {
                if (accessorContext.equals("SAME_CLASS")) {
                    return "ALLOWED";
                } else {
                    return "DENIED";
                }
            }

            // default/package-private -> accessible within SAME_PACKAGE
            if (fieldModifier.equals("default")) {
                if (accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")) {
                    return "ALLOWED";
                } else {
                    return "DENIED";
                }
            }

            // protected -> SAME_CLASS, SAME_PACKAGE
            // and subclasses in DIFFERENT_PACKAGE.
            // Since the given contexts do not specify inheritance,
            // DIFFERENT_PACKAGE is denied.
            if (fieldModifier.equals("protected")) {
                if (accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")) {
                    return "ALLOWED";
                } else {
                    return "DENIED";
                }
            }

            // public -> accessible everywhere
            if (fieldModifier.equals("public")) {
                return "ALLOWED";
            }

            // Unknown modifier/context
            return "DENIED";
        }


        // Summarize a batch of access attempts
        static String summarizeBatch(String[][] attempts) {

            int allowed = 0;
            int denied = 0;

            for (String[] attempt : attempts) {

                String result = classifyAccess(attempt[0], attempt[1]);

                if (result.equals("ALLOWED")) {
                    allowed++;
                } else {
                    denied++;
                }
            }

            return "Allowed : " + allowed + " | Denied : " + denied;
        }
    }


    // ---------------------------------------------------
    // PatientRecord
    // ---------------------------------------------------
    static class PatientRecord {

        // Patient ID should not be directly accessible
        private String patientId;

        // Ward information can be accessed by same package/subclasses
        protected String wardCode;

        // Medical score should be protected from direct outside access
        private double vitalScore;

        // Facility name can be accessed generally
        public String facilityName;


        // Parameterized constructor only
        PatientRecord(String patientId,
                      String wardCode,
                      double vitalScore,
                      String facilityName) {

            // Reject null, blank, whitespace-only,
            // or less than 4 characters
            if (patientId == null ||
                patientId.trim().isEmpty() ||
                patientId.trim().length() < 4) {

                throw new IllegalArgumentException(
                    "Invalid patientId"
                );
            }

            this.patientId = patientId;
            this.wardCode = wardCode;
            this.vitalScore = vitalScore;
            this.facilityName = facilityName;
        }
    }


    // ---------------------------------------------------
    // Main method for testing
    // ---------------------------------------------------
    public static void main(String[] args) {

        // Example 1
        System.out.println(
            AccessRuleEngine.classifyAccess(
                "private",
                "SAME_CLASS"
            )
        );


        // Example 2
        System.out.println(
            AccessRuleEngine.classifyAccess(
                "default",
                "DIFFERENT_PACKAGE"
            )
        );


        // Example 3
        String[][] attempts = {
            {"protected", "SAME_PACKAGE"},
            {"protected", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
            AccessRuleEngine.summarizeBatch(attempts)
        );


        // Example 4 - valid PatientRecord
        PatientRecord p1 = new PatientRecord(
            "MT94",
            "W3",
            98.2,
            "MediTrack Central"
        );

        System.out.println("Patient record created successfully.");


        // Example 5 - invalid PatientRecord
        try {

            PatientRecord p2 = new PatientRecord(
                "MT9",
                "W3",
                98.2,
                "MediTrack Central"
            );

        } catch (IllegalArgumentException e) {

            System.out.println("construction rejected");
        }
    }
}

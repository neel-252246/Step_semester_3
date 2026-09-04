package Classes&Objects_PracticeProblems;
public class HostelAllocation {

    // HostelRoom class
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

        // Allot a bed if the room is not full
        void allot(String name) {

            if (occupied < beds) {
                occupied++;
                System.out.println(name + " allotted to room " + roomNo);
            }
        }
    }

    // Find the first room having an available bed
    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {

        for (int i = 0; i < rooms.length; i++) {

            if (rooms[i].occupied < rooms[i].beds) {
                return rooms[i];
            }
        }

        // No room is available
        return null;
    }

    // Safely allot a student to an available room
    static void safeAllot(HostelRoom[] rooms, String studentName) {

        HostelRoom room = findAvailableRoom(rooms);

        // Check for null before using the object
        if (room == null) {
            System.out.println("No rooms available for " + studentName);
            return;
        }

        // Allot the student
        room.allot(studentName);
    }

    public static void main(String[] args) {

        // First case: room available
        HostelRoom[] rooms1 = {
            new HostelRoom("C-214", 3, 2),
            new HostelRoom("C-507", 2, 2)
        };

        System.out.println("Case 1:");
        safeAllot(rooms1, "Divya");


        // Second case: all rooms are full
        HostelRoom[] rooms2 = {
            new HostelRoom("C-214", 3, 3),
            new HostelRoom("C-507", 2, 2)
        };

        System.out.println("\nCase 2:");
        safeAllot(rooms2, "Divya");
    }
}

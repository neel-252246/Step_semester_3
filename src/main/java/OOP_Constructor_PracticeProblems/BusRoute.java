package OOP_Constructor_PracticeProblems;
import java.util.Arrays;

public class BusRoute implements Comparable<BusRoute> {

    private String routeCode;
    private String routeName;
    private int priority;

    // Constructor with all details
    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    // Constructor with 2 arguments
    // Priority defaults to 0
    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 0);
    }

    // compareTo() returns a signed integer
    @Override
    public int compareTo(BusRoute other) {

        // 1. Higher priority comes first
        int result = Integer.compare(other.priority, this.priority);

        if (result != 0) {
            return result;
        }

        // 2. If priority is same, compare route names
        result = this.routeName.compareTo(other.routeName);

        if (result != 0) {
            return result;
        }

        // 3. If route names are also same, compare route codes
        return this.routeCode.compareTo(other.routeCode);
    }

    // Sort all routes
    public static BusRoute[] rankRoutes(BusRoute[] routes) {

        // Uses Arrays.sort(), not a built-in sort utility from another class
        Arrays.sort(routes);

        return routes;
    }

    // toString() for displaying route code
    @Override
    public String toString() {
        return routeCode;
    }

    // Main method for testing
    public static void main(String[] args) {

        BusRoute[] routes = {
            new BusRoute("RT205", "Airport Express", 3),
            new BusRoute("RT201", "City Central", 4),
            new BusRoute("RT299", "Night Service") // priority defaults to 0
        };

        BusRoute[] rankedRoutes = rankRoutes(routes);

        System.out.println(Arrays.toString(rankedRoutes));
    }
}

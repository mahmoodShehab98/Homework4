package Stations;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Arrays;

public class Test {

    private static final Map<String, List<String>> LINES = new HashMap<>();

    static {
        LINES.put("Red", Arrays.asList(
                "South Station",
                "Park Street",
                "Kendall",
                "Central",

                "Harvard",
                "Porter",
                "Davis",
                "Alewife"
        ));

        LINES.put("Green", Arrays.asList(
                "Government Center",
                "Park Street",
                "Boylston",
                "Arlington",
                "Copley",
                "Hynes",
                "Kenmore"
        ));

        LINES.put("Orange", Arrays.asList(
                "North Station",
                "Haymarket",
                "Park Street",
                "State",
                "Downtown Crossing",
                "Chinatown",
                "Back Bay",
                "Forest Hills"
        ));
    }

    public static int stopsBetweenStations(String startLine, String startStation, String endLine, String endStation) {
        // Validation Check (Bonus Part 1)
        if (!isValidStation(startLine, startStation) || !isValidStation(endLine, endStation)) {
            System.out.println("Invalid line or station name provided.");
            return -1;
        }

        System.out.println("--- Trip Details ---");
        System.out.println("// Rider boards the train at " + startLine + " Line and " + startStation + ".");

        if (startLine.equals(endLine)) {
            int stops = printAndCountStops(startLine, startStation, endStation);
            System.out.println("// Rider exits the train at " + endLine + " Line and " + endStation + ".\n");
            return stops;
        }

        // Travel to Park Street
        int stopsToPark = printAndCountStops(startLine, startStation, "Park Street");
        System.out.println("// Rider transfers from " + startLine + " Line to " + endLine + " Line at Park Street.");

        // Travel from Park Street to destination
        int stopsFromPark = printAndCountStops(endLine, "Park Street", endStation);
        System.out.println("// Rider exits the train at " + endLine + " Line and " + endStation + ".\n");

        return stopsToPark + stopsFromPark;
    }

    private static boolean isValidStation(String line, String station) {
        return LINES.containsKey(line) && LINES.get(line).contains(station);
    }

    private static int printAndCountStops(String line, String fromStation, String toStation) {
        List<String> stations = LINES.get(line);
        int startIndex = stations.indexOf(fromStation);
        int endIndex = stations.indexOf(toStation);

        int step = (startIndex < endIndex) ? 1 : -1;
        int count = 0;

        for (int i = startIndex + step; i != endIndex + step; i += step) {
            System.out.println("// Rider arrives at " + line + " Line and " + stations.get(i) + ".");
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        // Bonus Example Test
        int totalStops = stopsBetweenStations("Red", "South Station", "Green", "Copley");
        System.out.println("Total stops: " + totalStops);
    }
}
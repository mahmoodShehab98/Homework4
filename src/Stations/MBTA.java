package Stations;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Arrays;

public class MBTA {

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
        if (startLine.equals(endLine)) {
            return getStopsOnSameLine(startLine, startStation, endStation);
        }

        int stopsToParkStreet = getStopsOnSameLine(startLine, startStation, "Park Street");
        int stopsFromParkStreet = getStopsOnSameLine(endLine, "Park Street", endStation);

        return stopsToParkStreet + stopsFromParkStreet;
    }

    private static int getStopsOnSameLine(String line, String fromStation, String toStation) {
        List<String> stations = LINES.get(line);
        int startIndex = stations.indexOf(fromStation);
        int endIndex = stations.indexOf(toStation);
        return Math.abs(startIndex - endIndex);

    }

    public static void main(String[] args) {
        System.out.println(stopsBetweenStations("Red", "Alewife", "Red", "Alewife")); // 0
        System.out.println(stopsBetweenStations("Red", "Alewife", "Red", "South Station")); // 7
        System.out.println(stopsBetweenStations("Red", "South Station", "Green", "Kenmore")); // 6
    }
}
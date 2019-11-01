public class UtilitiesCSV {
    private static final String[] EMPTY = {};
    private static final int CANCELED = 19;
    private static final int DELAYED = 18;
    private static final int AIRPORT_KEY = 0;
    private static final int FLIGHT_KEY = 1;
    private static final int FIRST_ITEM = 0;

    private static final int DEST_AIRPORT_INDEX = 14;

    public static String[]

    private static String[] parseFlightData(String line) {
        return line.split(",");
    }

    private static String[] filterFlightData(String[] parsed) {
        if (parsed[FIRST_ITEM].equals("\"YEAR\"")) {
            return EMPTY;
        } else {
            return parsed;
        }
    }

    /*static String[] removeQuotes(String[] filtered) {
        int size = filtered.length;
        for (int i = 0; i < size; i++) {
            filtered[i] = filtered[i].replaceAll("\"", "");
        }

        return filtered;
    }*/
}

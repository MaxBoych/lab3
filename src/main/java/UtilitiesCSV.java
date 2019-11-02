public class UtilitiesCSV {
    private static final String[] EMPTY = {};
    private static final int CANCELED = 19;
    private static final int DELAYED = 18;
    private static final int AIRPORT_KEY = 0;
    private static final int FLIGHT_KEY = 1;
    private static final int FIRST_ITEM = 0;

    static String[] parseAndFilter(String line, int keyData) {
        if (keyData == AIRPORT_KEY) {
            return filterAirportData(parseAirportData(line));
        } else if (keyData == FLIGHT_KEY) {
            return removeQuotes(filterFlightData(parseFlightData(line)));
        } else {
            return EMPTY;
        }
    }

    private static String[] parseAirportData(String line) {
        return line.split(",", 2);
    }

    private static String[] parseFlightData(String line) {
        return line.split(",");
    }

    /*static String[] filter(String[] parsed, int keyData) {
        if (keyData == AIRPORT_KEY) {
            return filterAirportData(parsed);
        } else if (keyData == FLIGHT_KEY) {
            return filterFlightData(parsed);
        } else {
            return EMPTY;
        }
    }*/

    private static String[] filterAirportData(String[] parsed) {
        if (parsed[FIRST_ITEM].equals("Code")) {
            return EMPTY;
        } else {
            return parsed;
        }
    }

    private static String[] filterFlightData(String[] parsed) {
        if ((parsed[FIRST_ITEM].equals("\"YEAR\"")) ||
                (parsed[CANCELED].equals("1.00")) ||
                (parsed[DELAYED].equals("0.00"))) {
            return EMPTY;
        } else {
            return parsed;
        }
    }

    private static String[] removeQuotes(String[] filtered) {
        if (filtered.length == 0) {
            return EMPTY;
        } else {
            int size = filtered.length;
            for (int i = 0; i < size; i++) {
                filtered[i] = filtered[i].replaceAll("\"", "");
            }

            return filtered;
        }
    }
}

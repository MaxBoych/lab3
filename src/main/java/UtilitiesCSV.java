public class UtilitiesCSV {
    private static final String[] EMPTY = {};
    private static final int FIRST_ITEM = 0;

    public static String[] parseAndFilter(String line) {
        return removeQuotes(filterFlightData(parseFlightData(line)));
    }

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

    private static String[] removeQuotes(String[] filtered) {
        int size = filtered.length;
        for (int i = 0; i < size; i++) {
            filtered[i] = filtered[i].replaceAll("\"", "");
        }
        return filtered;
    }

}

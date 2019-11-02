public class FlightInfo {

    private double delayed;
    private double cancelled;

    public FlightInfo(String delayed, String cancelled) {
        if (delayed.equals("")) {
            this.delayed = 0;
            this.cancelled = 0;
        } else {
            this.delayed = Double.parseDouble(delayed);
            this.cancelled = Double.parseDouble(cancelled);
        }
    }

    public double getDelayed() {
        return this.delayed;
    }

    public double getCancelled() {
        return this.cancelled;
    }
}

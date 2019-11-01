public class FlightInfo {

    private double delayed;
    private double cancelled;

    public FlightInfo(double delayed, double cancelled) {
        this.delayed = delayed;
        this.cancelled = cancelled;
    }

    public double getDelayed() {
        return this.delayed;
    }

    public double getCancelled() {
        return this.cancelled;
    }
}

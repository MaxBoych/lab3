public class FlightInfo {

    private int delayed;
    private int cancelled;

    public FlightInfo(int delayed, int cancelled) {
        this.delayed = delayed;
        this.cancelled = cancelled;
    }

    public int getDelayed() {
        return this.delayed;
    }

    public int getCancelled() {
        return this.cancelled;
    }
}

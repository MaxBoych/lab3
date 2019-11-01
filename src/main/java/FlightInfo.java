public class FlightInfo {

    private double delayed;
    private int cancelled;

    public FlightInfo(String delayed, String cancelled) {
        if (delayed.equals("")) {
            this.delayed = 0;
            this.cancelled = 0;
        } else {
            this.delayed = Double.parseDouble(delayed);
            this.cancelled = Integer.parseInt(cancelled);
        }
    }

    public double getDelayed() {
        return this.delayed;
    }

    public int getCancelled() {
        return this.cancelled;
    }
}

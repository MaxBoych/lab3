public class FlightInfo {

    private int delayed;
    private int cancelled;

    public FlightInfo(String delayed, String cancelled) {
        if (delayed.equals("")) {
            this.delayed = 0;
        } else {
            this.delayed = Integer.parseInt(delayed);
        }

        this.cancelled = Integer.parseInt(cancelled);
    }

    public int getDelayed() {
        return this.delayed;
    }

    public int getCancelled() {
        return this.cancelled;
    }
}

import java.io.Serializable;

public class FlightSerializable implements Serializable {

    private int flightsAmount;
    private int flightsDelayed;
    private int delayMaxValue;
    private int flightsCancelled;

    public FlightSerializable(int flightsAmount, int flightsDelayed,
                              int delayMaxValue, int flightsCancelled) {
        this.flightsAmount = flightsAmount;
        this.flightsDelayed = flightsDelayed;
        this.delayMaxValue = delayMaxValue;
        this.flightsCancelled = flightsCancelled;
    }

    public int getFlightsAmount() {
        return this.flightsAmount;
    }

    public int getFlightsDelayed() {
        return this.flightsDelayed;
    }

    public int getDelayMaxValue() {
        return this.delayMaxValue;
    }

    public int getFlightsCancelled() {
        return this.flightsCancelled;
    }
}

import java.io.Serializable;

public class FlightSerializable implements Serializable {

    private int flightsAmount;
    private int flightsDelayed;
    private double delayMaxValue;
    private int flightsCancelled;

    public FlightSerializable(int flightsAmount, int flightsDelayed,
                              double delayMaxValue, int flightsCancelled) {
        this.flightsAmount = flightsAmount;
        this.flightsDelayed = flightsDelayed;
        this.delayMaxValue = delayMaxValue;
        this.flightsCancelled = flightsCancelled;
    }

    public FlightSerializable reduceByKey(FlightInfo flight1, FlightInfo flight2) {
        FlightSerializable fs1 = createObject(flight1);
        FlightSerializable fs2 = createObject(flight2);

        return new FlightSerializable(
                fs1.getFlightsAmount() + fs2.getFlightsAmount(),
                fs1.getFlightsDelayed() + fs2.getFlightsDelayed(),
                Math.max(fs1.getDelayMaxValue(), fs2.getDelayMaxValue()),
                fs1.getFlightsCancelled() + fs2.getFlightsCancelled()
        );
    }

    private FlightSerializable createObject(FlightInfo flight) {
        int flightsDelayedSupport = flight.getDelayed() > 0 ? 1 : 0;
        return new FlightSerializable(
                1,
                flightsDelayedSupport,
                flight.getDelayed(),
                flight.getCancelled()
        );
    }

    public int getFlightsAmount() {
        return this.flightsAmount;
    }

    public int getFlightsDelayed() {
        return this.flightsDelayed;
    }

    public double getDelayMaxValue() {
        return this.delayMaxValue;
    }

    public int getFlightsCancelled() {
        return this.flightsCancelled;
    }
}

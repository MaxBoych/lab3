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

    public FlightSerializable(FlightInfo fl) {
        if (fl.getDelayed() == 0) {
            this.delayMaxValue = 0;
            this.flightsDelayed = 0;
        } else {
            this.delayMaxValue = fl.getDelayed();
            this.flightsDelayed = 1;
        }

        this.flightsCancelled = fl.getCancelled();

        this.flightsAmount = 1;
    }

    public static FlightSerializable reduceFlights(FlightSerializable fs1, FlightSerializable fs2) {
        //FlightSerializable fs1 = createObject(flight1);
        //FlightSerializable fs2 = createObject(flight2);

        return new FlightSerializable(
                fs1.getFlightsAmount() + fs2.getFlightsAmount(),
                fs1.getFlightsDelayed() + fs2.getFlightsDelayed(),
                Math.max(fs1.getDelayMaxValue(), fs2.getDelayMaxValue()),
                fs1.getFlightsCancelled() + fs2.getFlightsCancelled()
        );
    }

    /*private static FlightSerializable createObject(FlightInfo flight) {
        int flightsDelayedSupport = flight.getDelayed() > 0 ? 1 : 0;
        return new FlightSerializable(
                1,
                flightsDelayedSupport,
                flight.getDelayed(),
                flight.getCancelled()
        );
    }*/

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

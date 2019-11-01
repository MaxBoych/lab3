import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class AirportGroupingComparator extends WritableComparator {
    AirportGroupingComparator() {
        super(AirportWritableComparable.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        AirportWritableComparable a1 = (AirportWritableComparable) a;
        AirportWritableComparable b1 = (AirportWritableComparable) b;

        return a1.getAirportID() - b1.getAirportID();
    }
}

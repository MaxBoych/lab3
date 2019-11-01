import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

public class DataPartitioner extends HashPartitioner<AirportWritableComparable, Text> {
    @Override
    public int getPartition(AirportWritableComparable key, Text value, int numReduceTasks) {
        return (Integer.valueOf(key.getAirportID()).hashCode() & Integer.MAX_VALUE) % numReduceTasks;
    }
}

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportMapper extends Mapper<LongWritable, Text, AirportWritableComparable, Text> {
    private static final int AIRPORT_ID = 0;
    private static final int AIRPORT_NAME = 1;
    private static final int KEY_DATA = 0;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        MapTemplate.use(value, context, KEY_DATA, AIRPORT_ID, AIRPORT_NAME);
    }
}

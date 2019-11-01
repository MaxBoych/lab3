import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple4;

public class AirportTime {

    private static final String FLIGHTS_CSV = "/home/max/gitwatch/lab3/flights.csv";

    private static final int ORIGIN_AIRPORT_ID = 11;
    private static final int DEST_AIRPORT_ID = 14;
    private static final int ARR_DELAY_NEW = 18;
    private static final int CANCELLED = 19;

    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName("lab3");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> flights = sparkContext.textFile(FLIGHTS_CSV);
        JavaRDD<String[]> flightsFiltered = flights.map(UtilitiesCSV::parseAndFilter);

        JavaRDD<Tuple4<String, String, String, String>> flightsPairs = flightsFiltered
                .mapToPair(
                        values -> {
                            new Tuple4<>(values[
                                    values[ORIGIN_AIRPORT_ID]],
                                    values[DEST_AIRPORT_ID],
                                    values[ARR_DELAY_NEW],
                                    values[CANCELLED])
                        }
                );
    }
}

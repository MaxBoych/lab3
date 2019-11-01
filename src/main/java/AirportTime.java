import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class AirportTime {

    private static final String FLIGHTS_CSV = "/home/max/gitwatch/lab3/flights.csv";

    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName("lab3");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> flights = sparkContext.textFile(FLIGHTS_CSV);
        JavaRDD<String[]> flightsFiltered = UtilitiesCSV.parseAndFilter(flights);
    }
}

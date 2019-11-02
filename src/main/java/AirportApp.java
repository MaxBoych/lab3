import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
import scala.Tuple4;

public class AirportApp {

    private static final String FLIGHTS_CSV = "/home/max/gitwatch/lab3/flights.csv";
    private static final int ORIGIN_AIRPORT_ID = 11;
    private static final int DEST_AIRPORT_ID = 14;
    private static final int ARR_DELAY_NEW = 18;
    private static final int CANCELLED = 19;
    public static void main(String[] args) {

        FlightSerializable f = new FlightSerializable(new FlightInfo("1", "2"));

        SparkConf sparkConf = new SparkConf().setAppName("lab3");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> flights = sparkContext.textFile(FLIGHTS_CSV);
        JavaRDD<String[]> flightsFiltered = flights.map(UtilitiesCSV::parseAndFilter);


        JavaPairRDD<Tuple2<String, String>, FlightInfo> flightsPairs = flightsFiltered
                .mapToPair(

                        values -> {
                            FlightInfo flightInfo = new FlightInfo(values[ARR_DELAY_NEW], values[CANCELLED]);
                            new Tuple2<>(
                                    new Tuple2<>(values[ORIGIN_AIRPORT_ID], values[DEST_AIRPORT_ID]),
                                    new FlightSerializable(flightInfo)
                            );
                        }
                );

        JavaPairRDD<Tuple2<String, String>, FlightSerializable> flightsPairsSerializable = flightsPairs.reduceByKey(FlightSerializable::reduceFlights);
    }
}

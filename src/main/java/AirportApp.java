import com.sun.deploy.uitoolkit.ui.LoggerConsole;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.spark_project.jetty.util.log.Log;
import scala.Tuple2;

import java.util.Map;
import java.util.logging.Logger;

public class AirportApp {

    private static final String FLIGHTS_CSV = "/home/max/gitwatch/lab3/flights.csv";
    private static final String AIRPORTS_CSV = "/home/max/gitwatch/lab3/flights.csv";

    private static final int ORIGIN_AIRPORT_ID = 11;
    private static final int DEST_AIRPORT_ID = 14;
    private static final int ARR_DELAY_NEW = 18;
    private static final int CANCELLED = 19;

    private static final int AIRPORT_CODE = 0;
    private static final int AIRPORT_NAME = 1;

    public static void main(String[] args) {
        /*if (args.length != 3) {
            System.exit(-1);
        }*/


        System.out.println();
        System.out.println();
        System.out.println("@@@ " + args[0]);
        System.out.println();

        Log.getLogger("@@@ " + args[0]);
        Logger.getLogger("@@@ " + args[0]);

        SparkConf sparkConf = new SparkConf().setAppName("lab3");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);


        //flights
        JavaRDD<String> flights = sparkContext.textFile(FLIGHTS_CSV);
        JavaRDD<String[]> flightsFiltered = flights
                .map(line -> UtilitiesCSV.parseAndFilter(line, 1));

        JavaPairRDD<Tuple2<String, String>, FlightSerializable> flightsPairs = flightsFiltered
                .mapToPair(
                        values -> {
                            FlightInfo flightInfo = new FlightInfo(values[ARR_DELAY_NEW], values[CANCELLED]);
                            return new Tuple2<>(
                                    new Tuple2<>(values[ORIGIN_AIRPORT_ID], values[DEST_AIRPORT_ID]),
                                    new FlightSerializable(flightInfo)
                            );
                        }
                );

        JavaPairRDD<Tuple2<String, String>, FlightSerializable> flightsPairsSerializable = flightsPairs
                .reduceByKey(FlightSerializable::reduceFlights);



        //airports
        JavaRDD<String> airports = sparkContext.textFile(AIRPORTS_CSV);
        JavaRDD<String[]> airportsFiltered = airports.map(line -> UtilitiesCSV
                .parseAndFilter(line, 0));

        JavaPairRDD<String, String> airportsPairs = airportsFiltered
                .mapToPair(
                        values -> new Tuple2<>(
                                values[AIRPORT_CODE],
                                values[AIRPORT_NAME]
                        )
                );

        Map<String, String> airportsMap = airportsPairs
                .collectAsMap();
        final Broadcast<Map<String, String>> airportsBroadcasted = sparkContext
                .broadcast(airportsMap);

        JavaRDD<String> result = flightsPairsSerializable
                .map(
                        pair -> "from " +
                                airportsBroadcasted.value().get(pair._1._1) +

                                " to " +
                                airportsBroadcasted.value().get(pair._1._2) + " | " +

                                "delayMaxValue: " +
                                pair._2.getDelayMaxValue() + " | " +

                                "percentageDelayedFlights: " +
                                (pair._2.getFlightsDelayed() / pair._2.getFlightsAmount()) * 100 + " % | " +

                                "percentageCancelledFlights: " +
                                (pair._2.getFlightsCancelled() / pair._2.getFlightsAmount()) * 100 + " %\n"
                );

        result.saveAsTextFile("output");
    }
}

package airline;

import airline.model.Airplane;
import airline.service.AirlineService;
import airline.service.impl.AirlineServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Viktar on 29.09.2019
 */
public class Main {
    private static final String FILENAME = "airplanes.txt";
    static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {


        logger.info("Start");
        AirlineService service = new AirlineServiceImpl();
        List<Airplane> airplanes = service.createAirplanesList(FILENAME);

        for (Airplane airplane : airplanes) {
            System.out.println(airplane);
        }

    }
}

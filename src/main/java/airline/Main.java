package airline;

import airline.model.Airplane;
import airline.service.AirlineService;
import airline.service.impl.AirlineServiceImpl;

import java.util.List;

/**
 * @author Viktar on 29.09.2019
 */
public class Main {
    private static final String FILENAME = "airplanes.txt";

    public static void main(String[] args) {
        AirlineService service = new AirlineServiceImpl();
       List<Airplane> airplanes =  service.createAirplanesList(FILENAME);

        for(Airplane airplane : airplanes){
            System.out.println(airplane);
        }
    }
}

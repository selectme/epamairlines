package taskslist4.maintask.airline;

import taskslist4.maintask.airline.model.Airplane;
import taskslist4.maintask.airline.service.AirlineService;
import taskslist4.maintask.airline.service.impl.AirlineServiceImpl;

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

package taskslist4.maintask.airline.comparator;

import taskslist4.maintask.airline.model.Airplane;
import taskslist4.maintask.airline.model.CargoAirplane;
import taskslist4.maintask.airline.model.PassengerAirplane;

import java.util.Comparator;

/**
 * @author Viktar on 01.10.2019
 */
public class AirplanePassengersCapacityComparator implements Comparator<Airplane> {
    /**
     * Compares airplanes by passenger capacity from larger to smaller
     */
    @Override
    public int compare(Airplane o1, Airplane o2) {
        if (o1 instanceof PassengerAirplane && o2 instanceof PassengerAirplane) {
            return ((PassengerAirplane) o2).getMaxPassengers() - ((PassengerAirplane) o1).getMaxPassengers();
        } else if (o1 instanceof PassengerAirplane && o2 instanceof CargoAirplane) {
            return -1;
        } else return 0;
    }
}


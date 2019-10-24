package taskslist4.maintask.airline.comparator;

import taskslist4.maintask.airline.model.Airplane;

import java.util.Comparator;

/**
 * @author Viktar on 18.10.2019
 */
public class AirplaneFuelConsumptionComparator implements Comparator<Airplane> {

    /**
     * Compares airplanes by fuel consumption from smaller to larger
     */
    @Override
    public int compare(Airplane o1, Airplane o2) {
       return  Double.compare(o1.getFuelConsumption(), o2.getFuelConsumption());
    }
}

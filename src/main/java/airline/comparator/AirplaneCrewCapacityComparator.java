package airline.comparator;

import airline.model.Airplane;

import java.util.Comparator;

/**
 * @author Viktar on 01.10.2019
 */
public class AirplaneCrewCapacityComparator implements Comparator<Airplane> {
    /**
     * Compares airplanes by crew capacity from larger to lower capacity
     */
    @Override
    public int compare(Airplane o1, Airplane o2) {
        return o2.getCrewQuantity() - o1.getCrewQuantity();
    }
}

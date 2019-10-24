package taskslist4.maintask.airline.comparator;

import taskslist4.maintask.airline.model.Airplane;

import java.util.Comparator;

/**
 * @author Viktar on 01.10.2019
 */
public class AirplaneBrandComparator implements Comparator<Airplane> {

    /**
     * Compares airplanes by brand alphabetically
     */
    @Override
    public int compare(Airplane o1, Airplane o2) {
        return o1.getManufacturer().compareTo(o2.getManufacturer());
    }


}

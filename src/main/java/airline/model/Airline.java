package airline.model;

import java.util.List;

/**
 * @author Viktar on 30.09.2019
 */
public class Airline {
    /**
     * List of airplanes
     */
    private List<Airplane> airplanes;

    public Airline(List<Airplane> airplanes) {
        this.airplanes = airplanes;
    }

    public List<Airplane> getAirplanes() {
        return airplanes;
    }

}

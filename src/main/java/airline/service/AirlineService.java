package airline.service;

import airline.model.Airplane;

import java.util.List;

/**
 * @author Viktar on 02.10.2019
 */
public interface AirlineService  {

    List<Airplane> createAirplanesList(String filename);

    int getAirplanesQuantity(List<Airplane> airplanes);

    int findTotalAirplanesCapacity(List<Airplane> airplanes);

    int findTotalPassengersCapacity(List<Airplane> airplanes);

    int findTotalCrewCapacity(List<Airplane> airplanes);

    int findTotalAirplanesCarryingCapacity(List<Airplane> airplanes);

    void sortByPassengersAndCrewCapacity(List<Airplane> airplanes);

    void sortByPassengersCapacity(List<Airplane> airplanes);

    void sortByCrewCapacity(List<Airplane> airplanes);

    Airplane findPlaneByFuelConsumptionRange(List<Airplane> airplanes, int rangeFrom, int rangeTo);

}

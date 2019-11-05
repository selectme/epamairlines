package airline.service.impl;

import airline.comparator.AirplaneCrewCapacityComparator;
import airline.comparator.AirplaneFuelConsumptionComparator;
import airline.comparator.AirplanePassengersCapacityComparator;
import airline.model.Airplane;
import airline.model.AirplaneType;
import airline.model.CargoAirplane;
import airline.model.PassengerAirplane;
import airline.parser.Parser;
import airline.service.AirlineService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

/**
 * @author Viktar on 30.09.2019
 */
public class AirlineServiceImpl implements AirlineService {
    private final static int CHARACTERISTICS_LENGTH = 12;
    private final static Logger logger = LogManager.getLogger(AirlineServiceImpl.class);

    /**
     * Creates array of {@link Airplane}  from a list of airplanes
     *
     * @param filename fle which contains a list of airplanes
     * @return array of {@link Airplane}
     */
    public List<Airplane> createAirplanesList(String filename) {
        Parser parser = new Parser();

        Scanner input = parser.fileInput(filename);

        String sideNumber;
        String manufacturer;
        String model;
        AirplaneType airplaneType;
        int crew;
        int passengers;
        int maxSpeed;
        int maxAltitude;
        int maxFlightRange;
        int fuelSupply;
        double fuelConsumption;
        int cargoCapacity;

        input.nextLine();

        List<Airplane> airplanes = new ArrayList<>();

        while (input.hasNextLine()) {
            String[] characteristics = input.nextLine().split("\\|");
            if (characteristics.length == CHARACTERISTICS_LENGTH) {
                sideNumber = parser.parseSideNumber(characteristics);
                manufacturer = parser.parseManufacturer(characteristics);
                model = parser.parseModel(characteristics);
                airplaneType = parser.parseAirplaneType(characteristics);
                crew = parser.parseCrew(characteristics);
                passengers = parser.parsePassengers(characteristics);
                maxSpeed = parser.parseMaxSpeed(characteristics);
                maxAltitude = parser.parseMaxAltitude(characteristics);
                maxFlightRange = parser.parseMaxFlightRange(characteristics);
                fuelSupply = parser.parseFuelSupply(characteristics);
                fuelConsumption = parser.parseFuelCunsumption(characteristics);
                cargoCapacity = parser.parseCargoCapacity(characteristics);

                if (airplaneType == AirplaneType.CARGO) {
                    airplanes.add(new CargoAirplane(sideNumber, manufacturer, maxSpeed, maxAltitude, model, airplaneType,
                            crew, maxFlightRange, fuelSupply, fuelConsumption, cargoCapacity));
                } else {
                    airplanes.add(new PassengerAirplane(sideNumber, manufacturer, maxSpeed, maxAltitude, model, airplaneType,
                            crew, maxFlightRange, fuelSupply, fuelConsumption, passengers));
                }
            }
        }
        return airplanes;
    }


    /**
     * Finds total passengers and crew capacity of all airplanes
     *
     * @param airplanes array of{@link Airplane}
     * @return quantity of passengers and crew of all airplanes
     */
    public int findTotalAirplanesCapacity(List<Airplane> airplanes) {
        return findTotalPassengersCapacity(airplanes) + findTotalCrewCapacity(airplanes);
    }

    /**
     * Finds passengers capacity of all airplanes
     *
     * @param airplanes array of {@link Airplane}
     * @return total passengers capacity of all airplanes
     */
    public int findTotalPassengersCapacity(List<Airplane> airplanes) {

        int sumOfPassengers = 0;
        for (Airplane airplane : airplanes) {
            if (airplane instanceof PassengerAirplane) {
                sumOfPassengers += ((PassengerAirplane) airplane).getMaxPassengers();
            }
        }
        return sumOfPassengers;
    }

    /**
     * Finds crew capacity of all airplanes
     *
     * @param airplanes array of {@link Airplane}
     * @return total quantity of crew from all airplanes
     */
    public int findTotalCrewCapacity(List<Airplane> airplanes) {
        int sumOfCrews = 0;
        for (Airplane airplane : airplanes) {
            sumOfCrews += airplane.getCrewQuantity();
        }
        return sumOfCrews;
    }

    /**
     * Finds carrying capacity of all airplanes
     *
     * @param airplanes array of {@link Airplane}
     * @return total carrying capacity of all airplanes
     */
    public int findTotalAirplanesCarryingCapacity(List<Airplane> airplanes) {
        int carryingSum = 0;
        for (Airplane airplane : airplanes) {
            if (airplane instanceof CargoAirplane) {
                CargoAirplane cargoAirplane = (CargoAirplane) airplane;
                carryingSum += cargoAirplane.getCargoCapacity();
            }
        }
        return carryingSum;
    }


    /**
     * Gets quantity of airplanes from the airplanes text file
     *
     * @param airplanes array of {@link Airplane}
     * @return quantity of airplanes
     */
    public int getAirplanesQuantity(List<Airplane> airplanes) {
        return airplanes.size();
    }

    /**
     * Sorts airplanes by passengers capacity, then sorts by crew capacity
     *
     * @param airplanes array of{@link Airplane}
     */
    public void sortByPassengersAndCrewCapacity(List<Airplane> airplanes) {
        Comparator<Airplane> comparator = new AirplanePassengersCapacityComparator().thenComparing(new AirplaneCrewCapacityComparator());
        airplanes.sort(comparator);
        for (Airplane airplane : airplanes) {
            System.out.println(airplane);
        }
    }

    /**
     * Sorts airplanes by passengers capacity.
     *
     * @param airplanes array of {@link Airplane}
     */
    public void sortByPassengersCapacity(List<Airplane> airplanes) {
        Comparator<Airplane> comparator = new AirplanePassengersCapacityComparator();
        airplanes.sort(comparator);
        for (Airplane airplane : airplanes) {
            System.out.println(airplane);
        }
    }

    /**
     * Sorts airplanes by crew capacity
     *
     * @param airplanes array of{@link Airplane}
     */
    public void sortByCrewCapacity(List<Airplane> airplanes) {
        Comparator<Airplane> comparator = new AirplaneCrewCapacityComparator();
        airplanes.sort(comparator);
        for (Airplane airplane : airplanes) {
            System.out.println(airplane);
        }
    }

    /**
     * Finds an airplane suitable for the given parameters of fuel consumption, then finds the most economical airplane
     *
     * @param airplanes array of {@link Airplane}
     * @param rangeFrom minimal value of fuel consumption
     * @param rangeTo   max value of fuel consumption
     * @return the most economical airplane suitable of given parameters
     */
    public Airplane findPlaneByFuelConsumptionRange(List<Airplane> airplanes, int rangeFrom, int rangeTo) {
        if ((rangeTo < 0) || (rangeFrom < 0) || (rangeTo - rangeFrom < 0)) {
            logger.error("Range can't be negative");
            throw new IllegalArgumentException();
        }

        List<Airplane> suitableToRangeAirplanes = new ArrayList<>();
        for (Airplane airplane : airplanes) {
            if ((airplane.getFuelConsumption() >= rangeFrom) && (airplane.getFuelConsumption() <= rangeTo)) {
                suitableToRangeAirplanes.add(airplane);
            }
        }
        suitableToRangeAirplanes.sort(new AirplaneFuelConsumptionComparator());
        return suitableToRangeAirplanes.get(0);
    }

}

package airline.service.impl;

import airline.comparator.AirplaneCrewCapacityComparator;
import airline.comparator.AirplaneFuelConsumptionComparator;
import airline.comparator.AirplanePassengersCapacityComparator;
import airline.model.Airplane;
import airline.model.AirplaneType;
import airline.model.CargoAirplane;
import airline.model.PassengerAirplane;
import airline.service.AirlineService;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

/**
 * @author Viktar on 30.09.2019
 */
public class AirlineServiceImpl implements AirlineService {

    /**
     * Creates array of {@link Airplane}  from a list of airplanes
     *
     * @param filename fle which contains a list of airplanes
     * @return array of {@link Airplane}
     */
    public List<Airplane> createAirplanesList(String filename) {

        Scanner input = fileInput(filename);

        String sideNumber;
        String manufacturer;
        String model;
        AirplaneType airplaneType;
        int crew;
        int passengers = 0;
        int maxSpeed;
        int maxAltitude;
        int maxFlightRange;
        int fuelSupply;
        double fuelConsumption;
        int cargoCapacity = 0;

        try {
            input.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("File is empty");
        }

        List<Airplane> airplanes = new ArrayList<>();

        while (input.hasNextLine()) {

            String[] characteristics = input.nextLine().split("\\|");
            if (characteristics.length == 12) {
                sideNumber = characteristics[0].trim();
                manufacturer = characteristics[1].trim();
                model = characteristics[2].trim();

                try {
                    airplaneType = AirplaneType.valueOf(characteristics[3].trim().toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("ERROR! Airplane " + sideNumber + " must be only Passenger or Cargo." +
                            "Check your airplanes list ", e);
                }


                try {
                    crew = Integer.parseInt(characteristics[4].trim());
                    if (crew < 0) {
                        throw new IllegalArgumentException("ERROR! Crew can't be less than 0 and it must be a natural number." +
                                " Check the airplane number " + sideNumber);
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("ERROR! Crew must be a natural number. Check the airplane number "
                            + sideNumber, e);
                }


                try {
                    passengers = Integer.parseInt(characteristics[5].trim());
                    if (passengers < 0) {
                        throw new IllegalArgumentException("ERROR! Passengers can't be less than 0 and it must be a natural number." +
                                " Check the airplane number " + sideNumber);
                    } else if ((airplaneType == AirplaneType.CARGO) && (passengers > 0)) {
                        throw new IllegalStateException("Cargo type can't have passengers. Check the airplane number "
                                + sideNumber);
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("ERROR! Passengers must be a natural number. Check the airplane number "
                            + sideNumber, e);
                }


                try {
                    maxSpeed = Integer.parseInt(characteristics[6].trim());
                    if (maxSpeed < 0) {
                        throw new IllegalArgumentException("Error! Max speed can't be negative. " +
                                "Check the airplane number " + sideNumber);
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("ERROR! Max speed must be a positive natural number. " +
                            "Check the airplane number " + sideNumber);
                }


                try {
                    maxAltitude = Integer.parseInt(characteristics[7].trim());
                    if (maxAltitude < 0) {
                        throw new IllegalArgumentException("Error! Max altitude can't be negative. " +
                                "Check the airplane number " + sideNumber);
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("ERROR! Max altitude must be a positive natural number. " +
                            "Check the airplane number " + sideNumber);
                }


                try {
                    maxFlightRange = Integer.parseInt(characteristics[8].trim());
                    if (maxFlightRange < 0) {
                        throw new IllegalArgumentException("ERROR! Max flight range can't be negative. " +
                                "Check the airplane number " + sideNumber);
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("ERROR! Max speed must be a positive natural number. " +
                            "Check the airplane number " + sideNumber);
                }


                try {
                    fuelSupply = Integer.parseInt(characteristics[9].trim());
                    if (fuelSupply < 0) {
                        throw new IllegalArgumentException("ERROR! Fuel supply range can't be negative. " +
                                "Check the airplane number " + sideNumber);
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("ERROR! Fuel supply must be a positive natural number. " +
                            "Check the airplane number " + sideNumber);
                }


                try {
                    fuelConsumption = Double.parseDouble(characteristics[10].trim());
                    if (fuelConsumption < 0) {
                        throw new IllegalArgumentException("ERROR! Fuel consumption can't be negative. " +
                                "Check the airplane number " + sideNumber);
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("ERROR! Fuel consumption must be a positive number. " +
                            "Check the airplane number " + sideNumber);
                }


                try {
                    cargoCapacity = Integer.parseInt(characteristics[11].trim());
                    if ((airplaneType == AirplaneType.PASSENGER) && (((cargoCapacity > 0) || (cargoCapacity < 0)))) {
                        throw new IllegalArgumentException("Error! Passenger airplane does not carry cargo. " +
                                "Check the airplane number " + sideNumber);
                    } else if (cargoCapacity < 0) {
                        throw new IllegalArgumentException("ERROR! Cargo capacity can't negative it must be a natural number." +
                                "Check the airplane number " + sideNumber);
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("ERROR! Cargo capacity must be a natural number. " +
                            "Check the airplane number " + sideNumber);
                }


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
            throw new IllegalArgumentException("Range can't be negative");
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


    /**
     * Checks if text file exists
     *
     * @param filename file which contains a list of airplanes
     * @return Scanner
     */
    private Scanner fileInput(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL path = classLoader.getResource(filename);
        File file = null;
        if (path != null) {
            file = new File(path.getFile());
        }
        Scanner input = null;
        try {
            if (file != null) {
                input = new Scanner(file);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return input;
    }
}

package airline.parser;

import airline.model.AirplaneType;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Viktar on 04.11.2019
 */
public class Parser {

    public String parseSideNumber(String[] characteristics) {
        return characteristics[0].trim();
    }

    public String parseManufacturer(String[] characteristics) {
        return characteristics[1].trim();
    }

    public String parseModel(String[] characteristics) {
        return characteristics[2].trim();
    }

    public AirplaneType parseAirplaneType(String[] characteristics) {
        AirplaneType airplaneType;
        try {
            airplaneType = AirplaneType.valueOf(characteristics[3].trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("ERROR! Airplane " + parseSideNumber(characteristics) + " must be only Passenger or Cargo." +
                    "Check your airplanes list ", e);
        }
        return airplaneType;
    }

    public int parseCrew(String[] characteristics) {
        int crew;
        try {
            crew = Integer.parseInt(characteristics[4].trim());
            if (crew < 0) {
                throw new IllegalArgumentException("ERROR! Crew can't be less than 0 and it must be a natural number." +
                        " Check the airplane number " + parseSideNumber(characteristics));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ERROR! Crew must be a natural number. Check the airplane number "
                    + parseSideNumber(characteristics), e);
        }
        return crew;
    }

    public int parsePassengers(String[] characteristics) {
        int passengers;
        try {
            passengers = Integer.parseInt(characteristics[5].trim());
            if (passengers < 0) {
                throw new IllegalArgumentException("ERROR! Passengers can't be less than 0 and it must be a natural number." +
                        " Check the airplane number " + parseSideNumber(characteristics));
            } else if ((parseAirplaneType(characteristics) == AirplaneType.CARGO) && (passengers > 0)) {
                throw new IllegalStateException("Cargo type can't have passengers. Check the airplane number "
                        + parseSideNumber(characteristics));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ERROR! Passengers must be a natural number. Check the airplane number "
                    + parseSideNumber(characteristics), e);
        }
        return passengers;
    }

    public int parseMaxSpeed(String[] characteristics) {
        int maxSpeed;
        try {
            maxSpeed = Integer.parseInt(characteristics[6].trim());
            if (maxSpeed < 0) {
                throw new IllegalArgumentException("Error! Max speed can't be negative. " +
                        "Check the airplane number " + parseSideNumber(characteristics));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ERROR! Max speed must be a positive natural number. " +
                    "Check the airplane number " + parseSideNumber(characteristics));
        }
        return maxSpeed;
    }


    public int parseMaxAltitude(String[] characteristics) {
        int maxAltitude;
        try {
            maxAltitude = Integer.parseInt(characteristics[7].trim());
            if (maxAltitude < 0) {
                throw new IllegalArgumentException("Error! Max altitude can't be negative. " +
                        "Check the airplane number " + parseSideNumber(characteristics));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ERROR! Max altitude must be a positive natural number. " +
                    "Check the airplane number " + parseSideNumber(characteristics));
        }
        return maxAltitude;
    }

    public int parseMaxFlightRange(String[] characteristics) {
        int maxFlightRange;
        try {
            maxFlightRange = Integer.parseInt(characteristics[8].trim());
            if (maxFlightRange < 0) {
                throw new IllegalArgumentException("ERROR! Max flight range can't be negative. " +
                        "Check the airplane number " + parseSideNumber(characteristics));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ERROR! Max speed must be a positive natural number. " +
                    "Check the airplane number " + parseSideNumber(characteristics));
        }
        return maxFlightRange;
    }

    public int parseFuelSupply(String[] characteristics) {
        int fuelSupply;
        try {
            fuelSupply = Integer.parseInt(characteristics[9].trim());
            if (fuelSupply < 0) {
                throw new IllegalArgumentException("ERROR! Fuel supply range can't be negative. " +
                        "Check the airplane number " + parseSideNumber(characteristics));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ERROR! Fuel supply must be a positive natural number. " +
                    "Check the airplane number " + parseSideNumber(characteristics));
        }
        return fuelSupply;
    }


    public double parseFuelCunsumption(String[] characteristics) {
        double fuelConsumption;
        try {
            fuelConsumption = Double.parseDouble(characteristics[10].trim());
            if (fuelConsumption < 0) {
                throw new IllegalArgumentException("ERROR! Fuel consumption can't be negative. " +
                        "Check the airplane number " + parseSideNumber(characteristics));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ERROR! Fuel consumption must be a positive number. " +
                    "Check the airplane number " + parseSideNumber(characteristics));
        }
        return fuelConsumption;
    }


    public int parseCargoCapacity(String[] characteristics) {
        int cargoCapacity;
        try {
            cargoCapacity = Integer.parseInt(characteristics[11].trim());
            if ((parseAirplaneType(characteristics) == AirplaneType.PASSENGER) && (((cargoCapacity > 0) || (cargoCapacity < 0)))) {
                throw new IllegalArgumentException("Error! Passenger airplane does not carry cargo. " +
                        "Check the airplane number " + parseSideNumber(characteristics));
            } else if (cargoCapacity < 0) {
                throw new IllegalArgumentException("ERROR! Cargo capacity can't negative it must be a natural number." +
                        "Check the airplane number " + parseSideNumber(characteristics));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ERROR! Cargo capacity must be a natural number. " +
                    "Check the airplane number " + parseSideNumber(characteristics));
        }
        return cargoCapacity;
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

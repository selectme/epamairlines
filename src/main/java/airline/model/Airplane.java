package airline.model;

import java.util.Objects;

/**
 * Model object that represents an airplane.
 *
 * @author Viktar on 29.09.2019
 * @see Aircraft
 */
public abstract class Airplane extends Aircraft {

    /**
     * Side number of an airplane.
     */
    private String sideNumber;
    /**
     * Model of an airplane.
     */
    private String model;
    /**
     * An airplane type;
     */
    private AirplaneType airplaneType;
    /**
     * Quantity of crew on an airplane
     */
    private int crewQuantity;
    /**
     * Max range of flight
     */
    private int maxFlightRange;
    /**
     * Fuel supply on an airplane
     */
    private int fuelSupply;
    /**
     * Fuel consumption on an airplane
     */
    private double fuelConsumption;

    /**
     * New instance.
     *
     * @param sideNumber      side number of an airplane
     * @param manufacturer    brand of an airplane
     * @param maxSpeed        max speed of an airplane, km per hour
     * @param maxAltitude     max altitude of airplane, meters
     * @param model           model of airplane
     * @param airplaneType    {@link AirplaneType}
     * @param crewQuantity    crew quantity of an airplane
     * @param maxFlightRange  max flight distance of an airplane, km
     * @param fuelSupply      fuel supply or tank capacity of an airplane, ton
     * @param fuelConsumption fuel consumption of an airplane, kg per hour
     */
    public Airplane(String sideNumber, String manufacturer, int maxSpeed, int maxAltitude, String model,
                    AirplaneType airplaneType, int crewQuantity, int maxFlightRange, int fuelSupply, double fuelConsumption) {
        super(manufacturer, maxSpeed, maxAltitude);
        this.sideNumber = sideNumber;
        this.model = model;
        this.airplaneType = airplaneType;
        this.crewQuantity = crewQuantity;
        this.maxFlightRange = maxFlightRange;
        this.fuelSupply = fuelSupply;
        this.fuelConsumption = fuelConsumption;
    }

    public String getSideNumber() {
        return sideNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public AirplaneType getAirplaneType() {
        return airplaneType;
    }

    public int getCrewQuantity() {
        return crewQuantity;
    }

    public int getMaxFlightRange() {
        return maxFlightRange;
    }

    public int getFuelSupply() {
        return fuelSupply;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return crewQuantity == airplane.crewQuantity &&
                maxFlightRange == airplane.maxFlightRange &&
                fuelSupply == airplane.fuelSupply &&
                Double.compare(airplane.fuelConsumption, fuelConsumption) == 0 &&
                Objects.equals(sideNumber, airplane.sideNumber) &&
                Objects.equals(model, airplane.model) &&
                airplaneType == airplane.airplaneType;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = result * 31 + sideNumber.hashCode();
        result = result * 31 + model.hashCode();
        result = result * 31 + airplaneType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Side number: " + getSideNumber() + "\n" +
                "Manufacturer: " + super.getManufacturer() + "\n" +
                "Type: " + airplaneType + "\n" +
                "Model: " + model + "\n" +
                "Crew: " + crewQuantity + "\n" +
                "Max speed: " + super.getMaxSpeed() + " km/ph\n" +
                "Flight altitude: " + super.getMaxAltitude() + " km\n" +
                "Flight range: " + maxFlightRange + " km\n" +
                "Fuel supply: " + fuelSupply + " t\n" +
                "Fuel consumption: " + getFuelConsumption() + " kg/ph\n";
    }
}

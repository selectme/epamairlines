package airline.model;

/**
 * Model object that represents a passenger airplane.
 *
 * @author Viktar on 30.09.2019
 * @see Airplane
 */
public class PassengerAirplane extends Airplane {
    /**
     * Max passengers capacity on an airplane
     */
    private int maxPassengers;

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
     * @param maxPassengers   max passengers capacity of an airplane
     */
    public PassengerAirplane(String sideNumber, String manufacturer, int maxSpeed, int maxAltitude, String model,
                             AirplaneType airplaneType, int crewQuantity, int maxFlightRange, int fuelSupply,
                             double fuelConsumption, int maxPassengers) {
        super(sideNumber, manufacturer, maxSpeed, maxAltitude, model, airplaneType, crewQuantity, maxFlightRange,
                fuelSupply, fuelConsumption);
        this.maxPassengers = maxPassengers;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassengerAirplane that = (PassengerAirplane) o;
        return maxPassengers == that.maxPassengers;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Side number: " + getSideNumber() + "\n" +
                "Manufacturer: " + super.getManufacturer() + "\n" +
                "Type: " + super.getAirplaneType() + "\n" +
                "Model: " + super.getModel() + "\n" +
                "Crew: " + super.getCrewQuantity() + "\n" +
                "Passengers: " + getMaxPassengers() + "\n" +
                "Max speed: " + super.getMaxSpeed() + " km/ph\n" +
                "Flight altitude: " + super.getMaxAltitude() + " km\n" +
                "Flight range: " + super.getMaxFlightRange() + " km\n" +
                "Fuel supply: " + super.getFuelSupply() + " t\n" +
                "Fuel consumption: " + super.getFuelConsumption() + " kg/ph\n";
    }
}

package taskslist4.maintask.airline.model;

/**
 * Model object that represents a cargo airplane
 *
 * @author Viktar on 30.09.2019
 * @see Airplane
 */
public class CargoAirplane extends Airplane {
    /**
     * Cargo capacity of an airplane
     */
    private int cargoCapacity;

    /**
     * New instance.
     *
     * @param sideNumber        side number of an airplane
     * @param manufacturer      brand of an airplane
     * @param maxSpeed          max speed of an airplane, km per hour
     * @param maxAltitude       max altitude of airplane, meters
     * @param model             model of airplane
     * @param airplaneType      {@link AirplaneType}
     * @param crewQuantity      crew quantity of an airplane
     * @param maxFlightRange    max flight distance of an airplane, km
     * @param fuelSupply        fuel supply or tank capacity of an airplane, ton
     * @param fuelConsumption   fuel consumption of an airplane, kg per hour
     * @param carryingCapacity1 carrying capacity of an airplane
     */
    public CargoAirplane(String sideNumber, String manufacturer, int maxSpeed, int maxAltitude, String model,
                         AirplaneType airplaneType, int crewQuantity, int maxFlightRange, int fuelSupply,
                         double fuelConsumption, int carryingCapacity1) {
        super(sideNumber, manufacturer, maxSpeed, maxAltitude, model, airplaneType, crewQuantity, maxFlightRange,
                fuelSupply, fuelConsumption);
        this.cargoCapacity = carryingCapacity1;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CargoAirplane that = (CargoAirplane) o;
        return cargoCapacity == that.cargoCapacity;
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
                "Max speed: " + super.getMaxSpeed() + " km/ph\n" +
                "Flight altitude: " + super.getMaxAltitude() + " km\n" +
                "Flight range: " + super.getMaxFlightRange() + " km\n" +
                "Carrying capacity: " + getCargoCapacity() + " t\n" +
                "Fuel supply: " + super.getFuelSupply() + " t\n" +
                "Fuel consumption: " + super.getFuelConsumption() + " t/ph\n";
    }
}

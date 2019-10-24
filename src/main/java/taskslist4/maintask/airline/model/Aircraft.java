package taskslist4.maintask.airline.model;

/**
 * Model object that represents an aircraft
 *
 * @author Viktar on 30.09.2019
 */
public abstract class Aircraft {
    /**
     * Manufacturer of an aircraft.
     */
    private String manufacturer;
    /**
     * Max speed of an aircraft.
     */
    private int maxSpeed;
    /**
     * Max altitude of an aircraft
     */
    private int maxAltitude;

    /**
     * New instance.
     *
     * @param manufacturer Manufacturer of an aircraft
     * @param maxSpeed     Max speed of an aircraft
     * @param maxAltitude  Max altitude of an aircraft
     */
    public Aircraft(String manufacturer, int maxSpeed, int maxAltitude) {
        this.manufacturer = manufacturer;
        this.maxSpeed = maxSpeed;
        this.maxAltitude = maxAltitude;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getMaxAltitude() {
        return maxAltitude;
    }

    public void setMaxAltitude(int maxAltitude) {
        this.maxAltitude = maxAltitude;
    }

    @Override
    public String toString() {
        return "MANUFACTURER: " + manufacturer + "\n" +
                "Max speed: " + maxSpeed + " km/ph\n" +
                "Flight altitude: " + maxAltitude + " km\n";
    }
}

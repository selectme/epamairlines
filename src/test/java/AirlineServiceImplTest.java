import org.junit.Before;
import org.junit.Test;
import airline.comparator.AirplaneBrandComparator;
import airline.comparator.AirplaneCrewCapacityComparator;
import airline.comparator.AirplaneFuelConsumptionComparator;
import airline.comparator.AirplanePassengersCapacityComparator;
import airline.model.Airplane;
import airline.model.AirplaneType;
import airline.model.CargoAirplane;
import airline.model.PassengerAirplane;
import airline.service.AirlineService;
import airline.service.impl.AirlineServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Viktar on 07.10.2019
 */
public class AirlineServiceImplTest {
    private static final String RIGHT_DATA = "airplanesRightData.txt";
    private static final String WRONG_DATA = "airplanesWrongData.txt";
    private AirlineService airlineService;
    private List<Airplane> airplanesRightData;
    private List<Airplane> airplanesWrongData;
    private List<Airplane> airplanesTest;

    @Before
    public void setUp() throws Exception {
        airlineService = new AirlineServiceImpl();
        airplanesRightData = airlineService.createAirplanesList(RIGHT_DATA);

        airplanesTest = new ArrayList<>();
        Airplane cargoAirplane1 = new CargoAirplane("GH12", "Sukhoi", 850,
                10000, "C4312", AirplaneType.CARGO, 2, 5000, 245,
                170, 220);
        Airplane passengerAirplane1 = new PassengerAirplane("B423", "Airbus", 900,
                10000, "A320", AirplaneType.PASSENGER, 4, 6000, 195,
                120, 265);

        Airplane passengerAirplane2 = new PassengerAirplane("S513", "Airbus", 900,
                10500, "A370", AirplaneType.PASSENGER, 3, 5000, 200,
                160, 256);
        Airplane cargoAirplane2 = new CargoAirplane("GH15", "Bombardier", 800,
                11000, "B234", AirplaneType.CARGO, 4, 5000, 200,
                180, 220);
        Airplane passengerAirplane3 = new PassengerAirplane("Y1456", "Boeing", 950,
                8000, "737", AirplaneType.PASSENGER, 5, 6500, 180,
                130, 277);
        airplanesTest.add(cargoAirplane1);
        airplanesTest.add(passengerAirplane1);
        airplanesTest.add(passengerAirplane2);
        airplanesTest.add(cargoAirplane2);
        airplanesTest.add(passengerAirplane3);

    }

    /**
     * Tests for the right airplanes quantity
     */
    @Test
    public void testGetAirplanesQuantity() {
        int airplanes = airlineService.getAirplanesQuantity(airplanesRightData);
        assertEquals(5, airplanes);
    }

    /**
     * Tests creating airplanes list from text file
     */
    @Test
    public void testCreateAirplanesList_success() {
        List<Airplane> airplanes = airplanesRightData;
        assertEquals(airplanes.get(0), airplanesTest.get(0));
        assertEquals(airplanes.get(1), airplanesTest.get(1));
        assertEquals(airplanes.get(2), airplanesTest.get(2));
        assertEquals(airplanes.get(3), airplanesTest.get(3));
        assertEquals(airplanes.get(4), airplanesTest.get(4));
    }

    /**
     * Tests exception throwing if received incorrect data
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateAirplanesList_wrongInputData() {
        airplanesWrongData = airlineService.createAirplanesList(WRONG_DATA);
        List<Airplane> airplanes = airplanesWrongData;
        assertEquals(airplanes.get(0), airplanesTest.get(0));
        assertEquals(airplanes.get(1), airplanesTest.get(1));
        assertEquals(airplanes.get(2), airplanesTest.get(2));
        assertEquals(airplanes.get(3), airplanesTest.get(3));
        assertEquals(airplanes.get(4), airplanesTest.get(4));
    }

    /**
     * Tests for the right total airplanes crew capacity
     */
    @Test
    public void testFindTotalCrewCapacity() {
        int crews = airlineService.findTotalCrewCapacity(airplanesRightData);
        assertEquals(18, crews);
    }

    /**
     * Tests for the right total airplanes capacity
     */
    @Test
    public void testFindTotalAirplanesCapacity() {
        int total = airlineService.findTotalAirplanesCapacity(airplanesRightData);
        assertEquals(816, total);
    }

    /**
     * Tests for the right total airplanes passenger capacity
     */
    @Test
    public void testFindTotalPassengersCapacity() {
        int total = airlineService.findTotalPassengersCapacity(airplanesRightData);
        assertEquals(798, total);
    }

    /**
     * Tests for the right total airplanes carrying capacity
     */
    @Test
    public void testFindTotalAirplanesCarryingCapacity() {
        int total = airlineService.findTotalAirplanesCarryingCapacity(airplanesRightData);
        assertEquals(440, total);
    }

    /**
     * Tests finding plane by suitable fuel consumption range
     */
    @Test
    public void findPlaneByFuelConsumptionRange() {
        Airplane airplane = airlineService.findPlaneByFuelConsumptionRange(airplanesRightData, 100, 200);
        assertEquals(airplane.getFuelConsumption(), 120.0, 0.01);
    }

    /**
     * Compares airplanes by brand alphabetically
     */
    @Test
    public void testAirplaneBrandComparator() {
        List<Airplane> sortedAirplanes = new ArrayList<>();
        Airplane cargoAirplane1 = new CargoAirplane("GH12", "Sukhoi", 850,
                10000, "C4312", AirplaneType.CARGO, 2, 5000, 245,
                170, 220);
        Airplane passengerAirplane1 = new PassengerAirplane("B423", "Airbus", 900,
                10000, "A320", AirplaneType.PASSENGER, 4, 6000, 195,
                120, 265);
        Airplane passengerAirplane2 = new PassengerAirplane("S513", "Airbus", 900,
                10500, "A370", AirplaneType.PASSENGER, 3, 5000, 200,
                160, 256);
        Airplane cargoAirplane2 = new CargoAirplane("GH15", "Bombardier", 800,
                11000, "B234", AirplaneType.CARGO, 4, 5000, 200,
                180, 220);
        Airplane passengerAirplane3 = new PassengerAirplane("Y1456", "Boeing", 950,
                8000, "737", AirplaneType.PASSENGER, 5, 6500, 180,
                130, 277);

        sortedAirplanes.add(passengerAirplane1);
        sortedAirplanes.add(passengerAirplane2);
        sortedAirplanes.add(passengerAirplane3);
        sortedAirplanes.add(cargoAirplane2);
        sortedAirplanes.add(cargoAirplane1);

        airplanesRightData.sort(new AirplaneBrandComparator());

        assertEquals(sortedAirplanes, airplanesRightData);
    }

    /**
     * Compares airplanes by crew capacity from larger to lower capacity
     */
    @Test
    public void testAirplaneCrewCapacityComparator() {
        List<Airplane> sortedAirplanes = new ArrayList<>();
        Airplane cargoAirplane1 = new CargoAirplane("GH12", "Sukhoi", 850,
                10000, "C4312", AirplaneType.CARGO, 2, 5000, 245,
                170, 220);
        Airplane passengerAirplane1 = new PassengerAirplane("B423", "Airbus", 900,
                10000, "A320", AirplaneType.PASSENGER, 4, 6000, 195,
                120, 265);
        Airplane passengerAirplane2 = new PassengerAirplane("S513", "Airbus", 900,
                10500, "A370", AirplaneType.PASSENGER, 3, 5000, 200,
                160, 256);
        Airplane cargoAirplane2 = new CargoAirplane("GH15", "Bombardier", 800,
                11000, "B234", AirplaneType.CARGO, 4, 5000, 200,
                180, 220);
        Airplane passengerAirplane3 = new PassengerAirplane("Y1456", "Boeing", 950,
                8000, "737", AirplaneType.PASSENGER, 5, 6500, 180,
                130, 277);

        sortedAirplanes.add(passengerAirplane3);
        sortedAirplanes.add(passengerAirplane1);
        sortedAirplanes.add(cargoAirplane2);
        sortedAirplanes.add(passengerAirplane2);
        sortedAirplanes.add(cargoAirplane1);

        airplanesRightData.sort(new AirplaneCrewCapacityComparator());

        assertEquals(sortedAirplanes, airplanesRightData);
    }

    /**
     * Compares airplanes by passenger capacity from larger to smaller
     */
    @Test
    public void testAirplanePassengerCapacityComparator() {
        List<Airplane> sortedAirplanes = new ArrayList<>();
        Airplane cargoAirplane1 = new CargoAirplane("GH12", "Sukhoi", 850,
                10000, "C4312", AirplaneType.CARGO, 2, 5000, 245,
                170, 220);
        Airplane passengerAirplane1 = new PassengerAirplane("B423", "Airbus", 900,
                10000, "A320", AirplaneType.PASSENGER, 4, 6000, 195,
                120, 265);
        Airplane passengerAirplane2 = new PassengerAirplane("S513", "Airbus", 900,
                10500, "A370", AirplaneType.PASSENGER, 3, 5000, 200,
                160, 256);
        Airplane cargoAirplane2 = new CargoAirplane("GH15", "Bombardier", 800,
                11000, "B234", AirplaneType.CARGO, 4, 5000, 200,
                180, 220);
        Airplane passengerAirplane3 = new PassengerAirplane("Y1456", "Boeing", 950,
                8000, "737", AirplaneType.PASSENGER, 5, 6500, 180,
                130, 277);

        sortedAirplanes.add(passengerAirplane3);
        sortedAirplanes.add(passengerAirplane1);
        sortedAirplanes.add(passengerAirplane2);
        sortedAirplanes.add(cargoAirplane1);
        sortedAirplanes.add(cargoAirplane2);

        airplanesRightData.sort(new AirplanePassengersCapacityComparator());

        assertEquals(sortedAirplanes, airplanesRightData);
    }

    /**
     * Compares airplanes by fuel consumption from smaller to larger
     */
    @Test
    public void testAirplaneFuelConsumptionComparator() {
        List<Airplane> sortedAirplanes = new ArrayList<>();
        Airplane cargoAirplane1 = new CargoAirplane("GH12", "Sukhoi", 850,
                10000, "C4312", AirplaneType.CARGO, 2, 5000, 245,
                170, 220);
        Airplane passengerAirplane1 = new PassengerAirplane("B423", "Airbus", 900,
                10000, "A320", AirplaneType.PASSENGER, 4, 6000, 195,
                120, 265);
        Airplane passengerAirplane2 = new PassengerAirplane("S513", "Airbus", 900,
                10500, "A370", AirplaneType.PASSENGER, 3, 5000, 200,
                160, 256);
        Airplane cargoAirplane2 = new CargoAirplane("GH15", "Bombardier", 800,
                11000, "B234", AirplaneType.CARGO, 4, 5000, 200,
                180, 220);
        Airplane passengerAirplane3 = new PassengerAirplane("Y1456", "Boeing", 950,
                8000, "737", AirplaneType.PASSENGER, 5, 6500, 180,
                130, 277);

        sortedAirplanes.add(passengerAirplane1);
        sortedAirplanes.add(passengerAirplane3);
        sortedAirplanes.add(passengerAirplane2);
        sortedAirplanes.add(cargoAirplane1);
        sortedAirplanes.add(cargoAirplane2);

        airplanesRightData.sort(new AirplaneFuelConsumptionComparator());

        assertEquals(sortedAirplanes, airplanesRightData);
    }

}
/**
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import asgn2Aircraft.AircraftException;
import asgn2Passengers.PassengerException;

/**
 * @author michaelleontieff
 *
 */
public class AircraftTests {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}
	
	// CONSTRUCTOR TESTS
	
		// constructor exception tests
	
	@Test
	public void ValidParametersExceptionTest() {
		fail("Not yet implemented");
	}

	@Test (expected = AircraftException.class)  
	public void IsNullFlightCodeExceptionTest() {
		fail("Not yet implemented");
	}
	
	@Test (expected = AircraftException.class)
	public void DepartTimeLessThanZeroExceptionTest() {
		fail("Not yet implemented");
	}
	
	@Test (expected = AircraftException.class)
	public void DepartTimeZeroExceptionTest() {
		fail("Not yet implemented");
	}
	
	@Test (expected = AircraftException.class)
	public void FirstLessThanZeroExceptionTest() {
		fail("Not yet implemented");
	}
	
	@Test (expected = AircraftException.class)
	public void BusinessLessThanZeroExceptionTest() {
		fail("Not yet implemented");
	}
	
	@Test (expected = AircraftException.class)
	public void PremiumLessThanZeroExceptionTest() {
		fail("Not yet implemented");
	}
	
	@Test (expected = AircraftException.class)
	public void EconomyLessThanZeroExceptionTest() {
		fail("Not yet implemented");
	}
	
	// CANCEL BOOKING TESTS
	
	@Test
	public void CancelBookingTest() {
		fail("Not yet implemented");
	}
	
	@Test (expected = PassengerException.class)
	public void PassengerNotConfirmedExceptionTest() {
		fail("Not yet implemented");
	}
	
	@Test (expected = PassengerException.class)
	public void InvalidCancelTimeExceptionTest() {
		fail("Not yet implemented");
	}
	
	@Test (expected = AircraftException.class)
	public void PassengerNotInSeatingExceptionTest() {
		fail("Not yet implemented");
	}
	
	// CONFIRM BOOKING TESTS
	
	@Test 
	public void ConfirmBookingTest() {
		fail("Not yet implemented");
	}
	
	@Test (expected = PassengerException.class)
	public void IncorrectPassengerStateExceptionTest() {
		fail("Not yet implemented");
	}
	
	@Test (expected = PassengerException.class)
	public void InvalidConfirmTimeExceptionTest() {
		fail("Not yet implemented");
	}
	
	@Test (expected = PassengerException.class)
	public void InvalidDepartTimeExceptionTest() {
		fail("Not yet implemented");
	}
	
	@Test (expected = AircraftException.class)
	public void NoSeatsAvailableExceptionTest() {
		fail("Not yet implemented");
	}
	
	// FLIGHT EMPTY TESTS
	
	@Test
	public void FlightEmptyEmptyTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void FlightEmptyNotEmptyTest() {
		fail("Not yet implemented");
	}
	
	// FLIGHT FULL TESTS
	
	@Test
	public void FlightFullFullTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void FlightFullNotFullTest() {
		fail("Not yet implemented");
	}
	
	// FLY PASSENGERS TESTS
	
	@Test
	public void FlyPassengersCorrectStateChangeTest() {
		fail("Not yet implemented");
	}
	
	@Test (expected = PassengerException.class)
	public void FlyPassengersIncorrectStateExceptionTest() {
		fail("Not yet implemented");
	}
	
	// GET BOOKINGS TESTS
	
	@Test 
	public void GetBookingsTest() {
		fail("Not yet implemented");
	}
	
	// GET NUM BUSINESS TESTS
	
	@Test
	public void GetNumBusinessClassPassengersTest() {
		fail("Not yet implemented");
	}
	
	// GET NUM ECONOMY TESTS
	
	@Test
	public void GetNumEcomonyClassPassengersTest() {
		fail("Not yet implemented");
	}
	
	// GET NUM FIRST TESTS
	
	@Test
	public void GetNumFirstClassPassengersTest() {
		fail("Not yet implemented");
	}
	
	// GET NUM PASSENGERS TESTS
	
	@Test
	public void GetNumPassengersTest() {
		fail("Not yet implemented");
	}
	
	// GET NUM PREMIUM TESTS
	
	@Test
	public void GetNumPremiumClassPassengersTest() {
		fail("Not yet implemented");
	}
	
	// GET PASSENGERS TESTS
	
	@Test
	public void GetPassengersTest() {
		fail("Not yet implemented");
	}
	
	// HAS PASSENGER TESTS
	
	@Test
	public void HasPassengerTrueTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void HasPassengerFalseTest() {
		fail("Not yet implemented");
	}
	
	// SEATS AVAILABLE TESTS
	
	@Test
	public void HasSeatsAvailableTrueTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void HasSeatsAvailableFalseTest() {
		fail("Not yet implemented");
	}
	
	// UPGRADE BOOKING TESTS
	
	@Test (expected = PassengerException.class)
	public void UpgradeBookingsTest() {
		fail("Not yet implemented");
	}
	
	@Test (expected = PassengerException.class)
	public void UpgradeBookingIncorrectPassengerStateExceptionTest() {
		fail("Not yet implemented");
	}
	
	
	

}

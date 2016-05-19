/**
 * 
 * This file is a test case which forms part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn2Tests;

 
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import asgn2Passengers.Business;
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;

/**
 * This class represents a JUnit test class for the Business class as part of the
 * AircraftSimulator Project.
 * 
 * @author Vincenzo Iandolo - n9172912
 * @version 1.0
 *
 */
public class BusinessTests {
	
	// Declare constants
	private static final int TEST_BOOKING_TIME = 5;
	private static final int TEST_DEPARTURE_TIME = 20;

	// Declare test objects 
	private Business myPassenger;
	private Business testPassenger;
	private Passenger upgradedPassenger;
	
	/**
	 * @throws PassengerException
	 */
	@Before
	public void setUp() throws PassengerException {
		// Creates a test passenger and sets parameters
		myPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPARTURE_TIME);
	}
	
	// CONSTRUCTOR TESTS

	/**
	 * Test method for {@link asgn2Passengers.Business#Business()}.
	 * @throws PassengerException
	 */
	@Test (expected = PassengerException.class)
	public void testBusinessBookingTimeLessThanZeroBoundaryCase() throws PassengerException {
		testPassenger = new Business(-1, TEST_DEPARTURE_TIME);
	}
	
	@Test (expected = PassengerException.class)
	public void testBusinessBookingTimeLessThanZero() throws PassengerException {
		testPassenger = new Business(-5, TEST_DEPARTURE_TIME);
	}
	
	@Test (expected = PassengerException.class)
	public void testBusinessDepartureTimeEqualToZero() throws PassengerException {
		testPassenger = new Business(TEST_BOOKING_TIME, 0);
	}
	
	@Test (expected = PassengerException.class)
	public void testBusinessDepartureTimeTimeLessThanZeroBoundaryCase() throws PassengerException {
		testPassenger = new Business(TEST_BOOKING_TIME, -1);
	}
	
	@Test (expected = PassengerException.class)
	public void testFirstDepartureTimeLessThanZero() throws PassengerException {
		testPassenger = new Business(TEST_BOOKING_TIME, -10);
	}
	
	@Test (expected = PassengerException.class)
	public void testBusinessDepartureTimeTimeLessThanBookingTime() throws PassengerException {
		testPassenger = new Business(TEST_BOOKING_TIME, 2);
	}
	
	@Test (expected = PassengerException.class)
	public void testFirstBookingDepartureTimeLessThanBookingTimeLessThanZero() throws PassengerException {
		testPassenger = new Business(TEST_BOOKING_TIME, -1);
	}
	
	@Test (expected = PassengerException.class)
	public void testFirstBookingDepartureTimeLessThanBookingTimeZeroDepartureTime() throws PassengerException {
		testPassenger = new Business(TEST_BOOKING_TIME, 0);
	}
	
	// NO SEATS MESSAGE TESTS

	/**
	 * Test method for {@link asgn2Passengers.Business#noSeatsMsg()}.
	 */
	@Test
	public void testNoSeatsMsg() {
		assertEquals("No seats available in Business", myPassenger.noSeatsMsg());
	}

	/**
	 * Test method for {@link asgn2Passengers.Business#upgrade()}.
	 */
	@Test
	public void testUpgrade() {
		fail("Not yet implemented");
	}

}

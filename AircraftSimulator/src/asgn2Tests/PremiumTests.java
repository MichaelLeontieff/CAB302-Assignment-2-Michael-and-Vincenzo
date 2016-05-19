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
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;
import asgn2Passengers.Premium;

/**
 * This class represents a JUnit test class for the Premium class as part of the
 * AircraftSimulator Project.
 * 
 * @author Vincenzo Iandolo - n9172912
 * @version 1.0
 *
 */
public class PremiumTests {

	// Declare constants
	private static final int TEST_BOOKING_TIME = 2;
	private static final int TEST_DEPARTURE_TIME = 15;
	private static final int TEST_CONFIRMATION_TIME = 8;

	// Declare test objects 
	private Premium myPassenger;
	private Premium testPassenger;
	private Passenger upgradedPassenger;
	
	/**
	 * @throws PassengerException
	 */
	@Before
	public void setUp() throws PassengerException {
		// Creates a test passenger and sets parameters
		myPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPARTURE_TIME);
	}
	
	// CONSTRUCTOR TESTS
	


	// NO SEATS MESSAGE TESTS

	/**
	 * Test method for {@link asgn2Passengers.Premium#noSeatsMsg()}.
	 */
	@Test
	public void testNoSeatsMsg() {
		assertEquals("No seats available in Premium", myPassenger.noSeatsMsg());
	}

	/**
	 * Test method for {@link asgn2Passengers.Premium#upgrade()}.
	 */
	@Test
	public void testUpgrade() {
		fail("Not yet implemented");
	}

}

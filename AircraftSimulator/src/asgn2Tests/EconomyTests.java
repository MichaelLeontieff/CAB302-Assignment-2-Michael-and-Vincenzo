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
import asgn2Passengers.Economy;
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;

/**
 * This class represents a JUnit test class for the Economy class as part of the
 * AircraftSimulator Project.
 * 
 * @author Vincenzo Iandolo - n9172912
 * @version 1.0
 *
 */
public class EconomyTests {

	// Declare constants
	private static final int TEST_BOOKING_TIME = 5;
	private static final int TEST_DEPARTURE_TIME = 20;
	private static final int TEST_CONFIRMATION_TIME = 10;

	// Declare test objects 
	private Economy myPassenger;
	private Economy testPassenger;
	private Passenger upgradedPassenger;
		
	/**
	 * @throws PassengerException
	 */
	@Before
	public void setUp() throws PassengerException {
		// Creates a test passenger and sets parameters
		myPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPARTURE_TIME);
	}

	// CONSTRUCTOR TESTS
	
	/**
	 * Test method for {@link asgn2Passengers.Economy#Economy(int, int)}.
	 */
	@Test
	public void testEconomy() {
		fail("Not yet implemented");
	}

	// NO SEATS MESSAGE TESTS
	
	/**
	 * Test method for {@link asgn2Passengers.Economy#noSeatsMsg()}.
	 */
	@Test
	public void testNoSeatsMsg() {
		assertEquals("No seats available in Economy", myPassenger.noSeatsMsg());
	}

	/**
	 * Test method for {@link asgn2Passengers.Economy#upgrade()}.
	 */
	@Test
	public void testUpgrade() {
		fail("Not yet implemented");
	}

}

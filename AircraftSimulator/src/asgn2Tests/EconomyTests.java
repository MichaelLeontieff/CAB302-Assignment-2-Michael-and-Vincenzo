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
import asgn2Passengers.Premium;

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
	private static final int TEST_QUEUE_TIME = 6;

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
	 * @throws PassengerException 
	 */
	@Test (expected = PassengerException.class)
	public void testEconomyBookingTimeLessThanZeroBoundaryCase() throws PassengerException {
		testPassenger = new Economy(-1, TEST_DEPARTURE_TIME);
	}
	
	@Test (expected = PassengerException.class)
	public void testEconomyBookingTimeLessThanZero() throws PassengerException {
		testPassenger = new Economy(-7, TEST_DEPARTURE_TIME);
	}
	
	@Test (expected = PassengerException.class)
	public void testEconomyDepatureTimeEqualToZero() throws PassengerException {
		testPassenger = new Economy(TEST_BOOKING_TIME, 0);
	}
	
	@Test (expected = PassengerException.class)
	public void testEconomyDepartureTimeLessThanZeroBoundaryCase() throws PassengerException {
		testPassenger = new Economy(TEST_BOOKING_TIME, -1);
	}
	
	@Test (expected = PassengerException.class)
	public void testEconomyDepartureTimeLessThanZero() throws PassengerException {
		testPassenger = new Economy(TEST_BOOKING_TIME, -5);
	}
	
	@Test (expected = PassengerException.class)
	public void testEconomyDepartureTimeLessThanBookingTime() throws PassengerException {
		testPassenger = new Economy(TEST_BOOKING_TIME, 1);
	}
	
	@Test (expected = PassengerException.class)
	public void testEconomyDepartureTimeLessThanBookingTimeLessThanZero() throws PassengerException {
		testPassenger = new Economy(TEST_BOOKING_TIME, -1);
	}
	
	@Test (expected = PassengerException.class)
	public void testEconomyDepartureTimeLessThanBookingTimeZeroDepartureTime() throws PassengerException {
		testPassenger = new Economy(TEST_BOOKING_TIME, 0);
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
	public void testUpgradeBookingTime() throws PassengerException {
		// Set passenger to a confirmed state as upgrade will only get called on a passenger in a seat
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if matching Booking Times
		assertTrue(myPassenger.getBookingTime() == upgradedPassenger.getBookingTime());
	}
	
	@Test
	public void testUpgradeDepartureTime() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if matching Departure Times
		assertTrue(myPassenger.getDepartureTime() == upgradedPassenger.getDepartureTime());
	}
	
	@Test
	public void testUpgradeConfirmationTime() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if matching Confirmation Times
		assertTrue(myPassenger.getConfirmationTime() == upgradedPassenger.getConfirmationTime());
	}
	
	@Test
	public void testUpgradeEnterQueueTime() throws PassengerException {
		// Set passenger to a queued state
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if matching enter queue times
		assertTrue(myPassenger.getEnterQueueTime() == upgradedPassenger.getEnterQueueTime());
	}
	
	@Test
	public void testUpgradeEnterQueueTimeWhenNeverQueued() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if matching enter queue times
		assertTrue(myPassenger.getEnterQueueTime() == upgradedPassenger.getEnterQueueTime());
	}
	
	@Test
	public void testUpgradeExitQueueTimeAfterConfirmingSeat() throws PassengerException {
		// Set passenger to a queued state
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if matching exit queue times
		assertTrue(myPassenger.getExitQueueTime() == upgradedPassenger.getExitQueueTime());
	}

	@Test
	public void testUpgradeExitQueueWhenNeverQueued() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if matching exit queue times
		assertTrue(myPassenger.getExitQueueTime() == upgradedPassenger.getExitQueueTime());
	}
	
	public void testUpgradeWasConfirmed() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if both was confirmed
		assertTrue(myPassenger.wasConfirmed() == upgradedPassenger.wasConfirmed());
	}
	
	public void testUpgradeWasQueued() throws PassengerException {
		// Set passenger to a queued state
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if both was queued
		assertTrue(myPassenger.wasQueued() == upgradedPassenger.wasQueued());
	}

	public void testUpgradeWasQueuedAfterNeverBeingQueued() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if both weren't queued
		assertTrue(myPassenger.wasQueued() == upgradedPassenger.wasQueued());
	}
		
	@Test
	public void testUpgradeBothConfirmed() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if both passenger instances are in confirmed states
		assertTrue(myPassenger.isConfirmed() == upgradedPassenger.isConfirmed());
	}
	
	@Test
	public void testUpgradeBothNotQueued() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if both passengers are not in queued states
		assertTrue(myPassenger.isQueued() == upgradedPassenger.isQueued());
	}
	
	@Test
	public void testUpgradeBothNotNew() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if both passengers are not in new states
		assertTrue(myPassenger.isNew() == upgradedPassenger.isNew());
	}
	
	@Test
	public void testUpgradeBothNotRefused() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if both passengers are not in refused states
		assertTrue(myPassenger.isRefused() == upgradedPassenger.isRefused());
	}
	
	@Test
	public void testUpgradeBothNotFlown() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if both passengers are not in flown states
		assertTrue(myPassenger.isFlown() == upgradedPassenger.isFlown());
	}
	
	@Test
	public void testUpgradeBusinessClassToFirstClass() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if upgraded passenger changes to an instance of First
		assertTrue(upgradedPassenger instanceof Premium);
	}
	
	@Test
	public void testUpgradeBusinessClassUnchangedForOriginalPassenger() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if original passenger remains an instance of Business
		assertTrue(myPassenger instanceof Economy);
	}
	
	/*@Test
	public void testUpgradePassID() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if the passID's do not match
		assertTrue(myPassenger.getPassID() != upgradedPassenger.getPassID());
	} //if a first is upgraded, does their passID change? */
	
	/*	@Test
	public void testUpgradeBothNotQueued() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if both passenger instances are in confirmed states
		assertTrue(myPassenger.isQueued() == upgradedPassenger.isQueued());
		assertEquals(false, myPassenger.isQueued() && upgradedPassenger.isQueued())
	} */	

}

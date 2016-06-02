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
import asgn2Passengers.*;

/**
 * This class represents a JUnit test class for the Business class as part of the
 * AircraftSimulator Project.
 * 
 * @author Vincenzo Iandolo - n9172912
 * @version 1.0
 *
 */
public class BusinessTests {
	
	/*
	 *  Declare constants
	 */
	private static final int TEST_BOOKING_TIME = 5;
	private static final int TEST_DEPARTURE_TIME = 20;
	private static final int TEST_CONFIRMATION_TIME = 10;
	private static final int TEST_QUEUE_TIME = 7;
	private static final int NEGATIVE_BOUNDARY = -1;
	private static final int NEGATIVE_PARAMETER = -5;
	private static final int ZERO_BOUNDARY_CASE = 0;
	private static final int LESSER_DEPARTURE_TIME = 3;

	/*
	 *  Declare test objects 
	 */
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
	@Test
	public void testBusinessValidParametersExceptionTest() throws PassengerException {
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPARTURE_TIME);
		assertNotNull(testPassenger);
	}
	
	@Test (expected = PassengerException.class)
	public void testBusinessBookingTimeLessThanZeroBoundaryCase() throws PassengerException {
		testPassenger = new Business(NEGATIVE_BOUNDARY, TEST_DEPARTURE_TIME);
	}
	
	@Test (expected = PassengerException.class)
	public void testBusinessBookingTimeLessThanZero() throws PassengerException {
		testPassenger = new Business(NEGATIVE_PARAMETER, TEST_DEPARTURE_TIME);
	}
	
	@Test (expected = PassengerException.class)
	public void testBusinessDepartureTimeEqualToZero() throws PassengerException {
		testPassenger = new Business(TEST_BOOKING_TIME, ZERO_BOUNDARY_CASE);
	}
	
	@Test (expected = PassengerException.class)
	public void testBusinessDepartureTimeLessThanZeroBoundaryCase() throws PassengerException {
		testPassenger = new Business(TEST_BOOKING_TIME, NEGATIVE_BOUNDARY);
	}
	
	@Test (expected = PassengerException.class)
	public void testBusinessDepartureTimeLessThanZero() throws PassengerException {
		testPassenger = new Business(TEST_BOOKING_TIME, NEGATIVE_PARAMETER);
	}
	
	@Test (expected = PassengerException.class)
	public void testBusinessDepartureTimeLessThanBookingTime() throws PassengerException {
		testPassenger = new Business(TEST_BOOKING_TIME, LESSER_DEPARTURE_TIME);
	}
	
	@Test (expected = PassengerException.class)
	public void testBusinessDepartureTimeLessThanBookingTimeLessThanZero() throws PassengerException {
		testPassenger = new Business(TEST_BOOKING_TIME, NEGATIVE_BOUNDARY);
	}
	
	@Test (expected = PassengerException.class)
	public void testBusinessDepartureTimeLessThanBookingTimeZeroDepartureTime() throws PassengerException {
		testPassenger = new Business(TEST_BOOKING_TIME, ZERO_BOUNDARY_CASE);
	}
	
	// NO SEATS MESSAGE TESTS

	/**
	 * Test method for {@link asgn2Passengers.Business#noSeatsMsg()}.
	 */
	@Test
	public void testNoSeatsMsg() {
		assertEquals("No seats available in Business", myPassenger.noSeatsMsg());
	}
	
	// UPGRADE TESTS

	/**
	 * Test method for {@link asgn2Passengers.Business#upgrade()}.
	 * @throws PassengerException 
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
		assertTrue(upgradedPassenger instanceof First);
	}
	
	@Test
	public void testUpgradeBusinessClassUnchangedForOriginalPassenger() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if original passenger remains an instance of Business
		assertTrue(myPassenger instanceof Business);
	}
	
	@Test
	public void testUpgradePassID() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Create new passenger instance which is an upgrade of the original passenger
		upgradedPassenger = myPassenger.upgrade();
		// Check if the passID's do not match
		assertFalse(myPassenger.getPassID().equals(upgradedPassenger.getPassID()));
	} 	
}

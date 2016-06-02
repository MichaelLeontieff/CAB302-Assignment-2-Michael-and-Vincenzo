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
 * This class represents a JUnit test class for the First class as part of the
 * AircraftSimulator Project.
 * 
 * @author Vincenzo Iandolo - n9172912
 * @version 1.0
 *
 */
public class FirstTests {

	/*
	 *  Declare constants
	 */
	private static final int TEST_BOOKING_TIME = 1;
	private static final int TEST_DEPARTURE_TIME = 10;
	private static final int TEST_CONFIRMATION_TIME = 5;
	private static final int TEST_CANCELLATION_TIME = 8;
	private static final int TEST_QUEUE_TIME = 4;
	private static final int TEST_REFUSAL_TIME = 7;
	private static final int TEST_GREATER_QUEUE_TIME = 8;
	private static final int ALTERNATE_DEPARTURE_TIME = 20;
	private static final int ALTERNATE_REFUSAL_TIME = 8;
	private static final int NEGATIVE_BOUNDARY = -1;
	private static final int NEGATIVE_PARAMETER = -5;
	private static final int GREATER_BOOKING_TIME = 5;
	private static final int GREATER_QUEUE_TIME = 12;
	private static final int LESSER_DEPARTURE_TIME = 3;
	private static final int ZERO_BOUNDARY_CASE = 0;
	private static final int TEST_CANCELLATION_TIME_BOUNDARY = 11;
	private static final int TEST_REFUSAL_TIME_BOUNDARY = 11;

	/*
	 *  Declare test objects
	 */
	private First myPassenger;
	private First testPassenger;
	private Passenger upgradedPassenger;

	/**
	 * @throws PassengerException
	 */
	@Before
	public void setUp() throws PassengerException {
		myPassenger = new First(TEST_BOOKING_TIME, TEST_DEPARTURE_TIME);
	}

	// CONSTRUCTOR TESTS

	/**
	 * Test method for {@link asgn2Passengers.First#First(int, int)}.
	 * 
	 * @throws PassengerException
	 */
	@Test
	public void testFirstValidParametersExceptionTest() throws PassengerException {
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPARTURE_TIME);
		assertNotNull(testPassenger);
	}
	
	@Test(expected = PassengerException.class)
	public void testFirstBookingTimeLessThanZeroBoundaryCase() throws PassengerException {
		testPassenger = new First(NEGATIVE_BOUNDARY, TEST_DEPARTURE_TIME);
	}

	@Test(expected = PassengerException.class)
	public void testFirstBookingTimeLessThanZero() throws PassengerException {
		testPassenger = new First(NEGATIVE_PARAMETER, TEST_DEPARTURE_TIME);
	}

	@Test(expected = PassengerException.class)
	public void testFirstDepartureTimeEqualToZero() throws PassengerException {
		testPassenger = new First(TEST_BOOKING_TIME, ZERO_BOUNDARY_CASE);
	}

	@Test(expected = PassengerException.class)
	public void testFirstDepartureTimeLessThanZeroBoundaryCase() throws PassengerException {
		testPassenger = new First(TEST_BOOKING_TIME, NEGATIVE_BOUNDARY);
	}

	@Test(expected = PassengerException.class)
	public void testFirstDepartureTimeLessThanZero() throws PassengerException {
		testPassenger = new First(TEST_BOOKING_TIME, NEGATIVE_PARAMETER);
	}

	@Test(expected = PassengerException.class)
	public void testFirstDepartureTimeLessThanBookingTime() throws PassengerException {
		testPassenger = new First(GREATER_BOOKING_TIME, LESSER_DEPARTURE_TIME);
	}

	@Test(expected = PassengerException.class)
	public void testFirstDepartureTimeLessThanBookingTimeLessThanZero() throws PassengerException {
		testPassenger = new First(TEST_BOOKING_TIME, NEGATIVE_BOUNDARY);
	}

	@Test(expected = PassengerException.class)
	public void testFirstDepartureTimeLessThanBookingTimeZeroDepartureTime() throws PassengerException {
		testPassenger = new First(TEST_BOOKING_TIME, ZERO_BOUNDARY_CASE);
	}

	// NO SEATS MESSAGE TESTS

	/**
	 * Test method for {@link asgn2Passengers.First#noSeatsMsg()}.
	 */
	@Test
	public void testNoSeatsMsg() {
		assertEquals("No seats available in First", myPassenger.noSeatsMsg());
	}

	// UPGRADE TESTS

	/**
	 * Test method for {@link asgn2Passengers.First#upgrade()}.
	 * 
	 * @throws PassengerException
	 */
	@Test
	public void testUpgradeBookingTime() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		upgradedPassenger = myPassenger.upgrade();
		assertTrue(myPassenger.getBookingTime() == upgradedPassenger.getBookingTime());
	}

	@Test
	public void testUpgradeDepartureTime() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		upgradedPassenger = myPassenger.upgrade();
		assertTrue(myPassenger.getDepartureTime() == upgradedPassenger.getDepartureTime());
	}

	@Test
	public void testUpgradeConfirmationTime() throws PassengerException {	
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		upgradedPassenger = myPassenger.upgrade();
		assertTrue(myPassenger.getConfirmationTime() == upgradedPassenger.getConfirmationTime());
	}

	@Test
	public void testUpgradePassID() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		upgradedPassenger = myPassenger.upgrade();
		assertTrue(myPassenger.getPassID().equals(upgradedPassenger.getPassID()));
	} 

	@Test
	public void testUpgradeEnterQueueTime() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		upgradedPassenger = myPassenger.upgrade();
		assertTrue(myPassenger.getEnterQueueTime() == upgradedPassenger.getEnterQueueTime());
	}

	@Test
	public void testUpgradeEnterQueueTimeWhenNeverQueued() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		upgradedPassenger = myPassenger.upgrade();
		assertTrue(myPassenger.getEnterQueueTime() == upgradedPassenger.getEnterQueueTime());
	}

	@Test
	public void testUpgradeExitQueueTimeAfterConfirmingSeat() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		upgradedPassenger = myPassenger.upgrade();
		assertTrue(myPassenger.getExitQueueTime() == upgradedPassenger.getExitQueueTime());
	}

	@Test
	public void testUpgradeExitQueueWhenNeverQueued() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		upgradedPassenger = myPassenger.upgrade();
		assertTrue(myPassenger.getExitQueueTime() == upgradedPassenger.getExitQueueTime());
	}

	public void testUpgradeWasConfirmed() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		upgradedPassenger = myPassenger.upgrade();
		assertTrue(myPassenger.wasConfirmed() == upgradedPassenger.wasConfirmed());
	}

	public void testUpgradeWasQueued() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		upgradedPassenger = myPassenger.upgrade();
		assertTrue(myPassenger.wasQueued() == upgradedPassenger.wasQueued());
	}

	public void testUpgradeWasQueuedAfterNeverBeingQueued() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		upgradedPassenger = myPassenger.upgrade();
		assertTrue(myPassenger.wasQueued() == upgradedPassenger.wasQueued());
	}

	@Test
	public void testUpgradeBothConfirmed() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		upgradedPassenger = myPassenger.upgrade();
		assertTrue(myPassenger.isConfirmed() == upgradedPassenger.isConfirmed());
	}

	@Test
	public void testUpgradeBothNotNew() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		upgradedPassenger = myPassenger.upgrade();
		assertTrue(myPassenger.isNew() == upgradedPassenger.isNew());
	}

	@Test
	public void testUpgradeBothNotQueued() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		upgradedPassenger = myPassenger.upgrade();
		assertTrue(myPassenger.isQueued() == upgradedPassenger.isQueued());
	}

	@Test
	public void testUpgradeBothNotRefused() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		upgradedPassenger = myPassenger.upgrade();
		assertTrue(myPassenger.isRefused() == upgradedPassenger.isRefused());
	}

	@Test
	public void testUpgradeBothNotFlown() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		upgradedPassenger = myPassenger.upgrade();
		assertTrue(myPassenger.isFlown() == upgradedPassenger.isFlown());
	}

	@Test
	public void testUpgradeFirstClassUnchangedForUpgradedPassenger() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		upgradedPassenger = myPassenger.upgrade();
		assertTrue(upgradedPassenger instanceof First);
	}
	
	@Test
	public void testUpgradeFirstClassUnchangedForOriginalPassenger() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		upgradedPassenger = myPassenger.upgrade();
		assertTrue(myPassenger instanceof First);
	}

	// CANCEL SEAT TESTS

	/**
	 * Test method for {@link asgn2Passengers.Passenger#cancelSeat(int)}.
	 * 
	 * @throws PassengerException
	 */
	@Test
	public void testCancelSeatIsNewStateAfterCancellingSeat() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		assertTrue(myPassenger.isNew());
	}

	@Test
	public void testCancelSeatIsQueuedStateAfterCancellingSeat() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		assertFalse(myPassenger.isQueued());
	}

	@Test
	public void testCancelSeatIsRefusedStateAfterCancellingSeat() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		assertFalse(myPassenger.isRefused());
	}

	@Test
	public void testCancelSeatIsFlownStateAfterCancellingSeat() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		assertFalse(myPassenger.isFlown());
	}

	@Test
	public void testCancelSeatIsConfirmedStateAfterConfirmingSeat() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		assertFalse(myPassenger.isConfirmed());
	}

	@Test
	public void testCancelSeatIsNewAfterQueueingThenConfirmingSeat() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		assertTrue(myPassenger.isNew());
	}

	@Test
	public void testCancelSeatIsQueuedAfterQueueingThenConfirmingSeat() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		assertFalse(myPassenger.isQueued());
	}

	@Test
	public void testCancelSeatIsConfirmedAfterQueueingThenConfirmingSeat() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		assertFalse(myPassenger.isConfirmed());
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatWhenPassengerStateIsNew() throws PassengerException {
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);	
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatWhenPassengerStateIsQueued() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatWhenPassengerStateIsRefused()  throws PassengerException {
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatWhenPassengerStateIsFlown() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatCancellationTimeLessThanZeroBoundaryCase() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(NEGATIVE_BOUNDARY);
	}
	
	@Test(expected = PassengerException.class)
	public void testCancelSeatCancellationTimeLessThanZero() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(NEGATIVE_PARAMETER);
		
	}

	@Test(expected = PassengerException.class)
	public void testCancelSeatCancellationTimeGreaterThanDepartureTime() throws PassengerException {	
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME_BOUNDARY);
		
	}

	// CONFIRM SEAT TESTS

	/**
	 * Test method for {@link asgn2Passengers.Passenger#confirmSeat(int, int)}.
	 * 
	 * @throws PassengerException
	 */
	@Test
	public void testConfirmSeat() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertTrue(myPassenger.isConfirmed());
	}

	@Test
	public void testConfirmSeatIsConfirmedAfterQueueing() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertTrue(myPassenger.isConfirmed());
	}

	@Test
	public void testConfirmSeatIsQueuedAfterQueueing() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isQueued());
	}
	
	@Test
	public void testConfirmSeatExitQueueTimeSetToConfirmationTime() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertTrue(myPassenger.getExitQueueTime() == myPassenger.getConfirmationTime());
	}

	@Test
	public void testConfirmSeatIsRefusedAfterConfirmingSeat() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isRefused());
	}

	@Test
	public void testConfirmSeatIsFlownAfterConfirmingSeat() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isFlown());
	}

	@Test
	public void testConfirmSeatIsNewAfterConfirmingSeat() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isNew());
	}

	@Test
	public void testConfirmSeatAfterCancellingSeat() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		assertFalse(myPassenger.isConfirmed());
	}
	
	@Test
	public void testConfirmSeatWasConfirmedAfterConfirmingSeat() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertTrue(myPassenger.wasConfirmed());
	}

	@Test(expected = PassengerException.class)
	public void testConfirmSeatAfterConfirmingAgain() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
	}

	@Test(expected = PassengerException.class)
	public void testConfirmSeatWhenPassengerStateIsRefused() throws PassengerException {
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
	}

	@Test(expected = PassengerException.class)
	public void testConfirmSeatWhenPassengerStateIsFlown() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
	}

	@Test(expected = PassengerException.class)
	public void testConfirmSeatConfirmationTimeLessThanZeroBoundaryCase() throws PassengerException {
		myPassenger.confirmSeat(NEGATIVE_BOUNDARY, TEST_DEPARTURE_TIME);
	}
	
	@Test(expected = PassengerException.class)
	public void testConfirmSeatConfirmationTimeLessThanZero() throws PassengerException {
		myPassenger.confirmSeat(NEGATIVE_PARAMETER, TEST_DEPARTURE_TIME);
	}

	@Test(expected = PassengerException.class)
	public void testConfirmSeatConfirmationTimeGreaterThanDepartureTime() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, LESSER_DEPARTURE_TIME);
	}

	// FLY PASSENGER TESTS

	/**
	 * Test method for {@link asgn2Passengers.Passenger#flyPassenger(int)}.
	 * 
	 * @throws PassengerException
	 */
	@Test
	public void testFlyPassengerIsFlown() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		assertTrue(myPassenger.isFlown());
	}

	@Test
	public void testFlyPassengerIsConfirmed() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isConfirmed());
	}

	@Test
	public void testFlyPassengerIsNew() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isNew());
	}

	@Test
	public void testFlyPassengerIsQueued() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isQueued());
	}

	@Test
	public void testFlyPassengerIsRefused() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isRefused());
	}

	@Test(expected = PassengerException.class)
	public void testFlyPassengerWhenPassengerStateIsNew() throws PassengerException {
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
	}

	@Test(expected = PassengerException.class)
	public void testFlyPassengerWhenPassengerStateIsQueued() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
	}

	@Test(expected = PassengerException.class)
	public void testFlyPassengerWhenPassengerStateIsRefused() throws PassengerException {
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
	}

	@Test(expected = PassengerException.class)
	public void testFlyPassengerWhenPassengerStateIsFlown() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(ALTERNATE_DEPARTURE_TIME);
	}

	@Test(expected = PassengerException.class)
	public void testFlyPassengerDepartureTimeEqualsZero() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(ZERO_BOUNDARY_CASE);
	}

	@Test(expected = PassengerException.class)
	public void testFlyPassengerDepartureTimeLessThanZeroBoundaryCase() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(NEGATIVE_BOUNDARY);
	}
	
	@Test(expected = PassengerException.class)
	public void testFlyPassengerDepartureTimeLessThanZero() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(NEGATIVE_PARAMETER);	
	}

	// GET BOOKING TIME TESTS

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getBookingTime()}.
	 */
	@Test
	public void testGetBookingTime() {
		assertEquals(TEST_BOOKING_TIME, myPassenger.getBookingTime());
	}

	// GET CONFIRMATION TIME TESTS

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getConfirmationTime()}.
	 * 
	 * @throws PassengerException
	 */
	@Test
	public void testGetConfirmationTime() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertEquals(TEST_CONFIRMATION_TIME, myPassenger.getConfirmationTime());
	}

	@Test
	public void testGetConfirmationTimeBeforeConfirmingSeat() {
		assertEquals(ZERO_BOUNDARY_CASE, myPassenger.getConfirmationTime());
	}

	// GET DEPARTURE TIME TESTS

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getDepartureTime()}.
	 */
	@Test
	public void testGetDepartureTime() {
		assertEquals(TEST_DEPARTURE_TIME, myPassenger.getDepartureTime());
	}

	@Test
	public void testGetDepartureTimeAfterQueueingPassengerWithDifferentDepartureTime() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, ALTERNATE_DEPARTURE_TIME);
		assertEquals(ALTERNATE_DEPARTURE_TIME, myPassenger.getDepartureTime());
	}

	@Test
	public void testGetDepartureTimeAfterConfirmingPassengerWithDifferentDepartureTime() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, ALTERNATE_DEPARTURE_TIME);
		assertEquals(ALTERNATE_DEPARTURE_TIME, myPassenger.getDepartureTime());
	}

	@Test
	public void testGetDepartureTimeAfterFlyingPassengerWithDifferentDepartureTime() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(ALTERNATE_DEPARTURE_TIME);
		assertEquals(ALTERNATE_DEPARTURE_TIME, myPassenger.getDepartureTime());
	}

	// GET ENTER QUEUE TIME TESTS

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getEnterQueueTime()}.
	 * 
	 * @throws PassengerException
	 */
	@Test
	public void testGetEnterQueueTime() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		assertEquals(TEST_QUEUE_TIME, myPassenger.getEnterQueueTime());
	}

	// GET EXIT QUEUE TIME TESTS

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getExitQueueTime()}.
	 * 
	 * @throws PassengerException
	 */
	@Test
	public void testGetExitQueueTimeAfterConfirmingASeat() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertEquals(TEST_CONFIRMATION_TIME, myPassenger.getExitQueueTime());
	}

	@Test
	public void testGetExitQueueTimeAfterBeingRefused() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		assertEquals(TEST_REFUSAL_TIME, myPassenger.getExitQueueTime());
	}

	// IS CONFIRMED TESTS

	/**
	 * Test method for {@link asgn2Passengers.Passenger#isConfirmed()}.
	 * 
	 * @throws PassengerException
	 */
	@Test
	public void testIsConfirmed() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertTrue(myPassenger.isConfirmed());
	}

	@Test
	public void testIsConfirmedAfterCancellingSeat() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		assertFalse(myPassenger.isConfirmed());
	}

	// IS FLOWN TESTS

	/**
	 * Test method for {@link asgn2Passengers.Passenger#isFlown()}.
	 * 
	 * @throws PassengerException
	 */
	@Test
	public void testIsFlown() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		assertTrue(myPassenger.isFlown());
	}

	@Test
	public void testIsFlownAfterQueueingThenConfirmingSeat() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		assertTrue(myPassenger.isFlown());
	}

	@Test
	public void testIsFlownAfterCancellingSeat() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		assertFalse(myPassenger.isFlown());
	}

	// IS NEW TESTS

	/**
	 * Test method for {@link asgn2Passengers.Passenger#isNew()}.
	 */
	@Test
	public void testIsNew() {
		assertTrue(myPassenger.isNew());
	}

	@Test
	public void testIsNewAfterBeingConfirmed() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isNew());
	}

	@Test
	public void testIsNewAfterCancellingSeat() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		assertTrue(myPassenger.isNew());
	}

	@Test
	public void testIsNewAfterBeingQueued() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isNew());
	}

	@Test
	public void testIsNewAfterBeingQueuedThenConfirmedThenCancellingSeat() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		assertTrue(myPassenger.isNew());
	}

	// IS QUEUED TESTS

	/**
	 * Test method for {@link asgn2Passengers.Passenger#isQueued()}.
	 * 
	 * @throws PassengerException
	 */
	@Test
	public void testIsQueued() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		assertTrue(myPassenger.isQueued());
	}

	@Test
	public void testIsQueuedAfterBeingConfirmed() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isQueued());
	}

	@Test
	public void testIsQueuedAfterBeingRefused() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		assertFalse(myPassenger.isQueued());
	}

	// IS REFUSED TESTS

	/**
	 * Test method for {@link asgn2Passengers.Passenger#isRefused()}.
	 * 
	 * @throws PassengerException
	 */
	@Test
	public void testIsRefused() throws PassengerException {
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		assertTrue(myPassenger.isRefused());
	}

	@Test
	public void testIsRefusedAfterBeingQueued() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		assertTrue(myPassenger.isRefused());
	}

	@Test
	public void testIsRefusedAfterBeingConfirmed() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isRefused());
	}

	// QUEUE PASSENGER TESTS

	/**
	 * Test method for
	 * {@link asgn2Passengers.Passenger#queuePassenger(int, int)}.
	 * 
	 * @throws PassengerException
	 */
	@Test
	public void testQueuePassenger() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		assertTrue(myPassenger.isQueued());
	}

	@Test
	public void testQueuePassengerIsConfirmedAfterBeingQueued() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isConfirmed());
	}

	@Test
	public void testQueuePassengerIsNewAfterBeingQueued() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isNew());
	}

	@Test
	public void testQueuePassengerIsRefusedAfterBeingQueued() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isRefused());
	}

	@Test
	public void testQueuePassengerIsFlownAfterBeingQueued() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isFlown());
	}

	@Test
	public void testQueuePassengerIsQueuedAfterBeingConfirmed() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isQueued());
	}

	@Test
	public void testQueuePassengerIsConfirmedAfterBeingConfirmed() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertTrue(myPassenger.isConfirmed());
	}

	@Test
	public void testQueuePassengerIsQueuedAfterBeingRefused() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		assertFalse(myPassenger.isQueued());
	}

	@Test
	public void testQueuePassengerIsRefusedAfterBeingRefused() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		assertTrue(myPassenger.isRefused());
	}
	
	@Test
	public void testQueuePassengerWasQueuedAfterBeingQueued() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		assertTrue(myPassenger.wasQueued());
	}

	@Test(expected = PassengerException.class)
	public void testQueuePassengerWhenPassengerStateIsAlreadyQueued() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.queuePassenger(TEST_GREATER_QUEUE_TIME, TEST_DEPARTURE_TIME);
	}

	@Test(expected = PassengerException.class)
	public void testQueuePassengerWhenPassengerStateIsConfirmed() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.queuePassenger(TEST_GREATER_QUEUE_TIME, TEST_DEPARTURE_TIME);
	}

	@Test(expected = PassengerException.class)
	public void testQueuePassengerWhenPassengerStateIsRefused() throws PassengerException {
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		myPassenger.queuePassenger(TEST_GREATER_QUEUE_TIME, TEST_DEPARTURE_TIME);
	}

	@Test(expected = PassengerException.class)
	public void testQueuePassengerWhenPassengerStateIsFlown() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		myPassenger.queuePassenger(GREATER_QUEUE_TIME, ALTERNATE_DEPARTURE_TIME);
	}

	@Test(expected = PassengerException.class)
	public void testQueuePassengerWhenQueueTimeIsLessThanZeroBoundaryCase() throws PassengerException {
		myPassenger.queuePassenger(NEGATIVE_BOUNDARY, TEST_DEPARTURE_TIME);
	}
	
	@Test(expected = PassengerException.class)
	public void testQueuePassengerWhenQueueTimeIsLessThanZero() throws PassengerException {
		myPassenger.queuePassenger(NEGATIVE_PARAMETER, TEST_DEPARTURE_TIME);
	}

	@Test(expected = PassengerException.class)
	public void testQueuePassengerWhenDepartureTimeIsLessThanQueueTime() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, LESSER_DEPARTURE_TIME);
	}

	// REFUSE PASSENGER TESTS

	/**
	 * Test method for {@link asgn2Passengers.Passenger#refusePassenger(int)}.
	 * 
	 * @throws PassengerException
	 */
	@Test
	public void testRefusePassenger() throws PassengerException {
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		assertTrue(myPassenger.isRefused());
	}

	@Test
	public void testRefusePassengerIsConfirmedAfterBeingConfirmed() throws PassengerException {
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		assertFalse(myPassenger.isConfirmed());
	}

	@Test
	public void testRefusePassengerIsQueuedAfterBeingConfirmed() throws PassengerException {
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		assertFalse(myPassenger.isQueued());
	}

	@Test
	public void testRefusePassengerIsNewAfterBeingConfirmed() throws PassengerException {
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		assertFalse(myPassenger.isNew());
	}

	@Test
	public void testRefusePassengerIsFlownAfterBeingConfirmed() throws PassengerException {
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		assertFalse(myPassenger.isFlown());
	}

	@Test
	public void testRefusePassengerAfterBeingQueued() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		assertTrue(myPassenger.isRefused());
	}

	@Test
	public void testRefusePassengerAfterNeverBeingRefused() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isRefused());
	}

	@Test(expected = PassengerException.class)
	public void testRefusePassengerWhenPassengerStateIsConfirmed() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
	}

	@Test(expected = PassengerException.class)
	public void testRefusePassengerWhenPassengerStateIsAlreadyRefused() throws PassengerException {
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		myPassenger.refusePassenger(ALTERNATE_REFUSAL_TIME);
		
	}

	@Test(expected = PassengerException.class)
	public void testRefusePassengerWhenPassengerStateIsFlown() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		myPassenger.refusePassenger(TEST_REFUSAL_TIME_BOUNDARY);
	}

	@Test(expected = PassengerException.class)
	public void testRefusePassengerWhenRefusalTimeIsLessThanZero() throws PassengerException {
		myPassenger.refusePassenger(NEGATIVE_BOUNDARY);
		
	}
	
	@Test(expected = PassengerException.class)
	public void testRefusePassengerWhenRefusalTimeIsLessThanBookingTimeAndIsANegativeNumber() throws PassengerException {
		myPassenger.refusePassenger(NEGATIVE_PARAMETER);
	}

	@Test(expected = PassengerException.class)
	public void testRefusePassengerWhenRefusalTimeIsLessThanBookingTime() throws PassengerException {
		myPassenger.refusePassenger(ZERO_BOUNDARY_CASE);
	}

	// WAS CONFIRMED TESTS

	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasConfirmed()}.
	 * 
	 * @throws PassengerException
	 */
	@Test
	public void testWasConfirmedAfterCancellingSeat() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		assertTrue(myPassenger.wasConfirmed());
	}

	@Test
	public void testWasConfirmedAfterFlying() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);	
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		assertTrue(myPassenger.wasConfirmed());
	}

	@Test
	public void testWasConfirmedAfterNeverBeingConfirmed() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.wasConfirmed());
	}

	// WAS QUEUED TESTS

	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasQueued()}.
	 * 
	 * @throws PassengerException
	 */
	@Test
	public void testWasQueuedAfterBeingConfirmed() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertTrue(myPassenger.wasQueued());
	}

	@Test
	public void testWasQueuedAfterBeingRefused() throws PassengerException {
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		assertTrue(myPassenger.wasQueued());
	}

	@Test
	public void testWasQueuedAfterNeverBeingQueued() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.wasQueued());
	}
}
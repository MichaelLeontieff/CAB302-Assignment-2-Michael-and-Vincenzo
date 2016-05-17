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
import asgn2Passengers.First;
import asgn2Passengers.PassengerException;

/**
 * This class represents a unit test class for the First class as part of the
 * AircraftSimulator Project.
 * 
 * @author Vincenzo Iandolo - n9172912
 * @version 1.0
 *
 */
public class FirstTests {
	
	// Declare constants
	private static final int TEST_BOOKING_TIME = 1;
	private static final int TEST_DEPARTURE_TIME  = 10;
	private static final int TEST_CONFIRMATION_TIME = 5;
	private static final int TEST_CANCELLATION_TIME = 8;
	private static final int TEST_QUEUE_TIME = 6;
	private static final int TEST_REFUSAL_TIME = 7;
	
	// Declare test objects
	private First myPassenger;

	/**
	 * @throws PassengerException
	 */
	@Before
	public void setUp() throws PassengerException {
		// Creates a test passenger and sets parameters
		myPassenger = new First(TEST_BOOKING_TIME, TEST_DEPARTURE_TIME);
	}
	
	// CONSTRUCTOR TESTS
	
	/**
	 * Test method for {@link asgn2Passengers.First#First(int, int)}.
	 * @throws PassengerException
	 */
	@Test (expected = PassengerException.class)
	public void testFirstBookingTimeLessThanZero() throws PassengerException {
		First testPassenger = new First(-1, TEST_DEPARTURE_TIME);
	}
	
	@Test (expected = PassengerException.class)
	public void testFirstBookingEqualToZero() throws PassengerException {
		First testPassenger = new First(TEST_BOOKING_TIME, 0);
	}
	
	@Test (expected = PassengerException.class)
	public void testFirstDepartureTimeLessThanZero() throws PassengerException {
		First testPassenger = new First(TEST_BOOKING_TIME, -1);
	}
	
	@Test (expected = PassengerException.class)
	public void testFirstBookingDepartureTimeLessThanBookingTime() throws PassengerException {
		First testPassenger = new First(5, 3);
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
	 */
	@Test
	public void testUpgrade() {
		assertTrue(myPassenger == myPassenger.upgrade());
	}// or myPassenger.getBookingTime == myPassenger.upgrade.getBookingTime?

	// CANCEL SEAT TESTS
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#cancelSeat(int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testCancelSeatIsNewStateAfterConfirmingSeat() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Cancel the passenger's seat
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		// Check condition
		assertTrue(myPassenger.isNew());
	}
	
	@Test
	public void testCancelSeatIsConfirmedStateAfterConfirmingSeat() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Cancel the passenger's seat
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		// Check condition
		assertFalse(myPassenger.isConfirmed());
	}
	
	@Test
	public void testCancelSeatIsNewAfterQueueingThenConfirmingSeat() throws PassengerException {
		// Set passenger to a queued state
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Cancel passenger's seat
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		// Check condition
		assertTrue(myPassenger.isNew());
	}
	
	@Test
	public void testCancelSeatIsQueuedAfterQueueingThenConfirmingSeat() throws PassengerException {
		// Set passenger to a queued state
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Cancel passenger's seat
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		// Check condition
		assertFalse(myPassenger.isQueued());
	}
	
	@Test
	public void testCancelSeatIsConfirmedAfterQueueingThenConfirmingSeat() throws PassengerException {
		// Set passenger to a queued state
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Cancel passenger's seat
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		// Check condition
		assertFalse(myPassenger.isConfirmed());
	}
	
	@Test (expected = PassengerException.class)
	public void testCancelSeatWhenPassengerStateIsNew() throws PassengerException {
		// Cancel passenger's seat
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		// Expect exception
	}
	
	@Test (expected = PassengerException.class)
	public void testCancelSeatWhenPassengerStateIsQueued() throws PassengerException {
		// Set passenger to a queued state
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		// Cancel passenger's seat
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		// Expect exception
	}
	
	@Test (expected = PassengerException.class)
	public void testCancelSeatWhenPassengerStateIsRefused() throws PassengerException {
		// Set passenger to a refused state
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		// Cancel passenger's seat
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		// Expect exception
	}
	
	@Test (expected = PassengerException.class)
	public void testCancelSeatWhenPassengerStateIsFlown() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Set passenger to a flown state
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		// Cancel passenger's seat
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		// Expect exception
	}
	
	@Test (expected = PassengerException.class)
	public void testCancelSeatCancellationTimeLessThanZero() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Cancel passenger's seat
		myPassenger.cancelSeat(-1);
		// Expect exception
	}
	
	@Test (expected = PassengerException.class)
	public void testCancelSeatCancellationTimeGreaterThanDepartureTime() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Cancel passenger's seat
		myPassenger.cancelSeat(11);
		// Expect exception
	}
	
	// CONFIRM SEAT TESTS

	/**
	 * Test method for {@link asgn2Passengers.Passenger#confirmSeat(int, int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testConfirmSeat() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Check condition
		assertTrue(myPassenger.isConfirmed());
	}
	
	@Test
	public void testConfirmSeatIsConfirmedAfterQueueing() throws PassengerException {
		// Set passenger to a queued state
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Check condition
		assertTrue(myPassenger.isConfirmed());
	}
	
	@Test
	public void testConfirmSeatIsQueuedAfterQueueing() throws PassengerException {
		// Set passenger to a queued state
		myPassenger.queuePassenger(TEST_QUEUE_TIME, TEST_DEPARTURE_TIME);
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Check condition
		assertFalse(myPassenger.isQueued());
	}
	
	@Test
	public void testConfirmSeatAfterCancellingSeat() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Cancel passenger's seat
		myPassenger.cancelSeat(TEST_CANCELLATION_TIME);
		// Check condition
		assertFalse(myPassenger.isConfirmed());
	}
	
	@Test (expected = PassengerException.class)
	public void testConfirmSeatAfterConfirmingAgain() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Set passenger to a confirmed state again
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Expect exception
	}
	
	@Test (expected = PassengerException.class)
	public void testConfirmSeatWhenPassengerStateIsRefused() throws PassengerException {
		// Set passenger to a refused state
		myPassenger.refusePassenger(TEST_REFUSAL_TIME);
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Expect exception
	}
	
	@Test (expected = PassengerException.class)
	public void testConfirmSeatWhenPassengerStateIsFlown() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		// Set passenger to a flown state
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		// Set passenger to a confirmed state again
		myPassenger.confirmSeat(12, 20);
		// Expect exception
	}
	
	@Test (expected = PassengerException.class)
	public void testConfirmSeatConfirmationTimeLessThanZero() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(-1, TEST_DEPARTURE_TIME);
		// Expect exception
	}
	
	@Test (expected = PassengerException.class)
	public void testConfirmSeatConfirmationTimeGreaterThanDepartureTime() throws PassengerException {
		// Set passenger to a confirmed state
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, 2);
		// Expect exception
	}
	
	// FLY PASSENGER TESTS
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#flyPassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testFlyPassenger() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		assertTrue(myPassenger.isFlown());
	}
	
	@Test (expected = PassengerException.class)
	public void testFlyPassengerWhenPassengerStateIsNew() throws PassengerException {
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
	}
	
	@Test (expected = PassengerException.class)
	public void testFlyPassengerWhenPassengerStateIsQueued() throws PassengerException {
		myPassenger.queuePassenger(6, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
	}
	
	@Test (expected = PassengerException.class)
	public void testFlyPassengerWhenPassengerStateIsRefused() throws PassengerException {
		myPassenger.refusePassenger(8);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
	}
	
	@Test (expected = PassengerException.class)
	public void testFlyPassengerWhenPassengerStateIsFlown() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(20);
	}
	
	@Test (expected = PassengerException.class)
	public void testFlyPassengerDepartureTimeEqualsZero() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(0);
	}
	
	@Test (expected = PassengerException.class)
	public void testFlyPassengerDepartureTimeLessThanZero() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(-1);
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getBookingTime()}.
	 */
	@Test
	public void testGetBookingTime() {
		assertEquals(1, myPassenger.getBookingTime());
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getConfirmationTime()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testGetConfirmationTime() throws PassengerException {
		myPassenger.confirmSeat(3, TEST_DEPARTURE_TIME);
		assertEquals(3, myPassenger.getConfirmationTime());
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#getConfirmationTime()}.
	 */
	@Test
	public void testGetConfirmationTimeBeforeConfirmingSeat() {
		assertEquals(0, myPassenger.getConfirmationTime());
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getDepartureTime()}.
	 */
	@Test
	public void testGetDepartureTime() {
		assertEquals(10, myPassenger.getDepartureTime());
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getEnterQueueTime()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testGetEnterQueueTime() throws PassengerException {
		myPassenger.queuePassenger(7, TEST_DEPARTURE_TIME);
		assertEquals(7, myPassenger.getEnterQueueTime());
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getExitQueueTime()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testGetExitQueueTimeAfterConfirmingASeat() throws PassengerException {
		myPassenger.queuePassenger(7, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(9, TEST_DEPARTURE_TIME);
		assertEquals(9, myPassenger.getExitQueueTime());
	}
	
	@Test
	public void testGetExitQueueTimeAfterBeingRefused() throws PassengerException {
		myPassenger.queuePassenger(7, TEST_DEPARTURE_TIME);
		myPassenger.refusePassenger(8);
		assertEquals(8, myPassenger.getExitQueueTime());
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getPassID()}.
	 */
	@Test
	public void testGetPassID() {
		assertEquals("F:56", myPassenger.getPassID());
	} //not sure where passID comes from or where it is set?

	/**
	 * Test method for {@link asgn2Passengers.Passenger#isConfirmed()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testIsConfirmed() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertTrue(myPassenger.isConfirmed());
	}
	
	@Test
	public void testIsConfirmedCancelledSeat() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(7);
		assertFalse(myPassenger.isConfirmed());
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#isFlown()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testIsFlown() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		assertTrue(myPassenger.isFlown());
	}
	
	@Test
	public void testIsFlownCancelledSeat() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(7);
		assertFalse(myPassenger.isFlown());
	}

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
		myPassenger.cancelSeat(7);
		assertTrue(myPassenger.isNew());
	}
	
	@Test
	public void testIsNewAfterBeingQueued() throws PassengerException {
		myPassenger.queuePassenger(7, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isNew());
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#isQueued()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testIsQueued() throws PassengerException {
		myPassenger.queuePassenger(7, TEST_DEPARTURE_TIME);
		assertTrue(myPassenger.isQueued());
	}
	
	@Test
	public void testIsQueuedAfterBeingConfirmed() throws PassengerException {
		myPassenger.queuePassenger(7, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(8, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isQueued());
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#isRefused()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testIsRefused() throws PassengerException {
		myPassenger.refusePassenger(7);
		assertTrue(myPassenger.isRefused());
	}
	
	@Test
	public void testIsRefusedAfterBeingQueued() throws PassengerException {
		myPassenger.queuePassenger(6, TEST_DEPARTURE_TIME);
		myPassenger.refusePassenger(7);
		assertTrue(myPassenger.isRefused());
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#queuePassenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testQueuePassenger() throws PassengerException {
		myPassenger.queuePassenger(6, TEST_DEPARTURE_TIME);
		assertTrue(myPassenger.isQueued());
	}
	
	@Test
	public void testQueuePassengerAfterBeingConfirmed() throws PassengerException {
		myPassenger.queuePassenger(6, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(7, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isQueued());
	}
	
	@Test (expected = PassengerException.class)
	public void testQueuePassengerWhenPassengerStateIsAlreadyQueued() throws PassengerException {
		myPassenger.queuePassenger(6, TEST_DEPARTURE_TIME);
		myPassenger.queuePassenger(8, TEST_DEPARTURE_TIME);
	}
	
	@Test (expected = PassengerException.class)
	public void testQueuePassengerWhenPassengerStateIsConfirmed() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.queuePassenger(8, TEST_DEPARTURE_TIME);
	}
	
	@Test (expected = PassengerException.class)
	public void testQueuePassengerWhenPassengerStateIsRefused() throws PassengerException {
		myPassenger.refusePassenger(6);
		myPassenger.queuePassenger(8, TEST_DEPARTURE_TIME);
	}
	
	@Test (expected = PassengerException.class)
	public void testQueuePassengerWhenPassengerStateIsFlown() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		myPassenger.queuePassenger(8, TEST_DEPARTURE_TIME);
	}
	
	@Test (expected = PassengerException.class)
	public void testQueuePassengerWhenQueueTimeIsLessThanZero() throws PassengerException {
		myPassenger.queuePassenger(-1, TEST_DEPARTURE_TIME);
	}
	
	@Test (expected = PassengerException.class)
	public void testQueuePassengerWhenDepartureTimeIsLessThanQueueTime() throws PassengerException {
		myPassenger.queuePassenger(8, 5);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#refusePassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testRefusePassenger() throws PassengerException {
		myPassenger.refusePassenger(7);
		assertTrue(myPassenger.isRefused());
	}
	
	@Test
	public void testRefusePassengerAfterBeingQueued() throws PassengerException {
		myPassenger.queuePassenger(7, TEST_DEPARTURE_TIME);
		myPassenger.refusePassenger(9);
		assertTrue(myPassenger.isRefused());
	}
	
	@Test
	public void testRefusePassengerAfterNeverBeingRefused() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.isRefused());
	}
	
	@Test (expected = PassengerException.class)
	public void testRefusePassengerWhenPassengerStateIsConfirmed() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.refusePassenger(8);
	}
	
	@Test (expected = PassengerException.class)
	public void testRefusePassengerWhenPassengerStateIsAlreadyRefused() throws PassengerException {
		myPassenger.refusePassenger(6);
		myPassenger.refusePassenger(8);
	}
	
	@Test (expected = PassengerException.class)
	public void testRefusePassengerWhenPassengerStateIsFlown() throws PassengerException {
		myPassenger.confirmSeat(TEST_CONFIRMATION_TIME, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		myPassenger.refusePassenger(15);
	}
	
	@Test (expected = PassengerException.class)
	public void testRefusePassengerWhenRefusalTimeIsLessThanZero() throws PassengerException {
		myPassenger.refusePassenger(-1);
	}
	
/*	@Test (expected = PassengerException.class)
	public void testRefusePassengerWhenRefusalTimeIsLessThanBookingTime() throws PassengerException {
		myPassenger.refusePassenger(-1);
	} unsure how to test this */
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasConfirmed()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testWasConfirmedAfterCancellingSeat() throws PassengerException {
		myPassenger.confirmSeat(6, TEST_DEPARTURE_TIME);
		myPassenger.cancelSeat(8);
		assertTrue(myPassenger.wasConfirmed());
	}
	
	@Test
	public void testWasConfirmedAfterFlying() throws PassengerException {
		myPassenger.confirmSeat(6, TEST_DEPARTURE_TIME);
		myPassenger.flyPassenger(TEST_DEPARTURE_TIME);
		assertTrue(myPassenger.wasConfirmed());
	}
	
	@Test
	public void testWasConfirmedAfterNeverBeingConfirmed() throws PassengerException {
		myPassenger.queuePassenger(6, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.wasConfirmed());
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasQueued()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testWasQueuedAfterBeingConfirmed() throws PassengerException {
		myPassenger.queuePassenger(7, TEST_DEPARTURE_TIME);
		myPassenger.confirmSeat(8, TEST_DEPARTURE_TIME);
		assertTrue(myPassenger.wasQueued());
	}
	
	@Test
	public void testWasQueuedAfterBeingRefused() throws PassengerException {
		myPassenger.queuePassenger(7, TEST_DEPARTURE_TIME);
		myPassenger.refusePassenger(8);
		assertTrue(myPassenger.wasQueued());
	}
	
	@Test
	public void testWasQueuedAfterNeverBeingQueued() throws PassengerException {
		myPassenger.confirmSeat(8, TEST_DEPARTURE_TIME);
		assertFalse(myPassenger.wasQueued());
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#copyPassengerState(asgn2Passengers.Passenger)}.
	 */
	@Test
	public void testCopyPassengerState() {
		fail("Not yet implemented");
	}

}
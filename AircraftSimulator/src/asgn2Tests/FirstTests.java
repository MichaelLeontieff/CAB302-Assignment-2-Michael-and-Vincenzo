/**
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Passengers.First;
import asgn2Passengers.PassengerException;

/**
 * @author Vincenzo Iandolo
 * @version 1.0
 *
 */
public class FirstTests {
	
	//constants
	private final int testBookingTime = 1;
	private final int testDepartureTime  = 10;
	private final int testConfirmationTime = 5;
	
	//test objects
	private First myPassenger;

	/**
	 * @throws PassengerException
	 */
	@Before
	public void setUp() throws PassengerException {
		myPassenger = new First(testBookingTime, testDepartureTime);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.First#First(int, int)}.
	 * @throws PassengerException
	 */
	@Test (expected = PassengerException.class)
	public void testFirstBookingTimeLessThanZero() throws PassengerException {
		First testPassenger = new First(-1, testDepartureTime);
	}
	
	@Test (expected = PassengerException.class)
	public void testFirstBookingEqualToZero() throws PassengerException {
		First testPassenger = new First(testBookingTime, 0);
	}
	
	@Test (expected = PassengerException.class)
	public void testFirstDepartureTimeLessThanZero() throws PassengerException {
		First testPassenger = new First(testBookingTime, -1);
	}
	
	@Test (expected = PassengerException.class)
	public void testFirstBookingDepartureTimeLessThanBookingTime() throws PassengerException {
		First testPassenger = new First(5, 3);
	}

	/**
	 * Test method for {@link asgn2Passengers.First#noSeatsMsg()}.
	 */
	@Test
	public void testNoSeatsMsg() {
		assertEquals("No seats available in First", myPassenger.noSeatsMsg());
	}

	/**
	 * Test method for {@link asgn2Passengers.First#upgrade()}.
	 */
	@Test
	public void testUpgrade() {
		assertTrue(myPassenger == myPassenger.upgrade());
	}// or myPassenger.getBookingTime == myPassenger.upgrade.getBookingTime?

	/**
	 * Test method for {@link asgn2Passengers.Passenger#cancelSeat(int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testCancelSeatAfterConfirmingSeat() throws PassengerException {
		myPassenger.confirmSeat(testConfirmationTime, testDepartureTime);
		myPassenger.cancelSeat(8);
		assertTrue(myPassenger.isNew());
		assertFalse(myPassenger.isConfirmed());
	}// should i keep two asserts in one test?
	
	@Test
	public void testCancelSeatAfterQueueingThenConfirmingSeat() throws PassengerException {
		myPassenger.queuePassenger(6, testDepartureTime);
		myPassenger.confirmSeat(testConfirmationTime, testDepartureTime);
		myPassenger.cancelSeat(8);
		assertTrue(myPassenger.isNew());
		assertFalse(myPassenger.isQueued());
		assertFalse(myPassenger.isConfirmed());
	}
	
	@Test (expected = PassengerException.class)
	public void testCancelSeatWhenPassengerStateIsNew() throws PassengerException {
		myPassenger.cancelSeat(8);
	}
	
	@Test (expected = PassengerException.class)
	public void testCancelSeatWhenPassengerStateIsQueued() throws PassengerException {
		myPassenger.queuePassenger(6, testDepartureTime);
		myPassenger.cancelSeat(8);
	}
	
	@Test (expected = PassengerException.class)
	public void testCancelSeatWhenPassengerStateIsRefused() throws PassengerException {
		myPassenger.refusePassenger(6);
		myPassenger.cancelSeat(8);
	}
	
	@Test (expected = PassengerException.class)
	public void testCancelSeatWhenPassengerStateIsFlown() throws PassengerException {
		myPassenger.confirmSeat(testConfirmationTime, testDepartureTime);
		myPassenger.flyPassenger(testDepartureTime);
		myPassenger.cancelSeat(8);
	}
	
	@Test (expected = PassengerException.class)
	public void testCancelSeatCancellationTimeLessThanZero() throws PassengerException {
		myPassenger.confirmSeat(testConfirmationTime, testDepartureTime);
		myPassenger.cancelSeat(-1);
	}
	
	@Test (expected = PassengerException.class)
	public void testCancelSeatCancellationTimeGreaterThanDepartureTime() throws PassengerException {
		myPassenger.confirmSeat(testConfirmationTime, testDepartureTime);
		myPassenger.cancelSeat(11);
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#confirmSeat(int, int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testConfirmSeat() throws PassengerException {
		myPassenger.confirmSeat(testConfirmationTime, testDepartureTime);
		assertTrue(myPassenger.isConfirmed());
	}
	
	@Test
	public void testConfirmSeatAfterCancellingSeat() throws PassengerException {
		myPassenger.confirmSeat(testConfirmationTime, testDepartureTime);
		myPassenger.cancelSeat(9);
		assertFalse(myPassenger.isConfirmed());
	}
	
	@Test (expected = PassengerException.class)
	public void testConfirmSeatAfterConfirmingAgain() throws PassengerException {
		myPassenger.confirmSeat(testConfirmationTime, testDepartureTime);
		myPassenger.confirmSeat(testConfirmationTime, testDepartureTime);
	}
	
	@Test (expected = PassengerException.class)
	public void testConfirmSeatWhenPassengerStateIsRefused() throws PassengerException {
		myPassenger.refusePassenger(8);
		myPassenger.confirmSeat(testConfirmationTime, testDepartureTime);
	}
	
	@Test (expected = PassengerException.class)
	public void testConfirmSeatWhenPassengerStateIsFlown() throws PassengerException {
		myPassenger.confirmSeat(testConfirmationTime, testDepartureTime);
		myPassenger.flyPassenger(testDepartureTime);
		myPassenger.confirmSeat(12, 20);
	}
	
	@Test (expected = PassengerException.class)
	public void testConfirmSeatConfirmationTimeLessThanZero() throws PassengerException {
		myPassenger.confirmSeat(-1, testDepartureTime);
	}
	
	@Test (expected = PassengerException.class)
	public void testConfirmSeatConfirmationTimeGreaterThanDepartureTime() throws PassengerException {
		myPassenger.confirmSeat(testConfirmationTime, 2);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#flyPassenger(int)}.
	 */
	@Test
	public void testFlyPassenger() {
		fail("Not yet implemented");
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
		myPassenger.confirmSeat(3, testDepartureTime);
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
		myPassenger.queuePassenger(7, testDepartureTime);
		assertEquals(7, myPassenger.getEnterQueueTime());
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getExitQueueTime()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testGetExitQueueTimeAfterConfirmingASeat() throws PassengerException {
		myPassenger.queuePassenger(7, testDepartureTime);
		myPassenger.confirmSeat(9, testDepartureTime);
		assertEquals(9, myPassenger.getExitQueueTime());
	}
	
	@Test
	public void testGetExitQueueTimeAfterBeingRefused() throws PassengerException {
		myPassenger.queuePassenger(7, testDepartureTime);
		myPassenger.refusePassenger(8);
		assertEquals(8, myPassenger.getExitQueueTime());
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getPassID()}.
	 */
	@Test
	public void testGetPassID() {
		assertEquals("", myPassenger.getPassID());
	} //not sure where passID comes from or where it is set?

	/**
	 * Test method for {@link asgn2Passengers.Passenger#isConfirmed()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testIsConfirmed() throws PassengerException {
		myPassenger.confirmSeat(testConfirmationTime, testDepartureTime);
		assertTrue(myPassenger.isConfirmed());
	}
	
	@Test
	public void testIsConfirmedCancelledSeat() throws PassengerException {
		myPassenger.confirmSeat(testConfirmationTime, testDepartureTime);
		myPassenger.cancelSeat(7);
		assertFalse(myPassenger.isConfirmed());
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#isFlown()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testIsFlown() throws PassengerException {
		myPassenger.confirmSeat(testConfirmationTime, testDepartureTime);
		myPassenger.flyPassenger(testDepartureTime);
		assertTrue(myPassenger.isFlown());
	}
	
	@Test
	public void testIsFlownCancelledSeat() throws PassengerException {
		myPassenger.confirmSeat(testConfirmationTime, testDepartureTime);
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
		myPassenger.confirmSeat(testConfirmationTime, testDepartureTime);
		assertFalse(myPassenger.isNew());
	}
	
	@Test
	public void testIsNewAfterCancellingSeat() throws PassengerException {
		myPassenger.confirmSeat(testConfirmationTime, testDepartureTime);
		myPassenger.cancelSeat(7);
		assertTrue(myPassenger.isNew());
	}
	
	@Test
	public void testIsNewAfterBeingQueued() throws PassengerException {
		myPassenger.queuePassenger(7, testDepartureTime);
		assertFalse(myPassenger.isNew());
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#isQueued()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testIsQueued() throws PassengerException {
		myPassenger.queuePassenger(7, testDepartureTime);
		assertTrue(myPassenger.isQueued());
	}
	
	@Test
	public void testIsQueuedAfterBeingConfirmed() throws PassengerException {
		myPassenger.queuePassenger(7, testDepartureTime);
		myPassenger.confirmSeat(8, testDepartureTime);
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
		myPassenger.queuePassenger(6, testDepartureTime);
		myPassenger.refusePassenger(7);
		assertTrue(myPassenger.isRefused());
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#queuePassenger(int, int)}.
	 */
	@Test
	public void testQueuePassenger() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#refusePassenger(int)}.
	 */
	@Test
	public void testRefusePassenger() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasConfirmed()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testWasConfirmedAfterCancellingSeat() throws PassengerException {
		myPassenger.confirmSeat(6, testDepartureTime);
		myPassenger.cancelSeat(8);
		assertTrue(myPassenger.wasConfirmed());
	}
	
	@Test
	public void testWasConfirmedAfterFlying() throws PassengerException {
		myPassenger.confirmSeat(6, testDepartureTime);
		myPassenger.flyPassenger(testDepartureTime);
		assertTrue(myPassenger.wasConfirmed());
	}
	
	@Test
	public void testWasConfirmedAfterNeverBeingConfirmed() throws PassengerException {
		myPassenger.queuePassenger(6, testDepartureTime);
		assertFalse(myPassenger.wasConfirmed());
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasQueued()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testWasQueuedAfterBeingConfirmed() throws PassengerException {
		myPassenger.queuePassenger(7, testDepartureTime);
		myPassenger.confirmSeat(8, testDepartureTime);
		assertTrue(myPassenger.wasQueued());
	}
	
	@Test
	public void testWasQueuedAfterBeingRefused() throws PassengerException {
		myPassenger.queuePassenger(7, testDepartureTime);
		myPassenger.refusePassenger(8);
		assertTrue(myPassenger.wasQueued());
	}
	
	@Test
	public void testWasQueuedAfterNeverBeingQueued() throws PassengerException {
		myPassenger.confirmSeat(8, testDepartureTime);
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
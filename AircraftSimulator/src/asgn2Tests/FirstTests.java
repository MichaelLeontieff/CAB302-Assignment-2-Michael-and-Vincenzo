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
	 */
	@Test
	public void testCancelSeat() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#confirmSeat(int, int)}.
	 */
	@Test
	public void testConfirmSeat() {
		fail("Not yet implemented");
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
		
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getConfirmationTime()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testGetConfirmationTime() throws PassengerException {
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#getConfirmationTime()}.
	 */
	@Test
	public void testGetConfirmationTimeBeforeConfirmingSeat() {
		
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getDepartureTime()}.
	 */
	@Test
	public void testGetDepartureTime() {
		
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getEnterQueueTime()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testGetEnterQueueTime() throws PassengerException {
		
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getExitQueueTime()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testGetExitQueueTimeAfterConfirmingASeat() throws PassengerException {
		
	}
	
	@Test
	public void testGetExitQueueTimeAfterBeingRefused() throws PassengerException {
		
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getPassID()}.
	 */
	@Test
	public void testGetPassID() {
		
	} 

	/**
	 * Test method for {@link asgn2Passengers.Passenger#isConfirmed()}.
	 */
	@Test
	public void testIsConfirmed() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#isFlown()}.
	 */
	@Test
	public void testIsFlown() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#isNew()}.
	 */
	@Test
	public void testIsNew() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#isQueued()}.
	 */
	@Test
	public void testIsQueued() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#isRefused()}.
	 */
	@Test
	public void testIsRefused() {
		fail("Not yet implemented");
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
	 * Test method for {@link asgn2Passengers.Passenger#toString()}.
	 */
	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasConfirmed()}.
	 */
	@Test
	public void testWasConfirmed() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasQueued()}.
	 */
	@Test
	public void testWasQueued() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#copyPassengerState(asgn2Passengers.Passenger)}.
	 */
	@Test
	public void testCopyPassengerState() {
		fail("Not yet implemented");
	}

}

/**
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asgn2Aircraft.A380;
import asgn2Aircraft.Aircraft;
import asgn2Aircraft.AircraftException;
import asgn2Aircraft.Bookings;
import asgn2Passengers.Business;
import asgn2Passengers.Economy;
import asgn2Passengers.First;
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;
import asgn2Passengers.Premium;

/**
 * This class represents a unit test class for the abstract Aircraft class tested through the A380 subclass as part of the
 * AircraftSimulator Project.
 * 
 * @author Vincenzo Iandolo - n9172912
 * @version 1.0
 *
 */
public class A380Tests {

	/**
	 * @throws java.lang.Exception
	 *
	 */
	
	// constants
	private static final String TEST_FLIGHT_CODE = "LAX123";
	private static final int TEST_BOOKING_TIME = 5;
	private static final int TEST_DEPART_TIME = 20;
	private static final int TEST_CONFIRM_TIME = 10;
	private static final int TEST_CANCELLATION_TIME = 10;
	private static final int GET_TEST_NUM_PASS_PASSENGER_COUNT = 4;
	
	// test objects
	private Aircraft fieldTestObject;
	private Aircraft exceptionTestObject;
	private Aircraft cancelBookingTest;
	private Aircraft generalTester;
	private Aircraft getTestNumPassClass;
	private Passenger testPassenger;
	
	private Passenger testPassengerTwo;
	private Passenger testPassengerThree;
	private Passenger testPassengerFour;
	
	private Passenger FirstPassenger;
	private Passenger BusinessPassenger;
	private Passenger PremEconPassenger;
	private Passenger EconPassenger;
	
	@Before
	public void setUp() throws Exception {
		// for getter testing, create aircraft and populate it with one of each passenger
		getTestNumPassClass = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		
		FirstPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		BusinessPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		PremEconPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		EconPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		
		getTestNumPassClass.confirmBooking(FirstPassenger, TEST_CONFIRM_TIME);
		getTestNumPassClass.confirmBooking(BusinessPassenger, TEST_CONFIRM_TIME);
		getTestNumPassClass.confirmBooking(PremEconPassenger, TEST_CONFIRM_TIME);
		getTestNumPassClass.confirmBooking(EconPassenger, TEST_CONFIRM_TIME);
	}
	
	// CONSTRUCTOR TESTS
		
		// constructor exception tests
	
	@Test
	public void ValidParametersExceptionTest() throws AircraftException {
		exceptionTestObject = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME);
		assertNotNull(exceptionTestObject);
	}

	@Test (expected = AircraftException.class)  
	public void IsNullFlightCodeExceptionTest() throws AircraftException {
		exceptionTestObject = new A380(null, TEST_DEPART_TIME);
	}
	
	@Test (expected = AircraftException.class)
	public void DepartTimeLessThanZeroExceptionTest() throws AircraftException {
		exceptionTestObject = new A380(TEST_FLIGHT_CODE, -1);
	}
	
	@Test (expected = AircraftException.class)
	public void DepartTimeZeroExceptionTest() throws AircraftException {
		exceptionTestObject = new A380(TEST_FLIGHT_CODE, 0);
	}
	
	@Test (expected = AircraftException.class)
	public void FirstLessThanZeroExceptionTest() throws AircraftException {
		exceptionTestObject = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, -1, 1, 1, 1 );
	}
	
	@Test (expected = AircraftException.class)
	public void BusinessLessThanZeroExceptionTest() throws AircraftException {
		exceptionTestObject = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, -1, 1, 1 );
	}
	
	@Test (expected = AircraftException.class)
	public void PremiumLessThanZeroExceptionTest() throws AircraftException {
		exceptionTestObject = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, -1, 1 );
	}
	
	@Test (expected = AircraftException.class)
	public void EconomyLessThanZeroExceptionTest() throws AircraftException {
		exceptionTestObject = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, -1 );
	}
	
	// CANCEL BOOKING TESTS
	
	@Test
	public void CancelBookingTestCheckBusinessNumberOfPassengersDecrementCount() throws PassengerException, AircraftException {
		// create aircraft
		cancelBookingTest = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		// create business passenger in confirmed state
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);				
		// add passenger to aircraft
		cancelBookingTest.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		// cancel booking
		cancelBookingTest.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);
		// check condition
		assertTrue(cancelBookingTest.getNumPassengers() == 0);
	}

	@Test
	public void CancelBookingTestCheckPremiumNumberOfPassengersDecrementCount() throws PassengerException, AircraftException {
		// create aircraft
		cancelBookingTest = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		// create premium passenger in confirmed state
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);				
		// add passenger to aircraft
		cancelBookingTest.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		// cancel booking
		cancelBookingTest.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);
		// check condition
		assertTrue(cancelBookingTest.getNumPassengers() == 0);
	}

	@Test
	public void CancelBookingTestCheckEconomyNumberOfPassengersDecrementCount() throws PassengerException, AircraftException {
		// create aircraft
		cancelBookingTest = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		// create economy passenger in confirmed state
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);				
		// add passenger to aircraft
		cancelBookingTest.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		// cancel booking
		cancelBookingTest.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);
		// check condition
		assertTrue(cancelBookingTest.getNumPassengers() == 0);
	}

	@Test
	public void CancelBookingTestCheckPassengerIsNewState() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		// cancel booking
		generalTester.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);				
		// check condition
		assertTrue(testPassenger.isNew());
	}

	@Test
	public void CancelBookingTestCheckPassengerIsConfirmedState() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		// cancel booking
		generalTester.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);				
		// check condition
		assertFalse(testPassenger.isConfirmed());
	}

	@Test
	public void CancelBookingTestCheckPassengerIsQueuedState() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		// cancel booking
		generalTester.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);				
		// check condition
		assertFalse(testPassenger.isQueued());
	}

	@Test
	public void CancelBookingTestCheckPassengerIsRefusedState() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		// cancel booking
		generalTester.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);				
		// check condition
		assertFalse(testPassenger.isRefused());
	}

	@Test
	public void CancelBookingTestCheckPassengerIsFlownState() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		// cancel booking
		generalTester.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);				
		// check condition
		assertFalse(testPassenger.isFlown());
	}

	@Test
	public void CancelBookingTestCheckPassengerWasConfirmedState() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		// cancel booking
		generalTester.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);				
		// check condition
		assertTrue(testPassenger.wasConfirmed());
	}

	@Test
	public void CancelBookingTestCheckPassengerWasQueuedState() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		// cancel booking
		generalTester.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);				
		// check condition
		assertFalse(testPassenger.wasQueued());
	}
	
	@Test
	public void CancelBookingTestCheckPassengerDecrementCount() throws PassengerException, AircraftException {
		// create aircraft
		cancelBookingTest = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// add passenger to aircraft
		cancelBookingTest.confirmBooking(testPassenger, TEST_CONFIRM_TIME);	
		// cancel booking
		cancelBookingTest.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);
		// check condition
		assertTrue(cancelBookingTest.getNumPassengers() == 0);
	}
	
	@Test
	public void CancelBookingTestCheckFirstDecrementCount() throws PassengerException, AircraftException {
		// create aircraft
		cancelBookingTest = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);				
		// add passenger to aircraft
		cancelBookingTest.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		// cancel booking
		cancelBookingTest.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);
		// check condition
		assertTrue(cancelBookingTest.getNumFirst() == 0);
	}
	
	@Test
	public void CancelBookingTestCheckBusinessDecrementCount() throws PassengerException, AircraftException {
		// create aircraft
		cancelBookingTest = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		// create passenger in confirmed state
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);				
		// add passenger to aircraft
		cancelBookingTest.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		// cancel booking
		cancelBookingTest.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);
		// check condition
		assertTrue(cancelBookingTest.getNumBusiness() == 0);
	}
	
	@Test
	public void CancelBookingTestCheckPremiumDecrementCount() throws PassengerException, AircraftException {
		// create aircraft
		cancelBookingTest = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		// create passenger in confirmed state
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);				
		// add passenger to aircraft
		cancelBookingTest.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		// cancel booking
		cancelBookingTest.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);
		// check condition
		assertTrue(cancelBookingTest.getNumPremium() == 0);
	}
	
	@Test
	public void CancelBookingTestCheckEconomyDecrementCount() throws PassengerException, AircraftException {
		// create aircraft
		cancelBookingTest = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		// create passenger in confirmed state
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);				
		// add passenger to aircraft
		cancelBookingTest.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		// cancel booking
		cancelBookingTest.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);
		// check condition
		assertTrue(cancelBookingTest.getNumEconomy() == 0);
	}
	
	
	
	@Test
	public void CancelBookingTestCheckForPassengerObject() throws PassengerException, AircraftException {
		// create aircraft
		cancelBookingTest = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// add passenger to aircraft
		cancelBookingTest.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// cancel booking
		cancelBookingTest.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);
		// check condition
		assertFalse(cancelBookingTest.getPassengers().contains(cancelBookingTest));
	}
	
	
	@Test (expected = AircraftException.class)
	public void PassengerNotInSeatingExceptionTest() throws AircraftException, PassengerException {
		// create aircraft
		cancelBookingTest = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );			
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		// DO NOT ADD TO AIRCRAFT SEATING
		// cancel booking
		cancelBookingTest.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);
	}
	
	// CONFIRM BOOKING TESTS
	
	@Test
	public void ConfirmBookingTestCheckObject() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		// check for containment
		assertTrue(generalTester.getPassengers().contains(testPassenger));
	}
	
	@Test
	public void ConfirmBookingTestCheckPassengerCount() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		// check for containment
		assertTrue(generalTester.getNumPassengers() == 1);
	}
	
	@Test
	public void ConfirmBookingTestCheckFirstClassPassengerCount() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);					
		// check for containment
		assertTrue(generalTester.getNumFirst() == 1);
	}
	
	@Test
	public void ConfirmBookingTestCheckBusinessClassPassengerCount() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		// check for containment
		assertTrue(generalTester.getNumBusiness() == 1);
	}
	
	@Test
	public void ConfirmBookingTestCheckEconomyClassPassengerCount() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		// check for containment
		assertTrue(generalTester.getNumEconomy() == 1);
	}
	
	@Test
	public void ConfirmBookingTestCheckPremiumClassPassengerCount() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		// check for containment
		assertTrue(generalTester.getNumPremium() == 1);
	}
	
	@Test (expected = AircraftException.class)
	public void NoSeatsAvailableExceptionTest() throws AircraftException, PassengerException {
		// create aircraft with no seats (it had one job)
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 0, 0 );	
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		// add passenger to aircraft, expecting exception
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// expect exception
	}
	
	@Test
	public void ConfirmBookingTestCheckBusinessNumberOfPassengersCount() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		// check condition
		assertTrue(generalTester.getNumPassengers() == 1);
	}

	@Test
	public void ConfirmBookingTestCheckPremiumNumberOfPassengersCount() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		// check condition
		assertTrue(generalTester.getNumPassengers() == 1);
	}

	@Test
	public void ConfirmBookingTestCheckEconomyNumberOfPassengersCount() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		// check condition
		assertTrue(generalTester.getNumPassengers() == 1);
	}

	@Test
	public void ConfirmBookingTestCheckPassengerIsConfirmedState() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		// check condition
		assertTrue(testPassenger.isConfirmed());
	}

	@Test
	public void ConfirmBookingTestCheckPassengerIsQueuedState() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		// check condition
		assertFalse(testPassenger.isQueued());
	}

	@Test
	public void ConfirmBookingTestCheckPassengerIsNewState() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		// check condition
		assertFalse(testPassenger.isNew());
	}
	
	@Test
	public void ConfirmBookingTestCheckPassengerIsRefusedState() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		// check condition
		assertFalse(testPassenger.isRefused());
	}

	@Test
	public void ConfirmBookingTestCheckPassengerIsFlownState() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		// check condition
		assertFalse(testPassenger.isFlown());
	}

	@Test
	public void ConfirmBookingTestCheckPassengerWasConfirmedState() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		// check condition
		assertTrue(testPassenger.wasConfirmed());
	}

	@Test
	public void ConfirmBookingTestCheckPassengerWasQueuedState() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		// check condition
		assertFalse(testPassenger.wasQueued());
	}
	
	// FLIGHT EMPTY TESTS
	
	@Test
	public void FlightEmptyEmptyTest() throws AircraftException {
		// create aircraft that defaults to empty
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		assertTrue(generalTester.flightEmpty());
	}
	
	@Test
	public void FlightEmptyNotEmptyTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );			
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// check condition
		assertFalse(generalTester.flightEmpty());
	}
	
	@Test
	public void FlightEmptyNotEmptyTestUsingBusinessClassPassenger() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );			
		// create passenger in confirmed state
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// check condition
		assertFalse(generalTester.flightEmpty());
	}

	@Test
	public void FlightEmptyNotEmptyTestUsingPremiumClassPassenger() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );			
		// create passenger in confirmed state
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// check condition
		assertFalse(generalTester.flightEmpty());
	}

	@Test
	public void FlightEmptyNotEmptyTestUsingEconomyClassPassenger() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );			
		// create passenger in confirmed state
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// check condition
		assertFalse(generalTester.flightEmpty());
	}

	@Test
	public void FlightEmptyNotEmptyTestUsingMultiplePassengers() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );			
		// create passenger in confirmed state
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerTwo = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerTwo, TEST_CONFIRM_TIME);		
		// check condition
		assertFalse(generalTester.flightEmpty());
	}

	@Test
	public void FlightEmptyNotEmptyTestUsingMultiplePassengersOfEachClass() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );			
		// create passengers in confirmed states
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerTwo = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerThree = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerFour = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerTwo, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerThree, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerFour, TEST_CONFIRM_TIME);		
		// check condition
		assertFalse(generalTester.flightEmpty());
	}
	
	// FLIGHT FULL TESTS
	
	@Test
	public void FlightFullNotFullTest() throws AircraftException {
		// create aircraft that defaults to empty
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		assertFalse(generalTester.flightFull());
	}
	
	@Test
	public void FlightFullFullTest() throws AircraftException, PassengerException {
		// create aircraft with single available seat
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 0, 0, 0 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		// add passenger to single available seat of aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// check condition
		assertTrue(generalTester.flightFull());
	}
	
	@Test
	public void FlightFullAlmostFullTest() throws AircraftException, PassengerException {
		// create aircraft with single available seat
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerTwo = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerThree = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		// add passenger to single available seat of aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerTwo, TEST_CONFIRM_TIME);	
		generalTester.confirmBooking(testPassengerThree, TEST_CONFIRM_TIME);		
		// check condition
		assertFalse(generalTester.flightFull());
	}

	@Test
	public void FlightFullFullTestUsingEachClassOfPassenger() throws AircraftException, PassengerException {
		// create aircraft with single available seat
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerTwo = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerThree = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerFour = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		// add passenger to single available seat of aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerTwo, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerThree, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerFour, TEST_CONFIRM_TIME);		
		// check condition
		assertTrue(generalTester.flightFull());
	}

	@Test
	public void FlightFullNotFullTestUsingEachClassOfPassenger() throws AircraftException, PassengerException {
		// create aircraft with single available seat
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 2, 3 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerTwo = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerThree = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerFour = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		// add passenger to single available seat of aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerTwo, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerThree, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerFour, TEST_CONFIRM_TIME);		
		// check condition
		assertFalse(generalTester.flightFull());
	}
	
	
	// FLY PASSENGERS TESTS
	
	@Test
	public void FlyPassengersCorrectStateChangeTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		// fly passengers
		generalTester.flyPassengers(TEST_DEPART_TIME);
				
		// fetch first (and only) passenger in passenger list and check it's state
		assertTrue(generalTester.getPassengers().get(0).isFlown());
	}
	
	@Test
	public void FlyPassengersEmptySeatingTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		// fly passengers
		generalTester.flyPassengers(TEST_DEPART_TIME);		
		// fetch first (and only) passenger in passenger list and check it's state
		assertTrue(generalTester.getPassengers().isEmpty());
	}
	
	public void FlyPassengersCorrectStateChangeIsNewTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		// fly passengers
		generalTester.flyPassengers(TEST_DEPART_TIME);		
		// fetch first (and only) passenger in passenger list and check it's state
		assertFalse(generalTester.getPassengers().get(0).isNew());
	}

	public void FlyPassengersCorrectStateChangeIsConfirmedTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		// fly passengers
		generalTester.flyPassengers(TEST_DEPART_TIME);	
		// fetch first (and only) passenger in passenger list and check it's state
		assertFalse(generalTester.getPassengers().get(0).isConfirmed());
	}

	public void FlyPassengersCorrectStateChangeIsQueuedTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		// fly passengers
		generalTester.flyPassengers(TEST_DEPART_TIME);	
		// fetch first (and only) passenger in passenger list and check it's state
		assertFalse(generalTester.getPassengers().get(0).isQueued());
	}

	public void FlyPassengersCorrectStateChangeIsRefusedTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		// fly passengers
		generalTester.flyPassengers(TEST_DEPART_TIME);	
		// fetch first (and only) passenger in passenger list and check it's state
		assertFalse(generalTester.getPassengers().get(0).isRefused());
	}
	
	// GET BOOKINGS TESTS
	
	@Test 
	public void GetBookingsTestNumEconomy() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 0, 1 );		
		// create passenger
		Passenger EconomyBookingTest = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(EconomyBookingTest, TEST_CONFIRM_TIME);
		assertEquals(1, generalTester.getBookings().getNumEconomy());
	}
	
	@Test 
	public void GetBookingsTestNumPremium() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 1, 0 );		
		// create passenger
		Passenger PremiumBookingTest = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(PremiumBookingTest, TEST_CONFIRM_TIME);
		assertEquals(1, generalTester.getBookings().getNumPremium());
	}
	
	@Test 
	public void GetBookingsTestNumBusiness() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 1, 0, 0 );		
		// create passenger
		Passenger BusinessBookingTest = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(BusinessBookingTest, TEST_CONFIRM_TIME);
		assertEquals(1, generalTester.getBookings().getNumBusiness());
	}
	
	@Test 
	public void GetBookingsTestNumFirst() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 0, 0, 0 );		
		// create passenger
		Passenger FirstBookingTest = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(FirstBookingTest, TEST_CONFIRM_TIME);
		assertEquals(1, generalTester.getBookings().getNumFirst());
	}
	
	@Test 
	public void GetBookingsTestNumAvailable() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 2, 1 );		
		// create passengers
		Passenger FirstBookingTest = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger EconomyBookingTest = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger PremiumBookingTest = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger BusinessBookingTest = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// add to flight
		generalTester.confirmBooking(FirstBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(EconomyBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(PremiumBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(BusinessBookingTest, TEST_CONFIRM_TIME);
		// test
		assertEquals(1, generalTester.getBookings().getAvailable());
	}
	
	@Test 
	public void GetBookingsTestNumTotal() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		// create passengers
		Passenger FirstBookingTest = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger EconomyBookingTest = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger PremiumBookingTest = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger BusinessBookingTest = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// add to flight
		generalTester.confirmBooking(FirstBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(EconomyBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(PremiumBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(BusinessBookingTest, TEST_CONFIRM_TIME);
		// test
		assertEquals(4, generalTester.getBookings().getTotal());
	}
	
	@Test 
	public void GetBookingsTestNumAvailableMultipleSeatsAvailable() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 2, 2, 2, 3 );		
		// create passengers
		Passenger FirstBookingTest = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger EconomyBookingTest = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger PremiumBookingTest = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger BusinessBookingTest = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// add to flight
		generalTester.confirmBooking(FirstBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(EconomyBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(PremiumBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(BusinessBookingTest, TEST_CONFIRM_TIME);
		// test
		assertEquals(5, generalTester.getBookings().getAvailable());
	}

	@Test 
	public void GetBookingsTestNumTotalWithSeatsRemaining() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 2 );		
		// create passengers
		Passenger FirstBookingTest = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger EconomyBookingTest = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger PremiumBookingTest = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger BusinessBookingTest = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// add to flight
		generalTester.confirmBooking(FirstBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(EconomyBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(PremiumBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(BusinessBookingTest, TEST_CONFIRM_TIME);
		// test
		assertEquals(4, generalTester.getBookings().getTotal());
	}
	
	// GET NUM BUSINESS TESTS
	
	@Test
	public void GetNumBusinessClassPassengersTest() {
		assertEquals(1, getTestNumPassClass.getNumBusiness());
	}
	
	// GET NUM ECONOMY TESTS
	
	@Test
	public void GetNumEcomonyClassPassengersTest() {
		assertEquals(1, getTestNumPassClass.getNumEconomy());
	}
	
	// GET NUM FIRST TESTS
	
	@Test
	public void GetNumFirstClassPassengersTest() {
		assertEquals(1, getTestNumPassClass.getNumFirst());
	}
	
	// GET NUM PASSENGERS TESTS
	
	@Test
	public void GetNumPassengersTest() {
		assertEquals(GET_TEST_NUM_PASS_PASSENGER_COUNT, getTestNumPassClass.getNumPassengers());
	}
	
	@Test
	public void GetPassengersTestMultiplePassengers() throws AircraftException, PassengerException {
		List<Passenger> seats = new ArrayList<Passenger>();
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerTwo = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerThree = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerFour = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		// add passengers to available seats of aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerTwo, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerThree, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerFour, TEST_CONFIRM_TIME);		
		// add passengers to comparison list
		seats.add(testPassenger);
		seats.add(testPassengerTwo);
		seats.add(testPassengerThree);
		seats.add(testPassengerFour);
		// check condition
		assertEquals(seats.toString(), generalTester.getPassengers().toString());
	}
	
	// GET NUM PREMIUM TESTS
	
	@Test
	public void GetNumPremiumClassPassengersTest() {
		assertEquals(1, getTestNumPassClass.getNumPremium());
	}
	
	// GET PASSENGERS TESTS
	
	@Test
	public void GetPassengersTest() throws AircraftException, PassengerException {
		List<Passenger> seats = new ArrayList<Passenger>();
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		// add passenger to single available seat of aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// add passenger to comparison list
		seats.add(testPassenger);
		// check condition
		assertEquals(seats.toString(), generalTester.getPassengers().toString());
	}
	
	@Test
	public void GetPassengersAfterCancelledPassengerTest() throws AircraftException, PassengerException {
		List<Passenger> seats = new ArrayList<Passenger>();
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		// add passenger to single available seat of aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// remove it again
		generalTester.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);
		// check condition
		assertEquals(seats.toString(), generalTester.getPassengers().toString());
	}
	
	// HAS PASSENGER TESTS
	
	@Test
	public void HasPassengerTrueTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		// add passenger to single available seat of aircraft
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// has passenger
		assertTrue(generalTester.hasPassenger(testPassenger));
	}
	
	@Test
	public void HasPassengerFalseTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		// do not add to aircraft
		// has passenger
		assertFalse(generalTester.hasPassenger(testPassenger));
	}
	
	// SEATS AVAILABLE TESTS
	
	@Test
	public void SeatsAvailableTrueFirstTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create first passenger
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		// use passenger as parameter for checking seats
		assertTrue(generalTester.seatsAvailable(testPassenger));
		
	}
	
	@Test
	public void SeatsAvailableFalseFirstTest() throws AircraftException, PassengerException {
		// create empty aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 0, 0 );
		// create first passenger
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		// use passenger as parameter for checking seats
		assertFalse(generalTester.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableTrueBusinessTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create first passenger
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		// use passenger as parameter for checking seats
		assertTrue(generalTester.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableFalseBusinessTest() throws PassengerException, AircraftException {
		// create empty aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 0, 0 );
		// create first passenger
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		// use passenger as parameter for checking seats
		assertFalse(generalTester.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableTruePremiumTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create first passenger
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		// use passenger as parameter for checking seats
		assertTrue(generalTester.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableFalsePremiumTest() throws PassengerException, AircraftException {
		// create empty aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 0, 0 );
		// create first passenger
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		// use passenger as parameter for checking seats
		assertFalse(generalTester.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableTrueEconomyTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create first passenger
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		// use passenger as parameter for checking seats
		assertTrue(generalTester.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableFalseEconomyTest() throws AircraftException, PassengerException {
		// create empty aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 0, 0 );
		// create first passenger
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		// use passenger as parameter for checking seats
		assertFalse(generalTester.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableSeatFullFirstTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create first passenger
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// add passenger to the flight
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);	
		// use passenger as parameter for checking seats
		assertFalse(generalTester.seatsAvailable(testPassenger));	
	}

	@Test
	public void SeatsAvailableVacantSeatsFirstTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 2, 1, 1, 1 );
		// create first passenger
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// add passenger to the flight
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);	
		// use passenger as parameter for checking seats
		assertTrue(generalTester.seatsAvailable(testPassenger));	
	}

	@Test
	public void SeatsAvailableSeatFullBusinessTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create first passenger
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// add passenger to the flight
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);	
		// use passenger as parameter for checking seats
		assertFalse(generalTester.seatsAvailable(testPassenger));	
	}

	@Test
	public void SeatsAvailableVacantSeatsBusinessTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 2, 1, 1 );
		// create first passenger
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// add passenger to the flight
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);	
		// use passenger as parameter for checking seats
		assertTrue(generalTester.seatsAvailable(testPassenger));	
	}

	@Test
	public void SeatsAvailableSeatFullPremiumTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create first passenger
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// add passenger to the flight
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);	
		// use passenger as parameter for checking seats
		assertFalse(generalTester.seatsAvailable(testPassenger));	
	}

	@Test
	public void SeatsAvailableVacantSeatsPremiumTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 2, 1 );
		// create first passenger
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// add passenger to the flight
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);	
		// use passenger as parameter for checking seats
		assertTrue(generalTester.seatsAvailable(testPassenger));	
	}

	@Test
	public void SeatsAvailableSeatFullEconomyTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		// create first passenger
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// add passenger to the flight
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);	
		// use passenger as parameter for checking seats
		assertFalse(generalTester.seatsAvailable(testPassenger));	
	}

	@Test
	public void SeatsAvailableVacantSeatsEconomyTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 2 );
		// create first passenger
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// add passenger to the flight
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);	
		// use passenger as parameter for checking seats
		assertTrue(generalTester.seatsAvailable(testPassenger));	
	}
	
	// UPGRADE BOOKING TESTS
	// these tests may need revising, the following two will be mirrored for Premium and Business
	
	@Test
	public void UpgradeBookingsFromEconomyTestPremiumCounter() throws AircraftException, PassengerException {
		// create aircraft with upgrade space
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 1, 1 );
		// create economy passenger
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// confirm booking
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// upgrade to Premium
		generalTester.upgradeBookings();
		// if successful number of Premium passengers should be one
		assertEquals(1, generalTester.getNumPremium());
	}
	
	@Test
	public void UpgradeBookingsFromEconomyTestContainment() throws AircraftException, PassengerException {
		// create aircraft with upgrade space
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 1, 1 );
		// create economy passenger
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// confirm booking
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// upgrade to Premium
		generalTester.upgradeBookings();
		// passenger should still remain in seating
		assertTrue(generalTester.getPassengers().contains(testPassenger));
	}
	
	@Test
	public void UpgradeBookingsFromPremiumTestCounter() throws AircraftException, PassengerException {
		// create aircraft with upgrade space
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 1, 1, 0 );
		// create premium passenger
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// confirm booking
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// upgrade to Business
		generalTester.upgradeBookings();
		// if successful number of Business passengers should be one
		assertEquals(1, generalTester.getNumBusiness());
	}
	
	@Test
	public void UpgradeBookingsFromPremiumTestContainment() throws AircraftException, PassengerException {
		// create aircraft with upgrade space
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 1, 1, 0 );
		// create premium passenger
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// confirm booking
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// upgrade to Business 
		generalTester.upgradeBookings();
		// passenger should still remain in seating
		assertTrue(generalTester.getPassengers().contains(testPassenger));
	}
	
	@Test
	public void UpgradeBookingsFromBusinessTestFirstCounter() throws AircraftException, PassengerException {
		// create aircraft with upgrade space
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 0, 0 );
		// create business passenger
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// confirm booking
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// upgrade to First
		generalTester.upgradeBookings();
		// if successful number of Business passengers should be one
		assertEquals(1, generalTester.getNumFirst());
	}
	
	@Test
	public void UpgradeBookingsFromBusinessTestContainment() throws AircraftException, PassengerException {
		// create aircraft with upgrade space
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 0, 0 );
		// create business passenger
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// confirm booking
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// upgrade to First
		generalTester.upgradeBookings();
		// passenger should still remain in seating
		assertTrue(generalTester.getPassengers().contains(testPassenger));
	}
	
	@Test
	public void UpgradeBookingsFromEconomyTestEconomyCounter() throws AircraftException, PassengerException {
		// create aircraft with upgrade space
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 1, 1 );
		// create first passenger
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// confirm booking
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// upgrade to Premium
		generalTester.upgradeBookings();
		// if successful number of Economy passengers should be zero
		assertEquals(0, generalTester.getNumEconomy());
	}

	@Test
	public void UpgradeBookingsFromEconomyTestCheckContainmentAndInstanceType() throws AircraftException, PassengerException {
		// create aircraft with upgrade space
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 1, 1 );
		// create economy passenger
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// confirm booking
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// upgrade to Premium
		generalTester.upgradeBookings();
		// passenger should be of a Premium instance
		assertTrue(generalTester.getPassengers().get(0) instanceof Premium);
	}

	@Test
	public void UpgradeBookingsFromPremiumTestPremiumCounter() throws AircraftException, PassengerException {
		// create aircraft with upgrade space
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 1, 1, 0 );
		// create premium passenger
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// confirm booking
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// upgrade to Premium
		generalTester.upgradeBookings();
		// if successful number of Premium passengers should be zero
		assertEquals(0, generalTester.getNumPremium());
	}

	@Test
	public void UpgradeBookingsFromPremiumTestContainmentAndInstanceType() throws AircraftException, PassengerException {
		// create aircraft with upgrade space
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 1, 1, 0 );
		// create premium passenger
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// confirm booking
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// upgrade to Business 
		generalTester.upgradeBookings();
		// passenger should be of a Business instance
		assertTrue(generalTester.getPassengers().get(0) instanceof Business);
	}

	@Test
	public void UpgradeBookingsFromBusinessTestBusinessCounter() throws AircraftException, PassengerException {
		// create aircraft with upgrade space
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 0, 0 );
		// create business passenger
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// confirm booking
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// upgrade to First
		generalTester.upgradeBookings();
		// if successful number of Business passengers should be zero
		assertEquals(0, generalTester.getNumBusiness());
	}

	@Test
	public void UpgradeBookingsFromBusinessTestContainmentAndInstanceType() throws AircraftException, PassengerException {
		// create aircraft with upgrade space
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 0, 0 );
		// create first passenger
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// confirm booking
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// upgrade to First
		generalTester.upgradeBookings();
		// passenger should be of a First instance
		assertTrue(generalTester.getPassengers().get(0) instanceof First);
	}

	@Test
	public void UpgradeBookingsFromFirstTestFirstCounter() throws AircraftException, PassengerException {
		// create aircraft with upgrade space
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 0, 0 );
		// create business passenger
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// confirm booking
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// upgrade to First
		generalTester.upgradeBookings();
		// if successful number of First passengers should remain as one
		assertEquals(1, generalTester.getNumFirst());
	}

	@Test
	public void UpgradeBookingsFromFirstTestContainment() throws AircraftException, PassengerException {
		// create aircraft with upgrade space
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 0, 0 );
		// create first passenger
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// confirm booking
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// upgrade to First
		generalTester.upgradeBookings();
		// passenger should still remain in seating
		assertTrue(generalTester.getPassengers().contains(testPassenger));
	}

	@Test
	public void UpgradeBookingsFromFirstTestContainmentAndInstanceType() throws AircraftException, PassengerException {
		// create aircraft with upgrade space
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 0, 0 );
		// create first passenger
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// confirm booking
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// upgrade to First
		generalTester.upgradeBookings();
		// passenger should remain as a First instance
		assertTrue(generalTester.getPassengers().get(0) instanceof First);
	}
}
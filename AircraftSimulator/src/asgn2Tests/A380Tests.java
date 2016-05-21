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
 * @author Michael Leontieff - n9455396
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
	
	// UPGRADE BOOKING TESTS
	// these tests may need revising, the following two will be mirrored for Premium and Business
	
	@Test
	public void UpgradeBookingsFromEconomyTestCounter() throws AircraftException, PassengerException {
		// create aircraft with upgrade space
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 1, 1 );
		// create first passenger
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
		// create first passenger
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// confirm booking
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// upgrade to Premium
		generalTester.upgradeBookings();
		// passenger should still remain in seating
		assertEquals(true, generalTester.getPassengers().contains(testPassenger));
	}
	
	@Test
	public void UpgradeBookingsFromPremiumTestCounter() throws AircraftException, PassengerException {
		// create aircraft with upgrade space
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 1, 1, 0 );
		// create first passenger
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// confirm booking
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// upgrade to Premium
		generalTester.upgradeBookings();
		// if successful number of Business passengers should be one
		assertEquals(1, generalTester.getNumBusiness());
	}
	
	@Test
	public void UpgradeBookingsFromPremiumTestContainment() throws AircraftException, PassengerException {
		// create aircraft with upgrade space
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 1, 1, 0 );
		// create first passenger
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// confirm booking
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// upgrade to Premium
		generalTester.upgradeBookings();
		// passenger should still remain in seating
		assertEquals(true, generalTester.getPassengers().contains(testPassenger));
	}
	
	@Test
	public void UpgradeBookingsFromBusinessTestCounter() throws AircraftException, PassengerException {
		// create aircraft with upgrade space
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 0, 0 );
		// create first passenger
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// confirm booking
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// upgrade to Premium
		generalTester.upgradeBookings();
		// if successful number of Business passengers should be one
		assertEquals(1, generalTester.getNumFirst());
	}
	
	@Test
	public void UpgradeBookingsFromBusinessTestContainment() throws AircraftException, PassengerException {
		// create aircraft with upgrade space
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 0, 0 );
		// create first passenger
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		// confirm booking
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// upgrade to Premium
		generalTester.upgradeBookings();
		// passenger should still remain in seating
		assertEquals(true, generalTester.getPassengers().contains(testPassenger));
	}
	
	
	
	
	

	
	
	
	

}
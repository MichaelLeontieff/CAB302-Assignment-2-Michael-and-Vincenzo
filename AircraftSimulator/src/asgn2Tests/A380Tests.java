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
 * @author michaelleontieff
 *
 */
public class A380Tests {

	/**
	 * @throws java.lang.Exception
	 *
	 */
	
	// constants
	private final String testFlightCode = "LAX123";
	private final int testBookingTime = 5;
	private final int testDepartTime = 20;
	private final int testConfirmTime = 10;
	
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
		getTestNumPassClass = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );
		
		FirstPassenger = new First(testBookingTime, testDepartTime);
		BusinessPassenger = new Business(testBookingTime, testDepartTime);
		PremEconPassenger = new Premium(testBookingTime, testDepartTime);
		EconPassenger = new Economy(testBookingTime, testDepartTime);	
		
		getTestNumPassClass.confirmBooking(FirstPassenger, testConfirmTime);
		getTestNumPassClass.confirmBooking(BusinessPassenger, testConfirmTime);
		getTestNumPassClass.confirmBooking(PremEconPassenger, testConfirmTime);
		getTestNumPassClass.confirmBooking(EconPassenger, testConfirmTime);
	}
	
	// CONSTRUCTOR TESTS
		
		// constructor exception tests
	
	@Test
	public void ValidParametersExceptionTest() throws AircraftException {
		exceptionTestObject = new A380(testFlightCode, testDepartTime);
	}

	@Test (expected = AircraftException.class)  
	public void IsNullFlightCodeExceptionTest() throws AircraftException {
		exceptionTestObject = new A380(null, testDepartTime);
	}
	
	@Test (expected = AircraftException.class)
	public void DepartTimeLessThanZeroExceptionTest() throws AircraftException {
		exceptionTestObject = new A380(testFlightCode, -1);
	}
	
	@Test (expected = AircraftException.class)
	public void DepartTimeZeroExceptionTest() throws AircraftException {
		exceptionTestObject = new A380(testFlightCode, 0);
	}
	
	@Test (expected = AircraftException.class)
	public void FirstLessThanZeroExceptionTest() throws AircraftException {
		exceptionTestObject = new A380(testFlightCode, testDepartTime, -1, 1, 1, 1 );
	}
	
	@Test (expected = AircraftException.class)
	public void BusinessLessThanZeroExceptionTest() throws AircraftException {
		exceptionTestObject = new A380(testFlightCode, testDepartTime, 1, -1, 1, 1 );
	}
	
	@Test (expected = AircraftException.class)
	public void PremiumLessThanZeroExceptionTest() throws AircraftException {
		exceptionTestObject = new A380(testFlightCode, testDepartTime, 1, 1, -1, 1 );
	}
	
	@Test (expected = AircraftException.class)
	public void EconomyLessThanZeroExceptionTest() throws AircraftException {
		exceptionTestObject = new A380(testFlightCode, testDepartTime, 1, 1, 1, -1 );
	}
	
	// CANCEL BOOKING TESTS
	
	@Test
	public void CancelBookingTestCheckPassengerDecrementCount() throws PassengerException, AircraftException {
		// create aircraft
		cancelBookingTest = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(testBookingTime, testDepartTime);
		// add passenger to aircraft
		cancelBookingTest.confirmBooking(testPassenger, 10);	
		// cancel booking
		cancelBookingTest.cancelBooking(testPassenger, 10);
		// check condition
		assertTrue(cancelBookingTest.getNumPassengers() == 0);
	}
	
	@Test
	public void CancelBookingTestCheckFirstDecrementCount() throws PassengerException, AircraftException {
		// create aircraft
		cancelBookingTest = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );		
		// create passenger in confirmed state
		testPassenger = new First(testBookingTime, testDepartTime);				
		// add passenger to aircraft
		cancelBookingTest.confirmBooking(testPassenger, 10);					
		// cancel booking
		cancelBookingTest.cancelBooking(testPassenger, 10);
		// check condition
		assertTrue(cancelBookingTest.getNumFirst() == 0);
	}
	
	@Test
	public void CancelBookingTestCheckBusinessDecrementCount() throws PassengerException, AircraftException {
		// create aircraft
		cancelBookingTest = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );		
		// create passenger in confirmed state
		testPassenger = new Business(testBookingTime, testDepartTime);				
		// add passenger to aircraft
		cancelBookingTest.confirmBooking(testPassenger, 10);					
		// cancel booking
		cancelBookingTest.cancelBooking(testPassenger, 10);
		// check condition
		assertTrue(cancelBookingTest.getNumBusiness() == 0);
	}
	
	@Test
	public void CancelBookingTestCheckPremiumDecrementCount() throws PassengerException, AircraftException {
		// create aircraft
		cancelBookingTest = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );		
		// create passenger in confirmed state
		testPassenger = new Premium(testBookingTime, testDepartTime);				
		// add passenger to aircraft
		cancelBookingTest.confirmBooking(testPassenger, 10);					
		// cancel booking
		cancelBookingTest.cancelBooking(testPassenger, 10);
		// check condition
		assertTrue(cancelBookingTest.getNumPremium() == 0);
	}
	
	@Test
	public void CancelBookingTestCheckEconomyDecrementCount() throws PassengerException, AircraftException {
		// create aircraft
		cancelBookingTest = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );		
		// create passenger in confirmed state
		testPassenger = new Economy(testBookingTime, testDepartTime);				
		// add passenger to aircraft
		cancelBookingTest.confirmBooking(testPassenger, 10);					
		// cancel booking
		cancelBookingTest.cancelBooking(testPassenger, 10);
		// check condition
		assertTrue(cancelBookingTest.getNumEconomy() == 0);
	}
	
	
	
	@Test
	public void CancelBookingTestCheckForPassengerObject() throws PassengerException, AircraftException {
		// create aircraft
		cancelBookingTest = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(testBookingTime, testDepartTime);
		// add passenger to aircraft
		cancelBookingTest.confirmBooking(testPassenger, 10);	
		// cancel booking
		cancelBookingTest.cancelBooking(testPassenger, 10);
		// check condition
		assertFalse(cancelBookingTest.getPassengers().contains(cancelBookingTest));
	}
	
	
	@Test (expected = AircraftException.class)
	public void PassengerNotInSeatingExceptionTest() throws AircraftException, PassengerException {
		// create aircraft
		cancelBookingTest = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );			
		// create passenger in confirmed state
		testPassenger = new First(testBookingTime, testDepartTime);	
		// DO NOT ADD TO AIRCRAFT SEATING
		// cancel booking
		cancelBookingTest.cancelBooking(testPassenger, 10);
	}
	
	// CONFIRM BOOKING TESTS
	
	@Test
	public void ConfirmBookingTestCheckObject() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(testBookingTime, testDepartTime);	
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, 10);		
		// check for containment
		assertTrue(generalTester.getPassengers().contains(testPassenger));
	}
	
	@Test
	public void ConfirmBookingTestCheckPassengerCount() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(testBookingTime, testDepartTime);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, 10);			
		// check for containment
		assertTrue(generalTester.getNumPassengers() == 1);
	}
	
	@Test
	public void ConfirmBookingTestCheckFirstClassPassengerCount() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );		
		// create passenger in confirmed state
		testPassenger = new First(testBookingTime, testDepartTime);		
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, 10);				
		// check for containment
		assertTrue(generalTester.getNumFirst() == 1);
	}
	
	@Test
	public void ConfirmBookingTestCheckBusinessClassPassengerCount() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new Business(testBookingTime, testDepartTime);
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, 10);			
		// check for containment
		assertTrue(generalTester.getNumBusiness() == 1);
	}
	
	@Test
	public void ConfirmBookingTestCheckEconomyClassPassengerCount() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new Economy(testBookingTime, testDepartTime);
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, 10);			
		// check for containment
		assertTrue(generalTester.getNumEconomy() == 1);
	}
	
	@Test
	public void ConfirmBookingTestCheckPremiumClassPassengerCount() throws PassengerException, AircraftException {
		// create aircraft
		generalTester = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new Premium(testBookingTime, testDepartTime);
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, 10);			
		// check for containment
		assertTrue(generalTester.getNumPremium() == 1);
	}
	
	@Test (expected = AircraftException.class)
	public void NoSeatsAvailableExceptionTest() throws AircraftException, PassengerException {
		// create aircraft with no seats (it had one job)
		generalTester = new A380(testFlightCode, testDepartTime, 0, 0, 0, 0 );	
		// create passenger in confirmed state
		testPassenger = new First(testBookingTime, testDepartTime);	
		// add passenger to aircraft, expecting exception
		generalTester.confirmBooking(testPassenger, 10);	
		// expect exception
	}
	
	// FLIGHT EMPTY TESTS
	
	@Test
	public void FlightEmptyEmptyTest() throws AircraftException {
		// create aircraft that defaults to empty
		generalTester = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );
		assertTrue(generalTester.flightEmpty());
	}
	
	@Test
	public void FlightEmptyNotEmptyTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );			
		// create passenger in confirmed state
		testPassenger = new First(testBookingTime, testDepartTime);			
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, 10);	
		// check condition
		assertFalse(generalTester.flightEmpty());
	}
	
	// FLIGHT FULL TESTS
	
	@Test
	public void FlightFullNotFullTest() throws AircraftException {
		// create aircraft that defaults to empty
		generalTester = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );
		assertFalse(generalTester.flightFull());
	}
	
	@Test
	public void FlightFullFullTest() throws AircraftException, PassengerException {
		// create aircraft with single available seat
		generalTester = new A380(testFlightCode, testDepartTime, 1, 0, 0, 0 );
		// create passenger in confirmed state
		testPassenger = new First(testBookingTime, testDepartTime);	
		// add passenger to single available seat of aircraft
		generalTester.confirmBooking(testPassenger, 10);	
		// check condition
		assertTrue(generalTester.flightFull());
	}
	
	
	// FLY PASSENGERS TESTS
	
	@Test
	public void FlyPassengersCorrectStateChangeTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );		
		// create passenger in confirmed state
		testPassenger = new First(testBookingTime, testDepartTime);			
		// add passenger to aircraft
		generalTester.confirmBooking(testPassenger, 10);		
		// fly passengers
		generalTester.flyPassengers(15);
				
		// fetch first (and only) passenger in passenger list and check it's state
		assertTrue(generalTester.getPassengers().get(0).isFlown());
	}
	
	@Test
	public void FlyPassengersEmptySeatingTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );		
		// create passenger in confirmed state
		testPassenger = new First(testBookingTime, testDepartTime);			
		// fly passengers
		generalTester.flyPassengers(15);		
		// fetch first (and only) passenger in passenger list and check it's state
		assertTrue(generalTester.getPassengers().isEmpty());
	}
	
	// GET BOOKINGS TESTS
	
	@Test 
	public void GetBookingsTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );		
		Bookings comparison = new Bookings(1, 0, 0, 1, 2, 2);
		// create passengers
		Passenger FirstBookingTest = new First(testBookingTime, testDepartTime);
		Passenger EconomyBookingTest = new Economy(testBookingTime, testDepartTime);
		generalTester.confirmBooking(FirstBookingTest, testConfirmTime);
		generalTester.confirmBooking(EconomyBookingTest, testConfirmTime);
		String comparator = comparison.toString();
		String comparatorTwo = generalTester.getBookings().toString();
		assertEquals(comparator, comparatorTwo);
	// TODO fix issue of comparing memory references instead of actual value
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
		assertEquals(4, getTestNumPassClass.getNumPassengers());
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
		generalTester = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(testBookingTime, testDepartTime);	
		// add passenger to single available seat of aircraft
		generalTester.confirmBooking(testPassenger, 10);	
		// add passenger to comparison list
		seats.add(testPassenger);
		// check condition
		assertEquals(seats.toString(), generalTester.getPassengers().toString());
	}
	
	@Test
	public void GetPassengersAfterCancelledPassengerTest() throws AircraftException, PassengerException {
		List<Passenger> seats = new ArrayList<Passenger>();
		// create aircraft
		generalTester = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(testBookingTime, testDepartTime);	
		// add passenger to single available seat of aircraft
		generalTester.confirmBooking(testPassenger, 10);	
		// remove it again
		generalTester.cancelBooking(testPassenger, 11);
		// check condition
		assertEquals(seats.toString(), generalTester.getPassengers().toString());
	}
	
	// HAS PASSENGER TESTS
	
	@Test
	public void HasPassengerTrueTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(testBookingTime, testDepartTime);	
		// add passenger to single available seat of aircraft
		generalTester.confirmBooking(testPassenger, 10);	
		// has passenger
		assertTrue(generalTester.hasPassenger(testPassenger));
	}
	
	@Test
	public void HasPassengerFalseTest() throws AircraftException, PassengerException {
		// create aircraft
		generalTester = new A380(testFlightCode, testDepartTime, 1, 1, 1, 1 );
		// create passenger in confirmed state
		testPassenger = new First(testBookingTime, testDepartTime);	
		// do not add to aircraft
		// has passenger
		assertFalse(generalTester.hasPassenger(testPassenger));
	}
	
	// SEATS AVAILABLE TESTS
	
	@Test
	public void SeatsAvailableTrueFirstTest() throws AircraftException {
		fail("Not yet implemented");
	}
	
	@Test
	public void SeatsAvailableFalseFirstTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void SeatsAvailableTrueBusinessTest() throws AircraftException {
		fail("Not yet implemented");
	}
	
	@Test
	public void SeatsAvailableFalseBusinessTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void SeatsAvailableTruePremiumTest() throws AircraftException {
		fail("Not yet implemented");
	}
	
	@Test
	public void SeatsAvailableFalsePremiumTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void SeatsAvailableTrueEconomyTest() throws AircraftException {
		fail("Not yet implemented");
	}
	
	@Test
	public void SeatsAvailableFalseEconomyTest() {
		fail("Not yet implemented");
	}
	
	// UPGRADE BOOKING TESTS
	
	@Test (expected = PassengerException.class)
	public void UpgradeBookingsTest() {
		fail("Not yet implemented");
	}
	
	
	
	

}
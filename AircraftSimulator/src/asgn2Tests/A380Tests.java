/**
 * 
 * This file is a test case which forms part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn2Tests;


import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import asgn2Aircraft.*;
import asgn2Passengers.*;

/**
 * This class represents a unit test class for the abstract Aircraft class tested through the A380 subclass as part of the
 * AircraftSimulator Project.
 * 
 * @author Vincenzo Iandolo - n9172912
 * @version 1.0
 *
 */
public class A380Tests {
	
	// Declare constants
	private static final String TEST_FLIGHT_CODE = "LAX123";
	private static final int TEST_BOOKING_TIME = 5;
	private static final int TEST_DEPART_TIME = 20;
	private static final int TEST_CONFIRM_TIME = 10;
	private static final int TEST_CANCELLATION_TIME = 10;
	private static final int GET_TEST_NUM_PASS_PASSENGER_COUNT = 4;
	private static final int NEGATIVE_BOUNDARY = -1;
	private static final int NEGATIVE_PARAMETER = -5;
	private static final int ZERO_BOUNDARY_CASE = 0;
	private static final int ZERO_PASSENGERS = 0;
	private static final int SINGLE_PASSENGER = 1;
	private static final int TWO_PASSENGERS = 2;
	private static final int SINGLE_SEAT = 1;
	private static final int MULTIPLE_PASSENGERS = 4;
	private static final int MULTIPLE_SEATS = 4;
	private static final int FIRST_INDEX = 0;
<<<<<<< HEAD
	private static final int TWO_SEATS_ADD_ONE_PASSENGER = 2;
	
	/*
	 *  Declare test objects
	 */
=======

>>>>>>> 4983ca1dd1bbfbb86e15a51626d7a31479bc15cd
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
	
	/**
	 * Test methods for {@link asgn2Aircraft.A380#A380()}.
	 * 
	 * @throws AircraftException
	 */	
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
	public void DepartTimeLessThanZeroBoundaryExceptionTest() throws AircraftException {
		exceptionTestObject = new A380(TEST_FLIGHT_CODE, NEGATIVE_BOUNDARY);
	}
	
	@Test (expected = AircraftException.class)
	public void DepartTimeLessThanZeroExceptionTest() throws AircraftException {
		exceptionTestObject = new A380(TEST_FLIGHT_CODE, NEGATIVE_PARAMETER);
	}
	
	@Test (expected = AircraftException.class)
	public void DepartTimeZeroExceptionTest() throws AircraftException {
		exceptionTestObject = new A380(TEST_FLIGHT_CODE, ZERO_BOUNDARY_CASE);
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
	
	/**
	 * Test methods for {@link asgn2Aircraft.A380#cancelBooking()}.
	 * 
	 * @throws AircraftException
	 * @throws PassengerException
	 */	
	@Test
	public void CancelBookingTestCheckBusinessNumberOfPassengersDecrementCount() throws PassengerException, AircraftException {
		
		cancelBookingTest = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);				
		cancelBookingTest.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		cancelBookingTest.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);	
		assertEquals(ZERO_PASSENGERS, cancelBookingTest.getNumPassengers());
	}

	@Test
	public void CancelBookingTestCheckPremiumNumberOfPassengersDecrementCount() throws PassengerException, AircraftException {		
		cancelBookingTest = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);				
		cancelBookingTest.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		cancelBookingTest.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);		
		assertEquals(ZERO_PASSENGERS, cancelBookingTest.getNumPassengers());
	}

	@Test
	public void CancelBookingTestCheckEconomyNumberOfPassengersDecrementCount() throws PassengerException, AircraftException {		
		cancelBookingTest = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);				
		cancelBookingTest.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		cancelBookingTest.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);		
		assertEquals(ZERO_PASSENGERS, cancelBookingTest.getNumPassengers());
	}

	@Test
	public void CancelBookingTestCheckPassengerIsNewState() throws PassengerException, AircraftException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);						
		assertTrue(testPassenger.isNew());
	}

	@Test
	public void CancelBookingTestCheckPassengerIsConfirmedState() throws PassengerException, AircraftException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);						
		assertFalse(testPassenger.isConfirmed());
	}

	@Test
	public void CancelBookingTestCheckPassengerIsQueuedState() throws PassengerException, AircraftException {
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);					
		assertFalse(testPassenger.isQueued());
	}

	@Test
	public void CancelBookingTestCheckPassengerIsRefusedState() throws PassengerException, AircraftException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);				
		assertFalse(testPassenger.isRefused());
	}

	@Test
	public void CancelBookingTestCheckPassengerIsFlownState() throws PassengerException, AircraftException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);					
		assertFalse(testPassenger.isFlown());
	}

	@Test
	public void CancelBookingTestCheckPassengerWasConfirmedState() throws PassengerException, AircraftException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);					
		assertTrue(testPassenger.wasConfirmed());
	}

	@Test
	public void CancelBookingTestCheckPassengerWasQueuedState() throws PassengerException, AircraftException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);					
		assertFalse(testPassenger.wasQueued());
	}
	
	@Test
	public void CancelBookingTestCheckPassengerDecrementCount() throws PassengerException, AircraftException {	
		cancelBookingTest = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		cancelBookingTest.confirmBooking(testPassenger, TEST_CONFIRM_TIME);	
		cancelBookingTest.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);	
		assertEquals(ZERO_PASSENGERS, cancelBookingTest.getNumPassengers());
	}
	
	@Test
	public void CancelBookingTestCheckFirstDecrementCount() throws PassengerException, AircraftException {	
		cancelBookingTest = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);				
		cancelBookingTest.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		cancelBookingTest.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);	
		assertEquals(ZERO_PASSENGERS, cancelBookingTest.getNumFirst());
	}
	
	@Test
	public void CancelBookingTestCheckBusinessDecrementCount() throws PassengerException, AircraftException {	
		cancelBookingTest = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);				
		cancelBookingTest.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		cancelBookingTest.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);		
		assertEquals(ZERO_PASSENGERS, cancelBookingTest.getNumBusiness());
	}
	
	@Test
	public void CancelBookingTestCheckPremiumDecrementCount() throws PassengerException, AircraftException {	
		cancelBookingTest = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);				
		cancelBookingTest.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		cancelBookingTest.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);	
		assertEquals(ZERO_PASSENGERS, cancelBookingTest.getNumPremium());
	}
	
	@Test
	public void CancelBookingTestCheckEconomyDecrementCount() throws PassengerException, AircraftException {	
		cancelBookingTest = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);				
		cancelBookingTest.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		cancelBookingTest.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);		
		assertEquals(ZERO_PASSENGERS, cancelBookingTest.getNumEconomy());
	}
	
	@Test
	public void CancelBookingTestCheckForPassengerObject() throws PassengerException, AircraftException {	
		cancelBookingTest = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );	
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		cancelBookingTest.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		cancelBookingTest.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);
		assertFalse(cancelBookingTest.getPassengers().contains(testPassenger));
	}
	
	@Test (expected = AircraftException.class)
	public void PassengerNotInSeatingExceptionTest() throws AircraftException, PassengerException {	
		cancelBookingTest = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );				
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		cancelBookingTest.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);
	}
	
	// CONFIRM BOOKING TESTS
	
	/**
	 * Test methods for {@link asgn2Aircraft.A380#confirmBooking()}.
	 * 
	 * @throws AircraftException
	 * @throws PassengerException
	 */		
	@Test
	public void ConfirmBookingTestCheckObjectInSeats() throws PassengerException, AircraftException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );	
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		assertTrue(generalTester.getPassengers().contains(testPassenger));
	}
	
	@Test
	public void ConfirmBookingTestCheckPassengerCount() throws PassengerException, AircraftException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );	
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		assertEquals(SINGLE_PASSENGER, generalTester.getNumPassengers());
	}
	
	@Test
	public void ConfirmBookingTestCheckPassengerCountMultiplePassengers() throws PassengerException, AircraftException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerTwo = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerTwo, TEST_CONFIRM_TIME);
		assertEquals(TWO_PASSENGERS, generalTester.getNumPassengers());
	}
	
	@Test
	public void ConfirmBookingTestCheckFirstClassPassengerCount() throws PassengerException, AircraftException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);					
		assertEquals(SINGLE_PASSENGER, generalTester.getNumFirst());
	}
	
	@Test
	public void ConfirmBookingTestCheckBusinessClassPassengerCount() throws PassengerException, AircraftException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		assertEquals(SINGLE_PASSENGER, generalTester.getNumBusiness());
	}
	
	@Test
	public void ConfirmBookingTestCheckEconomyClassPassengerCount() throws PassengerException, AircraftException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );	
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		assertEquals(SINGLE_PASSENGER, generalTester.getNumEconomy());
	}
	
	@Test
	public void ConfirmBookingTestCheckPremiumClassPassengerCount() throws PassengerException, AircraftException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );	
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		assertEquals(SINGLE_PASSENGER, generalTester.getNumPremium());
	}
	
	@Test
	public void ConfirmBookingTestCheckBusinessNumberOfPassengersCount() throws PassengerException, AircraftException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);					
		assertEquals(SINGLE_PASSENGER, generalTester.getNumPassengers());
	}

	@Test
	public void ConfirmBookingTestCheckPremiumNumberOfPassengersCount() throws PassengerException, AircraftException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);					
		assertEquals(SINGLE_PASSENGER, generalTester.getNumPassengers());
	}

	@Test
	public void ConfirmBookingTestCheckEconomyNumberOfPassengersCount() throws PassengerException, AircraftException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		assertEquals(SINGLE_PASSENGER, generalTester.getNumPassengers());
	}

	@Test
	public void ConfirmBookingTestCheckPassengerIsConfirmedState() throws PassengerException, AircraftException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );	
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		assertTrue(testPassenger.isConfirmed());
	}
	
	@Test
	public void ConfirmBookingTestCheckPassengerIsConfirmedStateUsingGetPassengers() throws PassengerException, AircraftException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		assertTrue(generalTester.getPassengers().get(FIRST_INDEX).isConfirmed());
	}

	@Test
	public void ConfirmBookingTestCheckPassengerIsQueuedState() throws PassengerException, AircraftException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );	
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		assertFalse(testPassenger.isQueued());
	}
	
	@Test
	public void ConfirmBookingTestCheckPassengerIsQueuedStateUsingGetPassengers() throws PassengerException, AircraftException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		assertFalse(generalTester.getPassengers().get(FIRST_INDEX).isQueued());
	}

	@Test
	public void ConfirmBookingTestCheckPassengerIsNewState() throws PassengerException, AircraftException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);					
		assertFalse(testPassenger.isNew());
	}
	
	@Test
	public void ConfirmBookingTestCheckPassengerIsNewStateUsingGetPassengers() throws PassengerException, AircraftException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );	
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);					
		assertFalse(generalTester.getPassengers().get(FIRST_INDEX).isNew());
	}
	
	@Test
	public void ConfirmBookingTestCheckPassengerIsRefusedState() throws PassengerException, AircraftException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		assertFalse(testPassenger.isRefused());
	}
	
	@Test
	public void ConfirmBookingTestCheckPassengerIsRefusedStateUsingGetPassengers() throws PassengerException, AircraftException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		assertFalse(generalTester.getPassengers().get(FIRST_INDEX).isRefused());
	}
	
	@Test
	public void ConfirmBookingTestCheckPassengerIsFlownState() throws PassengerException, AircraftException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		assertFalse(testPassenger.isFlown());
	}

	@Test
	public void ConfirmBookingTestCheckPassengerIsFlownStateUsingGetPassengers() throws PassengerException, AircraftException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		assertFalse(generalTester.getPassengers().get(FIRST_INDEX).isFlown());
	}

	@Test
	public void ConfirmBookingTestCheckPassengerWasConfirmedState() throws PassengerException, AircraftException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );	
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		assertTrue(testPassenger.wasConfirmed());
	}
	
	@Test
	public void ConfirmBookingTestCheckPassengerWasConfirmedStateUsingGetPassengers() throws PassengerException, AircraftException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );	
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);						
		assertTrue(generalTester.getPassengers().get(FIRST_INDEX).wasConfirmed());
	}

	@Test
	public void ConfirmBookingTestCheckPassengerWasQueuedState() throws PassengerException, AircraftException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );	
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);					
		assertFalse(testPassenger.wasQueued());
	}
	
	@Test
	public void ConfirmBookingTestCheckPassengerWasQueuedStateUsingGetPassengers() throws PassengerException, AircraftException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);					
		assertFalse(generalTester.getPassengers().get(FIRST_INDEX).wasQueued());
	}
	
	@Test (expected = AircraftException.class)
	public void NoSeatsAvailableExceptionTest() throws AircraftException, PassengerException {
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 0, 0 );			
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		// expect exception
	}
	
	@Test (expected = AircraftException.class)
	public void NoSeatsAvailableExceptionTestSeatsFull() throws AircraftException, PassengerException {
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 0, 0, 0 );	
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerTwo = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerTwo, TEST_CONFIRM_TIME);
		// expect exception
	}
	
	// FLIGHT EMPTY TESTS
	
	/**
	 * Test methods for {@link asgn2Aircraft.A380#flightEmpty()}.
	 * 
	 * @throws AircraftException
	 * @throws PassengerException
	 */	
	@Test
	public void FlightEmptyEmptyTest() throws AircraftException {
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		assertTrue(generalTester.flightEmpty());
	}
	
	@Test
	public void FlightEmptyNotEmptyTest() throws AircraftException, PassengerException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );				
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		assertFalse(generalTester.flightEmpty());
	}
	
	@Test
	public void FlightEmptyNotEmptyTestUsingBusinessClassPassenger() throws AircraftException, PassengerException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );				
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		assertFalse(generalTester.flightEmpty());
	}

	@Test
	public void FlightEmptyNotEmptyTestUsingPremiumClassPassenger() throws AircraftException, PassengerException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );					
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		assertFalse(generalTester.flightEmpty());
	}

	@Test
	public void FlightEmptyNotEmptyTestUsingEconomyClassPassenger() throws AircraftException, PassengerException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );					
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		assertFalse(generalTester.flightEmpty());
	}

	@Test
	public void FlightEmptyNotEmptyTestUsingMultiplePassengers() throws AircraftException, PassengerException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );			
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerTwo = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerTwo, TEST_CONFIRM_TIME);				
		assertFalse(generalTester.flightEmpty());
	}

	@Test
	public void FlightEmptyNotEmptyTestUsingMultiplePassengersOfEachClass() throws AircraftException, PassengerException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );			
		
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerTwo = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerThree = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerFour = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);			

		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerTwo, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerThree, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerFour, TEST_CONFIRM_TIME);		
		
		assertFalse(generalTester.flightEmpty());
	}
	
	// FLIGHT FULL TESTS
	
	/**
	 * Test methods for {@link asgn2Aircraft.A380#flightFull()}.
	 * 
	 * @throws AircraftException
	 * @throws PassengerException
	 */	
	@Test
	public void FlightFullNotFullTest() throws AircraftException {
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		assertFalse(generalTester.flightFull());
	}
	
	@Test
	public void FlightFullFullTest() throws AircraftException, PassengerException {
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 0, 0, 0 );		
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		assertTrue(generalTester.flightFull());
	}
	
	@Test
	public void FlightFullAlmostFullTest() throws AircraftException, PassengerException {

		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );

		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerTwo = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerThree = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);	

		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerTwo, TEST_CONFIRM_TIME);	
		generalTester.confirmBooking(testPassengerThree, TEST_CONFIRM_TIME);		
		
		assertFalse(generalTester.flightFull());
	}

	@Test
	public void FlightFullFullTestUsingEachClassOfPassenger() throws AircraftException, PassengerException {
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );

		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerTwo = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerThree = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerFour = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);	

		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerTwo, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerThree, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerFour, TEST_CONFIRM_TIME);		
		
		assertTrue(generalTester.flightFull());
	}

	@Test
	public void FlightFullNotFullTestUsingEachClassOfPassenger() throws AircraftException, PassengerException {
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, TWO_SEATS_ADD_ONE_PASSENGER, TWO_SEATS_ADD_ONE_PASSENGER );
		
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerTwo = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerThree = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerFour = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);	

		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerTwo, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerThree, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerFour, TEST_CONFIRM_TIME);		
		
		assertFalse(generalTester.flightFull());
	}
	
	
	 // FLY PASSENGERS TESTS
	
	/**
	 * Test methods for {@link asgn2Aircraft.A380#flyPassengers()}.
	 * 
	 * @throws AircraftException
	 * @throws PassengerException
	 */	
	@Test
	public void FlyPassengersCorrectStateChangeTest() throws AircraftException, PassengerException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );			
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);					
		generalTester.flyPassengers(TEST_DEPART_TIME);		
		assertTrue(generalTester.getPassengers().get(FIRST_INDEX).isFlown());
	}
	
	@Test
	public void FlyPassengersEmptySeatingTest() throws AircraftException, PassengerException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );				
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);					
		generalTester.flyPassengers(TEST_DEPART_TIME);		
		assertTrue(generalTester.getPassengers().isEmpty());
	}
	
	public void FlyPassengersCorrectStateChangeIsNewTest() throws AircraftException, PassengerException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );			
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		generalTester.flyPassengers(TEST_DEPART_TIME);		
		assertFalse(generalTester.getPassengers().get(FIRST_INDEX).isNew());
	}

	public void FlyPassengersCorrectStateChangeIsConfirmedTest() throws AircraftException, PassengerException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		generalTester.flyPassengers(TEST_DEPART_TIME);	
		assertFalse(generalTester.getPassengers().get(FIRST_INDEX).isConfirmed());
	}

	public void FlyPassengersCorrectStateChangeIsQueuedTest() throws AircraftException, PassengerException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		generalTester.flyPassengers(TEST_DEPART_TIME);	
		assertFalse(generalTester.getPassengers().get(FIRST_INDEX).isQueued());
	}

	public void FlyPassengersCorrectStateChangeIsRefusedTest() throws AircraftException, PassengerException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );			
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		generalTester.flyPassengers(TEST_DEPART_TIME);	
		assertFalse(generalTester.getPassengers().get(FIRST_INDEX).isRefused());
	}
	
	public void FlyPassengersCorrectStateChangeWasConfirmedTest() throws AircraftException, PassengerException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );				
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		generalTester.flyPassengers(TEST_DEPART_TIME);	
		assertTrue(generalTester.getPassengers().get(FIRST_INDEX).wasConfirmed());
	}
	
	public void FlyPassengersCorrectStateChangeWasQueuedTest() throws AircraftException, PassengerException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );			
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);			
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		generalTester.flyPassengers(TEST_DEPART_TIME);	
		assertFalse(generalTester.getPassengers().get(FIRST_INDEX).wasQueued());
	}
	
	// GET BOOKINGS TESTS
	
	/**
	 * Test methods for {@link asgn2Aircraft.A380#getBookings()}.
	 * 
	 * @throws AircraftException
	 * @throws PassengerException
	 */	
	@Test 
	public void GetBookingsTestNumEconomy() throws AircraftException, PassengerException {
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 0, 1 );		
		Passenger EconomyBookingTest = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(EconomyBookingTest, TEST_CONFIRM_TIME);
		assertEquals(SINGLE_PASSENGER, generalTester.getBookings().getNumEconomy());
	}
	
	@Test 
	public void GetBookingsTestNumPremium() throws AircraftException, PassengerException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 1, 0 );		
		Passenger PremiumBookingTest = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(PremiumBookingTest, TEST_CONFIRM_TIME);
		assertEquals(SINGLE_PASSENGER, generalTester.getBookings().getNumPremium());
	}
	
	@Test 
	public void GetBookingsTestNumBusiness() throws AircraftException, PassengerException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 1, 0, 0 );		
		Passenger BusinessBookingTest = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(BusinessBookingTest, TEST_CONFIRM_TIME);
		assertEquals(SINGLE_PASSENGER, generalTester.getBookings().getNumBusiness());
	}
	
	@Test 
	public void GetBookingsTestNumFirst() throws AircraftException, PassengerException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 0, 0, 0 );		
		Passenger FirstBookingTest = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(FirstBookingTest, TEST_CONFIRM_TIME);
		assertEquals(SINGLE_PASSENGER, generalTester.getBookings().getNumFirst());
	}
	
	@Test 
	public void GetBookingsTestNumAvailable() throws AircraftException, PassengerException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, TWO_SEATS_ADD_ONE_PASSENGER, 1 );		

		Passenger FirstBookingTest = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger EconomyBookingTest = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger PremiumBookingTest = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger BusinessBookingTest = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);

		generalTester.confirmBooking(FirstBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(EconomyBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(PremiumBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(BusinessBookingTest, TEST_CONFIRM_TIME);

		assertEquals(SINGLE_SEAT, generalTester.getBookings().getAvailable());
	}
	
	@Test 
	public void GetBookingsTestNumTotal() throws AircraftException, PassengerException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		

		Passenger FirstBookingTest = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger EconomyBookingTest = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger PremiumBookingTest = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger BusinessBookingTest = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);

		generalTester.confirmBooking(FirstBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(EconomyBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(PremiumBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(BusinessBookingTest, TEST_CONFIRM_TIME);

		assertEquals(MULTIPLE_PASSENGERS, generalTester.getBookings().getTotal());
	}
	
	@Test 
	public void GetBookingsTestNumAvailableMultipleSeatsAvailable() throws AircraftException, PassengerException {
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 
				TWO_SEATS_ADD_ONE_PASSENGER, TWO_SEATS_ADD_ONE_PASSENGER, TWO_SEATS_ADD_ONE_PASSENGER, TWO_SEATS_ADD_ONE_PASSENGER );		

		Passenger FirstBookingTest = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger EconomyBookingTest = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger PremiumBookingTest = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger BusinessBookingTest = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);

		generalTester.confirmBooking(FirstBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(EconomyBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(PremiumBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(BusinessBookingTest, TEST_CONFIRM_TIME);

		assertEquals(MULTIPLE_SEATS, generalTester.getBookings().getAvailable());
	}

	@Test 
	public void GetBookingsTestNumTotalWithSeatsRemaining() throws AircraftException, PassengerException {
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, TWO_SEATS_ADD_ONE_PASSENGER );
		
		Passenger FirstBookingTest = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger EconomyBookingTest = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger PremiumBookingTest = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		Passenger BusinessBookingTest = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);

		generalTester.confirmBooking(FirstBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(EconomyBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(PremiumBookingTest, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(BusinessBookingTest, TEST_CONFIRM_TIME);

		assertEquals(MULTIPLE_PASSENGERS, generalTester.getBookings().getTotal());
	}
	
	@Test 
	public void GetBookingsTestNumTotalWithNoPassengers() throws AircraftException, PassengerException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, TWO_SEATS_ADD_ONE_PASSENGER );		
		assertEquals(ZERO_PASSENGERS, generalTester.getBookings().getTotal());
	}
	
	// GET NUM BUSINESS TESTS
	
	@Test
	public void GetNumBusinessClassPassengersTest() {
		assertEquals(SINGLE_PASSENGER, getTestNumPassClass.getNumBusiness());
	}
	
	// GET NUM ECONOMY TESTS
	
	@Test
	public void GetNumEcomonyClassPassengersTest() {
		assertEquals(SINGLE_PASSENGER, getTestNumPassClass.getNumEconomy());
	}
	
	// GET NUM FIRST TESTS
	
	@Test
	public void GetNumFirstClassPassengersTest() {
		assertEquals(SINGLE_PASSENGER, getTestNumPassClass.getNumFirst());
	}
	
	// GET NUM PASSENGERS TESTS
	
	@Test
	public void GetNumPassengersTest() {
		assertEquals(GET_TEST_NUM_PASS_PASSENGER_COUNT, getTestNumPassClass.getNumPassengers());
	}
	
	// GET NUM PREMIUM TESTS
	
	@Test
	public void GetNumPremiumClassPassengersTest() {
		assertEquals(SINGLE_PASSENGER, getTestNumPassClass.getNumPremium());
	}
	
	// GET PASSENGERS TESTS
	
	/**
	 * Test methods for {@link asgn2Aircraft.A380#getPassengers()}.
	 * 
	 * @throws AircraftException
	 * @throws PassengerException
	 */	
	@Test
	public void GetPassengersTest() throws AircraftException, PassengerException {
		List<Passenger> seats = new ArrayList<Passenger>();	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		seats.add(testPassenger);		
		assertTrue(seats.toString().equals(generalTester.getPassengers().toString()));
	}
	
	@Test
	public void GetPassengersTestMultiplePassengers() throws AircraftException, PassengerException {
		List<Passenger> seats = new ArrayList<Passenger>();
		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerTwo = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerThree = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerFour = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);	

		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerTwo, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerThree, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerFour, TEST_CONFIRM_TIME);		

		seats.add(testPassenger);
		seats.add(testPassengerTwo);
		seats.add(testPassengerThree);
		seats.add(testPassengerFour);
		
		assertTrue(seats.toString().equals(generalTester.getPassengers().toString()));
	}
	
	@Test
	public void GetPassengersAfterCancelledPassengerTest() throws AircraftException, PassengerException {
		List<Passenger> seats = new ArrayList<Passenger>();	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		generalTester.cancelBooking(testPassenger, TEST_CANCELLATION_TIME);
		assertTrue(seats.toString().equals(generalTester.getPassengers().toString()));
	}
	
	// HAS PASSENGER TESTS
	
	/**
	 * Test methods for {@link asgn2Aircraft.A380#hasPassenger()}.
	 * 
	 * @throws AircraftException
	 * @throws PassengerException
	 */	
	@Test
	public void HasPassengerTrueTest() throws AircraftException, PassengerException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		assertTrue(generalTester.hasPassenger(testPassenger));
	}
	
	@Test
	public void HasPassengerFalseTest() throws AircraftException, PassengerException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		assertFalse(generalTester.hasPassenger(testPassenger));
	}
	
	@Test
	public void HasPassengerMultipePassengersTest() throws AircraftException, PassengerException {
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );

		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerTwo = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerThree = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		testPassengerFour = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);

		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerTwo, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerThree, TEST_CONFIRM_TIME);
		generalTester.confirmBooking(testPassengerFour, TEST_CONFIRM_TIME);

		assertTrue(generalTester.hasPassenger(testPassenger) && generalTester.hasPassenger(testPassengerTwo) && generalTester.hasPassenger(testPassengerThree) && generalTester.hasPassenger(testPassengerFour));
	}
	
	// SEATS AVAILABLE TESTS
	
	/**
	 * Test methods for {@link asgn2Aircraft.A380#seatsAvailable()}.
	 * 
	 * @throws AircraftException
	 * @throws PassengerException
	 */	
	@Test
	public void SeatsAvailableTrueFirstTest() throws AircraftException, PassengerException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );	
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		assertTrue(generalTester.seatsAvailable(testPassenger));	
	}
	
	@Test
	public void SeatsAvailableFalseFirstTest() throws AircraftException, PassengerException {
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 0, 0 );	
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		assertFalse(generalTester.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableTrueBusinessTest() throws AircraftException, PassengerException {
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		assertTrue(generalTester.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableFalseBusinessTest() throws PassengerException, AircraftException {
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 0, 0 );	
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		assertFalse(generalTester.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableTruePremiumTest() throws AircraftException, PassengerException {
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );	
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		assertTrue(generalTester.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableFalsePremiumTest() throws PassengerException, AircraftException {
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 0, 0 );	
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		assertFalse(generalTester.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableTrueEconomyTest() throws AircraftException, PassengerException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		assertTrue(generalTester.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableFalseEconomyTest() throws AircraftException, PassengerException {
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 0, 0 );	
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		assertFalse(generalTester.seatsAvailable(testPassenger));
	}
	
	@Test
	public void SeatsAvailableSeatFullFirstTest() throws AircraftException, PassengerException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );	
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);	
		assertFalse(generalTester.seatsAvailable(testPassenger));	
	}

	@Test
	public void SeatsAvailableVacantSeatsFirstTest() throws AircraftException, PassengerException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, TWO_SEATS_ADD_ONE_PASSENGER, 1, 1, 1 );	
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);	
		assertTrue(generalTester.seatsAvailable(testPassenger));	
	}

	@Test
	public void SeatsAvailableSeatFullBusinessTest() throws AircraftException, PassengerException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);	
		assertFalse(generalTester.seatsAvailable(testPassenger));	
	}

	@Test
	public void SeatsAvailableVacantSeatsBusinessTest() throws AircraftException, PassengerException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, TWO_SEATS_ADD_ONE_PASSENGER, 1, 1 );
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);	
		assertTrue(generalTester.seatsAvailable(testPassenger));	
	}

	@Test
	public void SeatsAvailableSeatFullPremiumTest() throws AircraftException, PassengerException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);	
		assertFalse(generalTester.seatsAvailable(testPassenger));	
	}

	@Test
	public void SeatsAvailableVacantSeatsPremiumTest() throws AircraftException, PassengerException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, TWO_SEATS_ADD_ONE_PASSENGER, 1 );	
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);	
		assertTrue(generalTester.seatsAvailable(testPassenger));	
	}

	@Test
	public void SeatsAvailableSeatFullEconomyTest() throws AircraftException, PassengerException {		
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, 1 );		
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);	
		assertFalse(generalTester.seatsAvailable(testPassenger));	
	}

	@Test
	public void SeatsAvailableVacantSeatsEconomyTest() throws AircraftException, PassengerException {	
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 1, TWO_SEATS_ADD_ONE_PASSENGER );
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);	
		assertTrue(generalTester.seatsAvailable(testPassenger));	
	}
	
	// UPGRADE BOOKING TESTS
	
	/**
	 * Test methods for {@link asgn2Aircraft.A380#upgradeBooking()}.
	 * 
	 * @throws AircraftException
	 * @throws PassengerException
	 */	
	@Test
	public void UpgradeBookingsFromEconomyTestPremiumCounter() throws AircraftException, PassengerException { 
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 1, 1 );
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		generalTester.upgradeBookings();
		assertEquals(SINGLE_PASSENGER, generalTester.getNumPremium());
	}
	
	@Test
	public void UpgradeBookingsFromEconomyTestContainment() throws AircraftException, PassengerException {		 
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 1, 1 );
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		generalTester.upgradeBookings();
		assertTrue(generalTester.getPassengers().contains(testPassenger));
	}
	
	@Test
	public void UpgradeBookingsFromEconomyToPremiumTestEconomyCounter() throws AircraftException, PassengerException {	 
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 0, 1, 1 );
		testPassenger = new Economy(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		generalTester.upgradeBookings();
		assertEquals(ZERO_PASSENGERS, generalTester.getNumEconomy());
	}
	
	@Test
	public void UpgradeBookingsFromPremiumTestCounter() throws AircraftException, PassengerException { 
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 1, 1, 0 );		
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		generalTester.upgradeBookings();
		assertEquals(SINGLE_PASSENGER, generalTester.getNumBusiness());
	}
	
	@Test
	public void UpgradeBookingsFromPremiumTestContainment() throws AircraftException, PassengerException {		 
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 1, 1, 0 );		
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		generalTester.upgradeBookings();		
		assertTrue(generalTester.getPassengers().contains(testPassenger));
	}
	
	@Test
	public void UpgradeBookingsFromPremiumToBusinessTestPremiumCounter() throws AircraftException, PassengerException {		 
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 1, 1, 0 );
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		generalTester.upgradeBookings();		
		assertEquals(ZERO_PASSENGERS, generalTester.getNumPremium());
	}
	
	@Test
	public void UpgradeBookingsFromBusinessTestFirstCounter() throws AircraftException, PassengerException {	 
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 0, 0 );
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		generalTester.upgradeBookings();
		assertEquals(SINGLE_PASSENGER, generalTester.getNumFirst());
	}
	
	@Test
	public void UpgradeBookingsFromBusinessTestContainment() throws AircraftException, PassengerException {	 
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 0, 0 );	
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);		
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);				
		generalTester.upgradeBookings();		
		assertTrue(generalTester.getPassengers().contains(testPassenger));
	}
	
	@Test
	public void UpgradeBookingsFromBusinessToFirstTestBusinessCounter() throws AircraftException, PassengerException {	 
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 0, 0 );	
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		generalTester.upgradeBookings();		
		assertEquals(ZERO_PASSENGERS, generalTester.getNumBusiness());
	}

	@Test
	public void UpgradeBookingsFromPremiumTestPremiumCounter() throws AircraftException, PassengerException {	 
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 0, 1, 1, 0 );	
		testPassenger = new Premium(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		generalTester.upgradeBookings();
		assertEquals(ZERO_PASSENGERS, generalTester.getNumPremium());
	}

	@Test
	public void UpgradeBookingsFromBusinessTestBusinessCounter() throws AircraftException, PassengerException {	 
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 0, 0 );
		testPassenger = new Business(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		generalTester.upgradeBookings();
		assertEquals(ZERO_PASSENGERS, generalTester.getNumBusiness());
	}

	@Test
	public void UpgradeBookingsFromFirstTestFirstCounter() throws AircraftException, PassengerException {	 
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 0, 0 );
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);	
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);			
		generalTester.upgradeBookings();	
		assertEquals(SINGLE_PASSENGER, generalTester.getNumFirst());
	}

	@Test
	public void UpgradeBookingsFromFirstTestContainment() throws AircraftException, PassengerException {
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 0, 0 );
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		generalTester.upgradeBookings();
		assertTrue(generalTester.getPassengers().contains(testPassenger));
	}

	@Test
	public void UpgradeBookingsFromFirstTestContainmentAndInstanceType() throws AircraftException, PassengerException {
		generalTester = new A380(TEST_FLIGHT_CODE, TEST_DEPART_TIME, 1, 1, 0, 0 );
		testPassenger = new First(TEST_BOOKING_TIME, TEST_DEPART_TIME);
		generalTester.confirmBooking(testPassenger, TEST_CONFIRM_TIME);		
		generalTester.upgradeBookings();
		assertTrue(generalTester.getPassengers().get(FIRST_INDEX) instanceof First);
	}
}
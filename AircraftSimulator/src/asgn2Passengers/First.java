/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Passengers;

/**
 * Specialisation of the {@link asgn2Passengers.Passenger} class to represent a  
 * passenger of the First class
 * 
 * @author hogan
 *
 */
public class First extends Passenger {

	/**
	 * First Constructor (Partially Supplied) 
	 * Passenger is created in New state, later given a Confirmed First Class reservation, 
	 * Queued, or Refused booking if waiting list is full. 
	 * 
	 * @param bookingTime <code>int</code> day of the original booking. 
	 * @param departureTime <code>int</code> day of the intended flight.  
	 * @throws PassengerException if invalid bookingTime or departureTime 
	 * @see asgnPassengers.Passenger#Passenger(int,int)
	 */
	public First(int bookingTime, int departureTime) throws PassengerException {
		super(bookingTime, departureTime);	
		this.passID = "F:" + this.passID;	
	}
	
	/**
	 * Simple constructor to support {@link asgn2Passengers.Passenger#upgrade()} in other subclasses
	 */
	protected First() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Passengers.Passenger#noSeatsMsg()
	 */
	@Override
	public String noSeatsMsg() {
		return "No seats available in First";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Passengers.Passenger#upgrade()
	 */
	@Override
	public Passenger upgrade() {
		// cannot be upgraded from first ergo return current passenger
		return this;
	}
}

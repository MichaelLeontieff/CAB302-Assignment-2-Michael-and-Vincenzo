/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Passengers;

/**
 * Specialisation of the {@link asgn2Passengers.Passenger} class to represent a  
 * passenger of the Business class
 * 
 * @author hogan
 *
 */
public class Business extends Passenger {

	/**
	 * Business Constructor (Partially Supplied) 
	 * Passenger is created in New state, later given a Confirmed Business Class reservation, 
	 * Queued, or Refused booking if waiting list is full. 
	 * 
	 * @param bookingTime <code>int</code> day of the original booking. 
	 * @param departureTime <code>int</code> day of the intended flight.  
	 * @throws PassengerException if invalid bookingTime or departureTime 
	 * @see asgnPassengers.Passenger#Passenger(int,int)
	 */
	public Business(int bookingTime, int departureTime) throws PassengerException { 
		super(bookingTime, departureTime);
		this.passID = "J:" + this.passID;
	}
	
	/**
	 * Simple constructor to support {@link asgn2Passengers.Passenger#upgrade()} in other subclasses
	 */
	protected Business() {
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Passengers.Passenger#noSeatsMsg
	 */
	@Override
	public String noSeatsMsg() {
		return "No seats available in Business";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Passengers.Passenger#upgrade
	 */
	@Override
	public Passenger upgrade() {
		// Create new passenger object of type First() upgrade
		Passenger upgraded = new First();
		// Copy over previous states
		upgraded.copyPassengerState(this);
		upgraded.passID = "F(U)" + upgraded.passID;
		return upgraded;
	}
}

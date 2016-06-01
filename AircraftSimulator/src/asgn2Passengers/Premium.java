/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Passengers;

/**
 * Specialisation of the {@link asgn2Passengers.Passenger} class to represent a  
 * passenger of the Premium class
 * 
 * @author hogan
 *
 */
public class Premium extends Passenger {

	/**
	 * Premium Constructor (Partially Supplied)
	 * Passenger is created in New state, later given a Confirmed Premium Class reservation, 
	 * Queued, or Refused booking if waiting list is full. 
	 * 
	 * @param bookingTime <code>int</code> day of the original booking. 
	 * @param departureTime <code>int</code> day of the intended flight.  
	 * @throws PassengerException if invalid bookingTime or departureTime 
	 * @see asgnPassengers.Passenger#Passenger(int,int)
	 */
	public Premium(int bookingTime,int departureTime) throws PassengerException {
		super(bookingTime, departureTime);
		this.passID = "P:" + this.passID;
	}
	
	/**
	 * Simple constructor to support {@link asgn2Passengers.Passenger#upgrade()} in other subclasses
	 */
	protected Premium() {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Passengers.Passenger#upgrade()
	 */
	@Override
	public Passenger upgrade() {
		// Create new passenger object
		Passenger upgraded = new Business();
		// Copy over previous states
		upgraded.copyPassengerState(this);
		upgraded.passID = "J(U)" + upgraded.passID;
		return upgraded;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Passengers.Passenger#noSeatsMsg()
	 */
	@Override
	public String noSeatsMsg() {
		return "No seats available in Premium";
	}
}

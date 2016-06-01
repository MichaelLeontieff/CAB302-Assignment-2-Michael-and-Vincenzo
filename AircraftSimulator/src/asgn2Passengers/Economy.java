/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Passengers;

/**
 * Specialisation of the {@link asgn2Passengers.Passenger} class to represent a  
 * passenger of the Economy class
 * 
 * @author hogan
 *
 */
public class Economy extends Passenger {

	/**
	 * Economy Constructor (Partially Supplied)
	 * Passenger is created in New state, later given a Confirmed Economy Class reservation, 
	 * Queued, or Refused booking if waiting list is full. 
	 * 
	 * @param bookingTime <code>int</code> day of the original booking. 
	 * @param departureTime <code>int</code> day of the intended flight.  
	 * @throws PassengerException if invalid bookingTime or departureTime 
	 * @see asgnPassengers.Passenger#Passenger(int,int)
	 */
	public Economy(int bookingTime,int departureTime) throws PassengerException { 
		super(bookingTime, departureTime);
		this.passID = "Y:" + this.passID;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Passengers.Passenger#noSeatsMsg()
	 */
	@Override
	public String noSeatsMsg() {
		return "No seats available in Economy";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Passengers.Passenger#upgrade()
	 */
	@Override
	public Passenger upgrade() {
		// Create new passenger object
		Passenger upgraded = new Premium();
		// copy over previous states
		upgraded.copyPassengerState(this);
		upgraded.passID = "P(U)" + upgraded.passID;
		return upgraded;
	}
}

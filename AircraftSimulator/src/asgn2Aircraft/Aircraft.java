/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Aircraft;


import java.util.ArrayList;
import java.util.List;

import asgn2Passengers.Business;
import asgn2Passengers.Economy;
import asgn2Passengers.First;
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;
import asgn2Passengers.Premium;
import asgn2Simulators.Log;

/**
 * The <code>Aircraft</code> class provides facilities for modelling a commercial jet 
 * aircraft with multiple travel classes. New aircraft types are created by explicitly 
 * extending this class and providing the necessary configuration information. 
 * 
 * In particular, <code>Aircraft</code> maintains a collection of currently booked passengers, 
 * those with a Confirmed seat on the flight. Queueing and Refused bookings are handled by the 
 * main {@link asgn2Simulators.Simulator} class. 
 *   
 * The class maintains a variety of constraints on passengers, bookings and movement 
 * between travel classes, and relies heavily on the asgn2Passengers hierarchy. Reports are 
 * also provided for logging and graphical display. 
 * 
 * @author hogan
 *
 */
public abstract class Aircraft {
	
	private static final int EMPTY_SEATS = 0;
	private static final int BUSINESS_PASSENGER = 0;
	private static final int PREMIUM_PASSENGER = 1;
	private static final int ECONOMY_PASSENGER = 2;
	private static final int PASSENGER_CLASS = 3;

	protected int firstCapacity;
	protected int businessCapacity;
	protected int premiumCapacity;
	protected int economyCapacity;
	protected int capacity;
		
	protected int numFirst;
	protected int numBusiness;
	protected int numPremium; 
	protected int numEconomy; 

	protected String flightCode;
	protected String type; 
	protected int departureTime; 
	protected String status;
	protected List<Passenger> seats;

	/**
	 * Constructor sets flight info and the basic size parameters. 
	 * 
	 * @param flightCode <code>String</code> containing flight ID 
	 * @param departureTime <code>int</code> scheduled departure time
	 * @param first <code>int</code> capacity of First Class 
	 * @param business <code>int</code> capacity of Business Class 
	 * @param premium <code>int</code> capacity of Premium Economy Class 
	 * @param economy <code>int</code> capacity of Economy Class 
	 * @throws AircraftException if isNull(flightCode) OR (departureTime <=0) OR ({first,business,premium,economy} <0)
	 */
	public Aircraft(String flightCode,int departureTime, int first, int business, int premium, int economy) throws AircraftException {
		// Check for exceptions
		checkInvalidNumPassengers(flightCode, departureTime, first, business, premium, economy);
		
		// Set each field
		this.status = "";
		this.flightCode = flightCode;
		this.departureTime = departureTime;
		this.firstCapacity = first;
		this.businessCapacity = business;
		this.premiumCapacity = premium;
		this.economyCapacity = economy;
		this.capacity = first + business + premium + economy;
		
		// Instantiate seats with a Passenger ArrayList
		seats = new ArrayList<Passenger>();
	}
	
	/**
	 * Method to remove passenger from the aircraft - passenger must have a confirmed 
	 * seat prior to entry to this method.   
	 *
	 * @param p <code>Passenger</code> to be removed from the aircraft 
	 * @param cancellationTime <code>int</code> time operation performed 
	 * @throws PassengerException if <code>Passenger</code> is not Confirmed OR cancellationTime 
	 * is invalid. See {@link asgn2Passengers.Passenger#cancelSeat(int)}
	 * @throws AircraftException if <code>Passenger</code> is not recorded in aircraft seating 
	 */
	public void cancelBooking(Passenger p,int cancellationTime) throws PassengerException, AircraftException {
		// Some local exception checking
		checkIfPassengerInSeating(p);
		// Transition method on the passenger
		p.cancelSeat(cancellationTime);
		// Update of status string for the aircraft
		this.status += Log.setPassengerMsg(p,"C","N");
		// Remove passenger from seat storage for the aircraft
		this.seats.remove(p);
		// Decrement the counts
		this.decrementCounts(p);
	}

	/**
	 * Method to add a Passenger to the aircraft seating. 
	 * Precondition is a test that a seat is available in the required fare class
	 * 
	 * @param p <code>Passenger</code> to be added to the aircraft 
	 * @param confirmationTime <code>int</code> time operation performed 
	 * @throws PassengerException if <code>Passenger</code> is in incorrect state 
	 * OR confirmationTime OR departureTime is invalid. See {@link asgn2Passengers.Passenger#confirmSeat(int, int)}
	 * @throws AircraftException if no seats available in <code>Passenger</code> fare class. 
	 */
	public void confirmBooking(Passenger p,int confirmationTime) throws AircraftException, PassengerException { 
		// Some local exception checking
		checkIfSeatsAreAvailable(p);
		// Transition method on the passenger
		p.confirmSeat(confirmationTime, this.departureTime);
		// Update of status string for the aircraft
		this.status += Log.setPassengerMsg(p,"N/Q","C");
		// Add passenger to seat storage for the aircraft
		this.seats.add(p);
		// Increment the counts
		this.incrementCounts(p);
	}

	/**
	 * State dump intended for use in logging the final state of the aircraft. (Supplied) 
	 * 
	 * @return <code>String</code> containing dump of final aircraft state 
	 */
	public String finalState() {
		String str = aircraftIDString() + " Pass: " + this.seats.size() + "\n";
		for (Passenger p : this.seats) {
			str += p.toString() + "\n";
		}
		return str + "\n";
	}
	
	/**
	 * Simple status showing whether aircraft is empty
	 * 
	 * @return <code>boolean</code> true if aircraft empty; false otherwise 
	 */
	public boolean flightEmpty() {
		return this.seats.size() == EMPTY_SEATS;
	}
	
	/**
	 * Simple status showing whether aircraft is full
	 * 
	 * @return <code>boolean</code> true if aircraft full; false otherwise 
	 */
	public boolean flightFull() {
		return this.seats.size() == this.capacity;
	}
	
	/**
	 * Method to finalise the aircraft seating on departure. 
	 * Effect is to change the state of each passenger to Flown. 
	 * departureTime parameter allows for rescheduling 
	 * 
	 * @param departureTime <code>int</code> actual departureTime from simulation  
	 * @throws PassengerException if <code>Passenger</code> is in incorrect state 
	 * See {@link asgn2Passengers.Passenger#flyPassenger(int)}. 
	 */
	public void flyPassengers(int departureTime) throws PassengerException {	
		for (Passenger p : this.seats) {
			p.flyPassenger(departureTime);
		}
	}
	
	/**
	 * Method to return an {@link asgn2Aircraft.Bookings} object containing the Confirmed 
	 * booking status for this aircraft. 
	 * 
	 * @return <code>Bookings</code> object containing the status.  
	 */
	public Bookings getBookings() {
		int total = numFirst + numBusiness + numPremium + numEconomy;
		int available = (firstCapacity - numFirst) + (businessCapacity - numBusiness) + (premiumCapacity - numPremium) + (economyCapacity - numEconomy);
		
		return new Bookings(numFirst, numBusiness, numPremium, numEconomy, total, available);
	}
	
	/**
	 * Simple getter for number of confirmed Business Class passengers
	 * 
	 * @return <code>int</code> number of Business Class passengers 
	 */
	public int getNumBusiness() {
		return numBusiness;
		
	}
	
	
	/**
	 * Simple getter for number of confirmed Economy passengers
	 * 
	 * @return <code>int</code> number of Economy Class passengers 
	 */
	public int getNumEconomy() {
		return numEconomy;
	}

	/**
	 * Simple getter for number of confirmed First Class passengers
	 * 
	 * @return <code>int</code> number of First Class passengers 
	 */
	public int getNumFirst() {
		return numFirst;
	}

	/**
	 * Simple getter for the total number of confirmed passengers 
	 * 
	 * @return <code>int</code> number of Confirmed passengers 
	 */
	public int getNumPassengers() {
		return numFirst + numBusiness + numPremium + numEconomy;
	}
	
	/**
	 * Simple getter for number of confirmed Premium Economy passengers
	 * 
	 * @return <code>int</code> number of Premium Economy Class passengers
	 */
	public int getNumPremium() {
		return numPremium;
	}
	
	/**
	 * Method to return an {@link java.util.List} object containing a copy of 
	 * the list of passengers on this aircraft. 
	 * 
	 * @return <code>List<Passenger></code> object containing the passengers.  
	 */
	public List<Passenger> getPassengers() {
		return new ArrayList<Passenger>(this.seats);
	}
	
	/**
	 * Method used to provide the current status of the aircraft for logging. (Supplied) 
	 * Uses private status <code>String</code>, set whenever a transition occurs. 
	 *  
	 * @return <code>String</code> containing current aircraft state 
	 */
	public String getStatus(int time) {
		String str = time +"::"
		+ this.seats.size() + "::"
		+ "F:" + this.numFirst + "::J:" + this.numBusiness 
		+ "::P:" + this.numPremium + "::Y:" + this.numEconomy; 
		str += this.status;
		this.status="";
		return str+"\n";
	}
	
	/**
	 * Simple boolean to check whether a passenger is included on the aircraft 
	 * 
	 * @param p <code>Passenger</code> whose presence we are checking
	 * @return <code>boolean</code> true if isConfirmed(p); false otherwise 
	 */
	public boolean hasPassenger(Passenger p) {
		return this.seats.contains(p);
	}
	

	/**
	 * State dump intended for logging the aircraft parameters (Supplied) 
	 * 
	 * @return <code>String</code> containing dump of initial aircraft parameters 
	 */ 
	public String initialState() {
		return aircraftIDString() + " Capacity: " + this.capacity 
				+ " [F: " 
				+ this.firstCapacity + " J: " + this.businessCapacity 
				+ " P: " + this.premiumCapacity + " Y: " + this.economyCapacity
				+ "]";
	}
	
	/**
	 * Given a Passenger, method determines whether there are seats available in that 
	 * fare class. 
	 *   
	 * @param p <code>Passenger</code> to be Confirmed
	 * @return <code>boolean</code> true if seats in Class(p); false otherwise
	 */
	public boolean seatsAvailable(Passenger p) {
		// Initially set available to false
		boolean available = false;
		// Check if seats are available for each class
		if (p instanceof First) { 
			available = seatsAvailableFirst();
		}
		else if (p instanceof Business) {
			available = seatsAvailableBusiness();
		}
		else if (p instanceof Premium) {
			available = seatsAvailablePremium();
		}
		else if (p instanceof Economy) {
			available = seatsAvailableEconomy();
		}
		return available;
	}

	/* 
	 * (non-Javadoc) (Supplied) 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return aircraftIDString() + " Count: " + this.seats.size() 
				+ " [F: " + numFirst
				+ " J: " + numBusiness 
				+ " P: " + numPremium 
				+ " Y: " + numEconomy 
			    + "]";
	}

	/**
	 * Method to upgrade Passengers to try to fill the aircraft seating. 
	 * Called at departureTime. Works through the aircraft fare classes in 
	 * descending order of status. No upgrades are possible from First, so 
	 * we consider Business passengers (upgrading if there is space in First), 
	 * then Premium, upgrading to fill spaces already available and those created 
	 * by upgrades to First), and then finally, we do the same for Economy, upgrading 
	 * where possible to Premium.  
	 */
	public void upgradeBookings() { 
		for (int i = BUSINESS_PASSENGER; i < PASSENGER_CLASS; i++) {
			for (Passenger p : this.seats) {
				if ((i == BUSINESS_PASSENGER) && (p instanceof Business) && (numFirst < firstCapacity)) {
					upgradeBusiness(p);
				}
				else if ((i == PREMIUM_PASSENGER) && (p instanceof Premium) && (numBusiness < businessCapacity)) {
					upgradePremium(p);
				}
				else if ((i == ECONOMY_PASSENGER) && (p instanceof Economy) && (numPremium < premiumCapacity)) {
					upgradeEconomy(p);
				}
			}
		}
	}

	/**
	 * Simple String method for the Aircraft ID 
	 * 
	 * @return <code>String</code> containing the Aircraft ID 
	 */
	private String aircraftIDString() {
		return this.type + ":" + this.flightCode + ":" + this.departureTime;
	}


	//Various private helper methods to check arguments and throw exceptions, to increment 
	//or decrement counts based on the class of the Passenger, and to get the number of seats 
	//available in a particular class
	
	
	//Used in the exception thrown when we can't confirm a passenger 
	/** 
	 * Helper method with error messages for failed bookings
	 * @param p Passenger seeking a confirmed seat
	 * @return msg string failure reason 
	 */
	private String noSeatsAvailableMsg(Passenger p) {
		String msg = "";
		return msg + p.noSeatsMsg(); 
	}
	
	/**
	 * Helper method to check if invalid parameters have been provided in the 
	 * constructor and throws an exception provided with a message
	 * 
	 * @param flightCode <code>String</code> containing flight ID 
	 * @param departureTime <code>int</code> scheduled departure time
	 * @param first <code>int</code> capacity of First Class 
	 * @param business <code>int</code> capacity of Business Class 
	 * @param premium <code>int</code> capacity of Premium Economy Class 
	 * @param economy <code>int</code> capacity of Economy Class 
	 * @throws AircraftException if isNull(flightCode) OR (departureTime <=0) OR ({first,business,premium,economy} <0)
	 */
	private void checkInvalidNumPassengers(String flightCode, int departureTime, int first, int business, int premium, int economy) throws AircraftException {
		if (flightCode == null || departureTime <= 0 || first < 0 || business < 0 || premium < 0 || economy < 0) {
			throw new AircraftException("Invalid entries in parameters or parameters don't conform to range requirements");
		}
	}
	
	/**
	 * Helper method to check if passengers are in the aircraft seating and 
	 * throws exception provided with a message
	 * 
	 * @param p <code>Passenger</code> whose presence we are checking
	 * @throws AircraftException if <code>Passenger</code> is not recorded in aircraft seating 
	 */
	private void checkIfPassengerInSeating(Passenger p) throws AircraftException {
		if (!hasPassenger(p)) {
			throw new AircraftException("The passenger is not recorded in the aircraft seating");
		}
	}
	
	/**
	 * Helper method to check if seats are available on the aircraft and throws
	 * exception provided with a message 
	 *   
	 * @param p <code>Passenger</code> whose fare class is to be checked for seats
	 * @throws AircraftException if no seats available in <code>Passenger</code> fare class. 
	 */
	private void checkIfSeatsAreAvailable(Passenger p) throws AircraftException {
		if (!seatsAvailable(p)) {
			throw new AircraftException(noSeatsAvailableMsg(p));
		}
	}
	
	/**
	 * Helper method to check if seats are available in First class
	 * 
	 * @return boolean value depending on seats available in First class or not
	 */
	private boolean seatsAvailableFirst() {
		return this.numFirst < this.firstCapacity;
	}
	
	/**
	 * Helper method to check if seats are available in Business class
	 * 
	 * @return boolean value depending on seats available in Business class or not
	 */
	private boolean seatsAvailableBusiness() {
		return this.numBusiness < this.businessCapacity;
	}
	
	/**
	 * Helper method to check if seats are available in Premium class
	 * 
	 * @return boolean value depending on seats available in Premium class or not
	 */
	private boolean seatsAvailablePremium() {
		return this.numPremium < this.premiumCapacity;
	}
	
	/**
	 * Helper method to check if seats are available in Economy class
	 * 
	 * @return boolean value depending on seats available in Economy class or not
	 */
	private boolean seatsAvailableEconomy() {
		return this.numEconomy < this.economyCapacity;
	}
	
	/**
	 * Helper method to increment passenger counts depending on their fare 
	 * class
	 * 
	 * @param p <code>Passenger</code> whose count is to be incremented
	 */
	private void incrementCounts(Passenger p) {
		if (p instanceof First) {
			this.numFirst++;
		}
		else if (p instanceof Business) {
			this.numBusiness++;
		}
		else if (p instanceof Premium) {
			this.numPremium++;
		} else {
			this.numEconomy++;
		}
	}
	
	/**
	 * Helper method to decrement passenger counts depending on their fare class 
	 * 
	 * @param p <code>Passenger</code> whose count is to be decremented
	 */
	private void decrementCounts(Passenger p) {
		if (p instanceof First) {
			this.numFirst--;
		}
		else if (p instanceof Business) {
			this.numBusiness--;
		}
		else if (p instanceof Premium) {
			this.numPremium--;
		} else {
			this.numEconomy--;
		}
	}
	
	/**
	 * Helper method to upgrade a Business passenger to First class and increment or decrement
	 * the relevant counts 
	 * 
	 * @param p <code>Passenger</code> whose fare class and counts will be updated
	 */
	private void upgradeBusiness(Passenger p) {
		Passenger upgrade = p.upgrade();
		numBusiness--;
		numFirst++;
	}
	
	/**
	 * Helper method to upgrade a Premium passenger to Business class and increment or decrement
	 * the relevant counts 
	 * 
	 * @param p <code>Passenger</code> whose fare class and counts will be updated
	 */
	private void upgradePremium(Passenger p) {
		Passenger upgrade = p.upgrade();
		numPremium--;
		numBusiness++;
	}
	
	/**
	 * Helper method to upgrade an Economy passenger to Premium class and increment or decrement
	 * the relevant counts 
	 * 
	 * @param p <code>Passenger</code> whose fare class and counts will be updated
	 */
	private void upgradeEconomy(Passenger p) {
		Passenger upgrade = p.upgrade();
		numEconomy--;
		numPremium++;
	}
}

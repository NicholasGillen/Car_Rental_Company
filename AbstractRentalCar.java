package uk.ac.ncl._100394872_CSC8002_Assignment1;

/**
 * Stores information about a rental car's registration number,
 * the amount of fuel it currently has and whether it is currently rented.
 * Cannot construct further rental cars if the maximum number of unique
 * car registration numbers has been reached.
 * 
 * @author Nicholas Gillen
 * @version 1.0
 *
 */
public abstract class AbstractRentalCar implements RentalCar {
	
	
	private final CarRegistrationNumber registrationNumber;
	private int fuelRemaining;
	private boolean isRented;
	
	/**
	 * Constructs a new rental car, assigns it a unique
	 * registration number, sets its current fuel to 0 and
	 * sets it as currently not rented.
	 * @throws MaxNumberOfRegistrationNumbersException if no
	 * more unique car registration numbers can be created
	 */
	public AbstractRentalCar() throws MaxNumberOfRegistrationNumbersException {
		registrationNumber = new CarRegistrationNumber();
		isRented = false;
		fuelRemaining = 0;
	}
	
	/**
	 * Returns the string representation of this rental car. The
	 * string has the form "aXXXX - YY litres remaining - rental status"
	 * where aXXXX is the car's registration number, YY is the amount of fuel
	 * remaining in litres and rental status is "Rented" or "Not Rented".
	 */
	public String toString() {
		return "" + getRegistrationNumber() + " - " + getFuelRemaining() + " litres remaining" + " - " + (isCurrentlyRented() ? 
				"Rented" : "Not Rented");
	}
	
	public String getRegistrationNumber() {
		return registrationNumber.getRegistrationNumber();
	}
	
	public int getFuelRemaining() {
		return fuelRemaining;
	}

	public boolean isCurrentlyRented() {
		return isRented;
	}
	
	//OVERRIDE THIS 
	public int addFuel(int fuel) {
		fuelRemaining += fuel;
		return fuel;
	} 
	
	protected void removeFuel(int fuel) {
		fuelRemaining -= fuel;
	}
	
	public void rentedOut() {
		isRented = true;
	}
	
	public void returned() {
		isRented = false;
	}

	
	
}

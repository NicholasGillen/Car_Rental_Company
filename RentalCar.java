package uk.ac.ncl._100394872_CSC8002_Assignment1;

/**
 * A rental car stores information about the car's registration number, 
 * the amount of fuel remaining and whether or not it is rented - a rental 
 * car can be rented out to persons, be driven and have fuel added. A car 
 * cannot be driven if it is not rented out or if it has no fuel remaining.
 * 
 * @author Nicholas Gillen
 * @version 1.0
 *
 */
public interface RentalCar {
	
	/**
	 * Gets the registration number of this rental car.
	 * @return the car's registration number
	 */
	public String getRegistrationNumber();
	/**
	 * Gets the maximum capacity of this rental car's fuel tank.
	 * @return the car's maximum fuel tank capacity
	 */
	public int getFuelTankCapacity();
	/**
	 * Gets the amount of fuel remaining in this car's tank.
	 * @return the amount of fuel remaining
	 */
	public int getFuelRemaining();
	/**
	 * Checks whether this car's fuel tank is full - returns true if
	 * the tank is full.
	 * @return true if the fuel tank is full, false otherwise
	 */
	public boolean fuelTankIsFull();
	/**
	 * Checks whether this car is currently being rented by someone - 
	 * returns true if it is being rented.
	 * @return true if the car is being rented, false otherwise
	 */
	public boolean isCurrentlyRented();
	/**
	 * Adds an integer amount of fuel to the tank.
	 * @param fuel the amount of fuel to be added
	 * @return the amount of fuel added - 0 if no fuel
	 * was added
	 * @throws IllegalArgumentException if fuel added < 0
	 */
	public int addFuel(int fuel) throws IllegalArgumentException;
	/**
	 * Signifies that the car has been driven for the given
	 * distance and calculates how much fuel was consumed during the 
	 * journey.
	 * @param distance the distance driven by the car
	 * @return the amount of fuel consumed - 0 if distance is 0
	 * @throws IllegalArgumentException if distance < 0
	 */
	public int drive(int distance) throws IllegalArgumentException;
	/**
	 * Signifies that the car has been rented out to a customer and is not
	 * available to be rented out.
	 */
	public void rentedOut();
	/**
	 * Signifies that the car has been returned and is available
	 * to be rented out again.
	 */
	public void returned();
}

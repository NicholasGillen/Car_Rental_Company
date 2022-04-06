package uk.ac.ncl._100394872_CSC8002_Assignment1;

public final class LargeRentalCar extends AbstractRentalCar {

	public static final int FUEL_TANK_CAPACITY = 60;
	public static final int FUEL_CONSUMPTION_FIRST_50KM = 10;
	public static final int FUEL_CONSUMPTION_AFTER_50KM = 15;
	
	public LargeRentalCar() throws MaxNumberOfRegistrationNumbersException {
		super();
	}
	
	/**
	 * Returns the string representation of this small rental car. The
	 * string has the form "aXXXX - YY litres remaining - rental status - Large"
	 * where aXXXX is the car's registration number, YY is the amount of fuel
	 * remaining in litres and rental status is "Rented" or "Not Rented".
	 */
	public String toString() {
		return super.toString() + " - " + "Large";
	}
	
	public int getFuelTankCapacity() {
		return FUEL_TANK_CAPACITY;
	}

	public boolean fuelTankIsFull() {
		return super.getFuelRemaining() == FUEL_TANK_CAPACITY;
	}

	public int addFuel(int fuel) {
		if (fuel < 0)
			throw new IllegalArgumentException("Fuel added cannot be negative");
		if (super.isCurrentlyRented()) {
			if (!fuelTankIsFull()) {
				int fuelAdded = fuel + super.getFuelRemaining() <= FUEL_TANK_CAPACITY ? 
						fuel : FUEL_TANK_CAPACITY - super.getFuelRemaining();
				super.addFuel(fuelAdded);
				return fuelAdded;
			}
		}
		return 0;
	}

	public int drive(int distance) {
		if (distance < 0) 
			throw new IllegalArgumentException("Distance driven cannot be negative");
		if(super.isCurrentlyRented() && distance != 0 && super.getFuelRemaining() > 0) {
			if (distance <= 50) {
				int fuelConsumed = (int) Math.ceil((double)distance/FUEL_CONSUMPTION_FIRST_50KM) ;
				super.removeFuel(fuelConsumed);
				return fuelConsumed;
			}
			else {
				int fuelConsumed = (int) (Math.ceil((50/FUEL_CONSUMPTION_FIRST_50KM) + ((double)distance - 50)/FUEL_CONSUMPTION_AFTER_50KM)) ;
				super.removeFuel(fuelConsumed);
				return fuelConsumed;
			}
		}
		return 0; 
	}

	
}

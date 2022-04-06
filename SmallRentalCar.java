package uk.ac.ncl._100394872_CSC8002_Assignment1;

public final class SmallRentalCar extends AbstractRentalCar {
	
	


	public static final int FUEL_TANK_CAPACITY = 49;
	public static final int FUEL_CONSUMPTION = 20;
	
	public SmallRentalCar() throws MaxNumberOfRegistrationNumbersException {
		super();
	}
	
	/**
	 * Returns the string representation of this small rental car. The
	 * string has the form "aXXXX - YY litres remaining - rental status - Small"
	 * where aXXXX is the car's registration number, YY is the amount of fuel
	 * remaining in litres and rental status is "Rented" or "Not Rented".
	 */
	public String toString() {
		return super.toString() + " - " + "Small";
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
			int fuelConsumed = (int) Math.ceil((double)distance/FUEL_CONSUMPTION);
			super.removeFuel(fuelConsumed);
			return fuelConsumed;
		}
		return 0; 
	}



	

	
}

package uk.ac.ncl._100394872_CSC8002_Assignment1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A car rental company provides rental cars to customers who
 * are eligible to rent them, keeps track of which cars are currently
 * being rented and by whom, and which cars are available for rent. 
 * This class is a singleton - only one instance of this class can be 
 * instantiated.
 * 
 * @author Nicholas Gillen
 * @version 1.0
 *
 */
public final class CarRentalCompany {
	
	 private static final CarRentalCompany INSTANCE;
	    static {
	        try {
	            INSTANCE = new CarRentalCompany();
	        } catch (MaxNumberOfRegistrationNumbersException e) {
	            throw new ExceptionInInitializerError(e);
	        }
	    }

	private Set<RentalCar> cars;
	private Map<DrivingLicence, RentalCar> rentedCars;

	private CarRentalCompany() throws MaxNumberOfRegistrationNumbersException  {
		cars = new HashSet<>();
		for (int i = 0; i < 30; i++)
			cars.add(new SmallRentalCar());
		for (int i = 0; i < 20; i++)
			cars.add(new LargeRentalCar());
		rentedCars = new HashMap<>();
	}
	
	/**
	 * Creates a new car rental company and allocates 50 
	 * rental cars as available to rent (30 small, 20 large) - 
	 * only one rental company can be instantiated.
	 * @return
	 */
	public static final CarRentalCompany getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Gets a list of cars of the specified type currently available 
	 * for rent. A car currently rented out is not available to be 
	 * rented until returned.
	 * @param typeOfCar the type of rental car being enquired
	 * about - small or large
	 * @return the list of cars available for rent of the specified 
	 * type
	 */
	public ArrayList<RentalCar> availableCars(String typeOfCar) {
		ArrayList<RentalCar> carsAvailable = new ArrayList<>();
		if (typeOfCar.equalsIgnoreCase("small"))
			for (RentalCar car : cars)
				if (car instanceof SmallRentalCar)
					if (!rentedCars.containsValue(car))
						carsAvailable.add(car);
		if (typeOfCar.equalsIgnoreCase("large"))
			for (RentalCar car : cars)
				if (car instanceof LargeRentalCar)
					if (!rentedCars.containsValue(car))
						carsAvailable.add(car);
		return carsAvailable;

	}
	
	/**
	 * Gets a list of cars currently being rented by customers.
	 * @return the list of cars currently being rented
	 */
	public ArrayList<RentalCar> getRentedCars() {
		return new ArrayList<RentalCar>(rentedCars.values());
	}
	
	/**
	 * Gets the car that is currently being rented by the person
	 * associated with the specified driving licence. 
	 * @param drivingLicence the driving licence of the person
	 * @return the car being rented by the person - null if person
	 * isn't currently renting any cars
	 */
	public RentalCar getCar(DrivingLicence drivingLicence) {
		return rentedCars.get(drivingLicence);
	}
	
	/**
	 * Provides a car of the specified type to the person associated
	 * with the specified driving licence, if a car is available and 
	 * the person satisfies the eligibility criteria, defined as follows:
	 * Small Car: At least 21 years old, held licence at least 1 year.
	 * Large Car: At least 25 years old, held licence at least 5 years.
	 * Car is provided with a full tank of petrol.
	 * @param drivingLicence the driving licence of the person 
	 * @param typeOfCar the type of rental car - small of large
	 * @return true if the car was successfully issued to the person,
	 * false otherwise
	 */
	public boolean issueCar(DrivingLicence drivingLicence, String typeOfCar) {
		if (typeOfCar.equalsIgnoreCase("small"))
			if (drivingLicence.isFull() && !(rentedCars.containsKey(drivingLicence))) {
				Calendar ageCutOff = Calendar.getInstance();
				ageCutOff.add(Calendar.YEAR, -21);
				Calendar licenceCutOff = Calendar.getInstance();
				licenceCutOff.add(Calendar.YEAR, -1);
				if (drivingLicence.getDriverDateOfBirth().before(ageCutOff.getTime())
						&& drivingLicence.getLicenceDateOfIssue().before(licenceCutOff.getTime())) {

					if (availableCars("small").size() != 0) {
						RentalCar toRent = availableCars("small").get(0); 
						rentedCars.put(drivingLicence, toRent);
						toRent.rentedOut();
						toRent.addFuel(toRent.getFuelTankCapacity());
						return true;
					}
				}
			}
		if (typeOfCar.equalsIgnoreCase("large"))
			if (drivingLicence.isFull() && !(rentedCars.containsKey(drivingLicence))) {
				Calendar ageCutOff = Calendar.getInstance();
				ageCutOff.add(Calendar.YEAR, -25);
				Calendar licenceCutOff = Calendar.getInstance();
				licenceCutOff.add(Calendar.YEAR, -5);
				if (drivingLicence.getDriverDateOfBirth().before(ageCutOff.getTime())
						&& drivingLicence.getLicenceDateOfIssue().before(licenceCutOff.getTime())) {
					if (availableCars("large").size() != 0) {
						RentalCar toRent = availableCars("large").get(0); 
						rentedCars.put(drivingLicence, toRent);
						toRent.rentedOut();
						toRent.addFuel(toRent.getFuelTankCapacity());
						return true;
					}
				}
			}
		return false;
	}
	
	/**
	 * Terminates the rental contract for the person associated with
	 * the specified driving licence, returns the car they were
	 * renting and calculates how much fuel is required to fill up
	 * the fuel tank to its maximum capacity. The car become available to 
	 * be rented again.
	 * @param drivingLicence the driving licence of the person
	 * @return how much fuel is needed to fill up the tank to maximum
	 * capacity, 0 if the specified driving licence is not currently
	 * renting a car
	 */
	public int terminateRental(DrivingLicence drivingLicence) {
		RentalCar toReturn = rentedCars.get(drivingLicence);
		if (toReturn != null) {
			int fuelToAdd = toReturn.getFuelTankCapacity() - toReturn.getFuelRemaining();
			toReturn.returned();
			rentedCars.remove(drivingLicence);
			return fuelToAdd;
		}
		return 0;
	}
}

package uk.ac.ncl._100394872_CSC8002_Assignment1;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CarRentalCompanyTest {

	Name n1, n2, n3;
	Calendar doi1, doi2, doi3, dob1, dob2, dob3;
	DrivingLicence dl1, dl2, dl3;

	@Before
	public void setUp() {
		doi1 = Calendar.getInstance();
		doi1.set(1999, 9, 9);
		doi2 = Calendar.getInstance();
		doi2.set(2015, 6, 3);
		doi3 = Calendar.getInstance();
		doi3.set(2002, 1, 29);
		n1 = new Name("Will", "Smith");
		n2 = new Name("Arnold", "Jackman");
		n3 = new Name("John", "Dwayne");
		dob1 = Calendar.getInstance();
		dob1.set(1963, 9, 9);
		dob2 = Calendar.getInstance();
		dob2.set(1992, 6, 3);
		dob3 = Calendar.getInstance();
		dob3.set(1980, 1, 29);
		dl1 = DrivingLicence.getInstance(n1, dob1.getTime(), doi1.getTime(), true);
		dl2 = DrivingLicence.getInstance(n2, dob2.getTime(), doi2.getTime(), true);
		dl3 = DrivingLicence.getInstance(n3, dob3.getTime(), doi3.getTime(), true);
	}
	
	@Test
	public void constructCarRentalCompanyTest() {
		CarRentalCompany company = CarRentalCompany.getInstance();
		assertEquals(30, company.availableCars("Small").size());
		assertEquals(20, company.availableCars("Large").size());
		assertEquals(0, company.getRentedCars().size());
	}
	
	@Test
	public void carRentalCompanyUniquenessTest() {
		CarRentalCompany company = CarRentalCompany.getInstance();
		CarRentalCompany alsoCompany = CarRentalCompany.getInstance();
		assertTrue(company == alsoCompany);
	}
	
	@Test
	public void issueSmallCarNormalTest() {
		CarRentalCompany company = CarRentalCompany.getInstance();
		assertEquals(true, company.issueCar(dl1, "small"));
		RentalCar rentedCar = company.getCar(dl1);
		assertTrue(company.getRentedCars().contains(rentedCar));
		assertFalse(company.availableCars("small").contains(rentedCar));
		assertTrue(rentedCar.isCurrentlyRented());
		assertTrue(rentedCar.fuelTankIsFull());
		// Return car - reset for other tests
		company.terminateRental(dl1);
	}
	
	@Test
	public void issueSmallCarDrivingLicenceAlreadyRentingACarTest() {
		CarRentalCompany company = CarRentalCompany.getInstance();
		assertEquals(true, company.issueCar(dl1, "small"));
		assertEquals(false, company.issueCar(dl1, "small"));
		// Return car - reset for other tests
		company.terminateRental(dl1);
	}
	
	@Test
	public void issueSmallCarNotFullLicenceTest() {
		CarRentalCompany company = CarRentalCompany.getInstance();
		DrivingLicence licence = DrivingLicence.getInstance(new Name("Test", "One"), dob1.getTime(), doi1.getTime(), false);
		assertEquals(false, company.issueCar(licence, "small"));
	}
	
	@Test
	public void issueSmallCarUnder21Test() {
		CarRentalCompany company = CarRentalCompany.getInstance();
		Calendar under21 = Calendar.getInstance();
		under21.add(Calendar.YEAR, -17);
		DrivingLicence licence = DrivingLicence.getInstance(new Name("Test", "Two"), under21.getTime(), doi1.getTime(), true);
		assertEquals(false, company.issueCar(licence, "small"));
	}
	
	@Test
	public void issueSmallCarLicenceUnder1YearTest() {
		CarRentalCompany company = CarRentalCompany.getInstance();
		Calendar licenceUnder1 = Calendar.getInstance();
		licenceUnder1.add(Calendar.MONTH, -11);
		DrivingLicence licence = DrivingLicence.getInstance(new Name("Test", "Three"), dob1.getTime(), licenceUnder1.getTime(), true);
		assertEquals(false, company.issueCar(licence, "small"));
	}
	
	@Test
	public void issueSmallCarNoCarsAvailableTest() {
		CarRentalCompany company = CarRentalCompany.getInstance();
		String fName = "a";
		String lName = "b";
		for (int i = 0; i < 30; i++) {
			company.issueCar(DrivingLicence.getInstance(new Name(fName, lName), dob1.getTime(), doi1.getTime(), true), "small");
			fName += "a";
			lName +="b";
		}
		assertEquals(0, company.availableCars("small").size());
		assertEquals(false, company.issueCar(dl1, "small"));
		// Return cars
		for (int i = 0; i < 31; i++) {
			company.terminateRental(DrivingLicence.getInstance(new Name(fName, lName), dob1.getTime(), doi1.getTime(), true));
			fName = fName.substring(0, fName.length()-1);
			lName = lName.substring(0, lName.length()-1);
		}
	}
	
	@Test
	public void issueLargeCarNormalTest() {
		CarRentalCompany company = CarRentalCompany.getInstance();
		assertEquals(true, company.issueCar(dl1, "large"));
		RentalCar rentedCar = company.getCar(dl1);
		assertTrue(company.getRentedCars().contains(rentedCar));
		assertFalse(company.availableCars("large").contains(rentedCar));
		assertTrue(rentedCar.isCurrentlyRented());
		assertTrue(rentedCar.fuelTankIsFull());
		// Return car - reset for other tests
		company.terminateRental(dl1);
	}
	
	@Test
	public void issueLargeCarDrivingLicenceAlreadyRentingACarTest() {
		CarRentalCompany company = CarRentalCompany.getInstance();
		assertEquals(true, company.issueCar(dl1, "large"));
		assertEquals(false, company.issueCar(dl1, "large"));
		// Return car - reset for other tests
		company.terminateRental(dl1);
	}
	
	@Test
	public void issueLargeCarNotFullLicenceTest() {
		CarRentalCompany company = CarRentalCompany.getInstance();
		DrivingLicence licence = DrivingLicence.getInstance(new Name("Test", "Four"), dob1.getTime(), doi1.getTime(), false);
		assertEquals(false, company.issueCar(licence, "large"));
	}
	
	@Test
	public void issueLargeCarUnder25Test() {
		CarRentalCompany company = CarRentalCompany.getInstance();
		Calendar under21 = Calendar.getInstance();
		under21.add(Calendar.YEAR, -24);
		DrivingLicence licence = DrivingLicence.getInstance(new Name("Test", "Five"), under21.getTime(), doi1.getTime(), true);
		assertEquals(false, company.issueCar(licence, "large"));
	}
	
	@Test
	public void issueLargeCarLicenceUnder5YearTest() {
		CarRentalCompany company = CarRentalCompany.getInstance();
		Calendar licenceUnder1 = Calendar.getInstance();
		licenceUnder1.add(Calendar.YEAR, -4);
		DrivingLicence licence = DrivingLicence.getInstance(new Name("Test", "Six"), dob1.getTime(), licenceUnder1.getTime(), true);
		assertEquals(false, company.issueCar(licence, "large"));
	}
	
	@Test
	public void terminateRentalTest() {
		CarRentalCompany company = CarRentalCompany.getInstance();
		company.issueCar(dl1, "small");
		RentalCar issuedCar = company.getCar(dl1);
		issuedCar.drive(40);
		int fuelNeededToAdd = issuedCar.getFuelTankCapacity() - issuedCar.getFuelRemaining();
		assertEquals(fuelNeededToAdd, company.terminateRental(dl1));
		assertFalse(company.getRentedCars().contains(issuedCar));
		assertTrue(company.availableCars("small").contains(issuedCar));
		assertEquals(false, issuedCar.isCurrentlyRented());
	}
	
	@Test
	public void terminateRentalNoContractTest() {
		CarRentalCompany company = CarRentalCompany.getInstance();
		assertEquals(0, company.terminateRental(dl1));
		assertEquals(0, company.terminateRental(null));		
	}
	
	@Test
	public void availableCarsTest() {
		CarRentalCompany company = CarRentalCompany.getInstance();
		assertEquals(30, company.availableCars("small").size());
		assertEquals(20, company.availableCars("large").size());
		company.issueCar(dl1, "small");
		company.issueCar(dl2, "small");
		company.issueCar(dl3, "large");
		assertEquals(28, company.availableCars("small").size());
		assertEquals(19, company.availableCars("large").size());
		// Return cars
		company.terminateRental(dl1);
		company.terminateRental(dl2);
		company.terminateRental(dl3);
	}
	
	@Test
	public void getCarTest() {
		CarRentalCompany company = CarRentalCompany.getInstance();
		company.issueCar(dl1, "Large");
		RentalCar car = company.getRentedCars().get(0);
		assertEquals(car, company.getCar(dl1));
		// Return car - reset for other tests
		company.terminateRental(dl1);
	}
	
	@Test
	public void getCarNoCarRentedToDrivingLicenceTest() {
		CarRentalCompany company = CarRentalCompany.getInstance();
		assertEquals(null, company.getCar(dl1));
	}
	
	@Test
	public void getRentedCarsTest() {
		CarRentalCompany company = CarRentalCompany.getInstance();
		assertEquals(0, company.getRentedCars().size());
		company.issueCar(dl1, "large");
		company.issueCar(dl2, "small");
		company.issueCar(dl3, "small");
		assertEquals(3, company.getRentedCars().size());
		RentalCar car1 = company.getCar(dl1);
		RentalCar car2 = company.getCar(dl2);
		RentalCar car3 = company.getCar(dl3);
		assertTrue(company.getRentedCars().contains(car1));
		assertTrue(company.getRentedCars().contains(car2));
		assertTrue(company.getRentedCars().contains(car3));
		company.terminateRental(dl1);
		assertFalse(company.getRentedCars().contains(car1));
		company.terminateRental(dl2);
		assertFalse(company.getRentedCars().contains(car2));
		company.terminateRental(dl3);
		assertFalse(company.getRentedCars().contains(car3));
	}

}

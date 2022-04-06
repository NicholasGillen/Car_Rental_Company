package uk.ac.ncl._100394872_CSC8002_Assignment1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LargeRentalCarTest {
	
	static RentalCar car;
	RentalCar largeCar;
	
	@BeforeClass
	public static void setUpBeforeClass() throws MaxNumberOfRegistrationNumbersException {
		car = new LargeRentalCar();
	}
	
	@Before
	public void setUp() throws MaxNumberOfRegistrationNumbersException {
	largeCar = new LargeRentalCar();
	}
	
	@Test
	public void constructLargeRentalCarTest() throws MaxNumberOfRegistrationNumbersException {
		RentalCar largeCar = new LargeRentalCar();
		assertEquals(0, largeCar.getFuelRemaining());
		assertEquals(false, largeCar.isCurrentlyRented());
	}
	
	@Test
	public void toStringTest() {
		assertEquals("a0000 - 0 litres remaining - Not Rented - Large", car.toString());
		car.rentedOut();
		car.addFuel(5);
		assertEquals("a0000 - 5 litres remaining - Rented - Large", car.toString());
		car.drive(50);
		car.returned();
		assertEquals("a0000 - 0 litres remaining - Not Rented - Large", car.toString());
	}
	
	@Test
	public void getFuelTankCapacityTest() {
		assertEquals(LargeRentalCar.FUEL_TANK_CAPACITY, car.getFuelTankCapacity());
	}
	
	@Test
	public void addFuelTest() throws MaxNumberOfRegistrationNumbersException {
		assertEquals(0, largeCar.addFuel(30));
		largeCar.rentedOut();
		assertEquals(30, largeCar.addFuel(30));
	}
	
	@Test
	public void addFuelZeroAmountTest() throws MaxNumberOfRegistrationNumbersException {
		largeCar.rentedOut();
		assertEquals(0, largeCar.addFuel(0));
	}
	
	@Test
	public void addFuelNegativeAmountTest() throws MaxNumberOfRegistrationNumbersException {
		largeCar.rentedOut();
		try {
			largeCar.addFuel(-50);
			fail("Should have thrown exception");
		}
		catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("Fuel added cannot be negative"));
		}
	}
	
	@Test
	public void driveTest() throws MaxNumberOfRegistrationNumbersException {
		assertEquals(0, largeCar.drive(50));
		largeCar.rentedOut();
		assertEquals(0, largeCar.drive(50));
		largeCar.addFuel(60);
		assertEquals(1, largeCar.drive(1));
		assertEquals(1, largeCar.drive(9));
		assertEquals(1, largeCar.drive(10));
		assertEquals(2, largeCar.drive(11));
		assertEquals(5, largeCar.drive(50));
		assertEquals(6, largeCar.drive(51));
		assertEquals(6, largeCar.drive(64));
		assertEquals(6, largeCar.drive(65));
		assertEquals(7, largeCar.drive(66));
		assertEquals(10, largeCar.drive(125));
		
	}
	
	@Test
	public void driveZeroDistanceTest() throws MaxNumberOfRegistrationNumbersException {
		largeCar.rentedOut();
		assertEquals(0, largeCar.drive(0));
	}
	
	@Test
	public void driveNegativeDistanceTest() throws MaxNumberOfRegistrationNumbersException {
		try {
			largeCar.drive(-50);
			fail("Should have thrown exception");
		}
		catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("Distance driven cannot be negative"));
		}
	}
	
	@Test
	public void fuelTankIsFullTest() throws MaxNumberOfRegistrationNumbersException {
		largeCar.rentedOut();
		assertEquals(false, largeCar.fuelTankIsFull());
		largeCar.addFuel(LargeRentalCar.FUEL_TANK_CAPACITY);
		assertEquals(true, largeCar.fuelTankIsFull());
		largeCar.drive(30);
		assertEquals(false, largeCar.fuelTankIsFull());
	}
	
	@Test
	public void getCarRegistrationNumberTest() {
		assertEquals("a0000", car.getRegistrationNumber());
	}
	
	@Test
	public void isCurrentlyRentedTest() throws MaxNumberOfRegistrationNumbersException {
		assertEquals(false, largeCar.isCurrentlyRented());
		largeCar.rentedOut();
		assertEquals(true, largeCar.isCurrentlyRented());
		largeCar.returned();
		assertEquals(false, largeCar.isCurrentlyRented());
	}
	
	@Test
	public void getFuelRemainingTest() {
		assertEquals(0, largeCar.getFuelRemaining());
		largeCar.rentedOut();
		largeCar.addFuel(20);
		assertEquals(20, largeCar.getFuelRemaining());
		largeCar.addFuel(10000);
		assertEquals(LargeRentalCar.FUEL_TANK_CAPACITY, largeCar.getFuelRemaining());
	}

}

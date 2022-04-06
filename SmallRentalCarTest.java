package uk.ac.ncl._100394872_CSC8002_Assignment1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SmallRentalCarTest {
	
	static RentalCar car;
	RentalCar smallCar;
	
	@BeforeClass
	public static void setUpBeforeClass() throws MaxNumberOfRegistrationNumbersException {
		car = new SmallRentalCar();
	}
	
	@Before
	public void setUp() throws MaxNumberOfRegistrationNumbersException {
	smallCar = new SmallRentalCar();
	}
	
	@Test
	public void constructSmallRentalCarTest() throws MaxNumberOfRegistrationNumbersException {
		RentalCar smallCar = new SmallRentalCar();
		assertEquals(0, smallCar.getFuelRemaining());
		assertEquals(false, smallCar.isCurrentlyRented());
	}
	
	@Test
	public void toStringTest() {
		assertEquals("a0000 - 0 litres remaining - Not Rented - Small", car.toString());
		car.rentedOut();
		car.addFuel(30);
		assertEquals("a0000 - 30 litres remaining - Rented - Small", car.toString());
		car.drive(600);
		car.returned();
		assertEquals("a0000 - 0 litres remaining - Not Rented - Small", car.toString());
	}
	
	@Test
	public void getFuelTankCapacityTest() {
		assertEquals(SmallRentalCar.FUEL_TANK_CAPACITY, car.getFuelTankCapacity());
	}
	
	@Test
	public void addFuelTest() throws MaxNumberOfRegistrationNumbersException {
		assertEquals(0, smallCar.addFuel(30));
		smallCar.rentedOut();
		assertEquals(30, smallCar.addFuel(30));
	}
	
	@Test
	public void addFuelZeroAmountTest() throws MaxNumberOfRegistrationNumbersException {
		smallCar.rentedOut();
		assertEquals(0, smallCar.addFuel(0));
	}
	
	@Test
	public void addFuelNegativeAmountTest() throws MaxNumberOfRegistrationNumbersException {
		smallCar.rentedOut();
		try {
			smallCar.addFuel(-50);
			fail("Should have thrown exception");
		}
		catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("Fuel added cannot be negative"));
		}
	}
	
	@Test
	public void driveTest() throws MaxNumberOfRegistrationNumbersException {
		assertEquals(0, smallCar.drive(50));
		smallCar.rentedOut();
		assertEquals(0, smallCar.drive(50));
		smallCar.addFuel(49);
		assertEquals(1, smallCar.drive(1));
		assertEquals(1, smallCar.drive(10));
		assertEquals(1, smallCar.drive(19));
		assertEquals(1, smallCar.drive(20));
		assertEquals(2, smallCar.drive(21));
		assertEquals(12, smallCar.drive(225));
		assertEquals(500, smallCar.drive(10000));
		smallCar.returned();
		assertEquals(0, smallCar.drive(60));
	}
	
	@Test
	public void driveZeroDistanceTest() throws MaxNumberOfRegistrationNumbersException {
		smallCar.rentedOut();
		assertEquals(0, smallCar.drive(0));
	}
	
	@Test
	public void driveNegativeDistanceTest() throws MaxNumberOfRegistrationNumbersException {
		try {
			smallCar.drive(-50);
			fail("Should have thrown exception");
		}
		catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("Distance driven cannot be negative"));
		}
	}
	
	@Test
	public void fuelTankIsFullTest() throws MaxNumberOfRegistrationNumbersException {
		smallCar.rentedOut();
		assertEquals(false, smallCar.fuelTankIsFull());
		smallCar.addFuel(SmallRentalCar.FUEL_TANK_CAPACITY);
		assertEquals(true, smallCar.fuelTankIsFull());
		smallCar.drive(30);
		assertEquals(false, smallCar.fuelTankIsFull());
	}
	
	@Test
	public void getCarRegistrationNumberTest() {
		assertEquals("a0000", car.getRegistrationNumber());
	}
	
	@Test
	public void isCurrentlyRentedTest() throws MaxNumberOfRegistrationNumbersException {
		assertEquals(false, smallCar.isCurrentlyRented());
		smallCar.rentedOut();
		assertEquals(true, smallCar.isCurrentlyRented());
		smallCar.returned();
		assertEquals(false, smallCar.isCurrentlyRented());
	}
	
	@Test
	public void getFuelRemainingTest() {
		assertEquals(0, smallCar.getFuelRemaining());
		smallCar.rentedOut();
		smallCar.addFuel(20);
		assertEquals(20, smallCar.getFuelRemaining());
		smallCar.addFuel(10000);
		assertEquals(SmallRentalCar.FUEL_TANK_CAPACITY, smallCar.getFuelRemaining());
	}

}

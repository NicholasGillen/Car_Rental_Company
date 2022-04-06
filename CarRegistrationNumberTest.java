package uk.ac.ncl._100394872_CSC8002_Assignment1;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class CarRegistrationNumberTest {
	
	static CarRegistrationNumber[] regNums;

	@BeforeClass
	public static void setUpBeforeClass() throws MaxNumberOfRegistrationNumbersException {
		regNums = new CarRegistrationNumber[260000];
		for (int i = 0; i < 260000; i++) 
			regNums[i] = new CarRegistrationNumber();
	}

	@Test
	public void carRegistrationNumberGeneratorTest() {
		assertEquals("a0000", regNums[0].getRegistrationNumber());
		assertEquals("a0009", regNums[9].getRegistrationNumber());
		assertEquals("a0010", regNums[10].getRegistrationNumber());
		assertEquals("a0100", regNums[100].getRegistrationNumber());
		assertEquals("a1000", regNums[1000].getRegistrationNumber());
		assertEquals("a9999", regNums[9999].getRegistrationNumber());
		assertEquals("b0000", regNums[10000].getRegistrationNumber());
		assertEquals("z9999", regNums[259999].getRegistrationNumber());
		
		try {
			CarRegistrationNumber exceedsMaxNumber = new CarRegistrationNumber();
			fail("Should have thrown exception");
		}
		catch (MaxNumberOfRegistrationNumbersException e) {
			assertTrue(e.getMessage().equals("Cannot create anymore unique Car Registration Numbers"));
		}
	}
	
	@Test
	public void getLetterTest() {
		assertEquals('a', regNums[0].getLetter());
	}
	
	@Test
	public void getNumberTest() {
		assertEquals("0000", regNums[0].getNumber());
	}
	
	@Test
	public void toStringTest() {
		assertEquals("a0000", regNums[0].toString());
	}
	
	@Test
	public void getRegistrationNumberTest() {
		assertEquals("a0000", regNums[0].getRegistrationNumber());
	}

}

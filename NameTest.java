package uk.ac.ncl._100394872_CSC8002_Assignment1;

import static org.junit.Assert.*;

import org.junit.Test;

public class NameTest {

	@Test // Also tests getFirstName() and getLastName()
	public void constructNameTest() {
		Name bilbo = new Name("Bilbo", "Baggins");
		assertEquals("Bilbo", bilbo.getFirstName());
		assertEquals("Baggins", bilbo.getLastName());
	}

	@Test(expected = NullPointerException.class)
	public void constructNameNullValuesTest() {
		Name nullName = new Name(null, null);
		fail("Should have thrown exception");
	}

	@Test
	public void constructNameNullFirstNameTest() {
		try {
			Name nullFirstName = new Name(null, "Baggins");
		} catch (NullPointerException e) {
			assertTrue(e.getMessage().equals("First name cannot be null"));
		}
	}

	@Test
	public void constructNameNullLastNameTest() {
		try {
			Name nullLastName = new Name("Bilbo", null);
		} catch (NullPointerException e) {
			assertTrue(e.getMessage().equals("Last name cannot be null"));
		}
	}

	@Test
	public void toStringTest() {
		Name nick = new Name("Nicholas", "Gillen");
		assertEquals("Nicholas Gillen", nick.toString());
	}

	@Test
	public void valueOfTest() {
		String bilboString = "Bilbo Baggins";
		Name bilboName = Name.valueOf(bilboString);
		assertEquals("Bilbo", bilboName.getFirstName());
		assertEquals("Baggins", bilboName.getLastName());	
		
		Name nick = new Name("Nicholas", "Gillen");
		Name nickAgain = Name.valueOf(nick.toString());
		assertEquals("Nicholas", nickAgain.getFirstName());
		assertEquals("Gillen", nickAgain.getLastName());
	}
}

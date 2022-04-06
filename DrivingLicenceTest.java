package uk.ac.ncl._100394872_CSC8002_Assignment1;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class DrivingLicenceTest {
	
	Name n1, n2, n3;
	Calendar doi1, doi2, doi3, dob1, dob2, dob3;
	
	
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
	}

	@Test
	public void constructDrivingLicenceTest() {
		DrivingLicence dl1 = DrivingLicence.getInstance(n1, dob1.getTime(), doi1.getTime(), true);
		DrivingLicence dl2 = DrivingLicence.getInstance(n2, dob2.getTime(), doi2.getTime(), true);
		DrivingLicence dl3 = DrivingLicence.getInstance(n3, dob3.getTime(), doi3.getTime(), true);
		assertEquals("Will", dl1.getName().getFirstName());
		assertEquals("Smith", dl1.getName().getLastName());
		assertEquals(dob1.getTime(), dl1.getDriverDateOfBirth());
		assertEquals(doi1.getTime(), dl1.getLicenceDateOfIssue());
		assertEquals(true, dl1.isFull());
		assertEquals("WS-1999-1", dl1.getLicenceNumber());
		assertEquals("AJ-2015-2", dl2.getLicenceNumber());
		assertEquals("JD-2002-3", dl3.getLicenceNumber());
		
		// Test Uniqueness
		DrivingLicence dl1Copy = DrivingLicence.getInstance(n1, dob1.getTime(), doi1.getTime(), true);
		assertEquals(dl1Copy, dl1);
		
		// Test toString() here due to random order of JUnit tests affecting instances
		assertEquals("Will Smith - Oct 09 1963 - WS-1999-1 - Full", dl1.toString());
	}
	
	@Test
	public void constructDrivingLicenceNullNameTest() {
		try {
			DrivingLicence licence = DrivingLicence.getInstance(null, dob1.getTime(), doi1.getTime(), true);
			fail("Should have thrown exception");
		}
		catch (NullPointerException e) {
			assertTrue(e.getMessage().equals("Name cannot be null"));
		}
	}
	
	@Test
	public void constructDrivingLicenceNullDateOfBirthTest() {
		try {
			DrivingLicence licence = DrivingLicence.getInstance(n1, null, doi1.getTime(), true);
			fail("Should have thrown exception");
		}
		catch (NullPointerException e) {
			assertTrue(e.getMessage().equals("Date of birth cannot be null"));
		}
	}
	
	@Test
	public void constructDrivingLicenceNullIssueDateTest() {
		try {
			DrivingLicence licence = DrivingLicence.getInstance(n1, dob1.getTime(), null, true);
			fail("Should have thrown exception");
		}
		catch (NullPointerException e) {
			assertTrue(e.getMessage().equals("Issue date cannot be null"));
		}
	}

}

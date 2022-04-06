package uk.ac.ncl._100394872_CSC8002_Assignment1;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class LicenceNumberTest {
	
	Name n1, n2, n3;
	Calendar doi1, doi2, doi3;
	
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
	}
	
	@Test
	public void licenceNumberGeneratorTest() {
		LicenceNumber l1 = LicenceNumber.getInstance(n1, doi1.getTime());
		LicenceNumber l2 = LicenceNumber.getInstance(n2, doi2.getTime());
		LicenceNumber l3 = LicenceNumber.getInstance(n3, doi3.getTime());
		
		assertEquals("WS-1999-1", l1.getLicenceNumber());
		assertEquals("AJ-2015-2", l2.getLicenceNumber());
		assertEquals("JD-2002-3", l3.getLicenceNumber());
	}
	
	@Test
	public void uniqueInstanceTest() {
		LicenceNumber licence = LicenceNumber.getInstance(n1, doi1.getTime());
		LicenceNumber sameLicence = LicenceNumber.getInstance(n1, doi1.getTime());
		assertEquals(licence, sameLicence);
		
		LicenceNumber sameNameDiffDateLicence = LicenceNumber.getInstance(n1, doi2.getTime());
		assertNotEquals(licence, sameNameDiffDateLicence);
		
		LicenceNumber sameDateDiffNameLicence = LicenceNumber.getInstance(n2, doi1.getTime());
		assertNotEquals(licence, sameDateDiffNameLicence);
	}
	
	@Test
	public void constructLicenceNumberNullNameTest() {
		try {
			LicenceNumber licence = LicenceNumber.getInstance(null, doi1.getTime());
			fail("Should have thrown exception");
		}
		catch (NullPointerException e) {
			assertTrue(e.getMessage().equals("Name cannot be null"));
		}
	}
	
	@Test
	public void constructLicenceNumberNullIssueDateTest() {
		try {
			LicenceNumber licence = LicenceNumber.getInstance(n1, null);
			fail("Should have thrown exception");
		}
		catch (NullPointerException e) {
			assertTrue(e.getMessage().equals("Issue date cannot be null"));
		}
	}
	
	@Test
	public void toStringTest() {
		LicenceNumber licence = LicenceNumber.getInstance(n1, doi1.getTime());
		assertEquals("WS-1999-1", licence.toString());
	}
	
	@Test
	public void getLicenceNumberTest() {
		LicenceNumber licence = LicenceNumber.getInstance(n1, doi1.getTime());
		assertEquals("WS-1999-1", licence.getLicenceNumber());
	}

}

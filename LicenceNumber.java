package uk.ac.ncl._100394872_CSC8002_Assignment1;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A licence number is a unique string made up of a 
 * person's initials, the year the licence was
 * issued and a serial number. To be used in conjunction
 * with a driving licence.
 * @author NickG
 *
 */
public final class LicenceNumber {
	
	private static final Map<String, LicenceNumber> LICENCE_NUMBERS
		= new HashMap<>();
	
	private String licence;
	private static int serialNumber = 1;
	
	private LicenceNumber(Name name, Date dateOfIssue) {
		char firstInitial = name.getFirstName().charAt(0);
		char lastInitial = name.getLastName().charAt(0);
		String yearOfIssue = dateOfIssue.toString().split(" ")[5];
		licence = "" + firstInitial + lastInitial + "-" + yearOfIssue + "-" + serialNumber;
		serialNumber++;
	}
	
	/**
	 * Creates a new licence number from the given name and
	 * the date the licence was issued and allocates a 
	 * unique serial number to ensure uniqueness.
	 * @param name the person's name
	 * @param dateOfIssue the issue date of the licence
	 * @return a new licence number
	 */
	public static final LicenceNumber getInstance(Name name, Date dateOfIssue) {
		if (name == null) 
			throw new NullPointerException("Name cannot be null");
		if (dateOfIssue == null)
			throw new NullPointerException("Issue date cannot be null");
		String[] dateRep = dateOfIssue.toString().split(" ");
		String key = "" + name + " - " + dateRep[1] + dateRep[2] + dateRep[5];
		LicenceNumber ln = LICENCE_NUMBERS.get(key);
		if (ln == null) {
			ln = new LicenceNumber(name, dateOfIssue);
			LICENCE_NUMBERS.put(key, ln);
		}
		return ln;
			
	}
	
	/**
	 * Returns the string representation of this 
	 * licence number. The string is in the form
	 * XX-YYYY-Z, where XX is the person's initials, 
	 * YYYY is the issue date of the licence and Z is the
	 * unique serial number of the licence.
	 */
	public String toString() {
		return licence;
	}
	
	/**
	 * Returns the licence number.
	 * @return the licence number
	 */
	public String getLicenceNumber() {
		return toString();
	}
	
}

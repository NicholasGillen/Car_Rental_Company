package uk.ac.ncl._100394872_CSC8002_Assignment1;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores information about a person's name, date of birth,
 * date of issue of the licence, licence number and whether
 * or not it is a full licence. 
 * 
 * @author Nicholas Gillen
 * @version 1.0
 *
 */
public final class DrivingLicence {
	
	public static final Map<LicenceNumber, DrivingLicence> DRIVING_LICENCES
	= new HashMap<>();
	
	private final Name driverName;
	private final LicenceNumber licenceNumber;
	private final Date driverDateOfBirth;
	private final Date licenceDateOfIssue;
	private final boolean isFull;

	private DrivingLicence(Name driverName, Date driverDateOfBirth, Date licenceDateOfIssue, boolean isFull) {
		if (driverName ==null)
			throw new NullPointerException("Name cannot be null");
		if (driverDateOfBirth ==null)
			throw new NullPointerException("Date of birth cannot be null");
		if (licenceDateOfIssue ==null)
			throw new NullPointerException("Issue date cannot be null");
		
		this.driverName = driverName;
		this.driverDateOfBirth = new Date(driverDateOfBirth.getTime());
		this.licenceDateOfIssue = new Date(licenceDateOfIssue.getTime());
		this.isFull = isFull;
		licenceNumber = LicenceNumber.getInstance(driverName, this.licenceDateOfIssue);
	}
	
	/**
	 * Gets a driving licence instance given a driver's name,
	 * date of birth, issue date of the licence and whether the licence
	 * is a full licence and generates a unique licence number for the
	 * licence - returns a new instance if a licence hasn't been 
	 * created for the driver, or the same instance if a licence has
	 * already been created for the driver.
	 * @param driverName the name of the driver
	 * @param driverDateOfBirth the date of birth of the driver
	 * @param licenceDateOfIssue the issue date of the licence
	 * @param isFull true if the licence is a full licence
	 * @return the driving licence instance for the driver's details
	 */
	public static final DrivingLicence getInstance(Name driverName, Date driverDateOfBirth, Date licenceDateOfIssue, boolean isFull) {
		LicenceNumber key = LicenceNumber.getInstance(driverName, licenceDateOfIssue);
		DrivingLicence licence = DRIVING_LICENCES.get(key);
		if (licence == null) {
			licence = new DrivingLicence(driverName, driverDateOfBirth, licenceDateOfIssue, isFull);
			DRIVING_LICENCES.put(key, licence);
		}
		return licence;
	}
	
	/**
	 * Returns the string representation of this driving licence. The
	 * string is in the form "Name - MM DD YYYY - XX-YYYY-Z - status", 
	 * where MM DD YYYY is the date of birth of the licence holder, 
	 * XX-YYYY-Z is the licence number and status is either "Full" or
	 * "Not Full".
	 */
	public String toString() {
		return "" + driverName + " - " + driverDateOfBirth.toString().split(" ")[1] + " " +
				driverDateOfBirth.toString().split(" ")[2] + " " + driverDateOfBirth.toString().split(" ")[5] + " - " + licenceNumber + " - " + 
				(isFull ? "Full" : "Not Full");
	}
	
	/**
	 * Gets the full name of the licence holder.
	 * @return the name of the licence holder
	 */
	public Name getName() {
		return driverName;
	}
	
	/**
	 * Gets the date of birth of the licence holder.
	 * @return the date of birth of the licence holder
	 */
	public Date getDriverDateOfBirth() {
		return new Date(driverDateOfBirth.getTime());
	}
	
	/**
	 * Gets the licence number of the licence.
	 * @return the licence number
	 */
	public String getLicenceNumber() {
		return licenceNumber.getLicenceNumber();
	}
	
	/**
	 * Gets the issue date of the licence.
	 * @return the issue date 
	 */
	public Date getLicenceDateOfIssue() {
		return new Date(licenceDateOfIssue.getTime());
	}
	
	/**
	 * Checks whether of not this licence is a full licence. 
	 * Returns true if this licence is full, returns false otherwise.
	 * @return true if this licence is full, false otherwise
	 */
	public boolean isFull() {
		return isFull;
	}

}

package uk.ac.ncl._100394872_CSC8002_Assignment1;
/**
 * 
 * Stores a person's first and last name
 * 
 * @author Nicholas Gillen
 * @version 1.0
 *
 */
public final class Name {

	private final String firstName;
	private final String lastName;
	
	/**
	 * Constructs a new name consisting of a first name and a last name.
	 * @param firstName the first name
	 * @param lastName the last name
	 * @throws NullPointerException if either parameter is null
	 */
	public Name(String firstName, String lastName) {
		if (firstName == null)
			throw new NullPointerException("First name cannot be null");
		if (lastName == null)
			throw new NullPointerException("Last name cannot be null");

		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/**
	 * Returns the string representation of this name. The 
	 * string consists of the first name, followed by a space,
	 * followed by the last name.
	 */
	public String toString() {
		return firstName + " " + lastName;
	}
	
	/**
	 * Gets the first name of the name.
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Gets the last name of the name.
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Returns a new Name object from the string
	 * representation of a given name in the form 
	 * "firstName lastName".
	 * @param name the string representation of a name
	 * @return a new Name object from the given name string
	 */
	public static Name valueOf(String name) {
		String[] parts = name.split(" ");
		String fName = parts[0];
		String lName = parts[1];
		return new Name(fName, lName);
	}

}

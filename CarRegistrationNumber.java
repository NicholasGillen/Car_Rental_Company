package uk.ac.ncl._100394872_CSC8002_Assignment1;

/**
 * A car registration number is a unique string 
 * made up of a letter followed by four digits. 
 * There are 260000 unique registration numbers 
 * from a0000 - z9999. Attempting to create further
 * registration numbers will throw an exception.
 * 
 * @author Nicholas Gillen
 * @version 1.0
 *
 */
public final class CarRegistrationNumber {

	private final char letter;
	private final String number;
	private static final int MAX_NUMBER_OF_REGISTRATION_NUMBERS = 26*10*10*10*10;
	private static int numberOfRegistrationNumbersIssued = 0;
	private static int[] digits = {0,0,0,0};
	private static char tempLetter = 'a';
	
	/**
	 * Constructs a new unique car registration number.
	 * @throws MaxNumberOfRegistrationNumbersException if attempting
	 * to create a car registration number after 260000 instances have
	 * already been created
	 */
	public CarRegistrationNumber() throws MaxNumberOfRegistrationNumbersException {
		if (numberOfRegistrationNumbersIssued == MAX_NUMBER_OF_REGISTRATION_NUMBERS)
			throw new MaxNumberOfRegistrationNumbersException("Cannot create anymore unique Car Registration Numbers");
		letter = tempLetter;
		number = "" + digits[0] + digits[1] + digits[2] + digits[3];
		numberOfRegistrationNumbersIssued++;
		
		if (digits[3] == 9) {
			if (digits[2] == 9) {
				if (digits[1] == 9) {
					if (digits[0] == 9) {
						digits[3] = 0;
						digits[2] = 0;
						digits[1] = 0;
						digits[0] = 0;
						tempLetter++;
					}
					else {
						digits[3] = 0;
						digits[2] = 0;
						digits[1] = 0;
						digits[0]++;
					}
				}
				else {
					digits[3] = 0;
					digits[2] = 0;
					digits[1]++;
				}
			}
			else {
				digits[3] = 0;
				digits[2]++;
			}
		}
		else 
			digits[3]++;
		
	}
	
	/**
	 * Gets the letter associated with the car 
	 * registration number. 
	 * @return the first letter of the car registration
	 * number
	 */
	public char getLetter() {
		return letter;
	}
	
	/**
	 * Gets the four digit number associated with
	 * the car registration number.
	 * @return the four digit number associated with
	 * the car registration number
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * Returns the string representation of this
	 * car registration number. The string is in the form
	 * aXXXX - where a is a letter from a-z and XXXX is a
	 * four digit sequence.
	 */
	public String toString() {
		return letter + number;
	}
	
	/**
	 * Returns the unique car registration number. This is 
	 * in the form aXXXX = where a is a letter from a-z and 
	 * XXXX is a four digit sequence.
	 * @return the registration number
	 */
	public String getRegistrationNumber() {
		return toString();
	}
}

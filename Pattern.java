package com.main_package;


/**
 * Class to hold the details of the pattern
 * of the user's choices for a predefined
 * pattern length/user num of choices
 * @author Deron Washington II
 *  Last Update: 9/25/19 at 10:30pm
 *
 */
public class Pattern
{
	// PRIVATE FIELD

	/** holds the pattern of choices 
	 * for a predefined length*/
	private String pattern = "";


	// PUBLIC METHODS

	/**
	 * Parameterized Constructor 
	 * loads in a string to initialize
	 * pattern private field
	 * @param p = string to initialize 
	 * pattern private field
	 */
	Pattern(String p)
	{
		pattern = p;
	}

	/**
	 * Get method to return the value
	 * of the pattern private field
	 * @return this.pattern
	 */
	public String getPattern()	
	{
		return pattern;
	}

	/**
	 * Function to override the hashCode method
	 * found in the Object class
	 * @return the hash code for a sequence of 
	 * input values
	 */
	@Override
	public int hashCode()	
	{		
		return pattern.hashCode();
	}

	/**
	 * Function to override the equals method found
	 * in the Object class
	 * @param o is the second object you are trying
	 * to determine is equal or not
	 * @return true if the objects are equal and false
	 * if they are not equal
	 */
	@Override
	public boolean equals(Object o)
	{
		if (o == null)
			return false;

		else if (this == o)
			return true;

		else if (this.getClass() != o.getClass())
			return false;

		Pattern oTemp = (Pattern) o;
		return (this.pattern.equals(oTemp.pattern));


	}

}

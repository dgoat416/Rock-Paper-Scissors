package com.main_package;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * Represents the user's AI opponent
 * Randomly chooses R,P,S unless 
 * contain hashMap info 
 * @author Deron Washington II
 * Last Update: 10/10/19 at 1:40am
 *
 */
public class Computer
{
	// PRIVATE FIELD

	/** holds the pattern and number of occurrences in HashMap format*/
	private HashMap<Pattern, Integer> patterns = new HashMap<Pattern, Integer>();

	// PUBLIC METHODS

	/**
	 * Default Constructor to initialize HashMap
	 */
	Computer()
	{
		// make sure HashMap is empty
		if (patterns.isEmpty() == false)
			patterns.clear();
	}

	/**
	 * Method to determine and return the move that
	 * counters the user's probable decision
	 * @param p is the pattern string of the user in 
	 * the most recent run
	 * @return 'R', 'P', or 'S' depending on which
	 * one will counter the user is expected to throw
	 */
	public char makePrediction(String p)
	{
		// create set of keys
		Character c = '\0';		

		if (p.length() > 3)
			p = p.substring(1);



		Pattern tempPattern1 = new Pattern(p.concat("R"));
		Pattern tempPattern2 = new Pattern(p.concat("P"));
		Pattern tempPattern3 = new Pattern(p.concat("S"));
		ArrayList<Pattern> tempPattern = new ArrayList<Pattern>();


		tempPattern.add(tempPattern1);
		tempPattern.add(tempPattern2);
		tempPattern.add(tempPattern3);	

		// takes in tempPattern 1 - 3 and stores the keys in that order
		int[] rps = new int [3];

		// initialize array to all 0s
		for (int i = 0; i < 3; i++)
			rps[i] = 0;


		if (p.length() == 3)
		{
			for (int i = 0; i < 3; i++)
			{
				// if pattern is the hashMap
				if (patterns.containsKey(tempPattern.get(i)))
				{
					// store value in the array
					rps[i] = patterns.get(tempPattern.get(i));
				}

			} // end for loop


			// if rps[0] is the greatest then tempStr = tempPattern1
			if (rps[0] > rps[1] && rps[0] > rps[2])
				return 'R';

			// if rps[1] is the greatest then tempStr = tempPattern2
			else if (rps[1] > rps[0] && rps[1] > rps[2])
				return 'P';

			// if rps[2] is the greatest then tempStr = tempPattern3
			else if (rps[2] > rps[0] && rps[2] > rps[1])
				return 'S';

			else if (rps[0] == rps[1])
			{
				Random rand = new Random();
				int choice = rand.nextInt(2); // 0 or 1

				if (choice == 0)
					return 'R';
				else if (choice == 1)
					return 'P';
				else 
					return '\0';

			}

			else if (rps[1] == rps[2])
			{
				Random rand = new Random();
				int choice = rand.nextInt(2) + 1; // 1 and 2

				if (choice == 1)
					return 'P';
				else if (choice == 2)
					return 'S';
				else 
					return '\0';

			}

			else if (rps[0] == rps[2])
			{
				Random rand = new Random();
				int choice = -999;


				while (choice != 0 || choice != 2)
				{
					choice = rand.nextInt(3); // [0,2]

					if (choice == 0)
						return 'R';
					else if (choice == 1)
						continue;
					else if (choice == 2)
						return 'S';
				}

			}

			else // they are all equal
			{
				Random rand = new Random();
				int choice = rand.nextInt(3); // [0,2] 

				if (choice == 0)
					return 'R';
				else if (choice == 1)
					return 'P';
				else if (choice == 2)
					return 'S';
			}
		}

		else // p.length != 3
		{

			Random rand = new Random();
			int choice = rand.nextInt(3); // [0,2]

			if (choice == 0)
				return 'R';
			else if (choice == 1)
				return 'P';
			else
				return 'S';
		} 

		return c;
	}

	/**
	 * Method to store the pattern of 4
	 * choices in the HashMap member variable "patterns"
	 * the pattern being referred to holds the user's throws
	 * for the aforementioned number of choices 
	 * @param p is the pattern to add
	 */
	public void storePattern(String p)
	{
		Pattern temp = new Pattern(p);

		if(patterns.containsKey(temp))
		{
			// add one to value
			patterns.put(temp, patterns.get(temp) + 1);
		}

		else if(patterns.containsKey(temp) == false)
			patterns.put(temp, 1);

	}

	/**
	 * Method to save the HashMap member variable contents
	 * to a file
	 * @param h is the file to write the contents of the HashMap
	 * member variable to
	 */
	@SuppressWarnings("resource")
	public void saveMapToFile(File h)
	{
		try
		{
			// creates PrintWriter and then outputs the HashMap private 
			// member variable to h
			h.createNewFile();
			h.setWritable(true);
			PrintWriter p = new PrintWriter(h);

			// create set of keys
			Set<Pattern> s = patterns.keySet();
			for(Pattern P : s)
			{
				p.println(P.getPattern() + "," + patterns.get(P));
			}

			p.close();
		} 
		catch (IOException e)
		{
			System.out.print("\nFile not found. So the map can't be saved.");
			return;
		} 

	}

	/**
	 * Method to read a previously stored file and return the most
	 * common pattern in the HashMap so the predictions have a starting
	 * point
	 * @param f is the file to read
	 * @return the most common pattern (string) in the HashMap
	 */
	public String readFile(File f)
	{
		String temp = "";
		String tempKey = "";
		int tempValue = 0;
		String mostCommonKey = "";
		int numPos = 0;
		int mostCommonValue = 0;
		boolean key = true;

		try
		{
			Scanner read = new Scanner(f);

			while (read.hasNextLine())
			{
				temp = read.nextLine();
				numPos = tempValue = 0;
				tempKey = "";
				key = true;

				for (int i = 0; i < temp.length(); i++)
				{
					// add temp to tempKey if before ',' 
					// key = true till we encounter ','
					// after ',' temp is added to tempValue and key = false 

					if ( Character.isLetter(temp.charAt(i)) )
					{
						if (key)
							tempKey += temp.charAt(i);
					}

					else if (temp.charAt(i) == ',')
					{
						key = false;
						continue;
					}

					// after the : get the rest of string as a number
					else if (Character.isDigit(temp.charAt(i)))
					{

						if (key == false)
						{		
							tempValue = 
									(tempValue * (int)Math.pow(10, numPos)) 
									+ Character.digit(temp.charAt(i), 10);

							numPos++;
						}
					}

				} // end of for loop


				if (tempValue > mostCommonValue)
				{
					mostCommonValue = tempValue;
					mostCommonKey = tempKey;
				}

				// store in hashMap
				Pattern tempP = new Pattern(tempKey);
				patterns.put(tempP, tempValue);

			} // end while loop

		} catch (FileNotFoundException e)
		{
			System.out.print("File not found");
			return "";
		}

		return mostCommonKey;
	} // end readFile




}

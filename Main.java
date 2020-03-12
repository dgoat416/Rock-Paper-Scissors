package com.main_package;

import java.io.File;
import java.io.FilenameFilter;
import java.text.NumberFormat;
import java.util.ArrayList;



/**
 * Main Class to hold the main 
 * method to run the program
 *
 * @author Deron Washington II
 * Last update: 10/10/19 at 1:41am
 */
public class Main 
{
	/**
	 * NOT USED IN THIS PROJECT
	 * Method to display the text files (.txt) in a directory
	 * @return an arrayList of the names of the files
	 */
	private static ArrayList<String> displayTxtFiles()
	{
		// outputting a file directory
		final String directory = "C:\\Users\\deron\\OneDrive\\Documents\\CECS 277\\CECS 277 Project 2 (Rock, Paper, Scissors)\\Rock, Paper, Scissors";
		File file = new File(directory);

		// all files in directory
		//File[]  listOfFiles = file.listFiles();

		// files in directory only ending with .txt
		File[] listOfFiles = file.listFiles(new FilenameFilter()
		{@Override
			public boolean accept(File dir, String name)
		{
			return name.endsWith(".txt");

		}
		});

		// counter
		int i = 0;
		ArrayList<String> fileNames = new ArrayList<String>();

		for (File  f : listOfFiles)
		{
			i++;

			fileNames.add(f.getName());
			System.out.printf("\nFile %d: " + f.getName(), i);
		}

		// blank line
		System.out.println(""); 

		return fileNames;
	}

	/**
	 * Method to display the outcome of a round of the game
	 * @param totalRounds = number of rounds played
	 * @param _name = name of the user
	 * @param userWins = # of times the user beat AI
	 * @param computerWins = # of times the AI beat the user
	 */
	private static void display(Integer totalRounds, String _name, Integer userWins, Integer computerWins)
	{
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);

		Float userPercent = null;
		Float computerPercent = null;

		if (totalRounds == 1)
		{
			userPercent = ((float)userWins) / ((float)(totalRounds));		
			computerPercent = ((float)computerWins) / ((float)(totalRounds));
		}

		else 
		{
			userPercent = ((float)userWins) / ((float)(totalRounds - 1));		
			computerPercent = ((float)computerWins) / ((float)(totalRounds - 1));
		}


		if (userWins != 1 && computerWins != 1)
		{
			System.out.printf("\nRound %d: ", totalRounds);
			System.out.printf("\n%s has won %d times. "
					+ "\n%s's winning percentage: %s"
					+ "\nThe Computer has won %d times."
					+ "\nThe Computer's winning percentage: %s\n"
					, _name, userWins, _name, nf.format(userPercent)
					,computerWins,  nf.format(computerPercent));
		}

		else if ( userWins == 1 && computerWins == 1)
		{
			System.out.printf("\nRound %d: ", totalRounds);
			System.out.printf("\n%s has won %d time. "
					+ "\n%s's winning percentage: %s"
					+ "\nThe Computer has won %d time."
					+ "\nThe Computer's winning percentage: %s\n"
					, _name, userWins, _name, nf.format(userPercent)
					,computerWins,  nf.format(computerPercent));
		}

		else if (userWins != 1 && computerWins == 1)
		{
			System.out.printf("\nRound %d: ", totalRounds);
			System.out.printf("\n%s has won %d times. "
					+ "\n%s's winning percentage: %s"
					+ "\nThe Computer has won %d time."
					+ "\nThe Computer's winning percentage: %s\n"
					, _name, userWins, _name, nf.format(userPercent)
					,computerWins,  nf.format(computerPercent));
		}

		else if (userWins == 1 && computerWins != 1)
		{
			System.out.printf("\nRound %d: ", totalRounds);
			System.out.printf("\n%s has won %d time. "
					+ "\n%s's winning percentage: %s"
					+ "\nThe Computer has won %d times."
					+ "\nThe Computer's winning percentage: %s\n"
					, _name, userWins, _name, nf.format(userPercent)
					,computerWins,  nf.format(computerPercent));
		}
	}

	/**
	 * Method to execute the functionality of the game
	 * @param _name =  name of the user
	 * @param _opponent = a computer AI object
	 * @param pattern = pattern string that the AI will
	 * 				  be using to start the game 
	 */
	private static void game(String _name, Computer _opponent, String pattern)
	{
		// MENU FOR GAME
		Integer choice = 0;
		final String MENU = "\n1. Rock \n2. Paper \n3. Scissors \n4. Quit\n";
		Integer userWins = 0;
		Integer computerWins = 0;
		Integer totalRounds = 1;
		String _pattern = pattern;
		String computerThrow = "";
		Character prediction = '\0';
		Character _throw = '\0';



		while (choice != 4)
		{
			display(totalRounds, _name, userWins, computerWins);
			System.out.println(MENU);
			choice = CheckInput.getIntRange(1, 4);
			prediction = _opponent.makePrediction(_pattern);

			// transform prediction of what user will pick to 
			// what the computer will throw
			if (prediction == 'R')
				_throw = 'P';

			else if (prediction == 'P')
				_throw = 'S';

			else if (prediction == 'S')
				_throw = 'R';

			// if pattern has length 4+ store it in the hashMap
			if (_pattern.length() > 3)
			{
				// store pattern into computer's memory
				_opponent.storePattern(_pattern);

				// erase the oldest choice in the string
				_pattern =_pattern.substring(1);

			}


			switch(choice)
			{
			case 1: // rock
			{	
				// add R to the pattern String
				// display computer's throw
				// determine if the user won or the computer

				// who won
				if (_throw == 'P' )
				{
					computerWins++;
					computerThrow = "Paper";
					System.out.printf("\nRock, Paper, Scissor.... BOOM"
							+ "\n%s throws Rock!"
							+ "\nThe Computer throws %s!\n", _name, computerThrow);
					System.out.println("Computer wins!");
				}
				else if (_throw == 'R')
				{
					computerThrow = "Rock";
					System.out.printf("\nRock, Paper, Scissor.... BOOM"
							+ "\n%s throws Rock!"
							+ "\nThe Computer throws %s!\n", _name, computerThrow);
					System.out.println("It's a tie! Go again!");
					_pattern += 'R';
					continue;
				}
				else if (_throw == 'S')
				{
					userWins++;
					computerThrow = "Scissors";
					System.out.printf("\nRock, Paper, Scissor.... BOOM"
							+ "\n%s throws Rock!"
							+ "\nThe Computer throws %s!\n", _name, computerThrow);
					System.out.printf("%n%s wins!%n", _name);
				}




				_pattern += 'R';
				break;
			}
			case 2: // paper
			{
				// add P to the pattern String
				// display computer's throw
				// determine if the user won or the computer

				// who won
				if (_throw == 'P' )
				{
					computerThrow = "Paper";
					System.out.printf("\nRock, Paper, Scissor.... BOOM"
							+ "\n%s throws Paper!"
							+ "\nThe Computer throws %s!\n", _name, computerThrow);
					System.out.println("It's a tie! Go again!");
					_pattern += 'P';
					continue;
				}
				else if (_throw == 'R')
				{
					userWins++;
					computerThrow = "Rock";
					System.out.printf("\nRock, Paper, Scissor.... BOOM"
							+ "\n%s throws Paper!"
							+ "\nThe Computer throws %s!\n", _name, computerThrow);
					System.out.printf("%n%s wins!%n", _name);
				}
				else if (_throw == 'S')
				{
					computerWins++;
					computerThrow = "Scissors";
					System.out.printf("\nRock, Paper, Scissor.... BOOM"
							+ "\n%s throws Paper!"
							+ "\nThe Computer throws %s!\n", _name, computerThrow);
					System.out.printf("%nThe Computer wins!%n", _name);
				}



				_pattern += 'P';
				break;
			}
			case 3: // scissors
			{
				// add S to the pattern String
				// display computer's throw
				// determine if the user won or the computer

				// who won
				if (_throw == 'P' )
				{
					userWins++;
					computerThrow = "Paper";
					System.out.printf("\nRock, Paper, Scissor.... BOOM"
							+ "\n%s throws Scissors!"
							+ "\nThe Computer throws %s!\n", _name, computerThrow);
					System.out.printf("%n%s wins!", _name);
				}
				else if (_throw == 'R')
				{
					computerWins++;
					computerThrow = "Rock";
					System.out.printf("\nRock, Paper, Scissor.... BOOM"
							+ "\n%s throws Scissors!"
							+ "\nThe Computer throws %s!\n", _name, computerThrow);
					System.out.println("The Computer wins!");
				}
				else if (_throw == 'S')
				{
					computerThrow = "Scissors";
					System.out.printf("\nRock, Paper, Scissor.... BOOM"
							+ "\n%s throws Scissors!"
							+ "\nThe Computer throws %s!\n", _name, computerThrow);
					System.out.println("It's a tie! Go again!");
					_pattern += 'S';
					continue;
				}



				_pattern += 'S';
				break;
			}
			case 4: // QUIT
			{
				choice = 4;
				System.out.print("Would you like to create a save file" 
						+ " for your series?" + "\nInput 'y' or 'yes' or 'n' or 'no.'\n");
				if (CheckInput.getYesNo())
				{
					File outputFile = new File ("experience.txt");					


					//write HashMap contents to file

					_opponent.saveMapToFile(outputFile);

					System.out.printf("\nYou successfully created the text"
							+ " file.\nSee you next time!");
					System.exit(0);

				} // end yes 

				else // no load to file
				{
					System.out.print("\nSee you next time!");
					System.exit(0);
				}

			} // end case 4
			} // end switch

			totalRounds++;
		} // end while

	} // end method

	/**
	 * Main method to run program
	 * @param args needed to run program
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub	

		Computer opponent = new Computer();
		System.out.println("What is your name: ");
		String name = CheckInput.getString();
		String pattern = "";

		// check if user wants to load a saved file for computer object
		System.out.printf("\n%s, would you like to load a saved file "
				+ "that contains information from previous games? "
				+ "\nInput 'y' or 'yes' or 'n' or 'no.'\n", name);

		if (CheckInput.getYesNo())
		{				
			File file = new File("experience.txt");


			// choose beginner or veteran mode
			final String DIFFICULTY = "\n1. Beginner \n2. Veteran\n";

			if(file.exists())
			{
				System.out.printf("%nThe file has been loaded.%n");
				System.out.print(DIFFICULTY);
				Integer decision = CheckInput.getIntRange(1, 2);

				if (decision == 1)
				{
					// Beginner so empty HashMap
					game(name, opponent, pattern);

				} 
				else if (decision == 2)
				{
					// Veteran so load in the most common pattern from file
					pattern = opponent.readFile(file);
					opponent.storePattern(pattern);
					game(name, opponent, pattern);
				} 
			} // end if file exists

			else
			{
				System.out.printf("\nThere is no previously stored data."
						+ "\nDon't worry %s, you can still play the game! : )%n", name);

				game(name, opponent, pattern);
			}

		} // end CheckInput yes or no

		// no loading the file  
		else
		{
			// Load opponent w/empty hashMap since no file available
			game(name, opponent, pattern);
		}


	} // end main method

} // end main class

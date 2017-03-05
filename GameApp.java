/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          GameApp
// FILE:             Scoreboard.java
//
// TEAM:    The Brogrammers, 07
// Authors: Zexing Li (Richard), Bryan Watson, Changhao Sun, Jonas Klare, Mason Gomm, Joshua DuBois,
//
// Author1: Zexing Li (Richard), zli674@wisc.edu, zexing, lec001
// Author2: Bryan Watson, bmwatson2@wisc.edu, bmwatson2, lec001
// Author3: Joshua DuBois, jdubois3@wisc.edu, jdubois3, lec001
// Author4: Changhao Sun, csun78@wisc.edu, csun78, lec001
// Author5: Mason Gomm, Mgomm@wisc.edu, mgomm, lec001
// Author6: Jonas Klare, klare@wisc.edu, klare, Lec001
//

//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: N/a
// 
// Online sources: N/a
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * Runs the game through a loop (start) and initializes the application as well
 * as the other classes to work together. 
 *
 * <p>Bugs: None known. 
 *
 */


import java.util.Scanner;

public class GameApp{
    /**
     * Scanner instance for reading input from console
     */
    private static final Scanner STDIN = new Scanner(System.in);
    private Game game;
    private boolean firstPass;

    /**
     * Constructor for instantiating game class
     * @param seed: Seed value as processed in command line
     * @param timeToPlay: Total time to play from command line
     */
    public GameApp(int seed, int timeToPlay){
    	this.game = new Game(seed, timeToPlay);
    	this.firstPass = true;
    }

    /**
     * Main function which takes the command line arguments and instantiate the
     * GameApp class.
     * The main function terminates when the game ends.
     * Use the getIntegerInput function to read inputs from console
     *
     * @param args: Command line arguments <seed> <timeToPlay>
     */
    public static void main(String[] args) 
    {
    	//Variables
    	int 
    	seed, 
    	timeToPlay;
    	String 
    	upSeed,       //Unparsed versions of their int form
    	upTimeToPlay; //Unparsed versions of their int form. 
    	//Variables

    	//Body
    	System.out.println("Welcome to the Job Market!");

    	//The first is the seed, the second is the time to play.
    	upSeed = args[0];
    	upTimeToPlay = args[1];

    	//Check to see if they are both good. 
    	seed = checkArgs(upSeed);
    	timeToPlay = checkArgs(upTimeToPlay);

    	//Create the new object. 
    	GameApp app = new GameApp(seed, timeToPlay);
    	while(app.game.getTimeToPlay() > 0)
    	{
    		app.start();
    	}
    	System.out.println("Game Over!");
		System.out.println("Your final score: " + app.game.getTotalScore());
    	//Body
    }

    /**
     * Add Comments as per implementation
     */
    private void start()
    {	
    	try
    	{
    		//Variables
    		int
    		jobIndex, //the index of the job wanted to be worked on.
    		jobTime; //The amount of time for the given job. 
    		final String
    		indexPrompt = "Select a job to work on: ",
    		timePrompt = "For how long would you like to work on this job?: ",
    		reentryPrompt = "At what position would you like to insert the "
    				+ "job back into the list?";
    		//Variables

    		//Body
    		// Display time remaining in game
    		int timeToPlay = this.game.getTimeToPlay();
    		System.out.println("You have " + timeToPlay + " left in the game!");

    		//Create new jobs on first iteration
    		if (firstPass)
    		{
    			this.game.createJobs();
    			firstPass = false;
    		}

    		//Display jobs w/Game Object. 
    		this.game.displayActiveJobs();

    		//Ask for user input of what job they want to perform
    		jobIndex = getIntegerInput(indexPrompt);

    		// Check validity of input
    		if (jobIndex < 0 || jobIndex > this.game.getNumberOfJobs() - 1)
    		{
    			throw new IndexOutOfBoundsException();
    		}

    		//Deduct time if not at index 0. 
    		if (jobIndex != 0)
    		{
    			int deducted = timeToPlay - jobIndex;
    			this.game.setTimeToPlay(deducted);
    		}

    		//Ask for user input of how long they want to work that job
    		jobTime = getIntegerInput(timePrompt);

    		//Get job and attempt to work for that long (w/ error checking)

    		Job currJob = this.game.updateJob(jobIndex, jobTime);

    		//If job isn't completed...
    		if (!currJob.isCompleted())
    		{
    			//Ask for new index
    			jobIndex = checkIndex(reentryPrompt);

    			//Determine and deduct the time penalty accrued.
    			int timePenalty;
    			if (jobIndex == -1 || jobIndex > this.game.getNumberOfJobs() - 1)
    			{
    				timePenalty = this.game.getNumberOfJobs();
    			}
    			else
    			{
    				timePenalty = jobIndex;
    			}
    			this.game.setTimeToPlay(this.game.getTimeToPlay() - timePenalty);

    			//Insert at specified index. 
    			this.game.addJob(jobIndex, currJob);
    		}


    		//If the job is completed...
    		else
    		{
    			//Display success message
    			System.out.println("Job completed! Current Score: " + 
    					this.game.getTotalScore());
    			this.game.displayCompletedJobs();
    		}
    		//Body
    	}
    	catch (Exception e)
    	{
    		System.out.println("ERROR: Invalid input. Try again.\n");
    	}
    }

    /**
     * Displays the prompt and returns the integer entered by the user
     * to the standard input stream.
     *
     * Does not return until the user enters an integer.
     * Does not check the integer value in any way.
     * @param prompt The user prompt to display before waiting for this integer.
     */
    public static int getIntegerInput(String prompt) {
    	
        System.out.print(prompt);
        while ( ! STDIN.hasNextInt() ) {
            System.out.println(STDIN.next()+" is not an int.  Please enter an integer.");
        }
        return STDIN.nextInt();
    }
    
    /**
     * Check to see if the incoming prompt is a correctly formatted integer
     * ie It's positive, and can be parsed. 
     * It exits if the number isn't positive. 
     * 
     * @param prompt: incoming string to be parsed. 
     * @return a parsed, correctly formatted string. 
     */
    public static int checkArgs(String prompt) 
    {
    	//Variables
    	int out = -1; 
    	//Variables
    	
    	//Body
    	try
    	{
    		//Check to see if it is an integer. 
    		out = Integer.parseInt(prompt);
    		//See if it is positive. 
    		if(out < 0) //If negative
    		{
    			System.exit(0);
    		}
    	}
    	catch(NumberFormatException e)
    	{
    		System.out.println(prompt + " is not an int. Please enter an integer.");
    	}
    	
    	//Body
    	
    	//Return
    	return out;
    }
    
    /**
     * Check to see if the incoming prompt is a correctly formatted integer
     * for reentry into 
     * ie It's positive, and can be parsed. 
     * It exits if the number isn't positive. 
     * 
     * @param prompt: incoming string to be parsed. 
     * @return a parsed, correctly formatted string. 
     */
    public static int checkIndex(String prompt) 
    {
    	//Variables
    	String out; 
    	int output;
    	//Variables
    	
    	//Body
    	try
    	{
    		System.out.println(prompt);
    		out = STDIN.next();
    		//Check to see if it is an integer. 
    		output = Integer.parseInt(out);
    		
    		//See if it is positive. 
    		if(output < 0) //If negative
    		{
    			return -1;
    		}
    	}
    	catch(NumberFormatException e)
    	{
    		return -1;
    	}
    	//Body
    	
    	//Return
    	return output;
    } 
}

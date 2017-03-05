/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          GameApp
// FILE:             Scoreboard.java
//
// TEAM:    The Brogrammers, 07
// Authors: Jonas Klare
// Author1: Jonas Klare, klare@wisc.edu, klare, 001
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
 * <p>Bugs: None found so far. 
 *
 * @author Jonas
 */


import java.util.Scanner;

public class GameApp{
    /**
     * Scanner instance for reading input from console
     */
    private static final Scanner STDIN = new Scanner(System.in);

    /**
     * Constructor for instantiating game class
     * @param seed: Seed value as processed in command line
     * @param timeToPlay: Total time to play from command line
     */
    public GameApp(int seed, int timeToPlay){
    	Game game = new Game(seed, timeToPlay);
    }

    /**
     * Main function which takes the command line arguments and instantiate the
     * GameApp class.
     * The main function terminates when the game ends.
     * Use the getIntegerInput function to read inputs from console
     *
     * @param args: Command line arguments <seed> <timeToPlay>
     */
    public static void main(String[] args) {
 
    	//System.exit(0);
    	//Variables
    	int 
    		seed, 
    		timeToPlay;
    	String 
    		commandLine, 
    		upSeed,       //Unparsed versions of their int form
    		upTimeToPlay; //Unparsed versions of their int form. 
    	String[]
    		split;
    	//Variables
    	
    	//Body
        System.out.println("Welcome to the Job Market!");

        //Get commandLine arguments. 
        commandLine = args[0]; //First line of args. 
        split = commandLine.split(" "); //Split at each space. 
        
        //The first is the seed, the second is the time to play. 
        upSeed = split[0];
        upTimeToPlay = split[1];
        
        //Check to see if they are both good. 
        seed = check(upSeed);
        timeToPlay = check(upTimeToPlay);
         
        //Create the new object. 
        GameApp app = new GameApp(seed, timeToPlay);
        app.start();
        //Body
    }

    /**
     * Add Comments as per implementation
     */
    private void start(){
        //TODO: The interactive game logic goes here
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
            System.out.print(STDIN.next()+" is not an int.  Please enter an integer.");
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
    public static int check(String prompt) 
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
    
}

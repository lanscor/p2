/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          GameApp
// FILE:             Game.java
//
// TEAM:    The Brogrammers, 07
// Authors: 
// Author1: 
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: N/a
// 
// Online sources: N/a
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.Iterator;

public class Game{

    /**
     * A list of all jobs currently in the queue.
     */
    private ListADT<Job> list;
    /**
     * Whenever a Job is completed it is added to the scoreboard
     */
    private ScoreboardADT scoreBoard;
    private int timeToPlay;
    private JobSimulator jobSimulator;

    /**
     * Constructor. Initializes all variables.
     * @param seed
     * seed used to seed the random number generator in the Jobsimulator class.
     * @param timeToPlay
     * duration used to determine the length of the game.
     */
    public Game(int seed, int timeToPlay){
    	this.timeToPlay = timeToPlay;
        this.scoreBoard = new Scoreboard();
        this.jobSimulator = new JobSimulator(seed);
        this.list = new JobList();
    }

    /**
     * Returns the amount of time currently left in the game.
     * @returns the amount of time left in the game.
     */
    public int getTimeToPlay() {
        return this.timeToPlay;
    }

    /**
     * Sets the amount of time that the game is to be executed for.
     * Can be used to update the amount of time remaining.
     * @param timeToPlay
     *        the remaining duration of the game
     */
    public void setTimeToPlay(int timeToPlay) {
        this.timeToPlay = timeToPlay;
    }

    /**
     * States whether or not the game is over yet.
     * @returns true if the amount of time remaining in
     * the game is less than or equal to 0,
     * else returns false
     */
    public boolean isOver(){
        if (this.timeToPlay <= 0) {
        	return true;
        }
        else {
        	return false;
        }
    }
    /**
     * This method simply invokes the simulateJobs method
     * in the JobSimulator object.
     */
    public void createJobs(){
    	this.jobSimulator.simulateJobs(this.list, this.timeToPlay);
    }

    /**
     * @returns the length of the Joblist.
     */
    public int getNumberOfJobs(){
        int numOfJobs = this.list.size();
        return numOfJobs;
    }

    /**
     * Adds a job to a given position in the joblist.
     * Also requires to calculate the time Penalty involved in
     * adding a job back into the list and update the timeToPlay
     * accordingly
     * @param pos
     *      The position that the given job is to be added to in the list.
     * @param item
     *      The job to be inserted in the list.
     */
    public void addJob(int pos, Job item){
        if (this.timeToPlay < item.getTimeUnits()) {
        	this.timeToPlay = 0;
        }
        else if (pos < 0 || pos >= this.list.size())
        {
        	this.list.add(item);
        	this.timeToPlay -= this.getNumberOfJobs();
        	this.createJobs();
        }
        else
        {
        	this.list.add(pos + 1, item);
        	this.timeToPlay -= pos;
        	this.createJobs();
        }
    }

    /**
     * Adds a job to the joblist.
     * @param item
     *      The job to be inserted in the list.
     */
    public void addJob(Job item){
        this.list.add(item);
    }

    /**
     * Given a valid index and duration,
     * executes the given job for the given duration.
     *
     * This function should remove the job from the list and
     * return it after applying the duration.
     *
     * This function should set duration equal to the
     * amount of time remaining if duration exceeds it prior
     * to executing the job.
     * After executing the job for a given amount of time,
     * check if it is completed or not. If it is, then
     * it must be inserted into the scoreBoard.
     * This method should also calculate the time penalty involved in
     * executing the job and update the timeToPlay value accordingly
     * @param index
     *      The job to be inserted in the list.
     * @param duration
     *      The amount of time the given job is to be worked on for.
     */
    public Job updateJob(int index, int duration) 
    		throws IndexOutOfBoundsException{
        if (index < 0 || index > this.list.size() || duration < 0) {
        	throw new IndexOutOfBoundsException();
        }
        
        if (duration > this.timeToPlay) {
        	duration = this.timeToPlay;
        }
        
        Job tmp = this.list.remove(index + 1);
        if (duration > tmp.getTimeUnits() - tmp.getSteps()) {
        	duration = tmp.getTimeUnits() - tmp.getSteps();
        }
        tmp.setSteps(tmp.getSteps() + duration);
        this.timeToPlay -= duration;
        if(tmp.isCompleted()) {
        	this.scoreBoard.updateScoreBoard(tmp);
        	this.createJobs();
        	return tmp;
        }
        else {
        	return tmp;
        }
    }

    /**
     * This method produces the output for the initial Job Listing, IE:
     * "Job Listing
     *  At position: job.toString()
     *  At position: job.toString()
     *  ..."
     *
     */
    public void displayActiveJobs(){
        Iterator<Job> itr = this.list.iterator();
        int count = 0;
        System.out.println("Job Listing");
        while (itr.hasNext()) {
        	System.out.println("At position: " + count + " " + itr.next().toString());
        	count++;
        }
        System.out.println();
    }

    /**
     * This function simply invokes the displayScoreBoard method in the ScoreBoard class.
     */
    public void displayCompletedJobs(){
        this.scoreBoard.displayScoreBoard();
    }

    /**
     * This function simply invokes the getTotalScore method of the ScoreBoard class.
     * @return the value calculated by getTotalScore
     */
    public int getTotalScore(){
        return this.scoreBoard.getTotalScore();
    }
}

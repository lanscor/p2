/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          JobMarket
// FILE:             JobList
//
// Authors: Zexing Li (Richard)
// Author1: Zexing Li (Richard), zli674@wisc.edu, zexing, lec001
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: N/A
// 
// Online sources: N/A
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * 
 * @author Zexing
 *
 */
public class JobListIterator implements java.util.Iterator<Job> {    //Iterate through JobList
	
	Listnode<Job> curr = null;
	
	/**
	 * 
	 * @param joblist
	 */
	public JobListIterator (JobList joblist) {    //Creates and returns a new Iterator
		curr = joblist.head;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean hasNext() {    //Checks if the Iterator has reached the end
		if(curr.getNext() != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 */
	@Override
	public Job next() {    //Gets the current Job and go on to the nest node
		if(curr != null) {
			Job curjob = curr.getData();
			curr = curr.getNext();
			return curjob;
		}
		else {
			throw new java.util.NoSuchElementException();
		}
	}
	
}

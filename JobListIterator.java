/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          JobMarket
// FILE:             JobList
//
// Authors: Zexing Li (Richard)
// Author1: Zexing Li (Richard), zli674@wisc.edu, zexing, lec001
// Author2: Bryan Watson, bmwatson2@wisc.edu, bmwatson2, lec001
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: N/A
// 
// Online sources: N/A
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * Iterate through JobList
 * @author Zexing
 *
 */
public class JobListIterator implements java.util.Iterator<Job> {   
	
	Listnode<Job> curr = null;
	
	/**
	 * Creates and returns a new Iterator
	 * @param joblist
	 */
	public JobListIterator (Listnode<Job> head) {    
		curr = head.getNext();
	}
	
	/**
	 * Checks if the Iterator has reached the end
	 */
	@Override
	public boolean hasNext() {    
		if(curr != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the current Job and go on to the nest node
	 */
	@Override
	public Job next() {   
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

/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          JobMarket
// FILE:             JobList
//
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

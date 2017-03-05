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
 * JobList is a data structure that stores Jobs, allowing them to be
 * removed, have more Jobs added, and also retrieve Jobs. 
 *
 * <p>Bugs N/A
 *
 * @author Zexing Li (Richard)
 */

public class JobList implements ListADT<Job> {
	private int size = 0;
	private Listnode<Job> head;
	
	public JobList() {
		this.head = new Listnode<Job>(null);
	}
	
	/**
	 * Adds an item at the end of the list
	 */
	public void add(Job item) throws IllegalArgumentException {   
		//check the new item
		if(item == null) {    
			throw new IllegalArgumentException();
		}
		//add a new node
		else {    
			//create a new node
			Listnode<Job> newmember = new Listnode<Job>(item); 
			
			//traverse to the prior node
			Listnode<Job> curr = this.head;    
			while(curr.getNext() != null) {
				curr = curr.getNext();
			}
			curr.setNext(newmember);
			
			 //increase the size
			this.size++;   
		}
	}

	/**
	 * 
	 */
	@Override
	public void add(int pos, Job item) throws IllegalArgumentException, 
		IndexOutOfBoundsException{ 
		
		//Add an item at any position in the list, check the target item
		if(item == null) {    
			throw new IllegalArgumentException();
		}
		//check the position
		if((pos > size()) || (pos < 1)) {    
			throw new IndexOutOfBoundsException();
		}
		//traverse to the target position
		Listnode<Job> curr = this.head;   
		for(int i = 0;i < pos-1 ;i++) {
			curr = curr.getNext();
		}
		//create a new node and link the next node after the new node
		Listnode<Job> newmember = new Listnode<Job> (item, curr.getNext());
		
		//link the prior node to the new node
		curr.setNext(newmember);    
		this.size++;
	}

	/**
	 * Check if a particular item exists in the list
	 */
	@Override
	public boolean contains(Job item) throws IllegalArgumentException{
		//check the target item
		if(item == null) {    
			throw new IllegalArgumentException();
		}
		
		Listnode<Job> curr = this.head;
		
		//traverse through the JobList
		while(curr != null) {    
			if(curr.getData() == item) {
				return true;
			}
			curr = curr.getNext();
		}
		
		return false;
	}

	/**
	 * returns a Job at a certain position
	 */
	@Override
	public Job get(int pos) throws IndexOutOfBoundsException { 
		//check the index
		if((pos > size()) || (pos < 0)) {    
			throw new IndexOutOfBoundsException();
		}
		//traverse to the target position
		Listnode<Job> curr = this.head;    
		for(int i = 0;i < pos;i++) {
			curr = curr.getNext();
		}
		return curr.getData();
	}

	/**
	 * 
	 */
	@Override
	public boolean isEmpty() {
		if(size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * removes a ListNode<Job> at a certain position and returns the 
	 * Job in target node
	 */
	@Override
	public Job remove(int pos) throws IndexOutOfBoundsException {    
		//check the index
		if((pos > size()) || (pos < 1)) {    
			throw new IndexOutOfBoundsException();
		}
		//get the Job before the ListNode<Job> to be removed
		Listnode<Job> curr = this.head;    
		for(int i = 0;i < pos-1;i++) {
			curr = curr.getNext();
		}
		//Get the target Job from the JobList
		Job removed = curr.getNext().getData();    
			
		//remove the target Job
		curr.setNext(curr.getNext().getNext());  
		
		//reduce the size
		this.size--;    
		return removed;
	}

	/**
	 * returns the size of JobList
	 */
	@Override
	public int size() {    
		return this.size;
	}

	/**
	 * required by java.util.Iterator, returns a iterator
	 */
	//@SuppressWarnings("unchecked")
	@Override
	public java.util.Iterator<Job> iterator() {    
		return (java.util.Iterator<Job>) new JobListIterator(this.head);    
	}

}


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
 * JobList is a data structure that stores Jobs, allowing them to be
 * removed, have more Jobs added, and also retrieve Jobs. 
 *
 * <p>Bugs N/A
 *
 * @author Zexing Li (Richard)
 */

public class JobList implements ListADT<Job> {
	private int size = 0;
	protected Listnode<Job> head = null;
	
	/**
	 * 
	 */
	public void add(Job item) throws IllegalArgumentException {   //Adds an item at the end of the list
		if(item == null) {    //check the new item
			throw new IllegalArgumentException();
		}
		if(head == null) {    //create the List if no list
			head = new Listnode<Job>(item);
			size++;
		}
		else {    //add a new node
			Listnode<Job> newmember = new Listnode<Job>(item);    //create a new node
			Listnode<Job> curr = head;    //traverse to the prior node
			while(curr.getNext() != null) {
				curr = curr.getNext();
			}
			curr.setNext(newmember);
			size++;    //increase the size
		}
	}

	/**
	 * 
	 */
	@Override
	public void add(int pos, Job item) throws IllegalArgumentException, IndexOutOfBoundsException{    //Add an item at any position in the list
		if(item == null) {    //check the target item
			throw new IllegalArgumentException();
		}
		if((pos > size() - 1) || (pos < 0)) {    //check the position
			throw new IndexOutOfBoundsException();
		}
		
		Listnode<Job> curr = head;    //traverse to the target position
		for(int i = 0;i < pos-1 ;i++) {
			curr = curr.getNext();
		}
		if (pos == 0)
		{
			Listnode<Job> newmember = new Listnode<Job> (item, curr);
			head = newmember;
		}
		else
		{
			Listnode<Job> newmember = new Listnode<Job> (item, curr.getNext());    //create a new node and link the next node after the new node
			curr.setNext(newmember);    //link the prior node to the new node
		}
		size++;
	}

	/**
	 * 
	 */
	@Override
	public boolean contains(Job item) throws IllegalArgumentException{    //Check if a particular item exists in the list
		if(item == null) {    //check the target item
			throw new IllegalArgumentException();
		}
		
		Listnode<Job> curr = head;
		while(curr != null) {    //traverse through the JobList
			if(curr.getData() == item) {
				return true;
			}
			curr = curr.getNext();
		}
		
		return false;
	}

	/**
	 * 
	 */
	@Override
	public Job get(int pos) throws IndexOutOfBoundsException {    //returns a Job at a certain position
		if((pos > size() - 1) || (pos < 0)) {    //check the index
			throw new IndexOutOfBoundsException();
		}
		
		Listnode<Job> curr = head;    //traverse to the target position
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
	 * 
	 */
	@Override
	public Job remove(int pos) throws IndexOutOfBoundsException {    //removes a ListNode<Job> at a certain position and returns the Job in target node
		if((pos > size() - 1) || (pos < 0)) {    //check the index
			throw new IndexOutOfBoundsException();
		}
		Job removed;
		Listnode<Job> curr = head;    //get the Job before the ListNode<Job> to be removed
		for(int i = 0;i < pos-1;i++) {
			curr = curr.getNext();
		}
		if (pos == 0)
		{
			removed = curr.getData();
			head = curr.getNext();
		}
		else
		{
			removed = curr.getNext().getData();    //Get the target Job from the JobList
			
			curr.setNext(curr.getNext().getNext());    //remove the target Job
		}
		size--;    //reduce the size
		return removed;
	}

	/**
	 * 
	 */
	@Override
	public int size() {    //returns the size of JobList
		return size;
	}

	/**
	 * 
	 */
	//@SuppressWarnings("unchecked")
	@Override
	public java.util.Iterator<Job> iterator() {    //required by java.util.Iterator, returns a iterator
		return (java.util.Iterator<Job>) new JobListIterator(this);    
	}

}

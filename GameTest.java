/*The Card Game: 
There will be two decks of cards - the deck of questions and the discard deck, and each card will have a question on it. 
The discard deck is initially empty. The player selects a card from the deck of questions. 
The question is displayed and the player answers the question displayed as y if he knows the answer, 
n otherwise. If the question is answered correctly, the card is placed at the bottom of the question deck. 
If the question is answered incorrectly it is placed on the top of the discard deck. For every correct answer,
the player gets 1 point. A score of 5 wins the game.The player loses in a situation when he tries to put a card on the discard deck which is already full.*/

import java.util.*;
import java.lang.*;
import java.io.*;
 
class GameTest {
 
    public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		//System.out.println("Enter number of testcases");
		int testcases = Integer.parseInt(scan.nextLine());
		//System.out.println("Enter number of Questions");
		int numOfQuestions = Integer.parseInt(scan.nextLine());
		Queue<Integer> questionQ = new QueueArray<Integer>(numOfQuestions);
		//System.out.println("Enter size of Discard Deck");
		int sizeOfDiscardDeck = Integer.parseInt(scan.nextLine());
		stack<Integer> discardStack = new StackArray<Integer>(sizeOfDiscardDeck);
		//int inputs = Integer.parseInt(scan.nextLine());
		//System.out.println("Qsize = "+QuestionQ.size());
		//System.out.println("Ssize = "+DiscardStack.size());
		for(int i =1; i<=numOfQuestions;i++)
		{
			questionQ.enqueue(i);
			
		}
		//System.out.println("Qsize = "+questionQ.size());
		//questionQ.printQueue();
		CGame player = new CGame();
		System.out.println("Final Score = " +player.play(questionQ, discardStack,scan));
		
	}
	
}
 
class CGame{
	private int score;
	CGame()
	{
		score = 0;
	}
	public int play(Queue<Integer> questionQ, stack<Integer> discardStack,Scanner scan) {
	    int count=0;
		String reply;
		int size;
		size=discardStack.stackCapacity();
		while(score!=5)
		{
		    //n--;
		    reply=scan.nextLine();
		    if(reply.equals("y"))
		    {
	            score++;	    
	            questionQ.enqueue(questionQ.dequeue());
	            System.out.println("Score = "+score);
	        }
	        else
	        {
	           count++;
	           
	           if(count==size+1)
	                break;
	           if(discardStack.isFull())
	                continue;
	            System.out.println("Score = "+score);
	           if(count==size)
	           {
	                System.out.println("Discard Deck is full:Life line Exhausted");
	           }
                discardStack.push(questionQ.dequeue());
	           
	        }
	        
		}
		if(score==5)
		{
		    System.out.println("You Won");
		}
		else
		{
		    System.out.println("You Lost");
		}
	    return score;
	}
}
 
 
interface Queue<E> {
	int size();
	boolean isEmpty();
	void enqueue(E e);
	E dequeue();
	E front();
 
void printQueue();
}
 
interface stack<E> {
	int size();
	int stackCapacity();
	boolean isEmpty();
	void push(E e);
	E pop();
	E top();
	void printStack();
	boolean isFull();
}
 
class StackArray<E> implements stack<E> {
	
	private E[] data; // array container
	private int t = -1; // index to top position
	// constructor
	int CAPACITY=0;
 
	public StackArray(int stackcap) {
		CAPACITY = stackcap;
		//System.out.println("size" + CAPACITY);
		data = (E[]) new Object[CAPACITY];
	}
	
	public int stackCapacity()
	{
		return CAPACITY;
	}
 
	public int size() {
		// TODO Auto-generated method stub
		return (t + 1);
	}
 
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (t < 0);
	}
	public boolean isFull()
	{
		return (size()== CAPACITY);
	}
 
	public void push(E e) {
		// TODO Auto-generated method stub
		if (size() == CAPACITY)
		{
			System.out.println("Stack full exception");
		}
		else {
			t = t + 1;
			data[t] = e;
		}
	}
 
	public E top() {
		// TODO Auto-generated method stub
		if (isEmpty())
			System.out.println("Stack Empty");
		return data[t];
	}
 
	public E pop() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			System.out.println("Stack Empty");
			return null;
		} else {
			E temp = data[t];
			data[t] = null;
			t = t - 1;
			return temp;
		}
 
	}
	
	public void printStack() {
		for (int i = 0; i < CAPACITY; i++) {
			if(data[i]!=null)
				System.out.print(data[i] + " ");
		}
		System.out.println("  ");
	}
}
 
class QueueArray<E> implements Queue<E> {
	
	private int CAPACITY;
	private E[] data;
	private int f=-1;
	private int r=0;
	private int sz=0;
 
	public QueueArray(int qcap) {
		CAPACITY = qcap;
		data= (E[]) new Object[CAPACITY];
		//System.out.println("size" + CAPACITY);
	}
	public int size() {
		return sz;
	}
 
	public boolean isEmpty() {
		return(size()==0);
	}
 
	public void enqueue(E e) throws IllegalStateException {
		if(sz==CAPACITY)
		{
			System.out.println("QueueFullException");
			return;
		}
		else{
			
			data[r]=e;
			r=(r+1)%CAPACITY;
			sz++;
			if(f== -1)
			{
				f=0;
			}
		}
	}
	public E dequeue() {
        if(isEmpty()){
        	System.out.println("QueueEmptyException");
			return null;
        }
        else{
            
			E temp= data[f];
    		data[f]=null;
    		f=(f+1)%CAPACITY;
    		sz--;
    		return temp;
        }
	}
 
	public E front() {
		if(isEmpty()){
			System.out.println("QueueEmptyException");
			return null;
		}
		else{
			return data[f];
		}
	}
 
	public void printQueue(){
		for (int i=0;i<CAPACITY;i++){
			System.out.print(data[i] + ":");
		}
		System.out.print("\n");
	}
	
	
} 

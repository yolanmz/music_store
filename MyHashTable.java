package assignment4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
    // num of entries to the table
    private int numEntries;
    // num of buckets 
    private int numBuckets;
    // load factor needed to check for rehashing 
    private static final double MAX_LOAD_FACTOR = 0.75;
    // ArrayList of buckets. Each bucket is a LinkedList of HashPair
    private ArrayList<LinkedList<HashPair<K,V>>> buckets; 
    
    // constructor
    public MyHashTable(int initialCapacity) {
        // ADD YOUR CODE BELOW THIS
    	   if (initialCapacity < 0) {//initial capacity needs to be larger than 0
    		   throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
    	   }
    	   //assign values to the fields
       this.buckets = new ArrayList<LinkedList<HashPair<K,V>>>(initialCapacity);
       this.numBuckets = initialCapacity;
       this.numEntries = 0;
       for (int i=0; i < initialCapacity; i++) {//fill the buckets with linkedlist
    	       LinkedList<HashPair<K, V>> newLinkedList = new LinkedList<HashPair<K,V>>();
    	       this.buckets.add(newLinkedList);
    	     
       }
        //ADD YOUR CODE ABOVE THIS
    }
    
    public int size() {
        return this.numEntries;
    }
    
    public int numBuckets() {
        return this.numBuckets;
    }
    
    /**
     * Returns the buckets vairable. Usefull for testing  purposes.
     */
    public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
        return this.buckets;
    }
    /**
     * Given a key, return the bucket position for the key. 
     */
    public int hashFunction(K key) {
        int hashValue = Math.abs(key.hashCode())%this.numBuckets;
        return hashValue;
    }
    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. Expected average run time  O(1)
     */
    public V put(K key, V value) {
        //  ADD YOUR CODE BELOW HERE
    	   
        int position = hashFunction(key);//use the function to calculate the position
        LinkedList<HashPair<K, V>> list  = buckets.get(position);//get the linkedlist at that position
            for (HashPair<K, V> hp: list) {
        	     
        	    	    if(hp.getKey().equals(key)) {
        	    	    V oldValue = hp.getValue();//create a variable to store the old value
        	    	    hp.setValue(value);//set the value to the new value
        	    	    return oldValue;
        	    	}
        	     
        }
        //if the same key cannot be found
            //create a new hashpair with the value we have 
        HashPair<K, V> newPair = new HashPair<K, V>(key, value);
        list.add(newPair);
        this.numEntries ++;
        double currentLoadFactor = size()/numBuckets();
        //after adding a new entry, if we the load factor is bigger than 0.75, call rehash
        if (currentLoadFactor > MAX_LOAD_FACTOR) {
        	    rehash();   
        }
        return null;
        //  ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Get the value corresponding to key. Expected average runtime = O(1)
     */
    
    public V get(K key) {
        //ADD YOUR CODE BELOW HERE
       	
    	    int position = hashFunction(key);
    	    LinkedList<HashPair<K, V>> list = buckets.get(position);//get the linkedlist at the key position
    	    
    	    
    	    for (HashPair<K, V> hp: list) {
    	    	   
           	 if( hp.getKey().equals(key)) {
           	    	    return hp.getValue();//find a same key then we return value
           	     
           	     
    	      	}
    	    }
    	    
        return null;
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Remove the HashPair correspoinding to key . Expected average runtime O(1) 
     */
    public V remove(K key) {
        //ADD YOUR CODE BELOW HERE
     	
    	    int position = hashFunction(key);
    	    LinkedList<HashPair<K, V>> list = buckets.get(position);
    	    

    	    	for(HashPair<K, V> hp : list) {
    	    		
    	    	    	if (hp.getKey().equals(key)) {
    	    	    	    	V removedValue = hp.getValue();//store the value we want to remove
    	    	    	    list.remove(hp);  
    	    	    	    	this.numEntries--;//entry reduces after removing
    	    	    	    	       
    	    	    	    return removedValue;
    	    	    	}
    	    		
    	    }
        return null;
        //ADD YOUR CODE ABOVE HERE
    }
    
    // Method to double the size of the hashtable if load factor increases
    // beyond MAX_LOAD_FACTOR.
    // Made public for ease of testing.
    
    public void rehash() {
        //ADD YOUR CODE BELOW HERE
        int newCapacity = 2 * this.numBuckets;//increase the capacity
        ArrayList<LinkedList<HashPair<K,V>>> oldBucket = this.buckets;//store the old bucket
        this.buckets = new ArrayList<LinkedList<HashPair<K,V>>>(newCapacity);//point this bucket to a new arraylist
        //fill the new arraylist with linkedlist
        for (int i=0; i < newCapacity; i++) {
 	       LinkedList<HashPair<K, V>> newLinkedList = new LinkedList<HashPair<K,V>>();
 	       this.buckets.add(newLinkedList);
        }
    
        this.numBuckets = newCapacity;//increase the capacity for the bucket
        //move linkedlist from the old bucket to the new bucket
        for (LinkedList<HashPair<K, V>> list: oldBucket) {
        	   
        	     for (HashPair<K, V> hp : list) {
        	    	      int bucketIndex = hashFunction(hp.getKey());//refind the index 
        	    	    	  this.buckets.get(bucketIndex).add(hp);
        	    	    	            	    	      
        	     }
        }
        
        
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Return a list of all the keys present in this hashtable.
     */
    
    public ArrayList<K> keys() {
        //ADD YOUR CODE BELOW HERE
      	ArrayList<K> keysArr = new ArrayList<K>();//create an array
      	MyHashTable<K, V>.MyHashIterator iter = this.iterator();
      	while(iter.hasNext()) {
      		keysArr.add(iter.next().getKey());//add key
      	
      	}
    	   
    	    
        return keysArr;
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(n)
     */
    public ArrayList<V> values() {
        //ADD CODE BELOW HERE
    	    ArrayList<V> valuesArr = new ArrayList<V>();//create an array to store values
    	   
    	    MyHashTable<K, V>.MyHashIterator iter = this.iterator();
        while(iter.hasNext()) {
           
        valuesArr.add(iter.next().getValue());//add value
        	        	
        }
    	    
        return valuesArr;
        //ADD CODE ABOVE HERE
    }
    
    
    
    @Override
    public MyHashIterator iterator() {
        return new MyHashIterator();
    }
    
    
    private class MyHashIterator implements Iterator<HashPair<K,V>> {
        private LinkedList<HashPair<K,V>> entries;
        
        private MyHashIterator() {
            //ADD YOUR CODE BELOW HERE
            entries = new LinkedList<HashPair<K, V>>();
            for(LinkedList<HashPair<K, V>> list: buckets) {
            	    for(HashPair<K, V> hp: list) {
            	    	    entries.add(hp);//add all the elements from the arraylist to the entry linkedlist
            	    }
            }
            
            }
            //ADD YOUR CODE ABOVE HERE
        
        
        @Override
        public boolean hasNext() {
            //ADD YOUR CODE BELOW HERE
            return !(this.entries.isEmpty());
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        public HashPair<K,V> next() {
            //ADD YOUR CODE BELOW HERE
        	    
            return this.entries.removeFirst();
            //ADD YOUR CODE ABOVE HERE
        }
        
    }
}

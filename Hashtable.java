import java.lang.Math;
import java.util.List;
import java.util.ArrayList;

public class Hashtable {

	HashNode[] buckets;
	int arraySize;
	double load_THRESHOLD;
	int entries;

	public Hashtable()
	{
		arraySize = 235886;
		buckets = new HashNode[arraySize];
		load_THRESHOLD = 0.5;
		entries = 0;
	}

	public String get(String key){
		HashNode head = buckets[getHash(key)];
		while(head != null) {
			if(head.key.equals(key))
				return(head.value);
			head = head.next;
		}
		return(null);
	}

	public boolean containsKey(String key) {
		//This checks to see if the index of buckets that would 
		//correspond to the key has a value in it
		return(buckets[getHash(key)] != null);	
	}

	public int getHash(String key)
	{
		return Math.abs(key.hashCode() % arraySize);
	}

	public void put(String key, String value) {
		HashNode head = buckets[getHash(key)];
		if(head == null) {
			buckets[getHash(key)] = new HashNode(key, value);
		}
		else {
			while(head != null){
				if(head.key.equals(key)){
					head.value = value;
				}
				head = head.next;
			}
			HashNode node = new HashNode(key, value);
			node.next = buckets[getHash(key)];
			buckets[getHash(key)] = node;
		}
		entries++;
		if(((entries * 1.0) / arraySize) >= load_THRESHOLD){
			Hashtable newTable = new Hashtable();
			while(((entries * 1.0) / arraySize) >= load_THRESHOLD) {
				arraySize++;
			}
			//Rehash each item in the original buckets so that now it's in its proper place in newBuckets
			
			HashNode[] newBuckets = new HashNode[arraySize];
			for(HashNode b: buckets)
				if(b != null)
					newBuckets[getHash(b.key)] = new HashNode(b.key, b.value);

			buckets = newBuckets;
		}
	}

	public String remove(String key)
	{
		HashNode head = buckets[getHash(key)];
		if(head != null){
			if(head.key.equals(key)) {
				buckets[getHash(key)] = head.next;
				entries--;
				return(head.value);
			}
			buckets[getHash(key)] = head.next;
		}
		else{ 
			HashNode prev = head;
			HashNode current = null;
			while(!prev.next.value.equals(null))
				current = prev.next;
			if(current.key.equals(key))
				prev.next = current.next;
		}
		return null;
	}
}
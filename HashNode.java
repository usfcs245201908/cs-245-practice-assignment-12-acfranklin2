public class HashNode
{
	String key;
	String value;
	HashNode next;

	public HashNode()
	{
		key = null;
		value = null;
		next = null;
	}

	public HashNode(String key, String value)
	{
		this.key = key;
		this.value = value;
		next = null;
	}

}


public class StringArrayIterator implements Iterator{
	String arr[];
	int pos=0;
	StringArrayIterator(String[] arr){
		this.arr=arr;
	}
	public boolean hasNext() {
		return (pos<arr.length)? true : false;
	}
	public Object next() {
		return arr[pos++];
	}
}

package Iterator;
public class StringArrayIterator implements Iterator{
	String arr[];
	int pos=0;
	public StringArrayIterator(String[] arr){
		this.arr=arr;
	}
	public boolean hasNext() {
		return (pos<arr.length)? true : false;
	}
	public Object next() {
		return arr[pos++];
	}
	public void remove() {
		for(int i=0;i<arr.length;i++)
			arr[i]="";
		pos=0;
	}

}

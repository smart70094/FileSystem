package Iterator;
import java.util.ArrayList;

import Composite.FileComponent;

public  class FileListIterator implements Iterator{
	ArrayList<FileComponent> list;
	int pos=0;
	public FileListIterator(ArrayList<FileComponent> list){
		this.list=list;
	}
	public boolean hasNext() {
		return (pos<list.size())? true : false;
	}
	public Object next() {
		return list.get(pos++);
	}
	public void remove() {
		list.clear();
	}
}

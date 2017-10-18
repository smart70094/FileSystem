import java.util.ArrayList;

public  class FileListIterator implements Iterator{
	ArrayList<FileComponent> list;
	int pos=0;
	FileListIterator(ArrayList<FileComponent> list){
		this.list=list;
	}
	public boolean hasNext() {
		return (pos<list.size())? true : false;
	}
	public Object next() {
		return list.get(pos++);
	}
}

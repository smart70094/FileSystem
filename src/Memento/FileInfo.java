package Memento;

public class FileInfo implements Cloneable{
	public String state;
	public String context;
	
	public FileInfo clone() throws CloneNotSupportedException {
		FileInfo fileInfo=(FileInfo)super.clone();
		return fileInfo;
	}

}

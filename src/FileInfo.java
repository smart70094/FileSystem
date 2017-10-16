
public class FileInfo implements Cloneable{
	String source,destination;
	String state;
	String context;
	
	public FileInfo clone() throws CloneNotSupportedException {
		FileInfo fileInfo=(FileInfo)super.clone();
		return fileInfo;
	}

}

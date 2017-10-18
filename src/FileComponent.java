import java.util.ArrayList;

public abstract class FileComponent {
	
	protected String name="";
	protected String context="";
	protected String type="";
	protected int size=0;
	
	
	public void add(FileComponent f) {
		throw new UnsupportedOperationException();
	}
	public void remove(String name) {
		throw new UnsupportedOperationException();
	}
	public FileComponent get(String name) {
		throw new UnsupportedOperationException();
	}
	
	static ArrayList<String>  resultList=new ArrayList<String>();
	static boolean find=false;
	public boolean search(FileComponent targetFile) {
		throw new UnsupportedOperationException();
	}
	public String getPath(FileComponent targetFile) {
		throw new UnsupportedOperationException();
	}
	public String getList() {
		throw new UnsupportedOperationException();
	}
	public FileComponent getParent(FileComponent target) {
		throw new UnsupportedOperationException();
	}
	
	public abstract int getSize();
	public abstract String getName();
	public String getInfo() {
		return name+"-"+type+"-"+Integer.toString(size);
	}
	
	
}

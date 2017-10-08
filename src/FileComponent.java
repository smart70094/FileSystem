import java.util.ArrayList;

public abstract class FileComponent {
	
	protected String name="";
	protected String context="";
	
	
	public void add(FileComponent f) {
		throw new UnsupportedOperationException();
	}
	public void remove(FileComponent f) {
		throw new UnsupportedOperationException();
	}
	public FileComponent get() {
		throw new UnsupportedOperationException();
	}
	public ArrayList<String> search(FileComponent targetFile) {
		throw new UnsupportedOperationException();
	}
	public String getPath(FileComponent targetFile) {
		throw new UnsupportedOperationException();
	}
	
	public abstract int getSize();
	public abstract String getName();
	
	
}

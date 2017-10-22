package Composite;

public class Text extends FileComponent{
	public Text(String name){
		super.name=name;
		super.type="Text";
	}

	public int getSize() {
		super.size=context.length();
		return size;
	}
	
	public void setName(String s) {
		name=s;
	}
	
	public String getName() {
		return name;
	}
}


public class Log extends FileComponent{
	Log(String name){
		super.name=name;
		super.type="Log";
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

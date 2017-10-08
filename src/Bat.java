

public class Bat extends FileComponent{

	Bat(String name){
		super.name=name+".bat";
	}
	
	public int getSize() {
		return (name.length()+context.length())*2;
	}
	
	public void setName(String s) {
		name=s;
	}
	
	public String getName() {
		return name+".bat";
	}
	
}

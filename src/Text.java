

public class Text extends FileComponent{
	
	Text(String name){
		super.name=name+".txt";
		super.type="file";
	}

	@Override
	public int getSize() {
		return (name.length()+context.length());
	}
	
	public void setName(String s) {
		name=s+".txt";
	}
	
	public String getName() {
		return name;
	}

}

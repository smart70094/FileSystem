import java.util.ArrayList;

public class FileFacade {
	FileComponent root;
	FileComponent currentNode;
	FileFacade(){
		root=new Directory("");
		currentNode=root;
	}
	public  String testComposite() {
		FileComponent read=new Directory("read");
		FileComponent jim=new Directory("jim");
		FileComponent tools=new Directory("tools");
		FileComponent eclipse=new Directory("eclipse");
		FileComponent conf=new Directory("conf");
		FileComponent as=new Directory("as");
		FileComponent txt=new Text("txt");
		FileComponent ftxt=new Text("ftxt");
		as.add(txt);
		
		eclipse.add(conf);
		conf.add(ftxt);
		
		tools.add(eclipse);
		tools.add(as);
		
		jim.add(read);
		jim.add(tools);
		
		root.add(jim); 
		
		return currentNode.getList();
	}
	
	public String getList() {
		return currentNode.getList();
	}
	
	public void add(String name,String type) {
		switch(type) {
		case "directory":
			currentNode.add(new Directory(name));
			break;
		case "Text":
			currentNode.add(new Text(name));
			break;
		}
	}
	public boolean move(String name) {
		String arr[]=currentNode.getList().split(",");
		boolean find=false;
		for(int i=0;i<arr.length;i++) {
			if(name.equals(arr[i])) {
				find=true;
				currentNode=currentNode.get(name);
				break;
			}
		}
		return find;
	}
	public void moveBack() {
		/*ArrayList<String> list=root.search(currentNode);
		
		int index=list.size()-1;
		if(index==0) 
			currentNode=root;
		else
			currentNode=root.get(list.get(list.size()-2));*/
		if(root.search(currentNode)) {
			FileComponent f=root.getParent(currentNode);
			currentNode=(f==null)? root:f;
		}else {
			currentNode=root;
		}
		
		
	}
	
}

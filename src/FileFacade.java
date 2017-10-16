import java.util.ArrayList;

public class FileFacade {
	FileComponent root;
	FileComponent currentNode;
	FileCareTaker fileCareTaker;
	FileFacade(){
		root=new Directory("");
		currentNode=root;
		fileCareTaker=new FileCareTaker();
	}
	public  String loadingTest() {
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
		root.name="root";
		
		return currentNode.getList();
	}
	

	
	public void add(String name,String type) {
		FileInfo fileInfo=new FileInfo();
		fileInfo.state="add";
		fileInfo.context=type+","+name;
		addFile(name,type);
		save(fileInfo);
	}
	public void addFile(String name,String type) {
		switch(type) {
		case "directory":
			currentNode.add(new Directory(name));		
			break;
		case "Text":
			currentNode.add(new Text(name));	
			break;
		case "Bat":
			currentNode.add(new Bat(name));
			break;
		}
	}
	public void remove(String name) {
		FileInfo fileInfo=new FileInfo();
		fileInfo.state="remove";
		fileInfo.context=root.get(name).type+","+name;
		removeFile(name);
		save(fileInfo);
	}
	public void removeFile(String name) {
		currentNode.remove(name);
	}
	public void rename(String name,String s) {
		FileInfo fileInfo=new FileInfo();
		fileInfo.state="rename";
		fileInfo.context=name+","+s;
		renameFile(name,s);
		save(fileInfo);
	}
	public void renameFile(String name,String s) {
		FileComponent fc=currentNode.get(name);
		fc.name=s;
	}
	
	//移動到下一個資料夾
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
		if(root.search(currentNode)) {
			FileComponent f=root.getParent(currentNode);
			currentNode=(f==null)? root:f;
		}else {
			currentNode=root;
		}
	}
	
	//移動到父資料夾
	public String getList() {
		return currentNode.getList();
	}
	public void save(FileInfo fileInfo) {
		FileMemento fileMemento=new FileMemento();
		fileMemento.setMemento(fileInfo);
		fileCareTaker.addMemento(currentNode,fileMemento);
	}
	
	public String undo() {
		FileMemento fileMemento=fileCareTaker.undo(currentNode);
		
		if(fileMemento!=null) {
			FileInfo fileInfo=fileMemento.getMemento();
			String state=fileInfo.state;
			String context=fileInfo.context;
			String arr[];
			switch(state) {
			case "add":
				arr=context.split(",");
				removeFile(arr[1]);
				break;
			case "remove":
				arr=context.split(",");
				addFile(arr[1],arr[0]);
				break;
			case "rename":
				arr=context.split(",");
				renameFile(arr[1],arr[0]);
				break;
			}
			return state+","+context;
		}
		return null;
	}
	public String redo() {
		FileMemento fileMemento=fileCareTaker.redo(currentNode);
		
		if(fileMemento!=null) {
			FileInfo fileInfo=fileMemento.getMemento();
			String state=fileInfo.state;
			String context=fileInfo.context;
			String arr[];
			arr=context.split(",");
			switch(state) {
			case "remove":
				removeFile(arr[1]);
				break;
			case "add":
				addFile(arr[1],arr[0]);
				break;
			case "rename":
				renameFile(arr[0],arr[1]);
				break;
			}
			return state+","+context;
		}
		return null;
	}
}

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
	public  String loadingSystemFolder() {
		FileComponent read=new SystemDirectory("read");
		FileComponent jim=new SystemDirectory("jim");
		FileComponent tools=new SystemDirectory("tools");
		FileComponent eclipse=new SystemDirectory("eclipse");
		FileComponent conf=new SystemDirectory("conf");
		FileComponent as=new SystemDirectory("as");
		FileComponent txt=new Text("txt");
		FileComponent ftxt=new Text("ftxt");
		FileComponent fff=new Text("123");
		fff.context="akdhfjjahdfjkhajsk";
		as.add(txt);	
		eclipse.add(conf);
		conf.add(ftxt);
		tools.add(eclipse);
		tools.add(as);
		jim.add(read);
		jim.add(tools);
		root.add(jim); 
		root.add(fff);
		root.name="root";
		return currentNode.getList();
	}
	

	
	public void add(String name,String type) {
		addFile(name,type);
		
		FileInfo fileInfo=new FileInfo();
		fileInfo.state="add";
	
		fileInfo.context=type+","+name;
		save(fileInfo);
	}
	public void addFile(String name,String type) {
		switch(type) {
		case "Directory":
			currentNode.add(new Directory(name));		
			break;
		case "Text":
			currentNode.add(new Text(name));	
			break;
		case "Log":
			currentNode.add(new Log(name));
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
	public void updateFileContext(String name,String s) {
		FileComponent fc=currentNode.get(name);
		if(!fc.context.equals(s)) {
			FileInfo fileInfo=new FileInfo();
			fileInfo.state="updateContext";
			fileInfo.context=name+","+fc.context+","+s;
			updateContext(name,s);
			save(fileInfo);
		}
	}
	public void renameFile(String name,String s) {
		FileComponent fc=currentNode.get(name);
		fc.name=s;
	}
	public void updateContext(String name,String context) {
		FileComponent fc=currentNode.get(name);
		fc.context=context;
	}
	
	//移動到下一個資料夾
	public String move(String name) {
		currentNode=currentNode.get(name);
		return getList();
	}
	public String moveBack() {
		if(root.search(currentNode)) {
			FileComponent f=root.getParent(currentNode);
			currentNode=(f==null)? root:f;
		}else {
			currentNode=root;
		}
		return currentNode.getList();
	}
	
	//移動到父資料夾
	public String getList() {
		return currentNode.getList();
	}
	public String[] getInfo(String name) {
		FileComponent f=root.get(name);
		String str[]=f.getInfo().split("-");
		return str;
		
	}
	public String getContext(String name) {
		String result="";
		FileComponent f=root.get(name);
		if(f.type.equals("Text") || f.type.equals("Log")) {
			result=f.context;
		}
		return result;
	}
	public int getSize(String name) {
		FileComponent f=root.get(name);
		return f.getSize();
	}
	public String getType(String name) {
		FileComponent f=root.get(name);
		return f.type;
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
			case "updateContext":
				arr=context.split(",");
				updateContext(arr[0],arr[1]);
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
			case "updateContext":
				arr=context.split(",");
				updateContext(arr[0],arr[2]);
				break;
			}
			return state+","+context;
		}
		return null;
	}
}

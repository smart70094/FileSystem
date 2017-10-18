import java.util.ArrayList;

public class SystemDirectory extends FileComponent{
	ArrayList<FileComponent> fileList=new ArrayList<FileComponent>();
	
	SystemDirectory(String name){
		super.name=name;
		super.type="SystemDirectory";
		super.size=0;
	}
	
	public void add(FileComponent f) {
		fileList.add(f);
	}
	public void remove(String name) {
		FileComponent f=get(name);
		fileList.remove(f);
	}
	
	public boolean search(FileComponent targetFile) {
		FileListIterator it=new FileListIterator(fileList);
		FileComponent f = null;
		while(it.hasNext()) { 
			f=(FileComponent) it.next();
			if(targetFile.equals(f)) {
				find=true;
				resultList.add(f.getName());
				break;
			}else if(f.type.equals("Text")||f.type.equals("Log")) continue;
			else if(!find){
				resultList.add(f.getName());
				f.search(targetFile);
			}
		}
		
		if(!find && resultList.size()>0) {
			resultList.remove(resultList.size()-1);
		}
		return find;
	}
	public String getList() {
		String result="";
		FileListIterator it=new FileListIterator(fileList);
		while(it.hasNext()) {
			FileComponent f=(FileComponent)it.next();
			result+=f.getName()+"-"+f.type+"-"+f.getSize()+",";
		}
		
		return result;
	}

	public int getSize() {
		FileListIterator it=new FileListIterator(fileList);
		int sum=0;
		while(it.hasNext()) {
			FileComponent f=(FileComponent)it.next();
			sum+=f.getSize();
		}
		return sum;
	}
	public FileComponent getParent(FileComponent target) {
		FileComponent f = null;
		int index=resultList.size()-2;
		if(index>-1) 
			f=get(resultList.get(index));
		initial();
		return f;
	}
	public FileComponent get(String name) {
		String result="";
		FileListIterator it=new FileListIterator(fileList);
		FileComponent f = null;
		while(it.hasNext()) {
			f=(FileComponent)it.next();
			if(f.getName().equals(name)) return f;
			if(f.type.equals("Directory")||f.type.equals("SystemDirectory")) {
				f=f.get(name); 
				if(f!=null) return f;
			}
		}
		return null;
	}
	public void setName(String s) {
		name=s;
	}
	public String getName() {
		return name;
	}
	
	private void initial() {
		resultList.clear();
		find=false;
	}
}

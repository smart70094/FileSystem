
import java.util.ArrayList;
import java.util.Iterator;





public class Directory extends FileComponent{
	ArrayList<FileComponent> fileList=new ArrayList<FileComponent>();
	
	Directory(String name){
		super.name=name;
		super.type="directory";
	}
	
	public void add(FileComponent f) {
		fileList.add(f);
	}
	public void remove(String name) {
		FileComponent f=get(name);
		fileList.remove(f);
	}
	
	static ArrayList<String>  resultList=new ArrayList<String>();
	static boolean find=false;
	public boolean search(FileComponent targetFile) {
		Iterator it=(Iterator) fileList.iterator();
		FileComponent f = null;
		while(it.hasNext()) { 
			f=(FileComponent) it.next();
			if(targetFile.equals(f)) {
				find=true;
				resultList.add(f.getName());
				break;
			}else if(f.getName().indexOf(".")>-1) continue;
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
	public String getPath(FileComponent targetFile) {
		ArrayList<String>  resultList=new ArrayList<String>();
		search(targetFile);
		Iterator it=(Iterator) resultList.iterator();
		String result="c:";
		while(it.hasNext()) {
			result+="/"+(String)it.next();
		}
		find=false;
		initial();
		return result;
	}

	public String getList() {
		String result="";
		Iterator it=(Iterator) fileList.iterator();
		while(it.hasNext()) {
			FileComponent f=(FileComponent)it.next();
			result+=f.getName()+",";
		}
		
		return result;
	}

	public int getSize() {
		Iterator it=fileList.iterator();
		int sum=0;
		while(it.hasNext()) {
			FileComponent f=(FileComponent)it.next();
			sum+=getName().length()+f.getSize();
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
		Iterator it=(Iterator) fileList.iterator();
		FileComponent f = null;
		while(it.hasNext()) {
			f=(FileComponent)it.next();
			if(f.getName().indexOf(".")>-1) continue;
			if(f.getName().equals(name)) break;
			f=f.get(name); 
		}
		return f;
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


import java.util.ArrayList;
import java.util.Iterator;





public class Directory extends FileComponent{
	ArrayList<FileComponent> fileList=new ArrayList<FileComponent>();
	
	Directory(String name){
		super.name=name;
	}
	
	public void add(FileComponent f) {
		fileList.add(f);
	}
	public void remove(FileComponent f) {
		fileList.remove(f);
	}
	
	
	static ArrayList<String>  resultList=new ArrayList<String>();
	static boolean find=false;
	public ArrayList<String> search(FileComponent targetFile) {
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
				resultList=f.search(targetFile);
			}
		}
		
		if(!find && resultList.size()>0) {
			resultList.remove(resultList.size()-1);
		}
		return resultList;
	}
	public String getPath(FileComponent targetFile) {
		ArrayList<String>  resultList=new ArrayList<String>();
		resultList=search(targetFile);
		Iterator it=(Iterator) resultList.iterator();
		String result="c:";
		while(it.hasNext()) {
			result+="/"+(String)it.next();
		}
		find=false;
		resultList.clear();
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
	public void setName(String s) {
		name=s;
	}
	public String getName() {
		return name;
	}



}

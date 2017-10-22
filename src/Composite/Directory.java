package Composite;
import java.util.ArrayList;

import Iterator.FileListIterator;

public class Directory extends FileComponent{
	ArrayList<FileComponent> fileList=new ArrayList<FileComponent>();
	
	public Directory(String name){
		super.name=name;
		super.type="Directory";
	}
	
	public void add(FileComponent f) {
		fileList.add(f);
	}
	public void remove(String name) {
		FileComponent f=get(name);
		fileList.remove(f);
	}
	
	/*
	 * 利用遞迴-回朔法，橫跨SystemDirectory的search()
	 * 將經歷過的資料夾記錄到resultList裡面，並且在一層一層深入直到找到目標在開始回來
	 * 如果沒找到目標，在回來的過程會將記錄刪除
	 * 最後整個跑完resultList從0到size就是檔案路徑
	 */
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
	//取得資料夾所包含的資料，並且不會更深入拜訪
	public String getList() {
		String result="";
		FileListIterator it=new FileListIterator(fileList);
		while(it.hasNext()) {
			FileComponent f=(FileComponent)it.next();
			result+=f.getName()+"-"+f.type+"-"+f.getSize()+",";
		}
		
		return result;
	}
	//取得資料夾大小，並且會進行更深層的拜訪
	public int getSize() {
		FileListIterator it=new FileListIterator(fileList);
		int sum=0;
		while(it.hasNext()) {
			FileComponent f=(FileComponent)it.next();
			sum+=f.getSize();
		}
		return sum;
	}
	//利用resultList先將路徑建完後，就可以輕易取得資料夾或檔案的父資料夾
	public FileComponent getParent(FileComponent target) {
		FileComponent f = null;
		int index=resultList.size()-2;
		if(index>-1) 
			f=get(resultList.get(index));
		initial();
		return f;
	}
	//利用名子為ID取得FileComponent的檔案
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

import java.util.ArrayList;
import java.util.HashMap;

public class FileCareTaker {
	//利用composite當ID,儲存每個composite的memento
	private HashMap<FileComponent,ArrayList<FileMemento>> fileStateMap=new HashMap<FileComponent,ArrayList<FileMemento>>();
	//利用composite當ID,儲存每個compositeＳ的
	private HashMap<FileComponent,Integer> fileIndexMap=new HashMap<FileComponent,Integer>();
	public void addMemento(FileComponent fc,FileMemento fm) {
		if(fileStateMap.containsKey(fc)) {
			//最大邊界
			int size=fileStateMap.get(fc).size()-1;
			//目前位子
			int index=fileIndexMap.get(fc);
			//多因為undo在執行會產生多於用不到的記錄需清除，以維護後續使用
			ArrayList<FileMemento> list=fileStateMap.get(fc);
			if(size>index) 
				list=remove(list,index,size);
			list.add(fm);
			fileStateMap.put(fc, list);
			
			fileIndexMap.put(fc, fileIndexMap.get(fc)+1);
		}else {
			ArrayList<FileMemento> list=new ArrayList<FileMemento>();
			list.add(fm);
			fileStateMap.put(fc, list);
			fileIndexMap.put(fc, 0);
		}
	}
	public ArrayList<FileMemento> remove(ArrayList<FileMemento> list,int start,int dest) {
		start++;
		for(int i=start;i<=dest;i++) {
			if(start<=list.size()-1)
				list.remove(start);
		}
		return list;
	}
	public FileMemento undo(FileComponent fc) {
		try {
			ArrayList<FileMemento> list=fileStateMap.get(fc);
			if(list!=null) {
				FileMemento fm;
				int index=fileIndexMap.get(fc);
				int size=list.size()-1;
				if(index>size) {
					fm=list.get(--index);
					index--;
					fileIndexMap.put(fc, index);
					return fm;
				}
				else if(index>=0) {
					fm=list.get(index--);
					fileIndexMap.put(fc, index);
					return fm;
				}
			}
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	public FileMemento redo(FileComponent fc) {
		ArrayList<FileMemento> list=fileStateMap.get(fc);
		if(list!=null) {
			int index=fileIndexMap.get(fc);
			int size=list.size()-1;
			 if((index+1)<=size) {
				FileMemento fm=list.get(++index);
				fileIndexMap.put(fc, index);
				return fm;
			}
		}
		return null;
	}
}

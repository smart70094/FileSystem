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
	 * �Q�λ��j-�^�Ҫk�A���SystemDirectory��search()
	 * �N�g���L����Ƨ��O����resultList�̭��A�åB�b�@�h�@�h�`�J������ؼЦb�}�l�^��
	 * �p�G�S���ؼСA�b�^�Ӫ��L�{�|�N�O���R��
	 * �̫��Ӷ]��resultList�q0��size�N�O�ɮ׸��|
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
	//���o��Ƨ��ҥ]�t����ơA�åB���|��`�J���X
	public String getList() {
		String result="";
		FileListIterator it=new FileListIterator(fileList);
		while(it.hasNext()) {
			FileComponent f=(FileComponent)it.next();
			result+=f.getName()+"-"+f.type+"-"+f.getSize()+",";
		}
		
		return result;
	}
	//���o��Ƨ��j�p�A�åB�|�i���`�h�����X
	public int getSize() {
		FileListIterator it=new FileListIterator(fileList);
		int sum=0;
		while(it.hasNext()) {
			FileComponent f=(FileComponent)it.next();
			sum+=f.getSize();
		}
		return sum;
	}
	//�Q��resultList���N���|�ا���A�N�i�H�������o��Ƨ����ɮת�����Ƨ�
	public FileComponent getParent(FileComponent target) {
		FileComponent f = null;
		int index=resultList.size()-2;
		if(index>-1) 
			f=get(resultList.get(index));
		initial();
		return f;
	}
	//�Q�ΦW�l��ID���oFileComponent���ɮ�
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

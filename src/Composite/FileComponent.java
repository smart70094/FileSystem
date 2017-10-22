package Composite;
import java.util.ArrayList;

public abstract class FileComponent {
	
	public String name="";
	public String context="";
	public String type="";
	public int size=0;
	
	
	public void add(FileComponent f) {
		throw new UnsupportedOperationException();
	}
	public void remove(String name) {
		throw new UnsupportedOperationException();
	}
	//�Q��name��ID���o�ɮ�
	public FileComponent get(String name) {
		throw new UnsupportedOperationException();
	}
	//���o��Ƨ��̭����ɭ�
	public String getList() {
		throw new UnsupportedOperationException();
	}
	//���o��Ƨ����ɮת�����Ƨ�
	public FileComponent getParent(FileComponent target) {
		throw new UnsupportedOperationException();
	}
	
	
	/*1.�b�s�����ɭԦ]���n�j�M���|�A�ҥH�ݭn�@���ܼƪ��ͩR�g���P�d���[�\Directory�PSystemDirectory�ΥH���X�R��h��compsite
	 * 2.
	 * ����leaf��s���쪺�ü{�A�b�Τ@�ާ@���ɭԧڭ̪�leaf�Pcomposite�o�꦳�i��leaf�ާ@�줣�Oleaf�i�H�ާ@����k
	 * �ҥH�ڭ̻ݭn�γz���Φw�����覡�h���@
	 * �ӦbresultList�Pfind�P�L�̤��@�˪��O�Aleaf�������|�ާ@��o����ܼơA�u��Composite�~���ާ@�o���ܼƪ���k
	 * leaf�ä��|���V�v�����D
	 */
	static ArrayList<String>  resultList=new ArrayList<String>();
	static boolean find=false;
	public boolean search(FileComponent targetFile) {
		throw new UnsupportedOperationException();
	}
	public abstract int getSize();
	public abstract String getName();
	/*
	 * ���F�N�ɮץ[��view�̭��ҷǳƪ���Ʈ榡�A�Q��"-"���}�A��F�e�ݴN�i�H�Q��splite�N�C�Ӥ������A����l�W
	 */
	public String getInfo() {
		return name+"-"+type+"-"+Integer.toString(size);
	}
	
	
}

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
	//利用name為ID取得檔案
	public FileComponent get(String name) {
		throw new UnsupportedOperationException();
	}
	//取得資料夾裡面的檔面
	public String getList() {
		throw new UnsupportedOperationException();
	}
	//取得資料夾或檔案的父資料夾
	public FileComponent getParent(FileComponent target) {
		throw new UnsupportedOperationException();
	}
	
	
	/*1.在瀏覽的時候因為要搜尋路徑，所以需要一個變數的生命週期與範圍能涵蓋Directory與SystemDirectory或以後擴充更多的compsite
	 * 2.
	 * 關於leaf能存取到的疑慮，在統一操作的時候我們的leaf與composite卻實有可讓leaf操作到不是leaf可以操作的方法
	 * 所以我們需要用透明或安全的方式去維護
	 * 而在resultList與find與他們不一樣的是，leaf完全不會操作到這兩個變數，只有Composite才有操作這些變數的方法
	 * leaf並不會有越權的問題
	 */
	static ArrayList<String>  resultList=new ArrayList<String>();
	static boolean find=false;
	public boolean search(FileComponent targetFile) {
		throw new UnsupportedOperationException();
	}
	public abstract int getSize();
	public abstract String getName();
	/*
	 * 為了將檔案加到view裡面所準備的資料格式，利用"-"分開，到了前端就可以利用splite將每個元素放到適當的位子上
	 */
	public String getInfo() {
		return name+"-"+type+"-"+Integer.toString(size);
	}
	
	
}

package Memento;

public class FileMemento  implements Cloneable{
	FileInfo fileInfo;
	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo=fileInfo;
	}
	public FileInfo getFileInfo() {
		return fileInfo;
	}
	
	public FileMemento clone() throws CloneNotSupportedException {
		//���ϥΤ����O���ƻs��k�i��ƻs�ާ@
		FileMemento fileMemento = (FileMemento) super.clone();
		if (this.fileInfo != null)
			fileMemento.fileInfo = (FileInfo) this.fileInfo.clone();
		return fileMemento;
	}
}

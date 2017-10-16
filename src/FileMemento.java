
public class FileMemento  implements Cloneable{
	FileInfo fileInfo;
	public void setMemento(FileInfo fileInfo) {
		this.fileInfo=fileInfo;
	}
	public FileInfo getMemento() {
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

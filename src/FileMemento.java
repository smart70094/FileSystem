
public class FileMemento  implements Cloneable{
	FileInfo fileInfo;
	public void setMemento(FileInfo fileInfo) {
		this.fileInfo=fileInfo;
	}
	public FileInfo getMemento() {
		return fileInfo;
	}
	
	public FileMemento clone() throws CloneNotSupportedException {
		//先使用父類別的複製方法進行複製操作
		FileMemento fileMemento = (FileMemento) super.clone();
		if (this.fileInfo != null)
			fileMemento.fileInfo = (FileInfo) this.fileInfo.clone();
		return fileMemento;
	}
}

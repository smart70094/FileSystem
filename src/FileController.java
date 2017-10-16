import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileController {
	FileFacade fileFacade=new FileFacade();
	View view;

	public void testComposite() {
		String s=fileFacade.loadingTest();
		
		String[] resultArr=s.split(",");
		//iterator
		for(int i=0;i<resultArr.length;i++) {
			view.addFile(resultArr[i]);
		}
		
	}
	public void startView() {
		if(view==null) {
			view=view.getInstance();
			view.setVisible(true);
			view.addDirectoryListener(new AddDirectoryListener());
			view.addDirListener(new AddDirListener());
			view.addMoveListener(new AddMoveListener());
			view.addMoveBackListener(new AddMoveBackListener());
			view.addRemoveListener(new AddRemoveListener());
			view.addundoListener(new AddUndoListener());
			view.addRedoListener(new AddRedoListener());
			view.addRenameListener(new AddRenameListener());
		}else {
			view.setVisible(true);
		}
	}
	public void startFileView() {
		FileView fileView = null;
		fileView=fileView.getInstance();
		fileView.setVisible(true);
	}


	//�s�W��Ƨ�
	class AddDirectoryListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String name=view.getInputNameString();
			fileFacade.add(name,"directory");
		}
	}
	//�C�X��Ƨ����e
	class AddDirListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			
			System.out.println(fileFacade.getList());
		}
	}
	//����
	class AddMoveListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			view.clearView();
			//���ʨ쪺��V
			String targetName=view.getInputNameString();
			if(fileFacade.move(targetName)) {
				String result=fileFacade.getList();
				String[] resultArr=result.split(",");
				//iterator
				for(int i=0;i<resultArr.length;i++) {
					view.addFile(resultArr[i]);
				}
			}
			
		}
	}
	//���s����ɦW
	class AddRenameListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String reName=view.message();
			String targetName=view.getInputNameString();
			fileFacade.rename(targetName, reName);
		}
	}
	//���^����
	class AddMoveBackListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			view.clearView();
			//���ʨ쪺��V
			
			fileFacade.moveBack();
			String result=fileFacade.getList();
			String[] resultArr=result.split(",");
			//iterator
			for(int i=0;i<resultArr.length;i++) {
				view.addFile(resultArr[i]);
			}
			
		}
	}
	
	//�R��
	class AddRemoveListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String name=view.getInputNameString();
			fileFacade.remove(name);
			view.removeFile(name);
		}
	}
	//undo
	class AddUndoListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String cmd=fileFacade.undo();
			if(cmd!=null) {
				String arr[]=cmd.split(",");
				String state=arr[0];
				String name=arr[2];
				switch(state) {
				case "add":
					view.removeFile(name);
					break;
				case "remove":
					view.addFile(name);
					break;
				case "rename":
					view.renameFile(arr[1],arr[2]);
					break;
				}
			}
			}
	}
	//redo
		class AddRedoListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				String cmd=fileFacade.redo();
				if(cmd!=null) {
					String arr[]=cmd.split(",");
					String state=arr[0];
					String name=arr[2];
					switch(state) {
					case "remove":
						view.removeFile(name);
						break;
					case "add":
						view.addFile(name);
						break;
					case "rename":
						view.renameFile(arr[2], arr[1]);
						break;
					}
				}
				}
		}
}

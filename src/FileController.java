import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileController {
	FileFacade fileFacade=new FileFacade();
	FileView fileView;

	public void testComposite() {
		fileFacade.loadingSystemFolder();
		
		//iterator
		String str=fileFacade.getList();
		String arrInfo[]=str.split(",");
		String arr[];
		for(int i=0;i<arrInfo.length;i++) {
			arr=arrInfo[i].split("-");
			fileView.addFile(fileFacade.getInfo(arr[0]));
		}
			
		
	}

	public void startFileView() {
		if(fileView==null) {
			//�ϥ�singleton pattern���o�ߤ@��FileView
			fileView=FileView.getInstance();
			fileView.setVisible(true);
			//�Q��controller�NFileView�W�����Ҧ��ƥ�Ұ����Ƶ��U�i�h
			fileView.addCreateFolderListener(new AddCreateFolderListener());
			fileView.addCreateSystemFolderListener(new AddCreateSystemFolderListener());
			fileView.addCreateTxtListener(new AddCreateTxtListener());
			fileView.addCreateLogListener(new AddCreateLogListener());
			fileView.addMoveListener(new AddMoveListener());
			fileView.addMoveBackListener(new AddMoveBackListener());
			fileView.addRemoveListener(new AddRemoveListener());
			fileView.addRenameListener(new AddRenameListener());
			fileView.addUndoListener(new AddUndoListener());
			fileView.addRedoListener(new AddRedoListener());
			
			fileView.addStartContextListener(new AddStartContextListener());
			fileView.addContextListener(new AddContextListener());
		}else {
			fileView.setVisible(true);
		}
	}
	//�s�WTxt
	class AddCreateTxtListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String name=fileView.getAlertInput("�п�J�ɮצW��");
			if(name!=null) {
				fileFacade.add(name,"Text");
				String str[]= {name,"Text","0"};
				fileView.addFile(str);
			}
		}
	}
	//�s�WLog
		class AddCreateLogListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				String name=fileView.getAlertInput("�п�J�ɮצW��");
				if(!name.equals(null)) {
					fileFacade.add(name,"Log");
					String str[]= {name,"Log","0"};
					fileView.addFile(str);
				}
			}
		}

	//�s�W��Ƨ�
	class AddCreateFolderListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
		
			String name=fileView.getAlertInput("�п�J��Ƨ��W��");
			if(name!=null) {
				fileFacade.add(name,"Directory");
				String str[]= {name,"Directory","0"};
				fileView.addFile(str);	
			}
		}
	}
	//�s�W�t�θ�Ƨ�
		class AddCreateSystemFolderListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
			
				String name=fileView.getAlertInput("�п�J��Ƨ��W��");
				if(name!=null) {
					fileFacade.add(name,"SystemDirectory");
					String str[]= {name,"SystemDirectory","0"};
					fileView.addFile(str);	
				}
			}
		}
	//����
	class AddMoveListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			//���ʨ쪺��V
			String targetName=fileView.getSelectedFile();
			if(!targetName.equals("")) {
				fileView.clearModel();
				String result=fileFacade.move(targetName);
				String[] resultArr=result.split(",");
				//iterator
				StringArrayIterator it=new StringArrayIterator(resultArr);
				while(it.hasNext()) {
					String s=(String)it.next();
					String[] fileDetail=s.split("-");
					String str[]= {fileDetail[0],fileDetail[1],fileDetail[2]};
					fileView.addFile(str);
				}
				
			}else fileView.alert("�п���ɮ�");
		}
	}
	//���^����
	class AddMoveBackListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			//���ʨ쪺��V
			String result=fileFacade.moveBack();
			if(!result.equals("")) { 
				fileView.clearModel();
				String[] resultArr=result.split(",");
				StringArrayIterator it=new StringArrayIterator(resultArr);
				//iterator
				while(it.hasNext()) {
					String s=(String)it.next();
					String[] fileDetail=s.split("-");
					String str[]= {fileDetail[0],fileDetail[1],fileDetail[2]};
					fileView.addFile(str);
				}
			}
			
		}
	}
	
	//�R��
	class AddRemoveListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String targetName=fileView.getSelectedFile();
			fileFacade.remove(targetName);
			fileView.removeFile();
		}
	}
	
	//���s����ɦW
	class AddRenameListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String targetName=fileView.getSelectedFile();
			String rename=fileView.getAlertInput("���u"+targetName+"�v�W��");
			if(!rename.equals(null)) {
				fileFacade.rename(targetName, rename);
				fileView.renameFile(targetName,rename,fileFacade.getType(rename));
			}
		}
	}
	//�Ұ�context������
	class AddStartContextListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String name=fileView.getSelectedFile();
			String result=fileFacade.getContext(name);
			if(!name.equals("")) {
				fileView.setContext(result);
				fileView.startContext();
			}else fileView.alert("�п���ɮ�");
		}
	}
	//��s�ɮפ��e���
	class AddContextListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String name=fileView.getSelectedFile();
			String context=fileView.getContext();
			fileFacade.updateFileContext(name, context);
			
			fileView.updateSize(name,fileFacade.getSize(name));
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
					fileView.removeFile(name);
					break;
				case "remove":
					String str[]=fileFacade.getInfo(name);
					fileView.addFile(str);
					break;
				case "rename":
					fileView.renameFile(arr[2],arr[1],fileFacade.getType(arr[1]));
					break;
				case "updateContext":
					fileView.updateSize(arr[1],fileFacade.getSize(arr[1]));
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
						fileView.removeFile(name);
						break;
					case "add":
						String str[]=fileFacade.getInfo(name);
						fileView.addFile(str);
						break;
					case "rename":
						fileView.renameFile(arr[1],arr[2],fileFacade.getType(arr[2]));
						break;
					case "updateContext":
						fileView.updateSize(arr[1],fileFacade.getSize(arr[1]));
						break;
					}
				}
		}
	}
}

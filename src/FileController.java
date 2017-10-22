import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Iterator.StringArrayIterator;
import Layout.FileView;

public class FileController {
	FileFacade fileFacade=new FileFacade();
	FileView fileView;

	public void loading() {
		fileFacade.loading();
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
			//使用singleton pattern取得唯一的FileView
			fileView=FileView.getInstance();
			fileView.setVisible(true);
			//利用controller將FileView上面的所有事件所做的事註冊進去
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
			fileView.addSearchListener(new AddSearchListener());
		}else {
			fileView.setVisible(true);
		}
	}
	//新增Txt
	class AddCreateTxtListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String name=fileView.getAlertInput("請輸入檔案名稱");
			if(name!=null) {
				if(fileFacade.add(name,"Text")) {
					String str[]= {name,"Text","0"};
					fileView.addFile(str);
				}else 
					fileView.alert("名稱已經在系統使用過");
			}
		}
	}
	//新增Log
		class AddCreateLogListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				String name=fileView.getAlertInput("請輸入檔案名稱");
				if(!name.equals(null)) {
					if(fileFacade.add(name,"Log")) {
						String str[]= {name,"Log","0"};
						fileView.addFile(str);
					}else
						fileView.alert("名稱已經在系統使用過");
				}
			}
		}

	//新增資料夾
	class AddCreateFolderListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
		
			String name=fileView.getAlertInput("請輸入資料夾名稱");
			if(name!=null) {
				if(fileFacade.add(name,"Directory")) {
					String str[]= {name,"Directory","0"};
					fileView.addFile(str);
				}else
					fileView.alert("名稱已經在系統使用過");
					
			}
		}
	}
	//新增系統資料夾
		class AddCreateSystemFolderListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
			
				String name=fileView.getAlertInput("請輸入資料夾名稱");
				if(name!=null) {
					if(fileFacade.add(name,"SystemDirectory")) {
						String str[]= {name,"SystemDirectory","0"};
						fileView.addFile(str);	
					}else
						fileView.alert("名稱已經在系統使用過");
				}
			}
		}
	//移動
	class AddMoveListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			//移動到的對向
			String targetName=fileView.getSelectedFile();
			if(!targetName.equals("")) {
				
				String result=fileFacade.move(targetName);
				if(result!=null) {
					fileView.clearModel();
					String[] resultArr=result.split(",");
					//iterator
					StringArrayIterator it=new StringArrayIterator(resultArr);
					while(it.hasNext()) {
						String s=(String)it.next();
						String[] fileDetail=s.split("-");
						String str[]= {fileDetail[0],fileDetail[1],fileDetail[2]};
						fileView.addFile(str);
					}
				}else fileView.alert("請選擇資料夾");
				
			}else fileView.alert("請選擇檔案");
		}
	}
	//往回移動
	class AddMoveBackListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			//移動到的對向
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
	
	//刪除
	class AddRemoveListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String targetName=fileView.getSelectedFile();
			fileFacade.remove(targetName);
			fileView.removeFile();
		}
	}
	
	//重新更改檔名
	class AddRenameListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String targetName=fileView.getSelectedFile();
			String rename=fileView.getAlertInput("更改「"+targetName+"」名稱");
			if(!rename.equals(null)) {
				fileFacade.rename(targetName, rename);
				fileView.renameFile(targetName,rename,fileFacade.getType(rename));
			}
		}
	}
	//啟動context的視窗
	class AddStartContextListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String name=fileView.getSelectedFile();
			String result=fileFacade.getContext(name);
			String type=fileView.getSelectedFileType();
			
			if(type.indexOf("Directory")==-1) {
				if(!name.equals("")) {
					fileView.setContext(result);
					fileView.startContext();
				}else fileView.alert("請選擇檔案");
			}else {
				fileView.alert("請選擇檔案");
			}
		}
	}
	//更新檔案內容資料
	class AddContextListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String name=fileView.getSelectedFile();
			String context=fileView.getContext();
			fileFacade.updateFileContext(name, context);
			fileView.updateSize(name,fileFacade.getSize(name));
			
		}
	}
	
	//搜尋檔案
		class AddSearchListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				//移動到的對向
				String targetName=fileView.getTargetTxt();
				if(!targetName.equals("")) {
					String result=fileFacade.moveTarget(targetName);
					if(result!=null) {
						fileView.clearModel();
						String[] resultArr=result.split(",");
						//iterator
						StringArrayIterator it=new StringArrayIterator(resultArr);
						while(it.hasNext()) {
							String s=(String)it.next();
							String[] fileDetail=s.split("-");
							String str[]= {fileDetail[0],fileDetail[1],fileDetail[2]};
							fileView.addFile(str);
						}
					}else fileView.alert("搜尋不到檔案或資料夾");
					
				}else fileView.alert("搜尋不到檔案或資料夾");
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileController {
	FileFacade fileFacade=new FileFacade();
	View view;

	public void testComposite() {
		String s=fileFacade.testComposite();
		
		String[] resultArr=s.split(",");
		//iterator
		for(int i=0;i<resultArr.length;i++) {
			view.addFile(resultArr[i]);
		}
		
	}
	public void startFileView() {
		if(view==null) {
			view=view.getInstance();
			view.setVisible(true);
			view.addDirectoryListener(new AddDirectoryListener());
			view.addDirListener(new AddDirListener());
			view.addMoveListener(new AddMoveListener());
			view.addMoveBackListener(new AddMoveBackListener());
		}else {
			view.setVisible(true);
		}
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
}

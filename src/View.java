import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class View extends JFrame {
	JButton  addDirectoryBtn = new JButton("addDirectoryBtn");
	JButton  addTextBtn = new JButton("addTextBtn");
	JButton  dirBtn = new JButton("dirBtn");
	JButton  moveBtn = new JButton("moveBtn");
	JButton  moveBackBtn = new JButton("moveBackBtn");
	JButton  removeBtn = new JButton("removeBtn");
	JButton  renameBtn = new JButton("renameBtn");
	JButton  undoBtn = new JButton("undoBtn");
	JButton  redoBtn = new JButton("redoBtn");
	JTextField inputName=new JTextField(5); 
	
	String reName="";
	FileController fileController=new FileController();
	JPanel panel = new JPanel();
	HashMap<String,JLabel> map;
	static volatile View view=null;
	public static View getInstance() {
		if(view==null) {
			synchronized(View.class){
				if(view==null)
					view=new View();
			}
		}
		return view;
	}

	/**
	 * Create the frame.
	 */
	private View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel parent=new JPanel();
		parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));
		
        
        panel.setBounds(61, 11, 81, 140);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JPanel btnPanel = new JPanel();
        
       
        addDirectoryBtn.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				String result="新增資料夾";
				if(!inputName.getText().equals("")) {
					result=inputName.getText();
				}
				addFile(result);
			}
		});  
        
        addTextBtn.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				String result="新增資文件.txt";
				if(!inputName.getText().equals("")) {
					result=inputName.getText()+".txt";
				}
				addFile(result);
			}
		});  
        
        removeBtn.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				String target=inputName.getText();
				JLabel lab=map.get(target);
				panel.remove(lab);
				revalidate();
			}
		});  
        
    
        
        btnPanel.add(addDirectoryBtn);
        btnPanel.add(dirBtn);
        btnPanel.add(addTextBtn);
        btnPanel.add(moveBtn);
        btnPanel.add(moveBackBtn);
        btnPanel.add(removeBtn);
        btnPanel.add(undoBtn);
        btnPanel.add(redoBtn);
        btnPanel.add(renameBtn);
        btnPanel.add(inputName);
        
        
        parent.add(panel);
        parent.add(btnPanel);
        
        add(parent);
        
        map=new HashMap<String,JLabel>();
	}
	public String message() {
		reName=JOptionPane.showInputDialog("請輸入姓名");
		JLabel lab=map.get(getInputNameString());
		lab.setText(reName);
		map.remove(getInputNameString());
		map.put(reName, lab);
		revalidate();
		return reName;
	}
	public void clearView() {
		panel.removeAll();
	}
	public void addFile(String name) {
		JLabel lab=new JLabel(name);
		panel.add(lab);
		map.put(name, lab);
		revalidate();
	}
	public void removeFile(String name) {
		JLabel lab=map.get(name);
		panel.remove(lab);
		revalidate();
	}
	public void renameFile(String name,String s) {
		JLabel lab=map.get(s);
		lab.setText(name);
		map.remove(s);
		map.put(name, lab);
		revalidate();
	}
	public String getInputNameString() {
		return inputName.getText();
	}
	public String getRename() {
		return reName;
	}
	
	public void addDirectoryListener(ActionListener listenForAddDirectoryBtn) {
		addDirectoryBtn.addActionListener(listenForAddDirectoryBtn);
	}
	public void addTextListener(ActionListener listenForTextBtn) {
		addTextBtn.addActionListener(listenForTextBtn);
	}
	public void addDirListener(ActionListener listenForDirBtn) {
		dirBtn.addActionListener(listenForDirBtn);
	}
	public void addMoveListener(ActionListener listenForMoveBtn) {
		moveBtn.addActionListener(listenForMoveBtn);
	}
	public void addMoveBackListener(ActionListener listenForMoveBackBtn) {
		moveBackBtn.addActionListener(listenForMoveBackBtn);
	}
	public void addRemoveListener(ActionListener listenForRemoveBtn) {
		removeBtn.addActionListener(listenForRemoveBtn);
	}
	public void addundoListener(ActionListener listenForundoBtn) {
		undoBtn.addActionListener(listenForundoBtn);
	}
	public void addRedoListener(ActionListener listenFforRedoBtn) {
		redoBtn.addActionListener(listenFforRedoBtn);
	}
	
	public void addRenameListener(ActionListener listenFforRenameBtn) {
		renameBtn.addActionListener(listenFforRenameBtn);
	}

}

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class View extends JFrame {
	JButton  addDirectoryBtn = new JButton("addDirectoryBtn");
	JButton  addTextBtn = new JButton("addTextBtn");
	JButton  dirBtn = new JButton("dirBtn");
	JButton  moveBtn = new JButton("moveBtn");
	JButton  moveBackBtn = new JButton("moveBackBtn");
	JTextField inputName=new JTextField(5); 
	FileController fileController=new FileController();
	JPanel panel = new JPanel();
	
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
				panel.add(new JLabel(result));
				revalidate();
			}
		});  
        
        addTextBtn.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				String result="新增資文件.txt";
				if(!inputName.getText().equals("")) {
					result=inputName.getText()+".txt";
				}
				panel.add(new JLabel(result));
				revalidate();
			}
		});  
        
        
        btnPanel.add(addDirectoryBtn);
        btnPanel.add(dirBtn);
        btnPanel.add(addTextBtn);
        btnPanel.add(moveBtn);
        btnPanel.add(moveBackBtn);
        btnPanel.add(inputName);
        
        
        parent.add(panel);
        parent.add(btnPanel);
        
        add(parent);
	}
	public void clearView() {
		panel.removeAll();
	}
	public void addFile(String name) {
		panel.add(new JLabel(name));
		revalidate();
	}
	public String getInputNameString() {
		return inputName.getText();
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
	

}

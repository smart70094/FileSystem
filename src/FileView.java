import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.Font;


import javax.swing.JTable;
import javax.swing.JTextArea;

public class FileView  extends JFrame{
	private JTextField renameText;
	
	private JButton createFolderBtn;
	private JButton createSystemFloderBtn;
	private JButton moveBtn;
	private JButton moveBackBtn;
	private JButton removeBtn;
	private JButton renameBtn;
	private JButton undoBtn;
	private JButton redoBtn;
	private JButton createTxtBtn;
	private JButton createLogBtn;
	private JButton treeBtn;
	private JButton okBtn;
	
	private JTextArea contextJTextField;
	
	private JTable table;
	DefaultTableModel fileModel;
	static volatile FileView fileView=null;
	public static FileView getInstance() {
		if(fileView==null) {
			synchronized(View.class){
				if(fileView==null)
					fileView=new FileView();
			}
		}
		return fileView;
	}
	
	public FileView() {
		
		setBounds(100, 100, 1034, 717);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		//左上角のFile Manager
		JLabel filemanagerLabel = new JLabel("File Manager");
		filemanagerLabel.setFont(new Font("Arial", Font.BOLD, 36));
		filemanagerLabel.setBounds(6, 0, 225, 61);
		getContentPane().add(filemanagerLabel);
		
		//蓋File  Managerの白色
		ImageIcon whiteImg = new ImageIcon("im/White_color.jpg");
		JLabel whiteLabel = new JLabel("");
		whiteLabel.setIcon(whiteImg);
		whiteLabel.setBackground(Color.WHITE);
		whiteLabel.setBounds(-13, 0, 244, 60);
		getContentPane().add(whiteLabel);
		
		//登出按鈕
		JButton logoutBtn = new JButton("登出");
		logoutBtn.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		logoutBtn.setForeground(new Color(255, 255, 255));
		logoutBtn.setBounds(935, 0, 87, 61);
		
		//去文字周邊的框框
		logoutBtn.setFocusPainted(false);
		logoutBtn.setBackground(new Color(11,128,241));
	    logoutBtn.setOpaque(true);
		getContentPane().add(logoutBtn);
		
		//左邊白色箭頭
		ImageIcon leftImg = new ImageIcon("im/smallleft.png");
		moveBackBtn = new JButton("");
		moveBackBtn.setBackground(new Color(11,128,241));
		moveBackBtn.setFocusPainted(false);
		moveBackBtn.setIcon(leftImg);
		moveBackBtn.setBounds(230, 0, 44, 62);
		getContentPane().add(moveBackBtn);
		
		//右邊白色箭頭
		ImageIcon rightImg = new ImageIcon("im/smallright.png");
		moveBtn = new JButton("");
		moveBtn.setFocusPainted(false);
		moveBtn.setBackground(new Color(11, 128, 241));
		moveBtn.setIcon(rightImg);
		moveBtn.setBounds(369, 0, 44, 62);
		getContentPane().add(moveBtn);
		
		//Tree Diagram按鈕
		ImageIcon treeImg = new ImageIcon("im/tree.png");
		treeBtn = new JButton("New button");
		treeBtn.setIcon(treeImg);
		treeBtn.setBounds(604, 148, 186, 36); 
		getContentPane().add(treeBtn);
		
		//createTxt按鈕
		ImageIcon createTxtImg = new ImageIcon("im/createTxT.png");
		createTxtBtn = new JButton("New button");
		createTxtBtn.setIcon(createTxtImg);
		//沒外框
		//createTxtBtn.setBorderPainted(false); 
		createTxtBtn.setBounds(24, 95, 135, 36);
		getContentPane().add(createTxtBtn);
		
		//createLog按鈕
		ImageIcon createLogImg = new ImageIcon("im/createdoc.png");
		createLogBtn = new JButton("New button");
		createLogBtn.setForeground(Color.WHITE);
		createLogBtn.setIcon(createLogImg);
		createLogBtn.setBounds(208, 95, 150, 36);
		getContentPane().add(createLogBtn);
		
		//createFolder按鈕
		ImageIcon createFolderImg = new ImageIcon("im/createfolder.png");
		createFolderBtn = new JButton("New button");
		createFolderBtn.setIcon(createFolderImg);
		createFolderBtn.setBounds(394, 95, 170, 36);
		
		getContentPane().add(createFolderBtn);
		
		//createSystemFolder按鈕
		ImageIcon createSystemFloderImg = new ImageIcon("im/create system floder.png");
		createSystemFloderBtn = new JButton("New button");
		createSystemFloderBtn.setIcon(createSystemFloderImg);
		createSystemFloderBtn.setBounds(604, 95, 268, 36);
		getContentPane().add(createSystemFloderBtn);
		
		//remove按鈕
		ImageIcon removeImg = new ImageIcon("im/remove.png");
		removeBtn = new JButton("New button");
		removeBtn.setIcon(removeImg);
		removeBtn.setBounds(900, 95, 112, 36);
		getContentPane().add(removeBtn);
		
		//redo按鈕
		ImageIcon redoImg = new ImageIcon("im/redo.png");
		redoBtn = new JButton("New button");
		redoBtn.setIcon(redoImg);
		redoBtn.setBounds(833, 148, 81, 36);
		getContentPane().add(redoBtn);
		
		//undo按鈕
		ImageIcon undoImg = new ImageIcon("im/undo.png");
		undoBtn = new JButton("New button");
		undoBtn.setIcon(undoImg);
		undoBtn.setBounds(932, 148, 80, 36);
		getContentPane().add(undoBtn);
		
		//rename輸入の地方
		renameText = new JTextField();
		renameText.setBounds(24, 149, 250, 36);
		getContentPane().add(renameText);
		renameText.setColumns(10);
		
		//rename按鈕
		ImageIcon renameImg = new ImageIcon("im/rename.png");
		renameBtn = new JButton("New button");
		renameBtn.setIcon(renameImg);
		renameBtn.setBounds(452, 148, 112, 36);
		getContentPane().add(renameBtn);
		
		//search按鈕
		ImageIcon searchImg = new ImageIcon("im/search.png");
		JButton searchBtn = new JButton("New button");
		searchBtn.setIcon(searchImg);
		searchBtn.setBounds(318, 148, 93, 36);
		getContentPane().add(searchBtn);
		
	
		
		//長の灰色那條
		JLabel garyLabel = new JLabel("");
		garyLabel.setBackground(new Color(55,59,70));
		garyLabel.setOpaque(true);
		garyLabel.setBounds(-3, 62, 1047, 157);
		getContentPane().add(garyLabel);
		
		//Layer上の藍色
		JLabel blueLabel = new JLabel("           layer");
		blueLabel.setForeground(Color.WHITE);
		blueLabel.setFont(blueLabel.getFont().deriveFont(blueLabel.getFont().getStyle() | Font.BOLD, blueLabel.getFont().getSize() + 10f));
		blueLabel.setBounds(232, 0, 689, 66);
		blueLabel.setBackground(new Color(11,128,241));
		blueLabel.setOpaque(true);
		getContentPane().add(blueLabel);
		
		//放值の地方
		String[] columnNames = {"姓名", "性別", "嗜好"};
		fileModel = new DefaultTableModel(){
	        @Override
	        public boolean isCellEditable(int rowIndex, int columnIndex){
	            return false;
	        }
	    };
	    fileModel.setColumnIdentifiers(columnNames);
		table = new JTable();
		table.setModel(fileModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int vColIndex =0;
		TableColumn col = table.getColumnModel().getColumn(vColIndex);
		int height = 500;
		col.setPreferredWidth(height);
		table.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		table.setBounds(0, 270, 1022, 365);
	    table.setRowHeight(30);

		
		//放名稱 類型 大小の藍色長條
		JLabel smallBlueLabel = new JLabel("       名稱                                                                                                             類型                                大小");
		smallBlueLabel.setForeground(new Color(255, 255, 255));
		smallBlueLabel.setFont(new Font("微軟正黑體", Font.BOLD, 21));
		smallBlueLabel.setBounds(0, 229, 1028, 41);
		smallBlueLabel.setBackground(new Color(11,128,241));
		smallBlueLabel.setOpaque(true);
		getContentPane().add(smallBlueLabel);
		
		//最下面灰色の長條
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(55,59,70));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(-13, 634, 1035, 47);
		getContentPane().add(lblNewLabel);
		getContentPane().add(table);
		
	}
	
	public String getAlertInput(String question) {
		String result=JOptionPane.showInputDialog(question);
		return result;
	}
	
	public void startContext() {
		JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 350);
        frame.setLocationRelativeTo( null );
        frame.setUndecorated(true);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel fileNameLab = new JLabel("File");
        fileNameLab.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add( fileNameLab);
        
        panel.add( contextJTextField );
        
        JPanel btnPanel=new JPanel();
        
        JButton cannelBtn=new JButton("cannel");
        okBtn.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				frame.dispose();
			}
		});
        cannelBtn.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				frame.dispose();
			}
		});
        btnPanel.add(okBtn);
        btnPanel.add(cannelBtn);
         
        panel.add(btnPanel);
        
        frame.setContentPane(panel);
        frame.setVisible(true);
        

      
	}
	
	
	public void alert(String context) {
		  JOptionPane.showMessageDialog(this,context,
                  "提示訊息", JOptionPane.INFORMATION_MESSAGE);
	}
	public void addFile(String[] str) {
		if(str[1].equals("Text")) str[0]=str[0]+".txt";
		else if(str[1].equals("Log")) str[0]=str[0]+".log";
		fileModel.addRow(str);
	}
	public void removeFile() {
		int index=table.getSelectedRow();
		if(index!=-1)
			fileModel.removeRow(index);
		else
			alert("請選擇要刪除的檔案");
	}
	public void removeFile(String name) {
		int size=fileModel.getRowCount();
		String str;
		for(int i=0;i<size;i++) {
			str=clearType(table.getModel().getValueAt(i, 0).toString());

			
			if(str.equals(name)) {
				fileModel.removeRow(i);
				break;
			}
		}
	}
	public void renameFile(String name,String rename,String type) {
		rename=addType(rename,type);
		int size=fileModel.getRowCount();
		String str;
		for(int i=0;i<size;i++) {
			str=clearType(table.getModel().getValueAt(i, 0).toString());
			if(str.equals(name)) {
				table.getModel().setValueAt(rename, i,0);
				break;
			}
		}	int index=table.getSelectedRow();	
		
	}
	public void updateSize(String name,int filesize) {
		
		int size=fileModel.getRowCount();
		String str;
		for(int i=0;i<size;i++) {
			str=clearType(table.getModel().getValueAt(i, 0).toString());
			if(str.equals(name)) {
				table.getModel().setValueAt(filesize, i,2);
				break;
			}
		}	
		
	}
	public String getSelectedFile() {
		String result="";
		int row = table.getSelectedRow();
		if(row!=-1) {
			result = table.getModel().getValueAt(row, 0).toString();
			//去副檔名
			result=clearType(result);
		}
		return result;
	
	}
	public String getContext() {
		return contextJTextField.getText();
	}
	public void setContext(String context) {
		contextJTextField.setText(context);
	}
	public void clearModel() {
		fileModel.setRowCount(0);
	}
	public String clearType(String result) {
		if(result.indexOf(".") >-1) result=result.substring(0, result.indexOf("."));
		return result;
	}
	public String addType(String name,String type) {
		if(type.equals("Text")) name=name+".txt";
		else if(type.equals("Log")) name=name+".log";
		return name;
	}
	
	public void addCreateTxtListener(ActionListener listenForCreateTxtBtn) {
		createTxtBtn.addActionListener(listenForCreateTxtBtn);
	}
	public void addCreateLogListener(ActionListener listenForCreateLogBtn) {
		createLogBtn.addActionListener(listenForCreateLogBtn);
	}
	public void addCreateFolderListener(ActionListener listenForCreateFolderBtn) {
		createFolderBtn.addActionListener(listenForCreateFolderBtn);
	}
	
	public void addStartContextListener(ActionListener listenForStartContextBtn) {
		treeBtn.addActionListener(listenForStartContextBtn);
	}
	public void addContextListener(ActionListener listenForContextBtn) {
		okBtn=new JButton("ok");
		contextJTextField = new JTextArea();
		okBtn.addActionListener(listenForContextBtn);
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
	public void addRenameListener(ActionListener listenFforRenameBtn) {
		renameBtn.addActionListener(listenFforRenameBtn);
	}
	public void addUndoListener(ActionListener listenForundoBtn) {
		undoBtn.addActionListener(listenForundoBtn);
	}
	public void addRedoListener(ActionListener listenFforRedoBtn) {
		redoBtn.addActionListener(listenFforRedoBtn);
	}
}

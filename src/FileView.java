import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.Font;
import javax.swing.JTable;

public class FileView  extends JFrame{
	private JTextField renameText;
	private JTable table;
	DefaultTableModel fileDTO;
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
		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(new Color(11,128,241));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setIcon(leftImg);
		btnNewButton.setBounds(230, 0, 44, 62);
		getContentPane().add(btnNewButton);
		
		//右邊白色箭頭
		ImageIcon rightImg = new ImageIcon("im/smallright.png");
		JButton button = new JButton("");
		button.setFocusPainted(false);
		button.setBackground(new Color(11, 128, 241));
		button.setIcon(rightImg);
		button.setBounds(369, 0, 44, 62);
		getContentPane().add(button);
		
		//Tree Diagram按鈕
		ImageIcon treeImg = new ImageIcon("im/tree.png");
		JButton treeBtn = new JButton("New button");
		treeBtn.setIcon(treeImg);
		treeBtn.setBounds(604, 148, 186, 36);
		treeBtn.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				int column = 0;
				int row = table.getSelectedRow();
				String value = table.getModel().getValueAt(row, column).toString();
				System.out.println(value);
			}
		});  
		getContentPane().add(treeBtn);
		
		//createTxt按鈕
		ImageIcon createTxtImg = new ImageIcon("im/createTxT.png");
		JButton createTxtBtn = new JButton("New button");
		createTxtBtn.setIcon(createTxtImg);
		//沒外框
		//createTxtBtn.setBorderPainted(false); 
		createTxtBtn.setBounds(24, 95, 135, 36);
		getContentPane().add(createTxtBtn);
		
		//createDoc按鈕
		ImageIcon createDocImg = new ImageIcon("im/createdoc.png");
		JButton createDocBtn = new JButton("New button");
		createDocBtn.setForeground(Color.WHITE);
		createDocBtn.setIcon(createDocImg);
		createDocBtn.setBounds(208, 95, 150, 36);
		getContentPane().add(createDocBtn);
		
		//createFolder按鈕
		ImageIcon createFolderImg = new ImageIcon("im/createfolder.png");
		JButton createFolderBtn = new JButton("New button");
		createFolderBtn.setIcon(createFolderImg);
		createFolderBtn.setBounds(394, 95, 170, 36);
		getContentPane().add(createFolderBtn);
		
		//createSystemFolder按鈕
		ImageIcon createSystemFloderImg = new ImageIcon("im/create system floder.png");
		JButton createSystemFloderBtn = new JButton("New button");
		createSystemFloderBtn.setIcon(createSystemFloderImg);
		createSystemFloderBtn.setBounds(604, 95, 268, 36);
		getContentPane().add(createSystemFloderBtn);
		
		//remove按鈕
		ImageIcon removeImg = new ImageIcon("im/remove.png");
		JButton removeBtn = new JButton("New button");
		removeBtn.setIcon(removeImg);
		removeBtn.setBounds(900, 95, 112, 36);
		getContentPane().add(removeBtn);
		
		//redo按鈕
		ImageIcon redoImg = new ImageIcon("im/redo.png");
		JButton redoBtn = new JButton("New button");
		redoBtn.setIcon(redoImg);
		redoBtn.setBounds(833, 148, 81, 36);
		getContentPane().add(redoBtn);
		
		//undo按鈕
		ImageIcon undoImg = new ImageIcon("im/undo.png");
		JButton undoBtn = new JButton("New button");
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
		JButton renameBtn = new JButton("New button");
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
		fileDTO = new DefaultTableModel(){
	        @Override
	        public boolean isCellEditable(int rowIndex, int columnIndex){
	            return false;
	        }
	    };
	    fileDTO.setColumnIdentifiers(columnNames);
		
		
		fileDTO.addRow(new Object[]{"狡兔三窟1","1","1000000"});
		/*for(int i=0;i<3;i++) {
			fileDTO.addRow(new Object[]{"狡兔三窟1","1","1000000"});
		}*/
		table = new JTable();
		table.setModel(fileDTO);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int vColIndex =0;
		TableColumn col = table.getColumnModel().getColumn(vColIndex);
		int height = 500;
		col.setPreferredWidth(height);
		table.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		table.setBounds(0, 270, 1022, 365);
	    table.setRowHeight(30);
	   getContentPane().add(table);

		
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
}

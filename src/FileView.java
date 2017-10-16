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
import java.awt.Font;
import javax.swing.JTable;

public class FileView  extends JFrame{

	private JFrame frame;
	private JTextField searchText;
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
		frame = new JFrame();
		frame.setBounds(100, 100, 1034, 717);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//左上角のFile Manager
		JLabel filemanagerLabel = new JLabel("File Manager");
		filemanagerLabel.setFont(new Font("Arial", Font.BOLD, 36));
		filemanagerLabel.setBounds(6, 0, 225, 61);
		frame.getContentPane().add(filemanagerLabel);
		
		//蓋File  Managerの白色
		Image whiteImg = new ImageIcon(this.getClass().getResource("/im/White_color.jpg")).getImage();
		JLabel whiteLabel = new JLabel("");
		whiteLabel.setIcon(new ImageIcon(whiteImg));
		whiteLabel.setBackground(Color.WHITE);
		whiteLabel.setBounds(-13, 0, 244, 60);
		frame.getContentPane().add(whiteLabel);
		
		//登出按鈕
		JButton logoutBtn = new JButton("登出");
		logoutBtn.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		logoutBtn.setForeground(new Color(255, 255, 255));
		logoutBtn.setBounds(935, 0, 87, 61);
		//去文字周邊的框框
		logoutBtn.setFocusPainted(false);
		logoutBtn.setBackground(new Color(11,128,241));
	    logoutBtn.setOpaque(true);
		frame.getContentPane().add(logoutBtn);
		
		//左上白色箭頭
		Image leftImg = new ImageIcon(this.getClass().getResource("/lefttes.png")).getImage();
		
		//Tree Diagram按鈕
		Image treeImg = new ImageIcon(this.getClass().getResource("/tree.png")).getImage();
		JButton treeBtn = new JButton("New button");
		treeBtn.setIcon(new ImageIcon(treeImg));
		treeBtn.setBounds(719, 148, 186, 36);
		treeBtn.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				int column = 0;
				int row = table.getSelectedRow();
				String value = table.getModel().getValueAt(row, column).toString();
				System.out.println(value);
			}
		});  
		frame.getContentPane().add(treeBtn);
		
		//createTxt按鈕
		Image createTxtImg = new ImageIcon(this.getClass().getResource("/createTxT.png")).getImage();
		JButton createTxtBtn = new JButton("New button");
		createTxtBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.printf("GGG");
			}
		});
		createTxtBtn.setIcon(new ImageIcon(createTxtImg));
		//沒外框
		//createTxtBtn.setBorderPainted(false); 
		createTxtBtn.setBounds(14, 95, 135, 36);
		frame.getContentPane().add(createTxtBtn);
		
		//createDoc按鈕
		Image createDocImg = new ImageIcon(this.getClass().getResource("/createdoc.png")).getImage();
		JButton createDocBtn = new JButton("New button");
		createDocBtn.setForeground(Color.WHITE);
		createDocBtn.setIcon(new ImageIcon(createDocImg));
		createDocBtn.setBounds(163, 95, 150, 36);
		frame.getContentPane().add(createDocBtn);
		
		//createFolder按鈕
		Image createFolderImg = new ImageIcon(this.getClass().getResource("/createfolder.png")).getImage();
		JButton createFolderBtn = new JButton("New button");
		createFolderBtn.setIcon(new ImageIcon(createFolderImg));
		createFolderBtn.setBounds(327, 95, 170, 36);
		frame.getContentPane().add(createFolderBtn);
		
		//createSystemFolder按鈕
		Image createSystemFloderImg = new ImageIcon(this.getClass().getResource("/create system floder.png")).getImage();
		JButton createSystemFloderBtn = new JButton("New button");
		createSystemFloderBtn.setIcon(new ImageIcon(createSystemFloderImg));
		createSystemFloderBtn.setBounds(511, 95, 268, 36);
		frame.getContentPane().add(createSystemFloderBtn);
		
		//remove按鈕
		Image removeImg = new ImageIcon(this.getClass().getResource("/remove.png")).getImage();
		JButton removeBtn = new JButton("New button");
		removeBtn.setIcon(new ImageIcon(removeImg));
		removeBtn.setBounds(793, 95, 112, 36);
		frame.getContentPane().add(removeBtn);
		
		//redo按鈕
		Image redoImg = new ImageIcon(this.getClass().getResource("/redo.png")).getImage();
		JButton redoBtn = new JButton("New button");
		redoBtn.setIcon(new ImageIcon(redoImg));
		redoBtn.setBounds(922, 95, 81, 36);
		frame.getContentPane().add(redoBtn);
		
		//undo按鈕
		Image undoImg = new ImageIcon(this.getClass().getResource("/undo.png")).getImage();
		JButton undoBtn = new JButton("New button");
		undoBtn.setIcon(new ImageIcon(undoImg));
		undoBtn.setBounds(923, 148, 80, 36);
		frame.getContentPane().add(undoBtn);
		
		//rename輸入の地方
		renameText = new JTextField();
		renameText.setBounds(14, 149, 231, 36);
		frame.getContentPane().add(renameText);
		renameText.setColumns(10);
		
		//rename按鈕
		Image renameImg = new ImageIcon(this.getClass().getResource("/rename.png")).getImage();
		JButton renameBtn = new JButton("New button");
		renameBtn.setIcon(new ImageIcon(renameImg));
		renameBtn.setBounds(259, 148, 113, 36);
		frame.getContentPane().add(renameBtn);
		
		//search輸入の地方
		searchText = new JTextField();
		searchText.setBounds(386, 149, 212, 36);
		frame.getContentPane().add(searchText);
		searchText.setColumns(10);
		
		//search按鈕
		Image searchImg = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton searchBtn = new JButton("New button");
		searchBtn.setIcon(new ImageIcon(searchImg));
		searchBtn.setBounds(612, 148, 93, 36);
		frame.getContentPane().add(searchBtn);
		
	
		
		//長の灰色那條
		JLabel garyLabel = new JLabel("");
		garyLabel.setBackground(new Color(55,59,70));
		garyLabel.setOpaque(true);
		garyLabel.setBounds(-3, 62, 1047, 157);
		frame.getContentPane().add(garyLabel);
		
	
		
		
		//Layer上の藍色
		JLabel blueLabel = new JLabel("      layer");
		blueLabel.setForeground(Color.WHITE);
		blueLabel.setFont(blueLabel.getFont().deriveFont(blueLabel.getFont().getStyle() | Font.BOLD, blueLabel.getFont().getSize() + 10f));
		blueLabel.setBounds(232, 0, 689, 66);
		blueLabel.setBackground(new Color(11,128,241));
		blueLabel.setOpaque(true);
		frame.getContentPane().add(blueLabel);
		
		//放值の地方
		String[] columnNames = {"姓名", "性別", "嗜好"};
		fileDTO = new DefaultTableModel(){
	        @Override
	        public boolean isCellEditable(int rowIndex, int columnIndex){
	            return false;
	        }
	    };
	    fileDTO.setColumnIdentifiers(columnNames);
		table = new JTable();
		table.setModel(fileDTO);
		//fileDTO.addRow(new Object[]{"狡兔三窟1","1","1000000"});

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int vColIndex =0;
		int height = 500;
		table.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		table.setBounds(0, 279, 1022, 353);
	    table.setRowHeight(30);
	    frame.add(new JScrollPane(table));
	    frame.getContentPane().add(table);
		
		//放名稱 類型 大小の藍色長條
		JLabel smallBlueLabel = new JLabel("       名稱                                                                                                             類型                                大小");
		smallBlueLabel.setForeground(new Color(255, 255, 255));
		smallBlueLabel.setFont(new Font("微軟正黑體", Font.BOLD, 21));
		smallBlueLabel.setBounds(0, 232, 1016, 34);
		smallBlueLabel.setBackground(new Color(11,128,241));
		smallBlueLabel.setOpaque(true);
		frame.getContentPane().add(smallBlueLabel);
		
		//最下面灰色の長條
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(55,59,70));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(-13, 634, 1035, 47);
		frame.getContentPane().add(lblNewLabel);
	}

}

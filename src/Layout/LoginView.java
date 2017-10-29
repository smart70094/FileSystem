package Layout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginView extends JFrame {
	private JTextField accontTextField;
	private JTextField passwordTextField;
	private JLabel managerLabel;
	private JLabel accountLabel;
	private JLabel passwordLabel;
	private JButton clickButton;
	private JLabel grayLabel;
	
	static volatile LoginView loginView=null;
	public static LoginView getInstance() {
		if(loginView==null) {
			synchronized(LoginView.class){
				if(loginView==null)
					loginView=new LoginView();
			}
		}
		return loginView;
	}
	
	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1034, 717);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		
		managerLabel = new JLabel("File Manager");
		managerLabel.setFont(new Font("Arial", Font.BOLD, 60));
		managerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		managerLabel.setForeground(new Color(255, 255, 255));
		managerLabel.setBackground(new Color(55,59,70));
		managerLabel.setBounds(0, 0, 1016, 169);
		managerLabel.setOpaque(true);
		getContentPane().add(managerLabel);
		
		accountLabel = new JLabel("\u5E33\u865F");
		accountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accountLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
		accountLabel.setBounds(334, 262, 73, 44);
		getContentPane().add(accountLabel);
		
		passwordLabel = new JLabel("\u5BC6\u78BC");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
		passwordLabel.setBounds(334, 362, 73, 69);
		getContentPane().add(passwordLabel);
		
		accontTextField = new JTextField();
		accontTextField.setText("root");
		accontTextField.setBounds(421, 267, 222, 44);
		accontTextField.setFont(new Font("Arial", Font.PLAIN, 24));
		accontTextField.setColumns(10);
		
		getContentPane().add(accontTextField);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setToolTipText("");
		passwordTextField.setFont(new Font("Arial", Font.PLAIN, 24));
		passwordTextField.setBounds(421, 379, 223, 44);
		passwordTextField.setColumns(10);
		passwordTextField.setText("root");
		getContentPane().add(passwordTextField);
		
		
		clickButton = new JButton("\u78BA\u8A8D");
		clickButton.setBackground(Color.WHITE);
		clickButton.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
		clickButton.setBounds(421, 463, 134, 69);
		clickButton.setFocusPainted(false);
		getContentPane().add(clickButton);
		
		grayLabel = new JLabel("New label");
		grayLabel.setBounds(0, 613, 1016, 57);
		getContentPane().add(grayLabel);
		grayLabel.setBackground(new Color(55,59,70));
		grayLabel.setOpaque(true);
				
	}
	public void addClickBtnActionListener(ActionListener listenForClickBtn){
		clickButton.addActionListener(listenForClickBtn);
	}
	
	public String getAccount() {
		return accontTextField.getText().trim();
	}
	
	public String getPassword() {
		return passwordTextField.getText().trim();
	}
	
	public void alert(String context) {
		  JOptionPane.showMessageDialog(this,context,
                "提示訊息", JOptionPane.INFORMATION_MESSAGE);
	}





	
}

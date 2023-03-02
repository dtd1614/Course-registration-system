package view.dialog;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controller.CAccount;
import valueObject.VAccount;
import view.frame.PMainFrame;

public class PLoginDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JTextField tfId;
	private JPasswordField tfPassword;	
	private CAccount cAccount;
	
	public PLoginDialog() {
		
		this.setTitle("명지대학교 로그인");
		this.setSize(400,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setIconImage(new ImageIcon("image/mju_icon.gif").getImage());

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		LayoutManager layoutManager = new GridLayout(0,1,10,10);
		panel.setLayout(layoutManager);
		
		JLabel lbId = new JLabel("아이디");
		panel.add(lbId);
			
		this.tfId = new JTextField();
		panel.add(this.tfId);
				
		JLabel lbPassword = new JLabel("비밀번호");
		panel.add(lbPassword);
		
		this.tfPassword = new JPasswordField();
		panel.add(this.tfPassword);				
		
		JLabel alert = new JLabel("아이디와 비밀번호를 입력하세요.");
		panel.add(alert);
				
		JButton btLogin = new JButton("로그인");
		this.getRootPane().setDefaultButton(btLogin);
		panel.add(btLogin);
		
		JButton btCreateAccount = new JButton("회원가입");
		panel.add(btCreateAccount);
		
		JButton btIdChatgi = new JButton("아이디 찾기");
		panel.add(btIdChatgi);
		
		JButton btPasswordChatgi = new JButton("비밀번호 찾기");
		panel.add(btPasswordChatgi);
		
		ActionHandler actionHandler = new ActionHandler();
		
		btLogin.addActionListener(actionHandler);
		btCreateAccount.addActionListener(actionHandler);
		btIdChatgi.addActionListener(actionHandler);
		btPasswordChatgi.addActionListener(actionHandler);
		
		this.add(panel);
		
		this.cAccount = new CAccount();
		
		this.setVisible(true);
		
	}
	
	public void login() {
		String id = this.tfId.getText();
		char[] charPassword = this.tfPassword.getPassword();
		String password = "";
		for(int i = 0; i<charPassword.length; i++) {
			password += charPassword[i];
		}		
		VAccount vAccount = this.cAccount.getLogin(id, password);
		if(vAccount != null) {
			new PMainFrame(this.cAccount, vAccount);
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(null,"아이디 혹은 비밀번호가 일치하지 않습니다.");				
		}	
	}

	public void createAccount() {
		new PCreateAccountDialog(this.cAccount);
	}

	public void findId() {
		new PFindIdDialog(this.cAccount);
	}

	public void findPassword() {
		new PFindPasswordDialog(this.cAccount);
	}
	
	public class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand()=="로그인") {
				login();
			}else if(e.getActionCommand()=="회원가입") {
				createAccount();
			}else if(e.getActionCommand()=="아이디 찾기") {
				findId();
			}else if(e.getActionCommand()=="비밀번호 찾기") {
				findPassword();
			}		
		}		
	}
}

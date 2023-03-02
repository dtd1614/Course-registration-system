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

public class PCreateAccountDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private JTextField tfId;
	private JPasswordField tfPassword;	
	private JTextField tfName;	
	private JTextField tfEmail;	
	private JTextField tfDepartment;	

	private CAccount cAccount;
	
	public PCreateAccountDialog(CAccount cAccount) {
		
		this.setTitle("회원가입");
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
		
		JLabel lbName = new JLabel("이름");
		panel.add(lbName);
		
		this.tfName = new JTextField();
		panel.add(this.tfName);	
		
		JLabel lbEmail = new JLabel("이메일");
		panel.add(lbEmail);
		
		this.tfEmail = new JTextField();
		panel.add(this.tfEmail);	
		
		JLabel lbDepartment = new JLabel("학과");
		panel.add(lbDepartment);
		
		this.tfDepartment = new JTextField();
		panel.add(this.tfDepartment);	
		
		JButton btCreateAccount = new JButton("회원가입");
		this.getRootPane().setDefaultButton(btCreateAccount);
		panel.add(btCreateAccount);
		
		ActionHandler actionHandler = new ActionHandler();
		btCreateAccount.addActionListener(actionHandler);
		
		this.add(panel);
		
		this.cAccount = cAccount;
		
		this.setVisible(true);
	}
	
	public void createAccount() {
		char[] charPassword = tfPassword.getPassword();
		String password = "";
		for(int i = 0; i<charPassword.length; i++) {
			password += charPassword[i];
		}			
		String id = tfId.getText().replaceAll(" ", "");
		password.replaceAll(" ", "");
		String name = tfName.getText().replaceAll(" ", "");
		String email = tfEmail.getText().replaceAll(" ", "");
		String department = tfDepartment.getText().replaceAll(" ", "");
		if(id.isEmpty()||password.isEmpty()||name.isEmpty()||email.isEmpty()||department.isEmpty()) {
			JOptionPane.showMessageDialog(null,"내용을 모두 작성해주세요.");				
		}
		else if(cAccount.getAccount(id) != null) {
			JOptionPane.showMessageDialog(null,"해당 아이디가 이미 존재합니다.");								
		}else if(cAccount.isDuplicatedEmail(email) == true) {
			JOptionPane.showMessageDialog(null,"해당 이메일이 이미 존재합니다.");								
		}else{
			VAccount vAccount = new VAccount();		
			vAccount.setId(id);
			vAccount.setPassword(password);
			vAccount.setName(name);
			vAccount.setEmail(email);
			vAccount.setDepartment(department);		
			cAccount.createAccount(vAccount);
			dispose();
		} 
	}
	
	public class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			createAccount();
		}		
	}
}
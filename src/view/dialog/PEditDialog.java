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

public class PEditDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private JPasswordField tfPassword;	
	private JTextField tfName;	
	private JTextField tfEmail;	
	private JTextField tfDepartment;
	private PMainFrame pMainFrame;

	private VAccount vAccount;
	private CAccount cAccount;
	
	public PEditDialog(PMainFrame pMainFrame, VAccount vAccount, CAccount cAccount) {
		
		this.setTitle("회원정보 수정");
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setIconImage(new ImageIcon("image/mju_icon.gif").getImage());
		
		this.pMainFrame = pMainFrame;

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		LayoutManager layoutManager = new GridLayout(0,1,10,10);
		panel.setLayout(layoutManager);
				
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
		
		JButton btEdit = new JButton("회원정보 수정");
		this.getRootPane().setDefaultButton(btEdit);
		panel.add(btEdit);
		
		ActionHandler actionHandler = new ActionHandler();
		btEdit.addActionListener(actionHandler);
		
		this.add(panel);
		
		this.vAccount = vAccount;
		this.cAccount = cAccount;
		
		this.setVisible(true);
	}
	
	public void edit() {
		char[] charPassword = tfPassword.getPassword();
		String password = "";
		for(int i = 0; i<charPassword.length; i++) {
			password += charPassword[i];
		}			
		password.replaceAll(" ", "");
		String name = tfName.getText().replaceAll(" ", "");
		String email = tfEmail.getText().replaceAll(" ", "");
		String department = tfDepartment.getText().replaceAll(" ", "");
		if(password.isEmpty()||name.isEmpty()||email.isEmpty()||department.isEmpty()) {
			JOptionPane.showMessageDialog(null,"내용을 모두 작성해주세요.");				
		}else if(cAccount.isDuplicatedEmail(this.vAccount.getId(), email) == true) {
			JOptionPane.showMessageDialog(null,"해당 이메일이 이미 존재합니다.");								
		}else{
			this.vAccount.setPassword(password);
			this.vAccount.setName(name);
			this.vAccount.setEmail(email);
			this.vAccount.setDepartment(department);
			cAccount.editAccount(this.vAccount);
			this.pMainFrame.dispose();
			new PLoginDialog();
			dispose();
		} 
	}
	
	public class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			edit();
		}		
	}
}

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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controller.CAccount;

public class PFindIdDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private JTextField tfName;	
	private JTextField tfEmail;	
	private JTextField tfDepartment;	

	private CAccount cAccount;
	
	public PFindIdDialog(CAccount cAccount) {
		
		this.setTitle("아이디 찾기");
		this.setSize(400,350);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setIconImage(new ImageIcon("image/mju_icon.gif").getImage());

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		LayoutManager layoutManager = new GridLayout(0,1,10,10);
		panel.setLayout(layoutManager);
		
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
		
		JButton btCreateAccount = new JButton("아이디 찾기");
		this.getRootPane().setDefaultButton(btCreateAccount);
		panel.add(btCreateAccount);
		
		ActionHandler actionHandler = new ActionHandler();
		btCreateAccount.addActionListener(actionHandler);
		
		this.add(panel);
		
		this.cAccount = cAccount;
		
		this.setVisible(true);
	}
	
	public void findId() {
		String name = tfName.getText().replaceAll(" ", "");
		String email = tfEmail.getText().replaceAll(" ", "");
		String department = tfDepartment.getText().replaceAll(" ", "");
		String id = cAccount.findId(name,email,department);
		if(name.isEmpty()||email.isEmpty()||department.isEmpty()) {
			JOptionPane.showMessageDialog(null,"내용을 모두 작성해주세요.");				
		}else if(id == null) {
			JOptionPane.showMessageDialog(null,"일치하는 계정이 없습니다.");								
		}else if(id != null){
			JOptionPane.showMessageDialog(null,"아이디는 " + id + " 입니다.");											
			dispose();
		} 
	}
	
	public class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			findId();
		}		
	}
}
package view.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.CAccount;
import valueObject.VAccount;
import view.dialog.PEditDialog;
import view.dialog.PLoginDialog;
import view.frame.PMainFrame;

public class PAccountPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private VAccount vAccount;
	private CAccount cAccount;
	private PMainFrame pMainFrame;

	public PAccountPanel(PMainFrame pMainFrame, VAccount vAccount, CAccount cAccount) {
		this.cAccount = cAccount;
		this.vAccount = vAccount;
		this.pMainFrame = pMainFrame;
		
		JButton btnLogout = new JButton("로그아웃");			
		this.add(btnLogout);
		
		JButton btnEdit = new JButton("회원정보수정");
		this.add(btnEdit);
		
		JButton btnDelete = new JButton("회원탈퇴");
		this.add(btnDelete);
		
		ActionHandler actionHandler = new ActionHandler();

		btnLogout.addActionListener(actionHandler);
		btnEdit.addActionListener(actionHandler);
		btnDelete.addActionListener(actionHandler);
	}
	
	public void logout() {
		this.pMainFrame.dispose();
		new PLoginDialog();
	}
	public void deleteAccount() {
		this.cAccount.deleteAccount(this.vAccount.getId());
		this.pMainFrame.dispose();
		new PLoginDialog();
	}
	public void editAccount() {
		new PEditDialog(this.pMainFrame, this.vAccount, this.cAccount);
	}
	
	public class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("로그아웃")) {
				logout();
			}else if(e.getActionCommand().equals("회원정보수정")) {
				editAccount();
			}else if(e.getActionCommand().equals("회원탈퇴")) {
				deleteAccount();
			}
		}		
	}
}

package view.frame;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import global.Constants;
import controller.CAccount;
import valueObject.VAccount;
import view.panel.PAccountPanel;
import view.panel.PSugnasincheongPanel;
import view.panel.PWelcomePanel;

public class PMainFrame extends JFrame {
	protected static final long serialVersionUID = 1L;

	private PWelcomePanel welcomePanel;
	private PSugnasincheongPanel sugnasincheongPanel;	
	private PAccountPanel accountPanel;	
	private VAccount vAccount;	
	private CAccount cAccount;
		
	public PMainFrame(CAccount cAccount, VAccount vAccount) {
		// attributes
		this.setTitle("명지대학교 수강신청 시스템");
		this.setSize(Constants.CMainFrame.WIDTH, Constants.CMainFrame.HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(new ImageIcon("image/mju_icon.gif").getImage());
		this.vAccount = vAccount;
		this.cAccount = cAccount;
		
		// components	
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		LayoutManager layoutManager = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layoutManager);
		
		this.welcomePanel = new PWelcomePanel(this.vAccount);
		panel.add(this.welcomePanel);
		
		this.sugnasincheongPanel = new PSugnasincheongPanel(this.vAccount);
		panel.add(this.sugnasincheongPanel);	
		
		this.accountPanel = new PAccountPanel(this, this.vAccount, this.cAccount);
		panel.add(this.accountPanel);
				
		this.add(panel);
		
		this.setVisible(true);
	}
}

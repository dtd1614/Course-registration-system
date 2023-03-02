package view.panel;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.panel.PSugnasincheongPanel.ActionHandler;

public class PControlPanel extends JPanel{	

	private static final long serialVersionUID = 1L;
	private JButton buttonRight;
	private JButton buttonleft;

	public PControlPanel(String panelId, ActionHandler actionHandler) {
		
		this.buttonRight = new JButton("↓↓↓ "+panelId);
		this.buttonRight.addActionListener(actionHandler);
		this.buttonRight.setActionCommand(panelId);
		this.add(this.buttonRight);
		
		
		this.buttonleft = new JButton("↑↑↑ "+"취소");
		this.buttonleft.addActionListener(actionHandler);
		this.buttonleft.setActionCommand(panelId + " 취소");
		this.add(this.buttonleft);
	}
}

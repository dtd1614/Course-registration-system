package view.panel;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PCreditPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel label;
	
	public PCreditPanel() {
		
		this.label = new JLabel("수강신청 학점 : " + 0 + " / 18");
		this.add(label);
	}
	
	public void update(int credit) {
		this.label.setText("수강신청 학점 : " + credit + " / 18");
	}
	
}

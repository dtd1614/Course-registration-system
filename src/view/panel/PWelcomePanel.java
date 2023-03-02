package view.panel;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import global.Locale;
import valueObject.VAccount;

public class PWelcomePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public PWelcomePanel(VAccount vAccount) {
		JLabel lName = new JLabel(vAccount.getName());
		this.add(lName);
		
		JLabel lInsa = new JLabel(Locale.WelcomePanel.INSA_POSTFIX);
		this.add(lInsa);
		
		JLabel lLogin = new JLabel(Locale.WelcomePanel.LOGINTIME_PREFIX);
		this.add(lLogin);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Locale.TIMEFORMAT);
		JLabel lTime = new JLabel(simpleDateFormat.format(new Date()));
		this.add(lTime);
		
		JLabel lText = new JLabel(Locale.IPNIDA);
		this.add(lText);
	}
}

package view.panel;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.CLecture;
import valueObject.VAccount;
import valueObject.VLecture;
import view.table.PLectureTable;

public class PSugnasincheongPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private PDirectoryPanel directoryPanel;
	private PControlPanel controlPanel1;
	private PLectureTable miridamgiPanel;
	private PControlPanel controlPanel2;
	private PLectureTable sincheongPanel;
	private PCreditPanel creditPanel;
	private VAccount vAccount;
	private CLecture cLecture;
	
	public PSugnasincheongPanel (VAccount vAccount) {
		this.vAccount = vAccount;
		this.cLecture = new CLecture();
		
		ActionHandler actionHandler = new ActionHandler();
		
		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layoutManager);
		
		this.directoryPanel = new PDirectoryPanel();
		this.add(this.directoryPanel);		
		
		this.controlPanel1 = new PControlPanel("미리담기", actionHandler);
		this.add(controlPanel1);		
		
		JScrollPane scrollPane = new JScrollPane();					
		this.miridamgiPanel = new PLectureTable(this.cLecture);
		this.miridamgiPanel.setData("miridamgi"+"/"+this.vAccount.getId());
		scrollPane.setViewportView(this.miridamgiPanel);
		this.add(scrollPane);

		this.controlPanel2 = new PControlPanel("수강신청", actionHandler);
		this.add(controlPanel2);
		
		scrollPane = new JScrollPane();					
		this.sincheongPanel = new PLectureTable(this.cLecture);
		this.sincheongPanel.setData("sugangsincheong"+"/"+this.vAccount.getId());
		scrollPane.setViewportView(this.sincheongPanel);
		this.add(scrollPane);		
		
		this.creditPanel = new PCreditPanel();
		this.add(this.creditPanel);
		this.creditPanel.update(sincheongPanel.getCredit());

	}	
	
	public void sicheongCancel() {
		Vector<VLecture> lectures = this.sincheongPanel.getSelectionLectures();
		if(lectures.isEmpty()) {
			JOptionPane.showMessageDialog(null,"강의를 선택해주세요.");				
		}else {
			this.sincheongPanel.deleteLectures();
			this.miridamgiPanel.addLectures(lectures);
			this.sincheongPanel.clearSelection();
		}
	}

	public void sicheong() {
		Vector<VLecture> lectures = this.miridamgiPanel.getSelectionLectures();
		if(lectures.isEmpty()) {
			JOptionPane.showMessageDialog(null,"강의를 선택해주세요.");				
		}else if(this.sincheongPanel.isCreditFull(lectures)) {
			JOptionPane.showMessageDialog(null,"신청 가능 학점을 초과했습니다.");				
		}else if(this.sincheongPanel.isDuplicatedId(lectures)) {
			JOptionPane.showMessageDialog(null,"중복된 강의입니다.");				
		}else if(this.sincheongPanel.isDuplicatedTime(lectures)) {
			JOptionPane.showMessageDialog(null,"중복된 시간의 강의입니다.");				
		}else {
			this.miridamgiPanel.deleteLectures();
			this.sincheongPanel.addLectures(lectures);
			this.miridamgiPanel.clearSelection();
		}
	}

	public void miridamgiCancel() {
		Vector<VLecture> lectures = this.miridamgiPanel.getSelectionLectures();
		if(lectures.isEmpty()) {
			JOptionPane.showMessageDialog(null,"강의를 선택해주세요.");				
		}else {
			this.miridamgiPanel.deleteLectures();
			this.miridamgiPanel.clearSelection();
		}
	}

	public void miridamgi() {
		Vector<VLecture> lectures = this.directoryPanel.getSelectionLectures();
		if(lectures.isEmpty()) {
			JOptionPane.showMessageDialog(null,"강의를 선택해주세요.");				
		}else if(this.miridamgiPanel.isDuplicatedId(lectures)) {
			JOptionPane.showMessageDialog(null,"중복된 강의입니다.");				
		}else {
			this.miridamgiPanel.addLectures(lectures);
			this.directoryPanel.getLectureTable().clearSelection();
		}
	}		
	
	public class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("미리담기")) {
				miridamgi();
			}else if(e.getActionCommand().equals("미리담기 취소")) {
				miridamgiCancel();
			}else if(e.getActionCommand().equals("수강신청")) {
				sicheong();
			}else if(e.getActionCommand().equals("수강신청 취소")) {
				sicheongCancel();
			}
			creditPanel.update(sincheongPanel.getCredit());
		}
	}
}
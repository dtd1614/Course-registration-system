package view.table;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.CLecture;
import valueObject.VLecture;

public class PLectureTable extends JTable{
	
	protected static final long serialVersionUID = 1L;
	protected DefaultTableModel tableModel;
	private Vector<VLecture> vLectures;
	private String fileName;
	private CLecture cLecture;

	public PLectureTable(CLecture cLecture) {
		this.fileName = "";
		this.cLecture = cLecture;
		this.setvLectures(new Vector<VLecture>());
		Vector<String> header = new Vector<String>();
		header.add("강의번호");
		header.add("강의이름");
		header.add("교수");
		header.add("학점");
		header.add("시간");
		this.tableModel = new DefaultTableModel(header, 0) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int mCollIndex) {
				return false;
			}
		};		
		this.setModel(this.tableModel);	
		this.getTableHeader().setReorderingAllowed(false);
	}	
	
	public void setData(String fileName) {
		this.fileName = fileName;
		this.setvLectures(cLecture.getLectures(fileName));
        this.tableModel.setNumRows(0);
		this.addLecturesToModel(this.getvLectures());
	}		
	
	public void addLectures(Vector<VLecture> vLectures) {
		this.addLecturesToModel(vLectures);
		this.getvLectures().addAll(vLectures);
		this.cLecture.setLectures(fileName, this.getvLectures());
	}
	
	public void addLecturesToModel(Vector<VLecture> vLectures) {
		for(VLecture vLecture : vLectures){
			Vector<String> row = new Vector<String>();
			row.add(vLecture.getId());
			row.add(vLecture.getName());
			row.add(vLecture.getProfessor());
			row.add(vLecture.getCredit());
			row.add(vLecture.getTime());
			this.tableModel.addRow(row);
		}
	}
	
	public Vector<VLecture> getSelectionLectures() {	
		Vector<VLecture> selectionLectures = new Vector<>();
		int[] selectedRows = this.getSelectedRows();
		for(int i = 0; i < selectedRows.length; i++) selectionLectures.add(this.getvLectures().get(selectedRows[i]));
		return selectionLectures;		
	}
    
    public void deleteLectures() {
		int[] selectedRows = this.getSelectedRows();
		for(int i = 0; i < selectedRows.length; i++) {
			this.getvLectures().remove(selectedRows[i]);
			this.tableModel.removeRow(selectedRows[i]);
			for(int j = i + 1; j < selectedRows.length; j++) selectedRows[j]--;
		}
		this.cLecture.setLectures(fileName, this.getvLectures());
    }

	public Vector<VLecture> getvLectures() {
		return vLectures;
	}

	public void setvLectures(Vector<VLecture> vLectures) {
		this.vLectures = vLectures;
	}
	
	public String getFileName() {
		return this.fileName;
	}

	public boolean isDuplicatedId(Vector<VLecture> lectures) {
		for(VLecture vLecture : this.vLectures) {
			for(VLecture vLectureToAdd : lectures) {
				if(vLecture.getId().equals(vLectureToAdd.getId())) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isDuplicatedTime(Vector<VLecture> lectures) {
		for(VLecture vLecture : this.vLectures) {
			for(VLecture vLectureToAdd : lectures) {
				if(vLecture.getTime().equals(vLectureToAdd.getTime())) {
					return true;
				}
			}
		}
		return false;	
	}
	public int getCredit() {
		int credit = 0;
		for(VLecture vLecture : this.vLectures) {
			credit += Integer.parseInt(vLecture.getCredit());
		}
		return credit;		
	}

	public boolean isCreditFull(Vector<VLecture> lectures) {
		int credit = 0;
		for(VLecture vLecture : lectures) {
			credit += Integer.parseInt(vLecture.getCredit());
		}		
		if(getCredit()+credit > 18) return true;
		return false;
	}
}

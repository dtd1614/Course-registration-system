package view.panel;
import java.awt.LayoutManager;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.CLecture;
import valueObject.VLecture;
import view.table.PDirectoryTable;
import view.table.PLectureTable;

public class PDirectoryPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private ListSelectionHandler listSelectionHandler;
	private PDirectoryTable campusTable;
	private PDirectoryTable collegeTable;
	private PDirectoryTable departmentTable;
	private PLectureTable lectureTable;

	public PDirectoryPanel() {

		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layoutManager);
		
		this.listSelectionHandler = new ListSelectionHandler();
		JPanel subPanel1 = new JPanel();		
			layoutManager = new BoxLayout(subPanel1, BoxLayout.X_AXIS);
			subPanel1.setLayout(layoutManager);

			JScrollPane scrollPane = new JScrollPane();					
			this.campusTable = new PDirectoryTable("캠퍼스");
			this.campusTable.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
			scrollPane.setViewportView(this.campusTable);
			subPanel1.add(scrollPane);
			
			scrollPane = new JScrollPane();
			this.collegeTable = new PDirectoryTable("대학");
			this.collegeTable.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
			scrollPane.setViewportView(this.collegeTable);
			subPanel1.add(scrollPane);	
			
			scrollPane = new JScrollPane();
			this.departmentTable = new PDirectoryTable("학과");
			this.departmentTable.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
			scrollPane.setViewportView(this.departmentTable);
			subPanel1.add(scrollPane);			
		this.add(subPanel1);
		
		JPanel subPanel2 = new JPanel();	
		
			layoutManager = new BoxLayout(subPanel2, BoxLayout.X_AXIS);
			subPanel2.setLayout(layoutManager);
			
			scrollPane = new JScrollPane();
			this.lectureTable = new PLectureTable(new CLecture());
			this.lectureTable.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
			scrollPane.setViewportView(this.lectureTable);
			subPanel2.add(scrollPane);	
			
		this.add(subPanel2);			
        this.updateTable(null, 0);		
	}
	
	public PLectureTable getLectureTable() {
		return this.lectureTable;
	}
	
	public void addLectures(Vector<VLecture> lectures) {
	}

	public Vector<VLecture> getSelectionLectures() {
		Vector<VLecture> selectionLectures = new Vector<>();
		int[] selectedRows = this.lectureTable.getSelectedRows();
		for(int i = 0; i < selectedRows.length; i++) selectionLectures.add(this.lectureTable.getvLectures().get(selectedRows[i]));
		return selectionLectures;			
	}
	
	private class ListSelectionHandler implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
            if(!e.getValueIsAdjusting()) {
                ListSelectionModel listSelectionModel = (ListSelectionModel)e.getSource();    
                int selectionIndex = listSelectionModel.getMaxSelectionIndex();
                if(selectionIndex!=-1) updateTable(listSelectionModel, selectionIndex);
            }
		}		
	}	

    private void updateTable(ListSelectionModel listSelectionModel, int selectionIndex) {
        String fileName = null;
        if(listSelectionModel == null){
            fileName = "root";
            fileName = this.campusTable.setData("root/"+fileName);
        }else if(listSelectionModel == this.campusTable.getSelectionModel()){
            fileName = this.campusTable.getVDirectories().get(selectionIndex).getFileName();
            fileName = this.collegeTable.setData("campus/"+fileName);
        }else if(listSelectionModel == this.collegeTable.getSelectionModel()){
            fileName = this.collegeTable.getVDirectories().get(selectionIndex).getFileName();
            fileName = this.departmentTable.setData("college/"+fileName);
        }else if(listSelectionModel == this.departmentTable.getSelectionModel()){
            fileName = this.departmentTable.getVDirectories().get(selectionIndex).getFileName();
            this.lectureTable.setData("department/"+fileName);
        }
    }
}

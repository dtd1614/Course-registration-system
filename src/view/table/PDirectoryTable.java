package view.table;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controller.CDirectory;
import valueObject.VDirectory;

public class PDirectoryTable extends JTable{
	
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
    private Vector<VDirectory> vDirectories;
	
	public PDirectoryTable(String title) {
		Vector<String> header = new Vector<String>();
		header.add(title);
		this.tableModel = new DefaultTableModel(header, 0) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int mCollIndex) {
				return false;
			}
		};
		this.setModel(this.tableModel);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.getTableHeader().setReorderingAllowed(false);
	}	
	
	public String setData(String fileName) {
		CDirectory cDirectory = new CDirectory();
		this.vDirectories = cDirectory.getDirectories(fileName);
        this.tableModel.setNumRows(0);

		for(VDirectory vDirectory : vDirectories){
			Vector<String> row = new Vector<String>();
			row.add(vDirectory.getName());
			this.tableModel.addRow(row);
		}
		this.setRowSelectionInterval(0, 0);
	    return vDirectories.get(0).getFileName();
	}
	
    public Vector<VDirectory> getVDirectories() { 
    	return this.vDirectories;
    }
}

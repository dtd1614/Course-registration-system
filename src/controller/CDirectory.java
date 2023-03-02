package controller;
import java.util.Vector;

import model.MDirectory;
import valueObject.VDirectory;

public class CDirectory {
	
	private MDirectory mDirectory;
	
	public CDirectory() {
		this.mDirectory = new MDirectory();
	}

	public Vector<VDirectory> getDirectories(String fileName) {		
		return this.mDirectory.getDirectories(fileName);
	}
	
	
}

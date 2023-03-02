package controller;
import java.util.Vector;

import model.MLecture;
import valueObject.VLecture;

public class CLecture {
	
	private MLecture mLecture;
	
	public CLecture() {
		this.mLecture = new MLecture();
	}

	public Vector<VLecture> getLectures(String fileName) {		
		return this.mLecture.getLectures(fileName);
	}

	public boolean isDuplicatedId(String fileName, Vector<VLecture> vLectures) {
		return this.mLecture.isDuplicatedId(fileName, vLectures);
	}

	public boolean isDuplicatedTime(String fileName, Vector<VLecture> vLectures) {
		return this.mLecture.isDuplicatedTime(fileName, vLectures);
	}

	public void setLectures(String fileName, Vector<VLecture> getvLectures) {
		this.mLecture.setLectures(fileName, getvLectures);
	}
}

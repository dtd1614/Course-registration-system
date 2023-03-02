package model;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import valueObject.VLecture;

public class MLecture {
	public Vector<VLecture> getLectures(String fileName) {
		
		Vector<VLecture> vLectures = new Vector<VLecture>();
		try {
			File file = new File("directory/" + fileName);
	        if(!file.exists()) file.createNewFile();
	        Scanner scanner = new Scanner(file);
			while(scanner.hasNext()) {
				VLecture vLecture = new VLecture();
				vLecture.read(scanner);
				vLectures.add(vLecture);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vLectures;
	}

	public void setLectures(String fileName, Vector<VLecture> getvLectures) {
		try {
			new FileWriter("directory/" + fileName, false).close();
			for(VLecture vLecture : getvLectures) {
	            File file = new File("directory/" + fileName);   
	            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
	            writer.newLine();
	            writer.write(vLecture.getId()+" ");
	            writer.write(vLecture.getName()+" ");
	            writer.write(vLecture.getProfessor()+" ");
	            writer.write(vLecture.getCredit()+" ");
	            writer.write(vLecture.getTime()+" ");
				writer.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public boolean isDuplicatedId(String fileName, Vector<VLecture> vLectures) {
		return false;
	}

	public boolean isDuplicatedTime(String fileName, Vector<VLecture> vLectures) {
		return false;
	}		
}

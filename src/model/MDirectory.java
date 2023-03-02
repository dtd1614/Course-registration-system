package model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import valueObject.VDirectory;

public class MDirectory {

	public Vector<VDirectory> getDirectories(String fileName) {
		Vector<VDirectory> vDirectories = new Vector<VDirectory>();
		try {
			Scanner scanner = new Scanner(new File("directory/" + fileName));
			while(scanner.hasNext()) {
				VDirectory vDirectory = new VDirectory();
				vDirectory.read(scanner);
				vDirectories.add(vDirectory);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return vDirectories;
	}

}

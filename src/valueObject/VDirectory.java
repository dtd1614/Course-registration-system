package valueObject;
import java.util.Scanner;

public class VDirectory {
	
	private String id;
	private String name;
	private String fileName;
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getFileName() {
		return fileName;
	}
	public void read(Scanner scanner) {
		this.id = scanner.next();
		this.name = scanner.next();
		this.fileName = scanner.next();
	}
}

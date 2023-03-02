package model;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import valueObject.VAccount;

public class MAccount {

	private String id;
	private String password;
	private String name;
	private String email;
	private String department;
	
	public MAccount() {
	}

	public VAccount getLogin(String id, String password) {
		VAccount vLogin = null;
		try {
			File file = new File("account/account");
			Scanner scanner = new Scanner(file);
			// file read
			boolean found = false;
			while (scanner.hasNext() && !found) {
				this.id = scanner.next();
				this.password = scanner.next();
				this.name = scanner.next();	
				this.email = scanner.next();
				this.department = scanner.next();
				if (this.id.equals(id)&&this.password.equals(password)) {
					found = true;
				}
			}
			scanner.close();
			
			if (found) {
				vLogin = new VAccount();
				vLogin.setId(this.id);
				vLogin.setPassword(this.password);
				vLogin.setName(this.name);
				vLogin.setEmail(this.email);
				vLogin.setDepartment(this.department);
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		return vLogin;
	}

	public void createAccount(VAccount vAccount) {
       try {
            File file = new File("account/account");   
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.newLine();
            writer.write(vAccount.getId()+" ");
            writer.write(vAccount.getPassword()+" ");
            writer.write(vAccount.getName()+" ");
            writer.write(vAccount.getEmail()+" ");
            writer.write(vAccount.getDepartment()+" ");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public VAccount getAccount(String id) {
		VAccount vAccount = null;
		try {
			File file = new File("account/account");
			Scanner scanner = new Scanner(file);
			// file read
			boolean found = false;
			while (scanner.hasNext() && !found) {
				this.id = scanner.next();
				this.password = scanner.next();
				this.name = scanner.next();	
				this.email = scanner.next();
				this.department = scanner.next();
				if (this.id.equals(id)) {
					found = true;
				}
			}
			scanner.close();
			
			if (found) {
				vAccount = new VAccount();
				vAccount.setId(this.id);
				vAccount.setPassword(this.password);
				vAccount.setName(this.name);
				vAccount.setEmail(this.email);
				vAccount.setDepartment(this.department);
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		return vAccount;	
	}

	public boolean isDuplicatedEmail(String email) {
		try {
			File file = new File("account/account");
			Scanner scanner = new Scanner(file);
			// file read
			while (scanner.hasNext()) {
				this.id = scanner.next();
				this.password = scanner.next();
				this.name = scanner.next();	
				this.email = scanner.next();
				this.department = scanner.next();
				if (this.email.equals(email)) {
					scanner.close();		
					return true;
				}
			}
			scanner.close();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		return false;			
	}
	
	public boolean isDuplicatedEmail(String id, String email) {
		try {
			File file = new File("account/account");
			Scanner scanner = new Scanner(file);
			// file read
			while (scanner.hasNext()) {
				this.id = scanner.next();
				this.password = scanner.next();
				this.name = scanner.next();	
				this.email = scanner.next();
				this.department = scanner.next();
				if (!this.id.equals(id)&&this.email.equals(email)) {
					scanner.close();		
					return true;
				}
			}
			scanner.close();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		return false;			
	}

	public String findId(String name, String email, String department) {
		try {
			File file = new File("account/account");
			Scanner scanner = new Scanner(file);
			// file read
			while (scanner.hasNext()) {
				this.id = scanner.next();
				this.password = scanner.next();
				this.name = scanner.next();	
				this.email = scanner.next();
				this.department = scanner.next();
				if (this.name.equals(name)&&this.email.equals(email)&&this.department.equals(department)) {
					scanner.close();		
					return id;
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		return null;		
	}

	public String findPassword(String id, String name, String email, String department) {
		try {
			File file = new File("account/account");
			Scanner scanner = new Scanner(file);
			// file read
			while (scanner.hasNext()) {
				this.id = scanner.next();
				this.password = scanner.next();
				this.name = scanner.next();	
				this.email = scanner.next();
				this.department = scanner.next();
				if (this.id.equals(id)&&this.name.equals(name)&&this.email.equals(email)&&this.department.equals(department)) {
					scanner.close();		
					return password;
				}
			}
			scanner.close();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		return null;	
	}

	public void deleteAccount(String id) {
		try {
			Vector<VAccount> vAccounts = new Vector<>();
			File file = new File("account/account");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				VAccount vAccount = new VAccount();
				vAccount.setId(scanner.next());
				vAccount.setPassword(scanner.next());
				vAccount.setName(scanner.next());
				vAccount.setEmail(scanner.next());
				vAccount.setDepartment(scanner.next());
				if (!vAccount.getId().equals(id)) {
					vAccounts.add(vAccount);
				}
			}
			scanner.close();
			new FileWriter("account/account", false).close();
			for(VAccount vAccount : vAccounts) {
				this.createAccount(vAccount);
			}
			System.gc();
			System.runFinalization();
			new File("directory/miridamgi"+"/"+id).delete();
			new File("directory/sugangsincheong/"+"/"+id).delete();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void editAccount(VAccount vAccount) {
		this.deleteAccount(vAccount.getId());
		this.createAccount(vAccount);
	}
}

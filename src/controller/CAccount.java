package controller;
import model.MAccount;
import valueObject.VAccount;

public class CAccount {
	
	private MAccount mAccount;
	public CAccount() {
		this.mAccount = new MAccount();
	}

	public VAccount getLogin(String id, String password) {
		return this.mAccount.getLogin(id, password);
	}

	public VAccount getAccount(String id) {
		return this.mAccount.getAccount(id);
	}

	public void createAccount(VAccount vAccount) {
		this.mAccount.createAccount(vAccount);
	}

	public boolean isDuplicatedEmail(String email) {
		return this.mAccount.isDuplicatedEmail(email);
	}

	public String findId(String name, String email, String department) {
		return this.mAccount.findId(name,email,department);
	}

	public String findPassword(String id, String name, String email, String department) {
		return this.mAccount.findPassword(id,name,email,department);
	}

	public void deleteAccount(String id) {
		this.mAccount.deleteAccount(id);
	}

	public void editAccount(VAccount vAccount) {
		this.mAccount.editAccount(vAccount);
	}

	public boolean isDuplicatedEmail(String id, String email) {
		return this.mAccount.isDuplicatedEmail(id, email);
	}
}

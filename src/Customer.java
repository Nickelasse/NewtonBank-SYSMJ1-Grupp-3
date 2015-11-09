
import java.util.ArrayList;

public class Customer {

	private String name;
	private long pNr;
	private ArrayList<SavingsAccount> accounts;

	public Customer(String name, long pNr) {
		this.name = name;
		this.pNr = pNr;
		this.accounts = new ArrayList<>();

	}

	// Get Name
	public String getName() {
		return name;

	}

	public void setName(String name) {
		this.name = name;
	}

	// Get PersonNummer
	public long getpNr() {
		return pNr;
	}

	public ArrayList<SavingsAccount> getAccounts() {
		return accounts;
	}

	// To String - Message
	public String toString() {
		String str = "Namn: " + name + "\n" + "Personnummer: (" + pNr + ")";
		return str;
	}

}

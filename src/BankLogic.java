

import java.util.ArrayList;
import java.util.Iterator;

public class BankLogic {
	private ArrayList<Customer> customerList;

	public BankLogic() {
		customerList = new ArrayList<Customer>();
	}

	/**
	 * Present name and personal id in a list
	 * @return list with all customers
	 */
	public ArrayList<String> getCustomers() {
		ArrayList<String> list = new ArrayList<>();
		for (Customer customer : customerList)
			list.add(customer.toString());
		return list;
	}

	/**
	 * Add new customer
	 * @param name-new customer's name
	 * @param pNr-new customer's personal id
	 * @return true if customer is added, false if customer with this personal
	 *         id exists
	 */
	public boolean addCustomer(String name, long pNr) {
		boolean add = true;
		for (Customer customer : customerList) {
			if (customer.getpNr() == pNr) {
				add = false;
			}
		}
		if (add) {
			customerList.add(new Customer(name, pNr));
		}
		return add;
	}

	/**
	 * Get customer with pNr and return his name, person id as well
	 * as all his accounts
	 * @param pNr - person id
	 * @return list with name, person id and all accounts
	 */
	public ArrayList<String> getCustomer(long pNr) {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getpNr() == pNr) {
				Customer customer = customerList.get(i);
				list.add(customer.toString());
				if (customer.getAccounts().size() != 0)
					list.add(customer.getAccounts().toString());
				return list;
			}
		}
		return null;
	}

	/**
	 * Change customer name with a certain person id
	 * @param name-new name
	 * @param pNr-customer with this person id
	 * @return true if customer exists, false otherwise
	 */
	public boolean changeCustomerName(String name, long pNr) {
		for (Customer customer : customerList) {
			if (customer.getpNr() == pNr) {
				customer.setName(name);
				return true;
			}
		}
		return false;
	}

	/**
	 * Remove customer with a certain person id. Show info about all accounts,
	 * as well as info about balance and interest rate
	 * @param pNr-customer with a person id to remove
	 * @return -list with all accounts, balance and interest
	 */
	public ArrayList<String> removeCustomer(long pNr) {
		ArrayList<String> list = new ArrayList<>();
		Iterator<Customer> it = customerList.iterator();
		double balance = 0, interest = 0;
		while (it.hasNext()) {
			Customer customer = it.next();
			if (customer.getpNr() == pNr) {
				ArrayList<SavingsAccount> accounts = customer.getAccounts();
				for (SavingsAccount account : accounts) {
					balance += account.getBalance();
					interest = account.calculateInterest(balance, account.getInterest());
				}
				it.remove();
				list.add(customer.getAccounts().toString());
				list.add("Balance: " + balance + ", Interest rate: " + interest);
				return list;
			}
		}
		return null;
	}

	/**
	 * Add savings account
	 * @param pNr-customer with person id to add account for
	 * @return account id if account created, -1 if it failed
	 */
	public int addSavingsAccount(long pNr) {
		SavingsAccount account;
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getpNr() == pNr) {
				account = new SavingsAccount();
				customerList.get(i).getAccounts().add(account);
				return account.getAccountId();
			}
		}
		return -1;
	}

	/**
	 * Return information about account with accountId and for customer with pNr
	 * @param pNr-person id
	 * @param accountId - account id
	 * @return info about account(balance, interest, type and account id
	 */
	public String getAccount(long pNr, int accountId) {
		ArrayList<SavingsAccount> accounts = null;
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getpNr() == pNr) {
				accounts = customerList.get(i).getAccounts();
				for (SavingsAccount account : accounts) {
					if (account.getAccountId() == accountId) {
						return account.toString();
					}
				}
			}
		}
		return null;
	}

	/**
	 * Deposit for a customer with person id, account id and amount
	 * @param pNr - person id
	 * @param accountId-account id
	 * @param amount-amount to deposit
	 * @return true if deposit successful, false otherwise
	 */
	public boolean deposit(long pNr, int accountId, double amount) {
		ArrayList<SavingsAccount> accounts;
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getpNr() == pNr) {
				accounts = customerList.get(i).getAccounts();
				for (SavingsAccount account : accounts) {
					if (account.getAccountId() == accountId) {
						account.deposit(amount);
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Withdraw amount for customer with person id, account id
	 * @param pNr-person id
	 * @param accountId-account id
	 * @param amount-amount to withdraw
	 * @return true if withdraw successful, false otherwise
	 */
	public boolean withdraw(long pNr, int accountId, double amount) {
		ArrayList<SavingsAccount> accounts;
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getpNr() == pNr) {
				accounts = customerList.get(i).getAccounts();
				for (SavingsAccount account : accounts) {
					if (account.getAccountId() == accountId) {
						account.withdraw(amount);
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Close the account with account id for a customer
	 * @param pNr-person id
	 * @param accountId-account id for account to close
	 * @return info about balance and interest
	 */
	public String closeAccount(long pNr, int accountId) {
		String info = null;
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getpNr() == pNr) {
				ArrayList<SavingsAccount> accounts = new ArrayList<>();
				accounts = customerList.get(i).getAccounts();
				for (SavingsAccount account : accounts) {
					if (account.getAccountId() == accountId) {
						accounts.remove(account);
						info = "Balance: " + account.getBalance() + ", Interest: "
								+ account.calculateInterest(account.getBalance(), account.getInterest());
						return info;
					}
				}
			}
		}
		return null;
	}
}

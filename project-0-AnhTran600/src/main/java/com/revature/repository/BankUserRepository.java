package com.revature.repository;

import com.revature.model.BankUser;
import java.util.List;

public interface BankUserRepository {

	public boolean createBankUser(BankUser user);
	
	public BankUser findBankUsername(String username);
	
	public List<BankUser> getAll();

	public void withdraw(BankUser user);
	
	public void deposit(BankUser user);
	
	public long findMaxId();
}

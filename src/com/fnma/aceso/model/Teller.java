package com.fnma.aceso.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class Teller {
	private List<User> userList=new ArrayList<User>();
	private Map<User,List<Account>> accountMap=new HashMap<User,List<Account>>();
	
	public void assignAccount(User user, Account account){
		List<Account> accounts=accountMap.get(user);
		if(accounts==null){
			accounts=new ArrayList<Account>();
			accounts.add(account);
			accountMap.put(user, accounts);			
		}
		if(!accounts.contains(account)){
			accounts.add(account);
			accountMap.put(user, accounts);
		}		
	}
	
	public List<Account> listFor(User user) {
		return accountMap.get(user);
	}
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public Map<User,List<Account>> getAccountMap() {
		return accountMap;
	}
	public void setAccountMap(Map<User,List<Account>> accountMap) {
		this.accountMap = accountMap;
	}
}

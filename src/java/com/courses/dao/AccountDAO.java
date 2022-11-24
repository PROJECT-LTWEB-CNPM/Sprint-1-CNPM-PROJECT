package com.courses.dao;

import java.util.List;
import java.util.Map;

import com.courses.models.Account;

public class AccountDAO extends JpaDAO<Account> implements GenericDAO<Account>{
	public AccountDAO( ) {
		super();
	}
	
	@Override
	public Account create(Account entity) {
		return super.create(entity);
	}
	
	@Override
	public Account update(Account entity) {
		return super.update(entity);
	}
	
	@Override
	public void delete(Object id) {
		super.delete(Account.class, id);
	}
	
	@Override
	public Account find(Object primaryKey) {
		return super.find(Account.class, primaryKey);
	}
	
	@Override
	public List<Account> findAll(){
		String queryName = "Account.findAll";
		return super.findAll(queryName, Account.class);
	}
	
	@Override
	public List<Account> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		return super.findWithNamedQuery(queryName, parameters);
	}
}

package com.courses.dao;

import java.util.List;

import com.courses.models.Admin;

public class AdminDAO extends JpaDAO<Admin> implements GenericDAO<Admin> {

	public AdminDAO() {
	}

	@Override
	public Admin create(Admin entity) {
		return super.create(entity);
	}

	@Override
	public Admin update(Admin entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public void delete(Object id) {
		super.delete(Admin.class, id);
	}

	@Override
	public Admin find(Object primaryKey) {
		return super.find(Admin.class, primaryKey);
	}

	@Override
	public List<Admin> findAll() {
		String queryName = "Admin.findAll";
		return super.findAll(queryName, Admin.class);
	}

}
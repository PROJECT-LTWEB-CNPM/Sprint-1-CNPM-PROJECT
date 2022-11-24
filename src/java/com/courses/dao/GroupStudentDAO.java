package com.courses.dao;

import java.util.List;
import java.util.Map;

import com.courses.models.GroupStudent;

public class GroupStudentDAO extends JpaDAO<GroupStudent> implements GenericDAO<GroupStudent> {
	public GroupStudentDAO() {
		super();
	}

	@Override
	public GroupStudent create(GroupStudent entity) {
		return super.create(entity);
	}

	@Override
	public GroupStudent update(GroupStudent entity) {
		return super.update(entity);
	}

	@Override
	public void delete(Object id) {
		super.delete(GroupStudent.class, id);
	}

	@Override
	public GroupStudent find(Object primaryKey) {
		return super.find(GroupStudent.class, primaryKey);
	}

	@Override
	public List<GroupStudent> findAll() {
		String queryName = "GroupStudent.findAll";
		return super.findAll(queryName, GroupStudent.class);
	}

	@Override
	public List<GroupStudent> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		return super.findWithNamedQuery(queryName, parameters);
	}
}

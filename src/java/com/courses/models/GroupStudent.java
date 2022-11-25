package com.courses.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the groupstudent database table.
 * 
 */
@Entity
@NamedQuery(name="GroupStudent.findAll", query="SELECT g FROM GroupStudent g")
public class GroupStudent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="group_id")
	private String groupId;

	@Column(name="current_number")
	private int currentNumber;

	private String description;

	@Column(name="is_deleted")
	private byte isDeleted;

	@Column(name="is_full")
	private byte isFull;

	@Column(name="leader_id")
	private String leaderId;

	//bi-directional many-to-one association to Topic
	@ManyToOne
	@JoinColumn(name="topic_id")
	private Topic topic;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy="groupstudent")
	private List<Student> students;

	public GroupStudent() {
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public int getCurrentNumber() {
		return this.currentNumber;
	}

	public void setCurrentNumber(int currentNumber) {
		this.currentNumber = currentNumber;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	public byte getIsFull() {
		return this.isFull;
	}

	public void setIsFull(byte isFull) {
		this.isFull = isFull;
	}

	public String getLeaderId() {
		return this.leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	public Topic getTopic() {
		return this.topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setGroupstudent(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setGroupstudent(null);

		return student;
	}

}
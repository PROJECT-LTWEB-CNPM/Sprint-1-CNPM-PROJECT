package com.courses.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the teacher database table.
 * 
 */
@Entity
@Table(name="teacher")
@NamedQuery(name="Teacher.findAll", query="SELECT t FROM Teacher t")
public class Teacher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="teacher_id", unique=true, nullable=false, length=12)
	private String teacherId;

	@Column(name="is_head")
	private byte isHead;

	//bi-directional many-to-one association to Major
	@ManyToOne
	@JoinColumn(name="major_id")
	private Major major;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;

	//bi-directional many-to-many association to Board
	@ManyToMany
	@JoinTable(
		name="teacherboard"
		, joinColumns={
			@JoinColumn(name="teacher_id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="board_id", nullable=false)
			}
		)
	private List<Board> boards;

	//bi-directional many-to-one association to Topic
	@OneToMany(mappedBy="teacher")
	private List<Topic> topics;

	public Teacher() {
	}

	public String getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public byte getIsHead() {
		return this.isHead;
	}

	public void setIsHead(byte isHead) {
		this.isHead = isHead;
	}

	public Major getMajor() {
		return this.major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Board> getBoards() {
		return this.boards;
	}

	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}

	public List<Topic> getTopics() {
		return this.topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public Topic addTopic(Topic topic) {
		getTopics().add(topic);
		topic.setTeacher(this);

		return topic;
	}

	public Topic removeTopic(Topic topic) {
		getTopics().remove(topic);
		topic.setTeacher(null);

		return topic;
	}

}
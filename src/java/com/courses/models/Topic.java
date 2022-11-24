package com.courses.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the topic database table.
 * 
 */
@Entity
@Table(name="topic")
@NamedQuery(name="Topic.findAll", query="SELECT t FROM Topic t")
public class Topic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="topic_id", unique=true, nullable=false, length=12)
	private String topicId;

	@Column(length=255)
	private String description;

	@Column(name="is_full")
	private byte isFull;

	@Column(name="is_selected")
	private byte isSelected;

	@Column(name="max_mo_member")
	private int maxMoMember;

	private byte status;

	@Column(name="topic_name", length=255)
	private String topicName;

	//bi-directional many-to-one association to GroupStudent
	@OneToMany(mappedBy="topic")
	private List<GroupStudent> groupstudents;

	//bi-directional many-to-one association to Major
	@ManyToOne
	@JoinColumn(name="major_id")
	private Major major;

	//bi-directional many-to-one association to RegistrationPeriod
	@ManyToOne
	@JoinColumn(name="registration_period_id")
	private RegistrationPeriod registrationperiod;

	//bi-directional many-to-one association to Teacher
	@ManyToOne
	@JoinColumn(name="teacher_id")
	private Teacher teacher;

	public Topic() {
	}

	public String getTopicId() {
		return this.topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getIsFull() {
		return this.isFull;
	}

	public void setIsFull(byte isFull) {
		this.isFull = isFull;
	}

	public byte getIsSelected() {
		return this.isSelected;
	}

	public void setIsSelected(byte isSelected) {
		this.isSelected = isSelected;
	}

	public int getMaxMoMember() {
		return this.maxMoMember;
	}

	public void setMaxMoMember(int maxMoMember) {
		this.maxMoMember = maxMoMember;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getTopicName() {
		return this.topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public List<GroupStudent> getGroupstudents() {
		return this.groupstudents;
	}

	public void setGroupstudents(List<GroupStudent> groupstudents) {
		this.groupstudents = groupstudents;
	}

	public GroupStudent addGroupstudent(GroupStudent groupstudent) {
		getGroupstudents().add(groupstudent);
		groupstudent.setTopic(this);

		return groupstudent;
	}

	public GroupStudent removeGroupstudent(GroupStudent groupstudent) {
		getGroupstudents().remove(groupstudent);
		groupstudent.setTopic(null);

		return groupstudent;
	}

	public Major getMajor() {
		return this.major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public RegistrationPeriod getRegistrationperiod() {
		return this.registrationperiod;
	}

	public void setRegistrationperiod(RegistrationPeriod registrationperiod) {
		this.registrationperiod = registrationperiod;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}
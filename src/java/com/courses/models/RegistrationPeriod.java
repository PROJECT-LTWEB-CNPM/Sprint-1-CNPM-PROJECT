package com.courses.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the registrationperiod database table.
 * 
 */
@Entity
@Table(name = "registrationperiod")
@NamedQueries({ @NamedQuery(name = "RegistrationPeriod.findAll", query = "SELECT r FROM RegistrationPeriod r"),
		@NamedQuery(name = "RegistrationPeriod.findByIsTeacher", query = "SELECT r FROM RegistrationPeriod r WHERE r.isRegistrationTeacher = :isRegistrationTeacher") })

public class RegistrationPeriod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "registration_period_id", unique = true, nullable = false, length = 12)
	private String registrationPeriodId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "close_date")
	private Date closeDate;

	@Column(length = 255)
	private String description;

	@Column(name = "is_active")
	private byte isActive;

	@Column(name = "is_registration_teacher", nullable = false)
	private byte isRegistrationTeacher;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "open_date")
	private Date openDate;

	@Column(name = "registration_period_name", length = 10)
	private String registrationPeriodName;

	@Column(name = "school_year")
	private int schoolYear;

	private int semeter;

	// bi-directional many-to-one association to Topic
	@OneToMany(mappedBy = "registrationperiod")
	private List<Topic> topics;

	public RegistrationPeriod() {
	}

	public String getRegistrationPeriodId() {
		return this.registrationPeriodId;
	}

	public void setRegistrationPeriodId(String registrationPeriodId) {
		this.registrationPeriodId = registrationPeriodId;
	}

	public Date getCloseDate() {
		return this.closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public byte getIsRegistrationTeacher() {
		return this.isRegistrationTeacher;
	}

	public void setIsRegistrationTeacher(byte isRegistrationTeacher) {
		this.isRegistrationTeacher = isRegistrationTeacher;
	}

	public Date getOpenDate() {
		return this.openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public String getRegistrationPeriodName() {
		return this.registrationPeriodName;
	}

	public void setRegistrationPeriodName(String registrationPeriodName) {
		this.registrationPeriodName = registrationPeriodName;
	}

	public int getSchoolYear() {
		return this.schoolYear;
	}

	public void setSchoolYear(int schoolYear) {
		this.schoolYear = schoolYear;
	}

	public int getSemeter() {
		return this.semeter;
	}

	public void setSemeter(int semeter) {
		this.semeter = semeter;
	}

	public List<Topic> getTopics() {
		return this.topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public Topic addTopic(Topic topic) {
		getTopics().add(topic);
		topic.setRegistrationperiod(this);

		return topic;
	}

	public Topic removeTopic(Topic topic) {
		getTopics().remove(topic);
		topic.setRegistrationperiod(null);

		return topic;
	}

}
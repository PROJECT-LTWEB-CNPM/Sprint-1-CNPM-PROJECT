package com.courses.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the teacherboard database table.
 * 
 */
@Entity
@Table(name="teacherboard")
@NamedQuery(name="TeacherBoard.findAll", query="SELECT t FROM TeacherBoard t")
public class TeacherBoard implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TeacherBoardPK id;

	public TeacherBoard() {
	}

	public TeacherBoardPK getId() {
		return this.id;
	}

	public void setId(TeacherBoardPK id) {
		this.id = id;
	}

}
package com.courses.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the board database table.
 * 
 */
@Entity
@Table(name="board")
@NamedQuery(name="Board.findAll", query="SELECT b FROM Board b")
public class Board implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="board_id", unique=true, nullable=false, length=12)
	private String boardId;

	@Column(name="board_name", length=255)
	private String boardName;

	@Column(length=255)
	private String description;

	@Column(name="no_member")
	private int noMember;

	//bi-directional many-to-many association to Teacher
	@ManyToMany(mappedBy="boards")
	private List<Teacher> teachers;

	public Board() {
	}

	public String getBoardId() {
		return this.boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getBoardName() {
		return this.boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNoMember() {
		return this.noMember;
	}

	public void setNoMember(int noMember) {
		this.noMember = noMember;
	}

	public List<Teacher> getTeachers() {
		return this.teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

}
package com.bhalchandra.studentregistrationmvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {
	
	public Student() {
	}
	
	@Id
	private int sid;
	private String sname;
	private String sbranch;
	private int semester;
	
	
	public Student(int sid, String sname, String sbranch, int semester) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.sbranch = sbranch;
		this.semester = semester;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", sbranch=" + sbranch + ", semester=" + semester + "]";
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSbranch() {
		return sbranch;
	}
	public void setSbranch(String sbranch) {
		this.sbranch = sbranch;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
}

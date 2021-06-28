package com.ttech.jwt.model;

import java.util.Arrays;

public class Course {
	
	private long courseId;
	private String courserName;
	private String nameOfInstructor;
	private String roomNumber;
	private String[] prerequisites;
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public String getCourserName() {
		return courserName;
	}
	public void setCourserName(String courserName) {
		this.courserName = courserName;
	}
	public String getNameOfInstructor() {
		return nameOfInstructor;
	}
	public void setNameOfInstructor(String nameOfInstructor) {
		this.nameOfInstructor = nameOfInstructor;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String[] getPrerequisites() {
		return prerequisites;
	}
	public void setPrerequisites(String[] prerequisites) {
		this.prerequisites = prerequisites;
	}
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courserName=" + courserName + ", nameOfInstructor="
				+ nameOfInstructor + ", roomNumber=" + roomNumber + ", prerequisites=" + Arrays.toString(prerequisites)
				+ "]";
	}
	

}

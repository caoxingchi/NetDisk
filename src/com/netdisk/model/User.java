package com.netdisk.model;

import java.io.Serializable;
import java.util.Set;

public class User implements Serializable{
	private String userName;
	private String user_tel;
	private String gender;
	private String userNum;
	private String password;
	private String Email;
	private String birth;
	private double usedCapacity;
	private double Capacity;
	private Set<UserFile> userFileSet;
	
	public Set<UserFile> getUserFileSet() {
		return userFileSet;
	}
	public void setUserFileSet(Set<UserFile> userFileSet) {
		this.userFileSet = userFileSet;
	}
	public double getCapacity() {
		return Capacity;
	}
	public void setCapacity(double capacity) {
		Capacity = capacity;
	}
	public double getUsedCapacity() {
		return usedCapacity;
	}
	public void setUsedCapacity(double usedCapacity) {
		this.usedCapacity = usedCapacity;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", user_tel=" + user_tel + ", gender=" + gender
				+ ", userNum=" + userNum + ", password=" + password + ", Email=" + Email + ", birth=" + birth + "]";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
}
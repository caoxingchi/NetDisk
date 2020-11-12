package com.netdisk.model;

import java.io.Serializable;

public class UserFile implements Serializable{
	
	private int fileid;
	private String userNum;
	private String fileName;
	private String fileType;
	private String fileLocalPath;
	private String filePath;
	private String fileFolder;
	private double fileSize;
	private String updateTime;
	
	public int getFileid() {
		return fileid;
	}
	public void setFileid(int fileid) {
		this.fileid = fileid;
	}
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileLocalPath() {
		return fileLocalPath;
	}
	public void setFileLocalPath(String fileLocalPath) {
		this.fileLocalPath = fileLocalPath;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileFolder() {
		return fileFolder;
	}
	public void setFileFolder(String fileFolder) {
		this.fileFolder = fileFolder;
	}
	public double getFileSize() {
		return fileSize;
	}
	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "UserFile [userNum=" + userNum + ", fileName=" + fileName + ", fileType=" + fileType + ", fileLocalPath="
				+ fileLocalPath + ", filePath=" + filePath + ", fileFolder=" + fileFolder + ", fileSize=" + fileSize
				+ ", updateTime=" + updateTime + "]";
	}
	public UserFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserFile(String userNum, String fileName, String fileType, String fileLocalPath, String fileFolder,
			double fileSize, String updateTime) {
		super();
		this.userNum = userNum;
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileLocalPath = fileLocalPath;
		this.fileFolder = fileFolder;
		this.fileSize = fileSize;
		this.updateTime = updateTime;
	}
	
	
	
}

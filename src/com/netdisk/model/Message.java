package com.netdisk.model;

import java.io.File;
import java.io.Serializable;
import java.util.Set;

/**
 * 封装网盘消息类型，用来创建
 * @author caoxi
 *
 */
public class Message implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1110114075708618057L;
	private User from;
	private String filename;
	private long fileSize;
	private MessageType type;
	private Set<File> files;
	private int update;

	public int getUpdate() {
		return update;
	}

	public void setUpdate(int update) {
		this.update = update;
	}



	public Set<File> getFiles() {
		return files;
	}
	public void setFiles(Set<File> files) {
		this.files = files;
	}
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User getFrom() {
		return from;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Message [from=" + from + ", filename=" + filename + ", fileSize=" + fileSize + ", type=" + type + "]";
	}
	public Message(User from, String filename, long fileSize, MessageType type) {
		super();
		this.from = from;
		this.filename = filename;
		this.fileSize = fileSize;
		this.type = type;
	}


}

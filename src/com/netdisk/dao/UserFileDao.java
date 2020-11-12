package com.netdisk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.netdisk.model.UserFile;
import com.netdisk.utils.StringUtil;

public class UserFileDao {
	/**
	 * 上传文件到服务器
	 * @param con
	 * @param userFile
	 */
	public int AddFile(Connection con,UserFile userFile) throws Exception{
		String sql="insert into userfiles"
				+ " (fileid,userNum,fileName,"
				+ "fileType,fileSize,updateTime,"
				+ "fileFolderName,fileLocalPath,filePath)"
				+ "values(null,?,?,?,?,?,?,?,?)";
		
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, userFile.getUserNum());
			ps.setString(2, userFile.getFileName());
			ps.setString(3, userFile.getFileType());
			ps.setDouble(4, userFile.getFileSize());
			ps.setString(5, userFile.getUpdateTime());
			ps.setString(6, userFile.getFileFolder());
			ps.setString(7, userFile.getFileLocalPath());
			ps.setString(8, userFile.getFilePath());
			
			return ps.executeUpdate();
			
	}
	
	public ResultSet FileList(Connection con,UserFile userFile) throws Exception{
		ResultSet rs=null;
		StringBuffer sql=new StringBuffer("select * from userFiles uf,t_user u where "
				+ "uf.userNum=u.userNum and u.userNum=?");
		//根据类型查询
		if(StringUtil.isNotEmpty(userFile.getFileType())) {
			sql.append(" and uf.fileType like'%" + userFile.getFileType()+ "%'");
		}
		//模糊查询
		if(StringUtil.isNotEmpty(userFile.getFileName())) {
			sql.append(" and uf.fileType like '%" + userFile.getFileName()+ "%'");
		}
		
		PreparedStatement ps = con.prepareStatement(sql.toString());
		ps.setString(1, userFile.getUserNum());
		return ps.executeQuery();
	}
	
	
}

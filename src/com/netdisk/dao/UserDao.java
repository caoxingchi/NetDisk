package com.netdisk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import com.netdisk.model.User;
import com.netdisk.model.UserFile;
import com.netdisk.utils.RegexUtil;
import com.netdisk.utils.StringUtil;

public class UserDao {
	User user;
	UserFileDao userFileDao=new UserFileDao();
	UserFile userFile;
	/**
	 * 
	 * @param con
	 * @param user
	 * @return
	 */
	public User Login(Connection con, String Account, String password) throws Exception {
		
		PreparedStatement ps = null;
		String userNum=null;
		String sqlTel = "select * from t_user where user_tel=? and password=?";
		String sqlUserNum = "select * from t_user where userNum=? and password=?";
		if (RegexUtil.isMobile(Account)) {
			ps = con.prepareStatement(sqlTel);
		} else {
			ps = con.prepareStatement(sqlUserNum);
		}
		ps.setString(1, Account);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			user=new User();
			userNum=rs.getString("userNum");
			user.setUserName(rs.getString("userName"));
			user.setUserNum(userNum);
			user.setUser_tel(rs.getString("user_tel"));
			user.setGender(rs.getString("user_gender"));
			user.setBirth(rs.getString("birth"));
			user.setEmail(rs.getString("Email"));
			user.setCapacity(rs.getDouble("Capacity"));
			user.setUsedCapacity(rs.getDouble("usedCapacity"));
		}
		userFile=new UserFile();
		userFile.setUserNum(userNum);
		ResultSet rsFile=userFileDao.FileList(con, userFile);
		Set userFileSet =new HashSet();
		while(rsFile.next()) {
			UserFile file=new UserFile();
			file.setUserNum(rsFile.getString("userNum"));
			file.setFileName(rsFile.getString("fileName"));
			file.setFileLocalPath(rsFile.getString("fileLocalPath"));
			file.setFileSize(rsFile.getDouble("fileSize"));
			file.setFilePath(rsFile.getString("filePath"));
			file.setFileType(rsFile.getString("fileType"));
			file.setUpdateTime(rsFile.getString("updateTime"));
			file.setFileFolder(rsFile.getString("fileFolderName"));
			userFileSet.add(file);
		}
		
		user.setUserFileSet(userFileSet);
		return user;
	}

	/**
	 * 是否存在该用户
	 * 
	 * @param user
	 * @param con
	 * @return
	 */
	public boolean isExistAccount(String account, Connection con) {

		ResultSet rs = null;
		PreparedStatement ps = null;
		String sqlTel = "select * from t_user where user_tel=?";
		String sqlUserNum = "select * from t_user where userNum=?";
		try {
			if (RegexUtil.isMobile(account)) {
				ps = con.prepareStatement(sqlTel);
			} else {
				ps = con.prepareStatement(sqlUserNum);
			}
			ps.setString(1, account);
			rs = ps.executeQuery();
			return rs.next();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 验证成功？
	 * @param user
	 * @param con
	 * @return
	 */
	public boolean isSuccessed(User user, Connection con) {

		ResultSet rs = null;
		PreparedStatement ps = null;
		String sqlUser = "select * from t_user where userName=? and Email=? and user_tel=?";
		try {
			ps = con.prepareStatement(sqlUser);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getUser_tel());
			rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @param con
	 * @param user
	 * @return
	 */
	public int Register(Connection con, User user) {
		String name = user.getUserName();
		String password = user.getPassword();
		String gender = user.getGender();
		String tel_num = user.getUser_tel();
		String email = user.getEmail();
		String birth = user.getBirth();
		int rs = 0;
		try {

			String sql = "insert into t_user (userName,userNum,user_tel,user_gender,"
					+ "birth,Email,password)values(?,?,?,?,?,?,?)";
			// userName userNum user_tel user_gender birth Email password
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			String user_num = StringUtil.GetUserNun().trim();
			ps.setString(2, user_num);
			ps.setString(3, tel_num);
			ps.setString(4, gender);
			ps.setString(5, birth);
			ps.setString(6, email);
			ps.setString(7, password);

			rs = ps.executeUpdate();
			if (rs > 0) {
				System.out.println("恭喜 " + name + " 注册成功");
			} else if (rs == 0) {
				System.out.println("注册失败！！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;

	}
	
	public int UpdateUserPassword(User user,Connection con) {
		//set SQL_SAFE_UPDATES=0;  将表格的安全性降低
		String sql="Update t_user set password=? where user_tel=? and Email=?";
		int executeUpdate=0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getUser_tel());
			ps.setString(3, user.getEmail());
			executeUpdate = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return executeUpdate;
	}
	
	public void CreateUserFileTable(User user,Connection con) {
		String table="netDiskFile"+user.getUserName();
		String createTable="create table "+table+"(userNum char(20)," + 
				"fileName char(50)," + 
				"fileType char(20)," + 
				"fileSize double," + 
				"fileTime datetime)engine InnoDB charset utf8";
	}
	
	public int UpdateCapacity(User user,Connection con) throws Exception{
		String sql="update t_user set Capacity=?,usedCapacity=? where userName=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setDouble(1, user.getCapacity());
		ps.setDouble(2, user.getUsedCapacity());
		ps.setString(3, user.getUserName());
		return ps.executeUpdate();
	}
}

package com.netdisk.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	private String url="jdbc:mysql://localhost:3306/db_netDisk?useUnicode=true&characterEncoding=utf-8";
	private String user="root";
	private String password="root";
	private String ClassName="com.mysql.jdbc.Driver";
	private Connection con;
	/**
	 * 获取连接
	 * @return
	 */
	public Connection getcon() {
		try {
			//
			Class.forName(ClassName);
			con = DriverManager.getConnection(url, user, password);
			System.out.println("连接数据库成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	/**
	 * 关闭连接
	 * @param con
	 */
	public static void closeCon(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				con = null;
			}
		}
	}
}

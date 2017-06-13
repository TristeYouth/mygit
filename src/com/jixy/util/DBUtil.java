package com.jixy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	private Connection conn = null; // 数据库连接对象
	private Statement st = null;// 执行sql语句的对象
	private ResultSet rs = null;// 存放查询结果的对象

	/**
	 * 获得ResultSet
	 * @return ResultSet
	 */
	public ResultSet getRs() {
		return rs;
	}
	
	/**
	 * 获得数据库连接
	 */
	private void getConnection() {
		final String DRIVERNAME = "com.mysql.jdbc.Driver";
		final String URL = "jdbc:mysql://localhost:3306/test3?characterEncoding=utf-8";
		final String LOGIN = "root";// 数据库管理员用户名
		final String PASSWORD = "123456";// 数据库密码
		try {
			Class.forName(DRIVERNAME); // 加载驱动程序
			conn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
		} catch (Exception e) {
			System.out.println("执行getConnection()方法出错：");
			e.printStackTrace();
		}
	}
	
	public DBUtil(){
		getConnection();
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 数据库查询
	 * @param sql 传入的SQL语句
	 */
public void executeQuery(String sql){
		try{
			rs=st.executeQuery(sql);//执行SQL语句获得结果集
		}catch(Exception e){
			System.out.println("执行executeQuery(String sql)方法出错："+sql);			e.printStackTrace();//打印出错误原因
		}
}
/**
 * 数据库增加/删除/修改
 * @param sql 传入的SQL语句
 * @return >0执行成功
 */
public int executeUpdate(String sql){
	int ret=-1;
	try{
		ret=st.executeUpdate(sql);//执行SQL语句对数据表进行操作，变量ret存放对表修改了多少行
	}catch(Exception e){
		System.out.println("执行executeUpdate(sql)方法出错："+sql);
		e.printStackTrace();//打印出错误原因
	} finally{
		close();
	}
	return ret;

}

/**
 * 数据库关闭
 */
public void close(){
	try {
	if (rs!=null){
		rs.close();//关闭结果集
	}
	if (st!=null){
		st.close();//关闭Statement对象
	}
	if (conn!=null){
		conn.close();//关闭连接
	}
}catch(Exception e){
		System.out.println("执行close()方法出错：");
		e.printStackTrace();
	}
}

}

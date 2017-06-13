package com.jixy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	private Connection conn = null; // ���ݿ����Ӷ���
	private Statement st = null;// ִ��sql���Ķ���
	private ResultSet rs = null;// ��Ų�ѯ����Ķ���

	/**
	 * ���ResultSet
	 * @return ResultSet
	 */
	public ResultSet getRs() {
		return rs;
	}
	
	/**
	 * ������ݿ�����
	 */
	private void getConnection() {
		final String DRIVERNAME = "com.mysql.jdbc.Driver";
		final String URL = "jdbc:mysql://localhost:3306/test3?characterEncoding=utf-8";
		final String LOGIN = "root";// ���ݿ����Ա�û���
		final String PASSWORD = "123456";// ���ݿ�����
		try {
			Class.forName(DRIVERNAME); // ������������
			conn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
		} catch (Exception e) {
			System.out.println("ִ��getConnection()��������");
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
	 * ���ݿ��ѯ
	 * @param sql �����SQL���
	 */
public void executeQuery(String sql){
		try{
			rs=st.executeQuery(sql);//ִ��SQL����ý����
		}catch(Exception e){
			System.out.println("ִ��executeQuery(String sql)��������"+sql);			e.printStackTrace();//��ӡ������ԭ��
		}
}
/**
 * ���ݿ�����/ɾ��/�޸�
 * @param sql �����SQL���
 * @return >0ִ�гɹ�
 */
public int executeUpdate(String sql){
	int ret=-1;
	try{
		ret=st.executeUpdate(sql);//ִ��SQL�������ݱ���в���������ret��ŶԱ��޸��˶�����
	}catch(Exception e){
		System.out.println("ִ��executeUpdate(sql)��������"+sql);
		e.printStackTrace();//��ӡ������ԭ��
	} finally{
		close();
	}
	return ret;

}

/**
 * ���ݿ�ر�
 */
public void close(){
	try {
	if (rs!=null){
		rs.close();//�رս����
	}
	if (st!=null){
		st.close();//�ر�Statement����
	}
	if (conn!=null){
		conn.close();//�ر�����
	}
}catch(Exception e){
		System.out.println("ִ��close()��������");
		e.printStackTrace();
	}
}

}

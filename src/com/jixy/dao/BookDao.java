package com.jixy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jixy.po.Book;
import com.jixy.util.DBUtil;

//  4������BookDao��,�������com.zdsoft.dao����
public class BookDao {
	//����DBUtil�� ,�����������鼮��Ϣ���в�ѯ��ɾ������,
	DBUtil db = null;
	public BookDao () {
		db = new DBUtil();
	}

	//1)	��������ͨ������������ѯ�౾�鼮,ע:��ѯ�������鼮��Ϣ��װ��Book�����ﲢ�ҷ��ڼ�����
	public List<Book> findBooks(String authorName){
		List < Book > list = new ArrayList< Book >();
		String sql = "select * from book where author_name='" + authorName + "'";
		db.executeQuery(sql);
		ResultSet res = db.getRs();
		try {
			while (res.next()) {
				Book b = new Book();
				b.setBookId(res.getString("book_id"));
				b.setAuthorName(res.getString("author_name"));
				b.setBookName(res.getString("book_name"));
				b.setPublish(res.getString("publish"));
				b.setPrice(res.getDouble("price"));
				list.add(b);
			}
		} catch (SQLException e) {
		}finally{
			//db.close();
}
		return list;

	}
	
	//2)	�ܹ�ͨ��Idɾ���鼮
	public void deleteBook(String book_id){
		String sql = "delete from book where book_id='" +book_id + "'";
		if (db.executeUpdate(sql) > 0) {
			System.out.println("����ɾ���ɹ�");
		} else {
			System.out.println("����ɾ��ʧ��");
		}
	}
}

package com.jixy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jixy.po.Book;
import com.jixy.util.DBUtil;

//  4．建立BookDao类,该类放在com.zdsoft.dao包下
public class BookDao {
	//利用DBUtil类 ,建立方法对书籍信息进行查询与删除操作,
	DBUtil db = null;
	public BookDao () {
		db = new DBUtil();
	}

	//1)	建立方法通过作者姓名查询多本书籍,注:查询出来的书籍信息封装在Book对象里并且放在集合中
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
	
	//2)	能够通过Id删除书籍
	public void deleteBook(String book_id){
		String sql = "delete from book where book_id='" +book_id + "'";
		if (db.executeUpdate(sql) > 0) {
			System.out.println("数据删除成功");
		} else {
			System.out.println("数据删除失败");
		}
	}
}

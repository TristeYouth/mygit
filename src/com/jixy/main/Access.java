package com.jixy.main;

import java.util.List;

import com.jixy.dao.BookDao;
import com.jixy.po.Book;

public class Access {
	public static void main(String[] args) {
		BookDao dao = new BookDao();
		List<Book> books = dao.findBooks("ÕÅ»ùÎÂ");
		for(Book b: books){
			System.out.print(b.getBookId());
			System.out.print(b.getBookName());
			System.out.print(b.getAuthorName());
			System.out.println(b.getPublish());
			System.out.println(b.getPrice());
		}
		dao.deleteBook("003");
	}

}

package web.com.service;

import web.com.pojo.Book;

import java.util.List;

/**
 * Created by Wan Yu on 2020/4/6
 * 书籍管理
 */
public interface BookService {
    //添加书
    void addBook(Book book);

    //查询书
    Book findBookByID(String id);

    List<Book> findBookByAuthor(String author);

    List<Book> findBookByName(String name);

    List<Book> findAllBook();

    //删除书
    void deleteBook(String id);

    List<Book> getBookRandom();
}

package web.com.dao;

import web.com.pojo.Book;
import java.util.List;

/**
 * Created by Wan Yu on 2020/3/11
 */
public interface BookDao {
    //添加书籍
    void addBook(Book book);
    //按id查找书籍
    Book findBookByID(String id);
    //按书名查找书籍
    List<Book> findBookByName(String name);
    //按作者查找书籍
    List<Book> findBookByAuthor(String author);
    //查找全部书籍
    List<Book> findAllBook();
    //删除书籍
    void deleteBook(String id);
    //随机获取书籍
    List<Book> getBookRandom();



}

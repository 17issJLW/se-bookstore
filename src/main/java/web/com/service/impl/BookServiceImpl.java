package web.com.service.impl;

import web.com.dao.BookDao;
import web.com.pojo.Book;
import web.com.service.BookService;
import web.com.utils.DaoFactory;

import java.util.List;

/**
 * Created by Wan Yu on 2020/4/6
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = DaoFactory.getInstance().createDao("com.dao.impl.BookDaoImpl",BookDao.class);


    //添加书
    public void addBook(Book book){
        bookDao.addBook(book);
    }

    //查询书byID
    public Book findBookByID(String id) {
        return bookDao.findBookByID(id);
    }

    //ByAuthor
    public List<Book> findBookByAuthor(String author) {
        return bookDao.findBookByAuthor(author);
    }

    public List<Book> findBookByName(String name) {
        return bookDao.findBookByName(name);
    }

    public List<Book> findAllBook() {
        return bookDao.findAllBook();
    }

    //删除书籍
    public void deleteBook(String id) {
        bookDao.deleteBook(id);
    }

    public List<Book> getBookRandom() {
        return bookDao.getBookRandom();
    }
}

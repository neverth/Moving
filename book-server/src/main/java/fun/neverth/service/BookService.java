package fun.neverth.service;

import fun.neverth.bean.form.BookForm;
import fun.neverth.bean.entity.Book;
import fun.neverth.bean.vo.BookVO;
import fun.neverth.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/15 22:28
 */
@Service
public class BookService {

    @Resource
    private BookRepository bookRepository;

    public List<BookVO> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        List<BookVO> bookVOList = new ArrayList<>();

        for (Book book : bookList) {
            BookVO bookVO = new BookVO();
            BeanUtils.copyProperties(book, bookVO);
            bookVOList.add(bookVO);
        }

        return bookVOList;
    }

    public BookVO getBookById(Long id) {
        Optional<Book> optional = bookRepository.findById(id);

        if (optional.isPresent()) {
            Book book = optional.get();

            BookVO bookVO = new BookVO();

            BeanUtils.copyProperties(book, bookVO);

            return bookVO;

        } else {

            return null;
        }
    }

    public int updateBookAmount(Long id, int amount) {
        Optional<Book> optional = bookRepository.findById(id);

        Book book;
        if (optional.isPresent()) {
            book = optional.get();

            book.setAmount(amount);

            return bookRepository.save(book).getAmount();

        } else {

            return -1;
        }
    }

    public int addBookAmount(Long id) {
        Optional<Book> optional = bookRepository.findById(id);

        Book book;
        if (optional.isPresent()) {
            book = optional.get();

            book.setAmount(book.getAmount() + 1);

            return bookRepository.save(book).getAmount();

        } else {
            return -1;
        }
    }

    public int reduceBookAmount(Long id) {
        Optional<Book> optional = bookRepository.findById(id);

        Book book;
        if (optional.isPresent()) {
            book = optional.get();

            book.setAmount(book.getAmount() - 1);

            return bookRepository.save(book).getAmount();

        } else {

            return -1;
        }
    }

    public BookVO updateBook(BookForm bookForm) {
        if (bookForm != null){
            Book book = new Book();
            BeanUtils.copyProperties(bookForm, book);

            Book save = bookRepository.save(book);

            BookVO bookVO = new BookVO();
            BeanUtils.copyProperties(save, bookVO);

            return bookVO;
        }

        return null;
    }

    public BookVO addBook(BookForm form){
        if (form != null){
            Book book = new Book();
            BeanUtils.copyProperties(form, book);
            Book save = bookRepository.save(book);

            BookVO bookVO = new BookVO();
            BeanUtils.copyProperties(save, bookVO);

            return bookVO;
        }

        return null;
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
}

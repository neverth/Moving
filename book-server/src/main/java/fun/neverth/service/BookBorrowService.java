package fun.neverth.service;

import fun.neverth.bean.entity.BookBorrow;
import fun.neverth.bean.form.BookBorrowForm;
import fun.neverth.bean.vo.BookBorrowVO;
import fun.neverth.repository.BookBorrowRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/17 11:20
 */
@Service
public class BookBorrowService {

    @Resource
    BookBorrowRepository bookBorrowRepository;

    @Resource
    BookService bookService;

    @Resource
    UserService userService;

    public List<BookBorrowVO> getAllBookBorrow() {
        List<BookBorrow> bookBorrows = bookBorrowRepository.findAll();
        List<BookBorrowVO> borrowVOS = new ArrayList<>();

        for (BookBorrow bookBorrow : bookBorrows) {
            if (bookBorrow.getHadReturn() == 1){
                continue;
            }
            BookBorrowVO borrowVO = new BookBorrowVO();
            BeanUtils.copyProperties(bookBorrow, borrowVO);

            borrowVO.setBookVO(bookService.getBookById(bookBorrow.getBookId()));
            borrowVO.setUserVO(userService.getUserById(bookBorrow.getUserId()));

            borrowVOS.add(borrowVO);
        }

        return borrowVOS;
    }

    public List<BookBorrowVO> getBookBorrowByUserIdAndBookId(Long userId, Long bookId) {
        List<BookBorrow> bookBorrows =
                bookBorrowRepository.getBookBorrowListByUserIdBookId(userId, bookId);
        List<BookBorrowVO> borrowVOS = new ArrayList<>();

        for (BookBorrow bookBorrow : bookBorrows) {
            if (bookBorrow.getHadReturn() == 1){
                continue;
            }
            BookBorrowVO borrowVO = new BookBorrowVO();
            BeanUtils.copyProperties(bookBorrow, borrowVO);
            borrowVOS.add(borrowVO);
        }

        return borrowVOS;
    }

    public BookBorrowVO returnBook(Long userId, Long bookId) {
        List<BookBorrow> bookBorrows =
                bookBorrowRepository.getBookBorrowListByUserIdBookId(userId, bookId);

        if (bookBorrows.size() != 1){
            return null;
        }

        BookBorrow bookBorrow1 = bookBorrows.get(0);
        bookBorrow1.setHadReturn(1);

        BookBorrow save = bookBorrowRepository.save(bookBorrow1);

        BookBorrowVO borrowVO = new BookBorrowVO();

        BeanUtils.copyProperties(save, borrowVO);

        return borrowVO;

    }

    public BookBorrowVO borrowBook(Long userId, Long bookId){
        BookBorrow bookBorrow = new BookBorrow();

        bookBorrow.setUserId(userId);
        bookBorrow.setBookId(bookId);
        bookBorrow.setHadReturn(0);
        bookBorrow.setNeedReturn(0);

        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        bookBorrow.setDate(sdf.format(new Date()));

        BookBorrow save = bookBorrowRepository.save(bookBorrow);
        BookBorrowVO borrowVO = new BookBorrowVO();
        BeanUtils.copyProperties(save, borrowVO);

        return borrowVO;
    }

    public BookBorrowVO updateBorrow(BookBorrowForm form) {
        if (form != null){
            BookBorrow bookBorrow = new BookBorrow();
            BeanUtils.copyProperties(form, bookBorrow);

            BookBorrow save = bookBorrowRepository.save(bookBorrow);

            BookBorrowVO borrowVO = new BookBorrowVO();
            BeanUtils.copyProperties(save, borrowVO);

            return borrowVO;
        }

        return null;
    }
}

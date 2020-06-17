package fun.neverth.controller;

import fun.neverth.bean.vo.BookBorrowVO;
import fun.neverth.service.BookBorrowService;
import fun.neverth.service.BookService;
import fun.neverth.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/17 11:24
 */
@RestController
@RequestMapping("/bookBorrow")
public class BookBorrowController {
    @Resource
    BookBorrowService bookBorrowService;

    @Resource
    BookService bookService;

    @GetMapping("/get")
    public Result<List<BookBorrowVO>> getBookBorrowByUserIdAndBookId(
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "bookId", required = false) String bookId
    ){
        List<BookBorrowVO> borrowVOS;
        try {
            if (userId == null || bookId == null) {
                borrowVOS = bookBorrowService.getAllBookBorrow();

            } else {
                borrowVOS = bookBorrowService.getBookBorrowByUserIdAndBookId(
                        Long.parseLong(userId),
                        Long.parseLong(bookId)
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

        if (borrowVOS != null) {
            return Result.success(borrowVOS);
        }

        return Result.error();
    }

    @GetMapping("/return")
    public Result<BookBorrowVO> returnBook(
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "bookId", required = false) String bookId
    ){
        BookBorrowVO borrowVO = bookBorrowService.returnBook(
                Long.parseLong(userId),
                Long.parseLong(bookId)
        );

        if (borrowVO != null) {
            if (bookService.addBookAmount(borrowVO.getBookId()) != -1){
                return Result.success(borrowVO);
            }
        }

        return Result.error();
    }

    @GetMapping("/add")
    public Result<BookBorrowVO> borrowBook(
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "bookId", required = false) String bookId
    ){
        BookBorrowVO borrowVO = bookBorrowService.borrowBook(
                Long.parseLong(userId),
                Long.parseLong(bookId)
        );

        if (borrowVO != null) {
            if (bookService.reduceBookAmount(borrowVO.getBookId()) != -1){
                return Result.success(borrowVO);
            }
        }

        return Result.error();
    }
}

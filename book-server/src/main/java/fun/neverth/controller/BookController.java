package fun.neverth.controller;

import fun.neverth.bean.form.BookForm;
import fun.neverth.bean.vo.BookVO;
import fun.neverth.service.BookService;
import fun.neverth.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/15 20:45
 */

@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    @GetMapping("/get")
    public Result<List<BookVO>> getBook(
            @RequestParam(value = "id", required = false) String id
    ) {
        List<BookVO> bookVOs;
        try {
            if (id == null) {
                bookVOs = bookService.getAllBooks();

            } else {
                bookVOs = new ArrayList<>();
                bookVOs.add(bookService.getBookById(Long.parseLong(id)));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

        if (bookVOs != null) {
            return Result.success(bookVOs);
        }

        return Result.error();
    }

    @PostMapping("/add")
    public Result<BookVO> addBook(BookForm bookForm) {
        BookVO bookVO;
        try {
            bookVO = bookService.addBook(bookForm);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

        if (bookVO != null) {
            return Result.success(bookVO);
        }

        return Result.error();
    }

    @PostMapping("/update")
    public Result<BookVO> updateBook(BookForm bookForm) {
        BookVO bookVO;
        try {
            bookVO = bookService.updateBook(bookForm);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

        if (bookVO != null) {
            return Result.success(bookVO);
        }

        return Result.error();
    }

    @PostMapping("/delete")
    public Result<String> deleteBook(@RequestParam("id") String id) {
        try {
            bookService.deleteBook(Long.parseLong(id));

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

        return Result.success();
    }

}

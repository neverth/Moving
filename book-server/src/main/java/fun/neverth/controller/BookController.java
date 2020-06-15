package fun.neverth.controller;

import fun.neverth.bean.po.BookDO;
import fun.neverth.bean.vo.BookVO;
import fun.neverth.repository.BookRepository;
import fun.neverth.service.BookService;
import fun.neverth.util.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/15 20:45
 */

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @GetMapping("/get")
    public BookVO getBook(){
        BookVO bookVO = new BookVO();
        List<BookDO> bookDOList = bookRepository.findAll();

        BeanUtils.copyProperties(bookDOList.get(0), bookVO);

        return bookVO;
    }

}

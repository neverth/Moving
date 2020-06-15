package fun.neverth.service;

import fun.neverth.bean.vo.BookVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/15 23:35
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookServiceTest {

    @Autowired
    BookService bookService;

    @Test
    public void getAllBooks(){
        List<BookVO> allBooks = bookService.getAllBooks();
        System.out.println(allBooks);
    }

    @Test
    public void a(){
        long id = 2;
        BookVO bookById = bookService.getBookById(id);
        System.out.println(bookById);
    }

}

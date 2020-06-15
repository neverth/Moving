package fun.neverth.service;

import fun.neverth.bean.po.BookDO;
import fun.neverth.bean.vo.BookVO;
import fun.neverth.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private BookRepository bookRepository;

    public List<BookVO> getAllBooks() {
        List<BookDO> bookDOList = bookRepository.findAll();
        List<BookVO> bookVOList = new ArrayList<>();


        for (BookDO bookDo : bookDOList) {
            BookVO bookVO = new BookVO();
            BeanUtils.copyProperties(bookDo, bookVO);
            bookVOList.add(bookVO);
        }

        return bookVOList;
    }

    public BookVO getBookById(Long id) {
        Optional<BookDO> optional = bookRepository.findById(id);

        BookVO bookVO = new BookVO();

        optional.ifPresent(bookDO -> BeanUtils.copyProperties(bookDO, bookVO));

        return bookVO;
    }

    public int updateBookAmount(Long id, int amount) {
        Optional<BookDO> optional = bookRepository.findById(id);

        BookDO bookDO;
        if (optional.isPresent()) {
            bookDO = optional.get();

            bookDO.setAmount(amount);

            return bookRepository.save(bookDO).getAmount();

        } else {

            return -1;
        }
    }
}

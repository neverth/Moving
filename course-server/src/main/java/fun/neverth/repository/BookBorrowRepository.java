package fun.neverth.repository;

import fun.neverth.bean.entity.BookBorrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/17 11:20
 */
@Repository
public interface BookBorrowRepository extends JpaRepository<BookBorrow, Long> {

    @Query(value = "from bookBorrow where userId = ?1 and bookId = ?2 and hadReturn = 0")
    List<BookBorrow> getBookBorrowListByUserIdBookId(Long userId, Long bookId);
}

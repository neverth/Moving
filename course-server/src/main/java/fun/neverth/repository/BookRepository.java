package fun.neverth.repository;

import fun.neverth.bean.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/15 20:31
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}

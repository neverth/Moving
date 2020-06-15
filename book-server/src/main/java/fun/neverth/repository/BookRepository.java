package fun.neverth.repository;

import fun.neverth.bean.po.BookDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/15 20:31
 */

@Repository
public interface BookRepository extends JpaRepository<BookDO, Long> {

}

package fun.neverth.repository;

import fun.neverth.bean.entity.Discuss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/16 10:38
 */
@Repository
public interface DiscussRepository extends JpaRepository<Discuss, Long> {

    @Query(value = "from discuss where bookId = ?1")
    List<Discuss> getDiscussListByBookId(Long id);

    @Query(value = "from discuss where userId = ?1")
    List<Discuss> getDiscussListByUserId(Long id);

}

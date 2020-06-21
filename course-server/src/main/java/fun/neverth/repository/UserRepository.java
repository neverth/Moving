package fun.neverth.repository;

import fun.neverth.bean.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/16 10:18
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "from user where name = ?1")
    List<User> getUserListByName(String name);
}

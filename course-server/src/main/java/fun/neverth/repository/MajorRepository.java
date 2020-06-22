package fun.neverth.repository;

import fun.neverth.bean.entity.Major;
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
public interface MajorRepository extends JpaRepository<Major, Long> {

    @Query(value = "from major where userId = ?1 and courseId = ?2")
    List<Major> getMajorListByUserIdCourseId(Long userId, Long courseId);

    @Query(value = "from major where userId = ?1 and flag = 1")
    List<Major> getMajorListByUserId(Long userId);
}

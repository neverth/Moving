package fun.neverth.repository;

import fun.neverth.bean.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/22 12:02
 */
@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

}

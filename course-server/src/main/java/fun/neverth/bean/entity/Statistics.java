package fun.neverth.bean.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/22 11:59
 */
@Data
@Entity(name = "statistics")
@Table(name = "statistics")
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private Float credit;

    @Column(name = "select_num")
    private Integer selectNum;

    private Integer pass;

    private Integer fail;
}

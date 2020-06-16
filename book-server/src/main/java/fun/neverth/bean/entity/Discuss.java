package fun.neverth.bean.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/16 10:33
 */
@Data
@Entity(name = "discuss")
@Table(name = "discuss")
public class Discuss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "user_id")
    private Long userId;

    private String comment;

    private String date;

}

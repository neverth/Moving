package fun.neverth.bean.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/17 11:16
 */
@Data
@Entity(name = "bookBorrow")
@Table(name = "book_borrow")
public class BookBorrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "user_id")
    private Long userId;

    private String date;

    @Column(name = "need_return")
    private Integer needReturn;

    @Column(name = "had_return")
    private Integer hadReturn;
}

package fun.neverth.bean.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/15 19:55
 */
@Data
@Entity(name = "book")
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @Column(name = "book_name")
    private String bookName;

    private String isbn;

    private String author;

    private String press;

    @Column(name = "pub_date")
    private String pubDate;

    private String price;

    private Integer amount;
}

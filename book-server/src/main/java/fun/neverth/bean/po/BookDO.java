package fun.neverth.bean.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/15 19:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "book")
@Table(name = "t_book")
public class BookDO {
    @Id
    @GeneratedValue
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

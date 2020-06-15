package fun.neverth.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/15 20:46
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookVO {

    private Long id;

    private String number;

    private String bookName;

    private String isbn;

    private String author;

    private String press;

    private String pubDate;

    private String price;

    private Integer amount;
}

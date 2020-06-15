package fun.neverth.bean.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/16 1:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookForm {

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

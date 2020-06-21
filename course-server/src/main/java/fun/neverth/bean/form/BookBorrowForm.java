package fun.neverth.bean.form;

import lombok.Data;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/17 11:16
 */
@Data
public class BookBorrowForm {

    private Long id;

    private Long bookId;

    private Long userId;

    private String date;

    private Integer needReturn;

    private Integer hadReturn;
}

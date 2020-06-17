package fun.neverth.bean.vo;

import lombok.Data;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/17 11:16
 */
@Data
public class BookBorrowVO {

    private Long id;

    private Long bookId;

    private BookVO bookVO;

    private Long userId;

    private UserVO userVO;

    private String date;

    private Integer needReturn;

    private Integer hadReturn;
}

package fun.neverth.bean.vo;

import lombok.Data;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/16 10:33
 */
@Data
public class DiscussVO {

    private Long id;

    private Long bookId;

    private BookVO bookVO;

    private Long userId;

    private UserVO userVO;

    private String comment;

    private String date;

}

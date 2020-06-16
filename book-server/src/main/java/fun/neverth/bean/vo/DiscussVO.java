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

    private Long userId;

    private String comment;

    private String date;

}

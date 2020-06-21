package fun.neverth.bean.form;

import lombok.Data;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/16 10:33
 */
@Data
public class DiscussForm {

    private Long id;

    private Long bookId;

    private Long userId;

    private String comment;

    private String date;

}

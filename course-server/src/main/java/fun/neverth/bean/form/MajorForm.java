package fun.neverth.bean.form;

import lombok.Data;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/17 11:16
 */
@Data
public class MajorForm {
    private Long id;

    private Long userId;

    private Long courseId;

    private Float record;

    private String startDate;

    private String endDate;

    private int flag;
}

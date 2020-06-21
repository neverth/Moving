package fun.neverth.bean.vo;

import lombok.Data;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/17 11:16
 */
@Data
public class MajorVO {

    private Long id;

    private Long userId;

    private UserVO userVO;

    private Long courseId;

    private CourseVO courseVO;

    private Float record;

    private String startDate;

    private String endDate;

    private int flag;
}

package fun.neverth.bean.vo;

import lombok.Data;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/15 20:46
 */

@Data
public class CourseVO {
    private Long id;

    private String number;

    private String name;

    private Float credit;

    private String theoryTime;

    private String practiceTime;

    private String examType;
}

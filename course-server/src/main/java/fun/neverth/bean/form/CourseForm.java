package fun.neverth.bean.form;

import lombok.Data;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/16 1:31
 */
@Data
public class CourseForm {

    private Long id;

    private String number;

    private String name;

    private Float credit;

    private String theoryTime;

    private String practiceTime;

    private String examType;
}

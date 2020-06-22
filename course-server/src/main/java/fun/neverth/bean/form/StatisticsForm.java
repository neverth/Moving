package fun.neverth.bean.form;

import lombok.Data;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/22 11:59
 */
@Data
public class StatisticsForm {

    private Long id;

    private Long userId;

    private Float credit;

    private Integer selectNum;

    private Integer pass;

    private Integer fail;
}

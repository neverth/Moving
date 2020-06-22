package fun.neverth.bean.vo;

import lombok.Data;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/22 11:59
 */
@Data
public class StatisticsVO {

    private Long id;

    private Long userId;

    private Float credit;

    private Integer selectNum;

    private Integer pass;

    private Integer fail;

    private UserVO userVO;
}

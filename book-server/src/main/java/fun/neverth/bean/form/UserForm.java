package fun.neverth.bean.form;

import lombok.Data;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/16 10:25
 */
@Data
public class UserForm {
    private Long id;

    private String name;

    private String password;

    private int role;
}
package fun.neverth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/5/20 14:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User{
    private int id;
    private String name;
}

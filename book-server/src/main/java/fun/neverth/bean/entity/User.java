package fun.neverth.bean.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/16 10:16
 */
@Data
@Entity(name = "user")
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private int role;
}

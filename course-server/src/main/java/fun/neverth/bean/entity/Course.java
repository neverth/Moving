package fun.neverth.bean.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/15 19:55
 */
@Data
@Entity(name = "course")
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private String name;

    private Float credit;

    @Column(name = "theory_time")
    private String theoryTime;

    @Column(name = "practice_time")
    private String practiceTime;

    @Column(name = "exam_type")
    private String examType;
}

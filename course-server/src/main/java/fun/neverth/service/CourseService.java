package fun.neverth.service;

import fun.neverth.bean.entity.Course;
import fun.neverth.bean.form.CourseForm;
import fun.neverth.bean.vo.CourseVO;
import fun.neverth.repository.CourseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/15 22:28
 */
@Service
public class CourseService {

    @Resource
    private CourseRepository courseRepository;

    public List<CourseVO> getAllCourse() {
        List<Course> courseList = courseRepository.findAll();
        List<CourseVO> courseVOList = new ArrayList<>();

        for (Course course : courseList) {
            CourseVO courseVO = new CourseVO();
            BeanUtils.copyProperties(course, courseVO);
            courseVOList.add(courseVO);
        }

        return courseVOList;
    }

    public CourseVO getCourseById(Long id) {
        Optional<Course> optional = courseRepository.findById(id);

        if (optional.isPresent()) {
            Course course = optional.get();

            CourseVO courseVO = new CourseVO();

            BeanUtils.copyProperties(course, courseVO);

            return courseVO;

        } else {

            return null;
        }
    }

    public CourseVO updateCourse(CourseForm courseForm) {
        if (courseForm != null){
            Course course = new Course();
            BeanUtils.copyProperties(courseForm, course);

            Course save = courseRepository.save(course);

            CourseVO courseVO = new CourseVO();
            BeanUtils.copyProperties(save, courseVO);

            return courseVO;
        }

        return null;
    }

    public CourseVO addCourse(CourseForm form){
        if (form != null){
            Course course = new Course();
            BeanUtils.copyProperties(form, course);
            Course save = courseRepository.save(course);

            CourseVO courseVO = new CourseVO();
            BeanUtils.copyProperties(save, courseVO);

            return courseVO;
        }

        return null;
    }

    public void deleteCourse(Long id){
        courseRepository.deleteById(id);
    }
}

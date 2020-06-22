package fun.neverth.controller;

import fun.neverth.bean.form.CourseForm;
import fun.neverth.bean.vo.CourseVO;
import fun.neverth.service.CourseService;
import fun.neverth.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/15 20:45
 */

@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    @GetMapping("/get")
    public Result<List<CourseVO>> getCourse(
            @RequestParam(value = "id", required = false) String id
    ) {
        List<CourseVO> courseVOS;
        try {
            if (id == null) {
                courseVOS = courseService.getAllCourse();

            } else {
                courseVOS = new ArrayList<>();
                courseVOS.add(courseService.getCourseById(Long.parseLong(id)));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

        if (courseVOS != null) {
            return Result.success(courseVOS);
        }

        return Result.error();
    }

    @GetMapping("/getWithState")
    public Result<List<CourseVO>> getWithState(
            @RequestParam(value = "userId") String id
    ) {
        List<CourseVO> courseVOS;
        try {
            courseVOS =
                    courseService.getAllCourseWithMajorStateByUserId(Long.parseLong(id));

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

        if (courseVOS != null) {
            return Result.success(courseVOS);
        }

        return Result.error();
    }

    @PostMapping("/add")
    public Result<CourseVO> addCourse(CourseForm courseForm) {
        CourseVO courseVO;
        try {
            courseVO = courseService.addCourse(courseForm);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

        if (courseVO != null) {
            return Result.success(courseVO);
        }

        return Result.error();
    }

    @PostMapping("/update")
    public Result<CourseVO> updateCourse(@RequestBody CourseForm courseForm) {
        CourseVO courseVO;
        try {
            courseVO = courseService.updateCourse(courseForm);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

        if (courseVO != null) {
            return Result.success(courseVO);
        }

        return Result.error();
    }

    @GetMapping("/delete")
    public Result<String> deleteCourse(@RequestParam("id") String id) {
        try {
            courseService.deleteCourse(Long.parseLong(id));

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

        return Result.success();
    }

}

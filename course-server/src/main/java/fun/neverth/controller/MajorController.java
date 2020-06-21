package fun.neverth.controller;

import fun.neverth.bean.form.MajorForm;
import fun.neverth.bean.vo.MajorVO;
import fun.neverth.service.MajorService;
import fun.neverth.service.CourseService;
import fun.neverth.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/17 11:24
 */
@RestController
@RequestMapping("/major")
public class MajorController {
    @Resource
    MajorService majorService;

    @Resource
    CourseService courseService;

    @GetMapping("/get")
    public Result<List<MajorVO>> getMajorByUserIdAndCourseId(
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "courseId", required = false) String courseId
    ){
        List<MajorVO> majorVOS;
        try {
            if (userId == null || courseId == null) {
                majorVOS = majorService.getAllMajor();

            } else {
                majorVOS = majorService.getMajorByUserIdAndCourseId(
                        Long.parseLong(userId),
                        Long.parseLong(courseId)
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

        if (majorVOS != null) {
            return Result.success(majorVOS);
        }

        return Result.error();
    }

    @GetMapping("/cancelMajor")
    public Result<MajorVO> cancelMajor(
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "courseId", required = false) String courseId
    ){
        MajorVO majorVO = majorService.cancelCourse(
                Long.parseLong(userId),
                Long.parseLong(courseId)
        );

        if (majorVO != null) {
            return Result.success(majorVO);
        }

        return Result.error();
    }

    @GetMapping("/selectMajor")
    public Result<MajorVO> selectMajor(
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "courseId", required = false) String courseId
    ){
        MajorVO majorVO = majorService.selectCourse(
                Long.parseLong(userId),
                Long.parseLong(courseId)
        );

        if (majorVO != null) {
            return Result.success(majorVO);
        }

        return Result.error();
    }

    @PostMapping("/update")
    public Result<MajorVO> updateMajor(@RequestBody MajorForm form) {
        MajorVO majorVO;
        try {
            majorVO = majorService.updateMajor(form);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

        if (majorVO != null) {
            return Result.success(majorVO);
        }

        return Result.error();
    }
}

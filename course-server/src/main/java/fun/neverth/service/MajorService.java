package fun.neverth.service;

import fun.neverth.bean.entity.Major;
import fun.neverth.bean.form.MajorForm;
import fun.neverth.bean.vo.MajorVO;
import fun.neverth.repository.MajorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/17 11:20
 */
@Service
public class MajorService {

    @Resource
    MajorRepository majorRepository;

    @Resource
    CourseService courseService;

    @Resource
    UserService userService;

    public List<MajorVO> getAllMajor() {
        List<Major> majors = majorRepository.findAll();
        List<MajorVO> majorVOArrayList = new ArrayList<>();

        for (Major major : majors) {

            MajorVO majorVO = new MajorVO();
            BeanUtils.copyProperties(major, majorVO);

            majorVO.setCourseVO(courseService.getCourseById(major.getCourseId()));
            majorVO.setUserVO(userService.getUserById(major.getUserId()));

            majorVOArrayList.add(majorVO);
        }

        return majorVOArrayList;
    }

    public List<MajorVO> getMajorByUserIdAndCourseId(Long userId, Long courseId) {
        List<Major> majors =
                majorRepository.getMajorListByUserIdCourseId(userId, courseId);
        List<MajorVO> majorVOArrayList = new ArrayList<>();

        for (Major major : majors) {

            MajorVO majorVO = new MajorVO();
            BeanUtils.copyProperties(major, majorVO);
            majorVOArrayList.add(majorVO);
        }

        return majorVOArrayList;
    }

    public List<MajorVO> getMajorByUserId(Long userId) {
        List<Major> majors =
                majorRepository.getMajorListByUserId(userId);
        List<MajorVO> majorVOArrayList = new ArrayList<>();

        for (Major major : majors) {

            MajorVO majorVO = new MajorVO();
            BeanUtils.copyProperties(major, majorVO);
            majorVOArrayList.add(majorVO);
        }

        return majorVOArrayList;
    }

    public MajorVO cancelCourse(Long userId, Long courseId) {
        List<Major> majors =
                majorRepository.getMajorListByUserIdCourseId(userId, courseId);

        if (majors.size() != 1){
            return null;
        }

        Major major1 = majors.get(0);
        majorRepository.deleteById(major1.getId());

        MajorVO majorVO = new MajorVO();

        BeanUtils.copyProperties(major1, majorVO);

        return majorVO;
    }

    public MajorVO selectCourse(Long userId, Long courseId){
        Major major = new Major();

        major.setUserId(userId);
        major.setCourseId(courseId);
        major.setFlag(1);

        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        major.setStartDate(sdf.format(new Date()));

        Major save = majorRepository.save(major);
        MajorVO majorVO = new MajorVO();
        BeanUtils.copyProperties(save, majorVO);

        return majorVO;
    }

    public MajorVO updateMajor(MajorForm form) {
        if (form != null){
            Major major = new Major();
            BeanUtils.copyProperties(form, major);

            Major save = majorRepository.save(major);

            MajorVO majorVO = new MajorVO();
            BeanUtils.copyProperties(save, majorVO);

            return majorVO;
        }

        return null;
    }
}

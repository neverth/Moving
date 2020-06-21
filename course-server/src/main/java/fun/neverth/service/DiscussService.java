package fun.neverth.service;

import fun.neverth.bean.entity.Discuss;
import fun.neverth.bean.form.DiscussForm;
import fun.neverth.bean.vo.CourseVO;
import fun.neverth.bean.vo.DiscussVO;
import fun.neverth.bean.vo.UserVO;
import fun.neverth.repository.DiscussRepository;
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
 * @date 2020/6/16 10:42
 */
@Service
public class DiscussService {
    @Resource
    private DiscussRepository discussRepository;

    @Resource
    private CourseService courseService;

    @Resource
    private UserService userService;

    public List<DiscussVO> getAllDiscuss() {
        List<Discuss> discussList = discussRepository.findAll();
        List<DiscussVO> discussVOs = new ArrayList<>();

        for (Discuss discuss : discussList) {
            DiscussVO discussVO = new DiscussVO();
            BeanUtils.copyProperties(discuss, discussVO);

            UserVO user = userService.getUserById(discuss.getUserId());
            CourseVO book = courseService.getCourseById(discuss.getBookId());

            user.setPassword(null);
            discussVO.setCourseVO(book);
            discussVO.setUserVO(user);

            discussVOs.add(discussVO);
        }

        return discussVOs;
    }

    public DiscussVO getDiscussById(Long id) {
        Optional<Discuss> optional = discussRepository.findById(id);

        if (optional.isPresent()) {
            Discuss discuss = optional.get();

            DiscussVO discussVO = new DiscussVO();

            BeanUtils.copyProperties(discuss, discussVO);

            return discussVO;

        } else {

            return null;
        }
    }

    public List<DiscussVO> getDiscussByBookId(Long id) {
        List<Discuss> discussList = discussRepository.getDiscussListByBookId(id);
        List<DiscussVO> discussVOs = new ArrayList<>();

        for (Discuss discuss : discussList) {
            DiscussVO discussVO = new DiscussVO();
            BeanUtils.copyProperties(discuss, discussVO);

            UserVO user = userService.getUserById(discuss.getUserId());
            user.setPassword(null);
            CourseVO book = courseService.getCourseById(discuss.getBookId());

            discussVO.setCourseVO(book);
            discussVO.setUserVO(user);
            discussVOs.add(discussVO);
        }

        return discussVOs;
    }

    public List<DiscussVO> getDiscussByUserId(Long id) {
        List<Discuss> discussList = discussRepository.getDiscussListByUserId(id);
        List<DiscussVO> discussVOs = new ArrayList<>();

        for (Discuss discuss : discussList) {
            DiscussVO discussVO = new DiscussVO();
            BeanUtils.copyProperties(discuss, discussVO);
            discussVOs.add(discussVO);
        }

        return discussVOs;
    }

    public DiscussVO updateDiscuss(DiscussForm form) {
        if (form != null){
            Discuss discuss = new Discuss();
            BeanUtils.copyProperties(form, discuss);

            Discuss save = discussRepository.save(discuss);

            DiscussVO discussVO = new DiscussVO();
            BeanUtils.copyProperties(save, discussVO);

            return discussVO;
        }

        return null;
    }

    public DiscussVO addDiscuss(DiscussForm form){
        if (form != null){
            Discuss discuss = new Discuss();
            BeanUtils.copyProperties(form, discuss);
            Discuss save = discussRepository.save(discuss);

            DiscussVO discussVO = new DiscussVO();
            BeanUtils.copyProperties(save, discussVO);

            return discussVO;
        }

        return null;
    }

    public void deleteDiscuss(Long id){
        discussRepository.deleteById(id);
    }
}

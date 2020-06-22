package fun.neverth.service;

import fun.neverth.bean.entity.Statistics;
import fun.neverth.bean.vo.MajorVO;
import fun.neverth.bean.vo.StatisticsVO;
import fun.neverth.bean.vo.UserVO;
import fun.neverth.repository.StatisticsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/22 12:40
 */
@Service
public class StatisticsService {

    @Resource
    private StatisticsRepository statisticsRepository;

    @Resource
    private MajorService majorService;

    @Resource
    private CourseService courseService;

    @Resource
    private UserService userService;

    public StatisticsVO getAutoUpdate(Long userId){

        List<MajorVO> majorVOS = majorService.getMajorByUserId(userId);

        Statistics statistics = new Statistics();

        statistics.setUserId(userId);
        statistics.setSelectNum(majorVOS.size());

        int pass = 0;
        int fail = 0;
        float credit = 0;
        for (MajorVO ma : majorVOS) {

            if (ma.getRecord() != null){
                if (ma.getRecord() < 60){
                    fail++;
                }else{
                    credit += courseService.getCourseById(ma.getCourseId()).getCredit();
                    pass++;
                }
            }
        }

        statistics.setFail(fail);
        statistics.setPass(pass);
        statistics.setCredit(credit);

        Statistics save = statisticsRepository.save(statistics);

        StatisticsVO statisticsVO = new StatisticsVO();

        BeanUtils.copyProperties(save, statisticsVO);

        return statisticsVO;
    }

    public List<StatisticsVO> getAllUserAutoUpdate(){

        List<UserVO> allUser = userService.getAllUser();
        List<StatisticsVO> statisticsVOS = new ArrayList<>();
        List<Statistics> statisticsList = statisticsRepository.findAll();

        for (UserVO u : allUser) {
            if (u.getRole() != 1){
                List<MajorVO> majorVOS = majorService.getMajorByUserId(u.getId());

                Statistics statistics = new Statistics();

                statistics.setUserId(u.getId());
                statistics.setSelectNum(majorVOS.size());

                int pass = 0;
                int fail = 0;
                float credit = 0;
                for (MajorVO ma : majorVOS) {

                    if (ma.getRecord() != null){
                        if (ma.getRecord() < 60){
                            fail++;
                        }else{
                            credit += courseService.getCourseById(ma.getCourseId()).getCredit();
                            pass++;
                        }
                    }
                }

                statistics.setFail(fail);
                statistics.setPass(pass);
                statistics.setCredit(credit);

                for (Statistics statistics1 : statisticsList) {
                    if (statistics1.getUserId().equals(statistics.getUserId())){
                        statistics.setId(statistics1.getId());
                        break;
                    }
                }

                Statistics save = statisticsRepository.save(statistics);
                StatisticsVO statisticsVO = new StatisticsVO();

                BeanUtils.copyProperties(save, statisticsVO);
                statisticsVO.setUserVO(u);
                statisticsVOS.add(statisticsVO);
            }
        }

        return statisticsVOS;
    }
}

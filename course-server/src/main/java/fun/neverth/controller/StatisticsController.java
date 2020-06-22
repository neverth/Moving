package fun.neverth.controller;

import fun.neverth.bean.vo.StatisticsVO;
import fun.neverth.service.StatisticsService;
import fun.neverth.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/22 12:52
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Resource
    StatisticsService statisticsService;

    @GetMapping("/get")
    public Result<List<StatisticsVO>> getStatisticsAuto(
            @RequestParam(value = "userId", required = false) String userId
    ){
        List<StatisticsVO> statisticsVOS;
        try {
            if (userId == null) {
                statisticsVOS = statisticsService.getAllUserAutoUpdate();

            } else {
                statisticsVOS = new ArrayList<>();
                statisticsVOS.add(
                        statisticsService.getAutoUpdate(Long.parseLong(userId)));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

        if (statisticsVOS != null) {
            return Result.success(statisticsVOS);
        }

        return Result.error();
    }
}

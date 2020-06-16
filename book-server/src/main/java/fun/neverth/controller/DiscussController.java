package fun.neverth.controller;

import fun.neverth.bean.form.DiscussForm;
import fun.neverth.bean.vo.DiscussVO;
import fun.neverth.service.DiscussService;
import fun.neverth.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/16 19:25
 */
@RestController
@RequestMapping("/discuss")
public class DiscussController {
    @Resource
    DiscussService discussService;

    @GetMapping("/get")
    public Result<List<DiscussVO>> getDiscussByBookId(@RequestParam(value = "id", required = false) String id){
        List<DiscussVO> discussVOS;
        try {
            if (id == null) {
                discussVOS = discussService.getAllDiscuss();

            } else {
                discussVOS = discussService.getDiscussByBookId(Long.parseLong(id));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

        if (discussVOS != null) {
            return Result.success(discussVOS);
        }

        return Result.error();
    }

    @PostMapping("/add")
    public Result<DiscussVO> addBook(@RequestBody DiscussForm form) {
        DiscussVO discussVO;
        try {
            discussVO = discussService.addDiscuss(form);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

        if (discussVO != null) {
            return Result.success(discussVO);
        }

        return Result.error();
    }
}

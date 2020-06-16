package fun.neverth.controller;

import fun.neverth.bean.form.UserForm;
import fun.neverth.bean.vo.UserVO;
import fun.neverth.service.UserService;
import fun.neverth.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/16 10:59
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/get")
    public Result<List<UserVO>> getUser(
            @RequestParam(value = "id", required = false) String id
    ) {
        List<UserVO> userVOS;
        try {
            if (id == null) {
                userVOS = userService.getAllUser();

            } else {
                userVOS = new ArrayList<>();
                userVOS.add(userService.getUserById(Long.parseLong(id)));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

        if (userVOS != null) {
            return Result.success(userVOS);
        }

        return Result.error();
    }

    @PostMapping("/login")
    public Result<UserVO> userLogin(
            @RequestParam("name") String name,
            @RequestParam("password") String password
    ) {
        List<UserVO> userByName = userService.getUserByName(name);
        if (userByName.size() != 1){
            return Result.error("用户不存在");

        }else if (!userByName.get(0).getPassword().equals(password)){
            return Result.error("用户密码错误");

        }else{
            userByName.get(0).setPassword("");
            return Result.success(userByName.get(0));
        }
    }

    @PostMapping("/add")
    public Result<UserVO> addUser(UserForm form) {
        UserVO userVO;
        try {
            userVO = userService.addUser(form);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

        if (userVO != null) {
            return Result.success(userVO);
        }

        return Result.error();
    }

    @PostMapping("/update")
    public Result<UserVO> updateUser(UserForm form) {
        UserVO userVO;
        try {
            userVO = userService.updateUser(form);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

        if (userVO != null) {
            return Result.success(userVO);
        }

        return Result.error();
    }

    @PostMapping("/delete")
    public Result<String> deleteUser(@RequestParam("id") String id) {
        try {
            userService.deleteUser(Long.parseLong(id));

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }

        return Result.success();
    }
}

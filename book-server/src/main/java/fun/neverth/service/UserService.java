package fun.neverth.service;

import fun.neverth.bean.entity.User;
import fun.neverth.bean.form.UserForm;
import fun.neverth.bean.vo.UserVO;
import fun.neverth.repository.UserRepository;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/16 10:18
 */
public class UserService {

    @Resource
    private UserRepository userRepository;


    public List<UserVO> getAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserVO> vos = new ArrayList<>();

        for (User user : userList) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            vos.add(userVO);
        }

        return vos;
    }

    public UserVO getUserById(Long id) {
        Optional<User> optional = userRepository.findById(id);

        if (optional.isPresent()) {
            User user = optional.get();

            UserVO userVO = new UserVO();

            BeanUtils.copyProperties(user, userVO);

            return userVO;

        } else {

            return null;
        }
    }

    public List<UserVO> getUserByName(String name) {
        List<User> userList = userRepository.getUserListByName(name);

        List<UserVO> vos = new ArrayList<>();

        for (User user : userList) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            vos.add(userVO);
        }

        return vos;
    }

    public UserVO updateUser(UserForm userForm) {
        if (userForm != null){
            User user = new User();
            BeanUtils.copyProperties(userForm, user);

            User save = userRepository.save(user);

            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(save, userVO);

            return userVO;
        }

        return null;
    }

    public UserVO addUser(UserForm userForm){
        if (userForm != null){
            User user = new User();
            BeanUtils.copyProperties(userForm, user);
            User save = userRepository.save(user);

            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(save, userVO);

            return userVO;
        }

        return null;
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}

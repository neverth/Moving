package fun.neverth.util;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/15 22:35
 */
public class Result<T> extends HashMap<String, Object> implements Serializable {

    public Result (){
        put("code", HttpStatus.OK);
        put("message", "success");
    }

    private Result(T data) {
        put("code", HttpStatus.OK);
        put("message", "success");
        put("data", data);
    }

    private Result(int code, String message) {
        put("code", code);
        put("message", message);
    }

    public static <T> Result<T> success(){
        return new Result<>();
    }

    public static <T> Result<T> success(T data){
        return new Result<>(data);
    }
}

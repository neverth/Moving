package fun.neverth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/5/20 14:39
 */
@Configuration
public class MyConfiguration {

    @Bean
    public User user(){
        return new User();
    }
}

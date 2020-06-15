package fun.neverth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/15 19:49
 */

@SpringBootApplication(scanBasePackages = "fun.neverth")
@EnableJpaRepositories(basePackages = "fun.neverth.repository")
@EntityScan(basePackages = "fun.neverth.bean.po")
public class ApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }

}

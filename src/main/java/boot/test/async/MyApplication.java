package boot.test.async;

import co.paralleluniverse.springframework.web.servlet.config.annotation.FiberWebMvcConfigurationSupport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author circlespainter
 */
@Configuration
@EnableAutoConfiguration
// @Import(FiberWebMvcConfigurationSupport.class)
@ComponentScan
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}

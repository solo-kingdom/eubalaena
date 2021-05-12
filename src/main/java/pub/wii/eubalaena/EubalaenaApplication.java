package pub.wii.eubalaena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan(basePackages = {"pub.wii.eubalaena", "pub.wii.common.spring"})
public class EubalaenaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EubalaenaApplication.class, args);
    }

}

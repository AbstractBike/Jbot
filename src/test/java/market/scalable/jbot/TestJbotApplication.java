package market.scalable.jbot;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class TestJbotApplication {

    public static void main(String[] args) {
        SpringApplication.from(JbotApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}

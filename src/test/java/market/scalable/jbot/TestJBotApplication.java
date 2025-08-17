package market.scalable.jbot;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class TestJBotApplication {

    public static void main(String[] args) {
        SpringApplication.from(JBotApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}

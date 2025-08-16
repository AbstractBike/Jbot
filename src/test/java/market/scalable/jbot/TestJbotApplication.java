package market.scalable.jbot;

import org.springframework.boot.SpringApplication;

public class TestJbotApplication {

	public static void main(String[] args) {
		SpringApplication.from(JbotApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

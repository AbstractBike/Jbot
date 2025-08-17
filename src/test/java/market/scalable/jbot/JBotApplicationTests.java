package market.scalable.jbot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("tech.cassandre.trading.bot")
@Import(TestcontainersConfiguration.class)
@SpringBootTest
class JBotApplicationTests {

    @Test
    void contextLoads() {
    }
}

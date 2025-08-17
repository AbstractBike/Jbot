package market.scalable.jbot.strategy;

import lombok.extern.slf4j.Slf4j;
import market.scalable.jbot.TestJBotApplication;
import market.scalable.jbot.TestcontainersConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import tech.cassandre.trading.bot.dto.util.CurrencyPairDTO;
import tech.cassandre.trading.bot.test.mock.TickerFluxMock;

import java.util.Set;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;
import static tech.cassandre.trading.bot.dto.position.PositionStatusDTO.OPENED;
import static tech.cassandre.trading.bot.dto.util.CurrencyDTO.USDC;

@Slf4j
@ActiveProfiles("test")
@Import({TickerFluxMock.class, TestcontainersConfiguration.class})
@DisplayName("Simple strategy test")
@SpringBootTest(classes = TestJBotApplication.class)
@TestPropertySource(properties = {"basic-test-strategy.enabled=true"})
public class BasicTestStrategyTest {

    /**
     * Basic test strategy.
     */
    @Autowired
    private BasicTestStrategy strategy;
    @Autowired
    private TickerFluxMock tickerFluxMock;

    @Test
    @DisplayName("Check gains")
    public void whenTickersArrives_thenCheckGains() {
        await().forever().until(() -> tickerFluxMock.isFluxDone());

        var gains = strategy.getGains();

        log.info("Cumulated gains:");
        gains.forEach((currency, gain) -> log.info("{} : {}", currency, gain.getAmount()));

        log.info("Position still opened :");
        strategy.getPositions()
                .values()
                .stream()
                .filter(p -> p.getStatus().equals(OPENED))
                .forEach(p -> log.info(" - {}", p.getDescription()));

        assertTrue(gains.get(USDC).getPercentage() > 0);
    }

    /**
     * Check that the strategy is correctly initialized.
     */
    @Test
    public void checkStrategyInitialization() {


        // Check that the strategy has the correct currency pairs.
        Set<CurrencyPairDTO> currencyPairs = strategy.getRequestedCurrencyPairs();
        assertNotNull(currencyPairs);
        assertEquals(1, currencyPairs.size());
        assertTrue(currencyPairs.contains(new CurrencyPairDTO("BTC/USDT")));

    }
}

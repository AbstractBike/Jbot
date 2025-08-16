package market.scalable.jbot.strategy;

import market.scalable.jbot.TestJbotApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import tech.cassandre.trading.bot.dto.util.CurrencyPairDTO;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic test strategy tests.
 */
@SpringBootTest(classes = TestJbotApplication.class)
@ActiveProfiles("test")
@TestPropertySource(properties = {"basic-test-strategy.enabled=true"})
public class BasicTestStrategyTest {

    /**
     * Basic test strategy.
     */
    @Autowired
    private BasicTestStrategy strategy;

    /**
     * Check that the strategy is correctly initialized.
     */
    @Test
    public void checkStrategyInitialization() {
        // Check that the strategy exists.
        assertNotNull(strategy);

        // Check that the strategy has the correct currency pairs.
        Set<CurrencyPairDTO> currencyPairs = strategy.getRequestedCurrencyPairs();
        assertNotNull(currencyPairs);
        assertEquals(1, currencyPairs.size());
        assertTrue(currencyPairs.contains(new CurrencyPairDTO("BTC/USDT")));

        // Check that the lists are initialized.
        assertNotNull(strategy.getTickersReceived());
        assertNotNull(strategy.getAccountsUpdatesReceived());
        assertNotNull(strategy.getOrdersReceived());
        assertNotNull(strategy.getTradesReceived());
        assertNotNull(strategy.getPositionsReceived());
    }
}

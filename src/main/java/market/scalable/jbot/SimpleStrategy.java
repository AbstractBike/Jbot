package market.scalable.jbot;

import tech.cassandre.trading.bot.dto.position.PositionRulesDTO;
import tech.cassandre.trading.bot.dto.user.AccountDTO;
import tech.cassandre.trading.bot.dto.util.CurrencyPairDTO;
import tech.cassandre.trading.bot.strategy.BasicCassandreStrategy;
import tech.cassandre.trading.bot.strategy.CassandreStrategy;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import static tech.cassandre.trading.bot.dto.util.CurrencyDTO.BTC;
import static tech.cassandre.trading.bot.dto.util.CurrencyDTO.USDT;

/**
 * Simple strategy.
 */
@CassandreStrategy(strategyId = "2")
public final class SimpleStrategy extends BasicCassandreStrategy {

    private static final CurrencyPairDTO POSITION_CURRENCY_PAIR = new CurrencyPairDTO(BTC, USDT);

    /** Amount we take on every position - 0.001 BTC = 29,000 USD on 18th May 2022. */
    private static final BigDecimal POSITION_AMOUNT = new BigDecimal("0.001");

    /** Rules set for every position. */
    private static final PositionRulesDTO POSITION_RULES = PositionRulesDTO.builder()
            .stopGainPercentage(4f)
            .stopLossPercentage(8f)
            .build();

    @Override
    public Set<CurrencyPairDTO> getRequestedCurrencyPairs() {
        return Set.of(POSITION_CURRENCY_PAIR);
    }

    @Override
    public Optional<AccountDTO> getTradeAccount(Set<AccountDTO> accounts) {
        return accounts.stream()
                .findFirst();
    }

}
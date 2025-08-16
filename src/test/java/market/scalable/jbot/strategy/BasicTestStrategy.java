package market.scalable.jbot.strategy;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import tech.cassandre.trading.bot.dto.market.TickerDTO;
import tech.cassandre.trading.bot.dto.position.PositionDTO;
import tech.cassandre.trading.bot.dto.trade.OrderDTO;
import tech.cassandre.trading.bot.dto.trade.TradeDTO;
import tech.cassandre.trading.bot.dto.user.AccountDTO;
import tech.cassandre.trading.bot.dto.util.CurrencyPairDTO;
import tech.cassandre.trading.bot.strategy.BasicCassandreStrategy;
import tech.cassandre.trading.bot.strategy.CassandreStrategy;

import java.util.*;

@Slf4j
@CassandreStrategy(strategyName = "Basic Test Strategy")
@ConditionalOnProperty(value = "basic-test-strategy.enabled", havingValue = "true")
public class BasicTestStrategy extends BasicCassandreStrategy {

    @Getter
    private final List<AccountDTO> accountsUpdatesReceived = new LinkedList<>();

    @Getter
    private final List<OrderDTO> ordersReceived = new LinkedList<>();

    @Getter
    private final List<TradeDTO> tradesReceived = new LinkedList<>();

    @Getter
    private final List<PositionDTO> positionsReceived = new LinkedList<>();

    @Getter
    private final List<TickerDTO> tickersReceived = new LinkedList<>();


    @Override
    public Set<CurrencyPairDTO> getRequestedCurrencyPairs() {
        Set<CurrencyPairDTO> currencyPairs = new HashSet<>();
        currencyPairs.add(new CurrencyPairDTO("BTC/USDT"));
        return currencyPairs;
    }

    @Override
    public Optional<AccountDTO> getTradeAccount(Set<AccountDTO> accounts) {
        if (accounts != null && !accounts.isEmpty()) {
            return Optional.of(accounts.iterator().next());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void onAccountsUpdates(Map<String, AccountDTO> accounts) {
        log.info("Account updates received: {}", accounts);
        accountsUpdatesReceived.addAll(accounts.values());
    }

    @Override
    public void onTickersUpdates(Map<CurrencyPairDTO, TickerDTO> tickers) {
        log.info("Tickers received: {}", tickers);
        tickersReceived.addAll(tickers.values());
    }

    @Override
    public void onOrdersUpdates(Map<String, OrderDTO> orders) {
        log.info("Orders received: {}", orders);
        ordersReceived.addAll(orders.values());
    }

    @Override
    public void onTradesUpdates(Map<String, TradeDTO> trades) {
        log.info("Trades received: {}", trades);
        tradesReceived.addAll(trades.values());
    }

    @Override
    public void onPositionsUpdates(Map<Long, PositionDTO> positions) {
        log.info("Positions received: {}", positions);
        positionsReceived.addAll(positions.values());
    }

    @Override
    public void onPositionsStatusUpdates(Map<Long, PositionDTO> positions) {
        log.info("Positions status received: {}", positions);
        positionsReceived.addAll(positions.values());
    }
}

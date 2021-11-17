package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class QuoteService {

  private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);
  private QuoteDao quoteDao;
  private MarketDataDao marketDataDao;

  @Autowired
  public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao) {
    this.quoteDao = quoteDao;
    this.marketDataDao = marketDataDao;
  }

  /**
   * Map an iexQuote into a quote object Note: iexQuote.getLastPrice() == null if the stock market
   * is closed Make sure set a default value for number field(s)
   *
   * @param iexQuote
   * @return
   */
  protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote) {
    return null;
  }

  /**
   * Update quote table against IEX data get all tickers from the database get updated values of the
   * tickers from IEX cloud (iexQuote) convert iexQuote to quote entity for persisting into the db
   */
  public void updateMarketData() {

  }

  /**
   * Validate (against IEX) and save given tickers to quote table - Get iexQuote(s) - convert each
   * iexQuote to Quote entity - persist the quote to db
   *
   * @param tickers
   * @return
   */
  public List<Quote> saveQuotes(List<String> tickers) {
    return null;
  }

  /**
   * Helper method.
   *
   * @param ticker
   * @return
   */
  public Quote saveQuote(String ticker) {
    return null;
  }

  /**
   * Find an IexQuote from IEX cloud
   *
   * @param ticker
   * @return
   */
  public IexQuote findIexQuoteByTicker(String ticker) {
    return marketDataDao.findById(ticker)
        .orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid!"));
  }

  /**
   * update a quote without validation
   *
   * @param quote
   * @return
   */
  public Quote saveQuote(Quote quote) {
    return quoteDao.save(quote);
  }

  /**
   * Find all quotes from the quote table
   *
   * @return
   */
  public List<Quote> findAllQuotes() {
    return quoteDao.findAll();
  }
}

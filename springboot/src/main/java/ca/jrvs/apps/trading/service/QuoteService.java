package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
   * Map an iexQuote into a quote object
   * Note: iexQuote.getLastPrice() == null if the stock market is closed
   * Make sure set a default value for number field(s)
   * @param iexQuote
   * @return
   */
  protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote) {
    Quote aQuote = new Quote();
    aQuote.setId(iexQuote.getSymbol());
    aQuote.setLastPrice(iexQuote.getLatestPrice());
    aQuote.setBidPrice(Double.valueOf(iexQuote.getIexBidPrice()));
    aQuote.setBidSize(iexQuote.getIexBidSize());
    aQuote.setAskPrice(Double.valueOf(iexQuote.getIexAskPrice()));
    aQuote.setAskSize(iexQuote.getIexAskSize());
    return aQuote;
  }

  /**
   * Update quote table against IEX data
   * get all tickers from the database
   * get updated values of the tickers from IEX cloud (iexQuote)
   * convert iexQuote to quote entity for persisting into the db
   */
  public void updateMarketData() {
    List<Quote> quotes = this.findAllQuotes();
    List<String> tickers = quotes.stream().map(quote->quote.getId()).collect(Collectors.toList());
    saveQuotes(tickers);
  }

  /**
   * Validate (against IEX) and save given tickers to quote table
   * Get iexQuote(s)
   * convert each iexQuote to Quote entity
   * persist the quote to db
   *
   * @param tickers
   * @return
   */
  public List<Quote> saveQuotes(List<String> tickers) {
    List<Quote> savedQuotes = tickers.stream().
        map(ticker -> saveQuote(ticker)).
        collect(Collectors.toList());
    return savedQuotes;
  }

  /**
   * Helper method. Find iexQuote, convert it to Quote and save to db
   *
   * @param ticker
   * @return
   */
  public Quote saveQuote(String ticker) {
    Optional<IexQuote> iexQuote = marketDataDao.findById(ticker);
    return quoteDao.save(buildQuoteFromIexQuote(iexQuote.orElseThrow(()-> new
        IllegalArgumentException("invalid ticker Id!"))));
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
   * update a quote without validation/conversion
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

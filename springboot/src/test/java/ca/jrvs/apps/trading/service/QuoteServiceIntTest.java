package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.Quote;
import com.sun.org.apache.xpath.internal.operations.Quo;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteServiceIntTest {

  @Autowired
  private QuoteService quoteService;

  @Autowired
  private QuoteDao quoteDao;

  private Quote aQuote;

  @Before
  public void setUp() {
    quoteDao.deleteAll();
  }

  @Test
  public void updateMarketData() {
    aQuote = new Quote();
    aQuote.setAskPrice(10d);
    aQuote.setAskSize(10);
    aQuote.setBidPrice(10.2d);
    aQuote.setBidSize(10);
    aQuote.setId("AAPL");
    aQuote.setLastPrice(10.1d);
    quoteDao.save(aQuote);
    assertEquals(aQuote,quoteDao.findById("AAPL").get());

    quoteService.updateMarketData();
    assertNotEquals(aQuote,quoteDao.findById("AAPL").get());
  }

  @Test
  public void saveQuotes() {
    List<String> tickers = new ArrayList<>(Arrays.asList("AAPL","FB"));
    quoteService.saveQuotes(tickers);
    assertEquals("AAPL",quoteDao.findById("AAPL").get().getId());
    assertEquals("FB",quoteDao.findById("FB").get().getId());
  }

  @Test
  public void saveQuote() {
    quoteService.saveQuote("AAPL");
    assertEquals("AAPL",quoteDao.findById("AAPL").get().getId());
    aQuote = new Quote();
    aQuote.setAskPrice(10d);
    aQuote.setAskSize(10);
    aQuote.setBidPrice(10.2d);
    aQuote.setBidSize(10);
    aQuote.setId("FB");
    aQuote.setLastPrice(10.1d);
    quoteService.saveQuote(aQuote);
    assertEquals(aQuote,quoteDao.findById("FB").get());
  }

  @Test
  public void findIexQuoteByTicker() {
    assertEquals("AAPL",quoteService.findIexQuoteByTicker("AAPL").getSymbol());
  }

  @Test
  public void findAllQuotes() {
    aQuote = new Quote();
    aQuote.setAskPrice(10d);
    aQuote.setAskSize(10);
    aQuote.setBidPrice(10.2d);
    aQuote.setBidSize(10);
    aQuote.setId("FB");
    aQuote.setLastPrice(10.1d);
    quoteService.saveQuote(aQuote);
    //make a new quote object
    Quote anotherQuote = new Quote(aQuote);
    anotherQuote.setId("APPL");
    quoteService.saveQuote(anotherQuote);
    //make a checker list
    List<Quote> checker = new ArrayList<>();
    checker.add(aQuote);
    checker.add(anotherQuote);
    //make a result list and compare
    List<Quote> result = quoteService.findAllQuotes();
    Collections.sort(checker);
    Collections.sort(result);
    assertEquals(checker,result);
  }
}
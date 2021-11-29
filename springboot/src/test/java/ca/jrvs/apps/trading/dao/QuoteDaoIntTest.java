package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
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
public class QuoteDaoIntTest {
  @Autowired
  private  QuoteDao quoteDao;

  private Quote aQuote;

  @Before
  public void setUp() {
//    quoteDao.deleteAll();
    aQuote = new Quote();
    aQuote.setAskPrice(10d);
    aQuote.setAskSize(10);
    aQuote.setBidPrice(10.2d);
    aQuote.setBidSize(10);
    aQuote.setId("AAPL");
    aQuote.setLastPrice(10.1d);
    quoteDao.save(aQuote);
  }

  @After
  public void tearDown() {
    quoteDao.deleteById(aQuote.getId());
  }

  @Test
  public void save(){
    aQuote = new Quote();
    aQuote.setAskPrice(10d);
    aQuote.setAskSize(10);
    aQuote.setBidPrice(10.2d);
    aQuote.setBidSize(10);
    aQuote.setId("FB");
    aQuote.setLastPrice(10.1d);
    quoteDao.save(aQuote);
    assertEquals(aQuote,quoteDao.findById("FB").get());
  }

  @Test
  public void saveAll(){
    aQuote = new Quote();
    aQuote.setAskPrice(10d);
    aQuote.setAskSize(10);
    aQuote.setBidPrice(10.2d);
    aQuote.setBidSize(10);
    aQuote.setId("FB");
    aQuote.setLastPrice(10.1d);

    List<Quote> quotes = new ArrayList<>();
    quotes.add(aQuote);
    quoteDao.saveAll(quotes);
    assertEquals(2,quoteDao.findAll().size());
  }

  @Test
  public void findById(){
    assertEquals(aQuote,quoteDao.findById("AAPL").get());
  }

  @Test
  public void existsById() {
    assertTrue(quoteDao.existsById("AAPL"));
  }

  @Test
  public void findAll() {
    List<Quote> savedQuote = quoteDao.findAll();
    assertEquals(savedQuote.size(), 1);
  }

  @Test
  public void count() {
    assertEquals(quoteDao.count(), 1);
  }

  @Test
  public void deleteAll(){
    quoteDao.deleteAll();
    assertEquals(0,quoteDao.findAll().size());
  }

}
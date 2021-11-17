package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
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
  public void save() {
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
  public void deleteById() {
    quoteDao.deleteById(aQuote.getId());
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
}
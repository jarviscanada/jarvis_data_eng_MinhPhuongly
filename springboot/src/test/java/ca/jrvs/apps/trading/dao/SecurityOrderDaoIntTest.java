package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.Calendar;
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
public class SecurityOrderDaoIntTest {
  @Autowired
  private TraderDao traderDao;
  private Trader aTrader;
  @Autowired
  private AccountDao accountDao;
  private Account anAccount;
  @Autowired
  private QuoteDao quoteDao;
  private Quote aQuote;
  @Autowired
  private SecurityOrderDao securityOrderDao;
  private SecurityOrder aSecurityOrder;

  @Before
  public void setUp() throws Exception {
    aTrader = new Trader();
    aTrader.setId(1); //id types serials (auto increase) this line can be opted out
    aTrader.setFirstName("Phuong");
    aTrader.setLastName("Ly");
    Calendar calendar = Calendar.getInstance();
    calendar.set(2000,01,01);
    aTrader.setDob(calendar.getTime());
    aTrader.setCountry("Vietnam");
    aTrader.setEmail("test@gmail.com");
    traderDao.save(aTrader);
    //Note: Account table has Trader_id as fk which need to reference to Trader table
    anAccount = new Account();
    anAccount.setId(1); //same with id in Trader table
    anAccount.setTrader_id(1);
    anAccount.setAmount(10.5f);
    accountDao.save(anAccount);

    aQuote = new Quote();
    aQuote.setAskPrice(10d);
    aQuote.setAskSize(10);
    aQuote.setBidPrice(10.2d);
    aQuote.setBidSize(10);
    aQuote.setId("AAPL");
    aQuote.setLastPrice(10.1d);
    quoteDao.save(aQuote);
    //Note: securityOrder has relationship with Account and Quote tables
    aSecurityOrder = new SecurityOrder();
    aSecurityOrder.setId(1);
    aSecurityOrder.setAccount_id(1);
    aSecurityOrder.setTicker("AAPL");
    aSecurityOrder.setPrice(10.9f);
    aSecurityOrder.setSize(15);
    aSecurityOrder.setStatus("Closed");
    aSecurityOrder.setNotes("Bought for fun!");
    securityOrderDao.save(aSecurityOrder);
  }

  @After
  public void tearDown() throws Exception {
    securityOrderDao.deleteById(1);
  }

  @Test
  public void count(){
    assertEquals(1,securityOrderDao.count());
  }

  @Test
  public void findAll(){
    assertEquals(1,securityOrderDao.findAll().size());
  }
}
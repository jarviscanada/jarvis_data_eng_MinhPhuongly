package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
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
public class TraderAccountServiceIntTest {
  @Autowired
  private TraderDao traderDao;
  private Trader aTrader;

  @Autowired
  private AccountDao accountDao;
  private Account anAccount;

  @Autowired
  private TraderAccountService traderAccountService;
  private TraderAccountView aTraderAccountView;


  @Before
  public void setUp(){
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
    anAccount.setTraderId(1);
    anAccount.setAmount(0.0);
    accountDao.save(anAccount);
  }

  @After
  public void tearDown() {
    accountDao.deleteAll();
    traderDao.deleteAll();
  }

  @Test
  public void createTraderAndAccount() {
    aTraderAccountView = traderAccountService.createTraderAndAccount(aTrader);
    assertEquals(2, accountDao.count());
  }

  @Test
  public void deleteTraderById() {
    traderAccountService.deleteTraderById(1);
    assertEquals(0, traderDao.count());
  }

  @Test
  public void deposit() {
    traderAccountService.deposit(1, 1000.00);
    assertEquals(new Double(1000), accountDao.findById(1).get().getAmount());
  }

  @Test
  public void withdraw() {
    traderAccountService.deposit(1, 999.0);
    traderAccountService.withdraw(1, 99.0);
    assertEquals(new Double(999.0-99.0), accountDao.findById(1).get().getAmount());
  }
}
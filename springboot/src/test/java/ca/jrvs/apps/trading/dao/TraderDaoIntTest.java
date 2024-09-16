package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.assertj.core.util.Lists;
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
public class TraderDaoIntTest {
  @Autowired
  private TraderDao traderDao;
  private Trader aTrader;

  @Before
  public void setUp() throws Exception {
    aTrader = new Trader();
    aTrader.setId(1);
    aTrader.setFirstName("Phuong");
    aTrader.setLastName("Ly");
    Calendar calendar = Calendar.getInstance();
    calendar.set(2000,01,01);
    aTrader.setDob(calendar.getTime());
    aTrader.setCountry("Vietnam");
    aTrader.setEmail("test@gmail.com");
    traderDao.save(aTrader);
  }

  @After
  public void tearDown() {
    traderDao.deleteById(1);
  }

  @Test
  public void count(){
    assertEquals(1,traderDao.count());
  }

  @Test
  public void findAll(){
    assertEquals(1,traderDao.findAll().size());
  }
}
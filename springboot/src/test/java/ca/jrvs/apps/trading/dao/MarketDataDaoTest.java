package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MarketDataDaoTest {
  private MarketDataDao dao;
  @Before
  public void setUp() throws Exception {
    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    cm.setMaxTotal(50);
    cm.setDefaultMaxPerRoute(50);

    MarketDataConfig marketDataConfig = new MarketDataConfig("https://cloud.iexapis.com/v1",
        System.getenv("IEX_SECRET"));
    dao = new MarketDataDao(cm,marketDataConfig);
  }

  @Test
  public void findById() {
    String ticker = "AAPL";
    IexQuote iexQuote = dao.findById(ticker).get();
    assertEquals(ticker,iexQuote.getSymbol());
  }

  @Test
  public void findAllById() throws IOException {
    //Happy path
    List<IexQuote> quoteList = dao.findAllById(Arrays.asList("AAPL","FB"));
    assertEquals(2,quoteList.size());
    assertEquals("AAPL", quoteList.get(0).getSymbol());

    //Sad path
    try{
      dao.findAllById(Arrays.asList("AAPL","FB2"));
      fail();
    }
    catch (IllegalArgumentException e){
      assertTrue(true);
    }
    catch (Exception e){
      fail();
    }
  }

}
package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"ca.jrvs.apps.trading.dao","ca.jrvs.apps.trading.service"})
public class TestConfig {
  /**
   * Setup marketDataConfig for passing into MarketDataDao
   * @return MarketDataConfig
   */
  @Bean
  public MarketDataConfig marketDataConfig(){
    MarketDataConfig dataConfig = new MarketDataConfig("https://cloud.iexapis.com/v1",
        System.getenv("IEX_SECRET"));
    return dataConfig;
  }

  /**
   * Setup connectionManager for passing in MarketDataDao
   * @return HttpClientConnectionManager
   */
  @Bean
  public HttpClientConnectionManager httpClientConnectionManager(){
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    connectionManager.setMaxTotal(50);
    connectionManager.setDefaultMaxPerRoute(50);
    return connectionManager;
  }
  @Bean
  public DataSource datasource(){
    String jdbcUrl =
        "jdbc:postgresql://"+
            System.getenv("PSQL_HOST")+":"+
            System.getenv("PSQL_PORT")+"/"+
            System.getenv("PSQL_DB_TEST");
    System.out.println("Creating apacheDataSource");
    //Note: do not log credentials/secrets. Use IDE debugger instead (or println)
    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setUrl(jdbcUrl);
    basicDataSource.setUsername(System.getenv("PSQL_USER"));
    basicDataSource.setPassword(System.getenv("PSQL_PASSWORD"));
    return basicDataSource;
  }
}


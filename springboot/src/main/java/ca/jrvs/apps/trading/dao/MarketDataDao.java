package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.helper.JsonParser;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

  private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
  private final String IEX_BATCH_URL;
  private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
  private HttpClientConnectionManager httpClientConnectionManager;

  public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager,
      MarketDataConfig marketDataConfig) {
    this.httpClientConnectionManager = httpClientConnectionManager;
    IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
  }

  /**
   * Get a quote from IEX
   *
   * @param ticker
   * @return
   */
  @Override
  public Optional<IexQuote> findById(String ticker) {
    Optional<IexQuote> iexQuote;
    List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));
    if (quotes.size() == 0) {
      return Optional.empty();
    } else if (quotes.size() == 1) {
      iexQuote = Optional.of(quotes.get(0));
    } else {
      throw new DataRetrievalFailureException("Unexpected number of quotes");
    }
    return iexQuote;
  }

  /**
   * Get quotes from IEX
   *
   * @param tickers is a list of tickers
   * @return a list of IexQuote objects
   */
  @Override
  public List<IexQuote> findAllById(Iterable<String> tickers) {
    List<IexQuote> iexQuotes = new ArrayList<>();
    String tickersString = String.join(",", tickers);
    try {
      final String IEX_FULL_BATCH_URL = String.format(IEX_BATCH_URL, tickersString.toString());
      String response = executeHttpGet(IEX_FULL_BATCH_URL).orElseThrow(
          () -> new IllegalArgumentException("No data found, check your tickers"));

      JSONObject jsonQuotes = new JSONObject(response);

      for (String key : tickers) {
        if (jsonQuotes.has(key)) {
          JSONObject aJsonQuote = jsonQuotes.getJSONObject(key);
          iexQuotes.add(
              JsonParser.toObjectFromJson(aJsonQuote.get("quote").toString(), IexQuote.class));
        } else {
          throw new IllegalArgumentException("Unknown symbol/ticker: " + key);
        }
      }
      return iexQuotes;
    } catch (DataRetrievalFailureException e) {
      throw new RuntimeException("Failed to find quotes by ID", e);
    } catch (IOException e) {
      throw new RuntimeException("Failed to find quotes by ID", e);
    }
  }

  /**
   * execute httpGet and return the response body as a String
   *
   * @param url
   * @return http response or Optional.empty() for 404 response
   */
  private Optional<String> executeHttpGet(String url)
      throws DataRetrievalFailureException, IOException {
    Optional<String> responseBody;

    HttpClient httpClient = getHttpClient();
    HttpGet request = new HttpGet(url);
    HttpResponse response = httpClient.execute(request);
    if (response.getStatusLine().getStatusCode() == 200) {
      responseBody = Optional.of(EntityUtils.toString(response.getEntity()));
    } else if (response.getStatusLine().getStatusCode() == 404) {
      return Optional.empty();
    } else {
      throw new RuntimeException("Wrong response status code!");
    }

    return responseBody;
  }

  /**
   * Borrow a HTTP client from httpClientConnectionManager
   *
   * @return a httpClient
   */
  private CloseableHttpClient getHttpClient() {
    return HttpClients.custom()
        .setConnectionManager(httpClientConnectionManager)
        //prevent connectionManager shutdown when calling httpClient.close()
        .setConnectionManagerShared(true)
        .build();
  }

  @Override
  public <S extends IexQuote> S save(S s) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public Iterable<IexQuote> findAll() {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public boolean existsById(String s) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public long count() {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public void deleteById(String s) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public void delete(IexQuote iexQuote) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public void deleteAll(Iterable<? extends IexQuote> iterable) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public void deleteAll() {
    throw new UnsupportedOperationException("Not implemented!");
  }
}

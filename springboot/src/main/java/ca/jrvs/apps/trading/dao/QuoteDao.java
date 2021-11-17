package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteDao implements CrudRepository<Quote, String> {

  private static final String TABLE_NAME = "quote";
  private static final String ID_COLUMN_NAME = "ticker";

  private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public QuoteDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
  }

  /**
   * Check if the quote exist -> update/add
   *
   * @param quote
   * @return
   * @throws DataRetrievalFailureException for unexpected SQL result or SQL execution failure
   */
  @Override
  public <S extends Quote> S save(S quote) {
    if (existsById(quote.getId())) {
      int updateRowNo = updateOne(quote);
      if (updateRowNo != 1) {
        throw new DataRetrievalFailureException("Unable to update quote");
      }
    } else {
      addOne(quote);
    }
    return quote;
  }

  /**
   * Helper method that save one quote
   *
   * @param quote
   */
  private void addOne(Quote quote) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
    int row = simpleJdbcInsert.execute(parameterSource);
    if (row != 1) {
      throw new IncorrectResultSizeDataAccessException("Failed to insert", 1, row);
    }
  }

  /**
   * Helper method to update one quote
   *
   * @param quote
   * @return
   */
  private int updateOne(Quote quote) {
    String update_sql = "UPDATE quote SET last_price=?, bid_price=?, bid_size=?, ask_price=?, "
        + "ask_size=? WHERE ticker=?";
    return jdbcTemplate.update(update_sql, makeUpdateValues(quote));
  }

  /**
   * Helper method that helps to make SQL update values
   *
   * @param quote
   * @return
   */
  private Object[] makeUpdateValues(Quote quote) {
    return new Object[]{quote.getLastPrice(),
        quote.getBidPrice(), quote.getBidSize(),
        quote.getAskPrice(), quote.getAskSize(),
        quote.getId()};
  }

  /**
   * Save all quotes into psql database
   *
   * @param iterable
   * @param <S>
   * @return
   */
  @Override
  public <S extends Quote> Iterable<S> saveAll(Iterable<S> iterable) {
    List<S> result = new ArrayList<>();
    for (S quote : iterable) {
      result.add(this.save(quote));
    }
    return result;
  }

  /**
   * Find a quote by ticker/symbol
   *
   * @param s
   * @return null if empty, else quote object.
   */
  @Override
  public Optional<Quote> findById(String s) {
    String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + "=?";
    Optional<Quote> result = Optional.empty();
    try {
      result = Optional.ofNullable(jdbcTemplate.queryForObject(sqlQuery,
          BeanPropertyRowMapper.newInstance(Quote.class), s));
    } catch (EmptyResultDataAccessException e) {
      logger.debug("Can't find trader id:" + s, e);
    }
    if (result.isPresent()) {
      return result;
    }
    return Optional.empty();
  }

  /**
   * Check if a quote exists by its Id
   *
   * @param s
   * @return
   */
  @Override
  public boolean existsById(String s) {
    return findById(s).isPresent();
  }

  /**
   * Find all quotes in the table
   *
   * @return
   */
  @Override
  public List<Quote> findAll() {
    List<Quote> quotes = jdbcTemplate.query("SELECT * FROM " + TABLE_NAME,
        BeanPropertyRowMapper.newInstance(Quote.class));
    return quotes;
  }

  /**
   * Count number of quotes in the table
   *
   * @return
   */
  @Override
  public long count() {
    return jdbcTemplate.queryForObject("SELECT count(*) FROM " + TABLE_NAME, Long.class);
  }

  /**
   * delete a quote by its id
   *
   * @param s
   */
  @Override
  public void deleteById(String s) {
    if (s == null) {
      throw new IllegalArgumentException("ID can't be null");
    }
    String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " =?";
    jdbcTemplate.update(deleteSql, s);
  }

  /**
   * delete all rows in the table
   */
  @Override
  public void deleteAll() {
    String deleteSql = "DELETE FROM " + TABLE_NAME;
    jdbcTemplate.update(deleteSql);
  }

  @Override
  public void delete(Quote quote) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public void deleteAll(Iterable<? extends Quote> iterable) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public Iterable<Quote> findAllById(Iterable<String> iterable) {
    throw new UnsupportedOperationException("Not implemented!");
  }

}

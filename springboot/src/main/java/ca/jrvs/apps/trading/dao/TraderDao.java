package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.Arrays;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class TraderDao extends JdbcCrudDao<Trader>{
  private static final Logger logger = LoggerFactory.getLogger(TraderDao.class);
  private final String TABLE_NAME = "trader";
  private final String ID_COLUMN = "id";

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleInsert;

  @Autowired
  public TraderDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).
        usingGeneratedKeyColumns(ID_COLUMN);
  }

  @Override
  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  @Override
  SimpleJdbcInsert getSimpleJdbcInsert() {
    return simpleInsert;
  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  @Override
  public String getIdColumnName() {
    return ID_COLUMN;
  }

  @Override
  Class<Trader> getEntityClass() {
    return Trader.class;
  }

  @Override
  public int updateOne(Trader trader) {
    String update_sql = "UPDATE trader SET first_name=?, last_name=?, dob=?, country=?, "
        + "email=? WHERE id=?";
    return jdbcTemplate.update(update_sql, makeUpdateValues(trader));
  }

  private Object[] makeUpdateValues(Trader trader) {
    return new Object[]{trader.getFirstName(), trader.getLastName(),
    trader.getDob(), trader.getCountry(), trader.getEmail(), trader.getId()};
  }


  @Override
  public void delete(Trader entity) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public void deleteAll(Iterable<? extends Trader> iterable) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public Iterable<Trader> findAllById(Iterable<Integer> iterable) {
    throw new UnsupportedOperationException("Not implemented!");
  }
}

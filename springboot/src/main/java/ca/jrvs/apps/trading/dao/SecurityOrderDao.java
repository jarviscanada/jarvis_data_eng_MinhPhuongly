package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class SecurityOrderDao extends JdbcCrudDao<SecurityOrder> {
  private static final Logger logger = LoggerFactory.getLogger(SecurityOrder.class);
  private final String TABLE_NAME = "security_order";
  private final String ID_COLUMN = "id";

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleInsert;

  @Autowired
  public SecurityOrderDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleInsert = new SimpleJdbcInsert(dataSource).
        withTableName(TABLE_NAME).
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
  Class<SecurityOrder> getEntityClass() {
    return SecurityOrder.class;
  }


  @Override
  public int updateOne(SecurityOrder securityOrder) {
    String update_sql = "UPDATE security_order SET account_id=?, status=?"
        + ", ticker=?, size=?, price=?, notes=? WHERE id=?";
    return jdbcTemplate.update(update_sql, makeUpdateValues(securityOrder));
  }

  private Object[] makeUpdateValues(SecurityOrder securityOrder) {
    return new Object[]{securityOrder.getAccount_id(),
        securityOrder.getStatus(),
        securityOrder.getTicker(),
        securityOrder.getSize(),
        securityOrder.getPrice(),
        securityOrder.getNotes(),
        securityOrder.getId()};
  }

  @Override
  public void delete(SecurityOrder entity) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public void deleteAll(Iterable<? extends SecurityOrder> iterable) {
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public Iterable<SecurityOrder> findAllById(Iterable<Integer> iterable) {
    throw new UnsupportedOperationException("Not implemented!");
  }
}

package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Position;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PositionDao {
  private static final Logger logger = LoggerFactory.getLogger(TraderDao.class);
  private final String TABLE_NAME = "position";
  private final String ID_COLUMN = "account_id";

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public PositionDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public Optional<Position> findById(Integer id) {
    String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + "=?";
    Optional<Position> result = Optional.empty();
    try {
      result = Optional.ofNullable( jdbcTemplate.queryForObject(sqlQuery,
          BeanPropertyRowMapper.newInstance(Position.class), id));
    } catch (IncorrectResultSizeDataAccessException e) {
      logger.debug("Can't find Position id:" + id, e);
    }
    return result;
  }

  public List<Position> findAll() {
    List<Position> entities = jdbcTemplate.query("SELECT * FROM " + TABLE_NAME,
        BeanPropertyRowMapper.newInstance(Position.class));
    return entities;
  }

  public boolean existsById(Integer id) {
    return findById(id).isPresent();
  }

  public long count() {
    return jdbcTemplate.queryForObject("SELECT count(*) FROM " + TABLE_NAME, Long.class);
  }

}

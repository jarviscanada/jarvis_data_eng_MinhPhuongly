package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Entity;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public abstract class JdbcCrudDao <T extends Entity<Integer>> implements CrudRepository<T, Integer>{

  private static final Logger logger = LoggerFactory.getLogger(JdbcCrudDao.class);
  abstract public JdbcTemplate getJdbcTemplate();
  abstract SimpleJdbcInsert getSimpleJdbcInsert();
  abstract public String getTableName();
  abstract public String getIdColumnName();
  abstract Class<T> getEntityClass();

  /**
   * Save an entity and update auto-generated integerID
   * @param entity
   * @param <S>
   * @return saved entity
   */
  @Override
  public <S extends T> S save(S entity) {
    if (existsById(entity.getId())) {
      if (updateOne(entity) != 1) {
        throw new DataRetrievalFailureException("Unable to update quote");
      }
    } else {
      addOne(entity);
    }
    return entity;
  }

  /**
   * Helper method that add one entity
   * @param entity
   * @param <S>
   */
  private <S extends T> void addOne(S entity) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(entity);
    //execute and return the generated key
    Number row = getSimpleJdbcInsert().executeAndReturnKey(parameterSource);
    entity.setId(row.intValue());
  }

  /**
   * Helper method that update one entity
   * @param entity
   * @return
   */
  abstract public int updateOne(T entity);

  /**
   * save all entity to db
   * @param iterable
   * @param <S>
   * @return
   */
  @Override
  public <S extends T> List<S> saveAll(Iterable<S> iterable) {
    List<S> result = new ArrayList<>();
    for (S quote : iterable) {
      result.add(this.save(quote));
    }
    return result;
  }

  /**
   *
   * @param id
   * @return
   */
  @Override
  public Optional<T> findById(Integer id) {
    String sqlQuery = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + "=?";
    Optional<T> result = Optional.empty();
    try {
      result = Optional.ofNullable( getJdbcTemplate().queryForObject(sqlQuery,
          BeanPropertyRowMapper.newInstance(getEntityClass()), id));
    } catch (IncorrectResultSizeDataAccessException e) {
      logger.debug("Can't find trader id:" + id, e);
    }
    return result;
  }


  @Override
  public boolean existsById(Integer id) {
    return findById(id).isPresent();
  }

  /**
   * Find all entities in the table
   *
   * @return
   */
  @Override
  public List<T> findAll() {
    List<T> entity = getJdbcTemplate().query("SELECT * FROM " + getTableName(),
        BeanPropertyRowMapper.newInstance(getEntityClass()));
    return entity;
  }

  /**
   * Count number of entities in the table
   *
   * @return
   */
  @Override
  public long count() {
    return getJdbcTemplate().queryForObject("SELECT count(*) FROM " + getTableName(), Long.class);
  }

  /**
   * delete an entity by its id
   *
   * @param id
   */
  @Override
  public void deleteById(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("ID can't be null");
    }
    String deleteSql = "DELETE FROM " + getTableName() + " WHERE " + getIdColumnName() + " =?";
    getJdbcTemplate().update(deleteSql, id);
  }

  /**
   * delete all rows in the table
   */
  @Override
  public void deleteAll() {
    String deleteSql = "DELETE FROM " + getTableName();
    getJdbcTemplate().update(deleteSql);
  }

  public abstract void delete(T entity);

  public abstract void deleteAll(Iterable<? extends T> iterable);

  public abstract Iterable<T> findAllById(Iterable<Integer> iterable);
}

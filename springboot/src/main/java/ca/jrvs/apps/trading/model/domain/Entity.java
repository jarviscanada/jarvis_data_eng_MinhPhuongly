package ca.jrvs.apps.trading.model.domain;

public interface Entity<T> {

  T getId();

  void setId(T id);
}

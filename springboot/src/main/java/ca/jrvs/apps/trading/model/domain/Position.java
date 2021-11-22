package ca.jrvs.apps.trading.model.domain;

import java.util.Objects;

public class Position implements Entity<Integer>{
  private Integer accountId;
  private String ticker;
  private Integer position;


  @Override
  public Integer getId() {
    return accountId;
  }

  @Override
  public void setId(Integer id) {
    this.accountId = id;
  }

  public Integer getAccountId() {
    return accountId;
  }

  public void setAccountId(Integer accountId) {
    this.accountId = accountId;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Position)) {
      return false;
    }
    Position position1 = (Position) o;
    return Objects.equals(getAccountId(), position1.getAccountId())
        && Objects.equals(getTicker(), position1.getTicker()) && Objects.equals(
        getPosition(), position1.getPosition());
  }


  @Override
  public String toString() {
    return "Position{" +
        "accountId=" + accountId +
        ", ticker='" + ticker + '\'' +
        ", position=" + position +
        '}';
  }
}

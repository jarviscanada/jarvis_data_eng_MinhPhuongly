package ca.jrvs.apps.trading.model.domain;

import java.util.Objects;

public class TraderAccountView {
  private Account account;
  private Trader trader;

  public TraderAccountView(Trader trader, Account account) {
    this.trader = trader;
    this.account = account;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Trader getTrader() {
    return trader;
  }

  public void setTrader(Trader trader) {
    this.trader = trader;
  }

  @Override
  public String toString() {
    return "TraderAccountView{" +
        "account=" + account +
        ", trader=" + trader +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TraderAccountView)) {
      return false;
    }
    TraderAccountView that = (TraderAccountView) o;
    return Objects.equals(getAccount(), that.getAccount()) && Objects.equals(
        getTrader(), that.getTrader());
  }

}

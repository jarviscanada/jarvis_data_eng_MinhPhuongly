package ca.jrvs.apps.trading.model.domain;


import java.util.Objects;

public class Quote implements Entity<String>, Comparable<Quote> {

  private String ticker;
  private Double lastPrice;
  private Double bidPrice;
  private Integer bidSize;
  private Double askPrice;
  private Integer askSize;

  public Quote() {
  }

  public Quote(Quote quote) {
    this.ticker = new String(quote.getId());
    this.lastPrice = new Double(quote.getLastPrice());
    this.bidPrice = new Double(quote.getBidPrice());
    this.bidSize = new Integer(quote.getBidSize());
    this.askPrice = new Double(quote.getAskPrice());
    this.askSize = new Integer(quote.getAskSize());
  }

  @Override
  public String getId() {
    return ticker;
  }

  @Override
  public void setId(String id) {
    this.ticker = id;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public Double getLastPrice() {
    return lastPrice;
  }

  public void setLastPrice(Double lastPrice) {
    this.lastPrice = lastPrice;
  }

  public Double getBidPrice() {
    return bidPrice;
  }

  public void setBidPrice(Double bidPrice) {
    this.bidPrice = bidPrice;
  }

  public Integer getBidSize() {
    return bidSize;
  }

  public void setBidSize(Integer bidSize) {
    this.bidSize = bidSize;
  }

  public Double getAskPrice() {
    return askPrice;
  }

  public void setAskPrice(Double askPrice) {
    this.askPrice = askPrice;
  }

  public Integer getAskSize() {
    return askSize;
  }

  public void setAskSize(Integer askSize) {
    this.askSize = askSize;
  }

  @Override
  public String toString() {
    return "Quote{" +
        "ticker='" + ticker + '\'' +
        ", lastPrice=" + lastPrice +
        ", bidPrice=" + bidPrice +
        ", bidSize=" + bidSize +
        ", askPrice=" + askPrice +
        ", askSize=" + askSize +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Quote)) {
      return false;
    }
    Quote quote = (Quote) o;
    return Objects.equals(getTicker(), quote.getTicker()) && Objects.equals(
        getLastPrice(), quote.getLastPrice()) && Objects.equals(getBidPrice(),
        quote.getBidPrice()) && Objects.equals(getBidSize(), quote.getBidSize())
        && Objects.equals(getAskPrice(), quote.getAskPrice()) && Objects.equals(
        getAskSize(), quote.getAskSize());
  }

  @Override
  public int compareTo(Quote o) {
    return this.getId().compareTo(o.getId());
  }

}

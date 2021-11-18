package ca.jrvs.apps.trading.model.domain;

public class SecurityOrder implements Entity<Integer>{
  private Integer id;
  private Integer account_id;
  private String status;
  private String ticker;
  private Integer size;
  private Float price;
  private String notes;

  public SecurityOrder() {}

  public SecurityOrder(Integer id, Integer account_id, String status, String ticker,
      Integer size, Float price, String notes) {
    this.id = id;
    this.account_id = account_id;
    this.status = status;
    this.ticker = ticker;
    this.size = size;
    this.price = price;
    this.notes = notes;
  }

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAccount_id() {
    return account_id;
  }

  public void setAccount_id(Integer account_id) {
    this.account_id = account_id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  @Override
  public String toString() {
    return "SecurityOrder{" +
        "id=" + id +
        ", account_id=" + account_id +
        ", status='" + status + '\'' +
        ", ticker='" + ticker + '\'' +
        ", size=" + size +
        ", price=" + price +
        ", notes='" + notes + '\'' +
        '}';
  }
}

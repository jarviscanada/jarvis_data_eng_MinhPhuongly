package ca.jrvs.apps.trading.model.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * IexQuote model coming from: https://iexcloud.io/docs/api/#quote
 * Tool used to create POJO Java model from JSON string: http://www.jsonschema2pojo.org/
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "symbol",
    "companyName",
    "primaryExchange",
    "calculationPrice",
    "open",
    "openTime",
    "openSource",
    "close",
    "closeTime",
    "closeSource",
    "high",
    "highTime",
    "highSource",
    "low",
    "lowTime",
    "lowSource",
    "latestPrice",
    "latestSource",
    "latestTime",
    "latestUpdate",
    "latestVolume",
    "iexRealtimePrice",
    "iexRealtimeSize",
    "iexLastUpdated",
    "delayedPrice",
    "delayedPriceTime",
    "oddLotDelayedPrice",
    "oddLotDelayedPriceTime",
    "extendedPrice",
    "extendedChange",
    "extendedChangePercent",
    "extendedPriceTime",
    "previousClose",
    "previousVolume",
    "change",
    "changePercent",
    "volume",
    "iexMarketPercent",
    "iexVolume",
    "avgTotalVolume",
    "iexBidPrice",
    "iexBidSize",
    "iexAskPrice",
    "iexAskSize",
    "iexOpen",
    "iexOpenTime",
    "iexClose",
    "iexCloseTime",
    "marketCap",
    "peRatio",
    "week52High",
    "week52Low",
    "ytdChange",
    "lastTradeTime",
    "currency",
    "isUSMarketOpen"
})
public class IexQuote {

  @JsonProperty("symbol")
  public String symbol;
  @JsonProperty("companyName")
  public String companyName;
  @JsonProperty("primaryExchange")
  public String primaryExchange;
  @JsonProperty("calculationPrice")
  public String calculationPrice;
  @JsonProperty("open")
  public Double open;
  @JsonProperty("openTime")
  public Long openTime;
  @JsonProperty("openSource")
  public String openSource;
  @JsonProperty("close")
  public Double close;
  @JsonProperty("closeTime")
  public Long closeTime;
  @JsonProperty("closeSource")
  public String closeSource;
  @JsonProperty("high")
  public Double high;
  @JsonProperty("highTime")
  public Long highTime;
  @JsonProperty("highSource")
  public String highSource;
  @JsonProperty("low")
  public Double low;
  @JsonProperty("lowTime")
  public Long lowTime;
  @JsonProperty("lowSource")
  public String lowSource;
  @JsonProperty("latestPrice")
  public Double latestPrice;
  @JsonProperty("latestSource")
  public String latestSource;
  @JsonProperty("latestTime")
  public String latestTime;
  @JsonProperty("latestUpdate")
  public Long latestUpdate;
  @JsonProperty("latestVolume")
  public Integer latestVolume;
  @JsonProperty("iexRealtimePrice")
  public Double iexRealtimePrice;
  @JsonProperty("iexRealtimeSize")
  public Integer iexRealtimeSize;
  @JsonProperty("iexLastUpdated")
  public Long iexLastUpdated;
  @JsonProperty("delayedPrice")
  public Double delayedPrice;
  @JsonProperty("delayedPriceTime")
  public Long delayedPriceTime;
  @JsonProperty("oddLotDelayedPrice")
  public Double oddLotDelayedPrice;
  @JsonProperty("oddLotDelayedPriceTime")
  public Long oddLotDelayedPriceTime;
  @JsonProperty("extendedPrice")
  public Double extendedPrice;
  @JsonProperty("extendedChange")
  public Double extendedChange;
  @JsonProperty("extendedChangePercent")
  public Double extendedChangePercent;
  @JsonProperty("extendedPriceTime")
  public Long extendedPriceTime;
  @JsonProperty("previousClose")
  public Double previousClose;
  @JsonProperty("previousVolume")
  public Integer previousVolume;
  @JsonProperty("change")
  public Double change;
  @JsonProperty("changePercent")
  public Double changePercent;
  @JsonProperty("volume")
  public Integer volume;
  @JsonProperty("iexMarketPercent")
  public Double iexMarketPercent;
  @JsonProperty("iexVolume")
  public Integer iexVolume;
  @JsonProperty("avgTotalVolume")
  public Integer avgTotalVolume;
  @JsonProperty("iexBidPrice")
  public Integer iexBidPrice;
  @JsonProperty("iexBidSize")
  public Integer iexBidSize;
  @JsonProperty("iexAskPrice")
  public Integer iexAskPrice;
  @JsonProperty("iexAskSize")
  public Integer iexAskSize;
  @JsonProperty("iexOpen")
  public Double iexOpen;
  @JsonProperty("iexOpenTime")
  public Long iexOpenTime;
  @JsonProperty("iexClose")
  public Double iexClose;
  @JsonProperty("iexCloseTime")
  public Long iexCloseTime;
  @JsonProperty("marketCap")
  public Long marketCap;
  @JsonProperty("peRatio")
  public Double peRatio;
  @JsonProperty("week52High")
  public Double week52High;
  @JsonProperty("week52Low")
  public Double week52Low;
  @JsonProperty("ytdChange")
  public Double ytdChange;
  @JsonProperty("lastTradeTime")
  public Long lastTradeTime;
  @JsonProperty("currency")
  public String currency;
  @JsonProperty("isUSMarketOpen")
  public Boolean isUSMarketOpen;

  public IexQuote() {
  }

  @Override
  public String toString() {
    return "IexQuote{" +
        "symbol='" + symbol + '\'' +
        ", companyName='" + companyName + '\'' +
        ", primaryExchange='" + primaryExchange + '\'' +
        ", calculationPrice='" + calculationPrice + '\'' +
        ", open=" + open +
        ", openTime=" + openTime +
        ", openSource='" + openSource + '\'' +
        ", close=" + close +
        ", closeTime=" + closeTime +
        ", closeSource='" + closeSource + '\'' +
        ", high=" + high +
        ", highTime=" + highTime +
        ", highSource='" + highSource + '\'' +
        ", low=" + low +
        ", lowTime=" + lowTime +
        ", lowSource='" + lowSource + '\'' +
        ", latestPrice=" + latestPrice +
        ", latestSource='" + latestSource + '\'' +
        ", latestTime='" + latestTime + '\'' +
        ", latestUpdate=" + latestUpdate +
        ", latestVolume=" + latestVolume +
        ", iexRealtimePrice=" + iexRealtimePrice +
        ", iexRealtimeSize=" + iexRealtimeSize +
        ", iexLastUpdated=" + iexLastUpdated +
        ", delayedPrice=" + delayedPrice +
        ", delayedPriceTime=" + delayedPriceTime +
        ", oddLotDelayedPrice=" + oddLotDelayedPrice +
        ", oddLotDelayedPriceTime=" + oddLotDelayedPriceTime +
        ", extendedPrice=" + extendedPrice +
        ", extendedChange=" + extendedChange +
        ", extendedChangePercent=" + extendedChangePercent +
        ", extendedPriceTime=" + extendedPriceTime +
        ", previousClose=" + previousClose +
        ", previousVolume=" + previousVolume +
        ", change=" + change +
        ", changePercent=" + changePercent +
        ", volume=" + volume +
        ", iexMarketPercent=" + iexMarketPercent +
        ", iexVolume=" + iexVolume +
        ", avgTotalVolume=" + avgTotalVolume +
        ", iexBidPrice=" + iexBidPrice +
        ", iexBidSize=" + iexBidSize +
        ", iexAskPrice=" + iexAskPrice +
        ", iexAskSize=" + iexAskSize +
        ", iexOpen=" + iexOpen +
        ", iexOpenTime=" + iexOpenTime +
        ", iexClose=" + iexClose +
        ", iexCloseTime=" + iexCloseTime +
        ", marketCap=" + marketCap +
        ", peRatio=" + peRatio +
        ", week52High=" + week52High +
        ", week52Low=" + week52Low +
        ", ytdChange=" + ytdChange +
        ", lastTradeTime=" + lastTradeTime +
        ", currency='" + currency + '\'' +
        ", isUSMarketOpen=" + isUSMarketOpen +
        '}';
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getPrimaryExchange() {
    return primaryExchange;
  }

  public void setPrimaryExchange(String primaryExchange) {
    this.primaryExchange = primaryExchange;
  }

  public String getCalculationPrice() {
    return calculationPrice;
  }

  public void setCalculationPrice(String calculationPrice) {
    this.calculationPrice = calculationPrice;
  }

  public Double getOpen() {
    return open;
  }

  public void setOpen(Double open) {
    this.open = open;
  }

  public Long getOpenTime() {
    return openTime;
  }

  public void setOpenTime(Long openTime) {
    this.openTime = openTime;
  }

  public String getOpenSource() {
    return openSource;
  }

  public void setOpenSource(String openSource) {
    this.openSource = openSource;
  }

  public Double getClose() {
    return close;
  }

  public void setClose(Double close) {
    this.close = close;
  }

  public Long getCloseTime() {
    return closeTime;
  }

  public void setCloseTime(Long closeTime) {
    this.closeTime = closeTime;
  }

  public String getCloseSource() {
    return closeSource;
  }

  public void setCloseSource(String closeSource) {
    this.closeSource = closeSource;
  }

  public Double getHigh() {
    return high;
  }

  public void setHigh(Double high) {
    this.high = high;
  }

  public Long getHighTime() {
    return highTime;
  }

  public void setHighTime(Long highTime) {
    this.highTime = highTime;
  }

  public String getHighSource() {
    return highSource;
  }

  public void setHighSource(String highSource) {
    this.highSource = highSource;
  }

  public Double getLow() {
    return low;
  }

  public void setLow(Double low) {
    this.low = low;
  }

  public Long getLowTime() {
    return lowTime;
  }

  public void setLowTime(Long lowTime) {
    this.lowTime = lowTime;
  }

  public String getLowSource() {
    return lowSource;
  }

  public void setLowSource(String lowSource) {
    this.lowSource = lowSource;
  }

  public Double getLatestPrice() {
    return latestPrice;
  }

  public void setLatestPrice(Double latestPrice) {
    this.latestPrice = latestPrice;
  }

  public String getLatestSource() {
    return latestSource;
  }

  public void setLatestSource(String latestSource) {
    this.latestSource = latestSource;
  }

  public String getLatestTime() {
    return latestTime;
  }

  public void setLatestTime(String latestTime) {
    this.latestTime = latestTime;
  }

  public Long getLatestUpdate() {
    return latestUpdate;
  }

  public void setLatestUpdate(Long latestUpdate) {
    this.latestUpdate = latestUpdate;
  }

  public Integer getLatestVolume() {
    return latestVolume;
  }

  public void setLatestVolume(Integer latestVolume) {
    this.latestVolume = latestVolume;
  }

  public Double getIexRealtimePrice() {
    return iexRealtimePrice;
  }

  public void setIexRealtimePrice(Double iexRealtimePrice) {
    this.iexRealtimePrice = iexRealtimePrice;
  }

  public Integer getIexRealtimeSize() {
    return iexRealtimeSize;
  }

  public void setIexRealtimeSize(Integer iexRealtimeSize) {
    this.iexRealtimeSize = iexRealtimeSize;
  }

  public Long getIexLastUpdated() {
    return iexLastUpdated;
  }

  public void setIexLastUpdated(Long iexLastUpdated) {
    this.iexLastUpdated = iexLastUpdated;
  }

  public Double getDelayedPrice() {
    return delayedPrice;
  }

  public void setDelayedPrice(Double delayedPrice) {
    this.delayedPrice = delayedPrice;
  }

  public Long getDelayedPriceTime() {
    return delayedPriceTime;
  }

  public void setDelayedPriceTime(Long delayedPriceTime) {
    this.delayedPriceTime = delayedPriceTime;
  }

  public Double getOddLotDelayedPrice() {
    return oddLotDelayedPrice;
  }

  public void setOddLotDelayedPrice(Double oddLotDelayedPrice) {
    this.oddLotDelayedPrice = oddLotDelayedPrice;
  }

  public Long getOddLotDelayedPriceTime() {
    return oddLotDelayedPriceTime;
  }

  public void setOddLotDelayedPriceTime(Long oddLotDelayedPriceTime) {
    this.oddLotDelayedPriceTime = oddLotDelayedPriceTime;
  }

  public Double getExtendedPrice() {
    return extendedPrice;
  }

  public void setExtendedPrice(Double extendedPrice) {
    this.extendedPrice = extendedPrice;
  }

  public Double getExtendedChange() {
    return extendedChange;
  }

  public void setExtendedChange(Double extendedChange) {
    this.extendedChange = extendedChange;
  }

  public Double getExtendedChangePercent() {
    return extendedChangePercent;
  }

  public void setExtendedChangePercent(Double extendedChangePercent) {
    this.extendedChangePercent = extendedChangePercent;
  }

  public Long getExtendedPriceTime() {
    return extendedPriceTime;
  }

  public void setExtendedPriceTime(Long extendedPriceTime) {
    this.extendedPriceTime = extendedPriceTime;
  }

  public Double getPreviousClose() {
    return previousClose;
  }

  public void setPreviousClose(Double previousClose) {
    this.previousClose = previousClose;
  }

  public Integer getPreviousVolume() {
    return previousVolume;
  }

  public void setPreviousVolume(Integer previousVolume) {
    this.previousVolume = previousVolume;
  }

  public Double getChange() {
    return change;
  }

  public void setChange(Double change) {
    this.change = change;
  }

  public Double getChangePercent() {
    return changePercent;
  }

  public void setChangePercent(Double changePercent) {
    this.changePercent = changePercent;
  }

  public Integer getVolume() {
    return volume;
  }

  public void setVolume(Integer volume) {
    this.volume = volume;
  }

  public Double getIexMarketPercent() {
    return iexMarketPercent;
  }

  public void setIexMarketPercent(Double iexMarketPercent) {
    this.iexMarketPercent = iexMarketPercent;
  }

  public Integer getIexVolume() {
    return iexVolume;
  }

  public void setIexVolume(Integer iexVolume) {
    this.iexVolume = iexVolume;
  }

  public Integer getAvgTotalVolume() {
    return avgTotalVolume;
  }

  public void setAvgTotalVolume(Integer avgTotalVolume) {
    this.avgTotalVolume = avgTotalVolume;
  }

  public Integer getIexBidPrice() {
    return iexBidPrice;
  }

  public void setIexBidPrice(Integer iexBidPrice) {
    this.iexBidPrice = iexBidPrice;
  }

  public Integer getIexBidSize() {
    return iexBidSize;
  }

  public void setIexBidSize(Integer iexBidSize) {
    this.iexBidSize = iexBidSize;
  }

  public Integer getIexAskPrice() {
    return iexAskPrice;
  }

  public void setIexAskPrice(Integer iexAskPrice) {
    this.iexAskPrice = iexAskPrice;
  }

  public Integer getIexAskSize() {
    return iexAskSize;
  }

  public void setIexAskSize(Integer iexAskSize) {
    this.iexAskSize = iexAskSize;
  }

  public Double getIexOpen() {
    return iexOpen;
  }

  public void setIexOpen(Double iexOpen) {
    this.iexOpen = iexOpen;
  }

  public Long getIexOpenTime() {
    return iexOpenTime;
  }

  public void setIexOpenTime(Long iexOpenTime) {
    this.iexOpenTime = iexOpenTime;
  }

  public Double getIexClose() {
    return iexClose;
  }

  public void setIexClose(Double iexClose) {
    this.iexClose = iexClose;
  }

  public Long getIexCloseTime() {
    return iexCloseTime;
  }

  public void setIexCloseTime(Long iexCloseTime) {
    this.iexCloseTime = iexCloseTime;
  }

  public Long getMarketCap() {
    return marketCap;
  }

  public void setMarketCap(Long marketCap) {
    this.marketCap = marketCap;
  }

  public Double getPeRatio() {
    return peRatio;
  }

  public void setPeRatio(Double peRatio) {
    this.peRatio = peRatio;
  }

  public Double getWeek52High() {
    return week52High;
  }

  public void setWeek52High(Double week52High) {
    this.week52High = week52High;
  }

  public Double getWeek52Low() {
    return week52Low;
  }

  public void setWeek52Low(Double week52Low) {
    this.week52Low = week52Low;
  }

  public Double getYtdChange() {
    return ytdChange;
  }

  public void setYtdChange(Double ytdChange) {
    this.ytdChange = ytdChange;
  }

  public Long getLastTradeTime() {
    return lastTradeTime;
  }

  public void setLastTradeTime(Long lastTradeTime) {
    this.lastTradeTime = lastTradeTime;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Boolean getUSMarketOpen() {
    return isUSMarketOpen;
  }

  public void setUSMarketOpen(Boolean USMarketOpen) {
    isUSMarketOpen = USMarketOpen;
  }
}

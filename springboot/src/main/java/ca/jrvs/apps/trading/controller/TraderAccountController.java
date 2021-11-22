package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import ca.jrvs.apps.trading.service.TraderAccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.sql.Date;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/trader")
public class TraderAccountController {

  private TraderAccountService traderAccountService;

  @Autowired
  public TraderAccountController(TraderAccountService traderAccountService) {
    this.traderAccountService = traderAccountService;
  }

  /**
   * Create trader using path params
   * @param firstname
   * @param lastname
   * @param dob
   * @param country
   * @param email
   * @return
   */
  @PostMapping(
      path = "/firstname/{firstname}/lastname/{lastname}/dob/{dob}/country/{country}/email/{email}",
      produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public TraderAccountView createTrader(
      @PathVariable String firstname,
      @PathVariable String lastname,
      @PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate dob,
      @PathVariable String country,
      @PathVariable String email) {
    try {
      Trader trader = new Trader();
      trader.setFirstName(firstname);
      trader.setLastName(lastname);
      trader.setCountry(country);
      trader.setEmail(email);
      trader.setDob(Date.valueOf(dob));
      return traderAccountService.createTraderAndAccount(trader);
    } catch (Exception e) {
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }

  /**
   * Create trader using DTO (RequestBody)
   * @param trader
   * @return
   */
  @PostMapping(
      path = "/",
      produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public TraderAccountView createTrader(@RequestBody Trader trader) {
    try {
      return traderAccountService.createTraderAndAccount(trader);
    } catch (Exception e) {
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }

  @DeleteMapping(path = "/traderId/{traderId}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteTrader(@PathVariable Integer traderId) {
    try {
      traderAccountService.deleteTraderById(traderId);
    } catch (Exception e) {
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @PutMapping(path = "/deposit/traderId/{traderId}/amount/{amount}")
  public Account depositFund(@PathVariable Integer traderId, @PathVariable Double amount) {
    try {
      return traderAccountService.deposit(traderId, amount);
    } catch (Exception e) {
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @PutMapping(path = "/withdraw/traderId/{traderId}/amount/{amount}")
  public Account withdrawFund(@PathVariable Integer traderId, @PathVariable Double amount) {
    try {
      return traderAccountService.withdraw(traderId, amount);
    } catch (Exception e) {
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }
}

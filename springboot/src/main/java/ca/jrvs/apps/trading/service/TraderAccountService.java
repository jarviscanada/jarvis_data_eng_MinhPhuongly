package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraderAccountService {
  private TraderDao traderDao;
  private AccountDao accountDao;
  private PositionDao positionDao;
  private SecurityOrderDao securityOrderDao;
  private Account anAccount;

  @Autowired
  public TraderAccountService(TraderDao traderDao, AccountDao accountDao,
      PositionDao positionDao, SecurityOrderDao securityOrderDao) {
    this.traderDao = traderDao;
    this.accountDao = accountDao;
    this.positionDao = positionDao;
    this.securityOrderDao = securityOrderDao;
  }

  /**
   * Create a new trader and initialize a new account with 0 amount
   * @param trader
   * @return
   */
  public TraderAccountView createTraderAndAccount(Trader trader){
    anAccount = new Account();
    anAccount.setTraderId(trader.getId());
    anAccount.setAmount(0.0);
    return new TraderAccountView(traderDao.save(trader), accountDao.save(anAccount));
  }

  /**
   * A Trader can be deleted if it has no open position and 0 cash balance
   * - validate trader Id
   * - get trader account by traderId and check account balance
   * - get positions by accountId and check positions
   * - delete all securityOrders, account, trader (in this order)
   * @param traderId
   */
  public void deleteTraderById(Integer traderId){
    anAccount = accountDao.findById(traderId).get();
    if (anAccount.getAmount().equals(0.0) &&
        !positionDao.findById(anAccount.getId()).isPresent()){
      securityOrderDao.deleteById(traderId);
      accountDao.deleteById(traderId);
      traderDao.deleteById(traderId);
    }
    else{
      throw new RuntimeException("You can't delete a trader with opening positions or balance >0");
    }
  }

  /**
   * Deposit a fund to an account by traderId
   * - validate user input
   * - account = accountDao.findByTraderId
   * - accountDao.updateAmountById
   * @param traderId must not be null
   * @param fund mustbe greater than 0
   * @return updated Account
   */
  public Account deposit(Integer traderId, Double fund){
    if (fund <= 0){
      throw new IllegalArgumentException("fund must be greater than 0");
    }
    anAccount = accountDao.findById(traderId).get();
    anAccount.setAmount(anAccount.getAmount() + fund);
    accountDao.updateOne(anAccount);
    return anAccount;
  }

  /**
   * Withdraw a fund to an account by traderId
   * - validate user input
   * account = accountDao.findByTraderId
   * - accountDao.updateAmountById
   * @param traderId
   * @param fund must be greater than 0
   * @return updated Account
   */
  public Account withdraw(Integer traderId, Double fund){
    anAccount = accountDao.findById(traderId).get();
    anAccount.setAmount(anAccount.getAmount() - fund);
    accountDao.updateOne(anAccount);
    return anAccount;
  }

}

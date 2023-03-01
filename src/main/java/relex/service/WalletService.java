package relex.service;

import relex.controller.Wallet.Models.*;

import relex.service.Models.CurrencyRateModel;
import relex.service.Models.ExchangeModel;
import relex.service.Models.WalletModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public interface  WalletService {
    ArrayList<WalletModel> getWallets(String key);
    WalletModel updateMoney( DepositRequest depositRequest) ;
    WalletModel withDraw( WithdrawalRequest withdrawalRequest) ;
    ArrayList<CurrencyRateModel> getRate(String key,String currency) ;
    public ExchangeModel exchangeMoney(ExchangeRequest exchangeRequest) ;

    public BigDecimal getSumCurrency(String key,String currency);

    public Integer countTransaction(String key,Date dateFrom, Date dateTo);
}

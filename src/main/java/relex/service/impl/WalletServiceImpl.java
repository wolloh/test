package relex.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import relex.controller.Wallet.Models.*;
import relex.exception.NoSuchUserException;
import relex.exception.NotEnoughMoneyException;
import relex.exception.RoleGrantException;
import relex.models.CurrencyRate;
import relex.models.Wallet;
import relex.repository.WalletRepository;
import relex.service.Models.CurrencyRateModel;
import relex.service.Models.ExchangeModel;
import relex.service.Models.WalletModel;
import relex.service.WalletService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class WalletServiceImpl  implements WalletService {

    private final WalletRepository repository;


    @Override
    public ArrayList<WalletModel> getWallets(String key)   {
        ArrayList<Wallet> wallets=repository.getWalletByKey(key);
        if(wallets.size()==0){
            throw new NoSuchUserException("There is no user with this key");
        }
        ArrayList<WalletModel> walletsBalance=new ArrayList<>();
        wallets.forEach(wallet->walletsBalance.add(
                new WalletModel(wallet.getWallet_type().getType(),wallet.getBalance())
        ));
        return walletsBalance;
    }


    @Override
    public WalletModel updateMoney(DepositRequest depositRequest)  {
        String walletType=getWalletType(depositRequest);
        Wallet wallet=repository.getWalletByKeyAndType(depositRequest.getKey(),walletType);
        if(wallet==null){
            throw new NoSuchUserException("There is no user with this key");
        }
        BigDecimal balance=wallet.getBalance();
        wallet.setBalance(balance.add(depositRequest.balanceByType(walletType)));
        repository.save(wallet);
        return new WalletModel(walletType,wallet.getBalance());
    }

    @Override
    public WalletModel withDraw(WithdrawalRequest withdrawalRequest)  {
        Wallet wallet=repository.getWalletByKeyAndType(withdrawalRequest.getKey(),withdrawalRequest.currency+"_wallet");
        if(wallet==null){
            throw new NoSuchUserException("There is no user with this key");
        }
        BigDecimal balance=wallet.getBalance();
        if(balance.compareTo(withdrawalRequest.count)<0){
            throw  new NotEnoughMoneyException("You dont have enough money to perform operation");
        }

        wallet.setBalance(balance.subtract(withdrawalRequest.count));
        repository.save(wallet);
        return new WalletModel(withdrawalRequest.currency+"_wallet",wallet.getBalance());
    }



    @Override
    public ArrayList<CurrencyRateModel> getRate(String key,String currency)  {
        CurrencyRate rate=repository.getRateByCurrencyAndKey(currency,key);
        if(rate==null){
            throw new NoSuchUserException("There is no user with this key");
        }
        ArrayList<CurrencyRateModel> currencyRates=new ArrayList<>();
        if(rate.getRUB_rate()!=new BigDecimal(1)){
            currencyRates.add(new CurrencyRateModel("RUB",rate.getRUB_rate()));
        }
        if(rate.getBTC_rate()!=new BigDecimal(1)){
            currencyRates.add(new CurrencyRateModel("BTC",rate.getBTC_rate()));
        }
        if(rate.getTON_rate()!=new BigDecimal(1)){
            currencyRates.add(new CurrencyRateModel("TON",rate.getTON_rate()));
        }
        return currencyRates;
    }




    @Override
    public ExchangeModel exchangeMoney(ExchangeRequest exchangeRequest)  {
        BigDecimal amount=exchangeRequest.getAmount();
        String currencyFrom=exchangeRequest.getCurrencyFrom();
        String currencyTo=exchangeRequest.getCurrencyTo();
        String key=exchangeRequest.getKey();
        CurrencyRate rate=repository.getRateByCurrencyAndKey(currencyFrom,key);
        Wallet walletFrom=repository.getWalletByKeyAndType(key,currencyFrom+"_wallet");
        Wallet walletTo=repository.getWalletByKeyAndType(key,currencyTo+"_wallet");
        if(rate==null || walletFrom==null || walletTo==null){
            throw new NoSuchUserException("There is no user with this key");
        }
        if(walletFrom.getBalance().compareTo(exchangeRequest.getAmount())<0){
            throw  new NotEnoughMoneyException("You dont have enough money to perform operation");
        }
        BigDecimal rateCurrency=rate.getRateToCurr(currencyTo);
        walletFrom.setBalance(walletFrom.getBalance().subtract(amount));
        walletTo.setBalance(amount.multiply(rateCurrency));
        repository.save(walletFrom);
        repository.save(walletTo);
        return new ExchangeModel(currencyFrom,currencyTo,amount,walletTo.getBalance());
    }


    @Override
    public BigDecimal getSumCurrency(String key,String currency)  {
        if(!key.equals(System.getenv("ADMIN_KEY"))){
            throw  new RoleGrantException("Dont enough rights");
        }
        BigDecimal sum=repository.getSumCurrency(currency+"_wallet");
        return sum;
    }


    @Override
    public Integer countTransaction(String key,Date dateFrom,Date dateTo)  {
        if(!key.equals(System.getenv("ADMIN_KEY"))){
            throw  new RoleGrantException("Dont enough rights");
        }
        Integer res=repository.getTransactionCount(dateFrom,dateTo);
        return res;
    }

    public String getWalletType(DepositRequest depositRequest){
        String result="";
        if(depositRequest.TON_wallet!=null)
            result="Ton_wallet";
        if(depositRequest.BTC_wallet!=null)
            result="BTC_wallet";
        if(depositRequest.RUB_wallet!=null)
            result="RUB_wallet";
        return result;
    }
}

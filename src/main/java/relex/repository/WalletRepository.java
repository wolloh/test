package relex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import relex.models.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;


@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Query(value = "select w from Wallet w where w.user.secretkey= :key ")
    ArrayList<Wallet> getWalletByKey(String key);

    @Query(value = "select w from Wallet w where w.user.secretkey= :key and w.wallet_type.type=:type")
    Wallet getWalletByKeyAndType(String key,String type);

    @Query(value="select c from CurrencyRate c,User u where c.currency.type= :currency and u.secretkey=:key")
    CurrencyRate getRateByCurrencyAndKey(String currency,String key);

    @Query(value="select c from CurrencyRate c")
    ArrayList<CurrencyRate> getAllRates();


    @Query(value="select sum(w.balance) from Wallet w where w.wallet_type.type=:currency")
    BigDecimal getSumCurrency(String currency);


    @Query(value="select count(t) from Transaction t where t.date between :dateFrom and :dateTo")
    Integer getTransactionCount(Date dateFrom,Date dateTo);
}
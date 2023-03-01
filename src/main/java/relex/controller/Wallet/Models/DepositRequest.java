package relex.controller.Wallet.Models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositRequest {
    public String key;
    public BigDecimal BTC_wallet;
    public BigDecimal RUB_wallet;
    public BigDecimal TON_wallet;
    public BigDecimal balanceByType(String type){
        BigDecimal res=new BigDecimal(-1);
        switch (type){
            case ("BTC_wallet"):
                res=BTC_wallet;
                break;
            case("RUB_wallet"):
                res=RUB_wallet;
                break;
            case("TON_wallet"):
                res=TON_wallet;
                break;
        }
        return res;
    }

}

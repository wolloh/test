package relex.service.Models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class CurrencyRateModel {
    private String currencyType;
    private BigDecimal rate;

    public String getRate() {
        return rate.toPlainString();
    }

    public CurrencyRateModel(String currencyType, BigDecimal rate){
        this.currencyType=currencyType;
        this.rate=rate;
    }
}

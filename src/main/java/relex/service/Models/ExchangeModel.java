package relex.service.Models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ExchangeModel {
    private String currencyFrom;
    private String currencyTo;
    private BigDecimal amountFrom;
    private BigDecimal amountTo;

    public String getAmountFrom() {
        return amountFrom.toPlainString();
    }
    public String getAmountTo() {
        return amountTo.toPlainString();
    }

    public ExchangeModel(String currencyFrom,String currencyTo,BigDecimal amountFrom,BigDecimal amountTo){
        this.amountFrom=amountFrom;
        this.amountTo=amountTo;
        this.currencyFrom=currencyFrom;
        this.currencyTo=currencyTo;
    }
}

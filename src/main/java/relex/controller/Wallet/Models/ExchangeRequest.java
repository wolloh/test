package relex.controller.Wallet.Models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ExchangeRequest {
    private String key;
    private String currencyFrom;
    private String currencyTo;
    private BigDecimal amount;
}

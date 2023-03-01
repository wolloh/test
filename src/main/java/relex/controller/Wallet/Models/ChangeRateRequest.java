package relex.controller.Wallet.Models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ChangeRateRequest
{
    private String key;
    private String baseCurr;
    private BigDecimal BTC;
    private BigDecimal RUB;
    private BigDecimal TON;
}

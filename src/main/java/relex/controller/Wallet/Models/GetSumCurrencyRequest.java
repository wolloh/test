package relex.controller.Wallet.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSumCurrencyRequest {
    private String key;
    private String currency;
}

package relex.controller.Wallet.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateRequest {
    private String key;
    private String currency;
    public RateRequest(String key,String currency){
        this.currency=currency;
        this.key=key;
    }
}

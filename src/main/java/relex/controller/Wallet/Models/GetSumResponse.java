package relex.controller.Wallet.Models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class GetSumResponse {
    private String type;
    private BigDecimal amount;
    public GetSumResponse(String type,BigDecimal amount){
        this.type=type;
        this.amount=amount;
    }

}


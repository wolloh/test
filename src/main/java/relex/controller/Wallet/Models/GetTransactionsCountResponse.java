package relex.controller.Wallet.Models;

import lombok.Getter;
import lombok.Setter;
;

@Getter
@Setter
public class GetTransactionsCountResponse {
    private Integer transactionCount;
    public GetTransactionsCountResponse(Integer transactionCount){
        this.transactionCount=transactionCount;
    }
}

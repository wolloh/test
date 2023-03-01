package relex.controller.Wallet.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RUB_wallet extends WalletResponse {
    @JsonProperty("RUB_wallet")
    private BigDecimal RUB_wallet;
    public RUB_wallet(BigDecimal balance){
        RUB_wallet=balance;
    }
    public String getRUB_wallet() {
        return RUB_wallet.toPlainString();
    }
}

package relex.controller.Wallet.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class TON_wallet extends WalletResponse {
    @JsonProperty("TON_wallet")

    private BigDecimal TON_wallet;
    public  TON_wallet(BigDecimal balance){
        TON_wallet=balance;
    }

    public String getTON_wallet() {
        return TON_wallet.toPlainString();
    }
}

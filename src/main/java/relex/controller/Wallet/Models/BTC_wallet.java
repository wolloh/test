package relex.controller.Wallet.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
@Getter
@Setter
public class BTC_wallet extends WalletResponse {
    @JsonProperty("BTC_wallet")
    private BigDecimal BTC_wallet;
    public  BTC_wallet(BigDecimal balance){
        BTC_wallet=balance;
    }
    public String getBTC_wallet() {
        return BTC_wallet.toPlainString();
    }
}

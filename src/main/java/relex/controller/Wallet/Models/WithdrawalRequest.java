package relex.controller.Wallet.Models;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WithdrawalRequest {
    public String key;
    public String currency;
    public BigDecimal count;
    @JsonAlias({"credit_card","wallet"})
    public String walletToTransfer;

}

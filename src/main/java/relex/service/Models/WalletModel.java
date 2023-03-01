package relex.service.Models;


import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;


@Getter
@Setter
public class WalletModel {

    private String walletType;

    private BigDecimal balance;

    public WalletModel(String walletType,BigDecimal balance) {
        this.walletType=walletType;
        this.balance=balance;
    }

}

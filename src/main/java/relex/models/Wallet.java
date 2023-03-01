package relex.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    private BigDecimal balance;
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    private User user;
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    private WalletType wallet_type;
    public Wallet(){}
    public Wallet(BigDecimal balance){
       this.balance=balance;
    }
}

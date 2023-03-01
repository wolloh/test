package relex.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name="transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @NotNull
    private Wallet wallet_id;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private Date date;
    @NotNull
    private String transactionType;
    public Transaction(){}
    public Transaction(BigDecimal amount,Date date,String transactionType){
        this.amount=amount;
        this.date=date;
        this.transactionType=transactionType;
    }
}

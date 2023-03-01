package relex.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import relex.exception.IncorrectCurrencyException;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name="currency_rate")
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotNull

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    private Currency currency;
    @NotNull
    private BigDecimal RUB_rate;
    @NotNull
    private BigDecimal TON_rate;
    @NotNull
    private BigDecimal BTC_rate;

    public CurrencyRate(){}
    public CurrencyRate(BigDecimal RUB,BigDecimal TON,BigDecimal BTC){
        this.RUB_rate=RUB;
        this.BTC_rate=BTC;
        this.TON_rate=TON;
    }
    public BigDecimal getRateToCurr(String toCurr)  {
        if(toCurr.equals("RUB")){
            return RUB_rate;
        }
        if(toCurr.equals("BTC")){
            return BTC_rate;
        }
        if(toCurr.equals("TON")){
            return TON_rate;
        }
        else throw  new IncorrectCurrencyException("There is no such currency");
    }


}

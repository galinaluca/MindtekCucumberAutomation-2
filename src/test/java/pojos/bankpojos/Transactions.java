package pojos.bankpojos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class Transactions {
    private Double amount;
    private LocalDateTime date;
    private Integer transactionId;
    private String transactionName;
}

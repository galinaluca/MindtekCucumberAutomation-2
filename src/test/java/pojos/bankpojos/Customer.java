package pojos.bankpojos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Customer {
    private String accountOpenDate;
    private List<Account> accounts;
    private Boolean active;
    private String address;
    private String fullName;
    private Integer id;
    private Boolean isActive;

}

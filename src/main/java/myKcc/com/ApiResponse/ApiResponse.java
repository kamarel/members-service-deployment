package myKcc.com.ApiResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myKcc.com.Entity.CashPayment;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String fullName;
    private List<CashPayment> cashPayments;

    // Constructor used in the query
    public ApiResponse(String fullName, CashPayment cashPayment) {
        this.fullName = fullName;
        this.cashPayments = List.of(cashPayment);
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<CashPayment> getCashPayments() {
        return cashPayments;
    }

    public void setCashPayments(List<CashPayment> cashPayments) {
        this.cashPayments = cashPayments;
    }
}

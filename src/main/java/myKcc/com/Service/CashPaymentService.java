package myKcc.com.Service;

import myKcc.com.ApiResponse.ApiResponse;
import myKcc.com.Entity.CashPayment;
import myKcc.com.Entity.Members;

import java.util.List;

public interface CashPaymentService {

    CashPayment makePayment(String memberId, CashPayment payment);


    List<CashPayment> getCashPaymentByMemberName(String fullName);

    void checkMonthlyPayment(Members members);

    List<Members>getMembersWhoMissedPayment();

    void deleteCashPaymentById(Long id);


    List<ApiResponse> getAllCashPaymentsWithMemberFullName();

    CashPayment updatePayment(Long id, CashPayment cashPayment);

}

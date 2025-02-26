package myKcc.com.Service;


import myKcc.com.Entity.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentCheckScheduler {

    @Autowired
    private CashPaymentServiceImp paymentServiceImp;

    @Autowired
    private MembersServiceImp membersServiceImp;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void checkAllMembersPayment(){
        List<Members> members = membersServiceImp.getAllMembers();
        for(Members members1 : members){
            paymentServiceImp.checkMonthlyPayment(members1);
        }
    }

}

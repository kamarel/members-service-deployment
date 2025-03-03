
package myKcc.com.Service;


import myKcc.com.ApiResponse.ApiResponse;
import myKcc.com.Entity.CashPayment;
import myKcc.com.Entity.Members;
import myKcc.com.Repository.CashPaymentRepository;
import myKcc.com.Repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CashPaymentServiceImp implements CashPaymentService {

    @Autowired
    private CashPaymentRepository cashPaymentRepository;
    @Autowired
    private MembersRepository membersRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public CashPayment makePayment(String memberId, CashPayment payment) {
        Members members = membersRepository.findByMemberId(memberId);

        payment.setMember(members);
        payment.setDate(LocalDate.now());

        CashPayment savedPayment = cashPaymentRepository.save(payment);

        return savedPayment;
    }

    @Override
    public CashPayment updatePayment(Long paymentId, CashPayment updatedPayment) {
        // Retrieve the payment by ID
        Optional<CashPayment> optionalPayment = cashPaymentRepository.findById(paymentId);

        // If the payment does not exist, throw an exception
        if (optionalPayment.isEmpty()) {
            throw new IllegalArgumentException("Payment with ID " + paymentId + " not found.");
        }

        CashPayment existingPayment = optionalPayment.get();

        // Update fields from the provided updatedPayment
        existingPayment.setMethod(updatedPayment.getMethod());
        existingPayment.setAmount(updatedPayment.getAmount());
        existingPayment.setCashierName(updatedPayment.getCashierName());

        // Update the date. Use the updatedPayment's date if provided, otherwise default to the current date
        existingPayment.setDate(updatedPayment.getDate() != null ? updatedPayment.getDate() : LocalDate.now());

        // Save the updated payment and return it
        return cashPaymentRepository.save(existingPayment);
    }




    @Override
    public List<CashPayment> getCashPaymentByMemberName(String fullName) {
        List<CashPayment>cashPaymentList = cashPaymentRepository.findByMemberName(fullName);
        return cashPaymentList;
    }

    @Override
    public void checkMonthlyPayment(Members members) {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYears = currentDate.getYear();

        List<CashPayment> payments = cashPaymentRepository.findByMemberAndYearAndMonth(members.getId(), currentYears, currentMonth);

        if(payments.isEmpty()){
            emailService.sendPaymentNotification(members.getEmail(), "Please contact your parish leader to make a payment");
        }
    }

    @Override
    public List<Members> getMembersWhoMissedPayment() {
        return cashPaymentRepository.findMembersWhoMissedPayment();
    }

    @Override
    public void deleteCashPaymentById(Long id) {
        cashPaymentRepository.deleteById(id);
    }

    @Override
    public List<ApiResponse> getAllCashPaymentsWithMemberFullName() {
        return cashPaymentRepository.findAllCashPaymentsWithMemberFullName();
    }







}

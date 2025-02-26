package myKcc.com.Repository;

import myKcc.com.ApiResponse.ApiResponse;
import myKcc.com.Entity.CashPayment;
import myKcc.com.Entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashPaymentRepository extends JpaRepository<CashPayment, Long> {

    @Query("SELECT cp FROM CashPayment cp WHERE cp.member.fullName = :fullName")
    List<CashPayment> findByMemberName(@Param("fullName") String fullName);

    @Query("SELECT p FROM CashPayment p WHERE p.member.id = :memberId AND YEAR(p.date) = :year AND MONTH(p.date) = :month")
    List<CashPayment> findByMemberAndYearAndMonth(Long memberId, int year, int month);


    // Query to find members who haven't made any payments this month
    @Query("SELECT m FROM Members m WHERE m.id NOT IN (" +
            "SELECT p.member.id FROM CashPayment p WHERE MONTH(p.date) = MONTH(CURRENT_DATE) AND YEAR(p.date) = YEAR(CURRENT_DATE))")
    List<Members> findMembersWhoMissedPayment();


    @Query("SELECT new myKcc.com.ApiResponse.ApiResponse(m.fullName, c) FROM CashPayment c JOIN c.member m")
    List<ApiResponse> findAllCashPaymentsWithMemberFullName();


    @Query("SELECT c FROM CashPayment c WHERE c.member.memberId = :memberId")
    List<CashPayment> findByMemberId(String memberId);



    void deleteByMember(Members member);


}

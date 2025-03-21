package myKcc.com.Controller;

import myKcc.com.ApiResponse.ApiResponse;
import myKcc.com.ApiResponse.ParishResponseApi;
import myKcc.com.Entity.CashPayment;
import myKcc.com.Entity.Members;
import myKcc.com.Service.CashPaymentServiceImp;
import myKcc.com.Service.EmailService;
import myKcc.com.Service.MembersServiceImp;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/members")
public class MembersController {

    @Autowired
    private MembersServiceImp membersServiceImp;

    @Autowired
    private CashPaymentServiceImp cashPaymentServiceImp;

    @Autowired
    private EmailService emailService;

    @Autowired
    private WebClient webClient;

    @GetMapping("/cash")
    public ResponseEntity<List<ApiResponse>> getAllCashPaymentsWithMemberFullName() {
        return ResponseEntity.ok(cashPaymentServiceImp.getAllCashPaymentsWithMemberFullName());
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<CashPayment>updateCashPayment(@PathVariable Long id, @RequestBody CashPayment updatePayment){
        CashPayment updated = cashPaymentServiceImp.updatePayment(id, updatePayment);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/pays")
    public ResponseEntity<List<CashPayment>>getCashPaymentsByMemberName(@RequestParam String fullName){
        List<CashPayment>payments = cashPaymentServiceImp.getCashPaymentByMemberName(fullName);
        return new ResponseEntity<>(payments, HttpStatus.FOUND);
    }

    @GetMapping("/missed")
    public List<Members>getMembersWhoMissedPayment(){
        return cashPaymentServiceImp.getMembersWhoMissedPayment();
    }


    @PostMapping("/{memberId}/pay")
    public ResponseEntity<CashPayment> makePayment(@PathVariable String memberId, @RequestBody CashPayment cashPayment) {
        try {
            // Validate the memberId and cashPayment fields
            Members members = membersServiceImp.findByMemberId(memberId);
            if (members == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            CashPayment savedPayment = cashPaymentServiceImp.makePayment(memberId, cashPayment);
            emailService.sendConfirmationCode(members.getEmail(), savedPayment.getAmount());
            return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Member not found
        } catch (Exception e) {
            // Log the exception (consider using a logging framework)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // General error
        }
    }



    @DeleteMapping("/{id}/delete/pay")
    public ResponseEntity<String>deletedPaymentById(@PathVariable Long id){
        cashPaymentServiceImp.deleteCashPaymentById(id);

        return new ResponseEntity<>("Cash payment deleted", HttpStatus.OK);
    }

    // Get all members
    @GetMapping
    public ResponseEntity<List<Members>> getAllMembers(){
        return ResponseEntity.ok(membersServiceImp.getAllMembers());
    }

    // Get member by numerical ID (Long)
    @GetMapping("/id/{id}")
    public ResponseEntity<Members> getMembersById(@PathVariable("id") Long id){
        return ResponseEntity.ok(membersServiceImp.getById(id));
    }

    // Create a new member
    @PostMapping
    public ResponseEntity<Members> createMembers(@RequestBody Members members,  @RequestHeader(name = "Authorization", required = true) String authorizationHeader){

        String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : authorizationHeader;
        Members members1 = membersServiceImp.saveMembers(members, token);

        //emailService.sendRegistration(members1.getEmail(), members1.getParishName());

        return ResponseEntity.ok(members1);
    }









    // Update a member by numerical ID (Long)
    @PutMapping("/id/{id}")
    public ResponseEntity<Members> updateMember(@RequestBody Members members, @PathVariable("id") Long id){
        return ResponseEntity.ok(membersServiceImp.updateMembers(id, members));
    }

    // Delete a member by numerical ID (Long)
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteMember(@PathVariable("id") Long id){
        return ResponseEntity.ok(membersServiceImp.deleteMemberById(id));
    }

    // Search for members by query string
    @GetMapping("/search")
    public ResponseEntity<List<Members>> searchMember(@RequestParam("query") String query){
        return new ResponseEntity<>(membersServiceImp.searchMembers(query), HttpStatus.FOUND);
    }

    // Delete all members
    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllMembers(){
        membersServiceImp.deleteAllMembers();
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    // Delete a member by member ID (String)
    @DeleteMapping("/delete/{memberId}")
    public ResponseEntity<Void> deleteByMemberId(@PathVariable("memberId") String memberId){
        membersServiceImp.deleteByMemberId(memberId);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/getBy/{memberId}")
    public ResponseEntity<Members> getMembersById(@PathVariable("memberId") String memberId) {
        Members members = membersServiceImp.findByMemberId(memberId);

        if (members != null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(members);
    }



    @GetMapping("/fullName/{fullName}")
    public ResponseEntity<Members>findByFullName(@PathVariable("fullName") String fullName){
        return new ResponseEntity<>(membersServiceImp.findByFullName(fullName), HttpStatus.FOUND);
    }


    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteMemberWithPayment(@PathVariable Long id) {
        membersServiceImp.deleteMemberWithPayments(id);
        return ResponseEntity.noContent().build();
    }








}

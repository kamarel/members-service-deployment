package myKcc.com.Service;

import jakarta.transaction.Transactional;
import myKcc.com.ApiResponse.ParishDto;
import myKcc.com.ApiResponse.ParishResponseApi;
import myKcc.com.Entity.Members;
import myKcc.com.Repository.CashPaymentRepository;
import myKcc.com.Repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MembersServiceImp implements MembersService {

    @Autowired
    private WebClient webClient;

    @Autowired
    private MembersRepository membersRepository;

    @Autowired
    private CashPaymentRepository cashPaymentRepository;

    @Override
    public List<Members> getAllMembers() {
        return membersRepository.findAll();
    }

    @Override
    public Members getById(Long id) {
        return membersRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
    }

    @Override
    public Members saveMembers(Members members) {

        // Save the member to the database
        Members savedMember = membersRepository.save(members);



            savedMember.setParishName(savedMember.getParishName());


        // Save the member again with the assigned parish
        return membersRepository.save(savedMember);

    }







    @Override
    public Members updateMembers(Long id, Members members) {
        Members existingMember = membersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        existingMember.setMemberId(members.getMemberId());
        existingMember.setFullName(members.getFullName());
        existingMember.setEmail(members.getEmail());
        existingMember.setDateOfBirth(members.getDateOfBirth());
        existingMember.setSex(members.getSex());
        existingMember.setPhoneNumber(members.getPhoneNumber());
        existingMember.setMaritalStatus(members.getMaritalStatus());
        existingMember.setMemberRank(members.getMemberRank());
        existingMember.setMemberRole(members.getMemberRole());
        existingMember.setNationality(members.getNationality());
        existingMember.setPlaceOfBirth(members.getPlaceOfBirth());
        existingMember.setAddress(members.getAddress());
        existingMember.setState(members.getState());
        existingMember.setCity(members.getCity());
        existingMember.setZipCode(members.getZipCode());
        existingMember.setParishName(members.getParishName());

        return membersRepository.save(existingMember);
    }

    @Override
    public String deleteMemberById(Long id) {
        Members members = membersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        membersRepository.delete(members);
        return "Member deleted successfully for ProductId " + id;
    }

    @Override
    public List<Members> searchMembers(String query) {
        return membersRepository.searchMembers(query);
    }

    @Override
    public void deleteByMemberId(String memberId) {
        membersRepository.deleteByMemberId(memberId);
    }

    @Override
    public void deleteAllMembers() {
        membersRepository.deleteAll();
    }



    @Override
    public Members findByMemberId(String memberId) {
        return membersRepository.findByMemberId(memberId);
    }


    @Override
    public Members findByFullName(String fullName) {
        return membersRepository.findByFullName(fullName);
    }

    @Transactional
    @Override
    public void deleteMemberWithPayments(Long memberId) {
        Members member = membersRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // Delete associated cash payments
        cashPaymentRepository.deleteByMember(member); // Assuming you have this method

        // Finally, delete the member
        membersRepository.delete(member);
    }


}

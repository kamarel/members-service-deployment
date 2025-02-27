package myKcc.com.Service;

import jakarta.transaction.Transactional;
import myKcc.com.ApiResponse.ParishDto;
import myKcc.com.ApiResponse.ParishResponseApi;
import myKcc.com.Entity.Members;
import myKcc.com.Repository.CashPaymentRepository;
import myKcc.com.Repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
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
        return membersRepository.findById(id).get();
    }

    @Override
    public Members saveMembers(Members members) {

        // Save the member to the database
        Members savedMember = membersRepository.save(members);

        // Fetch all parishes from the external service
        ParishResponseApi parishResponseApi = getAllParish();
        List<ParishDto> parishDtoList = parishResponseApi.getParishDtoList();

        // Collect all parish names
        List<String> parishNames = parishDtoList.stream()
                .map(ParishDto::getParishName)
                .collect(Collectors.toList());

        // Assign a parish to the member (e.g., based on the member ID, name, etc.)
        // For this example, we'll assign the parish randomly, but you can modify the logic as needed
        if (!parishNames.isEmpty()) {
            int randomIndex = new Random().nextInt(parishNames.size());
            String assignedParish = parishNames.get(randomIndex);

            // Assign the parish name to the member
            savedMember.setParishName(assignedParish);
        }

        // Save the member again with the assigned parish
        return membersRepository.save(savedMember);



    }


    @Override
    public ParishResponseApi getAllParish() {
        Mono<List<ParishDto>> listMono = webClient.get()
                .uri("https://worthy-stillness-production.up.railway.app/api/parish")
                .retrieve()
                .bodyToFlux(ParishDto.class)
                .collectList();


        List<ParishDto>parishDtoList = listMono.block();

        ParishResponseApi apiResponseDto = new ParishResponseApi();

        apiResponseDto.setParishDtoList(parishDtoList);

        return apiResponseDto;
    }



    @Override
    public Members updateMembers( Long id, Members members) {

        Members members1 = membersRepository.findById(id).get();

        if(members1 != null){
            members1.setMemberId(members.getMemberId());
            members1.setFullName(members.getFullName());
            members1.setEmail(members.getEmail());
            members1.setDateOfBirth(members.getDateOfBirth());
            members1.setSex(members.getSex());
            members1.setPhoneNumber(members.getPhoneNumber());
            members1.setMaritalStatus(members.getMaritalStatus());
            members1.setMemberRank(members.getMemberRank());
            members1.setMemberRole(members.getMemberRole());
            members1.setNationality(members.getNationality());
            members1.setPlaceOfBirth(members.getPlaceOfBirth());
            members1.setAddress(members.getAddress());
            members1.setState(members.getState());
            members1.setCity(members.getCity());
            members1.setZipCode(members.getZipCode());
            members1.setParishName(members.getParishName());






        }

        return membersRepository.save(members1);
    }

    @Override
    public String deleteMemberById(Long id) {

        Members members = membersRepository.findById(id).get();
        String deleteMessage = null;
        if(members != null){
            membersRepository.delete(members);

            deleteMessage = "Members deleted Successfully for ProductId " + id;
        }


        return  deleteMessage;
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

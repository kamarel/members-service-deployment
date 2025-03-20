package myKcc.com.Service;


import myKcc.com.ApiResponse.ParishResponseApi;
import myKcc.com.Entity.Members;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface MembersService {
    List<Members> getAllMembers();
    Members getById(Long id);
    Members saveMembers(Members members, String token);
    Members updateMembers( Long id, Members members);
    String deleteMemberById(Long id);
    List<Members>searchMembers(String query);
    void deleteByMemberId(String memberId);
    void deleteAllMembers();


    Members findByMemberId(String memberId);

    Members findByFullName(String fullName);
    void deleteMemberWithPayments(Long memberId);

    ParishResponseApi getAllParish(String token);
}

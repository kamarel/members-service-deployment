package myKcc.com.Service;


import myKcc.com.ApiResponse.ParishResponseApi;
import myKcc.com.Entity.Members;

import java.util.List;

public interface MembersService {
    List<Members> getAllMembers();
    Members getById(Long id);
    Members saveMembers(Members members);
    Members updateMembers( Long id, Members members);
    String deleteMemberById(Long id);
    List<Members>searchMembers(String query);
    void deleteByMemberId(String memberId);
    void deleteAllMembers();
    Members findByMemberId(String memberId);

    Members findByFullName(String fullName);
    void deleteMemberWithPayments(Long memberId);
    ParishResponseApi getAllParish();

}

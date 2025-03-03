package myKcc.com.Repository;


import myKcc.com.Entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MembersRepository extends JpaRepository<Members, Long> {


    Optional<Members> findByEmail(String email);
    void deleteByMemberId(String memberId);



    Members findByMemberId(String memberId);




    Members findByFullName(String fullName);

    @Query("SELECT p FROM Members p WHERE " +
            "CAST(p.id AS string) LIKE CONCAT('%', :query, '%') " +
            "OR p.memberId LIKE CONCAT('%', :query, '%') " +
            "OR p.fullName LIKE CONCAT('%', :query, '%') " +
            "OR p.email LIKE CONCAT('%', :query, '%') " +
            "OR p.phoneNumber LIKE CONCAT('%', :query, '%') " +
            "OR p.memberRole LIKE CONCAT('%', :query, '%') " +
            "OR p.memberRank LIKE CONCAT('%', :query, '%') " +
            "OR p.placeOfBirth LIKE CONCAT('%', :query, '%') " +
            "OR p.parishName LIKE CONCAT('%', :query, '%')")
    List<Members> searchMembers(@Param("query") String query);

}
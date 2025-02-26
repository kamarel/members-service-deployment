package myKcc.com.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "church_members")
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_id_seq")
    @SequenceGenerator(name = "event_id_seq", sequenceName = "YOUR_SEQUENCE_NAME", allocationSize = 1)
    private Long id;
    private String memberId;
    private String fullName;
    private String email;
    private String dateOfBirth;
    private String sex;
    private String phoneNumber;
    private String maritalStatus;
    private String memberRank;
    private String memberRole;
    private String nationality;
    private String placeOfBirth;
    private String address;
    private String country;
    private String state;
    private String city;
    private String zipCode;
    private String parishName;


 @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
 @JsonManagedReference
 private List<CashPayment> cashPayments;


}

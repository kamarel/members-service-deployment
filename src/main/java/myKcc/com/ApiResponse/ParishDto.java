package myKcc.com.ApiResponse;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParishDto {

    private Long id;
    private String parishId;
    private String parishName;
    private String parishLeaderName;
    private String deputyParishLeaderName;
    private String parishSecretary;
    private String parishCommittee;
    private String description;
    private String parishEmail;
    private String phoneNumber;
    private String dateCreated;
    private String country;
    private String address;
    private String state;
    private String city;
    private String zipCode;
}

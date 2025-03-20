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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParishId() {
        return parishId;
    }

    public void setParishId(String parishId) {
        this.parishId = parishId;
    }

    public String getParishName() {
        return parishName;
    }

    public void setParishName(String parishName) {
        this.parishName = parishName;
    }

    public String getParishLeaderName() {
        return parishLeaderName;
    }

    public void setParishLeaderName(String parishLeaderName) {
        this.parishLeaderName = parishLeaderName;
    }

    public String getDeputyParishLeaderName() {
        return deputyParishLeaderName;
    }

    public void setDeputyParishLeaderName(String deputyParishLeaderName) {
        this.deputyParishLeaderName = deputyParishLeaderName;
    }

    public String getParishSecretary() {
        return parishSecretary;
    }

    public void setParishSecretary(String parishSecretary) {
        this.parishSecretary = parishSecretary;
    }

    public String getParishCommittee() {
        return parishCommittee;
    }

    public void setParishCommittee(String parishCommittee) {
        this.parishCommittee = parishCommittee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParishEmail() {
        return parishEmail;
    }

    public void setParishEmail(String parishEmail) {
        this.parishEmail = parishEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}

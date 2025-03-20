package myKcc.com.ApiResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParishResponseApi {

    private List<ParishDto> parishDtoList;

    public List<ParishDto> getParishDtoList() {
        return parishDtoList;
    }

    public void setParishDtoList(List<ParishDto> parishDtoList) {
        this.parishDtoList = parishDtoList;
    }
}

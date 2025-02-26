package myKcc.com.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CashPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String method;
    private BigDecimal amount;


    private LocalDate date;
    private String cashierName;



    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Members member;

    @PrePersist
    protected void prePersist(){
        if(this.date == null){
            this.date = LocalDate.now();
        }


    }
}

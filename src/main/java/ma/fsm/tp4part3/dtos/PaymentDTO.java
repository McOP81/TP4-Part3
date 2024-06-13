package ma.fsm.tp4part3.dtos;

import jakarta.persistence.*;
import lombok.*;
import ma.fsm.tp4part3.entities.PaymentStatus;
import ma.fsm.tp4part3.entities.PaymentType;
import ma.fsm.tp4part3.entities.Student;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class PaymentDTO {
    private Long id;
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private PaymentStatus status;
}

package ma.fsm.tp4part3.repository;

import ma.fsm.tp4part3.entities.Payment;
import ma.fsm.tp4part3.entities.PaymentStatus;
import ma.fsm.tp4part3.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.security.core.parameters.P;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);
}

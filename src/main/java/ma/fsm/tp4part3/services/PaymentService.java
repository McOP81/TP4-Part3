package ma.fsm.tp4part3.services;

import jakarta.transaction.Transactional;
import ma.fsm.tp4part3.entities.Payment;
import ma.fsm.tp4part3.entities.PaymentStatus;
import ma.fsm.tp4part3.entities.PaymentType;
import ma.fsm.tp4part3.entities.Student;
import ma.fsm.tp4part3.repository.PaymentRepository;
import ma.fsm.tp4part3.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class PaymentService {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;

    public PaymentService(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
    }
    public Payment savePayment(MultipartFile file, LocalDate date, double amount, PaymentType type, String studentCode) throws IOException {
        Path folderPath = Paths.get(System.getProperty("user.home"),"TP4-Part3","payments");
        if (!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }
        String fileName = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"),"TP4-Part3","payments",fileName+".pdf");
        Files.copy(file.getInputStream(), filePath);
        Student student = studentRepository.findByCode(studentCode);
        Payment payment = Payment.builder()
                .date(date).type(type).student(student)
                .amount(amount)
                .file(filePath.toUri().toString())
                .status(PaymentStatus.CREATED)
                .build();
        return paymentRepository.save(payment);
    }
}

package ma.fsm.tp4part3;

import ma.fsm.tp4part3.entities.Payment;
import ma.fsm.tp4part3.entities.PaymentStatus;
import ma.fsm.tp4part3.entities.PaymentType;
import ma.fsm.tp4part3.entities.Student;
import ma.fsm.tp4part3.repository.PaymentRepository;
import ma.fsm.tp4part3.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;


@SpringBootApplication
public class Tp4Part3Application {

    public static void main(String[] args) {

        SpringApplication.run(Tp4Part3Application.class, args);
    }
    @Bean
    CommandLineRunner init(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        return args -> {
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstname("Mohamed").code("12345").programId("IAAD").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstname("Imane").code("123456").programId("SDIA").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstname("Yasmine").code("123457").programId("BDCC").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstname("Najat").code("123458").programId("IAAD").build());

            PaymentType[] paymentTypes =  PaymentType.values();
            Random random = new Random();
            studentRepository.findAll().forEach(st->{
                for(int i=0; i<10; i++){
                    int index = random.nextInt(paymentTypes.length);
                    Payment payment = Payment.builder()
                            .amount(1000+(int)(Math.random()+2000))
                            .type(paymentTypes[index])
                            .status(PaymentStatus.CREATED)
                            .date(LocalDate.now())
                            .student(st)
                            .build();
                    paymentRepository.save(payment);
                }

            });
        };
    }

}

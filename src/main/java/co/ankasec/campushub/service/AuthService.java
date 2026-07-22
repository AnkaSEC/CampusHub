package co.ankasec.campushub.service;

import co.ankasec.campushub.model.dto.SignupRequestDTO;
import co.ankasec.campushub.model.entity.Student;
import co.ankasec.campushub.model.enums.AccountType;
import co.ankasec.campushub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String register(SignupRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Bu e-posta adresi zaten kayıtlı!");
        }

        Student student = new Student();
        student.setUsername(request.getEmail());
        student.setName(request.getFullName());
        student.setEmail(request.getEmail());
        student.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        student.setUniversity(String.valueOf(request.getUniversityId()));
        student.setAccountType(AccountType.STUDENT);
        student.setActive(true);

        student.setStudentNumber(request.getStudentNumber());
        student.setDepartment(request.getDepartment());

        userRepository.save(student);

        return "Kayıt işlemi başarılı";
    }
}
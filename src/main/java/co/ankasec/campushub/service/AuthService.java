package co.ankasec.campushub.service;

import co.ankasec.campushub.helper.JwtUtils;
import co.ankasec.campushub.model.dto.AuthResponseDTO;
import co.ankasec.campushub.model.dto.LoginRequestDTO;
import co.ankasec.campushub.model.dto.RefreshTokenRequestDTO;
import co.ankasec.campushub.model.dto.SignupRequestDTO;
import co.ankasec.campushub.model.entity.Student;
import co.ankasec.campushub.model.entity.User;
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
    private final JwtUtils jwtUtils;

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

    public AuthResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı veya şifre hatalı!"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Kullanıcı bulunamadı veya şifre hatalı!");
        }

        String token = jwtUtils.generateAccessToken(user.getEmail());
        String refreshToken = jwtUtils.generateRefreshToken(user.getEmail());

        return AuthResponseDTO.builder()
                .token(token)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .email(user.getEmail())
                .build();
    }

    public AuthResponseDTO refreshToken(RefreshTokenRequestDTO request) {
        if (!jwtUtils.validateToken(request.getRefreshToken())) {
            throw new RuntimeException("Geçersiz veya süresi dolmuş Refresh Token!");
        }

        String email = jwtUtils.getEmailFromToken(request.getRefreshToken());

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));

        String newAccessToken = jwtUtils.generateAccessToken(user.getEmail());

        return AuthResponseDTO.builder()
                .token(newAccessToken)
                .refreshToken(request.getRefreshToken())
                .userId(user.getId())
                .email(user.getEmail())
                .build();
    }

    public String logout() {
        return "Çıkış işlemi başarılı.";
    }
}
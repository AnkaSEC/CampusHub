package co.ankasec.campushub.service;

import co.ankasec.campushub.model.dto.UserRequestDTO;
import co.ankasec.campushub.model.dto.UserResponseDTO;
import co.ankasec.campushub.model.entity.User;
import co.ankasec.campushub.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }


    public UserResponseDTO getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));
        return convertToResponseDTO(user);
    }


    public UserResponseDTO updateUser(UUID id, UserRequestDTO requestDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));

        if (requestDTO.getPassword() != null) {
            if (requestDTO.getPassword().length() < 8) {
                throw new IllegalArgumentException("Şifre en az 8 karakter olmalıdır!");
            }
            user.setPasswordHash(requestDTO.getPassword());
        }


        if (requestDTO.getPassword() != null)
            user.setPasswordHash(requestDTO.getPassword());
        if (requestDTO.getName() != null)
            user.setName(requestDTO.getName());
        if (requestDTO.getBio() != null)
            user.setBio(requestDTO.getBio());
        if (requestDTO.getPhotoUrl() != null)
            user.setPhotoUrl(requestDTO.getPhotoUrl());
        if (requestDTO.getUniversity() != null)
            user.setUniversity(requestDTO.getUniversity());


        User updatedUser = userRepository.save(user);
        return convertToResponseDTO(updatedUser);
    }


    private UserResponseDTO convertToResponseDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .photoUrl(user.getPhotoUrl())
                .bio(user.getBio())
                .university(user.getUniversity())
                .accountType(user.getAccountType())
                .isActive(user.isActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .verifiedAt(user.getVerifiedAt())
                .build();
    }
}
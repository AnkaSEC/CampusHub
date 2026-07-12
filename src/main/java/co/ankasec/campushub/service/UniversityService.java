package co.ankasec.campushub.service;

import co.ankasec.campushub.model.dto.UniversityRequestDTO;
import co.ankasec.campushub.model.dto.UniversityResponseDTO;
import co.ankasec.campushub.model.entity.University;
import co.ankasec.campushub.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UniversityService {

    private final UniversityRepository universityRepository;

    public List<UniversityResponseDTO> getAllUniversities() {
        return universityRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public UniversityResponseDTO getUniversityById(UUID id) {
        University university = universityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Üniversite bulunamadı"));

        return convertToResponseDTO(university);
    }

    public UniversityResponseDTO createUniversity(UniversityRequestDTO requestDTO) {
        University university = University.builder()
                .name(requestDTO.getName())
                .logoUrl(requestDTO.getLogoUrl())
                .domain(requestDTO.getDomain())
                .build();

        University savedUniversity = universityRepository.save(university);

        return convertToResponseDTO(savedUniversity);
    }

    private UniversityResponseDTO convertToResponseDTO(University university) {
        return UniversityResponseDTO.builder()
                .id(university.getId())
                .name(university.getName())
                .logoUrl(university.getLogoUrl())
                .domain(university.getDomain())
                .build();
    }

}
